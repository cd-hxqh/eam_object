package cdhxqh.shekou.api;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import cdhxqh.shekou.bean.InvuseResult;
import cdhxqh.shekou.bean.LoginResults;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Alndomain;
import cdhxqh.shekou.model.Alndomain2;
import cdhxqh.shekou.model.Assets;
import cdhxqh.shekou.model.Assignment;
import cdhxqh.shekou.model.Failurelist;
import cdhxqh.shekou.model.Failurereport;
import cdhxqh.shekou.model.Invbalances;
import cdhxqh.shekou.model.Invcost;
import cdhxqh.shekou.model.Inventory;
import cdhxqh.shekou.model.Invoice;
import cdhxqh.shekou.model.InvoiceLine;
import cdhxqh.shekou.model.Invuse;
import cdhxqh.shekou.model.Invuseline;
import cdhxqh.shekou.model.Item;
import cdhxqh.shekou.model.JobPlan;
import cdhxqh.shekou.model.Labor;
import cdhxqh.shekou.model.Laborcraftrate;
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Locations;
import cdhxqh.shekou.model.Matrectrans;
import cdhxqh.shekou.model.Matusetrans;
import cdhxqh.shekou.model.PR;
import cdhxqh.shekou.model.Person;
import cdhxqh.shekou.model.Pm;
import cdhxqh.shekou.model.Po;
import cdhxqh.shekou.model.PoLine;
import cdhxqh.shekou.model.PrLine;
import cdhxqh.shekou.model.Projappr;
import cdhxqh.shekou.model.Servrectrans;
import cdhxqh.shekou.model.Udev;
import cdhxqh.shekou.model.WaiXiePo;
import cdhxqh.shekou.model.Wfassignment;
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.model.WorkOrderTem;
import cdhxqh.shekou.model.WorkResult;
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
            if (errcode.equals(Constants.LOGINSUCCESS) || errcode.equals(Constants.CHANGEIMEI)) {
                loginResults.setResult(json.getString("result"));
            }


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
                wfassignment.udassign01 = jsonObject.getString("UDASSIGN01"); //应用
                wfassignment.udassign02 = jsonObject.getString("UDASSIGN02"); //发起人

                list.add(wfassignment);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 启动工作流*
     */
    public static String parsingwfserviceResult(String data) {
        Log.i(TAG, "data=" + data);

        String result = null;
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("msg")) {
                result = object.getString("msg");
            } else {
                result = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 审批工作流*
     */
    public static String parsingwfserviceGoOnResult(String data) {
        Log.i(TAG, "data=" + data);
        String result = null;
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("status")) {
                result = object.getString("status");
            } else {
                result = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 解析工单信息
     */
    public static ArrayList<WorkOrder> parsingWorkOrder(Context ctx, String data, String type) {
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
                workOrder.workorderid = jsonObject.getInt("WORKORDERID") + ""; //唯一ID
                workOrder.wonum = jsonObject.getString("WONUM"); //工单号
                workOrder.status = jsonObject.getString("STATUS");//状态
                workOrder.statusdate = jsonObject.getString("STATUSDATE");//状态日期
                workOrder.worktype = jsonObject.getString("WORKTYPE");//工单类型
                workOrder.wtypedesc = jsonObject.getString("WTYPEDESC");//工单类型名称
                workOrder.description = jsonObject.getString("DESCRIPTION");//工单描述
                workOrder.assetnum = jsonObject.getString("ASSETNUM");//设备
                workOrder.woeq3 = jsonObject.getString("WOEQ3");//设备管理班组编号
                workOrder.woeq2 = jsonObject.getString("WOEQ2");//设备管理室编号
                workOrder.woeq1 = jsonObject.getString("WOEQ1");//设备管理组编号
                if (jsonObject.has("UDISJJ")) {
                    workOrder.udisjj = jsonObject.getString("UDISJJ");//是否紧急维修
                }
                workOrder.assetdescription = jsonObject.getString("ASSETDESCRIPTION");//设备描述
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
                if (jsonObject.has("UDCREATEBYNAME")) {
                    workOrder.udcreatebyname = jsonObject.getString("UDCREATEBYNAME");//创建人名称
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
                if (jsonObject.has("GZGDWONUM")) {
                    workOrder.gzgdwonum = jsonObject.getString("GZGDWONUM");//抢修工单中对应的故障工单编号
                }
                if (jsonObject.has("GZGDDESCRIPTION")) {
                    workOrder.gzgddescription = jsonObject.getString("GZGDDESCRIPTION");//抢修工单中对应的故障工单描述
                }
                if (jsonObject.has("QXGDWONUM")) {
                    workOrder.qxgdwonum = jsonObject.getString("QXGDWONUM");//故障工单中对应的抢修工单编号
                }
                if (jsonObject.has("QXGDDESCRIPTION")) {
                    workOrder.qxgddescription = jsonObject.getString("QXGDDESCRIPTION");//故障工单中对应的抢修工单描述
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
    public static ArrayList<Woactivity> parsingWoactivity(Context ctx, String data, String wonum) {
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
                woactivity.workorderid = jsonObject.getString("WORKORDERID");
                woactivity.taskid = jsonObject.getString("TASKID"); //任务
                woactivity.description = jsonObject.getString("DESCRIPTION");//描述
                woactivity.wojo1 = jsonObject.getString("WOJO1");//编号
                woactivity.wojo2 = jsonObject.getString("WOJO2");//需要安检
                woactivity.udisdo = jsonObject.getString("UDISDO");//是否完成
                woactivity.udisyq = jsonObject.getString("UDISYQ");//是否延期
                woactivity.udyqyy = jsonObject.getString("UDYQYY");//延期原因
                woactivity.udremark = jsonObject.getString("UDREMARK");//备注
                woactivity.wonum = wonum;
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
                failurereport.failurereportid = jsonObject.getString("FAILUREREPORTID");
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
    public static ArrayList<Labtrans> parsingLabtrans(Context ctx, String data, String wonum) {
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
                labtrans.labtransid = jsonObject.getString("LABTRANSID");//员工ID
                labtrans.refwo = wonum;
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
                inventory.inventoryid = jsonObject.getInt("INVENTORYID"); //唯一ID
                inventory.itemnum = jsonObject.getString("ITEMNUM"); //库存备件
                inventory.item_description = jsonObject.getString("ITEM_DESCRIPTION"); //备件描述
                inventory.location = jsonObject.getString("LOCATION"); //库房
                inventory.locations_description = jsonObject.getString("LOCATIONS_DESCRIPTION"); //库房名称
                inventory.issueunit = jsonObject.getString("ISSUEUNIT"); //单位
                inventory.udfincp = jsonObject.getString("UDFINCP"); //财务公司
                inventory.udfincp_name = jsonObject.getString("UDFINCP_NAME"); //财务公司（中文）
                inventory.status = jsonObject.getString("STATUS"); //状态
                inventory.binnum = jsonObject.getString("BINNUM"); //默认存放位置
                inventory.siteid = jsonObject.getString("SITEID"); //地点
                inventory.curbaltotal = jsonObject.getInt("CURBALTOTAL") + ""; //当前余量
                inventory.avblbalance = jsonObject.getString("AVBLBALANCE"); //可用量
                inventory.item_inspectionrequired = jsonObject.getString("ITEM_INSPECTIONREQUIRED"); //接收时检查
                inventory.item_udisreturn = jsonObject.getString("ITEM_UDISRETURN"); //是否退还旧件

                list.add(inventory);
            }

            return list;
        } catch (JSONException e) {
            Log.i(TAG, "this is ee");
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
                invcost.invcostid = jsonObject.getInt("INVCOSTID"); //唯一ID
                invcost.itemnum = jsonObject.getString("ITEMNUM"); //项目编号
                invcost.avgcost = jsonObject.getString("AVGCOST"); //平均项目成本
                invcost.lastcost = jsonObject.getString("LASTCOST"); //项目成本

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
                invbalances.invbalancesid = jsonObject.getInt("INVBALANCESID"); //唯一ID
                invbalances.itemnum = jsonObject.getString("ITEMNUM"); //项目编号
                invbalances.itemdescription = jsonObject.getString("ITEMDESCRIPTION"); //项目描述
                invbalances.binnum = jsonObject.getString("BINNUM"); //货柜编号
                invbalances.curbal = jsonObject.getString("CURBAL"); //当前余量
                invbalances.physcntdate = jsonObject.getString("PHYSCNTDATE"); //盘点日期
                invbalances.location = jsonObject.getString("LOCATION"); //库房

                list.add(invbalances);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 解析出库/物料
     */
    public static ArrayList<Matusetrans> parsingMatusetrans(Context ctx, String data) {
        ArrayList<Matusetrans> list = null;
        Matusetrans matusetrans = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Matusetrans>();
            for (int i = 0; i < jsonArray.length(); i++) {
                matusetrans = new Matusetrans();
                jsonObject = jsonArray.getJSONObject(i);
                matusetrans.actualstaskid = jsonObject.getString("ACTUALSTASKID"); //任务ID
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
                matusetrans.quantity = jsonObject.getString("QUANTITY").replace("-", ""); //数量
                matusetrans.refwo = jsonObject.getString("REFWO"); //工单
                matusetrans.siteid = jsonObject.getString("SITEID"); //站点
                matusetrans.storeloc = jsonObject.getString("STORELOC"); //位置
                matusetrans.transdate = jsonObject.getString("TRANSDATE"); //交易日期
                matusetrans.unitcost = jsonObject.getString("UNITCOST"); //单位成本
                matusetrans.description = jsonObject.getString("DESCRIPTION"); //描述
                matusetrans.linetype = jsonObject.getString("LINETYPE"); //行类型

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
                matrectrans.quantity = jsonObject.getString("QUANTITY").replace("-", ""); //数量
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
                invuse.udisjj = jsonObject.getInt("UDISJJ") + ""; //是否紧急

                invuse.status = jsonObject.getString("STATUS"); //状态
                invuse.statusdate = jsonObject.getString("STATUSDATE"); //状态日期
                invuse.siteid = jsonObject.getString("SITEID"); //地点
                invuse.totalcost_v = jsonObject.getString("TOTALCOST_V"); //总价   22
                invuse.sq_displayname = jsonObject.getString("SQ_DISPLAYNAME"); //申请人
                invuse.createdate = jsonObject.getString("CREATEDATE"); //申请日期
                invuse.pz_displayname = jsonObject.getString("PZ_DISPLAYNAME"); //批准人
                invuse.pz_date = jsonObject.getString("PZ_DATE"); //批准日期
                invuse.changedate = jsonObject.getString("CHANGEDATE"); //修改时间
                invuse.udapptype = jsonObject.getString("UDAPPTYPE"); //领料类型
                invuse.udreason = jsonObject.getString("UDREASON"); //原因


                list.add(invuse);
            }

            return list;
        } catch (JSONException e) {

            Log.i(TAG, "this jsonexception");
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
                invuseline.classstructureid = jsonObject.getString("CLASSSTRUCTUREID"); //部位
                invuseline.classstructure_description = jsonObject.getString("CLASSSTRUCTURE_DESCRIPTION"); //部位名称
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
                assets.ownersite = jsonObject.getString("OWNERSITE");//财务公司
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
     * 解析抢修信息
     */
    public static ArrayList<Alndomain2> parsingAlndomain2(String data) {
        Log.i(TAG, "Alndomain2 data=" + data);
        ArrayList<Alndomain2> list = null;
        Alndomain2 alndomain = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Alndomain2>();
            for (int i = 0; i < jsonArray.length(); i++) {
                alndomain = new Alndomain2();
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

    /**
     * 封装工单数据
     *
     * @param workOrder
     * @param woactivities
     * @param labtranses
     * @param failurereports
     * @return
     */
    public static String WorkToJson(WorkOrder workOrder, ArrayList<Woactivity> woactivities
            , ArrayList<Labtrans> labtranses, ArrayList<Failurereport> failurereports) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("wonum", workOrder.wonum);
            jsonObject.put("status", workOrder.status);
            jsonObject.put("statusdate", workOrder.statusdate);
            jsonObject.put("worktype", workOrder.worktype);
            jsonObject.put("description", workOrder.description);
            jsonObject.put("assetnum", workOrder.assetnum);
            jsonObject.put("woeq1", workOrder.woeq1);
            jsonObject.put("woeq2", workOrder.woeq2);
            jsonObject.put("woeq3", workOrder.woeq2);
            jsonObject.put("udisjj", workOrder.udisjj);
            jsonObject.put("udisaq", workOrder.udisaq);
            jsonObject.put("udiscb", workOrder.udiscb);
            jsonObject.put("udisbx", workOrder.udisbx);
            jsonObject.put("udcreateby", workOrder.udcreateby);
            jsonObject.put("udcreatedate", workOrder.udcreatedate);
            jsonObject.put("jpnum", workOrder.jpnum);
            jsonObject.put("pmnum", null == workOrder.pmnum ? "" : workOrder.pmnum);
            jsonObject.put("reportedby", workOrder.reportedby);
            jsonObject.put("reportdate", workOrder.reportdate);
            jsonObject.put("udyxj", workOrder.udyxj);
            jsonObject.put("lead", workOrder.lead);
            jsonObject.put("targcompdate", workOrder.targcompdate);
            jsonObject.put("targstartdate", workOrder.targstartdate);
            jsonObject.put("udactstart", workOrder.udactstart);
            jsonObject.put("udactfinish", workOrder.udactfinish);
            jsonObject.put("udtjsj", "");
            jsonObject.put("udtjtime", workOrder.udtjtime);
            jsonObject.put("udremark", workOrder.udremark);
            jsonObject.put("udprojapprnum", null == workOrder.udprojapprnum ? "" : workOrder.udprojapprnum);
            jsonObject.put("udbugnum", null == workOrder.udbugnum ? "" : workOrder.udbugnum);
            jsonObject.put("udisjf", null == workOrder.udisjf ? "" : workOrder.udisjf);
            jsonObject.put("udassetbz", null == workOrder.udassetbz ? "" : workOrder.udassetbz);
            jsonObject.put("udevnum", null == workOrder.udevnum ? "" : workOrder.udevnum);
            jsonObject.put("supervisor", null == workOrder.supervisor ? "" : workOrder.supervisor);
            jsonObject.put("udsupervisor2", null == workOrder.udsupervisor2 ? "" : workOrder.udsupervisor2);
            jsonObject.put("udqxbz", null == workOrder.udqxbz ? "" : workOrder.udqxbz);
            jsonObject.put("udworkmemo", null == workOrder.udworkmemo ? "" : workOrder.udworkmemo);
            jsonObject.put("udisyq", null == workOrder.udisyq ? "" : workOrder.udisyq);
            jsonObject.put("failurecode", null == workOrder.failurecode ? "" : workOrder.failurecode);
            jsonObject.put("udgzlbdm", null == workOrder.udgzlbdm ? "" : workOrder.udgzlbdm);
            if (woactivities != null && woactivities.size() != 0) {
                JSONArray woactivityArray = new JSONArray();
                JSONObject woactivityObj;
                for (int i = 0; i < woactivities.size(); i++) {
                    woactivityObj = new JSONObject();
                    woactivityObj.put("taskid", woactivities.get(i).taskid);
                    woactivityObj.put("description", woactivities.get(i).description);
                    woactivityObj.put("wojo1", woactivities.get(i).wojo1);
                    woactivityObj.put("wojo2", woactivities.get(i).wojo2);
                    woactivityObj.put("udisdo", woactivities.get(i).udisdo);
                    woactivityObj.put("udisyq", woactivities.get(i).udisyq);
                    woactivityObj.put("udyqyy", woactivities.get(i).udyqyy);
                    woactivityObj.put("udremark", woactivities.get(i).udremark);
                    woactivityArray.put(woactivityObj);
                }
                jsonObject.put("wotasks", woactivityArray);
            } else {
                jsonObject.put("wotasks", new JSONArray());
            }
            if (labtranses != null && labtranses.size() != 0) {
                JSONArray labtransArray = new JSONArray();
                JSONObject labtransObj;
                for (int i = 0; i < labtranses.size(); i++) {
                    labtransObj = new JSONObject();
                    labtransObj.put("labtransid", labtranses.get(i).labtransid);
                    labtransObj.put("taskid", labtranses.get(i).actualstaskid);
                    labtransObj.put("laborcode", labtranses.get(i).laborcode);
                    labtransObj.put("startdate", labtranses.get(i).startdate);
                    labtransObj.put("starttime", labtranses.get(i).starttime);
                    labtransObj.put("finishtime", labtranses.get(i).finishtime);
                    labtransObj.put("regularhrs", labtranses.get(i).regularhrs);
                    labtransObj.put("payrate", labtranses.get(i).payrate);
//                    labtransObj.put("linecost", labtranses.get(i).linecost);
                    labtransObj.put("optiontype", labtranses.get(i).optiontype);
                    labtransArray.put(labtransObj);
                }
                jsonObject.put("labtrans", labtransArray);
            } else {
                jsonObject.put("labtrans", new JSONArray());
            }
            if (failurereports != null && failurereports.size() != 0) {
                JSONArray failurereportArray = new JSONArray();
                JSONObject failurereportObj;
                for (int i = 0; i < failurereports.size(); i++) {
                    failurereportObj = new JSONObject();
                    failurereportObj.put("wonum", failurereports.get(i).wonum);
                    failurereportObj.put("failurecode", failurereports.get(i).failurecode);
                    failurereportObj.put("assetnum", failurereports.get(i).assetnum);
                    failurereportObj.put("linenum", failurereports.get(i).linenum);
                    failurereportObj.put("type", failurereports.get(i).type);
                    failurereportArray.put(failurereportObj);
                }
                jsonObject.put("failurereport", failurereportArray);
            } else {
                jsonObject.put("failurereport", new JSONArray());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 解析新增工单返回信息
     *
     * @param data
     * @return
     */
    public static WorkResult parsingInsertWO(String data) {
        Log.i(TAG, "data=" + data);
        String woNum = null;
        WorkResult workResult = new WorkResult();
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("errorMsg")) {
                workResult.errorMsg = object.getString("errorMsg");
            }
            if (object.has("wonum")) {
                workResult.wonum = object.getString("wonum");
            }
            if (object.has("errorNo")) {
                workResult.errorNo = object.getString("errorNo");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return workResult;
    }

    public static WorkResult parsinDeleteWo(String data) {
        Log.i(TAG, "data=" + data);
        WorkResult workResult = new WorkResult();
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("msg")) {
                workResult.errorMsg = object.getString("msg");
            }
            if (object.has("wonum")) {
                workResult.wonum = object.getString("wonum");
            }
            if (object.has("success")) {
                workResult.errorNo = object.getString("success");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return workResult;
    }


    /**
     * 解析位置列表信息
     */
    public static ArrayList<Locations> parsingLocations(String data) {
        Log.i(TAG, "Locations data=" + data);
        ArrayList<Locations> list = null;
        Locations locations = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Locations>();
            for (int i = 0; i < jsonArray.length(); i++) {
                locations = new Locations();
                jsonObject = jsonArray.getJSONObject(i);
                locations.locationsid = jsonObject.getString("LOCATIONSID"); //id
                locations.location = jsonObject.getString("LOCATION"); //编号
                locations.description = jsonObject.getString("DESCRIPTION"); //描述
                locations.orgid = jsonObject.getString("ORGID"); //组织
                locations.siteid = jsonObject.getString("SITEID");//地点
                locations.type = jsonObject.getString("TYPE");//类型
                list.add(locations);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 解析备件列表信息
     */
    public static ArrayList<Item> parsingItem(String data) {
        Log.i(TAG, "item data=" + data);


        ArrayList<Item> list = null;
        Item item = null;
        try {

            JSONArray jsonArray = new JSONArray(data);
            Log.i(TAG, "jsonArray size=" + jsonArray.length());
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<Item>();
            for (int i = 0; i < jsonArray.length(); i++) {
                item = new Item();
                jsonObject = jsonArray.getJSONObject(i);
                Log.i(TAG, "jsonObject=" + jsonObject.toString());
                item.itemnum = jsonObject.getString("ITEMNUM"); //编号
                item.description = jsonObject.getString("DESCRIPTION");
                item.orderunit = jsonObject.getString("ORDERUNIT");
                item.status = jsonObject.getString("STATUS");
                item.udsubject = jsonObject.getString("UDSUBJECT");
                item.udbrand = jsonObject.getString("UDBRAND");//类型
                item.udmanufacturer = jsonObject.getString("UDMANUFACTURER");
                item.udmodel = jsonObject.getString("UDMODEL");
                item.udstdname = jsonObject.getString("UDSTDNAME");
                item.udchnname = jsonObject.getString("UDCHNNAME");
                item.uduse = jsonObject.getString("UDUSE");
                item.siteid = jsonObject.getString("SITEID");
                list.add(item);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(TAG, "sssss");
            return null;
        }
    }


    /**
     * 解析选择工单列表信息
     */
    public static ArrayList<WorkOrderTem> parsingWorkOrderTem(String data) {
        Log.i(TAG, "WorkOrderTem data=" + data);
        ArrayList<WorkOrderTem> list = null;
        WorkOrderTem workOrderTem = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = new JSONObject();
            list = new ArrayList<WorkOrderTem>();
            for (int i = 0; i < jsonArray.length(); i++) {
                workOrderTem = new WorkOrderTem();
                jsonObject = jsonArray.getJSONObject(i);
                workOrderTem.wonum = jsonObject.getString("WONUM"); //工单
                workOrderTem.description = jsonObject.getString("DESCRIPTION"); //描述
                workOrderTem.status = jsonObject.getString("STATUS"); //状态
                workOrderTem.siteid = jsonObject.getString("SITEID");//地点
                workOrderTem.worktype = jsonObject.getString("WORKTYPE");//类型
                workOrderTem.udcreateby = jsonObject.getString("UDCREATEBY");//类型
                list.add(workOrderTem);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 解析新增领料单返回信息
     *
     * @param data
     * @return
     */
    public static InvuseResult parsingInsertIn(String data) {
        Log.i(TAG, "data=" + data);
        InvuseResult invuseResult = new InvuseResult();
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("errorMsg")) {
                invuseResult.errorMsg = object.getString("errorMsg");
            }
            if (object.has("invusenum")) {
                invuseResult.invusenum = object.getString("invusenum");
            }
            if (object.has("errorNo")) {
                invuseResult.errorNo = object.getString("errorNo");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return invuseResult;
    }


    /**
     * 封装领料单数据*
     */
    public static String InvuseToJson(Invuse invuse, ArrayList<Invuseline> invuselines) {


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("invusenum", null == invuse.invusenum ? "" : invuse.invusenum);
            jsonObject.put("description", null == invuse.description ? "" : invuse.description);
            jsonObject.put("fromstoreloc", null == invuse.fromstoreloc ? "" : invuse.fromstoreloc);
            jsonObject.put("wonum", null == invuse.wonum ? "" : invuse.wonum);
            jsonObject.put("udissueto", null == invuse.udissueto ? "" : invuse.udissueto);
            jsonObject.put("uddept", null == invuse.uddept ? "" : invuse.uddept);
            jsonObject.put("udisjj", null == invuse.udisjj ? "" : invuse.udisjj);
            jsonObject.put("udjbr", null == invuse.udjbr ? "" : invuse.udjbr);
            jsonObject.put("status", null == invuse.status ? "" : invuse.status);
            jsonObject.put("udapptype", null == invuse.udapptype ? "" : invuse.udapptype);
            jsonObject.put("udreason", null == invuse.udreason ? "" : invuse.udreason);
            jsonObject.put("statusdate", null == invuse.statusdate ? "" : invuse.statusdate);
            jsonObject.put("createdate", null == invuse.createdate ? "" : invuse.createdate);

            if (invuselines != null && invuselines.size() != 0) {
                JSONArray invuselinesArray = new JSONArray();
                JSONObject invuselinesObj;
                for (int i = 0; i < invuselines.size(); i++) {
                    invuselinesObj = new JSONObject();
                    invuselinesObj.put("invusenum", invuselines.get(i).invusenum);
                    invuselinesObj.put("itemnum", invuselines.get(i).itemnum);
                    invuselinesObj.put("usetype", "发放");
                    invuselinesObj.put("quantity", invuselines.get(i).quantity);
                    invuselinesObj.put("frombin", invuselines.get(i).frombin);
                    invuselinesObj.put("taskid", invuselines.get(i).taskid);
                    invuselinesObj.put("issueto", invuselines.get(i).issueto);
                    invuselinesObj.put("level5", invuselines.get(i).classstructureid);
                    invuselinesObj.put("level6", invuselines.get(i).level6);
                    invuselinesObj.put("remark", invuselines.get(i).remark);
                    invuselinesObj.put("optiontype", invuselines.get(i).optiontype);
                    invuselinesArray.put(invuselinesObj);
                }
                jsonObject.put("invuseline", invuselinesArray);
            } else {
                JSONArray invuselines1Array = new JSONArray();
                JSONObject invuselines1Obj;
                invuselines1Obj = new JSONObject();
                invuselines1Obj.put("invusenum", "");
                invuselines1Obj.put("itemnum", "");
                invuselines1Obj.put("usetype", "");
                invuselines1Obj.put("quantity", "");
                invuselines1Obj.put("frombin", "");
                invuselines1Obj.put("taskid", "");
                invuselines1Obj.put("issueto", "");
                invuselines1Obj.put("level5", "");
                invuselines1Obj.put("level6", "");
                invuselines1Obj.put("remark", "");
                invuselines1Obj.put("optiontype", "");
                invuselines1Array.put(invuselines1Obj);


                jsonObject.put("invuseline", invuselines1Array);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    /**
     * 外协服务付款申请
     */
    public static ArrayList<Invoice> parsingInvoice(Context ctx, String data) {
        ArrayList<Invoice> list = null;
        Invoice invoice = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Invoice>();
            for (int i = 0; i < jsonArray.length(); i++) {
                invoice = new Invoice();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = invoice.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = invoice.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(invoice);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = invoice.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(invoice, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(invoice);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 外协服务付款申请行
     */
    public static ArrayList<InvoiceLine> parsingInvoiceLine(Context ctx, String data) {
        ArrayList<InvoiceLine> list = null;
        InvoiceLine invoiceLine = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<InvoiceLine>();
            for (int i = 0; i < jsonArray.length(); i++) {
                invoiceLine = new InvoiceLine();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = invoiceLine.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = invoiceLine.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(invoiceLine);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = invoiceLine.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(invoiceLine, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(invoiceLine);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 非年度采购订单
     */
    public static ArrayList<Po> parsingPo(Context ctx, String data) {
        ArrayList<Po> list = null;
        Po po = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Po>();
            for (int i = 0; i < jsonArray.length(); i++) {
                po = new Po();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = po.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = po.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(po);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = po.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(po, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(po);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 非年度采购订单行
     */
    public static ArrayList<PoLine> parsingPoLine(Context ctx, String data) {
        ArrayList<PoLine> list = null;
        PoLine poline = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<PoLine>();
            for (int i = 0; i < jsonArray.length(); i++) {
                poline = new PoLine();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = poline.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = poline.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(poline);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = poline.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(poline, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(poline);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 外协服务采购订单
     */
    public static ArrayList<WaiXiePo> parsingWaiXiePo(Context ctx, String data) {
        ArrayList<WaiXiePo> list = null;
        WaiXiePo waiXiePo = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<WaiXiePo>();
            for (int i = 0; i < jsonArray.length(); i++) {
                waiXiePo = new WaiXiePo();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = waiXiePo.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = waiXiePo.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(waiXiePo);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = waiXiePo.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(waiXiePo, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(waiXiePo);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 服务接收验收
     */
    public static ArrayList<Servrectrans> parsingServrectrans(Context ctx, String data) {
        ArrayList<Servrectrans> list = null;
        Servrectrans servrectrans = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<Servrectrans>();
            for (int i = 0; i < jsonArray.length(); i++) {
                servrectrans = new Servrectrans();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = servrectrans.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = servrectrans.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(servrectrans);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = servrectrans.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(servrectrans, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(servrectrans);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 物资采购申请
     */
    public static ArrayList<PR> parsingPr(Context ctx, String data) {
        ArrayList<PR> list = null;
        PR pr = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<PR>();
            for (int i = 0; i < jsonArray.length(); i++) {
                pr = new PR();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = pr.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = pr.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(pr);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = pr.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(pr, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(pr);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 物资采购申请行
     */
    public static ArrayList<PrLine> parsingPrline(Context ctx, String data) {
        ArrayList<PrLine> list = null;
        PrLine prline = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            list = new ArrayList<PrLine>();
            for (int i = 0; i < jsonArray.length(); i++) {
                prline = new PrLine();
                jsonObject = jsonArray.getJSONObject(i);
                Field[] field = prline.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
                for (int j = 0; j < field.length; j++) {     //遍历所有属性
                    field[j].setAccessible(true);
                    String name = field[j].getName();    //获取属性的名字
                    if (jsonObject.has(name) && jsonObject.getString(name) != null && !jsonObject.getString(name).equals("")) {
                        try {
                            // 调用getter方法获取属性值
                            Method getOrSet = prline.getClass().getMethod("get" + name);
                            Object value = getOrSet.invoke(prline);
                            if (value == null) {
                                //调用setter方法设属性值
                                Class[] parameterTypes = new Class[1];
                                parameterTypes[0] = field[j].getType();
                                getOrSet = prline.getClass().getDeclaredMethod("set" + name, parameterTypes);
                                getOrSet.invoke(prline, jsonObject.getString(name));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                list.add(prline);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


}