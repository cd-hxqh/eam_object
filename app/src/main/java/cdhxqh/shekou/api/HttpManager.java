package cdhxqh.shekou.api;


import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import cdhxqh.shekou.application.BaseApplication;
import cdhxqh.shekou.config.Constants;

/**
 * Created by apple on 15/5/27.
 */
public class HttpManager {

    private static BaseApplication mApp = BaseApplication.getInstance();
    private static AsyncHttpClient sClient = null;
    private static final String TAG = "ImManager";


    /**
     * 使用用户名密码登录
     *
     * @param cxt
     * @param username 用户名
     * @param password 密码
     * @param imei     密码
     * @param handler  返回结果处理
     */
    public static void loginWithUsername(final Context cxt, final String username, final String password, String imei,
                                         final HttpRequestHandler<String> handler) {


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("loginid", username);
        params.put("password", password);
        params.put("imei", imei);
        client.post(Constants.SIGN_IN_URL, params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                SafeHandler.onFailure(handler, IMErrorType.errorMessage(cxt, IMErrorType.ErrorLoginFailure));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "SstatusCode=" + statusCode + "responseString=" + responseString);
                if (statusCode == 200) {
                    String errmsg = JsonUtils.parsingAuthStr(cxt, responseString);
                    SafeHandler.onSuccess(handler, errmsg);
                }
            }
        });


    }


//    /**
//     * 不分页获取信息方法*
//     */
//    public static void getData(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams params = new RequestParams();
//        params.put("data", data);
//        client.get(Constants.BASE_URL, params, new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//
//                Results result = JsonUtils.parsingResults1(cxt, responseString);
//
//                SafeHandler.onSuccess(handler, result, result.getCurpage(), result.getShowcount());
//
//            }
//        });
//    }
//
//
//    /**
//     * 解析返回的结果--分页*
//     */
//    public static void getDataPagingInfo(final Context cxt, String data, final HttpRequestHandler<Results> handler) {
//        Log.i(TAG, "data=" + data);
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams params = new RequestParams();
//        params.put("data", data);
//        client.get(Constants.BASE_URL, params, new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                SafeHandler.onFailure(handler, cxt.getString(R.string.get_data_info_fail));
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                Log.i(TAG, "statusCode");
//                Results result = JsonUtils.parsingResults(cxt, responseString);
//
//                SafeHandler.onSuccess(handler, result, result.getCurpage(), result.getShowcount());
//            }
//        });
//    }


}
