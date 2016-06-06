package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by think on 2015/11/4.
 * 计划任务
 */
public class Woactivity implements Serializable {
    private static final String TAG = "Woactivity";
    private static final long serialVersionUID = 2015050105L;

    public String workorderid;//唯一标识
    public String taskid; //任务
    public String description; //描述
    public String wojo1; //编号
    public String wojo2; //需要安检
    public String udisdo;//是否完成
    public String udisyq;//是否延期
    public String udyqyy;//延期原因
    public String udremark;//备注
    public String wonum;//所属工单
    public String optiontype;//
//    public String targstartdate; //计划开始时间
//    public String targcompdate; //计划完成时间
//    public String actstart; //实际开始时间
//    public String actfinish; //实际完成时间
//    public String estdur; //持续时间
//
//
//
//    @Override
//    public void parse(JSONObject jsonObject) throws JSONException {
//        taskid = jsonObject.getString("taskid");
//        description = jsonObject.getString("description");
//        wojo1 = jsonObject.getString("wojo1");
//        wojo2 = jsonObject.getString("wojo2");
//        targstartdate = jsonObject.getString("targstartdate");
//        targcompdate = jsonObject.getString("targcompdate");
//        actstart = jsonObject.getString("actstart");
//        actfinish = jsonObject.getString("actfinish");
//        estdur = jsonObject.getString("estdur");
//    }
//
//    public Woactivity() {
//    }
//
//
//    private Woactivity(Parcel in) {
//        taskid = in.readString();
//        description = in.readString();
//        wojo1 = in.readString();
//        wojo2 = in.readString();
//        targstartdate = in.readString();
//        targcompdate = in.readString();
//        actstart = in.readString();
//        actfinish = in.readString();
//        estdur = in.readString();
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(taskid);
//        dest.writeString(description);
//        dest.writeString(wojo1);
//        dest.writeString(wojo2);
//        dest.writeString(targstartdate);
//        dest.writeString(targcompdate);
//        dest.writeString(actstart);
//        dest.writeString(actfinish);
//        dest.writeString(estdur);
//    }
//
//
//    public static final Creator<Woactivity> CREATOR = new Creator<Woactivity>() {
//        @Override
//        public Woactivity createFromParcel(Parcel source) {
//            return new Woactivity(source);
//        }
//
//        @Override
//        public Woactivity[] newArray(int size) {
//            return new Woactivity[size];
//        }
//    };

}
