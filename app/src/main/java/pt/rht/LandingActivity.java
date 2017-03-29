package pt.rht;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class LandingActivity extends AppCompatActivity {

    private TextView textRegister;
    private TextView textSignin;
    private TextView textQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        textRegister = (TextView) findViewById(R.id.text_register);
        textSignin = (TextView) findViewById(R.id.text_signin);
        textQuit = (TextView) findViewById(R.id.text_quit);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent = new Intent();
                    switch (item.getItemId()) {
                        case R.id.action_register:
                            textRegister.setVisibility(View.VISIBLE);
                            textSignin.setVisibility(View.GONE);
                            break;
                        case R.id.action_signin:
                            textSignin.setVisibility(View.GONE);
                            textRegister.setVisibility(View.VISIBLE);
                            break;
                        case R.id.action_quit:
                            textSignin.setVisibility(View.GONE);
                            textRegister.setVisibility(View.VISIBLE);
                            break;
                    }
                    return false;
                }
            });
    }
}