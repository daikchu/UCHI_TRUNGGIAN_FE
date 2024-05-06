package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.SystemProperties;
import com.vn.osp.modelview.Province;
import com.vn.osp.modelview.ProvinceAdd;
import com.vn.osp.modelview.ProvinceList;
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
import java.util.List;

/**
 * Created by ProBook on 5/25/2017.
 */
@Controller
@RequestMapping(value = "/province")
public class ProvinceController {
    @RequestMapping(value = "/add-view", method = RequestMethod.GET)
    public ModelAndView addProvinceView() {
        ProvinceAdd provinceAdd = new ProvinceAdd();
        return new ModelAndView("/province/PVN001", "provinceAdd", provinceAdd);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addProvince(ProvinceAdd provinceAdd, HttpServletRequest request, HttpServletResponse response) {
        try {

            provinceAdd.validate();
            if (provinceAdd.getSuccess() == false) {
                return new ModelAndView("/province/PVN001", "provinceAdd", provinceAdd);
            }

            String query = provinceAdd.getOrderString();
            System.out.println("query " + query);
            int count = QueryFactory.countTotalProvince(query);
            if (count > 0) {
                provinceAdd.setCode_("Mã đã tồn tại");
                provinceAdd.setSuccess(false);
                return new ModelAndView("/province/PVN001", "provinceAdd", provinceAdd);
            }


            String result = provinceAdd.generateAddJson().toString();
            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/province/AddProvince", result.toString());

//            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/province/AddProvince", result.toString());
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_province_add_success"));
            return new ModelAndView("redirect:/province/list");
        } catch (Exception e) {
            return new ModelAndView("/province/PVN001", "provinceAdd", provinceAdd);
        }

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listProvince(ProvinceList provinceList, HttpServletRequest request, HttpServletResponse response) {

        int controllerPage = 1;
        int controllerTotalPage;
        int controllerTotalProvince;

        HttpSession session = request.getSession();
        String action = (String) session.getAttribute(Constants.SESSION_ACTION);
        provinceList.setAction_status(action);
        session.removeAttribute(Constants.SESSION_ACTION);
        if (provinceList != null) {
            controllerPage = provinceList.getPage();
            controllerTotalPage = provinceList.getTotalPage();
            controllerTotalProvince = provinceList.getTotalProvince();
        }

        String query = provinceList.getOrderString();

        controllerTotalProvince = QueryFactory.countTotalProvince(query);
        controllerTotalPage = QueryFactory.countPage(controllerTotalProvince);
        System.out.println(controllerTotalProvince);
        System.out.println(controllerTotalPage);

        if (controllerPage < 1) controllerPage = 1;
        if (controllerPage > controllerTotalPage) controllerPage = controllerTotalPage;
        provinceList.setPage(controllerPage);
        provinceList.setTotalPage(controllerTotalPage);
        provinceList.setTotalProvince(controllerTotalProvince);

        List<Province> result = QueryFactory.getProvince(controllerPage, query);

        provinceList.setListProvince(result);
        return new ModelAndView("/province/PVN002", "provinceList", provinceList);
    }

    @RequestMapping(value = "/update-view/{id}", method = RequestMethod.GET)
    public ModelAndView updateProvinceView(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        List<Province> list = getProvinceById(id);
        Province province = list.get(0);
        province.setCodeUpdateView(province.getCode());

        System.out.println(" check update view");


        return new ModelAndView("/province/PVN003", "province", province);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateProvince(Province province, HttpServletRequest request, HttpServletResponse response) {
        try {
            province.updateValidate();
            if (province.getSuccess() == false) {
                return new ModelAndView("/province/PVN003", "province", province);
            }

            String query = province.getOrderString();
            System.out.println("query " + query);
            int count = QueryFactory.countTotalProvince(query);
            /*String controllerCodeUpdate = province.getCodeUpdateView();
            String controllerCode = province.getCode();


            if (controllerCodeUpdate == controllerCode && count > 1) {
                province.setCode_("Mã đã tồn tại");
                province.setSuccess(false);
                return new ModelAndView("/province/PVN003", "province", province);
            }
            if (!controllerCodeUpdate.equals(controllerCode) && count > 0) {
                province.setCode_("Mã đã tồn tại");
                province.setSuccess(false);
                return new ModelAndView("/province/PVN003", "province", province);
            }*/

            String result = province.generateUpdateJson().toString();
            System.out.println("result " + result);
            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/province/UpdateProvince", result.toString());
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_province_update_success"));
            return new ModelAndView("redirect:/province/list");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/province/PVN003");
        }

    }


    public List<Province> getProvinceById(Long id) {
        String stringFilter = " where id = " + id + "";
        List<Province> result = APIUtil.getProvince(SystemProperties.getProperty("url_config_server_api")+"/province/findProvinceByFilter", stringFilter);
        return result;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProvince(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("check id 0 : " + id);
            String stringId = id.toString();
            System.out.println("check id" + stringId);
            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/province/deleteProvince", stringId);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_province_delete_success"));
            return new ModelAndView("redirect:/province/list");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/province/PVN003");
        }
    }


}
