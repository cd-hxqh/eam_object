package cdhxqh.shekou.config;

/**
 * Created by think on 2015/10/19.
 */
public class Constants {

    /**
     * 基础接口*
     */
    public static final String HTTP_API_URL = "http://121.35.242.172:7001/maximo/mobile/";

    /**
     * 登陆URL*
     */

    public static final String SIGN_IN_URL = HTTP_API_URL + "system/login";


    /**通用接口查询**/
    public static final String BASE_URL = HTTP_API_URL + "common/api";

    /**------------------数据库表名配置－－开始**/
    //待办事项的appid
    public static final String WFASSIGNMENT_APPID="INBOX";

    //待办事项的表名
    public static final String WFASSIGNMENT_NAME="WFASSIGNMENT";
    /**工单管理**/
    //故障工单查询的appid
    public static final String UDWOCM_APPID="UDWOCM";
    //故障工单表名
    public static final String WORKORDER_NAME = "WORKORDER";
    //工单计划任务表名
    public static final String WOACTIVITY_NAME = "WOACTIVITY";
    //工单计划员工表名
    public static final String WPLABOR_NAME = "WPLABOR";
    //工单计划物料表名
    public static final String WPITEM_NAME = "WPITEM";
    //工单任务分配表名
    public static final String ASSIGNMENT_NAME = "ASSIGNMENT";

    /**库存查询**/
    //库存的appid
    public static final String INVENTOR_APPID="UDINVENTOR";
    //库存的表名
    public static final String INVENTORY_NAME="INVENTORY";

    //库存成本appid
    public static final String INVCOST_APPID="UDINVENTOR";
    //库存成本表名
    public static final String INVCOST_NAME="INVCOST";


    //库存余量appid
    public static final String INVBALANCES_APPID="UDINVENTOR";
    //库存余量表名
    public static final String INVBALANCES_NAME="INVBALANCES";


    //入库appid
    public static final String MATRECTRANS_APPID="UDINVENTOR";
    //入库表名
    public static final String MATRECTRANS_NAME="MATRECTRANS";

    //出库appid
    public static final String MATUSETRANS_APPID="UDINVENTOR";
    //出库表名
    public static final String MATUSETRANS_NAME="MATUSETRANS";


    //领料单appid
    public static final String INVUSE_APPID="UDUSE";
    //领料单表名
    public static final String INVUSE_NAME="INVUSE";


    //领料单行appid
    public static final String INVUSELINE_APPID="UDUSE";
    //领料单行表名
    public static final String INVUSELINE_NAME="INVUSELINE";







    public static final String USER_INFO = "userinfo";
    public static final String NAME_KEY = "name_key";
    public static final String PASS_KEY = "pass_key";
    public static final String ISREMENBER = "isRemenber";


    /**
     * 用户登录表识--开始*
     */
    public static final String LOGINSUCCESS = "USER-S-101"; //登录成功

    public static final String CHANGEIMEI = "USER-S-104"; //登录成功,检测到用户更换手机登录

    public static final String USERNAMEERROR = "USER-E-100";//用户名密码错误

    public static final String GETDATASUCCESS = "GLOBAL-S-0";//获取数据成功

    /**
     * 工单跳转类型标识
     */
    public static final String FAULT = "CM";//故障工单
    public static final String PREVENT = "PM";//预防性维护工单
    public static final String STATUS = "SR";//状态维修工单
    public static final String PROJECT = "PJ";//项目工单
    public static final String SERVICE = "RS";//可维护备件工单
    public static final String ACCIDENT = "EV";//事故工单
    public static final String REPAIR = "EM";//抢修工单
}
