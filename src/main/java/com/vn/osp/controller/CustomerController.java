package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.*;
import com.vn.osp.modelview.*;
import com.vn.osp.modelview.Package;
import com.vn.osp.service.APIUtil;
import com.vn.osp.service.QueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

   @RequestMapping(value = "/add-view", method = RequestMethod.GET)
   public ModelAndView addCustomerView(HttpServletRequest request, HttpServletResponse response) {
      CustomerInsert customerInsert = new CustomerInsert();
      List<ProvinceList> provinceLists = QueryFactory.getAllProvince();
      customerInsert.setProvinceList(provinceLists);
      List<Package> packages = QueryFactory.getAllPackage();
      List citizenVerifyPackages = QueryFactory.getAllCitizenVerifyPackages();
      customerInsert.setCitizenVerifyPackages(citizenVerifyPackages);
      customerInsert.setPackages(packages);
      return new ModelAndView("/customer/add", "customerInsert", customerInsert);
   }

   @RequestMapping(value = "/add", method = RequestMethod.POST)
   public ModelAndView addCustomer(CustomerInsert customerInsert, HttpServletRequest request, HttpServletResponse response) {
      ModelAndView view = new ModelAndView();
      try {
         if (!StringUtils.isBlank(customerInsert.getCode()) && !StringUtils.isBlank(customerInsert.getName())) {
            List<Customer> customers = getCustomerByCode(customerInsert.getCode());
            List<Customer> customers1 = getCustomerByName(customerInsert.getName());
            if (customers != null) {
               if (customers.get(0).getCode().equals(customerInsert.getCode())) {
                  List<ProvinceList> provinceLists = QueryFactory.getAllProvince();
                  customerInsert.setProvinceList(provinceLists);
                  List<Package> packages = QueryFactory.getAllPackage();
                  customerInsert.setPackages(packages);
                  customerInsert.setCode_("Mã tổ chức công chứng đã tồn tại !");
                  view.setViewName("/customer/add");
                  view.addObject("customerInsert", customerInsert);
                  return view;
               }
            }
            if (customers1 != null) {
               if (customers1.get(0).getName().equals(customerInsert.getName())) {
                  List<ProvinceList> provinceLists = QueryFactory.getAllProvince();
                  customerInsert.setProvinceList(provinceLists);
                  List<Package> packages = QueryFactory.getAllPackage();
                  customerInsert.setPackages(packages);
                  customerInsert.setName_("Tên tổ chức công chứng đã tồn tại !");
                  view.setViewName("/customer/add");
                  view.addObject("customerInsert", customerInsert);
                  return view;
               }
            }
         }
           customerInsert.validate();
           if(customerInsert.isSuccess()==false){
               List<ProvinceList> provinceLists = QueryFactory.getAllProvince();
               customerInsert.setProvinceList(provinceLists);
               List<Package> packages = QueryFactory.getAllPackage();
               customerInsert.setPackages(packages);
               return new ModelAndView("/customer/add","customerInsert",customerInsert);
           }
           String date_start = customerInsert.getDate_start().replaceAll("/","-");
           String date_exp = customerInsert.getDate_exp().replaceAll("/","-");
           customerInsert.setDate_start(date_start);
           customerInsert.setDate_exp(date_exp);
           String result = customerInsert.addJSON();
           System.out.println("result " + result);

           APIUtil.callAPIResult(Constants.OSP_API_LINK + "/customer/insert", result.toString());

           HttpSession session = request.getSession();
           session.setAttribute(Constants.SESSION_ACTION, "Thêm mới thông tin tổ chức công chức thành công");

           return new ModelAndView("redirect:/customer/list");
       }

      catch (Exception e){
         return new ModelAndView("/customer/add","customerInsert",customerInsert);
      }
   }

   @RequestMapping(value = "/list", method = RequestMethod.GET)
   public ModelAndView customerList(CustomerListWrapper customerListWrapper, HttpServletRequest request, HttpServletResponse response) {
      int firstRecord = 1;
      HttpSession session = request.getSession();
      String action = (String)session.getAttribute(Constants.SESSION_ACTION);
      customerListWrapper.setAction_status(action);
      session.removeAttribute(Constants.SESSION_ACTION);
      ModelAndView view = new ModelAndView();
      String validateToDate = "";
      String validateFromDate = "";
      if (customerListWrapper.getTimeType() == null) {
         validateToDate = "";
         validateFromDate = "";
         customerListWrapper.setTimeType("03");
      }

      if (!StringUtils.isBlank(customerListWrapper.getTimeType())) {
         if (customerListWrapper.getTimeType().equals("05")) {
//            ValidateUtil.validate_msg_from_date="";
//            ValidateUtil.validate_msg_to_date = "";
//            ValidateUtil.validateDateto(customerListWrapper.getPrintToDate());
//            ValidateUtil.validateDatefrom(customerListWrapper.getPrintFromDate());
//            validateFromDate = ValidateUtil.validate_msg_from_date;
//            validateToDate = ValidateUtil.validate_msg_to_date;

            if (!StringUtils.isBlank(customerListWrapper.getPrintFromDate())) {
               if (!StringUtils.isBlank(customerListWrapper.getPrintToDate())) {
                  Date fromDate = TimeUtil.stringToDate(customerListWrapper.getPrintFromDate());
                  Date toDate = TimeUtil.stringToDate(customerListWrapper.getPrintToDate());
                  if (fromDate.getTime() > toDate.getTime()) {
                     view.setViewName("/customer/list");
                     view.addObject("customerListWrapper", customerListWrapper);
                     validateFromDate = "Từ ngày không được lớn hơn Đến ngày";
                     view.addObject("validate_msg_from_date", validateFromDate);
                     return view;
                  }
               } else {
                  Date date = new Date();
                  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                  String toDate = format.format(date);
                  customerListWrapper.setPrintToDate(toDate);
               }
            }
            else {
               view.setViewName("/customer/list");
               view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống");
               view.addObject("validate_msg_to_date", "Trường đến ngày không được để trống");
               return view;
            }
         }
      }

      customerListWrapper = createCustomerList(customerListWrapper,request);
      firstRecord = (customerListWrapper.getPage() - 1) * 20 + 1;
      view.addObject("customerListWrapper",customerListWrapper);
      view.addObject("firstRecord", firstRecord);
      return view;
   }

   public CustomerListWrapper createCustomerList(CustomerListWrapper customerListWrapper, HttpServletRequest request) {

      int customerListNumber = 1;
      int customerTotalPage = 1;
      int page = 1;
      if (customerListWrapper != null) {
         customerListNumber = customerListWrapper.getTotal();
         customerTotalPage = customerListWrapper.getTotalPage();
         page = customerListWrapper.getPage();

      }
      List<ProvinceList> provinceLists = QueryFactory.getAllProvince();
      customerListWrapper.setProvinceList(provinceLists);
      customerListWrapper.createFromToDate();
      String nameFilter = customerListWrapper.getNameFilter();
      Long activeFlg = customerListWrapper.getActive_flg();

      String query = customerListWrapper.getOrderString();


      customerListNumber = QueryFactory.countTotalCustomer(page,query);
      customerTotalPage = QueryFactory.countPage(customerListNumber);
      customerListWrapper.setTotal(customerListNumber);
      customerListWrapper.setTotalPage(customerTotalPage);
      customerListWrapper.setNameFilter(nameFilter);

      if (page < 1) page = 1;
      if (customerListNumber < 1) customerListNumber = 1;
      if (customerTotalPage < 1) customerTotalPage = 1;

      if (page > customerTotalPage) page = customerTotalPage;

      customerListWrapper.setActive_flg(activeFlg);

      SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
      String timeSearch = dateFormat.format(new Date());

      customerListWrapper.setSearchTime(timeSearch);
      customerListWrapper.setPage(page);
      customerListWrapper.setCustomers(QueryFactory.getCustomer(page, query));
      CustomerListWrapper customerListSession = (CustomerListWrapper) request.getSession().getAttribute("customerListWrapper");
      if (customerListSession != null) request.getSession().removeAttribute("customerListWrapper");
      request.getSession().setAttribute("customerListWrapper", customerListWrapper);

      return customerListWrapper;
   }



   @RequestMapping(value = "/update-view/{noid}", method = RequestMethod.GET)
   public ModelAndView updateview(@PathVariable("noid") Long noid,HttpServletRequest request, HttpServletResponse response) {
      CustomerUpdate customerUpdate = new CustomerUpdate();

      List<Customer> list = getCustomerById(noid);
      if (list.size() > 0) {
         List<ProvinceList> provinceLists = QueryFactory.getAllProvince();
         customerUpdate.setProvinceList(provinceLists);
         List<Package> packages = QueryFactory.getAllPackage();
         List citizenVerifyPackages = QueryFactory.getAllCitizenVerifyPackages();
         customerUpdate.setCitizenVerifyPackages(citizenVerifyPackages);
         customerUpdate.setPackages(packages);
         Customer customer = list.get(0);
         customerUpdate.setNoid(customer.getNoid());
         customerUpdate.setActive_flg(customer.getActive_flg());
         customerUpdate.setAddress(customer.getAddress());
         customerUpdate.setCode(customer.getCode());
         customerUpdate.setEmail(customer.getEmail());
         customerUpdate.setMac_address(customer.getMac_address());
         customerUpdate.setIp_address(customer.getIp_address());
         customerUpdate.setName(customer.getName());
         customerUpdate.setOffice_type(customer.getOffice_type());
         customerUpdate.setPhone(customer.getPhone());
         customerUpdate.setWebsite(customer.getWebsite());
         customerUpdate.setDomain(customer.getDomain());
         customerUpdate.setIs_demo(customer.getIs_demo());
         customerUpdate.setPakage_code(customer.getPakage_code());
         customerUpdate.setDate_start(customer.getDate_start());
         customerUpdate.setDate_exp(customer.getDate_exp());
         customerUpdate.setMessage_show(customer.getMessage_show());
         customerUpdate.setMessage_time_to_show(customer.getMessage_time_to_show());
         customerUpdate.setMessage_active_flg(customer.getMessage_active_flg());
         customerUpdate.setType(customer.getType());
         customerUpdate.setDescription(customer.getDescription());
         customerUpdate.setProvince_code(customer.getProvince_code());
         customerUpdate.setNote(customer.getNote());
         customerUpdate.setCitizen_verify_package_code(customer.getCitizen_verify_package_code());
      }
      return new ModelAndView("/customer/edit", "customerUpdate",customerUpdate);
   }


   @RequestMapping(value = "/update", method = RequestMethod.POST)
      public ModelAndView updateCustomer(CustomerUpdate customerUpdate, HttpServletRequest request, HttpServletResponse response) {
      HttpSession session = request.getSession();
      ModelAndView view = new ModelAndView();
      try {
         List<Customer> customers = getCustomerById(customerUpdate.getNoid());
         if(customers !=null){
            if (customers.get(0).getCode().equals(customerUpdate.getCode()) && customers.get(0).getName().equals(customerUpdate.getName())) {
               List<ProvinceList> provinceLists = QueryFactory.getAllProvince();
               customerUpdate.setProvinceList(provinceLists);
               List<Package> packages = QueryFactory.getAllPackage();
               customerUpdate.setPackages(packages);
               List citizenVerifyPackages = QueryFactory.getAllCitizenVerifyPackages();
               customerUpdate.setCitizenVerifyPackages(citizenVerifyPackages);
            }
            else {
               if (!StringUtils.isBlank(customerUpdate.getName())) {
                  List<Customer> customers1 = getCustomerByName(customerUpdate.getName());
                  if (customers1 != null && !customers1.get(0).getName().equals(customers.get(0).getName())) {
                     if (customers1.get(0).getName().equals(customerUpdate.getName())) {
                        List<ProvinceList> provinceLists = QueryFactory.getAllProvince();
                        customerUpdate.setProvinceList(provinceLists);
                        List<Package> packages = QueryFactory.getAllPackage();
                        customerUpdate.setPackages(packages);
                        List citizenVerifyPackages = QueryFactory.getAllCitizenVerifyPackages();
                        customerUpdate.setCitizenVerifyPackages(citizenVerifyPackages);
                        customerUpdate.setName_("Tên tổ chức công chứng đã tồn tại !");
                        view.setViewName("/customer/edit");
                        view.addObject("customerUpdate", customerUpdate);
                        return view;
                     }
                  }
               }
            }
         }
         customerUpdate.validate();
         if(customerUpdate.isSuccess()==false){
            List<ProvinceList> provinceLists = QueryFactory.getAllProvince();
            customerUpdate.setProvinceList(provinceLists);
            List<Package> packages = QueryFactory.getAllPackage();
            customerUpdate.setPackages(packages);
            List citizenVerifyPackages = QueryFactory.getAllCitizenVerifyPackages();
            customerUpdate.setCitizenVerifyPackages(citizenVerifyPackages);
            return new ModelAndView("/customer/edit","customerUpdate",customerUpdate);
         }
         if(customerUpdate.getDate_exp().contains("/")) {
            String date_exp = customerUpdate.getDate_exp().replaceAll("/", "-");
            customerUpdate.setDate_exp(date_exp);
         }
         String result = customerUpdate.updateJson();
         APIUtil.callAPI(Constants.OSP_API_LINK +"/customer/update", result);
         session.setAttribute(Constants.SESSION_ACTION, "Cập nhật thông tin tổ chức công chức thành công");
         return new ModelAndView("redirect:/customer/list");
      }catch (Exception e){
         e.printStackTrace();
         return new ModelAndView("/customer/edit","customerUpdate",customerUpdate);
      }
   }

   @RequestMapping(value = "/delete/{noid}", method = RequestMethod.GET)
   public ModelAndView deleteCustomer(@PathVariable("noid") Long noid, HttpServletRequest request, HttpServletResponse response) {
      try {
         String stringId = noid.toString();
         APIUtil.callAPI(Constants.OSP_API_LINK+"/customer/deleteById",stringId);
         HttpSession session = request.getSession();
         session.setAttribute(Constants.SESSION_ACTION, "Xóa Tổ chức công chứng thành công");
         return new ModelAndView("redirect:/customer/list");
      } catch (Exception e) {
         e.printStackTrace();
         return new ModelAndView("/customer/edit","customerUpdate",noid);
      }
   }

   public static List<Customer> getCustomerById(Long noid){

      String filter = "where noid = "+ noid;

         List<Customer> result = APIUtil.getCustomer(Constants.OSP_API_LINK +"/customer/selectByFilter", filter);
      return result;
   }
   public static List<Customer> getCustomerByCode(String code) {
      String filter = " where code = ";
      filter += "'" + code + "'";
      List<Customer> result = APIUtil.getCustomer(Constants.OSP_API_LINK + "/customer/getCustomerByCode", filter);
      return result;
   }
   public static List<Customer> getCustomerByName(String name) {
      String filter = " where name = ";
      filter += "'" + name + "'";
      List<Customer> result = APIUtil.getCustomer(Constants.OSP_API_LINK + "/customer/getCustomerByCode", filter);
      return result;
   }


}
