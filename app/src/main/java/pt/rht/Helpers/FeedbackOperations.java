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

import pt.rht.Models.Feedback;


public class FeedbackOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_ROUND,
            DatabaseHelper.KEY_FEEDBACK,
            DatabaseHelper.KEY_COMMENT,
            DatabaseHelper.KEY_CREATED_AT,
    };

    public FeedbackOperations(Context context){
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
    public Feedback addFeedback(Feedback feedback){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_ROUND, feedback.getRound());
        values.put(DatabaseHelper.KEY_FEEDBACK, feedback.getFeedback());
        values.put(DatabaseHelper.KEY_COMMENT, feedback.getComment());
        values.put(DatabaseHelper.KEY_CREATED_AT, feedback.getCreatedAt());
        long insertId = database.insert(DatabaseHelper.TABLE_FEEDBACK,null,values);
        feedback.setId(insertId);
        return feedback;

    }

    // Getting single Feedback
    public Feedback getFeedback(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_FEEDBACK, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Feedback feedback = new Feedback(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return Feedback
        return feedback;
    }

    public List<Feedback> getAllFeedbacks() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_FEEDBACK, allColumns, null, null, null, null, null);

        List<Feedback> feedbacks = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Feedback feedback = new Feedback();
                feedback.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                feedback.setRound(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_ROUND)));
                feedback.setFeedback(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FEEDBACK)));
                feedback.setComment(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_COMMENT)));
                feedback.setCreatedAt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_CREATED_AT)));
                feedbacks.add(feedback);
            }
        }
        // return All Feedbacks
        return feedbacks;
    }
    // Updating Feedback
    public int updateFeedback(Feedback feedback) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_ROUND, feedback.getRound());
        values.put(DatabaseHelper.KEY_FEEDBACK, feedback.getFeedback());
        values.put(DatabaseHelper.KEY_COMMENT, feedback.getComment());
        values.put(DatabaseHelper.KEY_CREATED_AT, feedback.getCreatedAt());

        // updating row
        return database.update(DatabaseHelper.TABLE_PT, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(feedback.getId())});
    }

    // Deleting Feedback
    public void removeFeedback(Feedback feedback) {

        database.delete(DatabaseHelper.TABLE_FEEDBACK, DatabaseHelper.KEY_ID + "=" + feedback.getId(), null);
    }
}