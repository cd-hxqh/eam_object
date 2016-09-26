package cdhxqh.shekou.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 外协服务付款申请主表
 */
public class Invoice extends Entity {
    private static final String TAG = "Invoice";
    private static final long serialVersionUID = 2015050105L;


    public String INVOICEID; //唯一ID
    public String INVOICENUM; //付款单号
    public String UDFINCP; //财务公司
    public String UDJSFS; //结算方式
    public String VENDORINVOICENUM; //发票号
    public String DOCUMENTTYPE; //类型
    public String UDHSZJ; //含税总价
    public String STATUS; //状态
    public String SRR; //输入人
    public String UDREMARK; //申请理由
    public String PONUM; //订单号
    public String JS; //接收
    public String POCOST; //成本总计
    public String PRETAXTOTALFORUI; //税前总计
    public String TOTALTAX1FORUI; //税款总计
    public String CURRENCYCODE; //货币
    public String INVOICEDATE; //发票日期
    public String DUEDATE; //到期日
    public String PAIDDATE; //付款日期
    public String VENDOR; //供应商
    public String GYSMC; //供应商名称
    public String CONTACT; //联系人
    public String PHONE; //电话
    public String PAYMENTTERMS; //支付条款

    public String getINVOICEID() {
        return INVOICEID;
    }

    public void setINVOICEID(String INVOICEID) {
        this.INVOICEID = INVOICEID;
    }

    public String getINVOICENUM() {
        return INVOICENUM;
    }

    public void setINVOICENUM(String INVOICENUM) {
        this.INVOICENUM = INVOICENUM;
    }

    public String getUDFINCP() {
        return UDFINCP;
    }

    public void setUDFINCP(String UDFINCP) {
        this.UDFINCP = UDFINCP;
    }

    public String getUDJSFS() {
        return UDJSFS;
    }

    public void setUDJSFS(String UDJSFS) {
        this.UDJSFS = UDJSFS;
    }

    public String getVENDORINVOICENUM() {
        return VENDORINVOICENUM;
    }

    public void setVENDORINVOICENUM(String VENDORINVOICENUM) {
        this.VENDORINVOICENUM = VENDORINVOICENUM;
    }

    public String getDOCUMENTTYPE() {
        return DOCUMENTTYPE;
    }

    public void setDOCUMENTTYPE(String DOCUMENTTYPE) {
        this.DOCUMENTTYPE = DOCUMENTTYPE;
    }

    public String getUDHSZJ() {
        return UDHSZJ;
    }

    public void setUDHSZJ(String UDHSZJ) {
        this.UDHSZJ = UDHSZJ;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSRR() {
        return SRR;
    }

    public void setSRR(String SRR) {
        this.SRR = SRR;
    }

    public String getUDREMARK() {
        return UDREMARK;
    }

    public void setUDREMARK(String UDREMARK) {
        this.UDREMARK = UDREMARK;
    }

    public String getPONUM() {
        return PONUM;
    }

    public void setPONUM(String PONUM) {
        this.PONUM = PONUM;
    }

    public String getJS() {
        return JS;
    }

    public void setJS(String JS) {
        this.JS = JS;
    }

    public String getPOCOST() {
        return POCOST;
    }

    public void setPOCOST(String POCOST) {
        this.POCOST = POCOST;
    }

    public String getPRETAXTOTALFORUI() {
        return PRETAXTOTALFORUI;
    }

    public void setPRETAXTOTALFORUI(String PRETAXTOTALFORUI) {
        this.PRETAXTOTALFORUI = PRETAXTOTALFORUI;
    }

    public String getTOTALTAX1FORUI() {
        return TOTALTAX1FORUI;
    }

    public void setTOTALTAX1FORUI(String TOTALTAX1FORUI) {
        this.TOTALTAX1FORUI = TOTALTAX1FORUI;
    }

    public String getCURRENCYCODE() {
        return CURRENCYCODE;
    }

    public void setCURRENCYCODE(String CURRENCYCODE) {
        this.CURRENCYCODE = CURRENCYCODE;
    }

    public String getINVOICEDATE() {
        return INVOICEDATE;
    }

    public void setINVOICEDATE(String INVOICEDATE) {
        this.INVOICEDATE = INVOICEDATE;
    }

    public String getDUEDATE() {
        return DUEDATE;
    }

    public void setDUEDATE(String DUEDATE) {
        this.DUEDATE = DUEDATE;
    }

    public String getPAIDDATE() {
        return PAIDDATE;
    }

    public void setPAIDDATE(String PAIDDATE) {
        this.PAIDDATE = PAIDDATE;
    }

    public String getVENDOR() {
        return VENDOR;
    }

    public void setVENDOR(String VENDOR) {
        this.VENDOR = VENDOR;
    }

    public String getGYSMC() {
        return GYSMC;
    }

    public void setGYSMC(String GYSMC) {
        this.GYSMC = GYSMC;
    }

    public String getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getPAYMENTTERMS() {
        return PAYMENTTERMS;
    }

    public void setPAYMENTTERMS(String PAYMENTTERMS) {
        this.PAYMENTTERMS = PAYMENTTERMS;
    }

    @Override
    public void parse(JSONObject jsonObject) throws JSONException {

    }
}
