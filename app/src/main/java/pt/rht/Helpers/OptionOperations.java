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

import pt.rht.Models.Option;


public class OptionOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_TITLE,

    };

    public OptionOperations(Context context){
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
    public Option addOption(Option option){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_TITLE, option.getTitle());
        long insertId = database.insert(DatabaseHelper.TABLE_OPTION,null,values);
        option.setId(insertId);
        return option;

    }

    // Getting single Option
    public Option getOption(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_OPTION, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Option option = new Option(Long.parseLong(cursor.getString(0)), cursor.getString(1));
        // return Option
        return option;
    }

    public List<Option> getAllOptions() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_OPTION, allColumns, null, null, null, null, null);

        List<Option> options = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Option option = new Option();
                option.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                option.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TITLE)));
                options.add(option);
            }
        }
        // return All Options
        return options;
    }
    // Updating Option
    public int updateOption(Option option) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TITLE, option.getTitle());

        // updating row
        return database.update(DatabaseHelper.TABLE_OPTION, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(option.getId())});
    }

    // Deleting Option
    public void removeOption(Option option) {

        database.delete(DatabaseHelper.TABLE_OPTION, DatabaseHelper.KEY_ID + "=" + option.getId(), null);
    }
}