package cdhxqh.shekou.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 15/10/26.
 * ���������
 */
public class Wfassignment extends Entity implements Parcelable {
    private static final String TAG = "Wfassignment";
    private static final long serialVersionUID = 2015050105L;

    public int wfassignmentid; //wfassignmentid
    public String app; //Ӧ�ó���
    public String assigncode; //�ѷ����������Ա����
    public String assigncodedesc; //�������������
    public String assignstatus; //�������״̬
    public String description; //����
    public String ownerid; //�����߱�ʶ
    public String ownertable; //�����߱�
    public String processname; //����
    public String startdate; //��ʼ����



    @Override
    public void parse(JSONObject jsonObject) throws JSONException {
        wfassignmentid = jsonObject.getInt("wfassignmentid");
        app = jsonObject.getString("app");
        assigncode = jsonObject.getString("assigncode");
        assigncodedesc = jsonObject.getString("assigncodedesc");
        assignstatus = jsonObject.getString("assignstatus");
        description = jsonObject.getString("description");
        ownerid = jsonObject.getString("ownerid");
        ownertable = jsonObject.getString("ownertable");
        processname = jsonObject.getString("processname");
        startdate = jsonObject.getString("startdate");
    }

    public Wfassignment() {
    }


    private Wfassignment(Parcel in) {
        wfassignmentid = in.readInt();
        app = in.readString();
        assigncode = in.readString();
        assigncodedesc = in.readString();
        assignstatus = in.readString();
        description = in.readString();
        ownerid = in.readString();
        ownertable = in.readString();
        processname = in.readString();
        startdate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(wfassignmentid);
        dest.writeString(app);
        dest.writeString(assigncode);
        dest.writeString(assigncodedesc);
        dest.writeString(assignstatus);
        dest.writeString(description);
        dest.writeString(ownerid);
        dest.writeString(ownertable);
        dest.writeString(processname);
        dest.writeString(startdate);

    }


    public static final Creator<Wfassignment> CREATOR = new Creator<Wfassignment>() {
        @Override
        public Wfassignment createFromParcel(Parcel source) {
            return new Wfassignment(source);
        }

        @Override
        public Wfassignment[] newArray(int size) {
            return new Wfassignment[size];
        }
    };

}
