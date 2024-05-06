package com.vn.osp.controller;

import com.vn.osp.common.util.PagingResult;
import com.vn.osp.modelview.CitizenVerifications;
import com.vn.osp.modelview.CitizenVerificationsWrapper;
import com.vn.osp.modelview.CitizenVerifyOrders;
import com.vn.osp.modelview.CitizenVerifyOrdersWrapper;
import com.vn.osp.service.QueryFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/xac-thuc-danh-tinh")
public class CitizenVerificationController {
    @RequestMapping(value = "/danh-sach", method = RequestMethod.GET)
    public ModelAndView list(CitizenVerificationsWrapper citizenVerificationsWrapper, HttpServletRequest request) throws UnsupportedEncodingException {
        PagingResult page = new PagingResult();
        page.setPageNumber(citizenVerificationsWrapper.getPage());
        page = QueryFactory.getPageCitizenVerification(citizenVerificationsWrapper);
//        page = TemplateFactory.getPageCitizenVerifyOrders(page);
        citizenVerificationsWrapper.setCitizenVerifications((List<CitizenVerifications>) page.getItems());
        citizenVerificationsWrapper.setTotal(page.getRowCount());
        citizenVerificationsWrapper.setTotalPage(page.getPageCount());

        return new ModelAndView("citizenVerification/list","dataWrapper",citizenVerificationsWrapper);
    }
}
