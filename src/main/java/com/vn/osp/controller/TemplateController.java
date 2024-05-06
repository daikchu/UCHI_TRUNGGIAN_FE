package com.vn.osp.controller;

import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.PagingResult;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.PrivyTemplate;
import com.vn.osp.modelview.PropertyTemplate;
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
import java.util.UUID;

/**
 * Created by TienManh on 8/8/2017.
 */
@Controller
@RequestMapping(value = "/template")
public class TemplateController {

    @RequestMapping(value = "/privy/list", method = RequestMethod.GET)
    public ModelAndView privyList(PagingResult page, HttpServletRequest request){
        page= TemplateFactory.listPrivyTemplate(page);

        return new ModelAndView("template/privy/list","page",page);
    }

    @RequestMapping(value = "/privy/detail/{id}", method = RequestMethod.GET)
    public ModelAndView privyDetail(@PathVariable("id") String id, HttpServletRequest request){

        return new ModelAndView("template/privy/detail","id",id);
    }

    @RequestMapping(value = "/privy/delete", method = RequestMethod.POST)
    public ModelAndView privyDelete(@RequestParam("id") String id, HttpServletRequest request){
        if(StringUtils.isBlank(id) || !EditString.isNumber(id)){
            return new  ModelAndView("redirect:/template/privy/list");
        }
        boolean check=TemplateFactory.deletePrivyTemplate(id);
        if(check){
            //delete ok
        }
        return new ModelAndView("redirect:/template/privy/list");
    }

    @RequestMapping(value = "/privy/edit/{id}", method = RequestMethod.GET)
    public ModelAndView privyEdit(@PathVariable("id") String id, HttpServletRequest request){
        if(StringUtils.isBlank(id)){
            return new ModelAndView("redirect:/template/privy/list");
        }
        PrivyTemplate item=TemplateFactory.getPrivyyTemplate(id);
        if(!StringUtils.isBlank(item.getHtml())){
            item.setHtml(StringUtil.removeSpecialCharactersNotHTML(item.getHtml()));
            item.setHtml(item.getHtml().trim());
        }
        return new ModelAndView("template/privy/edit","item",item);
    }

    @RequestMapping(value = "/privy/update", method = RequestMethod.POST)
    public ModelAndView privyEditSave(PrivyTemplate item, HttpServletRequest request, HttpServletResponse response){
        if(item==null || item.getId()==0){
            //return list privy
        }
        if(!StringUtils.isBlank(item.getHtml())){
            item.setHtml(StringUtil.removeSpecialCharactersNotHTML(item.getHtml()));
            item.setHtml(item.getHtml().trim());
        }
        item.setUpdate_user((int)(long)CommonContext.user.getUserId());
        item.setUpdate_time(new Date());
        boolean checkUpdate=TemplateFactory.updatePrivyTemplate(item);
        if(!checkUpdate){
            return new ModelAndView("template/privy/edit","item",item);
        }
        return new ModelAndView("redirect:/template/privy/list");

    }

    @RequestMapping(value = "/privy/add", method = RequestMethod.GET)
    public ModelAndView privyAdd(PrivyTemplate item, HttpServletRequest request){
        if(item==null || item.getName()==null || item.getHtml()==null){
            item=new PrivyTemplate();
            return new ModelAndView("template/privy/add","item",item);
        }
        if(!StringUtils.isBlank(item.getHtml())){
           item.setHtml(StringUtil.removeSpecialCharactersNotHTML(item.getHtml()));
            item.setHtml(item.getHtml().trim());
        }
        item.setEntry_time(new Date());
        item.setUpdate_time(item.getEntry_time());
        item.setEntry_user((int)(long)CommonContext.user.getUserId());
        item.setUpdate_user((int)(long)CommonContext.user.getUserId());
        boolean checkAdd=TemplateFactory.addPrivyTemplate(item);
        if(!checkAdd){
            return new ModelAndView("template/privy/add","item",item);
        }
        return new ModelAndView("redirect:/template/privy/list");
    }

    /*property*/

    @RequestMapping(value = "/property/list", method = RequestMethod.GET)
    public ModelAndView propertyList(PagingResult page, HttpServletRequest request){
        page= TemplateFactory.listPropertyTemplate(page);
        return new ModelAndView("template/property/list","page",page);
    }


    @RequestMapping(value = "/property/delete", method = RequestMethod.POST)
    public ModelAndView propertyDelete(@RequestParam("id") String id, HttpServletRequest request){
        if(StringUtils.isBlank(id) || !EditString.isNumber(id)){
            return new  ModelAndView("redirect:/template/property/list");
        }
        boolean check=TemplateFactory.deletePropertyTemplate(id);
        if(check){
            //delete ok
        }
        return new ModelAndView("redirect:/template/property/list");
    }

    @RequestMapping(value = "/property/edit/{id}", method = RequestMethod.GET)
    public ModelAndView propertyEdit(@PathVariable("id") String id, HttpServletRequest request){
        if(StringUtils.isBlank(id)){
            return new ModelAndView("redirect:/template/property/list");
        }
        PropertyTemplate item=TemplateFactory.getPropertyTemplate(id);
        if(!StringUtils.isBlank(item.getHtml())){
            item.setHtml(StringUtil.removeSpecialCharactersNotHTML(item.getHtml()));
            item.setHtml(item.getHtml().trim());
        }
        return new ModelAndView("template/property/edit","item",item);
    }

    @RequestMapping(value = "/property/update", method = RequestMethod.POST)
    public ModelAndView propertyEditSave(PropertyTemplate item, HttpServletRequest request, HttpServletResponse response){
        if(item==null || item.getId()==0 || StringUtils.isBlank(item.getType())){
        }
        if(!StringUtils.isBlank(item.getHtml())){
            item.setHtml(StringUtil.removeSpecialCharactersNotHTML(item.getHtml()));
            item.setHtml(item.getHtml().trim());
        }
        item.setUpdate_user((int)(long)CommonContext.user.getUserId());
        item.setUpdate_time(new Date());
        boolean checkUpdate=TemplateFactory.updatePropertyTemplate(item);
        if(!checkUpdate){
            return new ModelAndView("template/property/edit","item",item);
        }
        return new ModelAndView("redirect:/template/property/list");

    }

    @RequestMapping(value = "/property/add", method = RequestMethod.GET)
    public ModelAndView propertyAdd(PropertyTemplate item, HttpServletRequest request){
        if(item==null || item.getName()==null || item.getHtml()==null || StringUtils.isBlank(item.getType())){
            item=new PropertyTemplate();
            return new ModelAndView("template/property/add","item",item);
        }
        if(!StringUtils.isBlank(item.getHtml())){
            item.setHtml(StringUtil.removeSpecialCharactersNotHTML(item.getHtml()));
            item.setHtml(item.getHtml().trim());
        }

        item.setEntry_time(new Date());
        item.setUpdate_time(item.getEntry_time());
        item.setEntry_user((int)(long)CommonContext.user.getUserId());
        item.setUpdate_user((int)(long)CommonContext.user.getUserId());
        boolean checkAdd=TemplateFactory.addPropertyTemplate(item);
        if(!checkAdd){
            return new ModelAndView("template/property/add","item",item);
        }
        return new ModelAndView("redirect:/template/property/list");
    }

}
