package cdhxqh.shekou.api;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Invbalances;
import cdhxqh.shekou.model.Invcost;
import cdhxqh.shekou.model.Inventory;
import cdhxqh.shekou.model.Matrectrans;
import cdhxqh.shekou.model.Matusetrans;
import cdhxqh.shekou.model.Wfassignment;
import cdhxqh.shekou.model.WorkOrder;

/**
 * Json数据解析类
 */
public class JsonUtils {
    private static final String TAG = "JsonUtils";


    /**
     * 解析登录信息*
     */
    public static String parsingAuthStr(final Context cxt, String data) {
        Log.i(TAG,"data="+data);
        String isSuccess = null;
        String errmsg = null;
        try {
            JSONObject json = new JSONObject(data);
            String jsonString = json.getString("errcode");
            if (jsonString.equals(Constants.LOGINSUCCESS) || jsonString.equals(Constants.CHANGEIMEI)) {
                errmsg = json.getString("errmsg");
            }

            return errmsg;


        } catch (JSONException e) {
            e.printStackTrace();
            return isSuccess;
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
                workOrder.worktype = type;//工单类型
                workOrder.description = jsonObject.getString("DESCRIPTION");//工单描述
                workOrder.assetnum = jsonObject.getString("ASSETNUM");//设备
                workOrder.assetdescription = jsonObject.getString("ASSETDESCRIPTION");//设备描述
                workOrder.udisaq = jsonObject.getString("UDISAQ");//是否安全
                workOrder.udisbx = jsonObject.getString("UDISBX");//是否保修
                workOrder.udiscb = jsonObject.getString("UDISCB");//是否抄表
                workOrder.udisjf = jsonObject.getString("UDISJF");//是否按项目计费
                workOrder.udisjj = jsonObject.getString("UDISJJ");//是否紧急维修
                workOrder.udisplayname = jsonObject.getString("UDISPLAYNAME");//
                workOrder.udremark = jsonObject.getString("UDREMARK");//备注
                workOrder.udtjsj = jsonObject.getString("UDTJSJ");//停机时间
                workOrder.actstart = jsonObject.getString("ACTSTART");//实际开始时间
                workOrder.actfinish = jsonObject.getString("ACTFINISH");//实际完成时间
                workOrder.glbz = jsonObject.getString("GLBZ");//设备管理班组编号
                workOrder.gls = jsonObject.getString("GLS");//设备管理室编号
                workOrder.glz = jsonObject.getString("GLZ");//设备管理组编号
                workOrder.jpnum = jsonObject.getString("JPNUM");//作业计划
                workOrder.ldispayname = jsonObject.getString("LDISPAYNAME");//
                workOrder.reportdate = jsonObject.getString("REPORTDATE");//汇报日期
                workOrder.reportedby = jsonObject.getString("REPORTEDBY");//报告人
                workOrder.sdisplayname = jsonObject.getString("SDISPLAYNAME");//
                workOrder.targstartdate = jsonObject.getString("TARGSTARTDATE");//计划开始时间
                workOrder.targcompdate = jsonObject.getString("TARGCOMPDATE");//计划完成时间
                list.add(workOrder);
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




}