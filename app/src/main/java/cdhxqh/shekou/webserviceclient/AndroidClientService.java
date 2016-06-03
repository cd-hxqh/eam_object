package cdhxqh.shekou.webserviceclient;

import android.content.Context;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.config.Constants;

/**
 * Created by think on 2015/8/11.
 */
public class AndroidClientService {
    private static final String TAG = "AndroidClientService";
    public static String NAMESPACE = "http://www.ibm.com/maximo";
    public static String url = "http://121.35.242.172:7001/meaweb/services/WORKORDERSERVICE";
    public int timeOut = 60000;

    public AndroidClientService() {
    }

    public AndroidClientService(String url) {
        this.url = url;
    }

    public void setTimeOut(int seconds) {
        this.timeOut = seconds * 1000;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    /**
     * 审批工作流
     *
     * @return
     */
    public static String approve(Context context, String processname, String mbo, String keyValue, String key, String zx, String desc) {


        String url = "http://121.35.242.172:7001/meaweb/services/WFMENAGEMENTSERVIC";

        Log.i(TAG,"url="+url);
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "wfmenagementservicwfGoOn");
        soapReq.addProperty("processname", processname);//工单：UDFJHWO，采购申请（含零星和集中采购风电场部分审批）：UDPR，集中汇总采购计划流程（分公司发起）：UDPRHZ
        soapReq.addProperty("mboName", mbo);//工单WORKORDER,采购申请pr
        soapReq.addProperty("keyValue", keyValue);//对应的表ID的值，如工单需要传送wonum的值，采购申请prnum的值
        soapReq.addProperty("key", key);//对应的表ID，如工单：wonum，采购申请，prnum
        soapReq.addProperty("zx", zx);//审批的结果，1为审批通过，0为审批不通过
        if (!desc.equals("")) {
            soapReq.addProperty("desc", desc);//审批意见
        }
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url, 120000);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            return null;
        } catch (XmlPullParserException e) {
            return null;
        }
        String obj = null;
        String result = null;
        try {
            obj = soapEnvelope.getResponse().toString();
            result = JsonUtils.parsingwfserviceGoOnResult(obj);
        } catch (SoapFault soapFault) {
            Log.i(TAG,"ssssss");
            return null;
        }
        return result;
    }








    /**
     * 测试方法
     *
     * @param s
     * @return
     */
    public String TestInsertWO(String s) {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "INV01RecByPO");
//        s= "{\n" +
//                "  \"wonum\": \"\",\n" +
//                "  \"description\": \"description\",\n" +
//                "  \"assetnum\": \"C-RT107\",\n" +
//                "  \"worktype\": \"PM\",\n" +
//                "  \"woeq1\": \"03\",\n" +
//                "  \"woeq2\": \"0302\",\n" +
//                "  \"woeq3\": \"030201\",\n" +
//                "  \"udisjj\": \"0\",\n" +
//                "  \"udisaq\": \"0\",\n" +
//                "  \"udiscb\": \"0\",\n" +
//                "  \"udisbx\": \"0\",\n" +
//                "  \"udcreateby\": \"CCTADMIN\",\n" +
//                "  \"jpnum\": \"B-RTTASK001H\",\n" +
//                "  \"pmnum\": \"C-RT107TASK001H\",\n" +
//                "  \"reportedby\": \"MAXADMIN\",\n" +
//                "  \"udyxj\": \"高\",\n" +
//                "  \"udtjsj\": \"\",\n" +
//                "  \"lead\": \"FQA00155\",\n" +
//                "  \"udremark\": \"测试\",\n" +
//                "  \"udprojapprnum\": \"\",\n" +
//                "  \"udbugnum\": \"\",\n" +
//                "  \"udisjf\": \"0\",\n" +
//                "  \"udassetbz\": \"0306\",\n" +
//                "  \"udevnum\": \"\",\n" +
//                "  \"udqxbz\": \"\",\n" +
//                "  \"supervisor\": \"\",\n" +
//                "  \"udsupervisor2\": \"\",\n" +
//                "  \"udworkmemo\": \"\",\n" +
//                "  \"udisyq\": \"0\",\n" +
//                "  \"failurecode\": \"\",\n" +
//                "  \"udgzlbdm\": \"\",\n" +
//                "  \"udcreatedate\": \"2016-04-26 01:02:19\",\n" +
//                "  \"udactstart\": \"\",\n" +
//                "  \"udactfinish\": \"\",\n" +
//                "  \"reportdate\": \"2016-04-26 01:02:19\",\n" +
//                "  \"targstartdate\": \"2016-05-08 00:00:00\",\n" +
//                "  \"targcompdate\": \"2016-05-08 00:00:00\",\n" +
//                "  \"status\": \"工单建立\",\n" +
//                "  \"statusdate\": \"2016-04-23 01:02:19\",\n" +
//                "  \"wotasks\": [\n" +
//                "    {\n" +
//                "      \"taskid\": \"10\",\n" +
//                "      \"description\": \"步骤1\",\n" +
//                "      \"wojo1\": \"B-RTTASK001H-000\",\n" +
//                "      \"wojo2\": \"1\",\n" +
//                "      \"udisdo\": \"0\",\n" +
//                "      \"udisyq\": \"0\",\n" +
//                "      \"udyqyy\": \"\",\n" +
//                "      \"udremark\": \"测试备注\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"taskid\": \"20\",\n" +
//                "      \"description\": \"步骤2\",\n" +
//                "      \"wojo1\": \"B-RTTASK001H-001\",\n" +
//                "      \"wojo2\": \"1\",\n" +
//                "      \"udisdo\": \"0\",\n" +
//                "      \"udisyq\": \"0\",\n" +
//                "      \"udyqyy\": \"\",\n" +
//                "      \"udremark\": \"测试备注\"\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"labtrans\": [\n" +
//                "    {\n" +
//                "      \"taskid\": \"\",\n" +
//                "      \"craft\": \"\",\n" +
//                "      \"skilllevel\": \"\",\n" +
//                "      \"laborcode\": \"\",\n" +
//                "      \"startdate\": \"\",\n" +
//                "      \"starttime\": \"\",\n" +
//                "      \"finishtime\": \"\",\n" +
//                "      \"regularhrs\": \"\",\n" +
//                "      \"payrate\": \"\",\n" +
//                "      \"linecost\": \"\",\n" +
//                "      \"assetnum\": \"C-RT107\",\n" +
//                "      \"transtype\": \"工作\"\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"failurereport\": [\n" +
//                "    {\n" +
//                "      \"failurecode\": \"\",\n" +
//                "      \"assetnum\": \"\",\n" +
//                "      \"type\": \"\",\n" +
//                "      \"linenum\": \"\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
        soapReq.addProperty("userid", "maxadmin");
        soapReq.addProperty("ponum", "12345");
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        String obj = null;
        try {
            obj = soapEnvelope.getResponse().toString();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return obj;
    }

    /**
     * 工单新增方法
     *
     * @param json
     * @return
     */
    public static String InsertWO(String json, String userid,String url) {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "workorderserviceInsertWO");
        soapReq.addProperty("json", json);
        soapReq.addProperty("userid",userid);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        String obj = null;
        try {
            obj = soapEnvelope.getResponse().toString();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return obj;
    }

    /**
     * 工单修改
     */
    public String UpdateWO(String json,String userid,String url) {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "workorderserviceUpdateWO");
        soapReq.addProperty("json", json);
        soapReq.addProperty("userid",userid);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        String obj = null;
        try {
            obj = soapEnvelope.getResponse().toString();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return obj;
    }

    /**
     * 工单删除
     */
    public String DeleteWO(String wonum,String userid,String url) {
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.implicitTypes = true;
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject(NAMESPACE, "workorderserviceDeleteWO");
        soapReq.addProperty("wonum", wonum);
        soapReq.addProperty("userid",userid);
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE(url);
        try {
            httpTransport.call("urn:action", soapEnvelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        String obj = null;
        try {
            obj = soapEnvelope.getResponse().toString();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }
        return obj;
    }
}
