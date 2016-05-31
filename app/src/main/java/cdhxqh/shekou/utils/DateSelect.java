package cdhxqh.shekou.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
    private TimePickerDialog timePickerDialog;
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
        int hour = objTime.get(Calendar.HOUR_OF_DAY);

        int minute = objTime.get(Calendar.MINUTE);


        datePickerDialog = new DatePickerDialog(context, new datelistener(), iYear, iMonth, iDay);
        timePickerDialog = new CumTimePickerDialog(context, new timelistener(), hour, minute, true);
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
            timePickerDialog.show();
        }
    }

    private class timelistener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            sb.append(" ");
            if (i1 < 10) {
                sb.append(i + ":" + "0" + i1 + ":00");
            } else {
                sb.append(i + ":" + i1 + ":00");
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
