package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.SystemProperties;
import com.vn.osp.modelview.*;
import com.vn.osp.service.APIUtil;
import com.vn.osp.service.QueryFactory;
import com.vn.osp.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Admin on 2/6/2017.
 */
@Controller
@RequestMapping(value = "/contracttemplate")
public class ContractTemplateController {
    @RequestMapping(value = "/add-view", method = RequestMethod.GET)
    public ModelAndView addContractTempView() {
        ContractTempAdd contractTempAdd = new ContractTempAdd();

        String stringFilter = "where 1=1";
        List<ContractKind> resultList1 = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api") + "/contractkind/findContractKindByFilter", stringFilter);
        contractTempAdd.setListContractKind(resultList1);
        contractTempAdd.setActive_flg(Long.valueOf(1));

        return new ModelAndView("/contracttemplate/CTP001", "contractTempAdd", contractTempAdd);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addContractTemp(ContractTempAdd contractTempAdd, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!StringUtils.isBlank(contractTempAdd.getKind_html())) {
//                contractTempAdd.setKind_html(contractTempAdd.getKind_html().replaceAll("'",""));
                contractTempAdd.setKind_html(StringUtil.removeSpecialCharactersNotHTML(contractTempAdd.getKind_html()));
                contractTempAdd.setKind_html(contractTempAdd.getKind_html().trim());
            }
            if(contractTempAdd.getKind_id() == null){
                contractTempAdd.setKind_id(0L);
            }
            contractTempAdd.validate();
            if (contractTempAdd.getSuccess() == false) {
                String stringFilter = "where 1=1";
                List<ContractKind> result = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api") + "/contractkind/findContractKindByFilter", stringFilter);
                contractTempAdd.setListContractKind(result);
                return new ModelAndView("/contracttemplate/CTP001", "contractTempAdd", contractTempAdd);
            }


            String result = contractTempAdd.generateAddJson().toString();

            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api") + "/ContractTemplate/AddContractTemp", result.toString());


            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_contract_template_add_success"));
            return new ModelAndView("redirect:/contracttemplate/list");
        } catch (Exception e) {
            return new ModelAndView("/contracttemplate/CTP001", "contractTempAdd", contractTempAdd);
        }

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listContractTemp(ContractTempList contractTempList, HttpServletRequest request, HttpServletResponse response) {

        int controllerPage = 1;
        int controllerTotalPage;
        int controllerTotalContractTemp;

        HttpSession session = request.getSession();
        String action = (String) session.getAttribute(Constants.SESSION_ACTION);
        contractTempList.setAction_status(action);
        session.removeAttribute(Constants.SESSION_ACTION);
        String stringFilter = "where 1=1";
        List<ContractKind> resultList1 = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api") + "/contractkind/findContractKindByFilter", stringFilter);
        contractTempList.setListContractKind(resultList1);
        if (contractTempList != null) {
            controllerPage = contractTempList.getPage();
            controllerTotalPage = contractTempList.getTotalPage();
            controllerTotalContractTemp = contractTempList.getTotalContractTemp();
        }

        String query = contractTempList.getOrderString();

        controllerTotalContractTemp = QueryFactory.countTotalContractTemp(query);
        controllerTotalPage = QueryFactory.countPage(controllerTotalContractTemp);

        if (controllerPage < 1) controllerPage = 1;
        if (controllerPage > controllerTotalPage) controllerPage = controllerTotalPage;
        contractTempList.setPage(controllerPage);
        contractTempList.setTotalPage(controllerTotalPage);
        contractTempList.setTotalContractTemp(controllerTotalContractTemp);

        List<ContractTemp> result = QueryFactory.getContractTemp(controllerPage, query);
        if (result != null && resultList1 != null) {
            int j = result.size();
            int m = resultList1.size();
            for (int i = 0; i < j; i++) {
                for (int n = 0; n < m; n++) {
                    if (result.get(i).getCode().equals(resultList1.get(n).getContract_kind_code())) {
                        result.get(i).setContractKindName(resultList1.get(n).getName());
                    }

                }

            }
        }

        contractTempList.setListContractTemp(result);
        return new ModelAndView("/contracttemplate/CTP002", "contractTempList", contractTempList);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateContractTemp(ContractTemp contractTemp, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!StringUtils.isBlank(contractTemp.getKind_html())) {
//                contractTemp.setKind_html(contractTemp.getKind_html().replaceAll("'",""));
                contractTemp.setKind_html(StringUtil.removeSpecialCharactersNotHTML(contractTemp.getKind_html()));
                contractTemp.setKind_html(contractTemp.getKind_html().trim());
            }
            contractTemp.validateUpdate();

            if (contractTemp.getSuccess() == false) {
                String stringFilter = "where 1=1";
                List<ContractKind> resultList1 = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api") + "/contractkind/findContractKindByFilter", stringFilter);
                contractTemp.setListContractKind(resultList1);
                return new ModelAndView("/contracttemplate/CTP003", "contractTemp", contractTemp);
            }


            String result = contractTemp.generateUpdateJson().toString();

            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api") + "/ContractTemplate/UpdateContractTemp", result.toString());
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_contract_template_update_success"));
            return new ModelAndView("redirect:/contracttemplate/list");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/contracttemplate/CTP003");
        }

    }

    @RequestMapping(value = "/update-view/{id}", method = RequestMethod.GET)
    public ModelAndView updateContractTempView(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        Logger LOG = LoggerFactory.getLogger(ContractTemplateController.class);
        List<ContractTemp> list = getContractTempById(id);
        ContractTemp contractTemp = list.get(0);
        if (!StringUtils.isBlank(contractTemp.getKind_html())) {
            contractTemp.setKind_html(StringUtil.removeSpecialCharactersNotHTML(contractTemp.getKind_html()));
//            contractTemp.setKind_html(contractTemp.getKind_html().replaceAll("'",""));
//            contractTemp.setKind_html(contractTemp.getKind_html().replaceAll("\n",""));
//            contractTemp.setKind_html(contractTemp.getKind_html().replaceAll("\t",""));
            contractTemp.setKind_html(contractTemp.getKind_html().trim());
        }
        List<ContractTemp> listContractTemp = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/FindContractTempByFilter"," where code_template = " + contractTemp.getKind_id());
        contractTemp.setContractTemps(listContractTemp);

        String stringFilter = "where 1=1";
        List<ContractKind> resultList1 = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api") + "/contractkind/findContractKindByFilter", stringFilter);
        contractTemp.setListContractKind(resultList1);

        return new ModelAndView("/contracttemplate/CTP003", "contractTemp", contractTemp);

    }


    public List<ContractTemp> getContractTempById(Long id) {
        String stringFilter = " where id = " + id + "";
        List<ContractTemp> result = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api") + "/ContractTemplate/FindContractTempByFilter", stringFilter);
        return result;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteContractTemp(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        try {
            String stringId = id.toString();
            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api") + "/ContractTemplate/DeleteContractTemp", stringId);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_contract_template_delete_success"));
            return new ModelAndView("redirect:/contracttemplate/list");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/contracttemplate/CTP003");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/loadContractTemplate", method = RequestMethod.GET)
    public String loadContractTemplate(HttpServletRequest request) throws JSONException {
        String contractKind = request.getParameter("contractKind");
        String result = "";
        List<ContractTemp> contractTemplates = null;
        if (contractKind.equals("")) {
            contractTemplates = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/FindContractTempByFilter","where 1=1");
        } else {
            contractTemplates = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/FindContractTempByFilter"," where code = "+ contractKind);
        }
        for (int i = 0; i < contractTemplates.size(); i++) {
            result += contractTemplates.get(i).getCode_template() + "o0o" + contractTemplates.get(i).getName() + ";";
        }

        return new JSONObject().put("result", result).toString();

    }
}
