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

    public int inventoryid; //唯一ID
    public String itemnum; //库存备件
    public String item_description; //备件描述
    public String location; //库房
    public String locations_description; //库房名称
    public String issueunit; //单位
    public String udfincp; //财务公司
    public String udfincp_name; //财务公司（中文）
    public String status; //状态
    public String binnum; //默认存放位置
    public String siteid; //地点
    public String curbaltotal; //当前余量
    public String lastissuedate; //上次发放日期
    public String avblbalance; //可用量
    public String item_inspectionrequired; //接收时检查
    public String item_udisreturn; //是否退还旧件






    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        inventoryid = jsonObject.getInt("inventoryid");
        itemnum = jsonObject.getString("itemnum");
        item_description = jsonObject.getString("item_description");
        location = jsonObject.getString("location");
        locations_description = jsonObject.getString("locations_description");
        issueunit = jsonObject.getString("issueunit");
        udfincp= jsonObject.getString("udfincp");
        udfincp_name = jsonObject.getString("udfincp_name");
        status = jsonObject.getString("status");
        binnum = jsonObject.getString("binnum");
        siteid = jsonObject.getString("siteid");
        curbaltotal = jsonObject.getString("curbaltotal");
        lastissuedate = jsonObject.getString("lastissuedate");
        avblbalance = jsonObject.getString("avblbalance");
        item_inspectionrequired = jsonObject.getString("item_inspectionrequired");
        item_udisreturn = jsonObject.getString("item_udisreturn");
    }

    public Inventory() {
    }


    private Inventory(Parcel in) {
        inventoryid = in.readInt();
        itemnum = in.readString();
        item_description = in.readString();
        location = in.readString();
        locations_description = in.readString();
        issueunit = in.readString();
        udfincp = in.readString();
        udfincp_name = in.readString();
        status = in.readString();
        binnum = in.readString();
        siteid = in.readString();
        curbaltotal = in.readString();
        lastissuedate = in.readString();
        avblbalance = in.readString();
        item_inspectionrequired = in.readString();
        item_udisreturn = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(inventoryid);
        dest.writeString(itemnum);
        dest.writeString(item_description);
        dest.writeString(location);
        dest.writeString(locations_description);
        dest.writeString(issueunit);
        dest.writeString(udfincp);
        dest.writeString(udfincp_name);
        dest.writeString(status);
        dest.writeString(binnum);
        dest.writeString(siteid);
        dest.writeString(curbaltotal);
        dest.writeString(lastissuedate);
        dest.writeString(avblbalance);
        dest.writeString(item_inspectionrequired);
        dest.writeString(item_udisreturn);

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
