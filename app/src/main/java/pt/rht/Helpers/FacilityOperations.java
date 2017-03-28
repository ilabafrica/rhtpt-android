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

import pt.rht.Models.Facility;


public class FacilityOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_NAME,
            DatabaseHelper.KEY_MFL,

    };

    public FacilityOperations(Context context){
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
    public Facility addFacility(Facility facility){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_NAME, facility.getName());
        values.put(DatabaseHelper.KEY_MFL, facility.getMfl());
        long insertId = database.insert(DatabaseHelper.TABLE_FACILITY,null,values);
        facility.setId(insertId);
        return facility;

    }

    // Getting single Facility
    public Facility getFacility(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_FACILITY, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Facility facility = new Facility(Long.parseLong(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        // return Facility
        return facility;
    }

    public List<Facility> getAllFacilities() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_FACILITY, allColumns, null, null, null, null, null);

        List<Facility> facilities = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Facility facility = new Facility();
                facility.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                facility.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_NAME)));
                facility.setMfl(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_MFL))));
                facilities.add(facility);
            }
        }
        // return All Facilities
        return facilities;
    }
    // Updating Facility
    public int updateFacility(Facility facility) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_NAME, facility.getName());
        values.put(DatabaseHelper.KEY_MFL, facility.getMfl());

        // updating row
        return database.update(DatabaseHelper.TABLE_FACILITY, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(facility.getId())});
    }

    // Deleting Facility
    public void removeFacility(Facility facility) {

        database.delete(DatabaseHelper.TABLE_FACILITY, DatabaseHelper.KEY_ID + "=" + facility.getId(), null);
    }
}