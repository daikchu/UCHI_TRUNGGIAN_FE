package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by minh on 4/19/2017.
 */
public class ProvinceList {
    private List<Province> listProvince;
    private int totalProvince;
    private int page;
    private int totalPage;
    private String action_status;
    private String titleFilter;
    private String name;
    private String code;




    public ProvinceList(List<Province> listProvince, int totalProvince, int page, int totalPage, String action_status, String titleFilter, String name, String code) {
        this.listProvince = listProvince;
        this.totalProvince = totalProvince;
        this.page = page;
        this.totalPage = totalPage;
        this.action_status = action_status;
        this.titleFilter = titleFilter;
        this.name = name;
        this.code = code;
    }

    public ProvinceList() {
    }



    public List<Province> getListProvince() {
        return listProvince;
    }

    public void setListProvince(List<Province> listProvince) {
        this.listProvince = listProvince;
    }

    public int getTotalProvince() {
        return totalProvince;
    }

    public void setTotalProvince(int totalProvince) {
        this.totalProvince = totalProvince;
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
