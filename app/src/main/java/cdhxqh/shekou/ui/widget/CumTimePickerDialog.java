package cdhxqh.shekou.ui.widget;

import android.app.TimePickerDialog;
import android.content.Context;

/**
 * Created by apple on 15/9/17.
 */
public class CumTimePickerDialog extends TimePickerDialog {


    public CumTimePickerDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView) {
        super(context, callBack, hourOfDay, minute, is24HourView);
    }


    @Override
    protected void onStop() {
        //super.onStop();
    }
}
