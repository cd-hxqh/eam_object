package cdhxqh.shekou.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import cdhxqh.shekou.ui.widget.CumTimePickerDialog;

/**
 * Created by Administrator on 2016/5/30.
 */
public class DateSelect {
    //日期展示框
    TextView textView;
    //context
    Context context;

    private DatePickerDialog datePickerDialog;
    StringBuffer sb = new StringBuffer();

    public DateSelect(Context context, TextView textView) {
        this.textView = textView;
        this.context = context;
    }

    /**
     * 设置时间选择器*
     */
    private void setDataListener() {

        final Calendar objTime = Calendar.getInstance();
        int iYear = objTime.get(Calendar.YEAR);
        int iMonth = objTime.get(Calendar.MONTH);
        int iDay = objTime.get(Calendar.DAY_OF_MONTH);


        datePickerDialog = new DatePickerDialog(context, new datelistener(), iYear, iMonth, iDay);
    }

    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            sb = new StringBuffer();
            if (dayOfMonth < 10) {
                sb.append(year + "-" + (monthOfYear + 1) + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
            updateLabel(textView);
        }
    }

    public void showDialog() {
        setDataListener();
        datePickerDialog.show();
    }

    //更新页面TextView的方法
    private void updateLabel(View view) {
        ((TextView)view).setText(sb);
//        ((EditText)view).clearFocus();
//        ((EditText)view).setError(null);
    }
}
