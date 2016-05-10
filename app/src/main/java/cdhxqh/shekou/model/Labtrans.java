package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by think on 2015/11/3.
 * 计划员工
 */
public class Labtrans implements Serializable {
    private static final String TAG = "Labtrans";
    private static final long serialVersionUID = 2015050105L;

    public String actualstaskid; //任务
    public String craft; //工种
    public String skilllevel; //技能级别
    public String laborcode; //员工
    public String startdate; //开始日期
    public String starttime; //开始时间
//    public String finishdate; //结束日期
    public String finishtime; //结束时间
    public String regularhrs;//常规时数
//    public String enterby;//输入人
//    public String enterdate;//输入日期
    public String payrate;//费率
    public String linecost;//行成本
    public String assetnum;//资产
//    public String transdate;//交易日期
    public String transtype;//类型
//    public String orgid; //组织
//    public String siteid; //地点
    public String labtransid;

//
//
//    @Override
//    public void parse(JSONObject jsonObject) throws JSONException {
//        actualstaskid = jsonObject.getString("actualstaskid");
//        craft = jsonObject.getString("craft");
//        skilllevel = jsonObject.getString("skilllevel");
//        laborcode = jsonObject.getString("laborcode");
//        startdate = jsonObject.getString("startdate");
//        starttime = jsonObject.getString("starttime");
//        finishdate = jsonObject.getString("finishdate");
//        finishtime = jsonObject.getString("finishtime");
//        regularhrs = jsonObject.getString("regularhrs");
//        enterby = jsonObject.getString("enterby");
//        enterdate = jsonObject.getString("enterdate");
//        payrate = jsonObject.getString("payrate");
//        linecost = jsonObject.getString("linecost");
//        assetnum = jsonObject.getString("assetnum");
//        transdate = jsonObject.getString("transdate");
//        transtype = jsonObject.getString("transtype");
//        orgid = jsonObject.getString("orgid");
//        siteid = jsonObject.getString("siteid");
//        labtransid = jsonObject.getString("labtransid");
//    }
//
//    public Labtrans() {
//    }
//
//
//    private Labtrans(Parcel in) {
//        actualstaskid = in.readString();
//        craft = in.readString();
//        skilllevel = in.readString();
//        laborcode = in.readString();
//        startdate = in.readString();
//        starttime = in.readString();
//        finishdate = in.readString();
//        finishtime = in.readString();
//        regularhrs = in.readString();
//        enterby = in.readString();
//        enterdate = in.readString();
//        payrate = in.readString();
//        linecost = in.readString();
//        assetnum = in.readString();
//        transdate = in.readString();
//        transtype = in.readString();
//        orgid = in.readString();
//        siteid = in.readString();
//        labtransid = in.readString();
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(actualstaskid);
//        dest.writeString(craft);
//        dest.writeString(skilllevel);
//        dest.writeString(laborcode);
//        dest.writeString(startdate);
//        dest.writeString(starttime);
//        dest.writeString(finishdate);
//        dest.writeString(finishtime);
//        dest.writeString(regularhrs);
//        dest.writeString(enterby);
//        dest.writeString(enterdate);
//        dest.writeString(payrate);
//        dest.writeString(linecost);
//        dest.writeString(assetnum);
//        dest.writeString(transdate);
//        dest.writeString(transtype);
//        dest.writeString(orgid);
//        dest.writeString(siteid);
//        dest.writeString(labtransid);
//    }
//
//
//    public static final Creator<Labtrans> CREATOR = new Creator<Labtrans>() {
//        @Override
//        public Labtrans createFromParcel(Parcel source) {
//            return new Labtrans(source);
//        }
//
//        @Override
//        public Labtrans[] newArray(int size) {
//            return new Labtrans[size];
//        }
//    };

}
