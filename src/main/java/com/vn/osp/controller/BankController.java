package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.SystemProperties;
import com.vn.osp.modelview.Bank;
import com.vn.osp.modelview.BankAdd;
import com.vn.osp.modelview.BankList;
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
 * Created by Admin on 31/5/2017.
 */
@Controller
@RequestMapping(value = "/bank")
public class BankController {
    @RequestMapping (value = "/add-view", method= RequestMethod.GET)
    public ModelAndView addBankView(){
        BankAdd bankAdd = new BankAdd();
        return new ModelAndView("/bank/BNK001","bankAdd",bankAdd);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addBank(BankAdd bankAdd, HttpServletRequest request, HttpServletResponse response){
        try {

            bankAdd.validate();
            if(bankAdd.getSuccess()==false){
                return new ModelAndView("/bank/BNK001","bankAdd",bankAdd);
            }

            String query = bankAdd.getOrderString();
            System.out.println("query "+query);
            int count = QueryFactory.countTotalBank(query);
            System.out.println("check count"+count);
            if( count > 0){
                bankAdd.setCode_("Mã đã tồn tại");
                bankAdd.setSuccess(false);
                return new ModelAndView("/bank/BNK001","bankAdd",bankAdd);
            }


            String result = bankAdd.generateAddJson().toString();
            System.out.println("result " + result);

            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/bank/AddBank", result.toString());


            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_bank_add_success"));
            return new ModelAndView("redirect:/bank/list");
        }

        catch (Exception e){
            return new ModelAndView("/bank/BNK001","bankAdd",bankAdd);
        }

    }
    @RequestMapping(value= "/list", method = RequestMethod.GET)
    public ModelAndView listBank(BankList bankList, HttpServletRequest request, HttpServletResponse response){

        int controllerPage = 1;
        int controllerTotalPage;
        int controllerTotalBank;

        HttpSession session = request.getSession();
        String action = (String)session.getAttribute(Constants.SESSION_ACTION);
        bankList.setAction_status(action);
        session.removeAttribute(Constants.SESSION_ACTION);
        if(bankList != null){
            controllerPage = bankList.getPage();
            controllerTotalPage = bankList.getTotalPage();
            controllerTotalBank = bankList.getTotalBank();
        }

        String query = bankList.getOrderString();

        controllerTotalBank = QueryFactory.countTotalBank(query);
        controllerTotalPage = QueryFactory.countPage(controllerTotalBank);
        System.out.println(controllerTotalBank);
        System.out.println(controllerTotalPage);

        if(controllerPage < 1) controllerPage = 1;
        if(controllerPage > controllerTotalPage) controllerPage = controllerTotalPage;
        bankList.setPage(controllerPage);
        bankList.setTotalPage(controllerTotalPage);
        bankList.settotalBank(controllerTotalBank);

        List<Bank> result = QueryFactory.getBank(controllerPage,query);

        bankList.setListBank(result);
        return new ModelAndView("/bank/BNK002","bankList",bankList);
    }

    @RequestMapping(value = "/update-view/{id}", method = RequestMethod.GET)
    public ModelAndView updateBankView(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        List<Bank> list = getBankById(id);
        Bank bank = list.get(0);

        bank.setCodeUpdateView(bank.getCode());



        return new ModelAndView("/bank/BNK003", "bank",bank);

    }

    @RequestMapping(value= "/update",method = RequestMethod.POST)
    public ModelAndView updateBank(Bank bank, HttpServletRequest request, HttpServletResponse response){
        try{
            System.out.println(" truong check "+bank.getCode());
            bank.updateValidate();
            if(bank.getSuccess()==false){
                return new ModelAndView("/bank/BNK003","bank",bank);
            }

            /*String query = bank.getOrderString();

            int count = QueryFactory.countTotalBank(query);
            String controllerCodeUpdate = bank.getCodeUpdateView();
            String controllerCode = bank.getCode();*/




            /*if( controllerCodeUpdate == controllerCode && count > 1){
                bank.setCode_("Mã đã tồn tại");
                bank.setSuccess(false);
                return new ModelAndView("/bank/BNK003","bank",bank);
            }
            if( !controllerCodeUpdate.equals(controllerCode) && count > 0){
                bank.setCode_("Mã đã tồn tại");
                bank.setSuccess(false);
                return new ModelAndView("/bank/BNK003","bank",bank);
            }*/

            String result = bank.generateUpdateJson().toString();
            System.out.println("result "+ result);
            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/bank/UpdateBank", result.toString());
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION,SystemMessageProperties.getSystemProperty("v3_bank_update_success"));
            return new ModelAndView("redirect:/bank/list");
        }
        catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("/bank/BNK003");
        }

    }



    public List<Bank> getBankById(Long id){
        String stringFilter = " where id = "+id+"" ;
        List<Bank> result = APIUtil.getBank(SystemProperties.getProperty("url_config_server_api")+"/bank/FindBankByFilter",stringFilter);
        return result;
    }
    @RequestMapping(value= "/delete/{id}",method = RequestMethod.GET)
    public ModelAndView deleteBank(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response){
        try{
            System.out.println("check id 0 : "+id);
            String stringId= id.toString();
            System.out.println("check id"+stringId);
            APIUtil.callAPI(SystemProperties.getProperty("url_config_server_api")+"/bank/DeleteBank",stringId);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION,SystemMessageProperties.getSystemProperty("v3_bank_delete_success"));
            return new ModelAndView("redirect:/bank/list");
        }catch ( Exception e){
            e.printStackTrace();
            return new ModelAndView("/bank/BNK003");
        }
    }



}
