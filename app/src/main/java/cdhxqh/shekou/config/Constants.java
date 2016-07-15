package cdhxqh.shekou.config;

/**
 * Created by think on 2015/10/19.
 */
public class Constants {

    /**
     * 基础接口*
     */
    /**
     * 默认 外网*
     */
    public static final String HTTP_API_IP = "http://121.35.242.172:7001";
    /**
     * 内网*
     */
    public static final String HTTPZHENGSHI_API_IP = "http://172.16.1.75:7001";
//    public static final String HTTP_API_URL = "http://121.35.242.172:7001/maximo/mobile/";
//
//    public static final String HTTPS_API_URL = "http://121.35.242.172:7001/meaweb/services";


    /**
     * 登陆URL*
     */

    public static final String SIGN_IN_URL = "/maximo/mobile/system/login";


    /**
     * 通用接口查询*
     */
    public static final String BASE_URL = "/maximo/mobile/common/api";

    /**
     * 工单接口*
     */
    public static final String WORK_URL = "/meaweb/services/WORKORDERSERVICE";

    /**
     * 领料单接口*
     */
    public static final String INVUSE_URL = "/meaweb/services/INVUSESERVICE";
    /**
     * 工作流*
     */
    public static final String WORK_FLOW_URL = "/meaweb/services/WFMENAGEMENTSERVIC";

    /**
     * ------------------数据库表名配置－－开始*
     */
    //待办事项的appid
    public static final String WFASSIGNMENT_APPID = "INBOX";

    //待办事项的表名
    public static final String WFASSIGNMENT_NAME = "WFASSIGNMENT";
    /**
     * 工单管理*
     */
    //故障工单查询的appid
    public static final String UDWOCM_APPID = "UDWOCM";
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
    //工单实际员工表名
    public static final String LABTRANS_NAME = "LABTRANS";
    //故障汇报表名
    public static final String FAILUREREPORT_NAME = "FAILUREREPORT";

    /**
     * 库存查询*
     */
    //库存的appid
    public static final String INVENTOR_APPID = "UDINVENTOR";
    //库存的表名
    public static final String INVENTORY_NAME = "INVENTORY";

    //库存成本appid
    public static final String INVCOST_APPID = "UDINVENTOR";
    //库存成本表名
    public static final String INVCOST_NAME = "INVCOST";


    //库存余量appid
    public static final String INVBALANCES_APPID = "UDINVENTOR";
    //库存余量表名
    public static final String INVBALANCES_NAME = "INVBALANCES";


    //入库appid
    public static final String MATRECTRANS_APPID = "UDINVENTOR";
    //入库表名
    public static final String MATRECTRANS_NAME = "MATRECTRANS";

    //出库appid
    public static final String MATUSETRANS_APPID = "UDINVENTOR";
    //出库表名
    public static final String MATUSETRANS_NAME = "MATUSETRANS";


    //领料单appid
    public static final String INVUSE_APPID = "UDUSE";
    //领料单表名
    public static final String INVUSE_NAME = "INVUSE";


    //领料单行appid
    public static final String INVUSELINE_APPID = "UDUSE";
    //领料单行表名
    public static final String INVUSELINE_NAME = "INVUSELINE";

    //设备appid
    public static final String ASSET_APPID = "UDASSET";
    //设备表名
    public static final String ASSET_NAME = "ASSET";
    //作业计划appid
    public static final String JOBPLAN_APPID = "UDJOBPLAN";
    //作业计划表名
    public static final String JOBPLAN_NAME = "JOBPLAN";
    //人员appid
    public static final String PERSON_APPID = "PERSON";
    //人员表名
    public static final String PERSON_NAME = "PERSON";
    //员工的appid
    public static final String LABOR_APPID = "LABOR";
    //员工表名
    public static final String LABOR_NAME = "LABOR";
    //抢修班组的appid
    public static final String ALNDOMAIN_APPID = "DOMAINADM";
    //抢修班组的表名
    public static final String ALNDOMAIN_NAME = "ALNDOMAIN";
    //事故appid
    public static final String UDEV_APPID = "UDEV";
    //事故表名
    public static final String UDEV_NAME = "UDEV";
    //立项申报appid
    public static final String PROJAPPR_APPUD = "PROJAPPR";
    //立项申报表名
    public static final String PROJAPPR_NAME = "PROJAPPR";
    //预防性维护appid
    public static final String PM_APPID = "UDPM";
    //预防性维护表名
    public static final String PM_NAME = "PM";
    //员工工种appid
    public static final String LABORCRAFTRATE_APPID = "LABOR";
    //员工工种表名
    public static final String LABORCRAFTRATE_NAME = "LABORCRAFTRATE";
    //故障appid
    public static final String FAILURELIST_APPID = "UDFAILURE";
    //故障表名
    public static final String FAILURELIST_NAME = "FAILURELIST";


    /**
     * 位置表*
     */
    public static final String UDSTORELOC_APPID = "UDSTORELOC";
    /**
     * 位置表名*
     */
    public static final String LOCATIONS_NAME = "LOCATIONS";

    /**
     * 选择工单*
     */
    public static final String WOTRACK_APPID = "WOTRACK";
    /**
     * 选择工单表名*
     */
    public static final String WORKORDER_APPID = "WORKORDER";


    /**
     * 备件的appid*
     */
    public static final String UDITEM_APPID = "UDITEM";
    /**
     * 备件的表名*
     */
    public static final String ITEM_APPID = "ITEM";


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

    public static final String GETDATA_EXCEPTION = "GLOBAL-E-5";//获取数据异常

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

    //工单状态
    public static final String STATUS1 = "已批准";
    public static final String STATUS2 = "取消";
    public static final String STATUS3 = "关闭";
    public static final String STATUS4 = "完成";
    public static final String STATUS5 = "等待厂家处理";
    public static final String STATUS6 = "已验收";
    public static final String STATUS7 = "工单执行";
    public static final String STATUS8 = "已完成/关闭";
    public static final String STATUS9 = "提交监督审核";
    public static final String STATUS10 = "提交主任审核";
    public static final String STATUS11 = "工作计划";
    public static final String STATUS12 = "未通过实现后复审";
    public static final String STATUS13 = "调度";
    public static final String STATUS14 = "调度";
    public static final String STATUS15 = "复审";
    public static final String STATUS16 = "已拒绝";
    public static final String STATUS17 = "已拒绝";
    public static final String STATUS18 = "提交主任分配";
    public static final String STATUS19 = "工单修正";
    public static final String STATUS20 = "询比价处理";
    public static final String STATUS21 = "审核完成";
    public static final String STATUS22 = "外协执行";
    public static final String STATUS23 = "外协执行完成";
    public static final String STATUS24 = "服务验收";
    public static final String STATUS25 = "工单建立";


    /**设置数据库参数-开始**/
    /**
     * 数据库路径
     */
    public static final String PATH_DB = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/Android/data/";
    /**
     * 无SD卡的数据库路径
     */
    public static final String NOT_SDCARD_PATH_DB = "/data/data/";
    /**
     * 数据库名称 *
     */
    public static final String TB_NAME = "sqlite-eam.db";

    /**
     * 选项跳转请求值
     */
    public static final int ASSETCODE = 100; //设备

    public static final int JOBPLANCODE = 110; //作业计划

    public static final int PERSONCODE = 120;//人员

    public static final int PERSONCODE1 = 121;//物管员经办人

    public static final int LABORCODE = 130;//员工

    public static final int LABORCODE1 = 140;//抢修负责人

    public static final int LABORCODE2 = 150;//抢修执行人

    public static final int LABORCODE3 = 160;//抢修执行人2

    public static final int ALNDOMAINCODE = 170;//抢修班组

    public static final int UDEVCODE = 180;//事故

    public static final int PROJAPPR = 190;//立项申报

    public static final int PMCODE = 200;//预防性维护

    public static final int LABORCRAFTRATECODE = 210;//员工工种

    public static final int FAILURE_TYPE = 220;//故障原因

    public static final int FAILURE_QUESTION = 230;//故障问题

    public static final int FAILURE_CAUSE = 240;//故障原因

    public static final int FAILURE_REMEMDY = 250;//故障措施

    public static final int ALNDOMAIN2CODE = 260;//故障类别

    public static final int LOCATIONCODE = 270;//库房

    public static final int WORKORDERCODE = 280;//工单

    public static final int ITEMCODE = 290;//备件
}
