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

import pt.rht.Models.Round;


public class RoundOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_TITLE,

    };

    public RoundOperations(Context context){
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
    public Round addRound(Round round){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_TITLE, round.getTitle());
        values.put(DatabaseHelper.KEY_STARTS, round.getStarts());
        values.put(DatabaseHelper.KEY_ENDS, round.getEnds());
        long insertId = database.insert(DatabaseHelper.TABLE_ROUND,null,values);
        round.setId(insertId);
        return round;

    }

    // Getting single Round
    public Round getRound(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_ROUND, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Round round = new Round(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return round
        return round;
    }

    public List<Round> getAllRounds() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_ROUND, allColumns, null, null, null, null, null);

        List<Round> rounds = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Round round = new Round();
                round.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                round.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TITLE)));
                round.setStarts(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_STARTS)));
                round.setEnds(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_ENDS)));
                rounds.add(round);
            }
        }
        // return All Rounds
        return rounds;
    }
    // Updating Round
    public int updateRound(Round round) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TITLE, round.getTitle());
        values.put(DatabaseHelper.KEY_STARTS, round.getStarts());
        values.put(DatabaseHelper.KEY_ENDS, round.getEnds());

        // updating row
        return database.update(DatabaseHelper.TABLE_ROUND, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(round.getId())});
    }

    // Deleting Option
    public void removeOption(Round round) {

        database.delete(DatabaseHelper.TABLE_ROUND, DatabaseHelper.KEY_ID + "=" + round.getId(), null);
    }
}