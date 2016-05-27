package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 *  库存成本
 */
public class Invcost extends Entity implements Parcelable {
    private static final String TAG = "Invcost";
    private static final long serialVersionUID = 2015050105L;

    public int invcostid; //唯一id
    public String itemnum; //项目编号
    public String avgcost; //平均成本
    public String lastcost; //上次接受成本




    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        invcostid = jsonObject.getInt("invcostid");
        itemnum = jsonObject.getString("itemnum");
        avgcost = jsonObject.getString("avgcost");
        lastcost = jsonObject.getString("lastcost");
    }

    public Invcost() {
    }


    private Invcost(Parcel in) {
        invcostid = in.readInt();
        itemnum = in.readString();
        avgcost = in.readString();
        lastcost = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(invcostid);
        dest.writeString(avgcost);
        dest.writeString(itemnum);
        dest.writeString(lastcost);

    }


    public static final Creator<Invcost> CREATOR = new Creator<Invcost>() {
        @Override
        public Invcost createFromParcel(Parcel source) {
            return new Invcost(source);
        }

        @Override
        public Invcost[] newArray(int size) {
            return new Invcost[size];
        }
    };

}
