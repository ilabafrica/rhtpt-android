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

import pt.rht.Models.Set;


public class SetOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_TITLE,

    };

    public SetOperations(Context context){
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
    public Set addSet(Set set){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_TITLE, set.getTitle());
        long insertId = database.insert(DatabaseHelper.TABLE_SET,null,values);
        set.setId(insertId);
        return set;

    }

    // Getting single Set
    public Set getFailure(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_SET, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Set set = new Set(Long.parseLong(cursor.getString(0)), cursor.getString(1));
        // return Set
        return set;
    }

    public List<Set> getAllSets() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_SET, allColumns, null, null, null, null, null);

        List<Set> sets = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Set set = new Set();
                set.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                set.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TITLE)));
                sets.add(set);
            }
        }
        // return All Sets
        return sets;
    }
    // Updating Set
    public int updateSet(Set set) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TITLE, set.getTitle());

        // updating row
        return database.update(DatabaseHelper.TABLE_SET, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(set.getId())});
    }

    // Deleting Set
    public void removeSet(Set set) {

        database.delete(DatabaseHelper.TABLE_SET, DatabaseHelper.KEY_ID + "=" + set.getId(), null);
    }
}