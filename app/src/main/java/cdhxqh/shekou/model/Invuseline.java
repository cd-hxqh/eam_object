package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 领料单行
 */
@DatabaseTable(tableName = "Invuseline")
public class Invuseline extends Entity implements Parcelable {
    private static final String TAG = "Invuseline";
    private static final long serialVersionUID = 2015050105L;
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "invuselineid")
    public String invuselineid; //唯一ID
    @DatabaseField(columnName = "invuselinenum")
    public String invuselinenum; //编号
    @DatabaseField(columnName = "itemnum")
    public String itemnum; //备件
    @DatabaseField(columnName = "invusenum")
    public String invusenum; //领料单号
    @DatabaseField(columnName = "description")
    public String description; //描述
    @DatabaseField(columnName = "level4")
    public String level4; //机构
    @DatabaseField(columnName = "level5")
    public String level5; //部位(五级)
    @DatabaseField(columnName = "level6")
    public String level6; //部位(六级)
    @DatabaseField(columnName = "quantity")
    public String quantity; //领料数量
    @DatabaseField(columnName = "frombin")
    public String frombin; //货柜
    @DatabaseField(columnName = "invbalances_curbal")
    public String invbalances_curbal; //货柜可用数量
    @DatabaseField(columnName = "inventory_curbaltotal")
    public String inventory_curbaltotal; //库存总数量
    @DatabaseField(columnName = "inventory_issueunit")
    public String inventory_issueunit; //发放单位
    @DatabaseField(columnName = "remark")
    public String remark; //备注
    @DatabaseField(columnName = "udbudctrlnum")
    public String udbudctrlnum; //预算编号
    @DatabaseField(columnName = "budctrl_description")
    public String budctrl_description; //预算描述
    @DatabaseField(columnName = "udglaccount")
    public String udglaccount; //成本科目
    @DatabaseField(columnName = "chartofaccounts_accountname")
    public String chartofaccounts_accountname; //成本科目描述
    @DatabaseField(columnName = "budctrl_udlimit")
    public String budctrl_udlimit; //预算总额(元)
    @DatabaseField(columnName = "budctrl_actual")
    public String budctrl_actual; //实际金额(元)
    @DatabaseField(columnName = "budctrl_remainder")
    public String budctrl_remainder; //剩余金额(元)
    @DatabaseField(columnName = "displayunitcost")
    public String displayunitcost; //单位成本
    @DatabaseField(columnName = "displaylinecost")
    public String displaylinecost; //行成本
    @DatabaseField(columnName = "wonum")
    public String wonum; //工单号
    @DatabaseField(columnName = "taskid")
    public String taskid; //任务号
    @DatabaseField(columnName = "assetnum")
    public String assetnum; //设备
    @DatabaseField(columnName = "linetype")
    public String linetype; //行类型
    @DatabaseField(columnName = "tositeid")
    public String tositeid; //目标地点
    @DatabaseField(columnName = "enterby_displayname")
    public String enterby_displayname; //输入人
    @DatabaseField(columnName = "issueto")
    public String issueto; //领料人
    @DatabaseField(columnName = "issueto_displayname")
    public String issueto_displayname; //领料人名称
    @DatabaseField(columnName = "optiontype")
    public String optiontype; //操作


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
        optiontype = jsonObject.getString("optiontype");
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
        optiontype = in.readString();

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
        dest.writeString(optiontype);


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
