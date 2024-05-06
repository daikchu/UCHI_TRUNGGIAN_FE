package com.vn.osp.modelview;

import com.vn.osp.common.util.TimeUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 12/29/2016.
 */
public class AnnouncementList {

    public static final String SESSION_KEY = "AnnouncemeneList";

    private List<Announcement> announcementInternalList;
    private int internalListNumber;
    private int internalTotalPage;

    private List<Announcement> announcementPublicList;
    private int publicListNumber;
    private int publicTotalPage;

    private List<NotaryOffice> notaryOffices;

    private List<User> userList;

    private int page;

    private String senderInfoFilter;
    private int titleFilter;
    private int importansFilter;
    private int userEntryFilter;
    private int dateEntryFilter;
    private String authenticationFilter;
    /*tab*/
    private int defaultTabOpen;
    private int internalPage;
    private int publicPage;

    /*date*/

    private String timeType;
    private String toDate;
    private String fromDate;

    private String notaryDateFromFilter;
    private String notaryDateToFilter;


    private String announcementTitleFilter;

    public AnnouncementList() {
    }


    public List<Announcement> getAnnouncementInternalList() {
        return announcementInternalList;
    }

    public void setAnnouncementInternalList(List<Announcement> announcementInternalList) {
        this.announcementInternalList = announcementInternalList;
    }

    public List<Announcement> getAnnouncementPublicList() {
        return announcementPublicList;
    }

    public void setAnnouncementPublicList(List<Announcement> announcementPublicList) {
        this.announcementPublicList = announcementPublicList;
    }
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


    public List<NotaryOffice> getNotaryOffices() {
        return notaryOffices;
    }

    public void setNotaryOffices(List<NotaryOffice> notaryOffices) {
        this.notaryOffices = notaryOffices;
    }



    public int getInternalListNumber() {
        return internalListNumber;
    }

    public void setInternalListNumber(int internalListNumber) {
        this.internalListNumber = internalListNumber;
    }

    public int getInternalTotalPage() {
        return internalTotalPage;
    }

    public void setInternalTotalPage(int internalTotalPage) {
        this.internalTotalPage = internalTotalPage;
    }

    public int getPublicListNumber() {
        return publicListNumber;
    }

    public void setPublicListNumber(int publicListNumber) {
        this.publicListNumber = publicListNumber;
    }

    public int getPublicTotalPage() {
        return publicTotalPage;
    }

    public void setPublicTotalPage(int publicTotalPage) {
        this.publicTotalPage = publicTotalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTitleFilter() {
        return titleFilter;
    }

    public void setTitleFilter(int titleFilter) {
        this.titleFilter = titleFilter;
    }

    public int getImportansFilter() {
        return importansFilter;
    }

    public void setImportansFilter(int importansFilter) {
        this.importansFilter = importansFilter;
    }

    public int getUserEntryFilter() {
        return userEntryFilter;
    }

    public void setUserEntryFilter(int userEntryFilter) {
        this.userEntryFilter = userEntryFilter;
    }

    public int getDateEntryFilter() {
        return dateEntryFilter;
    }

    public void setDateEntryFilter(int dateEntryFilter) {
        this.dateEntryFilter = dateEntryFilter;
    }

    public String getAnnouncementTitleFilter() {
        return announcementTitleFilter;
    }

    public int getDefaultTabOpen() {
        return defaultTabOpen;
    }

    public void setDefaultTabOpen(int defaultTabOpen) {
        this.defaultTabOpen = defaultTabOpen;
    }

    public int getInternalPage() {
        return internalPage;
    }

    public void setInternalPage(int internalPage) {
        this.internalPage = internalPage;
    }

    public int getPublicPage() {
        return publicPage;
    }

    public void setPublicPage(int publicPage) {
        this.publicPage = publicPage;
    }



    public void setAnnouncementTitleFilter(String announcementTitleFilter) {
        this.announcementTitleFilter = announcementTitleFilter;
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

    public String getSenderInfoFilter() {
        return senderInfoFilter;
    }

    public void setSenderInfoFilter(String senderInfoFilter) {
        this.senderInfoFilter = senderInfoFilter;
    }


    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getToDate(){return toDate; }

    public void setToDate(String toDate){ this.toDate = toDate;}

    public String getFromDate(){return fromDate; }

    public void setFromDate(String fromDate){ this.fromDate = fromDate;}

    public String getAuthenticationFilter(){return authenticationFilter; }

    public void setAuthenticationFilter(String authenticationFilter){ this.authenticationFilter = authenticationFilter;}



    public String getOrderString1(){
        String whereString ="  where npo_announcement.announcement_type = 0 " ;
        String senderString = "";
        String importantString ="";
        String senderDate ="";
        String authenticationString="";
        if (announcementTitleFilter != null && !announcementTitleFilter.equals("")) {
            whereString += " and title like '%" + announcementTitleFilter + "%' ";
        }
        if (senderInfoFilter != null && !senderInfoFilter.equals("")) {
            senderString += " and sender_info like '%" + senderInfoFilter + "%'";
        }
        if (importansFilter == 1) importantString += " and importance_type = 1";
        if (importansFilter == 2) importantString += "and importance_type = 2";


        if (timeType != null && !timeType.equals("")) {
            senderDate = " and  npo_announcement.send_date_time >= '" + notaryDateFromFilter + "' and npo_announcement.send_date_time <= '" + notaryDateToFilter + "'  ";
        }


        String query = whereString + senderString + importantString + senderDate  ;
        return query;
    }

    public String getOrderString2(){
        String whereString =" INNER JOIN npo_announcement_receipt on  npo_announcement_receipt.data_id = npo_announcement.aid where ( npo_announcement.announcement_type = 1 or npo_announcement.announcement_type = 2 ) " ;
        String senderString = "";
        String importantString ="";
        String senderDate ="";
        String authenticationString ="";
        String groupBy = " GROUP BY npo_announcement.aid";
        if (announcementTitleFilter != null && !announcementTitleFilter.equals("")) {
            whereString += " and npo_announcement.title like '%" + announcementTitleFilter + "%' ";
        }
        if (senderInfoFilter != null && !senderInfoFilter.equals("")) {
            senderString += " and npo_announcement.sender_info like '%" + senderInfoFilter + "%'";
        }
        if (authenticationFilter != null && !authenticationFilter.equals("")) {
            authenticationString += " and npo_announcement_receipt.authentication_id like '%" + authenticationFilter + "%'";
        }
        if (importansFilter == 1) importantString += " and npo_announcement.importance_type = 1";
        if (importansFilter == 2) importantString += "and npo_announcement.importance_type = 2";

        if (timeType != null && !timeType.equals("")) {
            senderDate = " and  npo_announcement.send_date_time >= '" + notaryDateFromFilter + "' and npo_announcement.send_date_time <= '" + notaryDateToFilter + "'  ";
        }

        String query = whereString + authenticationString+ senderString + importantString + senderDate  + groupBy ;
        return query;
    }
    public void createFromToDate(){
        if(this.timeType == null || this.timeType.equals("")){
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

}
