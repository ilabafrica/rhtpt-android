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

import pt.rht.Models.Designation;


public class DesignationOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_TITLE,

    };

    public DesignationOperations(Context context){
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
    public Designation addDesignation(Designation designation){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_TITLE, designation.getTitle());
        long insertId = database.insert(DatabaseHelper.TABLE_DESIGNATION,null,values);
        designation.setId(insertId);
        return designation;

    }

    // Getting single Designation
    public Designation getDesignation(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_DESIGNATION, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Designation designation = new Designation(Long.parseLong(cursor.getString(0)), cursor.getString(1));
        // return designation
        return designation;
    }

    public List<Designation> getAllDesignations() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_DESIGNATION, allColumns, null, null, null, null, null);

        List<Designation> designations = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Designation designation = new Designation();
                designation.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                designation.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TITLE)));
                designations.add(designation);
            }
        }
        // return All Designations
        return designations;
    }
    // Updating Designation
    public int updateDesignation(Designation designation) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TITLE, designation.getTitle());

        // updating row
        return database.update(DatabaseHelper.TABLE_DESIGNATION, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(designation.getId())});
    }

    // Deleting Designation
    public void removeDesignation(Designation designation) {

        database.delete(DatabaseHelper.TABLE_DESIGNATION, DatabaseHelper.KEY_ID + "=" + designation.getId(), null);
    }
}