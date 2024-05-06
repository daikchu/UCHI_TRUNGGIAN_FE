package com.vn.osp.service.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.PagingResult;
import com.vn.osp.modelview.PrivyTemplate;
import com.vn.osp.modelview.PropertyTemplate;
import com.vn.osp.service.client.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TienManh on 8/9/2017.
 */
public class TemplateFactory {

    /*for property template */
    public static PagingResult listPropertyTemplate(PagingResult page){
        if(page.getPageNumber()<1) page.setNumberPerPage(1);
        int offset=page.getNumberPerPage()*(page.getPageNumber()-1);
        String param2[]={"offset",String.valueOf(offset)};
        String param3[]={"number",String.valueOf(page.getNumberPerPage())};
        List<String[]> params=new ArrayList<String[]>();
        params.add(param2);
        params.add(param3);

        String response="/ContractTemplate/property-template-get-page";
        response=Client.getObjectByParams(response,params);

        String path="/ContractTemplate/property-template-total";
        path=Client.getObject(path);

        List<PropertyTemplate> items=new ArrayList<PropertyTemplate>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            items = Arrays.asList(objectMapper.readValue(response, PropertyTemplate[].class));
            page.setItems(items);
            if(EditString.isNumber(path)){
                page.setRowCount(Integer.parseInt(path));
            }
        }catch (Exception e){
            System.out.printf("error roi listPropertyTemplate: "+e.getMessage());
        }

        return page;
    }

    public static Boolean addPropertyTemplate(PropertyTemplate item){

        String response="/ContractTemplate/property-template";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            response=Client.postObject(response,objectMapper.writeValueAsString(item));
            return Boolean.parseBoolean(response);
        }catch (Exception e){
            System.out.printf("error roi addPropertyTemplate: "+e.getMessage());
        }

        return false;
    }

    public static Boolean updatePropertyTemplate(PropertyTemplate item){
        String response="/ContractTemplate/property-template";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            response=Client.putObject(response,objectMapper.writeValueAsString(item));
            return Boolean.parseBoolean(response);
        }catch (Exception e){
            System.out.println("error roi updatePropertyTemplate: "+e.getMessage());
        }

        return false;
    }

    public static PropertyTemplate getPropertyTemplate(String id){
        String response="/ContractTemplate/property-template-by-id";
        response=Client.getObjectByFilter(response,"id",id);
        PropertyTemplate item=new PropertyTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            item=objectMapper.readValue(response, PropertyTemplate.class);
            return item;
        }catch (Exception e){
            System.out.printf("error roi getPropertyTemplate: "+e.getMessage());
        }

        return null;
    }

    public static boolean deletePropertyTemplate(String id){
        String response="/ContractTemplate/property-template";
        response=Client.deleteObject(response,"id",id);
        return Boolean.parseBoolean(response);
    }

    /*for privy template */
    public static PagingResult listPrivyTemplate(PagingResult page){
        if(page.getPageNumber()<1) page.setNumberPerPage(1);
        int offset=page.getNumberPerPage()*(page.getPageNumber()-1);
        String param2[]={"offset",String.valueOf(offset)};
        String param3[]={"number",String.valueOf(page.getNumberPerPage())};
        List<String[]> params=new ArrayList<String[]>();
        params.add(param2);
        params.add(param3);

        String response="/ContractTemplate/privy-template-get-page";
        response=Client.getObjectByParams(response,params);

        String path="/ContractTemplate/privy-template-total";
        path=Client.getObject(path);

        List<PrivyTemplate> items=new ArrayList<PrivyTemplate>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            items = Arrays.asList(objectMapper.readValue(response, PrivyTemplate[].class));
            page.setItems(items);
            if(EditString.isNumber(path)){
                page.setRowCount(Integer.parseInt(path));
            }
        }catch (Exception e){
            System.out.printf("error roi listPrivyTemplate: "+e.getMessage());
        }

        return page;
    }

    public static Boolean addPrivyTemplate(PrivyTemplate item){

        String response="/ContractTemplate/privy-template";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            response=Client.postObject(response,objectMapper.writeValueAsString(item));
            return Boolean.parseBoolean(response);
        }catch (Exception e){
            System.out.printf("error roi addPrivyTemplate: "+e.getMessage());
        }

        return false;
    }

    public static Boolean updatePrivyTemplate(PrivyTemplate item){
        String response="/ContractTemplate/privy-template";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            response=Client.putObject(response,objectMapper.writeValueAsString(item));
            return Boolean.parseBoolean(response);
        }catch (Exception e){
            System.out.println("error roi updatePrivyTemplate: "+e.getMessage());
        }

        return false;
    }

    public static PrivyTemplate getPrivyyTemplate(String id){
        String response="/ContractTemplate/privy-template-by-id";
        response=Client.getObjectByFilter(response,"id",id);
        PrivyTemplate item=new PrivyTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            item=objectMapper.readValue(response, PrivyTemplate.class);
            return item;
        }catch (Exception e){
            System.out.printf("error roi getPrivyyTemplate: "+e.getMessage());
        }
        return null;
    }

    public static boolean deletePrivyTemplate(String id){
        String response="/ContractTemplate/privy-template";
        response=Client.deleteObject(response,"id",id);
        return Boolean.parseBoolean(response);
    }

    public static PagingResult getPageCitizenVerifyOrders(PagingResult page) {

        return new PagingResult();
    }
}
