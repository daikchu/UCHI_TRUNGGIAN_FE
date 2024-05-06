package com.vn.osp.modelview;

import com.vn.osp.common.util.TimeUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 3/3/2017.
 */
public class ReportByUser {


    private List<TransactionProperty> reportByUser;
    private int total;
    private int page;
    private int totalPage;

    List<NotaryOfficeList> notaryOfficeList;

    List<EntryUserName> entryUserName;

    private String notaryDateFromFilter;
    private String notaryDateToFilter;
    private String notary_office_name;
    private String entry_user_name;

    private String timeType;
    private String toDate;
    private String fromDate;
    public ReportByUser() {
    }
    public List<NotaryOfficeList> getNotaryOfficeList() {
        return notaryOfficeList;
    }

    public void setNotaryOfficeList(List<NotaryOfficeList> notaryOfficeList) {
        this.notaryOfficeList= notaryOfficeList;
    }

    public List<EntryUserName> getEntryUserName() {
        return entryUserName;
    }

    public void setEntryUserName(List<EntryUserName> entryUserName) {
        this.entryUserName= entryUserName;
    }


    public List<TransactionProperty> getReportByUser() {
        return reportByUser;
    }

    public void setReportByUser(List<TransactionProperty> reportByUser) {
        this.reportByUser = reportByUser;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }




    public String getNotaryDateFromFilter() {
        return notaryDateFromFilter;
    }

    public void setNotaryDateFromFilter(String notaryDateFromFilter) {
        this.notaryDateFromFilter = notaryDateFromFilter;
    }

    public String getNotaryDateToFilter() {
        return notaryDateToFilter;
    }

    public void setNotaryDateToFilter(String notaryDateToFilter) {
        this.notaryDateToFilter = notaryDateToFilter;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }
    public void createFromToDate(){
        if(this.timeType == null || this.timeType.equals("") ){
            this.timeType = "03";
        }
        if(timeType.equals("01")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())).toString();

            fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())));
            toDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())));
        } else if(timeType.equals("02")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())).toString();

            fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())));
        } else if(timeType.equals("03")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())).toString();

            fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())));
        } else if(timeType.equals("04")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())).toString();

            fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())));
        } else if(timeType.equals("05")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,fromDate).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,toDate).toString();;
        }
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

    public String getNotary_office_name(){return notary_office_name; }

    public void setNotary_office_name(String notary_office_name){ this.notary_office_name= notary_office_name;}

    public String getEntry_user_name(){return entry_user_name; }

    public void setEntry_user_name(String entry_user_name){ this.entry_user_name= entry_user_name;}


    public String getToDate(){return toDate; }

    public void setToDate(String toDate){ this.toDate = toDate;}

    public String getFromDate(){return fromDate; }

    public void setFromDate(String fromDate){ this.fromDate = fromDate;}

}
