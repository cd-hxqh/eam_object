package cdhxqh.shekou.utils;

/**
 * Created by think on 2016/6/8.
 */
public class WorkTitle {
    public static String getTitle(String type){
        String title = "工单";
        switch (type){
            case "CM":
                title = "故障工单";
                break;
            case "PM":
                title = "预防性维护工单";
                break;
            case "SR":
                title = "状态维修工单";
                break;
            case "PJ":
                title = "项目工单";
                break;
            case "RS":
                title = "可维护备件工单";
                break;
            case "EV":
                title = "事故工单";
                break;
            case "EM":
                title = "抢修工单";
                break;
        }
        return title;
    }
}
