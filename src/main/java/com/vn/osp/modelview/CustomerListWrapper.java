package com.vn.osp.modelview;

import com.vn.osp.common.util.TimeUtil;

import java.util.Date;
import java.util.List;

public class CustomerListWrapper {
    private List<Customer> customers;
    private int total;
    private int page;
    private int totalPage;
    private List<ProvinceList> provinceList;
    private String name;
    private Long active_flg;
    private String nameFilter;

    private String stringKey;
    private String searchTime;
    private String action_status;


    private String timeType;
    private String fromDate;
    private String toDate;
    private String printFromDate;
    private String printToDate;
    private String customerDateFromFilter;
    private String customerDateToFilter;
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
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

    public String getName(){return name; }

    public void setName(String name){ this.name = name;}

    public Long getActive_flg(){return active_flg;}

    public void setActive_flg(Long active_flg){this.active_flg=active_flg;}

    public String getNameFilter(){return nameFilter; }

    public void setNameFilter(String nameFilter){ this.nameFilter = nameFilter;}

    public String getStringKey() {
        return stringKey;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getPrintFromDate() {
        return printFromDate;
    }

    public void setPrintFromDate(String printFromDate) {
        this.printFromDate = printFromDate;
    }

    public String getPrintToDate() {
        return printToDate;
    }

    public void setPrintToDate(String printToDate) {
        this.printToDate = printToDate;
    }

    public String getCustomerDateFromFilter() {
        return customerDateFromFilter;
    }

    public void setCustomerDateFromFilter(String customerDateFromFilter) {
        this.customerDateFromFilter = customerDateFromFilter;
    }

    public String getCustomerDateToFilter() {
        return customerDateToFilter;
    }

    public void setCustomerDateToFilter(String customerDateToFilter) {
        this.customerDateToFilter = customerDateToFilter;
    }

    public List<ProvinceList> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceList> provinceList) {
        this.provinceList = provinceList;
    }

    public String getOrderString(){
        String whereString ="where 1 = 1 " ;

        if(nameFilter !=null && !nameFilter.equals("")){
            whereString  += " and name like '%"+nameFilter+"%' ";
        }
       if((customerDateFromFilter !=null && !customerDateToFilter.equals("")) && (customerDateToFilter !=null && !customerDateToFilter.equals(""))){
           whereString += " and date_exp >='"+customerDateFromFilter+"' and date_exp <='"+customerDateToFilter+"' ";
       }
       whereString+= " order by date_start desc";
        String query = whereString  ;
        return query;
    }

    public void createFromToDate() {
        if (this.timeType == null || this.timeType.equals("")) {
            this.timeType = "03";
        }
        if (timeType.equals("01")) {
            this.customerDateFromFilter = TimeUtil.toTimestamp(true, TimeUtil.getDay(new Date())).toString();
            this.customerDateToFilter = TimeUtil.toTimestamp(false, TimeUtil.getDay(new Date())).toString();
            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true, TimeUtil.getDay(new Date())));
            printToDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false, TimeUtil.getDay(new Date())));
        } else if (timeType.equals("02")) {
            this.customerDateFromFilter = TimeUtil.toTimestamp(true, TimeUtil.getDateByFirstDayOfWeek(new Date())).toString();
            this.customerDateToFilter = TimeUtil.toTimestamp(false, TimeUtil.getDateByLastDayOfWeek(new Date())).toString();
            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true, TimeUtil.getDateByFirstDayOfWeek(new Date())));
            printToDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false, TimeUtil.getDateByLastDayOfWeek(new Date())));
        } else if (timeType.equals("03")) {
            this.customerDateFromFilter = TimeUtil.toTimestamp(true, TimeUtil.getDateByFirstDayOfMonth(new Date())).toString();
            this.customerDateToFilter = TimeUtil.toTimestamp(false, TimeUtil.getDateByLastDayOfMonth(new Date())).toString();
            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true, TimeUtil.getDateByFirstDayOfMonth(new Date())));
            printToDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false, TimeUtil.getDateByLastDayOfMonth(new Date())));
        } else if (timeType.equals("04")) {
            this.customerDateFromFilter = TimeUtil.toTimestamp(true, TimeUtil.getDateByFirstDayOfYear(new Date())).toString();
            this.customerDateToFilter = TimeUtil.toTimestamp(false, TimeUtil.getDateByLastDayOfYear(new Date())).toString();
            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true, TimeUtil.getDateByFirstDayOfYear(new Date())));
            printToDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false, TimeUtil.getDateByLastDayOfYear(new Date())));
        } else if (timeType.equals("05")) {
            this.customerDateFromFilter = TimeUtil.toTimestamp(true, printFromDate).toString();
            this.customerDateToFilter = TimeUtil.toTimestamp(false, printToDate).toString();

        }
    }
}
