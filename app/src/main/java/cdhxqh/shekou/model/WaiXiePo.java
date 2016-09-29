package cdhxqh.shekou.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 外协服务采购订单
 */
public class WaiXiePo extends Entity {
    private static final String TAG = "WaiXiePo";
    private static final long serialVersionUID = 2015050105L;


    public String POID; //唯一ID
    public String PONUM; //订单编号
    public String DESCRIPTION; //订单描述
    public String UDASSETNUM; //设备编码
    public String ADESCRIPTION; //设备描述
    public String VENDOR; //供应商
    public String VENDORNAME; //供应商名称
    public String CONTRACTREFNUM; //合同编号
    public String CDESCRIPTION; //合同描述
    public String UDHSZJ; //含税总价
    public String STATUS; //状态
    public String JBR; //经办人
    public String PZRNAME; //批准人
    public String STATUSDATE; //状态日期
    public String UDCREATEDATE; //制单日期
    public String UDAPPDATE; //批准日期
    public String UDREMARKS; //备注

    public String getPOID() {
        return POID;
    }

    public void setPOID(String POID) {
        this.POID = POID;
    }

    public String getPONUM() {
        return PONUM;
    }

    public void setPONUM(String PONUM) {
        this.PONUM = PONUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getUDASSETNUM() {
        return UDASSETNUM;
    }

    public void setUDASSETNUM(String UDASSETNUM) {
        this.UDASSETNUM = UDASSETNUM;
    }

    public String getADESCRIPTION() {
        return ADESCRIPTION;
    }

    public void setADESCRIPTION(String ADESCRIPTION) {
        this.ADESCRIPTION = ADESCRIPTION;
    }

    public String getVENDOR() {
        return VENDOR;
    }

    public void setVENDOR(String VENDOR) {
        this.VENDOR = VENDOR;
    }

    public String getVENDORNAME() {
        return VENDORNAME;
    }

    public void setVENDORNAME(String VENDORNAME) {
        this.VENDORNAME = VENDORNAME;
    }

    public String getCONTRACTREFNUM() {
        return CONTRACTREFNUM;
    }

    public void setCONTRACTREFNUM(String CONTRACTREFNUM) {
        this.CONTRACTREFNUM = CONTRACTREFNUM;
    }

    public String getCDESCRIPTION() {
        return CDESCRIPTION;
    }

    public void setCDESCRIPTION(String CDESCRIPTION) {
        this.CDESCRIPTION = CDESCRIPTION;
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

    public String getJBR() {
        return JBR;
    }

    public void setJBR(String JBR) {
        this.JBR = JBR;
    }

    public String getPZRNAME() {
        return PZRNAME;
    }

    public void setPZRNAME(String PZRNAME) {
        this.PZRNAME = PZRNAME;
    }

    public String getSTATUSDATE() {
        return STATUSDATE;
    }

    public void setSTATUSDATE(String STATUSDATE) {
        this.STATUSDATE = STATUSDATE;
    }

    public String getUDCREATEDATE() {
        return UDCREATEDATE;
    }

    public void setUDCREATEDATE(String UDCREATEDATE) {
        this.UDCREATEDATE = UDCREATEDATE;
    }

    public String getUDAPPDATE() {
        return UDAPPDATE;
    }

    public void setUDAPPDATE(String UDAPPDATE) {
        this.UDAPPDATE = UDAPPDATE;
    }

    public String getUDREMARKS() {
        return UDREMARKS;
    }

    public void setUDREMARKS(String UDREMARKS) {
        this.UDREMARKS = UDREMARKS;
    }

    @Override
    public void parse(JSONObject jsonObject) throws JSONException {

    }
}
