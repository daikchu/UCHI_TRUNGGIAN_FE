package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by minh on 3/28/2017.
 */
public class ManualList {
    private List<Manual> manualList;

    private int total;
    private int page;
    private int totalPage;

    private String title;
    private String entry_date_time;
    private String updảte_date_time;
    private String file_name;


    public List<Manual> getManualList() {
        return manualList;
    }

    public void setManualList(List<Manual> manualList) {
        this.manualList = manualList;
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

    public String getTitle(){return title; }

    public void setTitle(String title){ this.title = title;}

    public String getFile_name(){return file_name; }

    public void setFile_name(String file_name){ this.file_name = file_name;}


    public String getEntry_date_time(){return entry_date_time;}

    public void setEntry_date_time(String entry_date_time){this.entry_date_time=entry_date_time;}

    public String getUpdảte_date_time(){return updảte_date_time; }

    public void setUpdảte_date_time(String updảte_date_time){ this.updảte_date_time = updảte_date_time;}

    public String getOrderString(){
        String whereString ="where 1=1" ;
        String orderString ="";

        if(title !=null && !title.equals("")){
            orderString = " and title like '%"+title+"%' ";
        }

        String query = whereString + orderString ;
        return query;
    }

}
