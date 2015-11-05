package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by think on 2015/10/28.
 */
public class WorkOrder extends Entity implements Parcelable {
    private static final String TAG = "WorkOrder";
    private static final long serialVersionUID = 2015050105L;

    public String wonum;//工单号
    public String status;//状态
    public String statusdate;//状态日期
    public String worktype;//工单类型
    public String description;//描述
    public String assetnum;//设备
    public String assetdescription;//设备描述
    public String udisaq;//是否安全
    public String udisbx;//是否保修
    public String udiscb;//是否抄表
    public String udisjf;//是否按项目计费
    public String udisjj;//是否紧急维修
    public String udisplayname;//承包商负责人
    public String udremark;//备注
    public String udtjsj;//停机时间
    public String actstart;//实际开始时间
    public String actfinish;//实际完成时间
    public String glbz;//设备管理班组编号
    public String gls;//设备管理室编号
    public String glz;//设备管理组编号
    public String jpnum;//作业计划
    public String ldispayname;//工作负责人
    public String reportdate;//汇报日期
    public String reportedby;//报告人
    public String sdisplayname;//工作执行人
    public String targstartdate;//计划开始时间
    public String targcompdate;//计划完成时间



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        wonum = jsonObject.getString("wonum");
        status = jsonObject.getString("status");
        statusdate = jsonObject.getString("statusdate");
        worktype = jsonObject.getString("worktype");
        description = jsonObject.getString("description");
        assetnum = jsonObject.getString("assetnum");
        assetdescription = jsonObject.getString("assetdescription");
        udisaq = jsonObject.getString("udisaq");
        udisbx = jsonObject.getString("udisbx");
        udiscb = jsonObject.getString("udiscb");
        udisjf = jsonObject.getString("udisjf");
        udisjj = jsonObject.getString("udisjj");
        udisplayname = jsonObject.getString("udisplayname");
        udremark = jsonObject.getString("udremark");
        udtjsj = jsonObject.getString("udtjsj");
        actstart = jsonObject.getString("actstart");
        actfinish = jsonObject.getString("actfinish");
        glbz = jsonObject.getString("glbz");
        gls = jsonObject.getString("gls");
        glz = jsonObject.getString("glz");
        jpnum = jsonObject.getString("jpnum");
        ldispayname = jsonObject.getString("ldispayname");
        reportdate = jsonObject.getString("reportdate");
        reportedby = jsonObject.getString("reportedby");
        sdisplayname = jsonObject.getString("sdisplayname");
        targstartdate = jsonObject.getString("targstartdate");
        targcompdate = jsonObject.getString("targcompdate");
    }

    public WorkOrder() {
    }


    private WorkOrder(Parcel in) {
        wonum = in.readString();
        status = in.readString();
        statusdate = in.readString();
        worktype = in.readString();
        description = in.readString();
        assetnum = in.readString();
        assetdescription = in.readString();
        udisaq = in.readString();
        udisbx = in.readString();
        udiscb = in.readString();
        udisjf = in.readString();
        udisjj = in.readString();
        udisplayname = in.readString();
        udremark = in.readString();
        udtjsj = in.readString();
        actstart = in.readString();
        actfinish = in.readString();
        glbz = in.readString();
        gls = in.readString();
        glz = in.readString();
        jpnum = in.readString();
        ldispayname = in.readString();
        reportdate = in.readString();
        reportedby = in.readString();
        sdisplayname = in.readString();
        targstartdate = in.readString();
        targcompdate = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(wonum);
        dest.writeString(status);
        dest.writeString(statusdate);
        dest.writeString(worktype);
        dest.writeString(description);
        dest.writeString(assetnum);
        dest.writeString(assetdescription);
        dest.writeString(udisaq);
        dest.writeString(udisbx);
        dest.writeString(udiscb);
        dest.writeString(udisjf);
        dest.writeString(udisjj);
        dest.writeString(udisplayname);
        dest.writeString(udremark);
        dest.writeString(udtjsj);
        dest.writeString(actstart);
        dest.writeString(actfinish);
        dest.writeString(glbz);
        dest.writeString(gls);
        dest.writeString(glz);
        dest.writeString(jpnum);
        dest.writeString(ldispayname);
        dest.writeString(reportdate);
        dest.writeString(reportedby);
        dest.writeString(sdisplayname);
        dest.writeString(targstartdate);
        dest.writeString(targcompdate);
    }

    public static final Creator<WorkOrder> CREATOR = new Creator<WorkOrder>() {
        @Override
        public WorkOrder createFromParcel(Parcel source) {
            return new WorkOrder(source);
        }

        @Override
        public WorkOrder[] newArray(int size) {
            return new WorkOrder[size];
        }
    };
}
