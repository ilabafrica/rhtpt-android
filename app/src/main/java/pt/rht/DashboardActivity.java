package pt.rht;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import pt.rht.Session.Session;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        AlertDialog.Builder alert = new AlertDialog.Builder(DashboardActivity.this);
        alert.setMessage("There is no ongoing PT round. You will be notified when we're set.");
        alert.setPositiveButton("OK",
        new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int which) {
                Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
            }
        });
        if (id == R.id.nav_enrol) {
            //Toast.makeText(getApplicationContext(),"Enrollment",Toast.LENGTH_SHORT).show();
            alert.setTitle("Enrollments");
            alert.show();

        } else if (id == R.id.nav_panels) {
            alert.setTitle("Panels");
            alert.show();

        } else if (id == R.id.nav_tests) {
            alert.setTitle("Analysis Results");
            alert.show();

        } else if (id == R.id.nav_feedback) {
            alert.setTitle("Feedback");
            alert.show();

        } else if (id == R.id.nav_profile) {
            alert.setTitle("Profile");
            alert.show();

        } else if (id == R.id.nav_help) {
            alert.setTitle("Help");
            alert.show();

        } else if (id == R.id.nav_signout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void openActivity(int id) {
        switch (id) {
            case R.id.nav_enrol:
                //startActivity(new Intent(this, EnrolActivity.class));
                break;
            case R.id.nav_panels:
                //startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.nav_tests:
                //startActivity(new Intent(this, ContactActivity.class));
                break;
            case R.id.nav_feedback:
                //startActivity(new Intent(this, ContactActivity.class));
                break;
            case R.id.nav_profile:
                //startActivity(new Intent(this, ContactActivity.class));
                break;
            case R.id.nav_help:
                //sstartActivity(new Intent(this, ContactActivity.class));
                break;
            case R.id.nav_signout:
                //  Code for signing out the user to go here.
                break;
            default:
                break;
        }
    }
    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }
}
