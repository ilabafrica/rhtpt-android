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

import pt.rht.Models.Failure;


public class FailureOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_TITLE,

    };

    public FailureOperations(Context context){
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
    public Failure addFailure(Failure failure){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_TITLE, failure.getTitle());
        long insertId = database.insert(DatabaseHelper.TABLE_FAILURE,null,values);
        failure.setId(insertId);
        return failure;

    }

    // Getting single failure
    public Failure getFailure(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_FAILURE, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Failure failure = new Failure(Long.parseLong(cursor.getString(0)), cursor.getString(1));
        // return failure
        return failure;
    }

    public List<Failure> getAllFailures() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_FAILURE, allColumns, null, null, null, null, null);

        List<Failure> failures = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Failure failure = new Failure();
                failure.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                failure.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TITLE)));
                failures.add(failure);
            }
        }
        // return All Failures
        return failures;
    }
    // Updating Failure
    public int updateFailure(Failure failure) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TITLE, failure.getTitle());

        // updating row
        return database.update(DatabaseHelper.TABLE_FAILURE, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(failure.getId())});
    }

    // Deleting Failure
    public void removeFailure(Failure failure) {

        database.delete(DatabaseHelper.TABLE_FAILURE, DatabaseHelper.KEY_ID + "=" + failure.getId(), null);
    }
}