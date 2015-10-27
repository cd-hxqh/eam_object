package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * ����
 */
public class Inventory extends Entity implements Parcelable {
    private static final String TAG = "Inventory";
    private static final long serialVersionUID = 2015050105L;

    public String avgcost; //ƽ����Ŀ�ɱ�
    public String curbal; //��ǰ����
    public String issueunit; //���ŵ�λ
    public String itemnum; //��Ŀ���
    public String lastcost; //��Ŀ�ɱ�
    public String location; //�ⷿ
    public String orgid; //��֯��ʶ
    public String siteid; //վ��
    public String stdcost; //��Ŀ�ɱ�



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
