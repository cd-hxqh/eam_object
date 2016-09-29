package cdhxqh.shekou.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 外协服务付款申请主表
 */
public class InvoiceLine extends Entity {
    private static final String TAG = "InvoiceLine";
    private static final long serialVersionUID = 2015050105L;


    public String DESCRIPTION; //付款明细描述
    public String INVOICELINENUM; //行
    public String INVOICENUM; //付款申请编号
    public String INVWONUM; //申请单号
    public String INDMS; //申请单描述
    public String PODES; //订单描述
    public String POLINENUM; //订单行号
    public String PONUM; //采购订单
    public String UDTAXUNITCOST; //含税单价
    public String UDTOTALCOST; //含税总价

    public String getINDMS() {
        return INDMS;
    }

    public void setINDMS(String INDMS) {
        this.INDMS = INDMS;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getINVOICELINENUM() {
        return INVOICELINENUM;
    }

    public void setINVOICELINENUM(String INVOICELINENUM) {
        this.INVOICELINENUM = INVOICELINENUM;
    }

    public String getINVOICENUM() {
        return INVOICENUM;
    }

    public void setINVOICENUM(String INVOICENUM) {
        this.INVOICENUM = INVOICENUM;
    }

    public String getINVWONUM() {
        return INVWONUM;
    }

    public void setINVWONUM(String INVWONUM) {
        this.INVWONUM = INVWONUM;
    }

    public String getPODES() {
        return PODES;
    }

    public void setPODES(String PODES) {
        this.PODES = PODES;
    }

    public String getPOLINENUM() {
        return POLINENUM;
    }

    public void setPOLINENUM(String POLINENUM) {
        this.POLINENUM = POLINENUM;
    }

    public String getPONUM() {
        return PONUM;
    }

    public void setPONUM(String PONUM) {
        this.PONUM = PONUM;
    }

    public String getUDTAXUNITCOST() {
        return UDTAXUNITCOST;
    }

    public void setUDTAXUNITCOST(String UDTAXUNITCOST) {
        this.UDTAXUNITCOST = UDTAXUNITCOST;
    }

    public String getUDTOTALCOST() {
        return UDTOTALCOST;
    }

    public void setUDTOTALCOST(String UDTOTALCOST) {
        this.UDTOTALCOST = UDTOTALCOST;
    }

    @Override
    public void parse(JSONObject jsonObject) throws JSONException {

    }
}
