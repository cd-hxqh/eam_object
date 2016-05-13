package cdhxqh.shekou.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;


import java.io.File;

import cdhxqh.shekou.config.Constants;

public class DataUtils {

    private static final String TAG="Utils111";
    public static boolean isSdCard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    public static String getFilePath(Context context) {
        boolean isSdCard = isSdCard();
        String path = null;
        if (isSdCard) {
            path = Constants.PATH_DB + context.getPackageName() + File.separator;
        } else {
            path = Constants.NOT_SDCARD_PATH_DB + context.getPackageName() + File.separator;
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String DB_NAME = path + Constants.TB_NAME;
        Log.i(TAG,"DB_NAME="+DB_NAME);

        return DB_NAME;
    }


    public static double getDirSize(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                double size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {
                double size = (double) file.length() / 1024 / 1024;
                return size;
            }
        } else {
            return 0.0;
        }
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
