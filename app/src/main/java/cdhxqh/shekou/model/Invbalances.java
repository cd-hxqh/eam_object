package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 库存余量
 */
public class Invbalances extends Entity implements Parcelable {
    private static final String TAG = "Invcost";
    private static final long serialVersionUID = 2015050105L;

    public String binnum; //货柜编号
    public String curbal; //当前余量
    public String itemnum; //项目编号
    public String location; //库房
    public String orgid; //组织标识
    public String physcnt; //实际库存量
    public String physcntdate; //盘点日期
    public String siteid; //位置
    public String stagedcurbal; //暂存余量
    public String unitcost; //单位成本



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        binnum = jsonObject.getString("binnum");
        curbal = jsonObject.getString("curbal");
        itemnum = jsonObject.getString("itemnum");
        location = jsonObject.getString("location");
        orgid = jsonObject.getString("orgid");
        physcnt = jsonObject.getString("physcnt");
        physcntdate = jsonObject.getString("physcntdate");
        siteid = jsonObject.getString("siteid");
        stagedcurbal = jsonObject.getString("stagedcurbal");
        unitcost = jsonObject.getString("UNITCOST");
    }

    public Invbalances() {
    }


    private Invbalances(Parcel in) {
        binnum = in.readString();
        curbal = in.readString();
        itemnum = in.readString();
        location = in.readString();
        orgid = in.readString();
        physcnt = in.readString();
        physcntdate = in.readString();
        siteid = in.readString();
        stagedcurbal = in.readString();
        unitcost = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(binnum);
        dest.writeString(curbal);
        dest.writeString(itemnum);
        dest.writeString(location);
        dest.writeString(orgid);
        dest.writeString(physcnt);
        dest.writeString(physcntdate);
        dest.writeString(siteid);
        dest.writeString(stagedcurbal);
        dest.writeString(unitcost);

    }


    public static final Creator<Invbalances> CREATOR = new Creator<Invbalances>() {
        @Override
        public Invbalances createFromParcel(Parcel source) {
            return new Invbalances(source);
        }

        @Override
        public Invbalances[] newArray(int size) {
            return new Invbalances[size];
        }
    };

}
