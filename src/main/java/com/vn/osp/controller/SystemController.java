package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.SystemProperties;
import com.vn.osp.modelview.*;
import com.vn.osp.service.APIUtil;
import com.vn.osp.service.QueryFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tranv on 12/27/2016.
 */
@Controller
@RequestMapping("/system")
public class SystemController {
    @RequestMapping(value = "/add-user-view", method = RequestMethod.GET)
    public ModelAndView addUserViewer() {
        User user = new User();
        return new ModelAndView("/system/SM001","user",user);
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public ModelAndView addUser(User user , HttpServletRequest request, HttpServletResponse response) {
        user.valiate();

        if(user.getSuccess() == false) return new ModelAndView("/system/SM001", "user", user);
        Long id = APIUtil.addUserAPI("http://localhost:8081/api/users/add-user", user.generateAddJson());
        if(id < 0){
            user.setAccount_(SystemMessageProperties.getSystemProperty("v3_user_exits"));
            user.setSuccess(false);
            return new ModelAndView("/system/SM001", "user", user);
        }
        HttpSession session = request.getSession();
        session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_user_success"));
        return new ModelAndView("redirect:/system/user-list");
    }

    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public ModelAndView getUserList(UserList userList, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserList users = createUserList(userList);
        String action = (String)session.getAttribute(Constants.SESSION_ACTION);
        users.setAction_status(action);

        session.removeAttribute(Constants.SESSION_ACTION);
        return new ModelAndView("/system/SM002", "users", users);
    }

    public UserList createUserList(UserList userList) {
        int userListNumber = 0;
        int userTotalPage = 0;
        int page = 1;

        String family_name = "";
        String first_name = "";
        String account = "";
        int active_flg = 0;

        if (userList != null) {
            page = userList.getPage();
            family_name = userList.getFamily_name();
            first_name = userList.getFirst_name();
            account = userList.getAccount();
            active_flg = userList.getActive_flg();
        }

        userList.setAccount(account);
        userList.setFamily_name(family_name);
        userList.setFirst_name(first_name);
        userList.setActive_flg(active_flg);

        String query = userList.getFilter();
        userListNumber = QueryFactory.countTotalUserList(query);
        userTotalPage = QueryFactory.countPage(userListNumber);
        userList.setUserTotalPage(userTotalPage);
        userList.setUserListNumber(userListNumber);
        if (page < 1) page = 1;
        if (page > userTotalPage) page = userTotalPage;
        userList.setPage(page);
        userList.setUserList(QueryFactory.getUserList(page, query));

        return userList;
    }

    @RequestMapping(value = "/user-update-view/{userId}", method = RequestMethod.GET)
    public ModelAndView updateUserViewer(@PathVariable("userId") final String userId) {
        User user = QueryFactory.getUserById(Long.valueOf(userId));
        user.setUserId(Long.valueOf(userId));
        return new ModelAndView("/system/SM003", "user", user);
    }

    @RequestMapping(value = "/update-user", method = RequestMethod.POST)
    public ModelAndView updateUser(User user, HttpServletRequest request, HttpServletResponse response) {
        user.updateValiate();
        if(user.getSuccess() == false) return new ModelAndView("/system/SM003", "user", user);
        QueryFactory.updateUser(user.generateUpdateJson(user.getUserId()));
        HttpSession session = request.getSession();
        session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_user_update"));
        return new ModelAndView("redirect:/system/user-list");
    }

    @RequestMapping(value = "/remove-user/{id}", method = RequestMethod.GET)
    public ModelAndView removeUser(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Boolean result = QueryFactory.removeUser(id.toString());
        if(result){
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_user_delete_ok"));
            return new ModelAndView("redirect:/system/user-list");
        } else{
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_user_delete_not"));
            return new ModelAndView("redirect:/system/user-list");
        }
    }

    @RequestMapping(value = "/user-permission-view/{userId}", method = RequestMethod.GET)
    public ModelAndView userPermissionViewer(@PathVariable("userId") Long userId) {
        // load thong tin user
        User user = QueryFactory.getUserById(Long.valueOf(userId));
        //load tat ca nhom quyen
        List<GroupRole> groupRoles = QueryFactory.getActiveGroupRole("");
        UserPermissionForm userPermissionForm = new UserPermissionForm();
        userPermissionForm.setUser(user);
        userPermissionForm.setGroupRoles(groupRoles);
        //load nhom quyen cua usser
        List<UserGroupRole> userGroupRoles = QueryFactory.getUserGroupRoleList(user.getUserId());
        userPermissionForm.setUserGroupRoles(userGroupRoles);
        // dua nhom quyen cua user len giao dien
        userPermissionForm.mappingUserRole();

        return new ModelAndView("/system/SM004", "userPermissionForm", userPermissionForm);
    }

    @RequestMapping(value = "/user-permission", method = RequestMethod.POST)
    public String userPermission(UserPermisson userPermisson) {
        Boolean result = QueryFactory.permissionUser(userPermisson.generateJson());
        //if true
        //if false
        return "redirect:/system/user-list";
    }


    @RequestMapping(value = "/grouprole-list", method = RequestMethod.GET)
    public ModelAndView getGroupRoleList(GroupRoleListForm groupRoleListForm) {
        int listNumber = 0;
        int totalPage = 0;
        int page = 1;
        String titleFilter = "";
        if (groupRoleListForm != null) {
            listNumber = groupRoleListForm.getListNumber();
            totalPage = groupRoleListForm.getTotalPage();
            page = groupRoleListForm.getPage();
            titleFilter = groupRoleListForm.getTitleFilter();
        }
        if(titleFilter == null) titleFilter ="";
        listNumber = QueryFactory.countTotalGroupRole(titleFilter);
        totalPage = QueryFactory.countPage(listNumber);
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;
        groupRoleListForm.setListNumber(listNumber);
        groupRoleListForm.setTotalPage(totalPage);
        groupRoleListForm.setPage(page);
        groupRoleListForm.setTitleFilter(titleFilter);
        groupRoleListForm.setGroupRoles(QueryFactory.getGroupRoleByPage(page, titleFilter));

        return new ModelAndView("/system/SM0011", "groupRoleListForm", groupRoleListForm);
    }

    @RequestMapping(value = "/create-grouprole-view", method = RequestMethod.GET)
    public ModelAndView createGroupRoleView() {
        ArrayList<Authority> systemManagerList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
        ArrayList<Authority> functionList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_FUNCION);
        ArrayList<Authority> reportList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_REPORT);

        CreateGroupRoleForm createGroupRoleForm = new CreateGroupRoleForm();
        createGroupRoleForm.setSystemManagerList(systemManagerList);
        createGroupRoleForm.setFunctionList(functionList);
        createGroupRoleForm.setReportList(reportList);

        return new ModelAndView("/system/SM0012", "createGroupRoleForm", createGroupRoleForm);
    }

    @RequestMapping(value = "/create-grouprole", method = RequestMethod.POST)
    public String createGroupRole(CreateGroupRoleForm createGroupRoleForm) {
        Boolean result = QueryFactory.createGroupRole(createGroupRoleForm.generateAddJson());
        if(result) System.out.println("Them moi thanh cong");
        else System.out.println("Trung lap du lieu");
        return "redirect:/system/grouprole-list";
    }

    @RequestMapping(value = "/update-grouprole", method = RequestMethod.POST)
    public ModelAndView updateGroupRole(CreateGroupRoleForm createGroupRoleForm) {

        Boolean result = QueryFactory.updateGroupRole(createGroupRoleForm.generateUpdateJson());

        return new ModelAndView("/system/SM0012", "createGroupRoleForm", createGroupRoleForm);
    }
    @RequestMapping(value = "/delete-grouprole/{id}", method = RequestMethod.GET)
    public String deleteGroupRole(@PathVariable("id") Long id) {
        int numberUserAuthority = QueryFactory.countUserAuthorityByGroupId(id);
        if(numberUserAuthority > 0){
            System.out.println("Đang có cán bộ Sở Tư pháp sử dụng Nhóm quyền này. Xóa Nhóm quyền không thành công");
        } else{
            Boolean deleteGroupRoleAuthority = QueryFactory.deleteGroupRoleAuthority(id);
            //neu xoa thanh cong trong bang npo_grouprole_authority
            if(deleteGroupRoleAuthority){
                QueryFactory.deleteGroupRole(id);
                System.out.println("Xóa thành cong");
            }
        }
        return "redirect:/system/grouprole-list";
    }


    @RequestMapping(value = "/update-grouprole-view/{id}", method = RequestMethod.GET)
    public ModelAndView updateGroupRoleView(@PathVariable("id") long id) {
        CreateGroupRoleForm createGroupRoleForm = new CreateGroupRoleForm();

        ArrayList<Authority> systemManagerList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
        ArrayList<Authority> functionList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_FUNCION);
        ArrayList<Authority> reportList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_REPORT);

        createGroupRoleForm.setSystemManagerList(systemManagerList);
        createGroupRoleForm.setFunctionList(functionList);
        createGroupRoleForm.setReportList(reportList);

        //lay du lieu grouprole
        GroupRole groupRole = QueryFactory.getGroupRole(id);
        createGroupRoleForm.setGroupRoleId(groupRole.getGroupRoleId());
        createGroupRoleForm.setGrouprolename(groupRole.getGrouprolename());
        createGroupRoleForm.setDescription(groupRole.getDescription());
        createGroupRoleForm.setActive_flg(groupRole.getActive_flg());
        createGroupRoleForm.setEntry_user_id(groupRole.getEntry_user_id());
        createGroupRoleForm.setEntry_user_name(groupRole.getEntry_user_name());
        createGroupRoleForm.setEntry_date_time(groupRole.getEntry_date_time());

        createGroupRoleForm.setUpdate_user_id(groupRole.getUpdate_user_id());
        createGroupRoleForm.setUpdate_user_name(groupRole.getUpdate_user_name());
        createGroupRoleForm.setUpdate_date_time(groupRole.getUpdate_date_time());

        //lay giu lieu grouprole_authority
        List<GrouproleAuthority> grouproleAuthorities = QueryFactory.getGroupRoleAuthorityByFilter(" where grouprole_id=" + id);
        String cb01 = "";
        String cb02 = "";
        String cb03 = "";
        for (int i = 0; i < grouproleAuthorities.size(); i++) {
            GrouproleAuthority grouproleAuthority = grouproleAuthorities.get(i);
            int value = grouproleAuthority.getValue();
            String authority_code = grouproleAuthority.getAuthority_code();
            List<Authority> authorities = QueryFactory.getAuthorityByFilter(" where code like '" + authority_code + "'");
            long type = authorities.get(0).getType();
            if (type == 1) {
                cb01 = generateAuthoryCode(cb01, authority_code, value);
            }
            if (type == 2) {
                cb02 = generateAuthoryCode(cb02, authority_code, value);
            }
            if (type == 3) {
                cb03 = generateAuthoryCode(cb03, authority_code, value);
            }
        }

        createGroupRoleForm.setCb01(cb01);
        createGroupRoleForm.setCb02(cb02);
        createGroupRoleForm.setCb03(cb03);

        return new ModelAndView("/system/SM0013", "createGroupRoleForm", createGroupRoleForm);
    }


    /*
    * @author vietmanh
    * @date 3/14/2017
    * Ham generate chuoi authority code de dua len giao dien
    * */

    public String generateAuthoryCode(String cb, String authority_code, int value) {
        int temp64 = value & Constants.AUTHORITY_XEM;
        int temp32 = value & Constants.AUTHORITY_THEM;
        int temp16 = value & Constants.AUTHORITY_SUA;
        int temp8 = value & Constants.AUTHORITY_XOA;
        int temp4 = value & Constants.AUTHORITY_TIMKIEM;
        int temp2 = value & Constants.AUTHORITY_IN;
        int temp1 = value & Constants.AUTHORITY_BACKUP;
        int item = 0;
        if (temp64 > 0) {
            cb = cb + authority_code + "_64,";
            item++;
        }
        if (temp32 > 0) {
            cb = cb + authority_code + "_32,";
            item++;
        }
        if (temp16 > 0) {
            cb = cb + authority_code + "_16,";
            item++;
        }
        if (temp8 > 0) {
            cb = cb + authority_code + "_8,";
            item++;
        }
        if (temp4 > 0) {
            cb = cb + authority_code + "_4,";
            item++;
        }
        if (temp2 > 0) {
            cb = cb + authority_code + "_2,";
            item++;
        }
        if (temp1 > 0) {
            cb = cb + authority_code + "_1,";
            item++;
        }
        if (item == 7) cb = cb + authority_code + "_0,";
        return cb;
    }

    /*
  * @author minhbq
  * @date 3/19/2017
  * Lich su truy cap he thong
  * */
    @RequestMapping(value = "/access-history", method = RequestMethod.GET)
    public ModelAndView getAccessHistory(AccessHistoryList accessHistoryList) {
        accessHistoryList.createFromToDate();

        int accessListNumber = 1;
        int accessTotalPage = 1;
        int page = 1;

        if(accessHistoryList != null){
            accessListNumber= accessHistoryList.getTotal();
            accessTotalPage= accessHistoryList.getTotalPage();
            page = accessHistoryList.getPage();

        }
        Long userId = accessHistoryList.getUserId();
        String executeDateTime = accessHistoryList.getExecute_date_time();
        String clientInfo = accessHistoryList.getClient_info();
        Integer accessType = accessHistoryList.getAccess_type();


        if(accessTotalPage<1) accessTotalPage = 1;
        if (page <1) page = 1;
        if (page > accessTotalPage) page = accessTotalPage;

        accessHistoryList.setPage(page);

        String query = accessHistoryList.getOrderString();

        accessHistoryList.setAccessHistories(QueryFactory.getAccesHistory(page, query));
        accessListNumber = QueryFactory.countTotalAccessHistory(page, query);
        accessTotalPage = QueryFactory.countPage(accessListNumber);

        accessHistoryList.setTotal(accessListNumber);
        accessHistoryList.setTotalPage(accessTotalPage);

        accessHistoryList.setUserId(userId);
        accessHistoryList.setExecute_date_time(executeDateTime);
        accessHistoryList.setClient_info(clientInfo);
        accessHistoryList.setAccess_type(accessType);

        List<User> users = QueryFactory.getUser(null);
        accessHistoryList.setUserList(users);

        return new ModelAndView("/contract/CTR010","accessHistoryList", accessHistoryList);
    }

    @RequestMapping(value = "/backup-init", method = RequestMethod.POST)
    public ModelAndView backupInit(ConfigBackupDatabaseForm cfBackupForm, HttpServletRequest request, HttpServletResponse response) {

        if(cfBackupForm.getPathBackUp() != null && cfBackupForm.getPathBackUp().length() > 0){
            if(cfBackupForm.getPathBackUp().trim().indexOf(" ") > 0){
                cfBackupForm.setSuccess(false);
                cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_cfg_backup_filepath"));
                return new ModelAndView("/system/SM0014","cfBackup",cfBackupForm);
            } else{
                SystemProperties.setProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER, EditString.convertToFilePath(cfBackupForm.getPathBackUp().replaceAll("\\\\", "/")));
            }
        } else{
            cfBackupForm.setSuccess(false);
            cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_cfg_backup_not_filepath"));
            return new ModelAndView("/system/SM0014","cfBackup",cfBackupForm);
        }
        SystemProperties.setProperty(Constants.CHECK_BACKUP_DATABASE, "true");
        SystemProperties.setProperty(Constants.CONFIG_TIME_BACKUP, cfBackupForm.getBackupTime());

        String dateBackUp = String.valueOf(cfBackupForm.isMon()) + " "
                + String.valueOf(cfBackupForm.isTue()) + " "
                + String.valueOf(cfBackupForm.isWed()) + " "
                + String.valueOf(cfBackupForm.isThu()) + " "
                + String.valueOf(cfBackupForm.isFri()) + " "
                + String.valueOf(cfBackupForm.isSat()) + " "
                + String.valueOf(cfBackupForm.isSun());
        SystemProperties.setProperty(Constants.CONFIG_DATE_BACKUP, dateBackUp);
        cfBackupForm.setSuccess(true);
        cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("v3_config_backup_success"));

        return new ModelAndView("/system/SM0016","cfBackup",cfBackupForm);
    }
    @RequestMapping(value = "/backup-init-update", method = RequestMethod.POST)
    public ModelAndView backupInitUpdate(ConfigBackupDatabaseForm cfBackupForm, HttpServletRequest request, HttpServletResponse response) {

        if(cfBackupForm.getPathBackUp() != null && cfBackupForm.getPathBackUp().length() > 0){
            if(cfBackupForm.getPathBackUp().trim().indexOf(" ") > 0){
                cfBackupForm.setSuccess(false);
                cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_cfg_backup_filepath"));
                return new ModelAndView("/system/SM0014","cfBackup",cfBackupForm);
            } else{
                SystemProperties.setProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER, EditString.convertToFilePath(cfBackupForm.getPathBackUp().replaceAll("\\\\", "/")));
            }
        } else{
            cfBackupForm.setSuccess(false);
            cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_cfg_backup_not_filepath"));
            return new ModelAndView("/system/SM0014","cfBackup",cfBackupForm);
        }
        System.out.println("update="+cfBackupForm.isCheckBackup());
        SystemProperties.setProperty(Constants.CHECK_BACKUP_DATABASE, String.valueOf(cfBackupForm.isCheckBackup()));
        SystemProperties.setProperty(Constants.CONFIG_TIME_BACKUP, cfBackupForm.getBackupTime());

        String dateBackUp = String.valueOf(cfBackupForm.isMon()) + " "
                + String.valueOf(cfBackupForm.isTue()) + " "
                + String.valueOf(cfBackupForm.isWed()) + " "
                + String.valueOf(cfBackupForm.isThu()) + " "
                + String.valueOf(cfBackupForm.isFri()) + " "
                + String.valueOf(cfBackupForm.isSat()) + " "
                + String.valueOf(cfBackupForm.isSun());
        SystemProperties.setProperty(Constants.CONFIG_DATE_BACKUP, dateBackUp);
        cfBackupForm.setSuccess(true);
        if(!cfBackupForm.isCheckBackup()) cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("v3_backup_stop"));
        else cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("msg_save_backup_succes"));

        if(cfBackupForm.isCheckBackup() == true) return new ModelAndView("/system/SM0016","cfBackup",cfBackupForm);
        else return new ModelAndView("/system/SM0017","cfBackup",cfBackupForm);
        //return new ModelAndView("redirect:/system/backup-view");
    }
    @RequestMapping(value = "/backup-init-view", method = RequestMethod.GET)
    public ModelAndView backupView(HttpServletRequest request, HttpServletResponse response) {

        ConfigBackupDatabaseForm cfBackup = new ConfigBackupDatabaseForm();
        cfBackup.setPathBackUp(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
        cfBackup.setCheckBackup(Boolean.valueOf(SystemProperties.getProperty(Constants.CHECK_BACKUP_DATABASE)));
        cfBackup.setBackupTime(SystemProperties.getProperty(Constants.CONFIG_TIME_BACKUP));
        List <Boolean> listDatebackup = null;
        listDatebackup = EditString.parseListDateBackup(SystemProperties.getProperty(Constants.CONFIG_DATE_BACKUP));
        cfBackup.setMon(listDatebackup.get(0));
        cfBackup.setTue(listDatebackup.get(1));
        cfBackup.setWed(listDatebackup.get(2));
        cfBackup.setThu(listDatebackup.get(3));
        cfBackup.setFri(listDatebackup.get(4));
        cfBackup.setSat(listDatebackup.get(5));
        cfBackup.setSun(listDatebackup.get(6));
        return new ModelAndView("/system/SM0014","cfBackup",cfBackup);
    }
    @RequestMapping(value = "/backup-update-view", method = RequestMethod.GET)
    public ModelAndView backupUpdateView(HttpServletRequest request, HttpServletResponse response) {

        ConfigBackupDatabaseForm cfBackup = new ConfigBackupDatabaseForm();
        cfBackup.setPathBackUp(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
        cfBackup.setCheckBackup(Boolean.valueOf(SystemProperties.getProperty(Constants.CHECK_BACKUP_DATABASE)));
        cfBackup.setBackupTime(SystemProperties.getProperty(Constants.CONFIG_TIME_BACKUP));
        List <Boolean> listDatebackup = null;
        listDatebackup = EditString.parseListDateBackup(SystemProperties.getProperty(Constants.CONFIG_DATE_BACKUP));
        cfBackup.setMon(listDatebackup.get(0));
        cfBackup.setTue(listDatebackup.get(1));
        cfBackup.setWed(listDatebackup.get(2));
        cfBackup.setThu(listDatebackup.get(3));
        cfBackup.setFri(listDatebackup.get(4));
        cfBackup.setSat(listDatebackup.get(5));
        cfBackup.setSun(listDatebackup.get(6));
        return new ModelAndView("/system/SM0015","cfBackup",cfBackup);
    }
    @RequestMapping(value = "/backup-view", method = RequestMethod.GET)
    public ModelAndView backupconfig(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ConfigBackupDatabaseForm cfBackup = (ConfigBackupDatabaseForm)session.getAttribute(ConfigBackupDatabaseForm.SESSON_KEY);

        if(cfBackup == null) cfBackup = new ConfigBackupDatabaseForm();
        session.removeAttribute(ConfigBackupDatabaseForm.SESSON_KEY);
        cfBackup.setPathBackUp(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
        cfBackup.setCheckBackup(Boolean.valueOf(SystemProperties.getProperty(Constants.CHECK_BACKUP_DATABASE)));
        cfBackup.setBackupTime(SystemProperties.getProperty(Constants.CONFIG_TIME_BACKUP));
        List <Boolean> listDatebackup = null;
        listDatebackup = EditString.parseListDateBackup(SystemProperties.getProperty(Constants.CONFIG_DATE_BACKUP));
        cfBackup.setMon(listDatebackup.get(0));
        cfBackup.setTue(listDatebackup.get(1));
        cfBackup.setWed(listDatebackup.get(2));
        cfBackup.setThu(listDatebackup.get(3));
        cfBackup.setFri(listDatebackup.get(4));
        cfBackup.setSat(listDatebackup.get(5));
        cfBackup.setSun(listDatebackup.get(6));
        if(cfBackup.isCheckBackup() == true) return new ModelAndView("/system/SM0016","cfBackup",cfBackup);
        else return new ModelAndView("/system/SM0017","cfBackup",cfBackup);
    }
    @RequestMapping(value = "/backup-now", method = RequestMethod.GET)
    public ModelAndView backupNow(HttpServletRequest request, HttpServletResponse response){
        ConfigBackupDatabaseForm configBackupDatabaseForm = new ConfigBackupDatabaseForm();
        HttpSession session = request.getSession();

        File file = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
        if(!file.exists()){
            file.mkdirs();
        }
        String database = SystemProperties.getProperty("database");
        String user = SystemProperties.getProperty("user");
        String pass = SystemProperties.getProperty("pass");
        String host = SystemProperties.getProperty("host");
        Calendar cal = Calendar.getInstance();
        String fileBackUpName = String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + "h" + String.valueOf(cal.get(Calendar.MINUTE)) + "p_" + String.valueOf(cal.get(Calendar.DATE)) + "-" + String.valueOf(cal.get(Calendar.MONDAY) + 1) + "-" + String.valueOf(cal.get(Calendar.YEAR)) + ".sql";
        String fileBackupPath = SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER);
        String[] cmd = new String[10];
        int i = 0;
        cmd[i++] = SystemProperties.getProperty(Constants.CONFIG_MYSQL_DUMP_FOLDER).substring(0, 2);
        cmd[i++] = "cd \"" + SystemProperties.getProperty(Constants.CONFIG_MYSQL_DUMP_FOLDER) + "\"";
        cmd[i++] = "mysqldump -u" + user + " -p" + pass + " -h" + host + " " + database + " -r \"" + fileBackupPath + fileBackUpName + "\"";

        Runtime c = Runtime.getRuntime();
        createFileBackUpOrRetore(cmd, Constants.FILE_NAME_BACKUP, i);
        String cmdStr = "cmd /c start " + SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER)+ Constants.FILE_NAME_BACKUP;
        try {
            Process pro = c.exec(cmdStr , null);
            if(pro.waitFor() == 0){
                configBackupDatabaseForm.setSuccess(true);
                configBackupDatabaseForm.setAction_status(SystemMessageProperties.getSystemProperty("msg_backup_succes"));
                session.setAttribute(ConfigBackupDatabaseForm.SESSON_KEY, configBackupDatabaseForm);
            } else{
                configBackupDatabaseForm.setSuccess(false);
                configBackupDatabaseForm.setAction_status(SystemMessageProperties.getSystemProperty("err_backup_database"));
                session.setAttribute(ConfigBackupDatabaseForm.SESSON_KEY, configBackupDatabaseForm);
            }
        } catch (Exception e) {
            configBackupDatabaseForm.setSuccess(false);
            configBackupDatabaseForm.setAction_status(SystemMessageProperties.getSystemProperty("err_backup_database"));
            session.setAttribute(ConfigBackupDatabaseForm.SESSON_KEY, configBackupDatabaseForm);
        }
        return new ModelAndView("redirect:/system/backup-view");
    }
    public void createFileBackUpOrRetore(String[] fileContent, String fileName, int length){
        try {
            File file = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER) + fileName);
            File folder = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
            if(file.exists()){
                file.delete();
            } else{
                if(!folder.exists())
                    folder.mkdirs();
            }
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            for(int i=0; i<length; i++){
                writer.println(fileContent[i]);
                System.out.println(fileContent[i]);
            }
            writer.println("Exit");
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/backup-list", method = RequestMethod.GET)
    public ModelAndView backupList(BackupList backupList, HttpServletRequest request, HttpServletResponse response) {
        backupList = saveList(backupList.getFilename());
        return new ModelAndView("/system/SM0018","backupList",backupList);
    }
    public BackupList saveList(String key){
        System.out.println("key="+key);
        BackupList backupList = new BackupList();
        File folder = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
        if(!folder.exists()){
            folder.mkdirs();
        }
        File[] listFiles = folder.listFiles();
        ArrayList<BackupInfo> listBackup = new ArrayList<BackupInfo>();
        BackupInfo info = null;
        SimpleDateFormat splFomat = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
        for(File file : listFiles){
            if(!EditString.isNull(key)){
                if(file.getName().indexOf(key) > -1){
                    info = new BackupInfo();
                    if(!file.getName().equals("backup.bat") && !file.getName().equals("backupnow.bat") && !file.getName().equals("restore.bat") && !file.isDirectory()){
                        info.setFileName(file.getName());
                        info.setDateFile(splFomat.format(file.lastModified()));
                        listBackup.add(info);
                    }
                }
            }else{
                info = new BackupInfo();
                if(!file.getName().equals("backup.bat") && !file.getName().equals("backupnow.bat") && !file.getName().equals("restore.bat") && !file.isDirectory()){
                    info.setFileName(file.getName());
                    info.setDateFile(splFomat.format(file.lastModified()));
                    listBackup.add(info);
                }
            }
        }
        backupList.setList(listBackup);
        return backupList;
    }
    @RequestMapping(value = "/restore-view", method = RequestMethod.GET)
    public ModelAndView restoreView(HttpServletRequest request, HttpServletResponse response){

        return new ModelAndView("/system/SM0019");
    }
    //@RequestMapping(value = "/restore", method = RequestMethod.GET)
    public ModelAndView restore(ConfigBackupDatabaseForm cfBackupForm, HttpServletRequest request, HttpServletResponse response){
            HttpSession session = request.getSession();
            String filePath = cfBackupForm.getFileRestore();
            File file = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
            if (!file.exists()) {
                file.mkdirs();
            }
            String database = SystemProperties.getProperty("database");
            String user = SystemProperties.getProperty("user");
            String pass = SystemProperties.getProperty("pass");
            String host = SystemProperties.getProperty("host");
            System.out.println("host=" + host);

            File fileBackUp = new File(filePath.replaceAll("\\\\", "/"));
            if(!fileBackUp.exists()) System.out.println("1");
        if(!fileBackUp.isDirectory()) System.out.println("2");
        if(!fileBackUp.canRead()) System.out.println("3");
            System.out.println("cmnr="+filePath.replaceAll("\\\\", "/"));
            if (fileBackUp.exists() && !fileBackUp.isDirectory() && fileBackUp.canRead()) {
                System.out.println("exits");
                String[] cmd = new String[10];
                int i = 0;
                cmd[i++] = SystemProperties.getProperty(Constants.CONFIG_MYSQL_DUMP_FOLDER).substring(0, 2);
                cmd[i++] = "cd \"" + SystemProperties.getProperty(Constants.CONFIG_MYSQL_DUMP_FOLDER) + "\"";
                cmd[i++] = "mysql -u" + user + " -p" + pass + " -h" + host + " " + database + " < \"" + filePath.replaceAll("\\\\", "/") + "\"";

                Runtime c = Runtime.getRuntime();
                createFileBackUpOrRetore(cmd, Constants.FILE_NAME_RESTORE, i);
                String cmdStr = "cmd /c start " + SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER) + Constants.FILE_NAME_RESTORE;
                try {
                    Process pro = c.exec(cmdStr, null);
                    if (pro.waitFor() == 0) {
                        cfBackupForm.setSuccess(true);
                        cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("msg_restore_success"));
                        session.setAttribute(ConfigBackupDatabaseForm.SESSON_KEY, cfBackupForm);
                    } else {
                        cfBackupForm.setSuccess(false);
                        cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_restore_database"));
                        session.setAttribute(ConfigBackupDatabaseForm.SESSON_KEY, cfBackupForm);
                    }
                } catch (Exception e) {
                    cfBackupForm.setSuccess(false);
                    cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_restore_database"));
                    session.setAttribute(ConfigBackupDatabaseForm.SESSON_KEY, cfBackupForm);
                }
            } else {
                cfBackupForm.setSuccess(false);
                cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_restore_database_file_path"));
                session.setAttribute(ConfigBackupDatabaseForm.SESSON_KEY, cfBackupForm);
            }

            return new ModelAndView("redirect:/system/backup-view");

    }
    @RequestMapping(value = "/remove-backup", method = RequestMethod.GET)
    public ModelAndView remove(BackupList backupList, HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String cb11 = backupList.getCb11();
        System.out.println(cb11);
        String[] filenames = cb11.split(",");

        if(filenames == null || filenames.length <= 0){
            System.out.println("FILE_not_selected_announcement");
            /*ActionErrors error = new ActionErrors();
            error.add(new MessageUtil().createActionMessages("", "FILE_not_selected_announcement"));
            this.addErrors(request, error);
            return mapping.findForward(SUCCESS);*/
        } else{
            for(String fileName : filenames){
                File file = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER) + fileName.replaceAll("&#39;", "'"));
                if(file.exists()){
                    file.delete();
                }
            }
            System.out.println("xong");
        }
        return null;
        /*viewHelper.reset(f);
        ActionMessages messages = new ActionMessages();
        messages.add(new MessageUtil().createActionMessages("", request, "msg_delete_success", "item_fie"));
        this.addMessages(request, messages);

        return mapping.findForward(SUCCESS);*/
    }

}
