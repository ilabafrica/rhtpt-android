package pt.rht.Helpers;

/**
 * Created by Kitsao on 28/03/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pt.rht.Models.Field;


public class FieldOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_SID,
            DatabaseHelper.KEY_TITLE,
            DatabaseHelper.KEY_TAG,
    };

    public FieldOperations(Context context){
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
    public Field addField(Field field){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_SID, field.getsId());
        values.put(DatabaseHelper.KEY_TITLE, field.getTitle());
        values.put(DatabaseHelper.KEY_TAG, field.getTag());
        long insertId = database.insert(DatabaseHelper.TABLE_FIELD,null,values);
        field.setId(insertId);
        return field;

    }

    // Getting single Field
    public Field getField(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_FIELD, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Field field = new Field(Long.parseLong(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
        // return field
        return field;
    }

    public List<Field> getAllFields() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_FIELD, allColumns, null, null, null, null, null);

        List<Field> fields = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Field field = new Field();
                field.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                field.setsId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_SID))));
                field.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TITLE)));
                field.setTag(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TAG))));
                fields.add(field);
            }
        }
        // return All fields
        return fields;
    }
    // Updating field
    public int updateField(Field field) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_SID, field.getsId());
        values.put(DatabaseHelper.KEY_TITLE, field.getTitle());
        values.put(DatabaseHelper.KEY_TAG, field.getTag());

        // updating row
        return database.update(DatabaseHelper.TABLE_FIELD, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(field.getId())});
    }

    // Deleting field
    public void removeField(Field field) {

        database.delete(DatabaseHelper.TABLE_FIELD, DatabaseHelper.KEY_ID + "=" + field.getId(), null);
    }
}