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

import pt.rht.Models.Result;


public class ResultOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_PTID,
            DatabaseHelper.KEY_FID,
            DatabaseHelper.KEY_RESPONSE,
            DatabaseHelper.KEY_COMMENT
    };

    public ResultOperations(Context context){
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
    public Result addResult(Result result){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_PTID, result.getPtId());
        values.put(DatabaseHelper.KEY_FID, result.getfId());
        values.put(DatabaseHelper.KEY_RESPONSE, result.getResponse());
        values.put(DatabaseHelper.KEY_COMMENT, result.getComment());
        long insertId = database.insert(DatabaseHelper.TABLE_RESULT,null,values);
        result.setId(insertId);
        return result;

    }

    // Getting single Result
    public Result getResult(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_RESULT, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Result pt = new Result(Long.parseLong(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
        // return Pt
        return pt;
    }

    public List<Result> getAllResults() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_RESULT, allColumns, null, null, null, null, null);

        List<Result> results = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Result result = new Result();
                result.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                result.setPtId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PTID))));
                result.setfId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FID))));
                result.setResponse(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_RESPONSE)));
                result.setComment(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_COMMENT)));
                results.add(result);
            }
        }
        // return All Results
        return results;
    }
    // Updating Result
    public int updatePt(Result result) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_PTID, result.getPtId());
        values.put(DatabaseHelper.KEY_FID, result.getfId());
        values.put(DatabaseHelper.KEY_RESPONSE, result.getResponse());
        values.put(DatabaseHelper.KEY_COMMENT, result.getComment());

        // updating row
        return database.update(DatabaseHelper.TABLE_RESULT, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(result.getId())});
    }

    // Deleting Result
    public void removePt(Result result) {

        database.delete(DatabaseHelper.TABLE_RESULT, DatabaseHelper.KEY_ID + "=" + result.getId(), null);
    }
}