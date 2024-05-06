package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.BaseController;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.SystemProperties;
import com.vn.osp.modelview.ContractFeeBO;
import com.vn.osp.modelview.ContractFeeList;
import com.vn.osp.modelview.ContractKind;
import com.vn.osp.modelview.ContractTemp;
import com.vn.osp.service.APIUtil;
import com.vn.osp.service.QueryFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2018-05-10.
 */
@Controller
@RequestMapping(value = "/fee")
public class ContractFeeController {
    @RequestMapping (value = "/add-view", method= RequestMethod.GET)
    public ModelAndView addContractFeeView(){
        ContractFeeBO contractFee = new ContractFeeBO();

        List<ContractKind> listContractKind = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api")+"/contractkind/findContractKindByFilter","where 1=1");
        List<ContractTemp> listContractTemp = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/FindContractTempByFilter","where 1=1");
        contractFee.setContractKindList(listContractKind);
        contractFee.setContractTempList(listContractTemp);
        return new ModelAndView("/contractfee/CTF001","contractFee",contractFee);
    }
    @RequestMapping (value = "/add", method= RequestMethod.POST)
    public ModelAndView addContractFee(HttpServletRequest request, HttpServletResponse response, ContractFeeBO contractFee,RedirectAttributes redirectAttributes){
        BaseController.trimAllFieldOfObject(contractFee);
        List<ContractFeeBO> listCheck = new ArrayList<ContractFeeBO>();
        boolean check;
        boolean checkFromTo= true;
        contractFee.addValidate();
        if(!contractFee.getSuccess()){
            List<ContractKind> listContractKind = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api")+"/contractkind/findContractKindByFilter","where 1=1");
            List<ContractTemp> listContractTemp = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/FindContractTempByFilter","where 1=1");
            contractFee.setContractTempList(listContractTemp);
            contractFee.setContractKindList(listContractKind);

            return new ModelAndView("/contractfee/CTF001","contractFee",contractFee);
        }
        if(contractFee.getFrom_fee() == null){
            contractFee.setFrom_fee(Long.valueOf(0));
        }

        listCheck = QueryFactory.getContractFeeOnlyCode(contractFee.getCt_template_code());
        if(listCheck != null && listCheck.size()>0){
            for(int i=0;i<listCheck.size();i++){
                if(listCheck.get(i).getFrom_fee() == null){
                    listCheck.get(i).setFrom_fee(Long.valueOf(0));
                }
                if(listCheck.get(i).getTo_fee()==null){
                    long max = Long.MAX_VALUE;
                    listCheck.get(i).setTo_fee(max);
                }
                if(contractFee.getTo_fee()==null ){
                    double mid = (contractFee.getFrom_fee()+Long.MAX_VALUE)/2;

                    if((contractFee.getFrom_fee()>listCheck.get(i).getFrom_fee() && contractFee.getFrom_fee()<listCheck.get(i).getTo_fee()) || (mid>listCheck.get(i).getFrom_fee() && mid<listCheck.get(i).getTo_fee()) ){
                        checkFromTo = false;
                        break;
                    }

                }else {
                    double mid = (contractFee.getFrom_fee() + contractFee.getTo_fee())/2;
                    if((contractFee.getFrom_fee()>listCheck.get(i).getFrom_fee() && contractFee.getFrom_fee()<listCheck.get(i).getTo_fee()) || (contractFee.getTo_fee()>listCheck.get(i).getFrom_fee() && contractFee.getTo_fee()<listCheck.get(i).getTo_fee()) || (mid>listCheck.get(i).getFrom_fee() && mid<listCheck.get(i).getTo_fee())){
                        checkFromTo = false;
                        break;
                    }
                }

            }
        }


        if(!checkFromTo){
            List<ContractKind> listContractKind = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api")+"/contractkind/findContractKindByFilter","where 1=1");
            List<ContractTemp> listContractTemp = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/FindContractTempByFilter","where 1=1");
            contractFee.setContractTempList(listContractTemp);
            contractFee.setContractKindList(listContractKind);
            contractFee.setSpace_(SystemMessageProperties.getSystemProperty("v3_fee_exist"));
            return new ModelAndView("/contractfee/CTF001","contractFee",contractFee);
        }

        int i = QueryFactory.addContractFee(contractFee.ObjectJsonGen());
        if(i>0){
            check = true;
        }else {
            check = false;
        }
        redirectAttributes.addFlashAttribute("updateStatus",check);
        return new ModelAndView("redirect:/fee/list");
    }

    @RequestMapping (value = "/update-view/{id}", method= RequestMethod.GET)
    public ModelAndView updateContractFeeView(@PathVariable final int id,HttpServletRequest request, HttpServletResponse response){
        ContractFeeBO contractFee = QueryFactory.getContractFeeId(id);

        List<ContractKind> listContractKind = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api")+"/contractkind/findContractKindByFilter","where 1=1");
        List<ContractTemp> listContractTemp = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/FindContractTempByFilter","where 1=1");
        contractFee.setContractKindList(listContractKind);
        contractFee.setContractTempList(listContractTemp);
        return new ModelAndView("/contractfee/CTF003","contractFee",contractFee);
    }

    @RequestMapping (value = "/update", method= RequestMethod.POST)
    public ModelAndView updateContractFee(HttpServletRequest request, HttpServletResponse response, ContractFeeBO contractFee,RedirectAttributes redirectAttributes){
        boolean check;
        boolean checkFromTo= true;
        List<ContractFeeBO> listCheck = new ArrayList<ContractFeeBO>();
        BaseController.trimAllFieldOfObject(contractFee);
        contractFee.addValidate();
        if(!contractFee.getSuccess()){
            List<ContractKind> listContractKind = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api")+"/contractkind/findContractKindByFilter","where 1=1");
            List<ContractTemp> listContractTemp = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/FindContractTempByFilter","where 1=1");
            contractFee.setContractTempList(listContractTemp);
            contractFee.setContractKindList(listContractKind);
            return new ModelAndView("/contractfee/CTF003","contractFee",contractFee);
        }
        listCheck = QueryFactory.getContractFeeOnlyCodeExceptId(contractFee.getCt_template_code(),contractFee.getId());
        if(listCheck != null && listCheck.size()>0){
            for(int i=0;i<listCheck.size();i++){
                if(listCheck.get(i).getFrom_fee() == null){
                    listCheck.get(i).setFrom_fee(Long.valueOf(0));
                }
                if(listCheck.get(i).getTo_fee()==null){
                    long max = Long.MAX_VALUE;
                    listCheck.get(i).setTo_fee(max);
                }
                if(contractFee.getTo_fee()==null){

                    double mid = (contractFee.getFrom_fee()+Long.MAX_VALUE)/2;

                    if((contractFee.getFrom_fee()>listCheck.get(i).getFrom_fee() && contractFee.getFrom_fee()<listCheck.get(i).getTo_fee()) || (mid>listCheck.get(i).getFrom_fee() && mid<listCheck.get(i).getTo_fee()) ){
                        checkFromTo = false;
                        break;
                    }

                }else {
                    double mid = (contractFee.getFrom_fee() + contractFee.getTo_fee())/2;
                    if((contractFee.getFrom_fee()>listCheck.get(i).getFrom_fee() && contractFee.getFrom_fee()<listCheck.get(i).getTo_fee()) || (contractFee.getTo_fee()>listCheck.get(i).getFrom_fee() && contractFee.getTo_fee()<listCheck.get(i).getTo_fee()) || (mid>listCheck.get(i).getFrom_fee() && mid<listCheck.get(i).getTo_fee())){
                        checkFromTo = false;
                        break;
                    }
                }

            }
        }


        if(!checkFromTo){
            List<ContractKind> listContractKind = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api")+"/contractkind/findContractKindByFilter","where 1=1");
            List<ContractTemp> listContractTemp = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/FindContractTempByFilter","where 1=1");
            contractFee.setContractTempList(listContractTemp);
            contractFee.setContractKindList(listContractKind);
            contractFee.setSpace_(SystemMessageProperties.getSystemProperty("v3_fee_exist"));
            return new ModelAndView("/contractfee/CTF003","contractFee",contractFee);
        }
        int i = QueryFactory.updateContractFee(contractFee.ObjectJsonGen());
        if(i>0){
            check = true;
        }else {
            check = false;
        }
        redirectAttributes.addFlashAttribute("updateStatus",check);
        return new ModelAndView("redirect:/fee/list");
    }



    @RequestMapping (value = "/list", method= RequestMethod.GET)
    public ModelAndView listContractFeeView(ContractFeeList contractFeeList,HttpServletRequest request, HttpServletResponse response){
        int page = 1;
        int totalPage;
        int totalListNumber;


        HttpSession session = request.getSession();
        String action = (String)session.getAttribute(Constants.SESSION_ACTION);
        contractFeeList.setAction_status(action);
        session.removeAttribute(Constants.SESSION_ACTION);
        List<ContractKind> listContractKind = APIUtil.getContractKind(SystemProperties.getProperty("url_config_server_api")+"/contractkind/findContractKindByFilter","where 1=1");
        List<ContractTemp> listContractTemp = APIUtil.getContractTemp(SystemProperties.getProperty("url_config_server_api")+"/ContractTemplate/FindContractTempByFilter","where 1=1");
        contractFeeList.setListContractTemp(listContractTemp);
        contractFeeList.setContractKindList(listContractKind);
        if(contractFeeList != null){
            page = contractFeeList.getPage();
            totalPage = contractFeeList.getTotalPage();
            totalListNumber = contractFeeList.getTotal();
        }

        if(contractFeeList.getCode() == null){
            contractFeeList.setCode("");
        }
        if(contractFeeList.getNhomHD() == null){
            contractFeeList.setNhomHD("");
        }





        totalListNumber = QueryFactory.countTotalContractFee(contractFeeList.getCode(),contractFeeList.getNhomHD());
        totalPage = QueryFactory.countPage(totalListNumber);


        if(page > totalPage) page = totalPage;
        if(page < 1) page = 1;
        contractFeeList.setPage(page);
        contractFeeList.setTotalPage(totalPage);
        contractFeeList.setTotal(totalListNumber);

        List<ContractFeeBO> result = QueryFactory.getContractFeeCode(page,contractFeeList.getCode(),contractFeeList.getNhomHD());
        /*if(result!= null && result.size()>0){
            for(int i =0;i<result.size();i++){
                if(listContractTemp != null && listContractTemp.size()>0){
                    for (int j =0;j<listContractTemp.size();j++){
                        if(result.get(i).getCt_template_code() == listContractTemp.get(j).getCode_template() ){
                            result.get(i).setNameCode(listContractTemp.get(j).getName());
                        }

                    }
                }

            }
        }*/
        contractFeeList.setContractFeeLists(result);
        return new ModelAndView("/contractfee/CTF002","contractFeeList",contractFeeList);


    }
    // xóa
    @RequestMapping (value = "/delete/{id}", method= RequestMethod.GET)
    public ModelAndView updateContractFee(@PathVariable final int id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession();
        boolean check = QueryFactory.deleteContractFee(id);
        redirectAttributes.addFlashAttribute("deleteStatus",check);
        return new ModelAndView("redirect:/fee/list");
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
