package pt.rht;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import pt.rht.Fragments.DatePickerFragment;

public class RegUserInfoActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener{
    private static final String DIALOG_DATE = "DialogDate";
    private ImageView calendarImage;
    private RadioGroup radioGroup;
    private RadioButton maleRadioButton,femaleRadioButton;
    private EditText etName;
    private EditText etDob;
    private EditText etMob;
    private EditText etEmail;
    private EditText etFacility;
    private EditText etMfl;
    private Spinner spProg;
    private Spinner spDes;
    private Spinner spFailure;
    private Button register;
    private Button login;
    private String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_user_info);
        //  Set up the registration form
        etName = (EditText)findViewById(R.id.full_name);
        radioGroup = (RadioGroup) findViewById(R.id.radio_gender);
        maleRadioButton = (RadioButton) findViewById(R.id.radio_male);
        femaleRadioButton = (RadioButton) findViewById(R.id.radio_female);
        etDob = (EditText)findViewById(R.id.dob);
        etMob = (EditText)findViewById(R.id.dob);
        etEmail = (EditText)findViewById(R.id.dob);
        etFacility = (EditText)findViewById(R.id.dob);
        spProg = (Spinner)findViewById(R.id.program);
        spDes = (Spinner)findViewById(R.id.designation);
        spFailure = (Spinner)findViewById(R.id.addressee_failure);
        login = (Button)findViewById(R.id.btnRegister);
        register = (Button)findViewById(R.id.btnLinkToLoginScreen);
        calendarImage = (ImageView)findViewById(R.id.image_view_hire_date);

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
        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager, DIALOG_DATE);
            }
        });

    }
    @Override
    public void onFinishDialog(Date date) {
        etDob.setText(formatDate(date));

    }

    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dob = sdf.format(date);
        return dob;
    }
}
