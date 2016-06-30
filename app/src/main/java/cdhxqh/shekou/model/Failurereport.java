package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by think on 2015/11/10.
 * 故障报告
 */
public class Failurereport implements Serializable {
    private static final String TAG = "Failurereport";
    private static final long serialVersionUID = 2015050105L;

    public String failurereportid;//唯一标识
    public String wonum; //工单号
    public String assetnum; //资产
    public String failurecode; //故障代码
    public String linenum; //行
    public String type;//类型
    public String orgid; //组织
    public String siteid; //地点
    public String parent;
    public String description;
    public String optiontype;




}
