package pt.rht.Fragments;

/**
 * Date picker preference for settings on dates
 */
import android.content.Context;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import pt.rht.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DatePickerPreference extends DialogPreference {
    private DatePicker picker;
    long mMinDate;
    long mMaxDate;
    public void setMinDate(long minDate) {
        mMinDate = minDate;
    }

    public void setMaxDate(long maxDate) {
        mMaxDate = maxDate;
    }

    public DatePickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPositiveButtonText(R.string.set);
        setNegativeButtonText(R.string.cancel);
    }
    @Override
    protected View onCreateDialogView() {
        picker = new DatePicker(getContext());
        picker.setCalendarViewShown(false);
        picker.setMinDate(mMinDate);
        picker.setMaxDate(mMaxDate);

        return picker;
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {

            int lastYear = picker.getYear();
            int lastMonth = picker.getMonth();
            int lastDay = picker.getDayOfMonth();
        }
    }

}