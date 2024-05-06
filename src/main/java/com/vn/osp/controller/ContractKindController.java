package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.SystemProperties;
import com.vn.osp.modelview.ContractKind;
import com.vn.osp.modelview.ContractKindAdd;
import com.vn.osp.modelview.ContractKindList;
import com.vn.osp.modelview.ProvinceAdd;
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
 * Created by minh on 5/30/2017.
 */
@Controller
@RequestMapping(value = "/contractkind")
public class ContractKindController {
    @RequestMapping (value = "/add-view", method= RequestMethod.GET)
    public ModelAndView addContractKindView(){
        ContractKindAdd contractKindAdd = new ContractKindAdd();
        return new ModelAndView("/contractkind/CTR001","contractKindAdd",contractKindAdd);
        }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addContractKind(ContractKindAdd contractKindAdd, HttpServletRequest request, HttpServletResponse response){
        try {


            contractKindAdd.validate();
            if(contractKindAdd.getSuccess()==false){
                return new ModelAndView("/contractkind/CTR001","contractKindAdd",contractKindAdd);
            }

            System.out.println("vao daty");


            String result = contractKindAdd.generateAddJson().toString();
            System.out.println("result " + result);

            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/contractkind/AddContractKind", result.toString());


            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_province_add_success"));

            return new ModelAndView("redirect:/contractkind/list");
        }

        catch (Exception e){
            return new ModelAndView("/contractkind/CTR001","contractKindAdd",contractKindAdd);
        }

    }
    @RequestMapping(value= "/list", method = RequestMethod.GET)
    public ModelAndView listContractKind(ContractKindList contractKindList, HttpServletRequest request, HttpServletResponse response){

        int page = 1;
        int totalPage;
        int totalListNumber;


        HttpSession session = request.getSession();
        String action = (String)session.getAttribute(Constants.SESSION_ACTION);
        contractKindList.setAction_status(action);
        session.removeAttribute(Constants.SESSION_ACTION);

        if(contractKindList != null){
            page = contractKindList.getPage();
            totalPage = contractKindList.getTotalPage();
            totalListNumber = contractKindList.getTotal();
        }

        String query = contractKindList.getOrderString();

        totalListNumber = QueryFactory.countTotalContractKind(query);
        totalPage = QueryFactory.countPage(totalListNumber);

        if(page < 1) page = 1;
        if(page > totalPage) page = totalPage;
        contractKindList.setPage(page);
        contractKindList.setTotalPage(totalPage);
        contractKindList.setTotal(totalListNumber);

        List<ContractKind> result = QueryFactory.getContractKind(page,query);

        contractKindList.setListContractKind(result);
        return new ModelAndView("/contractkind/CTR002","contractKindList",contractKindList);
    }
    public List<ContractKind> getContractKindById(Long id){
        String stringFilter = " where id = "+id+"" ;
        List<ContractKind> result = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api")+"/contractkind/findContractKindByFilter",stringFilter);
        return result;
    }
    @RequestMapping(value = "/update-view/{id}", method = RequestMethod.GET)
    public ModelAndView updateContractKindView(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        List<ContractKind> list = getContractKindById(id);
        ContractKind contractKind = list.get(0);

        return new ModelAndView("/contractkind/CTR003", "contractKind",contractKind);

    }

    @RequestMapping(value= "/update",method = RequestMethod.POST)
    public ModelAndView updateContractKind(ContractKind contractKind, HttpServletRequest request, HttpServletResponse response){
        try{
            String result = contractKind.generateUpdateJson().toString();
            System.out.println("result "+ result);
            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/contractkind/UpdateContractKind", result.toString());

            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION,SystemMessageProperties.getSystemProperty("v3_contract_kind_update_success"));

            return new ModelAndView("redirect:/contractkind/list");
        }
        catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("/contractkind/CTR003");
        }

    }
    @RequestMapping(value= "/delete/{id}",method = RequestMethod.GET)
    public ModelAndView deleteContractKind(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response){
        try{
            String stringId= id.toString();
            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/contractkind/deleteContractKind",stringId);

            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION,SystemMessageProperties.getSystemProperty("v3_contract_kind_delete_success"));

            return new ModelAndView("redirect:/contractkind/list");
        }catch ( Exception e){
            e.printStackTrace();
            return new ModelAndView("/contractkind/CTR003");
        }
    }






}
