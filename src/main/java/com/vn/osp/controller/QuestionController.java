package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.SystemProperties;
import com.vn.osp.modelview.*;
import com.vn.osp.service.APIUtil;
import com.vn.osp.service.QueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by DaiCQ on 10/02/2020.
 */
@Controller
@RequestMapping(value = "/question-help")
public class QuestionController {
    @RequestMapping(value = "/add-view", method = RequestMethod.GET)
    public ModelAndView addView() {
        Question item = new Question();
        return new ModelAndView("/question-help/add", "item", item);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(Question item, HttpServletRequest request, HttpServletResponse response) {
        try {

            item.validate();
            if (item.getSuccess() == false) {
                return new ModelAndView("/question-help/add", "item", item);
            }


            String result = item.generateAddJson().toString();
            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/question-help/add", result.toString());

//            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/province/AddProvince", result.toString());
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_province_add_success"));
            return new ModelAndView("redirect:/question-help/list");
        } catch (Exception e) {
            return new ModelAndView("/question-help/add", "item", item);
        }

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(QuestionList qaList, HttpServletRequest request, HttpServletResponse response) {

        int controllerPage = 1;
        int controllerTotalPage;
        int controllerTotalQuestion;

        HttpSession session = request.getSession();
        String action = (String) session.getAttribute(Constants.SESSION_ACTION);
        session.removeAttribute(Constants.SESSION_ACTION);
        if (qaList != null) {
            controllerPage = qaList.getPage();
            controllerTotalPage = qaList.getTotalPage();
            controllerTotalQuestion = qaList.getTotalQuestion();
        }

        String query = qaList.getQueryString();

        controllerTotalQuestion = QueryFactory.countTotalQuestion(query);
        controllerTotalPage = QueryFactory.countPage(controllerTotalQuestion);
        System.out.println(controllerTotalQuestion);
        System.out.println(controllerTotalPage);

        if (controllerPage < 1) controllerPage = 1;
        if (controllerPage > controllerTotalPage) controllerPage = controllerTotalPage;
        qaList.setPage(controllerPage);
        qaList.setTotalPage(controllerTotalPage);
        qaList.setTotalQuestion(controllerTotalQuestion);

        List<Question> result = QueryFactory.getQuestion(controllerPage, query);

        qaList.setListQuestion(result);
        return new ModelAndView("/question-help/list", "qaList", qaList);
    }

    @RequestMapping(value = "/update-view/{id}", method = RequestMethod.GET)
    public ModelAndView updateView(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        List<Question> list = getQuestionById(id);
        Question item = list.get(0);
       /* question.setCodeUpdateView(province.getCode());*/

        System.out.println(" check update view");


        return new ModelAndView("/question-help/edit", "item", item);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(Question item, HttpServletRequest request, HttpServletResponse response) {
        try {
            item.validate();
            if (item.getSuccess() == false) {
                return new ModelAndView("/question-help/edit", "item", item);
            }

            String result = item.generateUpdateJson().toString();
            System.out.println("result " + result);
            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/question-help/update", result.toString());
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_province_update_success"));
            return new ModelAndView("redirect:/question-help/list");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/question-help/edit");
        }

    }


    public List<Question> getQuestionById(Long id) {
        String stringFilter = " and id = " + id + "";
        List<Question> result = APIUtil.getQuestion(SystemProperties.getProperty("url_config_server_api")+"/question-help/search", stringFilter);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam("id") String id, HttpServletRequest request){
        try {
            if(StringUtils.isBlank(id) || !EditString.isNumber(id)){
                return new  ModelAndView("redirect:/question-help/list");
            }
            System.out.println("check id 0 : " + id);
            String stringId = id.toString();
            System.out.println("check id" + stringId);
            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/question-help/delete", stringId);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_province_delete_success"));
            return new ModelAndView("redirect:/question-help/list");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/question-help/list");
        }
    }

  /*  @RequestMapping(value = "/property/delete", method = RequestMethod.POST)
    public ModelAndView propertyDelete(@RequestParam("id") String id, HttpServletRequest request){
        if(StringUtils.isBlank(id) || !EditString.isNumber(id)){
            return new  ModelAndView("redirect:/template/property/list");
        }
        boolean check=TemplateFactory.deletePropertyTemplate(id);
        if(check){
            //delete ok
        }
        return new ModelAndView("redirect:/template/property/list");
    }*/
}
