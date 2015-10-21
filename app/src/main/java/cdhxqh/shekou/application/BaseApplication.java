package cdhxqh.shekou.application;

import android.app.Application;


/**
 * Created by think on 2015/8/11.
 */
public class BaseApplication extends Application {
    private String username;
    private static BaseApplication mContext;
    private String OrderResult;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderResult() {
        return OrderResult;
    }

    public void setOrderResult(String orderResult) {
        OrderResult = orderResult;
    }

    public static BaseApplication getInstance(){
        return mContext;
    }

//    public AndroidClientService getWsService() {
//        return new AndroidClientService(Constants.getWsUrl(this));
//    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }


}
