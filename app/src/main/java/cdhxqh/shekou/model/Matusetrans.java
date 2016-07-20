package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 物资发放表
 *
 */
public class Matusetrans extends Entity implements Parcelable {
    private static final String TAG = "Matusetrans";
    private static final long serialVersionUID = 2015050105L;

    public String actualcost; //实际成本
    public String actualdate; //实际日期
    public String assetnum; //资产编号
    public String curbal; //当前余量
    public String enterby; //输入人
    public String issuetype; //交易类型
    public String itemnum; //资产
    public String linecost; //行成本
    public String location; //位置
    public String matusetransid; //唯一id
    public String orgid; //组织标识
    public String physcnt; //实际盘点
    public String quantity; //数量
    public String refwo; //工单
    public String siteid; //地点
    public String storeloc; //库房的位置
    public String transdate; //交易日期
    public String unitcost; //单位成本
    public String description; //描述



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        actualcost = jsonObject.getString("actualcost");
        actualdate = jsonObject.getString("actualdate");
        assetnum = jsonObject.getString("assetnum");
        curbal = jsonObject.getString("curbal");
        enterby = jsonObject.getString("enterby");
        issuetype = jsonObject.getString("issuetype");
        itemnum = jsonObject.getString("itemnum");
        linecost = jsonObject.getString("linecost");
        location = jsonObject.getString("location");
        matusetransid = jsonObject.getString("matusetransid");
        orgid = jsonObject.getString("orgid");
        physcnt = jsonObject.getString("physcnt");
        quantity = jsonObject.getString("quantity");
        refwo = jsonObject.getString("refwo");
        siteid = jsonObject.getString("siteid");
        storeloc = jsonObject.getString("storeloc");
        transdate = jsonObject.getString("transdate");
        unitcost = jsonObject.getString("unitcost");
        description = jsonObject.getString("description");
    }

    public Matusetrans() {
    }


    private Matusetrans(Parcel in) {
        actualcost = in.readString();
        actualdate = in.readString();
        assetnum = in.readString();
        curbal = in.readString();
        enterby = in.readString();
        issuetype = in.readString();
        itemnum = in.readString();
        linecost = in.readString();
        location = in.readString();
        matusetransid = in.readString();
        orgid = in.readString();
        physcnt = in.readString();
        quantity = in.readString();
        refwo = in.readString();
        siteid = in.readString();
        storeloc = in.readString();
        transdate = in.readString();
        unitcost = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(actualcost);
        dest.writeString(actualdate);
        dest.writeString(assetnum);
        dest.writeString(curbal);
        dest.writeString(enterby);
        dest.writeString(issuetype);
        dest.writeString(itemnum);
        dest.writeString(linecost);
        dest.writeString(location);
        dest.writeString(matusetransid);
        dest.writeString(orgid);
        dest.writeString(physcnt);
        dest.writeString(quantity);
        dest.writeString(refwo);
        dest.writeString(siteid);
        dest.writeString(storeloc);
        dest.writeString(transdate);
        dest.writeString(unitcost);
        dest.writeString(description);

    }


    public static final Creator<Matusetrans> CREATOR = new Creator<Matusetrans>() {
        @Override
        public Matusetrans createFromParcel(Parcel source) {
            return new Matusetrans(source);
        }

        @Override
        public Matusetrans[] newArray(int size) {
            return new Matusetrans[size];
        }
    };

}
