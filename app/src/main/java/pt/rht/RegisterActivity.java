package pt.rht;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View.OnClickListener;

import pt.rht.Fragments.DatePickerPreference;

public class RegisterActivity extends AppCompatActivity implements OnClickListener {
    private RadioGroup radioGroup;
    private RadioButton maleRadioButton,femaleRadioButton;
    private TextView dpDob;
    private EditText etName, etMob, etEmail, etFacility, etMfl;
    private Spinner spProg, spDes, spFailure;
    private Button btnLogin, btnReg;
    private String gender;

    private static final SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

    DatePickerDialog datePickerDialog;

    DatePickerPreference dobDatePickerPref;
    LinearLayout drawerPane;

    Calendar dateCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //  Set up the registration form
        etName = (EditText)findViewById(R.id.full_name);
        radioGroup = (RadioGroup) findViewById(R.id.radio_gender);
        maleRadioButton = (RadioButton) findViewById(R.id.radio_male);
        femaleRadioButton = (RadioButton) findViewById(R.id.radio_female);
        dpDob = (TextView) findViewById(R.id.dob);
        etMob = (EditText)findViewById(R.id.mobile_number);
        etEmail = (EditText)findViewById(R.id.email);
        etFacility = (EditText)findViewById(R.id.facility_name);
        spProg = (Spinner)findViewById(R.id.program);
        spDes = (Spinner)findViewById(R.id.designation);
        spFailure = (Spinner)findViewById(R.id.addressee_failure);
        btnLogin = (Button)findViewById(R.id.btnRegister);
        btnReg = (Button)findViewById(R.id.btnLinkToLoginScreen);

        spProg = (Spinner) findViewById(R.id.program);
        // Create an ArrayAdapter using the string array and a default spinner layoutß
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.programs_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spProg.setAdapter(adapter);

        spDes = (Spinner) findViewById(R.id.designation);
        // Create an ArrayAdapter using the string array and a default spinner layoutß
        ArrayAdapter<CharSequence> adapter_des = ArrayAdapter.createFromResource(this,
                R.array.designation_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_des.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spDes.setAdapter(adapter_des);

        spFailure = (Spinner) findViewById(R.id.addressee_failure);
        // Create an ArrayAdapter using the string array and a default spinner layoutß
        ArrayAdapter<CharSequence> adapter_failure = ArrayAdapter.createFromResource(this,
                R.array.failure_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_failure.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spFailure.setAdapter(adapter_failure);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            // find which radio button is selected
            if (checkedId == R.id.radio_male) {
                gender = "0";
            } else if (checkedId == R.id.radio_female) {
                gender = "1";
            }
            }

        });

        setListeners();

        //    For orientation change.
        if (savedInstanceState != null) {
            dateCalendar = Calendar.getInstance();
            if (savedInstanceState.getLong("dateCalendar") != 0)
                dateCalendar.setTime(new Date(savedInstanceState
                        .getLong("dateCalendar")));
            // Attached Listener

        }
    }
    private void setListeners() {
        dpDob.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(RegisterActivity.this,
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
//


                    dateCalendar = Calendar.getInstance();
                    dateCalendar.set(year, monthOfYear, dayOfMonth);
                    dpDob.setText(formatter.format(dateCalendar
                            .getTime()));

                    SharedPreferences dueDatePref = PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this);
                    SharedPreferences.Editor newDate = dueDatePref.edit();

                    newDate.putString("dueDate_pref", formatter.format(dateCalendar.getTime()));

                    // Commit the edits!
                    newDate.apply();
                }
                }, newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
        //submitbtn.setOnClickListener(this);
        //backHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == dpDob) {

            datePickerDialog.show();
        }
    }
}
