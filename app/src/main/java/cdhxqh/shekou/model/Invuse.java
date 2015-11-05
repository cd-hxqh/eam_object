package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 *  领料单
 */
public class Invuse extends Entity implements Parcelable {
    private static final String TAG = "Invuse";
    private static final long serialVersionUID = 2015050105L;

    public String description; //描述
    public String fromstoreloc; //库房
    public String invuseid; //唯一ID
    public String invusenum; //领料单号
    public String orgid; //组织标识
    public String siteid; //地点
    public String status; //状态
    public String statusdate; //状态的日期
    public String udisjj; //是否紧急
    public String udissueto; //领料人
    public String wonum; //工单



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        description = jsonObject.getString("description");
        fromstoreloc = jsonObject.getString("fromstoreloc");
        invuseid = jsonObject.getString("invuseid");
        invusenum = jsonObject.getString("invusenum");
        orgid = jsonObject.getString("orgid");
        siteid = jsonObject.getString("siteid");
        status = jsonObject.getString("status");
        statusdate = jsonObject.getString("statusdate");
        udisjj = jsonObject.getString("udisjj");
        udissueto = jsonObject.getString("udissueto");
        wonum = jsonObject.getString("wonum");
    }

    public Invuse() {
    }


    private Invuse(Parcel in) {
        description = in.readString();
        fromstoreloc = in.readString();
        invuseid = in.readString();
        invusenum = in.readString();
        orgid = in.readString();
        siteid = in.readString();
        status = in.readString();
        statusdate = in.readString();
        udisjj = in.readString();
        udissueto = in.readString();
        wonum = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(fromstoreloc);
        dest.writeString(invuseid);
        dest.writeString(invusenum);
        dest.writeString(orgid);
        dest.writeString(siteid);
        dest.writeString(status);
        dest.writeString(statusdate);
        dest.writeString(udisjj);
        dest.writeString(udissueto);
        dest.writeString(wonum);

    }


    public static final Creator<Invuse> CREATOR = new Creator<Invuse>() {
        @Override
        public Invuse createFromParcel(Parcel source) {
            return new Invuse(source);
        }

        @Override
        public Invuse[] newArray(int size) {
            return new Invuse[size];
        }
    };

}
