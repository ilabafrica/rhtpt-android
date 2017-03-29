package pt.rht;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;

import pt.rht.Session.Session;
import pt.rht.Helpers.DatabaseHelper;

/**
 * Created by Kitsao on 20/03/2017.
 */

public class SplashActivity extends AppCompatActivity {
    Session session;
    DatabaseHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}