package com.vn.osp.modelview;


import java.math.BigInteger;

/**
 * Created by tranv on 12/28/2016.
 */

public class ReportByGroupTotal {
    private long kindId;
    private String kindName;
    private String contract_kind;
    private String templateName;
    private long number_of_contract;
    private long number_of_internal_contract;
    private long number_of_external_contract;

    public ReportByGroupTotal() {
    }

    public ReportByGroupTotal(long kindId, String kindName, String contract_kind, String templateName, long number_of_contract, long number_of_internal_contract, long number_of_external_contract) {
        this.kindId = kindId;
        this.kindName = kindName;
        this.contract_kind = contract_kind;
        this.templateName = templateName;
        this.number_of_contract = number_of_contract;
        this.number_of_internal_contract = number_of_internal_contract;
        this.number_of_external_contract = number_of_external_contract;
    }

    public long getKindId() {
        return kindId;
    }

    public void setKindId(long kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getContract_kind() {
        return contract_kind;
    }

    public void setContract_kind(String contract_kind) {
        this.contract_kind = contract_kind;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public long getNumber_of_contract() {
        return number_of_contract;
    }

    public void setNumber_of_contract(long number_of_contract) {
        this.number_of_contract = number_of_contract;
    }

    public long getNumber_of_internal_contract() {
        return number_of_internal_contract;
    }

    public void setNumber_of_internal_contract(long number_of_internal_contract) {
        this.number_of_internal_contract = number_of_internal_contract;
    }

    public long getNumber_of_external_contract() {
        return number_of_external_contract;
    }

    public void setNumber_of_external_contract(long number_of_external_contract) {
        this.number_of_external_contract = number_of_external_contract;
    }
}
