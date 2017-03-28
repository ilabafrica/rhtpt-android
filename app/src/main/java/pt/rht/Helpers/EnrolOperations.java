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
import pt.rht.Models.Enrolment;


public class EnrolOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_TID,
            DatabaseHelper.KEY_FACILITY,
            DatabaseHelper.KEY_MFL,
            DatabaseHelper.KEY_PROGRAM,
            DatabaseHelper.KEY_DESIGNATION,
            DatabaseHelper.KEY_FID,
            DatabaseHelper.KEY_COMMENT,
            DatabaseHelper.KEY_CREATED_AT,
            DatabaseHelper.KEY_UPDATED_AT

    };

    public EnrolOperations(Context context){
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
    public Enrolment addEnrolment(Enrolment enrol){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_TID, enrol.getUserId());
        values.put(DatabaseHelper.KEY_FACILITY, enrol.getFacility());
        values.put(DatabaseHelper.KEY_MFL, enrol.getMfl());
        values.put(DatabaseHelper.KEY_PROGRAM, enrol.getProgram());
        values.put(DatabaseHelper.KEY_DESIGNATION, enrol.getDesignation());
        values.put(DatabaseHelper.KEY_FID, enrol.getAddFailure());
        values.put(DatabaseHelper.KEY_COMMENT, enrol.getComment());
        values.put(DatabaseHelper.KEY_CREATED_AT, enrol.getCreatedAt());
        values.put(DatabaseHelper.KEY_UPDATED_AT, enrol.getUpdatedAt());
        long insertId = database.insert(DatabaseHelper.TABLE_ENROL,null,values);
        enrol.setId(insertId);
        return enrol;

    }

    // Getting single Enrolment
    public Enrolment getEnrolment(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_ENROL, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Enrolment enrol = new Enrolment(Long.parseLong(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), cursor.getString(7), cursor.getString(8), cursor.getString(9));
        // return Enrolment
        return enrol;
    }

    public List<Enrolment> getAllEnrolments() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_ENROL, allColumns, null, null, null, null, null);

        List<Enrolment> enrols = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Enrolment enrol = new Enrolment();
                enrol.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                enrol.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TID))));
                enrol.setFacility(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FACILITY))));
                enrol.setMfl(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_MFL))));
                enrol.setProgram(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PROGRAM))));
                enrol.setDesignation(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DESIGNATION))));
                enrol.setAddFailure(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FAILURE))));
                enrol.setComment(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_COMMENT)));
                enrol.setCreatedAt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_CREATED_AT)));
                enrol.setUpdatedAt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_UPDATED_AT)));
                enrols.add(enrol);
            }
        }
        // return All Enrolments
        return enrols;
    }
    // Updating Enrolment
    public int updateEnrol(Enrolment enrol) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TID, enrol.getUserId());
        values.put(DatabaseHelper.KEY_FACILITY, enrol.getFacility());
        values.put(DatabaseHelper.KEY_MFL, enrol.getMfl());
        values.put(DatabaseHelper.KEY_PROGRAM, enrol.getProgram());
        values.put(DatabaseHelper.KEY_DESIGNATION, enrol.getDesignation());
        values.put(DatabaseHelper.KEY_FAILURE, enrol.getAddFailure());
        values.put(DatabaseHelper.KEY_COMMENT, enrol.getComment());
        values.put(DatabaseHelper.KEY_CREATED_AT, enrol.getCreatedAt());
        values.put(DatabaseHelper.KEY_UPDATED_AT, enrol.getUpdatedAt());

        // updating row
        return database.update(DatabaseHelper.TABLE_ENROL, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(enrol.getId())});
    }

    // Deleting Enrolment
    public void removeEnrol(Enrolment enrol) {

        database.delete(DatabaseHelper.TABLE_ENROL, DatabaseHelper.KEY_ID + "=" + enrol.getId(), null);
    }
}