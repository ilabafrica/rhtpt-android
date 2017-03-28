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

import pt.rht.Models.Pt;


public class PtOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_RID,
            DatabaseHelper.KEY_CREATED_AT,
            DatabaseHelper.KEY_UPDATED_AT
    };

    public PtOperations(Context context){
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
    public Pt addPt(Pt pt){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_TID, pt.getrId());
        values.put(DatabaseHelper.KEY_CREATED_AT, pt.getCreatedAt());
        values.put(DatabaseHelper.KEY_UPDATED_AT, pt.getUpdatedAt());
        long insertId = database.insert(DatabaseHelper.TABLE_PT,null,values);
        pt.setId(insertId);
        return pt;

    }

    // Getting single Pt
    public Pt getPt(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_PT, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Pt pt = new Pt(Long.parseLong(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3));
        // return Pt
        return pt;
    }

    public List<Pt> getAllPts() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_PT, allColumns, null, null, null, null, null);

        List<Pt> pts = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Pt pt = new Pt();
                pt.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                pt.setrId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_RID))));
                pt.setCreatedAt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_CREATED_AT)));
                pt.setUpdatedAt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_UPDATED_AT)));
                pts.add(pt);
            }
        }
        // return All Pts
        return pts;
    }
    // Updating Pt
    public int updatePt(Pt pt) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TID, pt.getrId());
        values.put(DatabaseHelper.KEY_CREATED_AT, pt.getCreatedAt());
        values.put(DatabaseHelper.KEY_UPDATED_AT, pt.getUpdatedAt());

        // updating row
        return database.update(DatabaseHelper.TABLE_PT, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(pt.getId())});
    }

    // Deleting Pt
    public void removePt(Pt pt) {

        database.delete(DatabaseHelper.TABLE_PT, DatabaseHelper.KEY_ID + "=" + pt.getId(), null);
    }
}