package com.vn.osp.service.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.PagingResult;
import com.vn.osp.modelview.Attestation;
import com.vn.osp.modelview.PrivyTemplate;
import com.vn.osp.modelview.PropertyTemplate;
import com.vn.osp.service.client.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Daicq on 23/11/2020.
 */
public class AttestationTemplateFactory {

    /*for property template */
    public static PagingResult listAttestationTemplate(PagingResult page){
        if(page.getPageNumber()<1) page.setNumberPerPage(1);
        int offset=page.getNumberPerPage()*(page.getPageNumber()-1);
        String param2[]={"offset",String.valueOf(offset)};
        String param3[]={"number",String.valueOf(page.getNumberPerPage())};
        List<String[]> params=new ArrayList<String[]>();
        params.add(param2);
        params.add(param3);

        String response="/api-attestation/get-page";
        response=Client.getObjectByParams(response,params);

        String path="/api-attestation/get-total";
        path=Client.getObject(path);

        List<Attestation> items=new ArrayList<Attestation>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            items = Arrays.asList(objectMapper.readValue(response, Attestation[].class));
            page.setItems(items);
            if(EditString.isNumber(path)){
                page.setRowCount(Integer.parseInt(path));
            }
        }catch (Exception e){
            System.out.printf("error loi roi AttestationTemplateFactory.listAttestationTemplate: "+e.getMessage());
        }

        return page;
    }

    /*for property template */
    public static List<Attestation> listAttestationTemplate(){

        String response="/api-attestation/search";
        response=Client.getObjectByParams(response,null);
        List<Attestation> items=new ArrayList<Attestation>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            items = Arrays.asList(objectMapper.readValue(response, Attestation[].class));
        }catch (Exception e){
            System.out.printf("error loi roi AttestationTemplateFactory.listAttestationTemplate: "+e.getMessage());
        }

        return items;
    }

    public static Boolean addTemplate(Attestation item){

        String response="/api-attestation/add";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            response=Client.postObject(response,objectMapper.writeValueAsString(item));
            return Boolean.parseBoolean(response);
        }catch (Exception e){
            System.out.printf("error loi AttestationTemplateFactory.addTemplate: "+e.getMessage());
        }

        return false;
    }

    public static Boolean updateTemplate(Attestation item){
        String response="/api-attestation/update";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            response=Client.putObject(response,objectMapper.writeValueAsString(item));
            return Boolean.parseBoolean(response);
        }catch (Exception e){
            System.out.println("error roi AttestationTemplateFactory.updateTemplate: "+e.getMessage());
        }

        return false;
    }

    public static Attestation getTemplate(String id){
        String response="/api-attestation/get";
        response=Client.getObjectByFilter(response,"id",id);
        Attestation item=new Attestation();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            item=objectMapper.readValue(response, Attestation.class);
            return item;
        }catch (Exception e){
            System.out.printf("error roi AttestationTemplateFactory.getTemplate: "+e.getMessage());
        }

        return null;
    }

    public static boolean deleteTemplate(String id){
        String response="/api-attestation/delete";
        response=Client.deleteObject(response,"id",id);
        return Boolean.parseBoolean(response);
    }

}
