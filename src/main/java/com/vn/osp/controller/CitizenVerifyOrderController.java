package com.vn.osp.controller;


import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.FileUtil;
import com.vn.osp.common.util.PagingResult;
import com.vn.osp.common.util.SystemProperties;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.*;
import com.vn.osp.service.QueryFactory;
import com.vn.osp.service.factory.TemplateFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/giao-dich-phi-xac-thuc")
public class CitizenVerifyOrderController {
    @RequestMapping(value = "/danh-sach", method = RequestMethod.GET)
    public ModelAndView privyList(CitizenVerifyOrdersWrapper citizenVerifyOrdersWrapper, HttpServletRequest request, RedirectAttributes redirectAttributes){
        PagingResult page = new PagingResult();
        page.setPageNumber(citizenVerifyOrdersWrapper.getPage());
        try{
            page = QueryFactory.getPageCitizenVerifyOrder(citizenVerifyOrdersWrapper);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("getMessage","Truy xuất thất bại, kiểm tra kết nối server trung gian hoặc liên hệ Admin!");
        }

//        page = TemplateFactory.getPageCitizenVerifyOrders(page);
        List<User> users = QueryFactory.getAllUser();
        citizenVerifyOrdersWrapper.setCitizenVerifyOrders((List<CitizenVerifyOrders>) page.getItems());
        citizenVerifyOrdersWrapper.setTotal(page.getRowCount());
        citizenVerifyOrdersWrapper.setTotalPage(page.getPageCount());
        citizenVerifyOrdersWrapper.setUser_updates(users);

        redirectAttributes.addAttribute("idOpenDetail",citizenVerifyOrdersWrapper.getIdOpenDetail());
        redirectAttributes.addAttribute("isOpenDetail",StringUtils.isNotBlank(citizenVerifyOrdersWrapper.getIdOpenDetail()) ? "true" : "false");
        redirectAttributes.addAttribute("updatedStatus",null);
        redirectAttributes.addAttribute("updatedMessage","");

        return new ModelAndView("citizenVerifyOrder/list","dataWrapper",citizenVerifyOrdersWrapper);
    }

    @RequestMapping(value = "/chinh-sua", method = RequestMethod.POST)
    public ModelAndView uploadFiles(@RequestParam("files") MultipartFile[] files,
                                    @RequestParam(value = "file_remove", required = false) String file_remove,
                                    @RequestParam("order_id") String order_id,
                                    @RequestParam("transaction_status") String transaction_status,
                                    @RequestParam(value = "status", required = false) String status,
                                    @RequestParam(value = "note", required = false) String note,
                                    @RequestParam("urlScreenList") String urlScreenList,
                                    RedirectAttributes redirectAttributes,
                                    HttpServletRequest request) {

        //init session
        HttpSession session = request.getSession();
        User userS = new User();
        try{
            userS = (User) session.getAttribute(User.SESSION_KEY);
        } catch (Exception e){
            return new ModelAndView("redirect:/login-view");
        }

//        CommonContext commonContext1 = (CommonContext) session.getAttribute(session.getId());
//        User userSession = QueryFactory.getUserByAccount(userS.getAccount()).get(0);

        String folder = SystemProperties.getProperty("system_folder");

        List<String> fileNames = new ArrayList<>();
        List<String> filePaths = new ArrayList<>();
        try {

            // Xử lý các tệp tin đã được tải lên
            for (MultipartFile file : files) {
                String localName = EditString.convertUnicodeToASCII(file.getOriginalFilename());
                if(StringUtils.isBlank(localName) && file.getSize()==0) continue;
                // Thực hiện các thao tác xử lý tệp tin tại đây
                File fileSave = FileUtil.createNewFile(folder, "PAYMENT_");
                String path = "";
                FileOutputStream outputStream = null;
                outputStream = new FileOutputStream(fileSave);
                outputStream.write(file.getBytes());
                path = fileSave.getAbsolutePath();
                String path2 = fileSave.getPath();
                fileNames.add(localName);
                filePaths.add(path);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        //todo call api
        CitizenVerifyOrderDTO itemUpdate = new CitizenVerifyOrderDTO();
        itemUpdate.setOrder_id(order_id);
        itemUpdate.setTransaction_status(transaction_status);
        itemUpdate.setStatus(status);
        itemUpdate.setFile_names(fileNames);
        itemUpdate.setFile_paths(filePaths);
        itemUpdate.setFiles_id_remove(file_remove);
        itemUpdate.setNote(note);
        itemUpdate.setUpdate_user_name(userS.getAccount());

        boolean updatedStatus = true;
        String updatedMessage = "";
        try{
            Object response = QueryFactory.updateCitizenVerifyOrder(itemUpdate.generateUpdateJson());
            if(response.equals(Boolean.valueOf(false))){
                updatedStatus = false;
            }
        }catch (Exception e){
            e.printStackTrace();
            updatedStatus = false;
        }
        updatedMessage = updatedStatus ? "Cập nhật thành công!" : "Cập nhật thất bại, liên hệ Admin!";

        // Chuyển hướng hoặc hiển thị một trang kết quả
        redirectAttributes.addFlashAttribute("idOpenDetail",order_id);
        redirectAttributes.addFlashAttribute("isOpenDetail","true");
        redirectAttributes.addFlashAttribute("updatedStatus",updatedStatus);
        redirectAttributes.addFlashAttribute("updatedMessage",updatedMessage);
        return new ModelAndView("redirect:/giao-dich-phi-xac-thuc/danh-sach"+urlScreenList);
    }
}
