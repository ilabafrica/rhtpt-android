package pt.rht;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;
import android.util.Log;

import android.app.ProgressDialog;

import pt.rht.Helpers.DatabaseHelper;
import pt.rht.Session.Session;
import pt.rht.Helpers.UserOperations;
import pt.rht.Models.User;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import pt.rht.Api.Rest;
/**
 * A login screen that offers login via username/password.
 *
 * Created by Kitsao on 20/03/2017.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login, register;
    private EditText etUsername, etPass;
    private DatabaseHelper db;
    private Session session;
    private UserOperations userOps;
    private User user;

    private ProgressDialog pDialog;
    private static final String TAG = RegUserInfoActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        db = new DatabaseHelper(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnLinkToRegisterScreen);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPass = (EditText)findViewById(R.id.etPass);
        userOps = new UserOperations(this);
        user = new User();
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        if(session.loggedin()){
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }
    }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                String username = etUsername.getText().toString();
                String pass = etPass.getText().toString();
                login(username, pass);
                break;
            case R.id.btnLinkToRegisterScreen:
                register();
                break;
            default:
        }
    }

    /**
     * Function to authenticate user
     */
    private void login(final String username, final String password){
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();
        StringRequest strReq = new StringRequest(Method.POST,
                Rest.AUTH_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        session.setLoggedin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");

                        JSONObject usr = jObj.getJSONObject("user");
                        Log.d("", usr.getString("name"));
                        String name = usr.getString("name");
                        String email = usr.getString("email");
                        String created_at = usr.getString("created_at");

                        // Inserting row in users table
                        userOps.open();
                        user.setName("Kennedy Otis");
                        user.setGender(0);
                        user.setUid(10001);
                        user.setDob("24-04-1990");
                        user.setMobile("0722000000");
                        user.setEmail("info@strathmore.edu");
                        user.setUsername("10001");
                        user.setPassword("secret");
                        user.setCreatedAt("12-04-2017");
                        user.setUpdatedAt("12-04-2017");
                        userOps.addUser(user);
                        userOps.close();

                        // Launch dashboard activity
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        // Adding request to request queue
        RhtPT.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
    /**
     * Function to call register screen
     */
    private void register(){
        Intent i = new Intent(getApplicationContext(), RegUserInfoActivity.class);
        startActivity(i);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}