package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.PagingResult;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.Attestation;
import com.vn.osp.modelview.AttestationList;
import com.vn.osp.modelview.PrivyTemplate;
import com.vn.osp.service.factory.AttestationTemplateFactory;
import com.vn.osp.service.factory.TemplateFactory;
import com.vn.osp.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/attestation-template")
public class AttestationController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request){
        List<Attestation> list = AttestationTemplateFactory.listAttestationTemplate();

        return new ModelAndView("template/attestation/list","list",list);
    }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public ModelAndView add(HttpServletRequest request){
      Attestation item = new Attestation();
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("template/attestation/add");
      modelAndView.addObject("item", item);
      return modelAndView;
  }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView privyDetail(@PathVariable("id") String id, HttpServletRequest request){

        return new ModelAndView("template/attestation/detail","id",id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView privyDelete(@RequestParam("id") String id, HttpServletRequest request){
        if(StringUtils.isBlank(id) || !EditString.isNumber(id)){
            return new  ModelAndView("redirect:/contracttemplate/list");
        }
        boolean check=AttestationTemplateFactory.deleteTemplate(id);
        if(check){
            //delete ok
        }
        return new ModelAndView("redirect:/attestation-template/list");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView privyEdit(@PathVariable("id") String id, HttpServletRequest request){
        if(StringUtils.isBlank(id)){
            return new ModelAndView("redirect:/attestation-template/list");
        }
        PrivyTemplate item= TemplateFactory.getPrivyyTemplate(id);
        if(!StringUtils.isBlank(item.getHtml())){
            item.setHtml(StringUtil.removeSpecialCharactersNotHTML(item.getHtml()));
            item.setHtml(item.getHtml().trim());
        }
        return new ModelAndView("template/attestation/edit","item",item);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView privyEditSave(PrivyTemplate item, HttpServletRequest request, HttpServletResponse response){
        if(item==null || item.getId()==0){
            //return list privy
        }
        if(!StringUtils.isBlank(item.getHtml())){
            item.setHtml(StringUtil.removeSpecialCharactersNotHTML(item.getHtml()));
            item.setHtml(item.getHtml().trim());
        }
        item.setUpdate_user((int)(long) CommonContext.user.getUserId());
        item.setUpdate_time(new Date());
        boolean checkUpdate=TemplateFactory.updatePrivyTemplate(item);
        if(!checkUpdate){
            return new ModelAndView("template/attestation/edit","item",item);
        }
        return new ModelAndView("redirect:/attestation-template/list");

    }

}
