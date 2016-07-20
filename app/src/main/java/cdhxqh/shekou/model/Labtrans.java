package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by think on 2015/11/3.
 * 实际员工
 */
public class Labtrans implements Serializable {
    private static final String TAG = "Labtrans";
    private static final long serialVersionUID = 2015050105L;

    public String actualstaskid; //任务
    public String craft; //工种
    public String skilllevel; //技能级别
    public String laborcode; //员工
    public String startdate; //工作日期
    public String starttime; //开始时间
    public String finishtime; //结束时间
    public String regularhrs;//常规时数
    public String payrate;//费率
    public String linecost;//行成本
    public String assetnum;//资产
    public String transtype;//类型
    public String labtransid;
    public String refwo;//所属工单
    public String optiontype;


    public String getActualstaskid() {
        return actualstaskid;
    }

    public void setActualstaskid(String actualstaskid) {
        this.actualstaskid = actualstaskid;
    }

    public String getCraft() {
        return craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }

    public String getSkilllevel() {
        return skilllevel;
    }

    public void setSkilllevel(String skilllevel) {
        this.skilllevel = skilllevel;
    }

    public String getLaborcode() {
        return laborcode;
    }

    public void setLaborcode(String laborcode) {
        this.laborcode = laborcode;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public String getRegularhrs() {
        return regularhrs;
    }

    public void setRegularhrs(String regularhrs) {
        this.regularhrs = regularhrs;
    }

    public String getPayrate() {
        return payrate;
    }

    public void setPayrate(String payrate) {
        this.payrate = payrate;
    }

    public String getLinecost() {
        return linecost;
    }

    public void setLinecost(String linecost) {
        this.linecost = linecost;
    }

    public String getAssetnum() {
        return assetnum;
    }

    public void setAssetnum(String assetnum) {
        this.assetnum = assetnum;
    }

    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype;
    }

    public String getLabtransid() {
        return labtransid;
    }

    public void setLabtransid(String labtransid) {
        this.labtransid = labtransid;
    }

    public String getRefwo() {
        return refwo;
    }

    public void setRefwo(String refwo) {
        this.refwo = refwo;
    }

    public String getOptiontype() {
        return optiontype;
    }

    public void setOptiontype(String optiontype) {
        this.optiontype = optiontype;
    }
}
