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
    public String invuseid; //唯一ID
    public String invusenum; //领料单号
    public String description; //描述
    public String fromstoreloc; //库房
    public String loc_description; //库房名称
    public String wonum; //工单
    public String workorder_description; //工单描述
    public String udissueto; //领料人
    public String llr_displayname; //领料人名称
    public String uddept; //部门
    public String uddept_description; //部门名称
    public String udjbr; //物管员经办人
    public String udjbr_displayname; //物管员经办人（中文）
    public String wonum_asset_assetnum; //工单设备
    public String eq1; //设备管理组
    public String eq2; //设备管理室
    public String eq3; //设备管理班组
    public String udisjj; //是否紧急
    public String status; //状态
    public String siteid; //地点
    public String totalcost_v; //总价
    public String sq_displayname; //申请人
    public String createdate; //申请日期
    public String pz_displayname; //批准人
    public String changedate; //批准日期




    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        invuseid = jsonObject.getString("invuseid");
        invusenum = jsonObject.getString("invusenum");
        description = jsonObject.getString("description");
        fromstoreloc = jsonObject.getString("fromstoreloc");
        loc_description = jsonObject.getString("loc_description");
        wonum = jsonObject.getString("wonum");
        workorder_description = jsonObject.getString("workorder_description");
        udissueto = jsonObject.getString("udissueto");
        llr_displayname = jsonObject.getString("llr_displayname");
        uddept = jsonObject.getString("uddept");
        uddept_description = jsonObject.getString("uddept_description");
        udjbr = jsonObject.getString("udjbr");
        udjbr_displayname = jsonObject.getString("udjbr_displayname");
        wonum_asset_assetnum = jsonObject.getString("wonum_asset_assetnum");
        eq1 = jsonObject.getString("eq1");
        eq2 = jsonObject.getString("eq2");
        eq3 = jsonObject.getString("eq3");
        udisjj = jsonObject.getString("udisjj");
        status = jsonObject.getString("status");
        siteid = jsonObject.getString("siteid");
        totalcost_v = jsonObject.getString("totalcost_v");
        sq_displayname = jsonObject.getString("sq_displayname");
        createdate = jsonObject.getString("createdate");
        pz_displayname = jsonObject.getString("pz_displayname");
        changedate = jsonObject.getString("changedate");

    }

    public Invuse() {
    }


    private Invuse(Parcel in) {
        invuseid = in.readString();
        invusenum = in.readString();
        description = in.readString();
        fromstoreloc = in.readString();
        loc_description = in.readString();
        wonum = in.readString();
        workorder_description = in.readString();
        udissueto = in.readString();
        llr_displayname = in.readString();
        uddept = in.readString();
        uddept_description = in.readString();
        udjbr = in.readString();
        udjbr_displayname = in.readString();
        wonum_asset_assetnum = in.readString();
        eq1 = in.readString();
        eq2 = in.readString();
        eq3 = in.readString();
        udisjj = in.readString();
        status = in.readString();
        siteid = in.readString();
        totalcost_v = in.readString();
        sq_displayname = in.readString();
        createdate = in.readString();
        pz_displayname = in.readString();
        changedate = in.readString();




    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(invuseid);
        dest.writeString(invusenum);
        dest.writeString(description);
        dest.writeString(fromstoreloc);
        dest.writeString(loc_description);
        dest.writeString(wonum);
        dest.writeString(workorder_description);
        dest.writeString(udissueto);
        dest.writeString(llr_displayname);
        dest.writeString(uddept);
        dest.writeString(uddept_description);
        dest.writeString(udjbr);
        dest.writeString(udjbr_displayname);
        dest.writeString(wonum_asset_assetnum);
        dest.writeString(eq1);
        dest.writeString(eq2);
        dest.writeString(eq3);
        dest.writeString(udisjj);
        dest.writeString(status);
        dest.writeString(siteid);
        dest.writeString(totalcost_v);
        dest.writeString(sq_displayname);
        dest.writeString(createdate);
        dest.writeString(pz_displayname);
        dest.writeString(changedate);


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
