package pt.rht.Helpers;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import pt.rht.Models.User;
import pt.rht.Models.Failure;
import pt.rht.Models.Enrolment;
import pt.rht.Models.Round;
import pt.rht.Models.Panel;
import pt.rht.Models.Set;
import pt.rht.Models.Field;
import pt.rht.Models.Option;
import pt.rht.Models.Pt;
import pt.rht.Models.Result;
import pt.rht.Models.Feedback;

/**
 * Created by Kitsao on 23/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "rhtpt";

    // Table Names
    private static final String TABLE_USER = "users";
    private static final String TABLE_FAILURE = "failures";
    private static final String TABLE_ENROL = "enrollments";
    private static final String TABLE_ROUND = "rounds";
    private static final String TABLE_PANEL = "panels";
    private static final String TABLE_SET = "sets";
    private static final String TABLE_FIELD = "fields";
    private static final String TABLE_OPTION = "options";
    private static final String TABLE_FIELD_OPTION = "field_options";
    private static final String TABLE_PT = "pt";
    private static final String TABLE_RESULT = "results";
    private static final String TABLE_FEEDBACK = "feedback";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_UPDATED_AT = "updated_at";
    private static final String KEY_TITLE = "title";
    private static final String KEY_RID = "rId";
    private static final String KEY_FID = "fId";
    private static final String KEY_COMMENT = "comment";

    // USERS Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_UID = "uid";
    private static final String KEY_DOB = "dob";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    //private static final String KEY_CREATED_AT = "created_at";
    //private static final String KEY_UPDATED_AT = "updated_at";

    // FAILURES Table - column names
    //private static final String KEY_TITLE = "title";

    // ENROLLMENTS Table - column names
    private static final String KEY_FACILITY = "facility";
    private static final String KEY_MFL = "mfl";
    private static final String KEY_PROGRAM = "program";
    private static final String KEY_DESIGNATION = "designation";
    private static final String KEY_FAILURE = "failure";
    //private static final String KEY_COMMENT = "comment";

    // ROUNDS Table - column names
    //private static final String KEY_TITLE = "title";
    private static final String KEY_STARTS = "starts";
    private static final String KEY_ENDS = "ends";

    // PANELS Table - column names
    //private static final String KEY_RID = "rId";
    private static final String KEY_RDATE = "rDate";
    private static final String KEY_PANELS = "panels";
    private static final String KEY_RECEIVER = "receiver";

    // SETS Table - column names
    //private static final String KEY_TITLE = "title";

    // FIELDS Table - column names
    private static final String KEY_SID = "sId";
    //private static final String KEY_TITLE = "title";
    private static final String KEY_TAG = "tag";

    // OPTIONS Table - column names
    //private static final String KEY_TITLE = "title";

    // FIELD-OPTIONS Table - column names
    //private static final String KEY_FID = "fId";
    private static final String KEY_OID = "oId";

    // PT Table - column names
    //private static final String KEY_RID = "rId";
    //private static final String KEY_CREATED_AT = "created_at";
    //private static final String KEY_UPDATED_AT = "updated_at";
    private static final String KEY_STATUS = "status";

    // RESULTS Table - column names
    private static final String KEY_PTID = "ptId";
    //private static final String KEY_FID = "fId";
    private static final String KEY_RESPONSE = "response";
    //private static final String KEY_COMMENT = "comment";

    // FEEDBACK Table - column names
    private static final String KEY_ROUND = "round";
    private static final String KEY_FEEDBACK = "feedback";
    //private static final String KEY_COMMENT = "comment";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_GENDER + " INTEGER," + KEY_UID + " TEXT," + KEY_DOB + " DATETIME,"
            + KEY_MOBILE + " TEXT," + KEY_EMAIL + " TEXT," + KEY_USERNAME + " TEXT,"
            + KEY_PASSWORD + " TEXT," + KEY_CREATED_AT + " DATETIME,"+ KEY_UPDATED_AT
            + " DATETIME" + ")";

    private static final String CREATE_TABLE_FAILURE = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT" + ")";

    private static final String CREATE_TABLE_ENROL = "CREATE TABLE "
            + TABLE_FAILURE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FACILITY + " TEXT,"
            + KEY_MFL + " INTEGER," + KEY_PROGRAM + " TEXT," + KEY_DESIGNATION + " TEXT,"
            + KEY_FAILURE + " TEXT," + KEY_COMMENT + " TEXT" + ")";

    private static final String CREATE_TABLE_ROUND = "CREATE TABLE "
            + TABLE_ROUND + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT"
            + KEY_STARTS + " DATETIME," + KEY_ENDS + " DATETIME" + ")";

    private static final String CREATE_TABLE_PANEL = "CREATE TABLE "
            + TABLE_PANEL + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_RID + " INTEGER,"
            + KEY_RDATE + " DATE," + KEY_PANELS + " INTEGER," + KEY_RECEIVER + " TEXT" + ")";

    private static final String CREATE_TABLE_SET = "CREATE TABLE "
            + TABLE_SET + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT" + ")";

    private static final String CREATE_TABLE_FIELD = "CREATE TABLE "
            + TABLE_FIELD + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SID + " INTEGER"
            + KEY_TITLE + " INTEGER," + KEY_TAG + " INTEGER" + ")";

    private static final String CREATE_TABLE_OPTION = "CREATE TABLE "
            + TABLE_OPTION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT"
            + KEY_TITLE + " INTEGER," + KEY_TAG + " INTEGER" + ")";

    private static final String CREATE_TABLE_FIELD_OPTION = "CREATE TABLE "
            + TABLE_FIELD_OPTION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FID + " INTEGER"
            + KEY_OID + " INTEGER" + ")";

    private static final String CREATE_TABLE_PT = "CREATE TABLE "
            + TABLE_PT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_RID + " INTEGER"
            + KEY_CREATED_AT + " DATETIME,"+ KEY_UPDATED_AT + " DATETIME" + ")";

    private static final String CREATE_TABLE_RESULT = "CREATE TABLE "
            + TABLE_RESULT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PTID + " INTEGER"
            + KEY_FID + " INTEGER,"+ KEY_RESPONSE + " TEXT" + KEY_COMMENT + " TEXT" + ")";

    private static final String CREATE_TABLE_FEEDBACK = "CREATE TABLE "
            + TABLE_FEEDBACK + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ROUND + " TEXT"
            + KEY_FEEDBACK + " TEXT,"+ KEY_COMMENT + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_FAILURE);
        db.execSQL(CREATE_TABLE_ENROL);
        db.execSQL(CREATE_TABLE_ROUND);
        db.execSQL(CREATE_TABLE_PANEL);
        db.execSQL(CREATE_TABLE_SET);
        db.execSQL(CREATE_TABLE_FIELD);
        db.execSQL(CREATE_TABLE_OPTION);
        db.execSQL(CREATE_TABLE_FIELD_OPTION);
        db.execSQL(CREATE_TABLE_PT);
        db.execSQL(CREATE_TABLE_RESULT);
        db.execSQL(CREATE_TABLE_FEEDBACK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIELD_OPTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIELD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENROL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAILURE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // create new tables
        onCreate(db);
    }
    // ------------------------ begin "users" table methods ----------------//

    /**
     * Creating a user
     */
    // ------------------------ begin "failure" table methods ----------------//

    /**
     * get single failure
     */
    public Failure getFailure(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_FAILURE + " WHERE "
                + KEY_ID + " = " + id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Failure failure = new Failure();
        failure.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        failure.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));

        return failure;
    }

    /**
     * getting all failures
     * */
    public List<Failure> getAllFailures() {
        List<Failure> failures = new ArrayList<Failure>();
        String selectQuery = "SELECT  * FROM " + TABLE_FAILURE;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Failure failure = new Failure();
                failure.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                failure.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));

                // adding to failures list
                failures.add(failure);
            } while (c.moveToNext());
        }

        return failures;
    }

    /**
     * getting failure count
     */
    public int getFailureCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FAILURE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating a failure

    public int updateFailure(Failure failure) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, failure.getId());
        values.put(KEY_TITLE, failure.getTitle());

        // updating row
        return db.update(TABLE_FAILURE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(failure.getId()) });
    } */

    /**
     * Deleting a failure

    public void deleteFailure(long fId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAILURE, KEY_ID + " = ?",
                new String[] { String.valueOf(fId) });
    } */

    // ------------------------ end "failure" table methods ----------------//

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}