package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by think on 2015/10/28.
 */
public class WorkOrder implements Serializable {
//    private static final String TAG = "WorkOrder";
//    private static final long serialVersionUID = 2015050105L;

    public String wonum;//工单号
    public String status;//状态
    public String statusdate;//状态日期
    public String worktype;//工单类型
    public String wtypedesc;//工单类型名称
    public String description;//描述
    public String assetnum;//设备
    public String assetdescription;//设备描述
    public String woeq3;//设备管理班组编号
    public String woeq2;//设备管理室编号
    public String woeq1;//设备管理组编号
    public String udisjj;//是否紧急维修
    public String udisaq;//是否安全
    public String udisbx;//是否保修
    public String udiscb;//是否抄表
    public String udcreateby;//创建人
    public String udcreatebyname;//创建人名称
    public String udcreatedate;//创建日期
    public String jpnum;//作业计划
    public String pmnum;//预防性维护
    public String reportdate;//汇报日期
    public String reportedby;//报告人
    public String udyxj;//优先级
    public String lead;//工作负责人/指派人
    public String targstartdate;//计划开始时间
    public String targcompdate;//计划完成时间
    public String udactstart;//实际开始时间
    public String udactfinish;//实际完成时间
//    public String udisplayname;//承包商负责人
    public String udtjsj;//实际维修时间
    public String udtjtime;//停机时间
    public String udremark;//备注
    public String udisjf;//是否按项目计费
    public String udprojapprnum;//立项编号
    public String udbugnum;//项目预算
    public String udassetbz;//财务公司
    public String udevnum;//事故编码
    public String supervisor;//抢修执行人
    public String udsupervisor2;//抢修执行人2
    public String udqxbz;//抢修班组
    public String udworkmemo;//工作备注
    public String udisyq;//是否跟进
    public String failurecode;//故障子机构
    public String udgzlbdm;//故障类别
//    public String ldispayname;//工作负责人
    public String gzgdwonum;//抢修工单管理故障工单编号
    public String gzgddescription;//抢修工单管理故障工单描述

    public String qxgdwonum;//故障工单管理抢修工单编号
    public String qxgddescription;//故障工单管理抢修工单描述





    public boolean isnew;//是否是新增工单




//    @Override
//    public void parse(JSONObject jsonObject) throws JSONException {
//        wonum = jsonObject.getString("wonum");
//        status = jsonObject.getString("status");
//        statusdate = jsonObject.getString("statusdate");
//        worktype = jsonObject.getString("worktype");
//        description = jsonObject.getString("description");
//        assetnum = jsonObject.getString("assetnum");
////        assetdescription = jsonObject.getString("assetdescription");
//        udisaq = jsonObject.getString("udisaq");
//        udisbx = jsonObject.getString("udisbx");
//        udiscb = jsonObject.getString("udiscb");
//        udisjf = jsonObject.getString("udisjf");
//        udisjj = jsonObject.getString("udisjj");
////        udisplayname = jsonObject.getString("udisplayname");
//        udremark = jsonObject.getString("udremark");
//        udtjsj = jsonObject.getString("udtjsj");
//        actstart = jsonObject.getString("actstart");
//        actfinish = jsonObject.getString("actfinish");
//        woeq3 = jsonObject.getString("woeq3");
//        woeq2 = jsonObject.getString("woeq2");
//        woeq1 = jsonObject.getString("woeq1");
//        jpnum = jsonObject.getString("jpnum");
////        ldispayname = jsonObject.getString("ldispayname");
//        udcreateby = jsonObject.getString("udcreateby");
//        udcreatedate = jsonObject.getString("udcreatedate");
//        reportdate = jsonObject.getString("reportdate");
//        reportedby = jsonObject.getString("reportedby");
//        lead = jsonObject.getString("lead");
//        targstartdate = jsonObject.getString("targstartdate");
//        targcompdate = jsonObject.getString("targcompdate");
//    }
//
//    public WorkOrder() {
//    }
//
//
//    private WorkOrder(Parcel in) {
//        wonum = in.readString();
//        status = in.readString();
//        statusdate = in.readString();
//        worktype = in.readString();
//        description = in.readString();
//        assetnum = in.readString();
////        assetdescription = in.readString();
//        udisaq = in.readString();
//        udisbx = in.readString();
//        udiscb = in.readString();
//        udisjf = in.readString();
//        udisjj = in.readString();
////        udisplayname = in.readString();
//        udremark = in.readString();
//        udtjsj = in.readString();
//        actstart = in.readString();
//        actfinish = in.readString();
//        woeq3 = in.readString();
//        woeq2 = in.readString();
//        woeq1 = in.readString();
//        jpnum = in.readString();
////        ldispayname = in.readString();
//        udcreateby = in.readString();
//        udcreatedate = in.readString();
//        reportdate = in.readString();
//        reportedby = in.readString();
//        lead = in.readString();
//        targstartdate = in.readString();
//        targcompdate = in.readString();
//    }
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(wonum);
//        dest.writeString(status);
//        dest.writeString(statusdate);
//        dest.writeString(worktype);
//        dest.writeString(description);
//        dest.writeString(assetnum);
////        dest.writeString(assetdescription);
//        dest.writeString(udisaq);
//        dest.writeString(udisbx);
//        dest.writeString(udiscb);
//        dest.writeString(udisjf);
//        dest.writeString(udisjj);
////        dest.writeString(udisplayname);
//        dest.writeString(udremark);
//        dest.writeString(udtjsj);
//        dest.writeString(actstart);
//        dest.writeString(actfinish);
//        dest.writeString(woeq3);
//        dest.writeString(woeq2);
//        dest.writeString(woeq1);
//        dest.writeString(jpnum);
////        dest.writeString(ldispayname);
//        dest.writeString(udcreateby);
//        dest.writeString(udcreatedate);
//        dest.writeString(reportdate);
//        dest.writeString(reportedby);
//        dest.writeString(lead);
//        dest.writeString(targstartdate);
//        dest.writeString(targcompdate);
//    }
//
//    public static final Creator<WorkOrder> CREATOR = new Creator<WorkOrder>() {
//        @Override
//        public WorkOrder createFromParcel(Parcel source) {
//            return new WorkOrder(source);
//        }
//
//        @Override
//        public WorkOrder[] newArray(int size) {
//            return new WorkOrder[size];
//        }
//    };
}
