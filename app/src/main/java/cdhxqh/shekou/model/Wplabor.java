package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by think on 2015/11/3.
 * 计划员工
 */
public class Wplabor extends Entity implements Parcelable {
    private static final String TAG = "Wplabor";
    private static final long serialVersionUID = 2015050105L;

    public String craft; //工种
    public String skilllevel; //技能级别
    public String laborcode; //员工
    public String vendor; //供应商
    public String contractnum; //员工合同
    public String quantity; //数量
    public String laborhrs; //常规时数
    public String orgid; //组织
    public String siteid; //地点
    public String wplaborid;



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        craft = jsonObject.getString("craft");
        skilllevel = jsonObject.getString("skilllevel");
        laborcode = jsonObject.getString("laborcode");
        vendor = jsonObject.getString("vendor");
        contractnum = jsonObject.getString("contractnum");
        quantity = jsonObject.getString("quantity");
        laborhrs = jsonObject.getString("laborhrs");
        orgid = jsonObject.getString("orgid");
        siteid = jsonObject.getString("siteid");
        wplaborid = jsonObject.getString("wplaborid");
    }

    public Wplabor() {
    }


    private Wplabor(Parcel in) {
        craft = in.readString();
        skilllevel = in.readString();
        laborcode = in.readString();
        vendor = in.readString();
        contractnum = in.readString();
        quantity = in.readString();
        laborhrs = in.readString();
        orgid = in.readString();
        siteid = in.readString();
        wplaborid = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(craft);
        dest.writeString(skilllevel);
        dest.writeString(laborcode);
        dest.writeString(vendor);
        dest.writeString(contractnum);
        dest.writeString(quantity);
        dest.writeString(laborhrs);
        dest.writeString(orgid);
        dest.writeString(siteid);
        dest.writeString(wplaborid);
    }


    public static final Creator<Wplabor> CREATOR = new Creator<Wplabor>() {
        @Override
        public Wplabor createFromParcel(Parcel source) {
            return new Wplabor(source);
        }

        @Override
        public Wplabor[] newArray(int size) {
            return new Wplabor[size];
        }
    };

}
