package com.vn.osp.service;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.PagingResult;
import com.vn.osp.common.util.SystemProperties;
import com.vn.osp.modelview.*;
import com.vn.osp.modelview.Package;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranv on 12/20/2016.
 */
public class QueryFactory {
    public static String getClientInfo(HttpServletRequest request) {
        String clientInfo = request.getRemoteAddr() + " [" + request.getSession().getId() + "]";
        return clientInfo;
    }


    public static int countTotalUserList(String query) {
        int result = APIUtil.countTotalList(SystemProperties.getProperty("url_config_server_api")+"/users/countTotal", query);
        return result;
    }


    public static User getUserById(Long id) {
        String filter = "where id=" + id;
        List<User> result = APIUtil.getUserByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/selectByFilter", filter);
        if (result != null) return result.get(0);
        else return null;
    }

    public static List<User> getUserByAccount(String username) {
        String filter = "where account = '" + username + "'";
        List<User> result = APIUtil.getUserByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/selectByFilter", filter);
        return result;
    }

    public static List<User> getUserList(int page, String filter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if(offset < 0) offset = 0;
        filter = filter + " limit " + offset + "," + Constants.ROW_PER_PAGE;
        List<User> result = APIUtil.getUserByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/selectByFilter", filter);
        return result;
    }

    public static List<User> getAllUser() {
        String filter = " where active_flg=1";
        List<User> result = APIUtil.getUserByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/selectByFilter", filter);
        return result;
    }

    public static Boolean updateUser(String data) {
        Boolean result = APIUtil.updateUserAPI(SystemProperties.getProperty("url_config_server_api")+"/users/update-user", data);
        return result;
    }

    public static Boolean removeUser(String data) {
        Boolean result = APIUtil.updateUserAPI(SystemProperties.getProperty("url_config_server_api")+"/users/remove-user", data);
        return result;
    }

    public static List<GroupRole> getActiveGroupRole(String filter) {
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/select-group-role", "where active_flg=1");
        return result;
    }

    public static List<GroupRole> getActiveGroupRoleByPage(int page, String titleFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        String filter = " where active_flg =1 and grouprolename like '%" + titleFilter + "%'";
        filter = filter + " limit " + offset + "," + Constants.ROW_PER_PAGE;
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/select-group-role", filter);
        return result;
    }
    public static List<GroupRole> getGroupRoleByPage(int page, String titleFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        String filter = " where grouprolename like '%" + titleFilter + "%'";
        filter = filter + " limit " + offset + "," + Constants.ROW_PER_PAGE;
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/select-group-role", filter);
        return result;
    }

    public static Integer countTotalActiveGroupRole(String titleFilter) {
        String filter = " where active_flg =1 and grouprolename like '%" + titleFilter + "%'";
        Integer result = APIUtil.countTotalList(SystemProperties.getProperty("url_config_server_api")+"/users/count-total-group-role", filter);
        return result;
    }
    public static Integer countTotalGroupRole(String titleFilter) {
        String filter = " where grouprolename like '%" + titleFilter + "%'";
        Integer result = APIUtil.countTotalList(SystemProperties.getProperty("url_config_server_api")+"/users/count-total-group-role", filter);
        return result;
    }


    public static Integer countUserAuthorityByGroupId(Long groupId) {
        String filter = " where groupid=" + groupId;
        Integer result = APIUtil.countTotalList(SystemProperties.getProperty("url_config_server_api")+"/users/count-user-authority", filter);
        return result;
    }

    public static GroupRole getGroupRole(long id) {
        String filter = " where id=" + id + " ";
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/select-group-role", filter);
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public static List<UserGroupRole> getUserGroupRoleList(Long userId) {
        String filter = " where nua.user_Id =" + userId + " and active_flg=1";
        List<UserGroupRole> result = APIUtil.getUserGroupRoleByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/select-user-group-role", filter);
        return result;
    }

    public static Boolean permissionUser(String data) {
        Boolean result = APIUtil.callAPIResult(SystemProperties.getProperty("url_config_server_api")+"/users/permission-user", data);
        return result;
    }

    public static Boolean deleteGroupRoleAuthority(Long groupid) {
        Boolean result = APIUtil.callAPIResult(SystemProperties.getProperty("url_config_server_api")+"/users/delete-group-role-authority-by-groupid", groupid.toString());
        return result;
    }

    public static Boolean deleteGroupRole(Long groupid) {
        Boolean result = APIUtil.callAPIResult(SystemProperties.getProperty("url_config_server_api")+"/users/delete-group-role-by-groupid", groupid.toString());
        return result;
    }


    public static ArrayList<Authority> getAuthorityByFilter(String stringFilter) {
        ArrayList<Authority> result = APIUtil.getAuthorityByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/get-authority-by-filter", stringFilter);
        return result;
    }

    public static ArrayList<GrouproleAuthority> getGroupRoleAuthorityByFilter(String stringFilter) {
        ArrayList<GrouproleAuthority> result = APIUtil.getGroupRoleAuthorityFilter(SystemProperties.getProperty("url_config_server_api")+"/users/select-group-role-authority", stringFilter);
        return result;
    }

    public static GrouproleAuthority getGroupRoleAuthority(long grouprole_id) {
        String stringFilter = " where grouprole_id=" + grouprole_id;
        ArrayList<GrouproleAuthority> result = APIUtil.getGroupRoleAuthorityFilter(SystemProperties.getProperty("url_config_server_api")+"/users/select-group-role-authority", stringFilter);
        if (result != null && result.size() > 0) return result.get(0);
        else return null;
    }
    public static List<GrouproleAuthority> getGroupRoleAuthorites(long grouprole_id) {
        String stringFilter = " where grouprole_id=" + grouprole_id;
        ArrayList<GrouproleAuthority> result = APIUtil.getGroupRoleAuthorityFilter(SystemProperties.getProperty("url_config_server_api")+"/users/select-group-role-authority", stringFilter);
        return result;
    }

    public static ArrayList<UserAuthority> getUserAuthorityByFilter(String stringFilter) {
        ArrayList<UserAuthority> result = APIUtil.getUserAuthorityFilter(SystemProperties.getProperty("url_config_server_api")+"/users/select-user-authority-by-filter", stringFilter);
        return result;
    }

    public static ArrayList<Authority> getAuthorityByType(int type) {
        String filter = " where type=" + type;
        ArrayList<Authority> result = APIUtil.getAuthorityByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/get-authority-by-filter", filter);
        return result;
    }


    public static String getSystemConfigByKey(String key) {
        String result =  APIUtil.getSystemConfigByKey(SystemProperties.getProperty("url_config_server_api")+"/systemmanager/getConfigValue", key);
        return result;
    }

    public static ArrayList<Authority> getAuthorities() {
        ArrayList<Authority> result = APIUtil.getAuthorityByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/get-authority-by-filter", "where 1=1");
        return result;
    }

    public static Boolean createGroupRole(String data) {
        Boolean result = APIUtil.callAPIResult(SystemProperties.getProperty("url_config_server_api")+"/users/add-grouprole", data);
        return result;
    }

    public static Boolean updateGroupRole(String data) {
        Boolean result = APIUtil.callAPIResult(SystemProperties.getProperty("url_config_server_api")+"/users/update-grouprole", data);
        return result;
    }
    public static int countTotalProvince(String advanceSearchFilter) {
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter ;

        Integer result = APIUtil.countTotalList(SystemProperties.getProperty("url_config_server_api")+"/province/countProvinceByFilter", filter);
        return result;
    }
    public static List<Province> getProvince(int page, String  advanceSearchFilter){
        int offset = Constants.ROW_PER_PAGE * (page - 1 );
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        List<Province> result = APIUtil.getProvince(SystemProperties.getProperty("url_config_server_api")+"/province/findProvinceByFilter", filter);
        return result;
    }

    //api for question help
    public static Integer countTotalQuestion(String advanceSearchFilter) {
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1" ;
        String filter = advanceSearchFilter ;

        Integer result = APIUtil.countTotalList(SystemProperties.getProperty("url_config_server_api")+"/question-help/count", filter);
        return result;
    }
    public static List<Question> getQuestion(int page, String  advanceSearchFilter){
        int offset = Constants.ROW_PER_PAGE * (page - 1 );
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        List<Question> result = APIUtil.getQuestion(SystemProperties.getProperty("url_config_server_api")+"/question-help/search", filter);
        return result;
    }


    /*
    * author vietmanh
    * date 3/14/2017
    * Ham dem so trang
    * */
    public static int countPage(int total) {
        int rowPerpage = Constants.ROW_PER_PAGE;
        int result = 0;
        result = total / rowPerpage;
        int temp = total % rowPerpage;
        if (temp > 0) {
            result = result + 1;
            return result;
        } else return result;
    }



    public static List<EntryUserName> getEntryUserName(String notaryOfficeName) {
        ReportByUser reportByUser = new ReportByUser();
        reportByUser.setNotary_office_name(notaryOfficeName);
        List<EntryUserName> result = APIUtil.getEntryUserName(SystemProperties.getProperty("url_config_server_api")+"/transaction/selectEntryUserName", reportByUser.getNotary_office_name());
        return result;
    }


    public static List<User> getUser(String filter) {
        List<User> result = APIUtil.getUserByFilter(SystemProperties.getProperty("url_config_server_api")+"/users/selectByFilter", "where 1=1");
        return result;
    }

    public static int countTotalAccessHistory(int page,String advanceSearchFilter) {
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter  ;



        Integer result = APIUtil.countTotalList(SystemProperties.getProperty("url_config_server_api")+"/systemmanager/countTotal", filter);
        return result;
    }
    /*
  * author minhbq
  * date 3/23/2017
  * lich su truy cap he thong
  * */
    public static List<AccessHistory> getAccesHistory(int page, String  advanceSearchFilter){
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        System.out.println("Query vao "+ filter);
        List<AccessHistory> result = APIUtil.getAccessHistory(SystemProperties.getProperty("url_config_server_api")+"/systemmanager/selectAccessHistoryByFilter", filter);
        return result;
    }
    /*
  * author MANHTV
  * date 3/23/2017
  * lich su truy cap he thong
  * */
    public static Boolean setAccesHistory(AccessHistory accessHistory){
        Boolean result = APIUtil.callAPIResult(SystemProperties.getProperty("url_config_server_api")+"/systemmanager/setAccessHistory", accessHistory.generateUpdateJson());
        return result;
    }

    public static int countTotalContractKind(String advanceSearchFilter) {
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter ;

        Integer result = APIUtil.countTotalList(SystemProperties.getProperty("url_config_server_api")+"/contractkind/countContractKindByFilter", filter);
        return result;
    }
    public static int countTotalBank(String advanceSearchFilter) {
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter ;

        Integer result = APIUtil.countTotalList(SystemProperties.getProperty("url_config_server_api")+"/bank/CountBankByFilter", filter);
        return result;
    }
    public static int countTotalContractTemp(String advanceSearchFilter) {
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter ;

        Integer result = APIUtil.countTotalList(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/CountContractTempByFilter", filter);
        return result;
    }

    public static List<Bank> getBank(int page, String  advanceSearchFilter){
        int offset = Constants.ROW_PER_PAGE * (page - 1 );
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        List<Bank> result = APIUtil.getBank(SystemProperties.getProperty("url_config_server_api")+"/bank/FindBankByFilter", filter);
        return result;
    }
    public static List<ContractTemp> getContractTemp(int page, String  advanceSearchFilter){
        int offset = Constants.ROW_PER_PAGE * (page - 1 );
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        List<ContractTemp> result = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/FindContractTempByFilter", filter);
        return result;
    }
    public static List<ContractKind> getContractKind(int page, String  advanceSearchFilter){
        int offset = Constants.ROW_PER_PAGE * (page - 1 );
        if(advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        List<ContractKind> result = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api")+"/contractkind/findContractKindByFilter", filter);
        return result;
    }

    public static int addContractFee(String data){
        return APIUtil.addContractFee(SystemProperties.getProperty("url_config_server_api")+"/contractfee/AddContractFee", data);
    }
    public static int countTotalContractFee(String codeContract,String codeKind) {


        int result = APIUtil.countTotalContractFee(SystemProperties.getProperty("url_config_server_api")+"/contractfee/countContractFeeAllInnerJoinContractTemplate?codeContract="+codeContract+"&codeKind="+codeKind);
        return result;
    }

    public static List<ContractFeeBO> getContractFeeCode(int page,String code,String codeKind) {

        List<ContractFeeBO> result = APIUtil.getContractFeeCode(SystemProperties.getProperty("url_config_server_api")+"/contractfee/getContractFeeCodeJoinContractTemplate?page="+page+"&codeContract="+code+"&codeKind="+codeKind);
        return result;
    }

    public static ContractFeeBO getContractFeeId(int id) {

        ContractFeeBO result = APIUtil.getContractFeeId(SystemProperties.getProperty("url_config_server_api")+"/contractfee/getContractFeeId?id="+id);
        return result;
    }

    public static int updateContractFee(String data){
        return APIUtil.updateContractFee(SystemProperties.getProperty("url_config_server_api")+"/contractfee/UpdateContractFee", data);
    }

    public static Boolean deleteContractFee(int id) {

        Boolean result = APIUtil.deleteContractFee(SystemProperties.getProperty("url_config_server_api")+"/contractfee/deleteContractFee?id="+id);
        return result;
    }

    public static List<ContractFeeBO> getContractFeeOnlyCode(Long code) {

        List<ContractFeeBO> result = APIUtil.getContractFeeOnlyCode(SystemProperties.getProperty("url_config_server_api")+"/contractfee/getContractFeeOnlyCode?&codeContract="+code);
        return result;
    }

    public static List<ContractFeeBO> getContractFeeOnlyCodeExceptId(Long code,int id) {

        List<ContractFeeBO> result = APIUtil.getContractFeeOnlyCode(SystemProperties.getProperty("url_config_server_api")+"/contractfee/getContractFeeOnlyCodeExceptId?codeContract="+code+"&id="+id);
        return result;
    }

    // quyenlc add
    public static int countTotalCustomer(int page, String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;
        Integer result = APIUtil.countTotalList(Constants.OSP_API_LINK + "/customer/countTotalCustomer", filter);
        return result;
    }

    public static List<Customer> getCustomer(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        List<Customer> result = APIUtil.getCustomer(Constants.OSP_API_LINK + "/customer/selectByFilter", filter);
        return result;
    }

    public static List<ProvinceList> getAllProvince() {
        String filter = " where 1=1 order by name asc";
        List<ProvinceList> result = APIUtil.getProvinceList(Constants.OSP_API_LINK + "/province/findProvinceByFilter", filter);
        return result;
    }

    public static List<Function> getFunctionByType(int type) {
        String filter = " where type=" + type;
        List<Function> result = APIUtil.getFunctionByFilter(Constants.OSP_API_LINK +"/function/get-function-by-filter", filter);
        return result;
    }
    public static List<GroupRoleFunctionTemplate> getGroupRoleFunctionTemplateList(String filter) {
        List<GroupRoleFunctionTemplate> result = APIUtil.getGroupRoleFunctionTemplateList(Constants.OSP_API_LINK +"/function/get-group-role-function-template",filter);
        return result;
    }

    public static List<PackageFunction> selectPackageFunctionbyFilter(String filter) {
        List<PackageFunction> result = APIUtil.selectPackageFunctionbyFilter(Constants.OSP_API_LINK +"/packageFunction/get-package-function-by-filter",filter);
        return result;
    }

    public static List<Function> getFunctionByCode(String filter) {
        List<Function> result = APIUtil.getFunctionByCode(Constants.OSP_API_LINK +"/function/get-function-by-code", filter);
        return result;
    }

    public static Integer countTotalPackage(String titleFilter) {
        String filter = " where name like '%" + titleFilter + "%'";
        Integer result = APIUtil.countTotalList(SystemProperties.getProperty("url_config_server_api")+"/package/count-total-package", filter);
        return result;
    }

    public static List<Package> getPackageByPage(int page, String titleFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;

//        public static List<Customer> getCustomer(int page, String advanceSearchFilter) {
//            int offset = Constants.ROW_PER_PAGE * (page - 1);
//            if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
//            String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
//            String filter = advanceSearchFilter + paggingQuery;
//            List<Customer> result = APIUtil.getCustomer(Constants.OSP_API_LINK + "/customer/selectByFilter", filter);
//            return result;
//        }
        String filter = "";
        if (titleFilter == null || titleFilter.equals("")) {
            titleFilter = " where 1=1 ";
            filter = titleFilter;
        }
        else {
             filter = " where name like '%" + titleFilter + "%'";
             filter = filter + " limit " + offset + "," + Constants.ROW_PER_PAGE;
        }
        List<Package> result = APIUtil.getPackageByFilter(SystemProperties.getProperty("url_config_server_api")+"/package/get-package-by-filter", filter);
        return result;
    }
    public static List<Package> getAllPackage() {
        String filter = " where 1=1 order by name asc";
        List<Package> result = APIUtil.getAllPackage(Constants.OSP_API_LINK + "/package/get-package-by-filter", filter);
        return result;
    }

    public static List getAllCitizenVerifyPackages() {
        return APIUtil.getList(Constants.OSP_API_LINK + "/citizen-verify-packages");
    }


    public static PagingResult getPageCitizenVerifyOrder(CitizenVerifyOrdersWrapper citizenVerifyOrdersWrapper) {
        StringBuilder urlParams = new StringBuilder();
        urlParams.append("?page=").append(citizenVerifyOrdersWrapper.getPage());
        if(StringUtils.isNoneBlank(citizenVerifyOrdersWrapper.getOrder_id())) urlParams.append("&order_id=").append(citizenVerifyOrdersWrapper.getOrder_id());
        if(StringUtils.isNoneBlank(citizenVerifyOrdersWrapper.getNotary_office_code())) urlParams.append("&notary_office_code=").append(citizenVerifyOrdersWrapper.getNotary_office_code());
        if(StringUtils.isNoneBlank(citizenVerifyOrdersWrapper.getProvince_code())) urlParams.append("&province_code=").append(citizenVerifyOrdersWrapper.getProvince_code());
        if(StringUtils.isNoneBlank(citizenVerifyOrdersWrapper.getTransaction_status())) urlParams.append("&transaction_status=").append(citizenVerifyOrdersWrapper.getTransaction_status());
        if(StringUtils.isNoneBlank(citizenVerifyOrdersWrapper.getStatus())) urlParams.append("&status=").append(citizenVerifyOrdersWrapper.getStatus());
        if(StringUtils.isNoneBlank(citizenVerifyOrdersWrapper.getUpdate_by())) urlParams.append("&update_by=").append(citizenVerifyOrdersWrapper.getUpdate_by());
        if(StringUtils.isNoneBlank(citizenVerifyOrdersWrapper.getOrder_time_from())) urlParams.append("&order_time_from=").append(citizenVerifyOrdersWrapper.getOrder_time_from());
        if(StringUtils.isNoneBlank(citizenVerifyOrdersWrapper.getOrder_time_to())) urlParams.append("&order_time_to=").append(citizenVerifyOrdersWrapper.getOrder_time_to());
        if(StringUtils.isNoneBlank(citizenVerifyOrdersWrapper.getUpdate_user_name())) urlParams.append("&update_user_name=").append(citizenVerifyOrdersWrapper.getUpdate_user_name());

        String url = Constants.OSP_API_LINK + "/citizen-verify-orders/page"+urlParams.toString();
        PagingResult pagingResult = new PagingResult();
        try{
            pagingResult = APIUtil.getReturnObject(url);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return pagingResult;
    }

    public static Object updatePaymentTransaction(String data) {
        Object result = APIUtil.callApiPutMethod(SystemProperties.getProperty("url_config_server_api")+"/payment", data);
        return result;
    }

    public static Object updateCitizenVerifyOrder(String data) {
        Object result = APIUtil.callApiPutMethod(SystemProperties.getProperty("url_config_server_api")+"/citizen-verify-orders", data);
        return result;
    }

    public static PagingResult getPageCitizenVerification(CitizenVerificationsWrapper citizenVerificationsWrapper) throws UnsupportedEncodingException {
            StringBuilder urlParams = new StringBuilder();
        urlParams.append("?page=").append(citizenVerificationsWrapper.getPage());
        if(StringUtils.isNoneBlank(citizenVerificationsWrapper.getVerify_id())) urlParams.append("&verify_id=").append(citizenVerificationsWrapper.getVerify_id());
        if(StringUtils.isNoneBlank(citizenVerificationsWrapper.getNotary_office_id())) urlParams.append("&notary_office_id=").append(citizenVerificationsWrapper.getNotary_office_id());
        if(StringUtils.isNoneBlank(citizenVerificationsWrapper.getProvince_code())) urlParams.append("&province_code=").append(citizenVerificationsWrapper.getProvince_code());
        if(StringUtils.isNoneBlank(citizenVerificationsWrapper.getVerify_by())) urlParams.append("&verify_by=").append(URLEncoder.encode(citizenVerificationsWrapper.getVerify_by(), "UTF-8"));
        if(StringUtils.isNoneBlank(citizenVerificationsWrapper.getVerify_by_name())) urlParams.append("&verify_by_name=").append(URLEncoder.encode(citizenVerificationsWrapper.getVerify_by_name(), "UTF-8"));
        if(StringUtils.isNoneBlank(citizenVerificationsWrapper.getVerify_status())) urlParams.append("&verify_status=").append(citizenVerificationsWrapper.getVerify_status());
        if(StringUtils.isNoneBlank(citizenVerificationsWrapper.getCitizen_verify_cccd())) urlParams.append("&citizen_verify_cccd=").append(URLEncoder.encode(citizenVerificationsWrapper.getCitizen_verify_cccd(), "UTF-8"));
        if(StringUtils.isNoneBlank(citizenVerificationsWrapper.getCitizen_verify_fullname())) urlParams.append("&citizen_verify_fullname=").append(URLEncoder.encode(citizenVerificationsWrapper.getCitizen_verify_fullname(), "UTF-8"));
        if(StringUtils.isNoneBlank(citizenVerificationsWrapper.getVerify_date_from())) urlParams.append("&verify_date_from=").append(citizenVerificationsWrapper.getVerify_date_from());
        if(StringUtils.isNoneBlank(citizenVerificationsWrapper.getVerify_date_to())) urlParams.append("&verify_date_to=").append(citizenVerificationsWrapper.getVerify_date_to());

        String url = Constants.OSP_API_LINK + "/citizen-verifications/page"+urlParams.toString();
        PagingResult pagingResult = new PagingResult();
        try{
            pagingResult = APIUtil.getReturnObject(url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pagingResult;
    }

}
