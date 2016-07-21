package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 待办任务表
 */
public class Wfassignment extends Entity implements Parcelable {
    private static final String TAG = "Wfassignment";
    private static final long serialVersionUID = 2015050105L;

    public int wfassignmentid; //wfassignmentid
    public String app; //应用程序
    public String assigncode; //已分配任务的人员代码
    public String assigncodedesc; //任务分配人描述
    public String assignstatus; //任务分配状态
    public String description; //描述
    public String ownerid; //所有者标识
    public String ownertable; //所有者表
    public String processname; //过程
    public String startdate; //开始日期
    public String udassign01; //应用程序名称
    public String udassign02; //发起人



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        wfassignmentid = jsonObject.getInt("wfassignmentid");
        app = jsonObject.getString("app");
        assigncode = jsonObject.getString("assigncode");
        assigncodedesc = jsonObject.getString("assigncodedesc");
        assignstatus = jsonObject.getString("assignstatus");
        description = jsonObject.getString("description");
        ownerid = jsonObject.getString("ownerid");
        ownertable = jsonObject.getString("ownertable");
        processname = jsonObject.getString("processname");
        startdate = jsonObject.getString("startdate");
        udassign01 = jsonObject.getString("udassign01");
        udassign02 = jsonObject.getString("udassign02");
    }

    public Wfassignment() {
    }


    private Wfassignment(Parcel in) {
        wfassignmentid = in.readInt();
        app = in.readString();
        assigncode = in.readString();
        assigncodedesc = in.readString();
        assignstatus = in.readString();
        description = in.readString();
        ownerid = in.readString();
        ownertable = in.readString();
        processname = in.readString();
        startdate = in.readString();
        udassign01 = in.readString();
        udassign02 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(wfassignmentid);
        dest.writeString(app);
        dest.writeString(assigncode);
        dest.writeString(assigncodedesc);
        dest.writeString(assignstatus);
        dest.writeString(description);
        dest.writeString(ownerid);
        dest.writeString(ownertable);
        dest.writeString(processname);
        dest.writeString(startdate);
        dest.writeString(udassign01);
        dest.writeString(udassign02);

    }


    public static final Creator<Wfassignment> CREATOR = new Creator<Wfassignment>() {
        @Override
        public Wfassignment createFromParcel(Parcel source) {
            return new Wfassignment(source);
        }

        @Override
        public Wfassignment[] newArray(int size) {
            return new Wfassignment[size];
        }
    };

}
