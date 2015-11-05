package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by think on 2015/11/3.
 * 计划物料
 */
public class Wpitem extends Entity implements Parcelable {
    private static final String TAG = "Wpitem";
    private static final long serialVersionUID = 2015050105L;

    public String taskid; //任务
    public String itemnum; //项目
    public String itemqty; //项目数量
    public String orderunit; //订购单位
    public String unitcost; //单位成本
    public String linecost; //行成本
    public String location; //库房
    public String storelocsite; //库房地点
    public String requestnum; //请求
    public String requiredate; //要求的日期
    public String orgid; //组织标识
    public String siteid; //地点



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        taskid = jsonObject.getString("taskid");
        itemnum = jsonObject.getString("itemnum");
        itemqty = jsonObject.getString("itemqty");
        orderunit = jsonObject.getString("orderunit");
        unitcost = jsonObject.getString("unitcost");
        linecost = jsonObject.getString("linecost");
        location = jsonObject.getString("location");
        storelocsite = jsonObject.getString("storelocsite");
        requestnum = jsonObject.getString("requestnum");
        requiredate = jsonObject.getString("requiredate");
        orgid = jsonObject.getString("orgid");
        siteid = jsonObject.getString("siteid");
    }

    public Wpitem() {
    }


    private Wpitem(Parcel in) {
        taskid = in.readString();
        itemnum = in.readString();
        itemqty = in.readString();
        orderunit = in.readString();
        unitcost = in.readString();
        linecost = in.readString();
        location = in.readString();
        storelocsite = in.readString();
        requestnum = in.readString();
        requiredate = in.readString();
        orgid = in.readString();
        siteid = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(taskid);
        dest.writeString(itemnum);
        dest.writeString(itemqty);
        dest.writeString(orderunit);
        dest.writeString(unitcost);
        dest.writeString(linecost);
        dest.writeString(location);
        dest.writeString(storelocsite);
        dest.writeString(requestnum);
        dest.writeString(requiredate);
        dest.writeString(orgid);
        dest.writeString(siteid);
    }


    public static final Creator<Wpitem> CREATOR = new Creator<Wpitem>() {
        @Override
        public Wpitem createFromParcel(Parcel source) {
            return new Wpitem(source);
        }

        @Override
        public Wpitem[] newArray(int size) {
            return new Wpitem[size];
        }
    };

}
