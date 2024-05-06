package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.*;
import com.vn.osp.modelview.*;
import com.vn.osp.modelview.Package;
import com.vn.osp.service.APIUtil;
import com.vn.osp.service.QueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by quyenlc on 11/6/2021.
 */
@Controller
@RequestMapping("/package")
public class PackageController extends BaseController {

    @RequestMapping(value = "/package-list", method = RequestMethod.GET)
    public ModelAndView getPackageList(PackageListForm packageListForm, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView view = new ModelAndView();
        int firstRecord = 1;
        String action = (String) session.getAttribute(Constants.SESSION_ACTION);
        packageListForm.setAction_status(action);
        session.removeAttribute(Constants.SESSION_ACTION);

        int listNumber = 0;
        int totalPage = 0;
        int page = 1;
        String titleFilter = "";
        if (packageListForm != null) {
            listNumber = packageListForm.getListNumber();
            totalPage = packageListForm.getTotalPage();
            page = packageListForm.getPage();
            if (!StringUtils.isBlank(packageListForm.getTitleFilter())) {
                titleFilter = packageListForm.getTitleFilter().trim();
            }
        }
        listNumber = QueryFactory.countTotalPackage(titleFilter);
        totalPage = QueryFactory.countPage(listNumber);
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;
        packageListForm.setListNumber(listNumber);
        packageListForm.setTotalPage(totalPage);
        packageListForm.setPage(page);
        packageListForm.setTitleFilter(titleFilter);
        packageListForm.setPackages(QueryFactory.getPackageByPage(page, titleFilter));
        firstRecord = (page - 1) * 20 + 1;
        view.setViewName("/package/list");
        view.addObject("firstRecord", firstRecord);
        view.addObject("packageListForm", packageListForm);
        return view;
    }

    @RequestMapping(value = "/create-package-view", method = RequestMethod.GET)
    public ModelAndView createPackageView(PackageForm packageForm, HttpServletRequest request) {
        List<Function> notarySystemManagerList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
        List<Function> notaryfunctionList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_FUNCION);
        List<Function> notaryreportList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_REPORT);
        packageForm.setNotarySystemManagerList(notarySystemManagerList);
        packageForm.setNotaryfunctionList(notaryfunctionList);
        packageForm.setNotaryreportList(notaryreportList);
        return new ModelAndView("/package/add", "packageForm", packageForm);
    }

    @RequestMapping(value = "/create-package", method = RequestMethod.POST)
    public ModelAndView createPackage(PackageForm packageForm, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView();
        try {
            if (StringUtils.isBlank(packageForm.getCb01()) && StringUtils.isBlank(packageForm.getCb02()) && StringUtils.isBlank(packageForm.getCb03())) {
                List<Function> notarySystemManagerList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
                List<Function> notaryfunctionList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_FUNCION);
                List<Function> notaryreportList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_REPORT);
                packageForm.setNotarySystemManagerList(notarySystemManagerList);
                packageForm.setNotaryfunctionList(notaryfunctionList);
                packageForm.setNotaryreportList(notaryreportList);
                view.setViewName("/package/add");
                view.addObject("msg", "Thêm mới gói dịch vụ không thành công, bạn nên cấp ít nhất một quyền cho gói dịch vụ này !");
                view.addObject("packageForm", packageForm);
                return view;
            }
            if (!StringUtils.isBlank(packageForm.getCode()) && !StringUtils.isBlank(packageForm.getName())) {
                List<Package> packageList = getPackageByCode(packageForm.getCode());
                List<Package> packageList1 = getPackageByName(packageForm.getName());
                if (packageList != null) {
                    if (packageList.get(0).getCode().equals(packageForm.getCode())) {
                        List<Function> notarySystemManagerList1 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
                        List<Function> notaryfunctionList1 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_FUNCION);
                        List<Function> notaryreportList1 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_REPORT);
                        packageForm.setNotarySystemManagerList(notarySystemManagerList1);
                        packageForm.setNotaryfunctionList(notaryfunctionList1);
                        packageForm.setNotaryreportList(notaryreportList1);
                        packageForm.setCode_("Mã gói dịch vụ đã tồn tại !");
                        view.setViewName("/package/add");
                        view.addObject("packageForm", packageForm);
                        return view;
                    }
                }
                if (packageList1 != null) {
                    if (packageList1.get(0).getName().equals(packageForm.getName())) {
                        List<Function> notarySystemManagerList2 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
                        List<Function> notaryfunctionList2 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_FUNCION);
                        List<Function> notaryreportList2 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_REPORT);
                        packageForm.setNotarySystemManagerList(notarySystemManagerList2);
                        packageForm.setNotaryfunctionList(notaryfunctionList2);
                        packageForm.setNotaryreportList(notaryreportList2);
                        packageForm.setName_("Tên gói dịch vụ đã tồn tại !");
                        view.setViewName("/package/add");
                        view.addObject("packageForm", packageForm);
                        return view;
                    }
                }
            }
            packageForm.validate();
            if (packageForm.isSuccess() == false) {
                List<Function> notarySystemManagerList3 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
                List<Function> notaryfunctionList3 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_FUNCION);
                List<Function> notaryreportList3 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_REPORT);
                packageForm.setNotarySystemManagerList(notarySystemManagerList3);
                packageForm.setNotaryfunctionList(notaryfunctionList3);
                packageForm.setNotaryreportList(notaryreportList3);
                return new ModelAndView("/package/add", "packageForm", packageForm);
            }
            String result = packageForm.generateAddJson();
            APIUtil.callAPIResult(Constants.OSP_API_LINK + "/package/insert", result.toString());
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, "Thêm mới thông tin gói dịch vụ thành công");
            return new ModelAndView("redirect:/package/package-list");
        } catch (Exception e) {
            return new ModelAndView("/package/add", "packageForm", packageForm);
        }
    }

    @RequestMapping(value = "/update-package", method = RequestMethod.POST)
    public ModelAndView updatePackage(PackageForm packageForm, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView view = new ModelAndView();
        try {
            if (StringUtils.isBlank(packageForm.getCb01()) && StringUtils.isBlank(packageForm.getCb02()) && StringUtils.isBlank(packageForm.getCb03())) {
                List<Function> notarySystemManagerList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
                List<Function> notaryfunctionList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_FUNCION);
                List<Function> notaryreportList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_REPORT);
                packageForm.setNotarySystemManagerList(notarySystemManagerList);
                packageForm.setNotaryfunctionList(notaryfunctionList);
                packageForm.setNotaryreportList(notaryreportList);
                view.setViewName("/package/edit");
                view.addObject("msg", "Cập nhật gói dịch vụ không thành công, bạn nên cấp ít nhất một quyền cho gói dịch vụ này !");
                view.addObject("packageForm", packageForm);
                return view;
            }
            List<Package> packages = getPackageById(packageForm.getId());
            if (packages != null) {
                if (packages.get(0).getCode().equals(packageForm.getCode()) && packages.get(0).getName().equals(packageForm.getName())) {
                    List<Function> notarySystemManagerList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
                    List<Function> notaryfunctionList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_FUNCION);
                    List<Function> notaryreportList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_REPORT);
                    packageForm.setNotarySystemManagerList(notarySystemManagerList);
                    packageForm.setNotaryfunctionList(notaryfunctionList);
                    packageForm.setNotaryreportList(notaryreportList);
                } else {
                    if (!StringUtils.isBlank(packageForm.getName())) {
//                        List<Package> packageList = getPackageByCode(packageForm.getCode());
                        List<Package> packageList1 = getPackageByName(packageForm.getName());
//                        if (packageList != null && !packageList.get(0).getCode().equals(packages.get(0).getCode())) {
//                            if (packageList.get(0).getCode().equals(packageForm.getCode())) {
//                                List<Function> notarySystemManagerList1 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
//                                List<Function> notaryfunctionList1 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_FUNCION);
//                                List<Function> notaryreportList1 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_REPORT);
//                                packageForm.setNotarySystemManagerList(notarySystemManagerList1);
//                                packageForm.setNotaryfunctionList(notaryfunctionList1);
//                                packageForm.setNotaryreportList(notaryreportList1);
//                                packageForm.setCode_("Mã gói dịch vụ đã tồn tại !");
//                                view.setViewName("/package/edit");
//                                view.addObject("packageForm", packageForm);
//                                return view;
//                            }
//                        }
                        if (packageList1 != null && !packageList1.get(0).getName().equals(packages.get(0).getName())) {
                            if (packageList1.get(0).getName().equals(packageForm.getName())) {
                                List<Function> notarySystemManagerList2 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
                                List<Function> notaryfunctionList2 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_FUNCION);
                                List<Function> notaryreportList2 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_REPORT);
                                packageForm.setNotarySystemManagerList(notarySystemManagerList2);
                                packageForm.setNotaryfunctionList(notaryfunctionList2);
                                packageForm.setNotaryreportList(notaryreportList2);
                                packageForm.setName_("Tên gói dịch vụ đã tồn tại !");
                                view.setViewName("/package/edit");
                                view.addObject("packageForm", packageForm);
                                return view;
                            }
                        }
                    }
                }
            }

            packageForm.validate();
            if (packageForm.isSuccess() == false) {
                List<Function> notarySystemManagerList3 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
                List<Function> notaryfunctionList3 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_FUNCION);
                List<Function> notaryreportList3 = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_REPORT);
                packageForm.setNotarySystemManagerList(notarySystemManagerList3);
                packageForm.setNotaryfunctionList(notaryfunctionList3);
                packageForm.setNotaryreportList(notaryreportList3);
                return new ModelAndView("/package/edit", "packageForm", packageForm);
            }
            String result = packageForm.generateUpdateJson();
            APIUtil.callAPI(Constants.OSP_API_LINK + "/package/update", result);
            session.setAttribute(Constants.SESSION_ACTION, "Cập nhật thông tin gói dịch vụ thành công");
            return new ModelAndView("redirect:/package/package-list");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/package/edit", "packageForm", packageForm);
        }
    }

    @RequestMapping(value = "/delete-package/{id}", method = RequestMethod.GET)
    public ModelAndView deletePackage(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            List<Package> list = getPackageById(id);
            String code = "";
            if (list.size() > 0) {
                Package packageDelete = list.get(0);
                code = packageDelete.getCode();
            }
            List<Customer> customers = getCustomerByCode(code);
            if (customers != null) {
                ModelAndView view = new ModelAndView();
                redirectAttributes.addFlashAttribute("errorCode", "Gói dịch vụ đang được khách hàng sử dụng không thể xóa !");
                view.setViewName("redirect:/package/update-package-view/" + id);
                return view;
            } else {
                String stringId = id.toString();
                APIUtil.callAPI(Constants.OSP_API_LINK + "/package/deleteById", stringId);
                HttpSession session = request.getSession();
                session.setAttribute(Constants.SESSION_ACTION, "Xóa gói dịch vụ thành công");
            }
            return new ModelAndView("redirect:/package/package-list");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/package/edit", "packageForm", id);
        }
    }


    @RequestMapping(value = "/update-package-view/{id}", method = RequestMethod.GET)
    public ModelAndView updatePackageView(@PathVariable long id, RedirectAttributes redirectAttributes) {
        PackageForm packageForm = new PackageForm();
        List<Function> notarySystemManagerList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
        List<Function> notaryfunctionList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_FUNCION);
        List<Function> notaryreportList = QueryFactory.getFunctionByType(Constants.AUTHORITY_TYPE_REPORT);
        packageForm.setNotarySystemManagerList(notarySystemManagerList);
        packageForm.setNotaryfunctionList(notaryfunctionList);
        packageForm.setNotaryreportList(notaryreportList);

        List<Package> list = getPackageById(id);
        if (list.size() > 0) {
            Package packageUpdate = list.get(0);
            packageForm.setId(packageUpdate.getId());
            packageForm.setCode(packageUpdate.getCode());
            packageForm.setName(packageUpdate.getName());
            packageForm.setUser_num(packageUpdate.getUser_num());
            packageForm.setDescription(packageUpdate.getDescription());
            packageForm.setNote(packageUpdate.getNote());
        }

        String filter = " where package_code=";
        filter += "'" + packageForm.getCode() + "'";
        List<PackageFunction> packageFunctionList = QueryFactory.selectPackageFunctionbyFilter(filter);
        String cb01 = "";
        String cb02 = "";
        String cb03 = "";
        if (packageFunctionList != null) {
            for (int i = 0; i < packageFunctionList.size(); i++) {
                PackageFunction packageFunction = packageFunctionList.get(i);
                String function_code = packageFunction.getFunction_code();
                List<Function> functionList = QueryFactory.getFunctionByCode(" where code like '" + function_code + "'");
                if (functionList == null || functionList.size() == 0) {
                    continue;
                }
                long type = functionList.get(0).getType();
                if (type == 1) {
                    cb01 = generateFunctionCode(new StringBuilder(cb01), function_code, (int) type);
                }
                if (type == 2) {
                    cb02 = generateFunctionCode(new StringBuilder(cb02), function_code, (int) type);
                }
                if (type == 3) {
                    cb03 = generateFunctionCode(new StringBuilder(cb03), function_code, (int) type);
                }
                packageForm.setCb01(cb01);
                packageForm.setCb02(cb02);
                packageForm.setCb03(cb03);
            }
        } else {
            packageForm.setCb01(cb01);
            packageForm.setCb02(cb02);
            packageForm.setCb03(cb03);
        }
        return new ModelAndView("/package/edit", "packageForm", packageForm);
    }

    /*
     * Createdby quyenlc 09/06/2021
     * Ham generate chuoi function_code de dua len giao dien
     * */
    public String generateFunctionCode(StringBuilder cb, String function_code, int type) {
        switch (type) {
            case 1:
                cb.append(function_code + "_64,");
                cb.append(function_code + "_32,");
                cb.append(function_code + "_16,");
                cb.append(function_code + "_8,");
                cb.append(function_code + "_4,");
                cb.append(function_code + "_2,");
                cb.append(function_code + "_1,");
                cb.append(function_code + "_0,");
                break;
            case 2:
                cb.append(function_code + "_64,");
                cb.append(function_code + "_32,");
                cb.append(function_code + "_16,");
                cb.append(function_code + "_8,");
                cb.append(function_code + "_4,");
                cb.append(function_code + "_2,");
                cb.append(function_code + "_128,");
                cb.append(function_code + "_256,");
                cb.append(function_code + "_512,");
                cb.append(function_code + "_0,");
                break;
            case 3:
                cb.append(function_code + "_64,");
                cb.append(function_code + "_4,");
                cb.append(function_code + "_2,");
                cb.append(function_code + "_0,");
                break;
            default:
                break;
        }
        return cb.toString();
    }


    public static List<Package> getPackageById(Long id) {
        String filter = " where id = " + id;
        List<Package> result = APIUtil.getPackageByFilter(Constants.OSP_API_LINK + "/package/get-package-by-filter", filter);
        return result;
    }

    public static List<Customer> getCustomerByCode(String code) {
        String filter = " where pakage_code = ";
        filter += "'" + code + "'";
        List<Customer> result = APIUtil.getCustomer(Constants.OSP_API_LINK + "/customer/getCustomerByCode", filter);
        return result;
    }

    public static List<Package> getPackageByCode(String code) {
        String filter = " where code = ";
        filter += "'" + code + "'";
        List<Package> result = APIUtil.getPackageByFilter(Constants.OSP_API_LINK + "/package/get-package-by-filter", filter);
        return result;
    }

    public static List<Package> getPackageByName(String name) {
        String filter = " where name = ";
        filter += "'" + name + "'";
        List<Package> result = APIUtil.getPackageByFilter(Constants.OSP_API_LINK + "/package/get-package-by-filter", filter);
        return result;
    }


}
