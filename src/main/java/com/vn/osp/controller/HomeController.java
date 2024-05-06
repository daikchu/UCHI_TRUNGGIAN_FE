package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.Crypter;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.*;
import com.vn.osp.service.QueryFactory;
import com.vn.osp.service.mail.SendMailTLS;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by tranv on 12/14/2016.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView toHome(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(User.SESSION_KEY) != null) {
            HomeForm homeForm = new HomeForm();
            return new ModelAndView("index", "homeForm", homeForm);
        } else return new ModelAndView("login", "loginForm", null);

    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CommonContext commonContext = new CommonContext();
        if (session.getAttribute(User.SESSION_KEY) != null) {
            User user = (User) session.getAttribute(User.SESSION_KEY);
            commonContext.setUser(user);
            HomeForm homeForm = new HomeForm();
            // lay du lieu grouprole
            ArrayList<GroupRole> groupRoles = new ArrayList<GroupRole>();
            Long userId = user.getUserId();
            List<UserAuthority> userAuthorities = QueryFactory.getUserAuthorityByFilter(" where user_id=" + userId);
            if (userAuthorities != null && userAuthorities.size() > 0) {
                for (int i = 0; i < userAuthorities.size(); i++) {
                    GroupRole groupRole = QueryFactory.getGroupRole(userAuthorities.get(i).getGroupid());
                    if (groupRole != null) groupRoles.add(groupRole);
                }
            }
            commonContext.setGroupRoles(groupRoles);
            //lay du lieu grouprole authority
            ArrayList<GrouproleAuthority> grouproleAuthorities = new ArrayList<GrouproleAuthority>();
            for (int i = 0; i < groupRoles.size(); i++) {
                List<GrouproleAuthority> part = QueryFactory.getGroupRoleAuthorites(groupRoles.get(i).getGroupRoleId());
                if (part != null && part.size() > 0) {
                    for (int j = 0; j < part.size(); j++) {
                        grouproleAuthorities.add(part.get(j));
                    }
                }
            }
            commonContext.setGrouproleAuthorities(grouproleAuthorities);
            //lay du lieu config
            String agent = QueryFactory.getSystemConfigByKey("notary_office_name");
            commonContext.setAgency(agent);


            session.setAttribute(commonContext.SESSION_KEY, commonContext);

            return new ModelAndView("index", "homeForm", homeForm);
        } else return new ModelAndView("login", "loginForm", null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(LoginForm loginForm, HttpServletRequest request) {
        loginForm.valiate();
        if (loginForm.getSuccess() == false) return new ModelAndView("login", "loginForm", loginForm);
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        List<User> users = QueryFactory.getUserByAccount(username);
        if (users != null && users.size() > 0) {
            User user = users.get(0);
            if (Crypter.matches(user.getPassword(), password)) {
                //session
                HttpSession session = request.getSession();
                session.setAttribute(User.SESSION_KEY, user);
                //set access history
                setAccessHistory(user, Constants.LOGIN, request);
                return new ModelAndView("redirect:/home");
            } else {
                // sai pass
                loginForm.setPassword_(SystemMessageProperties.getSystemProperty("v3_login_error"));
                loginForm.setSuccess(false);
                return new ModelAndView("login", "loginForm", loginForm);
            }
        } else {
            //khong ton tai username
            loginForm.setPassword_(SystemMessageProperties.getSystemProperty("v3_login_error"));
            loginForm.setSuccess(false);
            return new ModelAndView("login", "loginForm", loginForm);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        setAccessHistory(CommonContext.user, Constants.LOGOUT, request);
        HttpSession session = request.getSession();
        session.removeAttribute(User.SESSION_KEY);
        session.removeAttribute(CommonContext.SESSION_KEY);
        return "redirect:/login-view";
    }

    @RequestMapping(value = "/login-view", method = RequestMethod.GET)
    public String loginView() {
        return "login";
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
    public String forgetPassword() {
        return "forgot-password";
    }

    @RequestMapping(value = "/contact-us", method = RequestMethod.GET)
    public String contact() {
        return "contact-us";
    }

    @RequestMapping(value = "/send-email", method = RequestMethod.POST)
    public ModelAndView sendEmail(ForgetPasswordForm forgetPasswordForm) {
        try {
            forgetPasswordForm.valiate();
            if (forgetPasswordForm.getSuccess() == false)
                return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
            String account = forgetPasswordForm.getAccount();
            String email = forgetPasswordForm.getEmail();
            List<User> users = QueryFactory.getUserByAccount(account);

            if (users != null && users.size() > 0) {
                User user = users.get(0);
                if (user.getEmail().equals(email)) {
                    String newPass = new BigInteger(130, new SecureRandom()).toString(32);
                    user.setPassword(newPass);
                    boolean result = QueryFactory.updateUser(user.generateUpdateJsonNoneUser(user.getUserId()));

                    boolean mail = SendMailTLS.mail(user.getEmail(), newPass);
                    if (mail == true && mail == true) {
                        forgetPasswordForm.setEmail_(SystemMessageProperties.getSystemProperty("v3_send_mail_ok"));
                        forgetPasswordForm.setSuccess(true);
                        return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
                    } else {
                        forgetPasswordForm.setEmail_(SystemMessageProperties.getSystemProperty("v3_not_send_mail"));
                        forgetPasswordForm.setSuccess(false);
                        return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
                    }
                } else {
                    forgetPasswordForm.setEmail_(SystemMessageProperties.getSystemProperty("v3_account_and_email_not_exits"));
                    forgetPasswordForm.setSuccess(false);
                    return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
                }
            }
            forgetPasswordForm.setEmail_(SystemMessageProperties.getSystemProperty("v3_account_and_email_not_exits"));
            forgetPasswordForm.setSuccess(false);
            return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
        } catch (Exception e) {
            forgetPasswordForm.setEmail_(SystemMessageProperties.getSystemProperty("v3_not_send_mail"));
            forgetPasswordForm.setSuccess(false);
            return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
        }
    }

    /*
 * @author manhtv
 * @date 3/19/2017
 * Lich su truy cap he thong
 * */
    public void setAccessHistory(User user, int accessType, HttpServletRequest request) {
        AccessHistory accessHistory = new AccessHistory();
        accessHistory.setUsid(Long.valueOf(user.getUserId()).intValue());
        accessHistory.setExecute_person(user.getFamily_name() + " " + user.getFirst_name() + " (" + user.getAccount() + ")");
        accessHistory.setAccess_type(accessType);
        accessHistory.setClient_info(request.getRemoteAddr() + " [" + request.getSession().getId() + "]");
        QueryFactory.setAccesHistory(accessHistory);

    }

}
