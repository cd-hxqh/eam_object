package cdhxqh.shekou.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 非年度采购订单行
 */
public class PoLine extends Entity {
    private static final String TAG = "PoLine";
    private static final long serialVersionUID = 2015050105L;


    public String POLINENUM; //序号
    public String PONUM; //采购单号
    public String ITEMNUM; //备件编码
    public String DESCRIPTION; //采购项
    public String ORDERQTY; //数量
    public String ORDERUNIT; //订购单位
    public String UDTAXUNITCOST; //含税单价
    public String UDTOTALCOST; //含税总价
    public String TAX1CODE; //税代码
    public String UDGHZQ; //交货期
    public String UDBZQ; //质保期
    public String SQR; //请购人
    public String QGBM; //请购部门
    public String PRNUM; //请购单
    public String SCCGD; //上次采购单
    public String SCCGDJ; //上次采购单价
    public String SQPZR; //请购批准人



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {

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

    public String getITEMNUM() {
        return ITEMNUM;
    }

    public void setITEMNUM(String ITEMNUM) {
        this.ITEMNUM = ITEMNUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getORDERQTY() {
        return ORDERQTY;
    }

    public void setORDERQTY(String ORDERQTY) {
        this.ORDERQTY = ORDERQTY;
    }

    public String getORDERUNIT() {
        return ORDERUNIT;
    }

    public void setORDERUNIT(String ORDERUNIT) {
        this.ORDERUNIT = ORDERUNIT;
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

    public String getTAX1CODE() {
        return TAX1CODE;
    }

    public void setTAX1CODE(String TAX1CODE) {
        this.TAX1CODE = TAX1CODE;
    }

    public String getUDGHZQ() {
        return UDGHZQ;
    }

    public void setUDGHZQ(String UDGHZQ) {
        this.UDGHZQ = UDGHZQ;
    }

    public String getUDBZQ() {
        return UDBZQ;
    }

    public void setUDBZQ(String UDBZQ) {
        this.UDBZQ = UDBZQ;
    }

    public String getSQR() {
        return SQR;
    }

    public void setSQR(String SQR) {
        this.SQR = SQR;
    }

    public String getQGBM() {
        return QGBM;
    }

    public void setQGBM(String QGBM) {
        this.QGBM = QGBM;
    }

    public String getPRNUM() {
        return PRNUM;
    }

    public void setPRNUM(String PRNUM) {
        this.PRNUM = PRNUM;
    }

    public String getSCCGD() {
        return SCCGD;
    }

    public void setSCCGD(String SCCGD) {
        this.SCCGD = SCCGD;
    }

    public String getSCCGDJ() {
        return SCCGDJ;
    }

    public void setSCCGDJ(String SCCGDJ) {
        this.SCCGDJ = SCCGDJ;
    }

    public String getSQPZR() {
        return SQPZR;
    }

    public void setSQPZR(String SQPZR) {
        this.SQPZR = SQPZR;
    }
}
