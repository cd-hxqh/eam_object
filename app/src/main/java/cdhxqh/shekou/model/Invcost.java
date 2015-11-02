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

    public String avgcost; //
    public String itemnum; //
    public String lastcost; //
    public String location; //
    public String orgid; //
    public String siteid; //
    public String stdcost; //



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        avgcost = jsonObject.getString("avgcost");
        itemnum = jsonObject.getString("itemnum");
        lastcost = jsonObject.getString("lastcost");
        location = jsonObject.getString("location");
        orgid = jsonObject.getString("orgid");
        siteid = jsonObject.getString("siteid");
        stdcost = jsonObject.getString("stdcost");
    }

    public Invcost() {
    }


    private Invcost(Parcel in) {
        avgcost = in.readString();
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
        dest.writeString(itemnum);
        dest.writeString(lastcost);
        dest.writeString(location);
        dest.writeString(orgid);
        dest.writeString(siteid);
        dest.writeString(stdcost);

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
