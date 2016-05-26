package cdhxqh.shekou.api;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cdhxqh.shekou.bean.LoginResults;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Alndomain;
import cdhxqh.shekou.model.Assets;
import cdhxqh.shekou.model.Assignment;
import cdhxqh.shekou.model.Failurelist;
import cdhxqh.shekou.model.Failurereport;
import cdhxqh.shekou.model.Invbalances;
import cdhxqh.shekou.model.Invcost;
import cdhxqh.shekou.model.Inventory;
import cdhxqh.shekou.model.Invuse;
import cdhxqh.shekou.model.Invuseline;
import cdhxqh.shekou.model.JobPlan;
import cdhxqh.shekou.model.Labor;
import cdhxqh.shekou.model.Laborcraftrate;
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Matrectrans;
import cdhxqh.shekou.model.Matusetrans;
import cdhxqh.shekou.model.Person;
import cdhxqh.shekou.model.Pm;
import cdhxqh.shekou.model.Projappr;
import cdhxqh.shekou.model.Udev;
import cdhxqh.shekou.model.Wfassignment;
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.model.Wpitem;
import cdhxqh.shekou.model.Wplabor;

/**
 * Json数据解析类
 */
public class JsonUtils {
    private static final String TAG = "JsonUtils";


    /**
     * 解析登录信息*
     */
    public static LoginResults parsingAuthStr(final Context cxt, String data) {
        Log.i(TAG, "data=" + data);
        LoginResults loginResults = new LoginResults();
        try {
            JSONObject json = new JSONObject(data);
            String errcode = json.getString("errcode");
            String errmsg = json.getString("errmsg");
            loginResults.setErrcode(errcode);
            loginResults.setErrmsg(errmsg);
            return loginResults;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分页解析返回的结果*
     */
    public static Results parsingResults(Context ctx, String data) {
        Log.i(TAG, "data=" + data);
        String result = null;
        Results results = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.GETDATASUCCESS)) {
                result = json.getString("result");
                JSONObject rJson = new JSONObject(result);
                String curpage = rJson.getString("curpage");
                String totalresult = rJson.getString("totalresult");
                String resultlist = rJson.getString("resultlist");
                String totalpage = rJson.getString("totalpage");
                String showcount = rJson.getString("showcount");
                results = new Results();
                results.setCurpage(Integer.valueOf(curpage));
                results.setTotalresult(totalresult);
                results.setResultlist(resultlist);
                results.setTotalpage(totalpage);
                results.setShowcount(Integer.valueOf(showcount));
            }

            return results;


        } catch (JSONException e) {
            e.printStackTrace();
            return results;
        }

    }

    /**
     * 不分页解析返回的结果*
     */
    public static Results parsingResults1(Context ctx, String data) {

        String result = null;
        Results results = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.GETDATASUCCESS)) {
                result = json.getString("result");
                Log.i(TAG, "result=" + result);
                results = new Results();
                results.setResultlist(result);
            }

            return results;


        } catch (JSONException e) {
            e.printStackTrace();
            return results;
        }

    }


    /**
     * 解析待办事项信息
     */
    public static ArrayList<Wfassignment> parsingWfassignment(Context ctx, String data) {
        Log.i(TAG, "Wfassignment data=" + data);
        ArrayList<Wfassignment> list = null;
        Wfassignment wfassignment = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Wfassignment>();
            for (int i = 0; i < jsonArray.length(); i++) {
                wfassignment = new Wfassignment();
                jsonObject = jsonArray.getJSONObject(i);
                wfassignment.wfassignmentid = jsonObject.getInt("WFASSIGNMENTID"); //wfassignmentid
                wfassignment.app = jsonObject.getString("APP"); //应用程序
                wfassignment.assigncode = jsonObject.getString("ASSIGNCODE"); //已分配任务的人员代码
                wfassignment.assigncodedesc = jsonObject.getString("ASSIGNCODEDESC"); //描述
                wfassignment.assignstatus = jsonObject.getString("ASSIGNSTATUS"); //任务分配状态
                wfassignment.description = jsonObject.getString("DESCRIPTION"); //描述
                wfassignment.ownerid = jsonObject.getString("OWNERID"); //所有者标识
                wfassignment.ownertable = jsonObject.getString("OWNERTABLE"); //所有者表
                wfassignment.processname = jsonObject.getString("PROCESSNAME"); //过程
                wfassignment.startdate = jsonObject.getString("STARTDATE"); //开始日期

                list.add(wfassignment);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
    /**
     * 解析工单信息
     */
    public static ArrayList<WorkOrder> parsingWorkOrder(Context ctx, String data,String type) {
        Log.i(TAG, "WorkOrder data=" + data);
        ArrayList<WorkOrder> list = null;
        WorkOrder workOrder = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<WorkOrder>();
            for (int i = 0; i < jsonArray.length(); i++) {
                workOrder = new WorkOrder();
                jsonObject = jsonArray.getJSONObject(i);
                workOrder.wonum = jsonObject.getString("WONUM"); //工单号
                workOrder.status = jsonObject.getString("STATUS");//状态
                workOrder.statusdate = jsonObject.getString("STATUSDATE");//状态日期
                workOrder.worktype = jsonObject.getString("WORKTYPE");//工单类型
                workOrder.description = jsonObject.getString("DESCRIPTION");//工单描述
                workOrder.assetnum = jsonObject.getString("ASSETNUM");//设备
                workOrder.woeq3 = jsonObject.getString("WOEQ3");//设备管理班组编号
                workOrder.woeq2 = jsonObject.getString("WOEQ2");//设备管理室编号
                workOrder.woeq1 = jsonObject.getString("WOEQ1");//设备管理组编号
                if (jsonObject.has("UDISJJ")) {
                    workOrder.udisjj = jsonObject.getString("UDISJJ");//是否紧急维修
                }
//                workOrder.assetdescription = jsonObject.getString("ASSETDESCRIPTION");//设备描述
                if (jsonObject.has("UDISAQ")) {
                    workOrder.udisaq = jsonObject.getString("UDISAQ");//是否安全
                }
                if (jsonObject.has("UDISBX")) {
                    workOrder.udisbx = jsonObject.getString("UDISBX");//是否保修
                }
                if (jsonObject.has("UDISCB")) {
                    workOrder.udiscb = jsonObject.getString("UDISCB");//是否抄表
                }
                if (jsonObject.has("UDCREATEBY")) {
                    workOrder.udcreateby = jsonObject.getString("UDCREATEBY");//创建人
                }
                if (jsonObject.has("UDCREATEDATE")) {
                    workOrder.udcreatedate = jsonObject.getString("UDCREATEDATE");//创建日期
                }
                if (jsonObject.has("JPNUM")) {
                    workOrder.jpnum = jsonObject.getString("JPNUM");//作业计划
                }
                if (jsonObject.has("PMNUM")) {
                    workOrder.pmnum = jsonObject.getString("PMNUM");//预防性维护
                }
                if (jsonObject.has("REPORTDATE")) {
                    workOrder.reportdate = jsonObject.getString("REPORTDATE");//汇报日期
                }
                if (jsonObject.has("REPORTEDBY")) {
                    workOrder.reportedby = jsonObject.getString("REPORTEDBY");//报告人
                }
                if (jsonObject.has("UDYXJ")) {
                    workOrder.udyxj = jsonObject.getString("UDYXJ");//优先级
                }
                if (jsonObject.has("LEAD")) {
                    workOrder.lead = jsonObject.getString("LEAD");//工作负责人/指派人
                }
                if (jsonObject.has("TARGSTARTDATE")) {
                    workOrder.targstartdate = jsonObject.getString("TARGSTARTDATE");//计划开始时间
                }
                if (jsonObject.has("TARGCOMPDATE")) {
                    workOrder.targcompdate = jsonObject.getString("TARGCOMPDATE");//计划完成时间
                }
                if (jsonObject.has("UDACTSTART")) {
                    workOrder.udactstart = jsonObject.getString("UDACTSTART");//实际开始时间
                }
                if (jsonObject.has("UDACTFINISH")) {
                    workOrder.udactfinish = jsonObject.getString("UDACTFINISH");//实际完成时间
                }
                if (jsonObject.has("UDTJSJ")) {
                    workOrder.udtjsj = jsonObject.getString("UDTJSJ");//实际维修时间
                }
                if (jsonObject.has("UDTJTIME")) {
                    workOrder.udtjtime = jsonObject.getString("UDTJTIME");//停机时间
                }
                if (jsonObject.has("UDREMARK")) {
                    workOrder.udremark = jsonObject.getString("UDREMARK");//备注
                }
                if (jsonObject.has("UDISJF")) {
                    workOrder.udisjf = jsonObject.getString("UDISJF");//是否按项目计费
                }
                if (jsonObject.has("UDPROJAPPRNUM")) {
                    workOrder.udprojapprnum = jsonObject.getString("UDPROJAPPRNUM");//立项编号
                }
                if (jsonObject.has("UDBUGNUM")) {
                    workOrder.udbugnum = jsonObject.getString("UDBUGNUM");//项目预算
                }
                if (jsonObject.has("UDASSETBZ")) {
                    workOrder.udassetbz = jsonObject.getString("UDASSETBZ");//财务公司
                }
                if (jsonObject.has("UDEVNUM")) {
                    workOrder.udevnum = jsonObject.getString("UDEVNUM");//事故编码
                }
                if (jsonObject.has("SUPERVISOR")) {
                    workOrder.supervisor = jsonObject.getString("SUPERVISOR");//抢修执行人
                }
                if (jsonObject.has("UDSUPERVISOR2")) {
                    workOrder.udsupervisor2 = jsonObject.getString("UDSUPERVISOR2");//抢修执行人2
                }
                if (jsonObject.has("UDQXBZ")) {
                    workOrder.udqxbz = jsonObject.getString("UDQXBZ");//抢修班组
                }
                if (jsonObject.has("UDWORKMEMO")) {
                    workOrder.udworkmemo = jsonObject.getString("UDWORKMEMO");//工作备注
                }
                if (jsonObject.has("UDISYQ")) {
                    workOrder.udisyq = jsonObject.getString("UDISYQ");//是否跟进
                }
                if (jsonObject.has("FAILURECODE")) {
                    workOrder.failurecode = jsonObject.getString("FAILURECODE");//故障子机构
                }
                if (jsonObject.has("UDGZLBDM")) {
                    workOrder.udgzlbdm = jsonObject.getString("UDGZLBDM");//故障类别
                }
                list.add(workOrder);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析工单计划任务信息
     */
    public static ArrayList<Woactivity> parsingWoactivity(Context ctx, String data) {
        Log.i(TAG, "Woactivity data=" + data);
        ArrayList<Woactivity> list = null;
        Woactivity woactivity = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Woactivity>();
            for (int i = 0; i < jsonArray.length(); i++) {
                woactivity = new Woactivity();
                jsonObject = jsonArray.getJSONObject(i);
                woactivity.taskid = jsonObject.getString("TASKID"); //任务
                woactivity.description = jsonObject.getString("DESCRIPTION");//描述
                woactivity.wojo1 = jsonObject.getString("WOJO1");//编号
                woactivity.wojo2 = jsonObject.getString("WOJO2");//需要安检
                woactivity.udisdo = jsonObject.getString("UDISDO");//是否完成
                woactivity.udisyq = jsonObject.getString("UDISYQ");//是否延期
                woactivity.udyqyy = jsonObject.getString("UDYQYY");//延期原因
                woactivity.udremark = jsonObject.getString("UDREMARK");//备注

//                woactivity.targstartdate = jsonObject.getString("TARGSTARTDATE");//计划开始时间
//                woactivity.targcompdate = jsonObject.getString("TARGCOMPDATE");//计划完成时间
//                woactivity.actstart = jsonObject.getString("ACTSTART");//时间开始时间
//                woactivity.actfinish = jsonObject.getString("ACTFINISH");//实际完成时间
//                woactivity.estdur = jsonObject.getString("ESTDUR");//持续时间
                list.add(woactivity);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析工单计划员工信息
     */
    public static ArrayList<Wplabor> parsingWplabor(Context ctx, String data) {
        Log.i(TAG, "Wplabor data=" + data);
        ArrayList<Wplabor> list = null;
        Wplabor wplabor = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Wplabor>();
            for (int i = 0; i < jsonArray.length(); i++) {
                wplabor = new Wplabor();
                jsonObject = jsonArray.getJSONObject(i);
                wplabor.craft = jsonObject.getString("CRAFT"); //工种
                wplabor.skilllevel = jsonObject.getString("SKILLLEVEL");//技能级别
                wplabor.laborcode = jsonObject.getString("LABORCODE");//员工
                wplabor.vendor = jsonObject.getString("VENDOR");//供应商
                wplabor.contractnum = jsonObject.getString("CONTRACTNUM");//员工合同
                wplabor.quantity = jsonObject.getString("QUANTITY");//数量
                wplabor.laborhrs = jsonObject.getString("LABORHRS");//常规时数
                wplabor.orgid = jsonObject.getString("ORGID");//组织
                wplabor.siteid = jsonObject.getString("SITEID");//地点
                wplabor.wplaborid = jsonObject.getString("WPLABORID");//
                list.add(wplabor);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析工单计划物料信息
     */
    public static ArrayList<Wpitem> parsingWpitem(Context ctx, String data) {
        Log.i(TAG, "Wpitem data=" + data);
        ArrayList<Wpitem> list = null;
        Wpitem wpitem = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Wpitem>();
            for (int i = 0; i < jsonArray.length(); i++) {
                wpitem = new Wpitem();
                jsonObject = jsonArray.getJSONObject(i);
                wpitem.taskid = jsonObject.getString("TASKID"); //任务
                wpitem.itemnum = jsonObject.getString("ITEMNUM");//项目
                wpitem.itemqty = jsonObject.getString("ITEMQTY");//项目数量
                wpitem.orderunit = jsonObject.getString("ORDERUNIT");//订购单位
                wpitem.unitcost = jsonObject.getString("UNITCOST");//单位成本
                wpitem.linecost = jsonObject.getString("LINECOST");//行成本
                wpitem.location = jsonObject.getString("LOCATION");//库房
                wpitem.storelocsite = jsonObject.getString("STORELOCSITE");//库房地点
                wpitem.requestnum = jsonObject.getString("REQUESTNUM");//请求
                wpitem.requiredate = jsonObject.getString("REQUIREDATE");//要求的日期
                wpitem.orgid = jsonObject.getString("ORGID");//组织标识
                wpitem.siteid = jsonObject.getString("SITEID");//地点
                list.add(wpitem);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析任务分配信息
     */
    public static ArrayList<Assignment> parsingAssignment(Context ctx, String data) {
        Log.i(TAG, "Wpitem data=" + data);
        ArrayList<Assignment> list = null;
        Assignment assignment = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Assignment>();
            for (int i = 0; i < jsonArray.length(); i++) {
                assignment = new Assignment();
                jsonObject = jsonArray.getJSONObject(i);
                assignment.taskid = jsonObject.getString("TASKID"); //任务
                assignment.laborcode = jsonObject.getString("LABORCODE");//员工
                assignment.craftcode = jsonObject.getString("CRAFTCODE");//工种
                assignment.skilllevel = jsonObject.getString("SKILLLEVEL");//技能级别
                assignment.contractnum = jsonObject.getString("CONTRACTNUM");//员工合同
                assignment.vendor = jsonObject.getString("VENDOR");//供应商
                assignment.scheduledate = jsonObject.getString("SCHEDULEDATE");//调度开始时间
                assignment.laborhrs = jsonObject.getString("LABORHRS");//时数
                assignment.status = jsonObject.getString("STATUS");//状态
                assignment.orgid = jsonObject.getString("ORGID");//组织
                assignment.siteid = jsonObject.getString("SITEID");//地点
                assignment.assignmentid = jsonObject.getString("ASSIGNMENTID");//
                list.add(assignment);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析故障汇报信息
     */
    public static ArrayList<Failurereport> parsingFailurereport(Context ctx, String data) {
        Log.i(TAG, "Failurereport data=" + data);
        ArrayList<Failurereport> list = null;
        Failurereport failurereport = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Failurereport>();
            for (int i = 0; i < jsonArray.length(); i++) {
                failurereport = new Failurereport();
                jsonObject = jsonArray.getJSONObject(i);
                failurereport.wonum = jsonObject.getString("WONUM"); //工单号
                failurereport.assetnum = jsonObject.getString("ASSETNUM"); //资产
                failurereport.failurecode = jsonObject.getString("FAILURECODE");//故障代码
                failurereport.linenum = jsonObject.getString("LINENUM");//行
                failurereport.type = jsonObject.getString("TYPE");//类型
                failurereport.orgid = jsonObject.getString("ORGID");//组织
                failurereport.siteid = jsonObject.getString("SITEID");//地点
//                failurereport.failurereportid = jsonObject.getString("FAILUREREPORTID");//供应商
                list.add(failurereport);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析实际员工信息
     */
    public static ArrayList<Labtrans> parsingLabtrans(Context ctx, String data) {
        Log.i(TAG, "Labtrans data=" + data);
        ArrayList<Labtrans> list = null;
        Labtrans labtrans = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Labtrans>();
            for (int i = 0; i < jsonArray.length(); i++) {
                labtrans = new Labtrans();
                jsonObject = jsonArray.getJSONObject(i);
                labtrans.actualstaskid = jsonObject.getString("ACTUALSTASKID"); //任务
                labtrans.craft = jsonObject.getString("CRAFT"); //任务
                labtrans.skilllevel = jsonObject.getString("SKILLLEVEL");//技能级别
                labtrans.laborcode = jsonObject.getString("LABORCODE");//员工
                labtrans.startdate = jsonObject.getString("STARTDATE");//工种
                labtrans.starttime = jsonObject.getString("STARTTIME");//工种
//                labtrans.finishdate = jsonObject.getString("FINISHDATE");//员工合同
                labtrans.finishtime = jsonObject.getString("FINISHTIME");//供应商
                labtrans.regularhrs = jsonObject.getString("REGULARHRS");//调度开始时间
//                labtrans.enterby = jsonObject.getString("ENTERBY");//时数
//                labtrans.enterdate = jsonObject.getString("ENTERDATE");//状态
                labtrans.payrate = jsonObject.getString("PAYRATE");//状态
                labtrans.linecost = jsonObject.getString("LINECOST");//状态
                labtrans.assetnum = jsonObject.getString("ASSETNUM");//状态
//                labtrans.transdate = jsonObject.getString("TRANSDATE");//状态
                labtrans.transtype = jsonObject.getString("TRANSTYPE");//状态
//                labtrans.orgid = jsonObject.getString("ORGID");//组织
//                labtrans.siteid = jsonObject.getString("SITEID");//地点
                labtrans.labtransid = jsonObject.getString("LABTRANSID");//
                list.add(labtrans);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析库存信息
     */
    public static ArrayList<Inventory> parsingInventory(Context ctx, String data) {
        Log.i(TAG, "Inventory data=" + data);
        ArrayList<Inventory> list = null;
        Inventory inventory = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Inventory>();
            for (int i = 0; i < jsonArray.length(); i++) {
                inventory = new Inventory();
                jsonObject = jsonArray.getJSONObject(i);
                inventory.avgcost = jsonObject.getString("AVGCOST"); //平均项目成本
                inventory.curbal = jsonObject.getString("CURBAL"); //当前余量
                inventory.issueunit = jsonObject.getString("ISSUEUNIT"); //发放单位
                inventory.itemnum = jsonObject.getString("ITEMNUM"); //项目编号
                inventory.lastcost = jsonObject.getString("LASTCOST"); //项目成本
                inventory.location = jsonObject.getString("LOCATION"); //库房
                inventory.orgid = jsonObject.getString("ORGID"); //组织标识
                inventory.siteid = jsonObject.getString("SITEID"); //站点
                inventory.stdcost = jsonObject.getString("STDCOST"); //项目成本

                list.add(inventory);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 解析库存成本
     */
    public static ArrayList<Invcost> parsingInvcost(Context ctx, String data) {
        Log.i(TAG, "Invcost data=" + data);
        ArrayList<Invcost> list = null;
        Invcost invcost = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Invcost>();
            for (int i = 0; i < jsonArray.length(); i++) {
                invcost = new Invcost();
                jsonObject = jsonArray.getJSONObject(i);
                invcost.avgcost = jsonObject.getString("AVGCOST"); //平均项目成本
                invcost.itemnum = jsonObject.getString("ITEMNUM"); //项目编号
                invcost.lastcost = jsonObject.getString("LASTCOST"); //项目成本
                invcost.location = jsonObject.getString("LOCATION"); //库房
                invcost.orgid = jsonObject.getString("ORGID"); //组织标识
                invcost.siteid = jsonObject.getString("SITEID"); //站点
                invcost.stdcost = jsonObject.getString("STDCOST"); //项目成本

                list.add(invcost);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析库存余量
     */
    public static ArrayList<Invbalances> parsingInvbalances(Context ctx, String data) {
        Log.i(TAG, "Invbalances data=" + data);
        ArrayList<Invbalances> list = null;
        Invbalances invbalances = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Invbalances>();
            for (int i = 0; i < jsonArray.length(); i++) {
                invbalances = new Invbalances();
                jsonObject = jsonArray.getJSONObject(i);
                invbalances.binnum = jsonObject.getString("BINNUM"); //货柜编号
                invbalances.curbal = jsonObject.getString("CURBAL"); //当前余量
                invbalances.itemnum = jsonObject.getString("ITEMNUM"); //项目编号
                invbalances.location = jsonObject.getString("LOCATION"); //库房
                invbalances.orgid = jsonObject.getString("ORGID"); //组织标识
                invbalances.physcnt = jsonObject.getString("PHYSCNT"); //实际库存量
                invbalances.physcntdate = jsonObject.getString("PHYSCNTDATE"); //盘点日期
                invbalances.siteid = jsonObject.getString("SITEID"); //位置
                invbalances.stagedcurbal = jsonObject.getString("STAGEDCURBAL"); //暂存余量

                list.add(invbalances);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析入库
     */
    public static ArrayList<Matusetrans> parsingMatusetrans(Context ctx, String data) {
        Log.i(TAG, "Matusetrans data=" + data);
        ArrayList<Matusetrans> list = null;
        Matusetrans matusetrans = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Matusetrans>();
            for (int i = 0; i < jsonArray.length(); i++) {
                matusetrans = new Matusetrans();
                jsonObject = jsonArray.getJSONObject(i);
                matusetrans.actualcost = jsonObject.getString("ACTUALCOST"); //实际成本
                matusetrans.actualdate = jsonObject.getString("ACTUALDATE"); //实际日期
                matusetrans.assetnum = jsonObject.getString("ASSETNUM"); //资产编号
                matusetrans.curbal = jsonObject.getString("CURBAL"); //当前余量
                matusetrans.enterby = jsonObject.getString("ENTERBY"); //输入人
                matusetrans.issuetype = jsonObject.getString("ISSUETYPE"); //交易类型
                matusetrans.itemnum = jsonObject.getString("ITEMNUM"); //资产
                matusetrans.linecost = jsonObject.getString("LINECOST"); //行成本
                matusetrans.location = jsonObject.getString("LOCATION"); //位置
                matusetrans.matusetransid = jsonObject.getString("MATUSETRANSID"); //唯一id
                matusetrans.orgid = jsonObject.getString("ORGID"); //组织标识
                matusetrans.physcnt = jsonObject.getString("PHYSCNT"); //实际盘点
                matusetrans.quantity = jsonObject.getString("QUANTITY"); //数量
                matusetrans.refwo = jsonObject.getString("REFWO"); //工单
                matusetrans.siteid = jsonObject.getString("SITEID"); //站点
                matusetrans.storeloc = jsonObject.getString("STORELOC"); //位置
                matusetrans.transdate = jsonObject.getString("TRANSDATE"); //交易日期
                matusetrans.unitcost = jsonObject.getString("UNITCOST"); //单位成本

                list.add(matusetrans);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析出库
     */
    public static ArrayList<Matrectrans> parsingMatrectrans(Context ctx, String data) {
        Log.i(TAG, "Matrectrans data=" + data);
        ArrayList<Matrectrans> list = null;
        Matrectrans matrectrans = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Matrectrans>();
            for (int i = 0; i < jsonArray.length(); i++) {
                matrectrans = new Matrectrans();
                jsonObject = jsonArray.getJSONObject(i);
                matrectrans.actualcost = jsonObject.getString("ACTUALCOST"); //实际成本
                matrectrans.actualdate = jsonObject.getString("ACTUALDATE"); //实际日期
                matrectrans.fromsiteid = jsonObject.getString("FROMSITEID"); //原地点
                matrectrans.fromstoreloc = jsonObject.getString("FROMSTORELOC"); //原位置
                matrectrans.issuetype = jsonObject.getString("ISSUETYPE"); //交易类型
                matrectrans.itemnum = jsonObject.getString("ITEMNUM"); //项目编号
                matrectrans.linecost = jsonObject.getString("LINECOST"); //行成本
                matrectrans.loadedcost = jsonObject.getString("LOADEDCOST"); //记入成本
                matrectrans.quantity = jsonObject.getString("QUANTITY"); //数量
                matrectrans.tostoreloc = jsonObject.getString("TOSTORELOC"); //目标位置
                matrectrans.transdate = jsonObject.getString("TRANSDATE"); //交易日期
                matrectrans.unitcost = jsonObject.getString("UNITCOST"); //单位成本

                list.add(matrectrans);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析领料单信息
     */
    public static ArrayList<Invuse> parsingInvuse(Context ctx, String data) {
        Log.i(TAG, "Invuse data=" + data);
        ArrayList<Invuse> list = null;
        Invuse invuse = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Invuse>();
            for (int i = 0; i < jsonArray.length(); i++) {
                invuse = new Invuse();
                jsonObject = jsonArray.getJSONObject(i);
                invuse.invuseid = jsonObject.getString("INVUSEID"); //唯一ID
                invuse.invusenum = jsonObject.getString("INVUSENUM"); //领料单号
                invuse.description = jsonObject.getString("DESCRIPTION"); //描述
                invuse.fromstoreloc = jsonObject.getString("FROMSTORELOC"); //库房
                invuse.loc_description = jsonObject.getString("LOC_DESCRIPTION"); //库房名称
                invuse.wonum = jsonObject.getString("WONUM"); //工单
                invuse.workorder_description = jsonObject.getString("WORKORDER_DESCRIPTION"); //工单描述  22
                invuse.udissueto = jsonObject.getString("UDISSUETO"); //领料人
                invuse.llr_displayname = jsonObject.getString("LLR_DISPLAYNAME"); //领料人名称
                invuse.uddept = jsonObject.getString("UDDEPT"); //部门  22
                invuse.uddept_description = jsonObject.getString("UDDEPT_DESCRIPTION"); //部门名称
                invuse.udjbr = jsonObject.getString("UDJBR"); //物管员经办人  22
                invuse.udjbr_displayname = jsonObject.getString("UDJBR_DISPLAYNAME"); //物管员经办人（中文）
                invuse.wonum_asset_assetnum = jsonObject.getString("WONUM_ASSET_ASSETNUM"); //工单设备
                invuse.eq1 = jsonObject.getString("EQ1"); //设备管理组
                invuse.eq2 = jsonObject.getString("EQ2"); //设备管理室
                invuse.eq3 = jsonObject.getString("EQ3"); //设备管理班组
                invuse.udisjj = jsonObject.getInt("UDISJJ")+""; //是否紧急

                invuse.status = jsonObject.getString("STATUS"); //状态
                invuse.siteid = jsonObject.getString("SITEID"); //地点
                invuse.totalcost_v = jsonObject.getString("TOTALCOST_V"); //总价   22
                invuse.sq_displayname = jsonObject.getString("SQ_DISPLAYNAME"); //申请人
                invuse.createdate = jsonObject.getString("CREATEDATE"); //申请日期
                invuse.pz_displayname = jsonObject.getString("PZ_DISPLAYNAME"); //批准人
                invuse.changedate = jsonObject.getString("CHANGEDATE"); //批准日期


                list.add(invuse);
            }

            return list;
        } catch (JSONException e) {

            Log.i(TAG,"this jsonexception");
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析领料单行信息
     */
    public static ArrayList<Invuseline> parsingInvuseline(Context ctx, String data) {
        Log.i(TAG, "Invuseline data=" + data);
        ArrayList<Invuseline> list = null;
        Invuseline invuseline = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Invuseline>();
            for (int i = 0; i < jsonArray.length(); i++) {
                invuseline = new Invuseline();
                jsonObject = jsonArray.getJSONObject(i);
                invuseline.invuselinenum = jsonObject.getString("INVUSELINENUM"); //编号
                invuseline.invuselineid = jsonObject.getString("INVUSELINEID"); //唯一ID
                invuseline.itemnum = jsonObject.getString("ITEMNUM"); //备件
                invuseline.invusenum = jsonObject.getString("INVUSENUM"); //领料单号
                invuseline.description = jsonObject.getString("DESCRIPTION"); //描述
                invuseline.level4 = jsonObject.getString("LEVEL4"); //机构
                invuseline.level5 = jsonObject.getString("LEVEL5"); //部位(五级)
                invuseline.level6 = jsonObject.getString("LEVEL6"); //部位(六级)
                invuseline.quantity = jsonObject.getString("QUANTITY"); //领料数量
                invuseline.frombin = jsonObject.getString("FROMBIN"); //货柜
                invuseline.invbalances_curbal = jsonObject.getString("INVBALANCES_CURBAL"); //货柜可用数量
                invuseline.inventory_curbaltotal = jsonObject.getString("INVENTORY_CURBALTOTAL"); //库存总数量
                invuseline.inventory_issueunit = jsonObject.getString("INVENTORY_ISSUEUNIT"); //发放单位
                invuseline.remark = jsonObject.getString("REMARK"); //备注
                invuseline.udbudctrlnum = jsonObject.getString("UDBUDCTRLNUM"); //预算编号
                invuseline.budctrl_description = jsonObject.getString("BUDCTRL_DESCRIPTION"); //预算描述
                invuseline.udglaccount = jsonObject.getString("UDGLACCOUNT"); //成本科目
                invuseline.chartofaccounts_accountname = jsonObject.getString("CHARTOFACCOUNTS_ACCOUNTNAME"); //成本科目描述
                invuseline.budctrl_udlimit = jsonObject.getString("BUDCTRL_UDLIMIT"); //预算总额(元)
                invuseline.budctrl_actual = jsonObject.getString("BUDCTRL_ACTUAL"); //实际金额(元)
                invuseline.budctrl_remainder = jsonObject.getString("BUDCTRL_REMAINDER"); //剩余金额(元)
                invuseline.displayunitcost = jsonObject.getString("DISPLAYUNITCOST"); //单位成本
                invuseline.displaylinecost = jsonObject.getString("DISPLAYLINECOST"); //行成本
                invuseline.wonum = jsonObject.getString("WONUM"); //工单号
                invuseline.taskid = jsonObject.getString("TASKID"); //任务号
                invuseline.assetnum = jsonObject.getString("ASSETNUM"); //设备
                invuseline.linetype = jsonObject.getString("LINETYPE"); //行类型
                invuseline.tositeid = jsonObject.getString("TOSITEID"); //目标地点
                invuseline.enterby_displayname = jsonObject.getString("ENTERBY_DISPLAYNAME"); //输入人
                invuseline.issueto = jsonObject.getString("ISSUETO"); //领料人
                invuseline.issueto_displayname = jsonObject.getString("ISSUETO_DISPLAYNAME"); //领料人


                list.add(invuseline);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 解析设备信息
     */
    public static ArrayList<Assets> parsingAsset(String data) {
        Log.i(TAG, "Assets data=" + data);
        ArrayList<Assets> list = null;
        Assets assets = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Assets>();
            for (int i = 0; i < jsonArray.length(); i++) {
                assets = new Assets();
                jsonObject = jsonArray.getJSONObject(i);
                assets.assetnum = jsonObject.getString("ASSETNUM"); //设备编码
                assets.description = jsonObject.getString("DESCRIPTION"); //设备名称
                assets.eq1 = jsonObject.getString("EQ1"); //管理组
                assets.eq2 = jsonObject.getString("EQ2"); //管理室
                assets.eq3 = jsonObject.getString("EQ3"); //管理班组
                assets.assettype = jsonObject.getString("ASSETTYPE"); //资产类型
                assets.udassettype = jsonObject.getString("UDASSETTYPE"); //设备类型
                assets.siteid = jsonObject.getString("SITEID"); //地点
                list.add(assets);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析设备信息
     */
    public static ArrayList<JobPlan> parsingJobplan(String data) {
        Log.i(TAG, "JobPlan data=" + data);
        ArrayList<JobPlan> list = null;
        JobPlan jobPlan = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<JobPlan>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jobPlan = new JobPlan();
                jsonObject = jsonArray.getJSONObject(i);
                jobPlan.jpnum = jsonObject.getString("JPNUM"); //设备编码
                jobPlan.description = jsonObject.getString("DESCRIPTION"); //设备名称
                jobPlan.templatetype = jsonObject.getString("TEMPLATETYPE"); //管理组
                jobPlan.udassettype = jsonObject.getString("UDASSETTYPE"); //管理室
                jobPlan.udassettype_description = jsonObject.getString("UDASSETTYPE_DESCRIPTION"); //管理班组
                jobPlan.udassettype1 = jsonObject.getString("UDASSETTYPE1"); //资产类型
                jobPlan.orgid = jsonObject.getString("ORGID"); //设备类型
                jobPlan.siteid = jsonObject.getString("SITEID"); //地点
                list.add(jobPlan);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析人员信息
     */
    public static ArrayList<Person> parsingPerson(String data) {
        Log.i(TAG, "Person data=" + data);
        ArrayList<Person> list = null;
        Person person = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Person>();
            for (int i = 0; i < jsonArray.length(); i++) {
                person = new Person();
                jsonObject = jsonArray.getJSONObject(i);
                person.personid = jsonObject.getString("PERSONID"); //人员
                person.displayname = jsonObject.getString("DISPLAYNAME"); //名称
                person.title = jsonObject.getString("TITLE"); //头衔
                person.department = jsonObject.getString("DEPARTMENT"); //部门
                person.location = jsonObject.getString("LOCATION"); //人员的位置
                person.locationsite = jsonObject.getString("LOCATIONSITE"); //人员的地点
                person.locationorg = jsonObject.getString("LOCATIONORG"); //组织
                list.add(person);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析员工信息
     */
    public static ArrayList<Labor> parsingLabor(String data) {
        Log.i(TAG, "Person data=" + data);
        ArrayList<Labor> list = null;
        Labor labor = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Labor>();
            for (int i = 0; i < jsonArray.length(); i++) {
                labor = new Labor();
                jsonObject = jsonArray.getJSONObject(i);
                labor.laborcode = jsonObject.getString("LABORCODE"); //员工
                labor.displayname = jsonObject.getString("DISPLAYNAME"); //名称
                labor.udisoutside = jsonObject.getString("UDISOUTSIDE"); //是否外部人员
                labor.craft = jsonObject.getString("CRAFT"); //工种
                labor.craft_description = jsonObject.getString("CRAFT_DESCRIPTION"); //描述
                labor.udeq1 = jsonObject.getString("UDEQ1"); //管理组
                labor.udeq2 = jsonObject.getString("UDEQ2"); //管理室
                labor.udeq3 = jsonObject.getString("UDEQ3"); //管理班组
                list.add(labor);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析抢修信息
     */
    public static ArrayList<Alndomain> parsingAlndomain(String data) {
        Log.i(TAG, "Alndomain data=" + data);
        ArrayList<Alndomain> list = null;
        Alndomain alndomain = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Alndomain>();
            for (int i = 0; i < jsonArray.length(); i++) {
                alndomain = new Alndomain();
                jsonObject = jsonArray.getJSONObject(i);
                alndomain.value = jsonObject.getString("VALUE"); //值
                alndomain.description = jsonObject.getString("DESCRIPTION"); //描述
                list.add(alndomain);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析事故信息
     */
    public static ArrayList<Udev> parsingUdev(String data) {
        Log.i(TAG, "Udev data=" + data);
        ArrayList<Udev> list = null;
        Udev alndomain = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Udev>();
            for (int i = 0; i < jsonArray.length(); i++) {
                alndomain = new Udev();
                jsonObject = jsonArray.getJSONObject(i);
                alndomain.evnum = jsonObject.getString("EVNUM"); //事故编码
                alndomain.description = jsonObject.getString("DESCRIPTION"); //描述
                alndomain.evtype = jsonObject.getString("EVTYPE"); //事故类型
                alndomain.evclass = jsonObject.getString("EVCLASS"); //事故分类
                alndomain.evgrade = jsonObject.getString("EVGRADE"); //事故等级
                list.add(alndomain);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析立项申报信息
     */
    public static ArrayList<Projappr> parsingProjappr(String data) {
        Log.i(TAG, "Projappr data=" + data);
        ArrayList<Projappr> list = null;
        Projappr projappr = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Projappr>();
            for (int i = 0; i < jsonArray.length(); i++) {
                projappr = new Projappr();
                jsonObject = jsonArray.getJSONObject(i);
                projappr.prjnum = jsonObject.getString("PRJNUM"); //项目编码
                projappr.projapprnum = jsonObject.getString("PROJAPPRNUM"); //立项编号
                projappr.bugnum = jsonObject.getString("BUGNUM"); //项目预算
                projappr.description = jsonObject.getString("DESCRIPTION"); //描述
                projappr.year = jsonObject.getString("YEAR"); //项目年份
                projappr.ysje = jsonObject.getString("YSJE"); //项目预算金额
                projappr.fzrqm = jsonObject.getString("FZRQM"); //负责人
                list.add(projappr);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析预防性维护信息
     */
    public static ArrayList<Pm> parsingPm(String data) {
        Log.i(TAG, "Pm data=" + data);
        ArrayList<Pm> list = null;
        Pm pm = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Pm>();
            for (int i = 0; i < jsonArray.length(); i++) {
                pm = new Pm();
                jsonObject = jsonArray.getJSONObject(i);
                pm.pmnum = jsonObject.getString("PMNUM"); //预防性维护
                pm.description = jsonObject.getString("DESCRIPTION"); //描述
                pm.assetnum = jsonObject.getString("ASSETNUM"); //设备
                pm.jpnum = jsonObject.getString("JPNUM"); //作业计划
                pm.status = jsonObject.getString("STATUS"); //状态
                pm.priority = jsonObject.getString("PRIORITY"); //优先级
                pm.siteid = jsonObject.getString("SITEID"); //地点
                list.add(pm);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析员工工种信息
     */
    public static ArrayList<Laborcraftrate> parsingLaborcraftrate(String data) {
        Log.i(TAG, "Laborcraftrate data=" + data);
        ArrayList<Laborcraftrate> list = null;
        Laborcraftrate laborcraftrate = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Laborcraftrate>();
            for (int i = 0; i < jsonArray.length(); i++) {
                laborcraftrate = new Laborcraftrate();
                jsonObject = jsonArray.getJSONObject(i);
                laborcraftrate.laborcode = jsonObject.getString("LABORCODE"); //员工
                laborcraftrate.displayname = jsonObject.getString("DISPLAYNAME"); //描述
                laborcraftrate.craft = jsonObject.getString("CRAFT"); //工种
                laborcraftrate.craft_description = jsonObject.getString("CRAFT_DESCRIPTION"); //
                laborcraftrate.locationsite = jsonObject.getString("LOCATIONSITE");
                list.add(laborcraftrate);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析故障列表信息
     */
    public static ArrayList<Failurelist> parsingFailurelist(String data) {
        Log.i(TAG, "Failurelist data=" + data);
        ArrayList<Failurelist> list = null;
        Failurelist failurelist = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Failurelist>();
            for (int i = 0; i < jsonArray.length(); i++) {
                failurelist = new Failurelist();
                jsonObject = jsonArray.getJSONObject(i);
                failurelist.failurecode = jsonObject.getString("FAILURECODE"); //故障代码
                failurelist.description = jsonObject.getString("DESCRIPTION"); //描述
                failurelist.parent = jsonObject.getString("PARENT"); //父级
                failurelist.type = jsonObject.getString("TYPE"); //类型
                failurelist.failurelist = jsonObject.getString("FAILURELIST");
                list.add(failurelist);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}