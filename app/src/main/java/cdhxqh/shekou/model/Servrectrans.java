package cdhxqh.shekou.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 服务接收验收
 */
public class Servrectrans extends Entity {
    private static final String TAG = "Servrectrans";
    private static final long serialVersionUID = 2015050105L;


    public String PONUM; //订单号
    public String POLINENUM; //订单行
    public String DESCRIPTION; //描述
    public String QTYTORECEIVE; //数量
    public String ISSUETYPE; //类型
    public String TRANSDATE; //接收日期
    public String JSR; //接收人
    public String WONUM; //申请单号
    public String STATUS; //检查状态


    public String getPONUM() {
        return PONUM;
    }

    public void setPONUM(String PONUM) {
        this.PONUM = PONUM;
    }

    public String getPOLINENUM() {
        return POLINENUM;
    }

    public void setPOLINENUM(String POLINENUM) {
        this.POLINENUM = POLINENUM;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getQTYTORECEIVE() {
        return QTYTORECEIVE;
    }

    public void setQTYTORECEIVE(String QTYTORECEIVE) {
        this.QTYTORECEIVE = QTYTORECEIVE;
    }

    public String getISSUETYPE() {
        return ISSUETYPE;
    }

    public void setISSUETYPE(String ISSUETYPE) {
        this.ISSUETYPE = ISSUETYPE;
    }

    public String getTRANSDATE() {
        return TRANSDATE;
    }

    public void setTRANSDATE(String TRANSDATE) {
        this.TRANSDATE = TRANSDATE;
    }

    public String getJSR() {
        return JSR;
    }

    public void setJSR(String JSR) {
        this.JSR = JSR;
    }

    public String getWONUM() {
        return WONUM;
    }

    public void setWONUM(String WONUM) {
        this.WONUM = WONUM;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    @Override
    public void parse(JSONObject jsonObject) throws JSONException {

    }
}
