package cdhxqh.shekou.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 物资采购申请
 */
public class PR extends Entity {
    private static final String TAG = "PR";
    private static final long serialVersionUID = 2015050105L;


    public String PRID; //唯一ID
    public String PRNUM; //申请单号
    public String UDFINCP; //财务公司
    public String DESCRIPTION; //申购描述
    public String REQUESTEDBY; //申请人
    public String SQRNAME; //申请人姓名
    public String ISSUEDATE; //申请日期
    public String REQUIREDDATE; //要求到货日期
    public String SQBM; //申请部门
    public String SQGLS; //申请管理室
    public String PRETAXTOTAL; //金额总计
    public String STATUS; //状态
    public String STATUSDATE; //状态日期
    public String ZDR; //制单人
    public String UDCREATEDATE; //制单日期
    public String ZDBM; //制单部门

    public String getPRID() {
        return PRID;
    }

    public void setPRID(String PRID) {
        this.PRID = PRID;
    }

    public String getPRNUM() {
        return PRNUM;
    }

    public void setPRNUM(String PRNUM) {
        this.PRNUM = PRNUM;
    }

    public String getUDFINCP() {
        return UDFINCP;
    }

    public void setUDFINCP(String UDFINCP) {
        this.UDFINCP = UDFINCP;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getREQUESTEDBY() {
        return REQUESTEDBY;
    }

    public void setREQUESTEDBY(String REQUESTEDBY) {
        this.REQUESTEDBY = REQUESTEDBY;
    }

    public String getSQRNAME() {
        return SQRNAME;
    }

    public void setSQRNAME(String SQRNAME) {
        this.SQRNAME = SQRNAME;
    }

    public String getISSUEDATE() {
        return ISSUEDATE;
    }

    public void setISSUEDATE(String ISSUEDATE) {
        this.ISSUEDATE = ISSUEDATE;
    }

    public String getREQUIREDDATE() {
        return REQUIREDDATE;
    }

    public void setREQUIREDDATE(String REQUIREDDATE) {
        this.REQUIREDDATE = REQUIREDDATE;
    }

    public String getSQBM() {
        return SQBM;
    }

    public void setSQBM(String SQBM) {
        this.SQBM = SQBM;
    }

    public String getSQGLS() {
        return SQGLS;
    }

    public void setSQGLS(String SQGLS) {
        this.SQGLS = SQGLS;
    }

    public String getPRETAXTOTAL() {
        return PRETAXTOTAL;
    }

    public void setPRETAXTOTAL(String PRETAXTOTAL) {
        this.PRETAXTOTAL = PRETAXTOTAL;
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

    public String getZDR() {
        return ZDR;
    }

    public void setZDR(String ZDR) {
        this.ZDR = ZDR;
    }

    public String getUDCREATEDATE() {
        return UDCREATEDATE;
    }

    public void setUDCREATEDATE(String UDCREATEDATE) {
        this.UDCREATEDATE = UDCREATEDATE;
    }

    public String getZDBM() {
        return ZDBM;
    }

    public void setZDBM(String ZDBM) {
        this.ZDBM = ZDBM;
    }

    @Override
    public void parse(JSONObject jsonObject) throws JSONException {

    }
}
