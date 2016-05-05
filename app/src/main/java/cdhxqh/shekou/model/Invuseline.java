package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 领料单行
 */
public class Invuseline extends Entity implements Parcelable {
    private static final String TAG = "Invuseline";
    private static final long serialVersionUID = 2015050105L;

    public String invuselinenum; //编号
    public String invuselineid; //唯一ID
    public String itemnum; //备件
    public String invusenum; //领料单号
    public String description; //描述
    public String level4; //机构
    public String level5; //部位(五级)
    public String level6; //部位(六级)
    public String quantity; //领料数量
    public String frombin; //货柜
    public String invbalances_curbal; //货柜可用数量
    public String inventory_curbaltotal; //库存总数量
    public String inventory_issueunit; //发放单位
    public String remark; //备注
    public String udbudctrlnum; //预算编号
    public String budctrl_description; //预算描述
    public String udglaccount; //成本科目
    public String chartofaccounts_accountname; //成本科目描述
    public String budctrl_udlimit; //预算总额(元)
    public String budctrl_actual; //实际金额(元)
    public String budctrl_remainder; //剩余金额(元)
    public String displayunitcost; //单位成本
    public String displaylinecost; //行成本
    public String wonum; //工单号
    public String taskid; //任务号
    public String assetnum; //设备
    public String linetype; //行类型
    public String tositeid; //目标地点
    public String enterby_displayname; //输入人
    public String issueto; //领料人
    public String issueto_displayname; //领料人名称


    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        invuselinenum = jsonObject.getString("invuselinenum");
        invuselineid = jsonObject.getString("invuselineid");
        itemnum = jsonObject.getString("itemnum");
        invusenum = jsonObject.getString("invusenum");
        description = jsonObject.getString("description");
        level4 = jsonObject.getString("level4");
        level5 = jsonObject.getString("level5");
        level6 = jsonObject.getString("level6");
        quantity = jsonObject.getString("quantity");
        frombin = jsonObject.getString("frombin");
        invbalances_curbal = jsonObject.getString("invbalances_curbal");
        inventory_curbaltotal = jsonObject.getString("inventory_curbaltotal");
        inventory_issueunit = jsonObject.getString("inventory_issueunit");
        remark = jsonObject.getString("remark");
        udbudctrlnum = jsonObject.getString("udbudctrlnum");
        budctrl_description = jsonObject.getString("budctrl_description");
        udglaccount = jsonObject.getString("udglaccount");
        chartofaccounts_accountname = jsonObject.getString("chartofaccounts_accountname");
        budctrl_udlimit = jsonObject.getString("budctrl_udlimit");
        budctrl_actual = jsonObject.getString("budctrl_actual");
        budctrl_remainder = jsonObject.getString("budctrl_remainder");
        displayunitcost = jsonObject.getString("displayunitcost");
        displaylinecost = jsonObject.getString("displaylinecost");
        wonum = jsonObject.getString("wonum");
        taskid = jsonObject.getString("taskid");
        assetnum = jsonObject.getString("assetnum");
        linetype = jsonObject.getString("linetype");
        tositeid = jsonObject.getString("tositeid");
        enterby_displayname = jsonObject.getString("enterby_displayname");
        issueto = jsonObject.getString("issueto");
        issueto_displayname = jsonObject.getString("issueto_displayname");
    }

    public Invuseline() {
    }


    private Invuseline(Parcel in) {
        invuselinenum = in.readString();
        invuselineid = in.readString();
        itemnum = in.readString();
        invusenum = in.readString();
        description = in.readString();
        level4 = in.readString();
        level5 = in.readString();
        level6 = in.readString();
        quantity = in.readString();
        frombin = in.readString();
        invbalances_curbal = in.readString();
        inventory_curbaltotal = in.readString();
        inventory_issueunit = in.readString();
        remark = in.readString();
        udbudctrlnum = in.readString();
        budctrl_description = in.readString();
        udglaccount = in.readString();
        chartofaccounts_accountname = in.readString();
        budctrl_udlimit = in.readString();
        budctrl_actual = in.readString();
        budctrl_remainder = in.readString();
        displayunitcost = in.readString();
        displaylinecost = in.readString();
        wonum = in.readString();
        taskid = in.readString();
        assetnum = in.readString();
        linetype = in.readString();
        tositeid = in.readString();
        enterby_displayname = in.readString();
        issueto = in.readString();
        issueto_displayname = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(invuselinenum);
        dest.writeString(invuselineid);
        dest.writeString(itemnum);
        dest.writeString(invusenum);
        dest.writeString(description);
        dest.writeString(level4);
        dest.writeString(level5);
        dest.writeString(level6);
        dest.writeString(quantity);
        dest.writeString(frombin);
        dest.writeString(invbalances_curbal);
        dest.writeString(inventory_curbaltotal);
        dest.writeString(inventory_issueunit);
        dest.writeString(remark);
        dest.writeString(udbudctrlnum);
        dest.writeString(budctrl_description);
        dest.writeString(udglaccount);
        dest.writeString(chartofaccounts_accountname);
        dest.writeString(budctrl_udlimit);
        dest.writeString(budctrl_actual);
        dest.writeString(budctrl_remainder);
        dest.writeString(displayunitcost);
        dest.writeString(displaylinecost);
        dest.writeString(wonum);
        dest.writeString(taskid);
        dest.writeString(assetnum);
        dest.writeString(linetype);
        dest.writeString(tositeid);
        dest.writeString(enterby_displayname);
        dest.writeString(issueto);
        dest.writeString(issueto_displayname);


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
