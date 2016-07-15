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

    public int invbalancesid; //唯一ID
    public String itemnum; //项目编号
    public String itemdescription; //项目描述
    public String binnum; //货位
    public String udtype; //批号
    public String curbal; //当前余量
    public String physcntdate; //实际盘点日期
    public String location; //库房


    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        invbalancesid = jsonObject.getInt("invbalancesid");
        itemnum = jsonObject.getString("itemnum");
        itemdescription = jsonObject.getString("itemdescription");
        binnum = jsonObject.getString("binnum");
        udtype = jsonObject.getString("udtype");
        curbal = jsonObject.getString("curbal");
        physcntdate = jsonObject.getString("physcntdate");
        location = jsonObject.getString("location");
    }

    public Invbalances() {
    }


    private Invbalances(Parcel in) {
        invbalancesid = in.readInt();
        itemnum = in.readString();
        binnum = in.readString();
        udtype = in.readString();
        curbal = in.readString();
        physcntdate = in.readString();
        location = in.readString();
        itemdescription = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(invbalancesid);
        dest.writeString(itemnum);
        dest.writeString(binnum);
        dest.writeString(udtype);
        dest.writeString(curbal);
        dest.writeString(physcntdate);
        dest.writeString(location);
        dest.writeString(itemdescription);

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
