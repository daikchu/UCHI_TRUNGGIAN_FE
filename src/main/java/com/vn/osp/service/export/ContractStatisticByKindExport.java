package com.vn.osp.service.export;

import com.vn.osp.service.report.ContractStatisticByKindReport;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletOutputStream;

/**
 * Created by tranv on 2/22/2017.
 */
public class ContractStatisticByKindExport {

    public static final String SUCCESS = "success";
    public static final String NOTARY_IN_DAY = "01";
    public static final String NOTARY_IN_WEAK = "02";
    public static final String NOTARY_IN_MONTH = "03";
    public static final String NOTARY_IN_YEAR = "04";
    public static final String NOTARY_IN_RANGE = "05";
    public static final String ORDER_FIELD_ORDER_NUMBER = "order_number";
    public static final String ORDER_FIELD_TEMPLATE_NAME = "templateName";
    public static final String FILE_NAME = "BaocaotheonhomHD.xls";
    public static final String NOTARY_PLACE = "1";


}
