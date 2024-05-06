package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by minh on 5/30/2017.
 */
public class ContractKindList {
    private List<ContractKind> listContractKind;
    private int total;
    private int page;
    private int totalPage;

    private String action_status;
    private String titleFilter;
    private String name;
    private String code;




    public ContractKindList() {
    }

    public List<ContractKind> getListContractKind() {
        return listContractKind;
    }

    public void setListContractKind(List<ContractKind> listContractKind) {
        this.listContractKind = listContractKind;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getTitleFilter() {
        return titleFilter;
    }

    public void setTitleFilter(String titleFilter) {
        this.titleFilter = titleFilter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderString(){
        String whereString ="where 1=1" ;
        String orderString1 ="";
        String orderString2 = "";

        if(name !=null && !name.equals("")){
            orderString1 = " and name like '%"+name.trim()+"%' " ;
        }
        if(code!= null && !code.equals("")){
            orderString2 = " and contract_kind_code like '%"+code.trim()+"%'";
        }

        String query = whereString + orderString1 +orderString2 ;
        return query;
    }

}
