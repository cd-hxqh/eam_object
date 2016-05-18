package cdhxqh.shekou.api;


import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import cdhxqh.shekou.R;
import cdhxqh.shekou.application.BaseApplication;
import cdhxqh.shekou.bean.LoginResults;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;

/**
 * Created by apple on 15/5/27.
 */
public class HttpManager {

    private static BaseApplication mApp = BaseApplication.getInstance();
    private static AsyncHttpClient sClient = null;
    private static final String TAG = "HttpManager";

    /**
     * 设置待办事项接口*
     */
    public static String getwfassignmentUrl(String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.WFASSIGNMENT_APPID + "','objectname':'" + Constants.WFASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.WFASSIGNMENT_APPID + "','objectname':'" + Constants.WFASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WFASSIGNMENTID':'" + vlaue + "'}}";
        }
    }


    /**
     * 设置工单接口*
     */
    public static String getworkorderUrl(String type, String search, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WORKTYPE':'" + type + "'}}";
        } else {
            return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'%" + search + "%','WORKTYPE':'" + type + "'}}";
        }
    }

    /**
     * 设置计划任务接口*
     */
    public static String getwoactivityUrl(String type, int curpage, int showcount) {
        return "{'appid':'" + "UDWOALL','objectname':'" + Constants.WOACTIVITY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" +
//                ",'condition':{'WONUM':'" + wonum + "'}" +
                "}";
    }

    /**
     * 设置计划员工接口*
     */
    public static String getwplaborUrl(String type, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WPLABOR_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置计划物料接口*
     */
    public static String getwpitemUrl(String type, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WPITEM_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置任务分配接口*
     */
    public static String getassignmentUrl(String type, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.ASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置实际员工接口
     */
    public static String getlabtransUrl(String type, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.LABTRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置实际物料接口
     */
    public static String getmatusetransUrl(String type, int curpage, int showcount,String wonum) {
        return "{'appid':'" + Constants.MATUSETRANS_APPID + "','objectname':'" + Constants.MATUSETRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
    }

    /**
     * 设置故障汇报接口
     */
    public static String getfailurereportUrl(String type, String wonum) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.FAILUREREPORT_NAME + "','option':'read','condition':{'WONUM':'" + wonum + "'}}";
    }

    /**
     * 设置库存查询的接口
     */
    public static String getInventorurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.INVENTOR_APPID + "','objectname':'" + Constants.INVENTORY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        }
        return "{'appid':'" + Constants.INVENTOR_APPID + "','objectname':'" + Constants.INVENTORY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + value + "'}}";
    }


    /**
     * 设置库存成本的接口
     * 根据Itemnum
     */
    public static String getInvcosturl(String value, int curpage, int showcount, String itemnum) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.INVCOST_APPID + "','objectname':'" + Constants.INVCOST_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "'}}";
        } else {
            return "{'appid':'" + Constants.INVCOST_APPID + "','objectname':'" + Constants.INVCOST_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "','ITEMNUM':'" + value + "'}}";

        }
    }

    /**
     * 设置库存余量的接口
     * 根据Itemnum
     */
    public static String getInvbalancesurl(String value, int curpage, int showcount, String itemnum) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.INVBALANCES_APPID + "','objectname':'" + Constants.INVBALANCES_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "'}}";

        } else {
            return "{'appid':'" + Constants.INVBALANCES_APPID + "','objectname':'" + Constants.INVBALANCES_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "','BINNUM':'" + value + "'}}";

        }
    }


    /**
     * 设置入库的接口
     * 根据Itemnum
     */
    public static String getMatrectransurl(String value, int curpage, int showcount, String itemnum) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.MATRECTRANS_APPID + "','objectname':'" + Constants.MATRECTRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "'}}";

        } else {
            return "{'appid':'" + Constants.MATRECTRANS_APPID + "','objectname':'" + Constants.MATRECTRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "','ISSUETYPE':'" + value + "'}}";
        }
    }

    /**
     * 设置出库的接口
     * 根据Itemnum
     */
    public static String getMatusetransurl(String value, int curpage, int showcount, String itemnum) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.MATUSETRANS_APPID + "','objectname':'" + Constants.MATUSETRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "'}}";

        } else {
            return "{'appid':'" + Constants.MATUSETRANS_APPID + "','objectname':'" + Constants.MATUSETRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "','ISSUETYPE':'" + value + "'}}";

        }
    }

    /**
     * 设置领料单的接口
     */
    public static String getInvuseurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.INVUSE_APPID + "','objectname':'" + Constants.INVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        }
        return "{'appid':'" + Constants.INVUSE_APPID + "','objectname':'" + Constants.INVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INVUSENUM':'" + value + "'}}";
    }

    /**
     * 设置领料单行的接口
     */
    public static String getInvuselineurl(String value, int curpage, int showcount, String invusenum) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.INVUSELINE_APPID + "','objectname':'" + Constants.INVUSELINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INVUSENUM':'" + invusenum + "'}}";
        } else {
            return "{'appid':'" + Constants.INVUSELINE_APPID + "','objectname':'" + Constants.INVUSELINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INVUSENUM':'" + invusenum + "','ITEMNUM':'" + value + "'}}";
        }
    }

    /**
     * 设置设备下载数据接口
     */
    public static String getAssetUrl(String siteid){
        return "{'appid':'" + Constants.ASSET_APPID + "','objectname':'" + Constants.ASSET_NAME + "','option':'read','condition':{'SITEID':'CCT','UDLEVEL':'单体设备','STATUS':'操作中,活动,有限制的使用'}}";
    }

    /**
     * 设置作业计划下载数据接口
     */
    public static String getJpNumUrl(String siteid){
        return "{'appid':'" + Constants.JOBPLAN_APPID + "','objectname':'" + Constants.JOBPLAN_NAME + "','option':'read','condition':{'SITEID':'CCT','UDISOS':'0'}}";
    }

    /**
     * 设置人员下载数据接口
     */
    public static String getPersonUrl(String siteid){
        return "{'appid':'" + Constants.PERSON_APPID + "','objectname':'" + Constants.PERSON_NAME + "','option':'read','condition':{'LOCATIONSITE':'CCT'}}";
    }

    /**
     * 设置员工下载数据接口
     */
    public static String getLaborUrl(String siteid){
        return "{'appid':'" + Constants.LABOR_APPID + "','objectname':'" + Constants.LABOR_NAME + "','option':'read','condition':{'WORKSITE':'CCT'}}";
    }

    /**
     * 设置抢修班组下载数据接口
     */
    public static String getAlndomainUrl(String siteid){
        if (siteid.equals("CCT")) {
            return "{'appid':'" + Constants.ALNDOMAIN_APPID + "','objectname':'" + Constants.ALNDOMAIN_NAME + "','option':'read','condition':{'domainid':'UDEQ3','description':'ST-1,ST-2,ST-3,ST-4'}}";
        }else if (siteid.equals("SCT")){
            return "{'appid':'" + Constants.ALNDOMAIN_APPID + "','objectname':'" + Constants.ALNDOMAIN_NAME + "','option':'read','condition':{'domainid':'UDEQ3','value':'030401,030402,03040',030404'}}";
        }
        return null;
    }

    /**
     * 使用用户名密码登录
     *
     * @param cxt
     * @param username 用户名
     * @param password 密码
     * @param imei     密码
     * @param handler  返回结果处理
     */
    public static void loginWithUsername(final Context cxt, final String username, final String password, String imei,
                                         final HttpRequestHandler<String> handler) {


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("loginid", username);
        params.put("password", password);
        params.put("imei", imei);
        client.setTimeout(20000);
        client.post(Constants.SIGN_IN_URL, params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, IMErrorType.errorMessage(cxt, IMErrorType.ErrorLoginFailure));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "SstatusCode=" + statusCode + "responseString=" + responseString);
                if (statusCode == 200) {
                    LoginResults loginResults = JsonUtils.parsingAuthStr(cxt, responseString);
                    if(loginResults!=null){
                        if(loginResults.getErrcode().equals(Constants.LOGINSUCCESS)||loginResults.getErrcode().equals(Constants.CHANGEIMEI)){
                            SafeHandler.onSuccess(handler, loginResults.getErrmsg());
                        }else if(loginResults.getErrcode().equals(Constants.USERNAMEERROR)){
                            SafeHandler.onFailure(handler, loginResults.getErrmsg());
                        }
                    }

                }
            }
        });


    }


    /**
     * 不分页获取信息方法*
     */
    public static void getData(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setTimeout(20000);
        client.get(Constants.BASE_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Results result = JsonUtils.parsingResults1(cxt, responseString);

                SafeHandler.onSuccess(handler, result);

            }
        });
    }


    /**
     * 解析返回的结果--分页*
     */
    public static void getDataPagingInfo(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
        Log.i(TAG, "data=" + data);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setTimeout(20000);
        client.get(Constants.BASE_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "statusCode" + "responseString=" + responseString);
                Results result = JsonUtils.parsingResults(cxt, responseString);

                SafeHandler.onSuccess(handler, result, result.getCurpage(), result.getShowcount());
            }
        });
    }


}
