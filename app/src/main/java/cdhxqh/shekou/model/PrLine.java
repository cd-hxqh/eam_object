package cdhxqh.shekou.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 请购明细行
 */
public class PrLine extends Entity {
    private static final String TAG = "PrLine";
    private static final long serialVersionUID = 2015050105L;


    public String PRNUM; //申请单号
    public String PRLINENUM; //行号
    public String ITEMNUM; //设备编码
    public String DESCRIPTION; //描述
    public String ORDERQTY; //数量
    public String ORDERUNIT; //订购单位
    public String UNITCOST; //预估单价
    public String LINECOST; //预估总价
    public String UDJEDX; //大写金额
    public String UDYY; //申购原因
    public String REMARK; //备注
    public String REQUESTEDBY; //申请人
    public String REQDELIVERYDATE; //要求的日期
    public String UDZBQ; //要求质保期(天)
    public String UDSYSJ; //预计使用时间
    public String UDISXY; //协议采购？
    public String ISCSX; //超限？
    public String UDCANCLE; //取消？



    public String UDMANUFACTURER; //品牌/制造商
    public String PPMS; //品牌描述
    public String CHANNUM; //渠道编码
    public String QDMS; //渠道描述
    public String FPCGY; //分配采购员
    public String UDISFP; //是否已分配
    public String UDCONTRACTNUM; //年度合同编号
    public String UDISSC; //是否首次采购
    public String RFQNUM; //询价单号
    public String RFQUNITCOST; //单价
    public String RFQODERQTY; //数量
    public String RFQLINECOST; //行成本
    public String PONUM; //采购单号
    public String POORDERQTY; //数量
    public String POUNITCOST; //单位成本
    public String POLINECOST; //行成本


    public String getPRNUM() {
        return PRNUM;
    }

    public void setPRNUM(String PRNUM) {
        this.PRNUM = PRNUM;
    }

    public String getPRLINENUM() {
        return PRLINENUM;
    }

    public void setPRLINENUM(String PRLINENUM) {
        this.PRLINENUM = PRLINENUM;
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

    public String getUNITCOST() {
        return UNITCOST;
    }

    public void setUNITCOST(String UNITCOST) {
        this.UNITCOST = UNITCOST;
    }

    public String getLINECOST() {
        return LINECOST;
    }

    public void setLINECOST(String LINECOST) {
        this.LINECOST = LINECOST;
    }

    public String getUDJEDX() {
        return UDJEDX;
    }

    public void setUDJEDX(String UDJEDX) {
        this.UDJEDX = UDJEDX;
    }

    public String getUDYY() {
        return UDYY;
    }

    public void setUDYY(String UDYY) {
        this.UDYY = UDYY;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getREQUESTEDBY() {
        return REQUESTEDBY;
    }

    public void setREQUESTEDBY(String REQUESTEDBY) {
        this.REQUESTEDBY = REQUESTEDBY;
    }

    public String getREQDELIVERYDATE() {
        return REQDELIVERYDATE;
    }

    public void setREQDELIVERYDATE(String REQDELIVERYDATE) {
        this.REQDELIVERYDATE = REQDELIVERYDATE;
    }

    public String getUDZBQ() {
        return UDZBQ;
    }

    public void setUDZBQ(String UDZBQ) {
        this.UDZBQ = UDZBQ;
    }

    public String getUDSYSJ() {
        return UDSYSJ;
    }

    public void setUDSYSJ(String UDSYSJ) {
        this.UDSYSJ = UDSYSJ;
    }

    public String getUDISXY() {
        return UDISXY;
    }

    public void setUDISXY(String UDISXY) {
        this.UDISXY = UDISXY;
    }

    public String getISCSX() {
        return ISCSX;
    }

    public void setISCSX(String ISCSX) {
        this.ISCSX = ISCSX;
    }

    public String getUDCANCLE() {
        return UDCANCLE;
    }

    public void setUDCANCLE(String UDCANCLE) {
        this.UDCANCLE = UDCANCLE;
    }

    public String getUDMANUFACTURER() {
        return UDMANUFACTURER;
    }

    public void setUDMANUFACTURER(String UDMANUFACTURER) {
        this.UDMANUFACTURER = UDMANUFACTURER;
    }

    public String getPPMS() {
        return PPMS;
    }

    public void setPPMS(String PPMS) {
        this.PPMS = PPMS;
    }

    public String getCHANNUM() {
        return CHANNUM;
    }

    public void setCHANNUM(String CHANNUM) {
        this.CHANNUM = CHANNUM;
    }

    public String getQDMS() {
        return QDMS;
    }

    public void setQDMS(String QDMS) {
        this.QDMS = QDMS;
    }

    public String getFPCGY() {
        return FPCGY;
    }

    public void setFPCGY(String FPCGY) {
        this.FPCGY = FPCGY;
    }

    public String getUDISFP() {
        return UDISFP;
    }

    public void setUDISFP(String UDISFP) {
        this.UDISFP = UDISFP;
    }

    public String getUDCONTRACTNUM() {
        return UDCONTRACTNUM;
    }

    public void setUDCONTRACTNUM(String UDCONTRACTNUM) {
        this.UDCONTRACTNUM = UDCONTRACTNUM;
    }

    public String getUDISSC() {
        return UDISSC;
    }

    public void setUDISSC(String UDISSC) {
        this.UDISSC = UDISSC;
    }

    public String getRFQNUM() {
        return RFQNUM;
    }

    public void setRFQNUM(String RFQNUM) {
        this.RFQNUM = RFQNUM;
    }

    public String getRFQUNITCOST() {
        return RFQUNITCOST;
    }

    public void setRFQUNITCOST(String RFQUNITCOST) {
        this.RFQUNITCOST = RFQUNITCOST;
    }

    public String getRFQODERQTY() {
        return RFQODERQTY;
    }

    public void setRFQODERQTY(String RFQODERQTY) {
        this.RFQODERQTY = RFQODERQTY;
    }

    public String getRFQLINECOST() {
        return RFQLINECOST;
    }

    public void setRFQLINECOST(String RFQLINECOST) {
        this.RFQLINECOST = RFQLINECOST;
    }

    public String getPONUM() {
        return PONUM;
    }

    public void setPONUM(String PONUM) {
        this.PONUM = PONUM;
    }

    public String getPOUNITCOST() {
        return POUNITCOST;
    }

    public void setPOUNITCOST(String POUNITCOST) {
        this.POUNITCOST = POUNITCOST;
    }

    public String getPOORDERQTY() {
        return POORDERQTY;
    }

    public void setPOORDERQTY(String POORDERQTY) {
        this.POORDERQTY = POORDERQTY;
    }

    public String getPOLINECOST() {
        return POLINECOST;
    }

    public void setPOLINECOST(String POLINECOST) {
        this.POLINECOST = POLINECOST;
    }

    @Override
    public void parse(JSONObject jsonObject) throws JSONException {

    }


}
