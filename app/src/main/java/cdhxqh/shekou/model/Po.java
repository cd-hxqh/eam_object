package cdhxqh.shekou.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 非年度采购订单
 */
public class Po extends Entity {
    private static final String TAG = "Po";
    private static final long serialVersionUID = 2015050105L;


    public String PONUM; //订单编号
    public String POID; //唯一ID
    public String DESCRIPTION; //描述
    public String UDMS; //订单描述
    public String CWGS; //财务公司
    public String STATUS; //状态
    public String STATUSDATE; //状态日期
    public String CURRENCYCODE; //货币
    public String UDHSZJ; //含税总价
    public String UDTAX; //税率
    public String JYGYS; //公司
    public String FYFS; //发运方式
    public String JGJZ; //价格基准
    public String FKFS; //付款方式
    public String JBR; //制单人
    public String CBBM; //呈报部门
    public String CBRQ; //呈报日期
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

    public String getUDMS() {
        return UDMS;
    }

    public void setUDMS(String UDMS) {
        this.UDMS = UDMS;
    }

    public String getCWGS() {
        return CWGS;
    }

    public void setCWGS(String CWGS) {
        this.CWGS = CWGS;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSTATUSDATE() {
        return STATUSDATE;
    }

    public void setSTATUSDATE(String STATUSDATE) {
        this.STATUSDATE = STATUSDATE;
    }

    public String getCURRENCYCODE() {
        return CURRENCYCODE;
    }

    public void setCURRENCYCODE(String CURRENCYCODE) {
        this.CURRENCYCODE = CURRENCYCODE;
    }

    public String getUDHSZJ() {
        return UDHSZJ;
    }

    public void setUDHSZJ(String UDHSZJ) {
        this.UDHSZJ = UDHSZJ;
    }

    public String getUDTAX() {
        return UDTAX;
    }

    public void setUDTAX(String UDTAX) {
        this.UDTAX = UDTAX;
    }

    public String getJYGYS() {
        return JYGYS;
    }

    public void setJYGYS(String JYGYS) {
        this.JYGYS = JYGYS;
    }

    public String getFYFS() {
        return FYFS;
    }

    public void setFYFS(String FYFS) {
        this.FYFS = FYFS;
    }

    public String getJGJZ() {
        return JGJZ;
    }

    public void setJGJZ(String JGJZ) {
        this.JGJZ = JGJZ;
    }

    public String getFKFS() {
        return FKFS;
    }

    public void setFKFS(String FKFS) {
        this.FKFS = FKFS;
    }

    public String getJBR() {
        return JBR;
    }

    public void setJBR(String JBR) {
        this.JBR = JBR;
    }

    public String getCBBM() {
        return CBBM;
    }

    public void setCBBM(String CBBM) {
        this.CBBM = CBBM;
    }

    public String getCBRQ() {
        return CBRQ;
    }

    public void setCBRQ(String CBRQ) {
        this.CBRQ = CBRQ;
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
