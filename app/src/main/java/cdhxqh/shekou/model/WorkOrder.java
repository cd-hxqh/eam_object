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

    public String workorderid;//工单唯一
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
    public String udbugnum;//项目预算/事故预算
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




}
