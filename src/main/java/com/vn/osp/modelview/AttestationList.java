package com.vn.osp.modelview;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
public class AttestationList {
    List<Attestation> attestationList;
    private int total;
    private int page;
    private int totalPage;
    private Integer type;
    private String name;
    private Integer org_type;

    public String getQueryString(){
        String whereString =" " ;
        if(!StringUtils.isBlank(name)){
            whereString = " and bo.name like '%"+name.trim()+"%' ";
        }

        if(type != null){
            whereString = " and bo.type = "+type;
        }

        if(org_type != null){
            whereString = " and bo.type_org = "+org_type;
        }
        return whereString;
    }
}
