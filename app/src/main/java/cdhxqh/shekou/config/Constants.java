package cdhxqh.shekou.config;

/**
 * Created by think on 2015/10/19.
 */
public class Constants {

    /**
     * �����ӿ�*
     */
    public static final String HTTP_API_URL = "http://121.35.242.172:7001/maximo/mobile/";

    /**
     * ��½URL*
     */

    public static final String SIGN_IN_URL = HTTP_API_URL + "system/login";


    /**ͨ�ýӿڲ�ѯ**/
    public static final String BASE_URL = HTTP_API_URL + "common/api";

    /**------------------���ݿ�������ã�����ʼ**/
    //���������appid
    public static final String WFASSIGNMENT_APPID="INBOX";

    //��������ı���
    public static final String WFASSIGNMENT_NAME="WFASSIGNMENT";





    public static final String USER_INFO = "userinfo";
    public static final String NAME_KEY = "name_key";
    public static final String PASS_KEY = "pass_key";
    public static final String ISREMENBER = "isRemenber";


    /**
     * �û���¼��ʶ--��ʼ*
     */
    public static final String LOGINSUCCESS = "USER-S-101"; //��¼�ɹ�

    public static final String CHANGEIMEI = "USER-S-104"; //��¼�ɹ�,��⵽�û������ֻ���¼

    public static final String USERNAMEERROR = "USER-E-100";//�û����������

    public static final String GETDATASUCCESS = "GLOBAL-S-0";//��ȡ���ݳɹ�


}
