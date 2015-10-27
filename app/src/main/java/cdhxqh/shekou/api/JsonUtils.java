package cdhxqh.shekou.api;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Inventory;
import cdhxqh.shekou.model.Wfassignment;

/**
 * Json数据解析类
 */
public class JsonUtils {
    private static final String TAG = "JsonUtils";


    /**
     * 解析登录信息*
     */
    public static String parsingAuthStr(final Context cxt, String data) {
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

}