package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 库存表
 */
public class Inventory extends Entity implements Parcelable {
    private static final String TAG = "Inventory";
    private static final long serialVersionUID = 2015050105L;

    public String avgcost; //平均项目成本
    public String curbal; //当前余量
    public String issueunit; //发放单位
    public String itemnum; //项目编号
    public String lastcost; //项目成本
    public String location; //库房
    public String orgid; //组织标识
    public String siteid; //站点
    public String stdcost; //项目成本



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        avgcost = jsonObject.getString("avgcost");
        curbal = jsonObject.getString("curbal");
        issueunit = jsonObject.getString("issueunit");
        itemnum = jsonObject.getString("itemnum");
        lastcost = jsonObject.getString("lastcost");
        location = jsonObject.getString("location");
        orgid = jsonObject.getString("orgid");
        siteid = jsonObject.getString("siteid");
        stdcost = jsonObject.getString("stdcost");
    }

    public Inventory() {
    }


    private Inventory(Parcel in) {
        avgcost = in.readString();
        curbal = in.readString();
        issueunit = in.readString();
        itemnum = in.readString();
        lastcost = in.readString();
        location = in.readString();
        orgid = in.readString();
        siteid = in.readString();
        stdcost = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avgcost);
        dest.writeString(curbal);
        dest.writeString(issueunit);
        dest.writeString(itemnum);
        dest.writeString(lastcost);
        dest.writeString(location);
        dest.writeString(orgid);
        dest.writeString(siteid);
        dest.writeString(stdcost);

    }


    public static final Creator<Inventory> CREATOR = new Creator<Inventory>() {
        @Override
        public Inventory createFromParcel(Parcel source) {
            return new Inventory(source);
        }

        @Override
        public Inventory[] newArray(int size) {
            return new Inventory[size];
        }
    };

}
