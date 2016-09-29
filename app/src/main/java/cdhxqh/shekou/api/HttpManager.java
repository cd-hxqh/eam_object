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
import cdhxqh.shekou.utils.AccountUtils;

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
    public static String getwfassignmentUrl(String persionid, String vlaue, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + Constants.WFASSIGNMENT_APPID + "','objectname':'" + Constants.WFASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WFASSIGNMENTID DESC','condition':{'ASSIGNCODE':'" + persionid + "','ASSIGNSTATUS':'=活动'}}";
        } else {
            return "{'appid':'" + Constants.WFASSIGNMENT_APPID + "','objectname':'" + Constants.WFASSIGNMENT_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'WFASSIGNMENTID DESC','condition':{'ASSIGNCODE':'" + persionid + "','ASSIGNSTATUS':'=活动'}" + ",'sinorsearch':{'WFASSIGNMENTID':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";
        }
    }


    /**
     * 设置工单接口*
     */
    public static String getworkorderUrl(String type, String vlaue, String siteid, int curpage, int showcount) {
        if (vlaue.equals("")) {
            return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'LENGTH(WONUM) DESC,WONUM DESC','condition':{'WORKTYPE':'" + type + "','SITEID':'" + siteid + "','WOCLASS':'=工单','STATUS':'=工单建立,=提交主任分配,=工单执行,=提交监督审核,=提交主任审核'}}";
        } else {
            return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WORKORDER_NAME + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'LENGTH(WONUM) DESC,WONUM DESC','condition':{'WORKTYPE':'" + type + "','SITEID':'" + siteid + "','WOCLASS':'=工单','STATUS':'=工单建立,=提交主任分配,=工单执行,=提交监督审核,=提交主任审核'}" + ",'sinorsearch':{'WONUM':'" + vlaue + "','DESCRIPTION':'" + vlaue + "'}}";

        }
    }


    /**
     * 设置选择工单接口*
     */
    public static String getChooseWorkOrderUrl(String search, String siteid, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.WOTRACK_APPID + "','objectname':'" + Constants.WORKORDER_APPID + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'LENGTH(WONUM) DESC,WONUM DESC','condition':{'WORKTYPE':'!=OSPR','SITEID':'" + siteid + "','WOCLASS':'=工单','STATUS':'=提交主任分配,=工单执行,=提交监督审核,=提交主任审核'}}";
        } else {
            return "{'appid':'" + Constants.WOTRACK_APPID + "','objectname':'" + Constants.WORKORDER_APPID + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'LENGTH(WONUM) DESC,WONUM DESC','condition':{'WORKTYPE':'!=OSPR','SITEID':'" + siteid + "','WOCLASS':'=工单','STATUS':'=提交主任分配,=工单执行,=提交监督审核,=提交主任审核'}" + ",'sinorsearch':{'WONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";

        }
    }

    /**
     * 设置查询工单接口*
     */
    public static String getSearchWorkOrderUrl(String search, String siteid, int curpage, int showcount) {
        if (search.equals("")) {
            return "{'appid':'" + Constants.WOTRACK_APPID + "','objectname':'" + Constants.WORKORDER_APPID + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'LENGTH(WONUM) DESC,WONUM DESC','condition':{'WORKTYPE':'!=OSPR','WOCLASS':'=工单','WONUM':'!=UNWO'}}";
        } else {
            return "{'appid':'" + Constants.WOTRACK_APPID + "','objectname':'" + Constants.WORKORDER_APPID + "'," +
                    "'curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'LENGTH(WONUM) DESC,WONUM DESC','condition':{'WORKTYPE':'!=OSPR','WOCLASS':'=工单','WONUM':'!=UNWO'}" + ",'sinorsearch':{'WONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";

        }
    }

    /**
     * 设置计划任务接口*
     */
    public static String getwoactivityUrl(String type, String wonum, String sitesite, int curpage, int showcount) {
        return "{'appid':'" + "UDWOALL','objectname':'" + Constants.WOACTIVITY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'" +
                ",'condition':{'parent':'" + wonum + "','siteid':'" + sitesite + "'}" +
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
    public static String getlabtransUrl(String type, String wonum, int curpage, int showcount) {
        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.LABTRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','reporsearch':{'LABTRANS_WOANDTASK.WONUM':'=" + wonum + "','LABTRANS_WOANDTASK.PARENT':'=" + wonum + "'}}";
    }

    /**
     * 设置实际物料接口
     */
    public static String getmatusetransUrl(String type, int curpage, int showcount, String wonum) {
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
        return "{'appid':'" + Constants.INVENTOR_APPID + "','objectname':'" + Constants.INVENTORY_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','reporsearch':{'ITEMNUM':'" + value + "','ITEM.DESCRIPTION':'" + value + "'}}";
    }


    /**
     * 设置库存成本的接口
     * 根据Itemnum
     */
    public static String getInvcosturl(String value, int curpage, int showcount, String itemnum, String location, String siteid) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.INVCOST_APPID + "','objectname':'" + Constants.INVCOST_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "','LOCATION':'" + location + "','SITEID':'" + siteid + "'}}";
        } else {
            return "{'appid':'" + Constants.INVCOST_APPID + "','objectname':'" + Constants.INVCOST_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "','ITEMNUM':'" + value + "','LOCATION':'" + location + "','SITEID':'" + siteid + "'}}";

        }
    }

    /**
     * 设置库存余量的接口
     * 根据Itemnum
     */
    public static String getInvbalancesurl(String value, int curpage, int showcount, String itemnum, String location, String siteid) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.INVBALANCES_APPID + "','objectname':'" + Constants.INVBALANCES_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "','LOCATION':'" + location + "','SITEID':'" + siteid + "'}}";

        } else {
            return "{'appid':'" + Constants.INVBALANCES_APPID + "','objectname':'" + Constants.INVBALANCES_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ITEMNUM':'" + itemnum + "','BINNUM':'" + value + "','LOCATION':'" + location + "','SITEID':'" + siteid + "'}}";

        }
    }


    /**
     * 外协服务付款申请
     */
    public static String getInvoiceurl(String invoiceid) {

        return "{'appid':'" + Constants.INVOICE_APPID + "','objectname':'" + Constants.INVOICE_NAME + "','curpage':" + 1 + ",'showcount':" + 20 + ",'option':'read','condition':{'INVOICEID':'" + invoiceid + "'}}";


    }
    /**
     * 非年度采购单
     */
    public static String getPourl(String poid) {

        return "{'appid':'" + Constants.PO_APPID + "','objectname':'" + Constants.PO_NAME + "','curpage':" + 1 + ",'showcount':" + 20 + ",'option':'read','condition':{'POID':'" + poid + "'}}";


    }




    /**
     * 非年度采购单行
     */
    public static String getPoLineurl(String ponum) {

        return "{'appid':'" + Constants.PO_APPID + "','objectname':'" + Constants.POLINE_NAME + "','curpage':" + 1 + ",'showcount':" + 20 + ",'option':'read','condition':{'PONUM':'" + ponum + "'}}";


    }



    /**
     * 非年度采购单
     */
    public static String getWorkOrderByIdurl(String type,String workorderid) {

        return "{'appid':'" + "UDWO" + type + "','objectname':'" + Constants.WORKORDER_NAME + "','curpage':" + 1 + ",'showcount':" + 20 + ",'option':'read','condition':{'WORKORDERID':'" + workorderid + "'}}";


    }



    /**
     * 外协服务采购订单
     */
    public static String getWaiXiePourl(String poid) {

        return "{'appid':'" + Constants.WAIXIEPO_APPID + "','objectname':'" + Constants.PO_NAME + "','curpage':" + 1 + ",'showcount':" + 20 + ",'option':'read','condition':{'POID':'" + poid + "'}}";
    }


    /**
     * 外协服务采购单行
     */
    public static String getWaixiePoLineurl(String ponum) {

        return "{'appid':'" + Constants.WAIXIEPO_APPID + "','objectname':'" + Constants.POLINE_NAME + "','curpage':" + 1 + ",'showcount':" + 20 + ",'option':'read','condition':{'PONUM':'" + ponum + "'}}";


    }

    /**
     * 服务接收验收
     */
    public static String getServrectransurl(String ponum) {

        return "{'appid':'" + Constants.WAIXIEPO_APPID + "','objectname':'" + Constants.SERVRECTRANS_NAME + "','curpage':" + 1 + ",'showcount':" + 20 + ",'option':'read','condition':{'PONUM':'" + ponum + "'}}";


    }


    /**
     * 物资采购申请
     */
    public static String getPr(String prid) {

        return "{'appid':'" + Constants.PR_APPID + "','objectname':'" + Constants.PR_NAME + "','curpage':" + 1 + ",'showcount':" + 20 + ",'option':'read','condition':{'PRID':'" + prid + "'}}";


    }


    /**
     * 外协服务采购单行
     */
    public static String getWuZiPrLineurl(String prnum) {

        return "{'appid':'" + Constants.PR_APPID + "','objectname':'" + Constants.PRLINE_NAME + "','curpage':" + 1 + ",'showcount':" + 20 + ",'option':'read','condition':{'PRNUM':'" + prnum + "'}}";


    }

    /**
     * 付款申请行
     */
    public static String getInVoiceLineurl(int curpage, int showcount,String invoicenum) {

        return "{'appid':'" + Constants.INVOICE_APPID + "','objectname':'" + Constants.INVOICELINE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'INVOICENUM':'" + invoicenum + "'}}";


    }







    /**
     * 设置Invbalanceurl的接口
     */
    public static String getInvbalancesurl(String value, int curpage, int showcount, String location) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.INVBALANCES_APPID + "','objectname':'" + Constants.INVBALANCES_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'LOCATION':'" + location + "','CURBAL':'>0'}}";

        } else {
            return "{'appid':'" + Constants.INVBALANCES_APPID + "','objectname':'" + Constants.INVBALANCES_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'LOCATION':'" + location + "','CURBAL':'>0'}" + ",'sinorsearch':{'ITEMNUM':'" + value + "','DESCRIPTION':'" + value + "'}}";

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
     * 设置出库的接口
     * 根据wonum
     */
    public static String getMatusetransurl1(String value, int curpage, int showcount, String refnum) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.MATUSETRANS_APPID + "','objectname':'" + Constants.MATUSETRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'REFWO':'" + refnum + "'}}";

        } else {
            return "{'appid':'" + Constants.MATUSETRANS_APPID + "','objectname':'" + Constants.MATUSETRANS_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'REFWO':'" + refnum + "'}" + ",'sinorsearch':{'ITEMNUM':'" + value + "','DESCRIPTION':'" + value + "'}}";

        }
    }


    /**
     * 设置领料单的接口
     */
    public static String getInvuseurl(String value, String udapptype, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.INVUSE_APPID + "','objectname':'" + Constants.INVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'INVUSENUM DESC','condition':{'UDAPPTYPE':'=" + udapptype + "'}}";
        }
        return "{'appid':'" + Constants.INVUSE_APPID + "','objectname':'" + Constants.INVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'INVUSENUM DESC','condition':{'UDAPPTYPE':'=" + udapptype + "'}" + ",'sinorsearch':{'INVUSENUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
    }

    /**
     * 根据工单编号查询领料单的接口
     */
    public static String getByWonumInvuseurl(String value, String wonum, String udapptype, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.INVUSE_APPID + "','objectname':'" + Constants.INVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'INVUSENUM DESC','condition':{'UDAPPTYPE':'=" + udapptype + "','WONUM':'=" + wonum + "'}}";
        }
        return "{'appid':'" + Constants.INVUSE_APPID + "','objectname':'" + Constants.INVUSE_NAME + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','orderby':'INVUSENUM DESC','condition':{'UDAPPTYPE':'=" + udapptype + "','WONUM':'=" + wonum + "'}" + ",'sinorsearch':{'INVUSENUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
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
    public static String getAssetUrl(String siteid) {
        return "{'appid':'" + Constants.ASSET_APPID + "','objectname':'" + Constants.ASSET_NAME + "','option':'read','condition':{'SITEID':'" + siteid + "','UDLEVEL':'单体设备','STATUS':'操作中,活动,有限制的使用'}}";
    }

    /**
     * 设置作业计划下载数据接口
     */
    public static String getJpNumUrl(String siteid) {
        return "{'appid':'" + Constants.JOBPLAN_APPID + "','objectname':'" + Constants.JOBPLAN_NAME + "','option':'read','condition':{'SITEID':'" + siteid + "','UDISOS':'0'}}";
    }

    /**
     * 设置人员下载数据接口
     */
    public static String getPersonUrl(String siteid) {
        return "{'appid':'" + Constants.PERSON_APPID + "','objectname':'" + Constants.PERSON_NAME + "','option':'read','condition':{'LOCATIONSITE':'" + siteid + "'}}";
    }

    /**
     * 设置根据人员id查询人员信息
     */
    public static String getPersonUrl1(String persionid) {
        return "{'appid':'" + Constants.PERSON_APPID + "','objectname':'" + Constants.PERSON_NAME + "','option':'read','condition':{'PERSONID':'" + persionid + "'}}";
    }

    /**
     * 设置员工下载数据接口
     */
    public static String getLaborUrl(String siteid) {
        return "{'appid':'" + Constants.LABOR_APPID + "','objectname':'" + Constants.LABOR_NAME + "','option':'read','condition':{'WORKSITE':'" + siteid + "'}}";
    }

    /**
     * 设置抢修班组下载数据接口
     */
    public static String getAlndomainUrl(String siteid) {
        if (siteid.equals("CCT")) {
            return "{'appid':'" + Constants.ALNDOMAIN_APPID + "','objectname':'" + Constants.ALNDOMAIN_NAME + "','option':'read','condition':{'domainid':'UDEQ3','description':'ST-1,ST-2,ST-3,ST-4'}}";
        } else if (siteid.equals("SCT")) {
            return "{'appid':'" + Constants.ALNDOMAIN_APPID + "','objectname':'" + Constants.ALNDOMAIN_NAME + "','option':'read','condition':{'domainid':'UDEQ3','value':'030401,030402,03040',030404'}}";
        }
        return null;
    }

    /**
     * 设置故障类别下载数据接口
     */
    public static String getAlndomain2Url() {
        return "{'appid':'" + Constants.ALNDOMAIN_APPID + "','objectname':'" + Constants.ALNDOMAIN_NAME + "','option':'read','condition':{'DOMAINID':'UDGZLBDM'}}";
    }

    /**
     * 设置事故下载数据接口
     */
    public static String getUdevUrl(String siteid) {
        return "{'appid':'" + Constants.UDEV_APPID + "','objectname':'" + Constants.UDEV_NAME + "','option':'read','condition':{'status':'1','siteid':'" + siteid + "'}}";
    }

    /**
     * 设置立项申报下载数据接口
     */
    public static String getProjapprUrl(String siteid) {
        return "{'appid':'" + Constants.PROJAPPR_APPUD + "','objectname':'" + Constants.PROJAPPR_NAME + "','option':'read','condition':{'status':'APPR','FZDEPARTMENT':'90','siteid':'" + siteid + "'}}";
    }

    /**
     * 设置立项申报下载数据接口
     */
    public static String getPmUrl(String siteid) {
        return "{'appid':'" + Constants.PM_APPID + "','objectname':'" + Constants.PM_NAME + "','option':'read','condition':{'siteid':'" + siteid + "'}}";
    }

    /**
     * 设置员工工种下载数据接口
     */
    public static String getLaborcraftrateUrl(String siteid) {
        return "{'appid':'" + Constants.LABORCRAFTRATE_APPID + "','objectname':'" + Constants.LABORCRAFTRATE_NAME + "','option':'read','condition':{'defaultcraft':'1'}}";
    }

    /**
     * 设置故障下载数据接口
     */
    public static String getFailurelistUrl() {
        return "{'appid':'" + Constants.FAILURELIST_APPID + "','objectname':'" + Constants.FAILURELIST_NAME + "','option':'read'}";
    }


    /**
     * 设置库房下载数据接口
     */
    public static String getLocationUrl() {
        return "{'appid':'" + Constants.UDSTORELOC_APPID + "','objectname':'" + Constants.LOCATIONS_NAME + "','option':'read','condition':{'TYPE':'=库房'}}";
    }

    /**
     * 设置备件下载数据接口
     */
    public static String getItemUrl() {
        return "{'appid':'" + Constants.UDITEM_APPID + "','objectname':'" + Constants.ITEM_APPID + "','option':'read','curpage':1,'showcount':2000}";
    }
    /**
     * 设置选择备件数据接口
     */
    /**
     * 设置领料单行的接口
     */
    public static String getItemurl(String value, int curpage, int showcount) {
        if (value.equals("")) {
            return "{'appid':'" + Constants.UDITEM_APPID + "','objectname':'" + Constants.ITEM_APPID + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + Constants.UDITEM_APPID + "','objectname':'" + Constants.ITEM_APPID + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','sinorsearch':{'ITEMNUM':'" + value + "','DESCRIPTION':'" + value + "'}}";
        }
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

        String ip_adress = AccountUtils.getIpAddress(cxt) + Constants.SIGN_IN_URL;
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("loginid", username);
        params.put("password", password);
        params.put("imei", imei);
        client.setTimeout(20000);
        client.post(ip_adress, params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, IMErrorType.errorMessage(cxt, IMErrorType.ErrorLoginFailure));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "SstatusCode=" + statusCode + "responseString=" + responseString);
                if (statusCode == 200) {
                    LoginResults loginResults = JsonUtils.parsingAuthStr(cxt, responseString);
                    if (loginResults != null) {
                        if (loginResults.getErrcode().equals(Constants.LOGINSUCCESS) || loginResults.getErrcode().equals(Constants.CHANGEIMEI)) {
                            SafeHandler.onSuccess(handler, loginResults.getResult());
                        } else if (loginResults.getErrcode().equals(Constants.USERNAMEERROR)) {
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
        Log.i(TAG, "data=" + data);
        String url = AccountUtils.getIpAddress(cxt) + Constants.BASE_URL;
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setTimeout(60000);
        client.get(url, params, new TextHttpResponseHandler() {

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
        String url = AccountUtils.getIpAddress(cxt) + Constants.BASE_URL;
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);
        client.setTimeout(60000);
        client.get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "statusCode" + "responseString=" + responseString);
                Results result = JsonUtils.parsingResults(cxt, responseString);
                if (result == null) {
                    SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
                } else {
                    SafeHandler.onSuccess(handler, result, result.getCurpage(), result.getShowcount());
                }
            }
        });
    }


}
