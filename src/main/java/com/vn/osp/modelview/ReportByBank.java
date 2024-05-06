package com.vn.osp.modelview;

import org.json.JSONObject;

/**
 * Created by tranv on 12/28/2016.
 */
public class ReportByBank {
    private String notaryDateFromFilter;
    private String notaryDateToFilter;
    private String bank_name;

    public ReportByBank() {
    }

    public ReportByBank(String bank_name, String notaryDateFromFilter, String notaryDateToFilter) {
        this.bank_name = bank_name;
        this.notaryDateFromFilter = notaryDateFromFilter;
        this.notaryDateToFilter = notaryDateToFilter;
    }
    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
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

    public JSONObject generateJsonObject(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("bankname",bank_name);
            obj.put("notaryDateFromFilter", notaryDateFromFilter);
            obj.put("notaryDateToFilter", notaryDateToFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
