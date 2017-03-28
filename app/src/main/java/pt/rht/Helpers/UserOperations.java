package pt.rht.Helpers;

/**
 * Created by Kitsao on 28/03/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import pt.rht.Models.User;


public class UserOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_NAME,
            DatabaseHelper.KEY_GENDER,
            DatabaseHelper.KEY_UID,
            DatabaseHelper.KEY_DOB,
            DatabaseHelper.KEY_MOBILE,
            DatabaseHelper.KEY_EMAIL,
            DatabaseHelper.KEY_USERNAME,
            DatabaseHelper.KEY_PASSWORD,
            DatabaseHelper.KEY_CREATED_AT,
            DatabaseHelper.KEY_UPDATED_AT

    };

    public UserOperations(Context context){
        dbhandler = new DatabaseHelper(context);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();


    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();

    }
    public User addUser(User user){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_NAME, user.getName());
        values.put(DatabaseHelper.KEY_GENDER, user.getGender());
        values.put(DatabaseHelper.KEY_UID, user.getUid());
        values.put(DatabaseHelper.KEY_DOB, user.getDob());
        values.put(DatabaseHelper.KEY_MOBILE, user.getMobile());
        values.put(DatabaseHelper.KEY_EMAIL, user.getEmail());
        values.put(DatabaseHelper.KEY_USERNAME, user.getUsername());
        values.put(DatabaseHelper.KEY_PASSWORD, user.getPassword());
        values.put(DatabaseHelper.KEY_CREATED_AT, user.getCreatedAt());
        values.put(DatabaseHelper.KEY_UPDATED_AT, user.getUpdatedAt());
        long insertId = database.insert(DatabaseHelper.TABLE_USER,null,values);
        user.setId(insertId);
        return user;

    }

    // Getting single User
    public User getUser(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_USER, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Long.parseLong(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
        // return Employee
        return user;
    }

    public List<User> getAllUsers() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_USER, allColumns, null, null, null, null, null);

        List<User> users = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_NAME)));
                user.setGender(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_GENDER))));
                user.setUid(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_UID))));
                user.setDob(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DOB)));
                user.setMobile(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_MOBILE)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_EMAIL)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PASSWORD)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_CREATED_AT)));
                user.setUpdatedAt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_UPDATED_AT)));
                users.add(user);
            }
        }
        // return All Users
        return users;
    }
    // Updating User
    public int updateUser(User user) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_NAME, user.getName());
        values.put(DatabaseHelper.KEY_GENDER, user.getGender());
        values.put(DatabaseHelper.KEY_UID, user.getUid());
        values.put(DatabaseHelper.KEY_DOB, user.getDob());
        values.put(DatabaseHelper.KEY_MOBILE, user.getMobile());
        values.put(DatabaseHelper.KEY_EMAIL, user.getEmail());
        values.put(DatabaseHelper.KEY_USERNAME, user.getUsername());
        values.put(DatabaseHelper.KEY_PASSWORD, user.getPassword());
        values.put(DatabaseHelper.KEY_CREATED_AT, user.getCreatedAt());
        values.put(DatabaseHelper.KEY_UPDATED_AT, user.getUpdatedAt());

        // updating row
        return database.update(DatabaseHelper.TABLE_USER, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(user.getId())});
    }

    // Deleting User
    public void removeUser(User user) {

        database.delete(DatabaseHelper.TABLE_USER, DatabaseHelper.KEY_ID + "=" + user.getId(), null);
    }
}