package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by think on 2015/11/10.
 * 故障报告
 */
public class Failurereport implements Serializable {
    private static final String TAG = "Failurereport";
    private static final long serialVersionUID = 2015050105L;

    public String failurereportid;//唯一标识
    public String wonum; //工单号
    public String assetnum; //资产
    public String failurecode; //故障代码
    public String linenum; //行
    public String type;//类型
    public String orgid; //组织
    public String siteid; //地点
    public String parent;
    public String description;



//    @Override
//    public void parse(JSONObject jsonObject) throws JSONException {
//        wonum = jsonObject.getString("wonum");
//        assetnum = jsonObject.getString("assetnum");
//        failurecode = jsonObject.getString("failurecode");
//        linenum = jsonObject.getString("linenum");
//        type = jsonObject.getString("type");
//        orgid = jsonObject.getString("orgid");
//        siteid = jsonObject.getString("siteid");
//        failurereportid = jsonObject.getString("failurereportid");
//    }
//
//    public Failurereport() {
//    }
//
//
//    private Failurereport(Parcel in) {
//        wonum = in.readString();
//        assetnum = in.readString();
//        failurecode = in.readString();
//        linenum = in.readString();
//        type = in.readString();
//        orgid = in.readString();
//        siteid = in.readString();
//        failurereportid = in.readString();
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(wonum);
//        dest.writeString(assetnum);
//        dest.writeString(failurecode);
//        dest.writeString(linenum);
//        dest.writeString(type);
//        dest.writeString(orgid);
//        dest.writeString(siteid);
//        dest.writeString(failurereportid);
//    }
//
//
//    public static final Creator<Failurereport> CREATOR = new Creator<Failurereport>() {
//        @Override
//        public Failurereport createFromParcel(Parcel source) {
//            return new Failurereport(source);
//        }
//
//        @Override
//        public Failurereport[] newArray(int size) {
//            return new Failurereport[size];
//        }
//    };

}
