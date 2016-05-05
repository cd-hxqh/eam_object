package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 *  领料单行
 */
public class Invuseline extends Entity implements Parcelable {
    private static final String TAG = "Invuseline";
    private static final long serialVersionUID = 2015050105L;

    public String actualdate; //实际日期
    public String description; //描述
    public String enterby; //输入人
    public String fromstoreloc; //库房
    public String invuselineid; //唯一Id
    public String invuselinenum; //行号
    public String invusenum; //领料单号
    public String issueunit;//发放单位
    public String itemnum; // 备件编码
    public String linecost; //行成本
    public String orgid; //组织标识
    public String quantity; //数量
    public String refwo; //工单
    public String returnedqty; //已退回数量
    public String siteid; //地点
    public String tositeid; //目标地点
    public String tostoreloc; //目标位置
    public String unitcost; //单位成本
    public String usetype; //使用情况类型
    public String wonum; //工单


    public String frombin; //货柜
    public String udbudctrlnum; //预算编号
    public String udglaccount; //成本科目
    public String taskid; //任务号
    public String assetnum; //设备
    public String linetype; //行类型
    public String issueto; //领料人
    public String remark ; //备注



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        actualdate = jsonObject.getString("actualdate");
        description = jsonObject.getString("description");
        enterby = jsonObject.getString("enterby");
        fromstoreloc = jsonObject.getString("fromstoreloc");
        invuselineid = jsonObject.getString("invuselineid");
        invuselinenum = jsonObject.getString("invuselinenum");
        invusenum = jsonObject.getString("invusenum");
        issueunit = jsonObject.getString("issueunit");
        itemnum = jsonObject.getString("itemnum");
        linecost = jsonObject.getString("linecost");
        orgid = jsonObject.getString("orgid");
        quantity = jsonObject.getString("quantity");
        refwo = jsonObject.getString("refwo");
        returnedqty = jsonObject.getString("returnedqty");
        siteid = jsonObject.getString("siteid");
        tositeid = jsonObject.getString("tositeid");
        tostoreloc = jsonObject.getString("tostoreloc");
        unitcost = jsonObject.getString("unitcost");
        usetype = jsonObject.getString("usetype");
        wonum = jsonObject.getString("wonum");
    }

    public Invuseline() {
    }


    private Invuseline(Parcel in) {
        actualdate = in.readString();
        description = in.readString();
        enterby = in.readString();
        fromstoreloc = in.readString();
        invuselineid = in.readString();
        invuselinenum = in.readString();
        invusenum = in.readString();
        issueunit = in.readString();
        itemnum = in.readString();
        linecost = in.readString();
        orgid = in.readString();
        quantity = in.readString();
        refwo = in.readString();
        returnedqty = in.readString();
        siteid = in.readString();
        tositeid = in.readString();
        tostoreloc = in.readString();
        unitcost = in.readString();
        usetype = in.readString();
        wonum = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(actualdate);
        dest.writeString(description);
        dest.writeString(enterby);
        dest.writeString(fromstoreloc);
        dest.writeString(invuselineid);
        dest.writeString(invuselinenum);
        dest.writeString(invusenum);
        dest.writeString(issueunit);
        dest.writeString(itemnum);
        dest.writeString(linecost);
        dest.writeString(orgid);
        dest.writeString(quantity);
        dest.writeString(refwo);
        dest.writeString(returnedqty);
        dest.writeString(siteid);
        dest.writeString(tositeid);
        dest.writeString(tostoreloc);
        dest.writeString(unitcost);
        dest.writeString(usetype);
        dest.writeString(wonum);


    }


    public static final Creator<Invuseline> CREATOR = new Creator<Invuseline>() {
        @Override
        public Invuseline createFromParcel(Parcel source) {
            return new Invuseline(source);
        }

        @Override
        public Invuseline[] newArray(int size) {
            return new Invuseline[size];
        }
    };

}
