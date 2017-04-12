package pt.rht.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.lang.reflect.AccessibleObject;
import java.util.Date;
import java.util.GregorianCalendar;

import pt.rht.R;

/**
 * Created by Kitsao on 12/04/2017.
 */
public class DatePickerFragment extends DialogFragment {

    private DatePicker datePicker;
    private static final int REQUEST_DATE = 1;

    public interface DateDialogListener {
        void onFinishDialog(Date date);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);

        datePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.prompt_dob)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int year = datePicker.getYear();
                                int mon = datePicker.getMonth();
                                int day = datePicker.getDayOfMonth();
                                Date date = new GregorianCalendar(year,mon,day).getTime();
                                DateDialogListener activity = (DateDialogListener) getActivity();
                                activity.onFinishDialog(date);
                                dismiss();
                            }
                        })
                .create();
    }
}