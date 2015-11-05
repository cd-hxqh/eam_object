package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by think on 2015/11/3.
 * 任务分配
 */
public class Assignment extends Entity implements Parcelable {
    private static final String TAG = "Assignment";
    private static final long serialVersionUID = 2015050105L;

    public String taskid; //任务
    public String laborcode; //员工
    public String craftcode; //工种
    public String skilllevel; //技能级别
    public String contractnum; //员工合同
    public String vendor; //供应商
    public String scheduledate; //调度开始时间
    public String laborhrs;//时数
    public String status;//状态
    public String orgid; //组织
    public String siteid; //地点
    public String assignmentid;



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        taskid = jsonObject.getString("taskid");
        laborcode = jsonObject.getString("laborcode");
        craftcode = jsonObject.getString("craftcode");
        skilllevel = jsonObject.getString("skilllevel");
        contractnum = jsonObject.getString("contractnum");
        vendor = jsonObject.getString("vendor");

        scheduledate = jsonObject.getString("scheduledate");
        laborhrs = jsonObject.getString("laborhrs");
        status = jsonObject.getString("status");
        orgid = jsonObject.getString("orgid");
        siteid = jsonObject.getString("siteid");
        assignmentid = jsonObject.getString("assignmentid");
    }

    public Assignment() {
    }


    private Assignment(Parcel in) {
        taskid = in.readString();
        laborcode = in.readString();
        craftcode = in.readString();
        skilllevel = in.readString();
        contractnum = in.readString();
        vendor = in.readString();

        scheduledate = in.readString();
        laborhrs = in.readString();
        status = in.readString();
        orgid = in.readString();
        siteid = in.readString();
        assignmentid = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(taskid);
        dest.writeString(laborcode);
        dest.writeString(craftcode);
        dest.writeString(skilllevel);
        dest.writeString(contractnum);
        dest.writeString(vendor);

        dest.writeString(scheduledate);
        dest.writeString(laborhrs);
        dest.writeString(status);
        dest.writeString(orgid);
        dest.writeString(siteid);
        dest.writeString(assignmentid);
    }


    public static final Creator<Assignment> CREATOR = new Creator<Assignment>() {
        @Override
        public Assignment createFromParcel(Parcel source) {
            return new Assignment(source);
        }

        @Override
        public Assignment[] newArray(int size) {
            return new Assignment[size];
        }
    };

}
