package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * 物资接收表
 */
public class Matrectrans extends Entity implements Parcelable {
    private static final String TAG = "Matrectrans";
    private static final long serialVersionUID = 2015050105L;

    public String actualcost; //实际成本
    public String actualdate; //实际日期
    public String fromsiteid; //原地点
    public String fromstoreloc; //原位置
    public String issuetype; //交易类型
    public String itemnum; //项目编号
    public String linecost; //行成本
    public String loadedcost; //记入成本
    public String quantity; //数量
    public String tostoreloc; //目标位置
    public String transdate; //交易日期
    public String unitcost; //单位成本



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        actualcost = jsonObject.getString("actualcost");
        actualdate = jsonObject.getString("actualdate");
        fromsiteid = jsonObject.getString("fromsiteid");
        fromstoreloc = jsonObject.getString("fromstoreloc");
        issuetype = jsonObject.getString("issuetype");
        itemnum = jsonObject.getString("itemnum");
        linecost = jsonObject.getString("linecost");
        loadedcost = jsonObject.getString("loadedcost");
        quantity = jsonObject.getString("quantity");
        tostoreloc = jsonObject.getString("tostoreloc");
        transdate = jsonObject.getString("transdate");
        unitcost = jsonObject.getString("unitcost");
    }

    public Matrectrans() {
    }


    private Matrectrans(Parcel in) {
        actualcost = in.readString();
        actualdate = in.readString();
        fromsiteid = in.readString();
        fromstoreloc = in.readString();
        issuetype = in.readString();
        itemnum = in.readString();
        linecost = in.readString();
        loadedcost = in.readString();
        quantity = in.readString();
        tostoreloc = in.readString();
        transdate = in.readString();
        unitcost = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(actualcost);
        dest.writeString(actualdate);
        dest.writeString(fromsiteid);
        dest.writeString(fromstoreloc);
        dest.writeString(issuetype);
        dest.writeString(itemnum);
        dest.writeString(linecost);
        dest.writeString(loadedcost);
        dest.writeString(quantity);
        dest.writeString(tostoreloc);
        dest.writeString(transdate);
        dest.writeString(unitcost);

    }


    public static final Creator<Matrectrans> CREATOR = new Creator<Matrectrans>() {
        @Override
        public Matrectrans createFromParcel(Parcel source) {
            return new Matrectrans(source);
        }

        @Override
        public Matrectrans[] newArray(int size) {
            return new Matrectrans[size];
        }
    };

}
