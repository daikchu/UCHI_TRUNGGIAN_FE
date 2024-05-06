package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by Admin on 31/5/2017.
 */
public class BankList {

    private List<Bank> listBank;
    private String name;
    private String code;
    private int page;
    private int totalPage;
    private int totalBank;
    private String action_status ;

    public BankList() {
    }

    public BankList(List<Bank> listBank, String name, String code, int page, int totalPage, int totalBank) {
        this.listBank = listBank;
        this.name = name;
        this.code = code;
        this.page = page;
        this.totalPage = totalPage;
        this.totalBank = totalBank;
    }

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public List<Bank> getListBank() {
        return listBank;
    }

    public void setListBank(List<Bank> listBank) {
        this.listBank = listBank;
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

    public int getTotalBank() {
        return totalBank;
    }

    public void settotalBank(int totalBank) {
        this.totalBank = totalBank;
    }
    public String getOrderString(){
        String whereString ="where 1=1" ;
        String orderString1 ="";
        String orderString2 = "";
        String orderBy = " ORDER BY name asc";
       if(name!= null && !name.equals("")){

            orderString1 = " and name like '%"+name.trim()+"%'";
        }

        if(code!= null && !code.equals("")){

            orderString2 = " and code like '%"+code.trim()+"%'";
        }

        String query = whereString  + orderString1 + orderString2 + orderBy ;
        return query;
    }
}
