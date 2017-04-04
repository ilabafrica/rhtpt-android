package pt.rht;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import pt.rht.Helpers.DatabaseHelper;
import pt.rht.Session.Session;

/**
 * A login screen that offers login via username/password.
 *
 * Created by Kitsao on 20/03/2017.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private EditText etUsername, etPass;
    private DatabaseHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        db = new DatabaseHelper(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPass = (EditText)findViewById(R.id.etPass);
        login.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }
    }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                login();
                break;
            default:

        }
    }
    private void login(){
        String username = etUsername.getText().toString();
        String pass = etPass.getText().toString();
        Toast.makeText(getApplicationContext(), pass,Toast.LENGTH_SHORT).show();

        if(username.equalsIgnoreCase("53212") && pass.equalsIgnoreCase("secret")){
            session.setLoggedin(true);
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Wrong username/password",Toast.LENGTH_SHORT).show();
        }
    }
}