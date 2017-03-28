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
    public static final String TABLE_USER = "users";
    public static final String TABLE_FACILITY = "facilities";
    public static final String TABLE_FAILURE = "failures";
    public static final String TABLE_DESIGNATION = "designation";
    public static final String TABLE_ENROL = "enrollments";
    public static final String TABLE_ROUND = "rounds";
    public static final String TABLE_PANEL = "panels";
    public static final String TABLE_SET = "sets";
    public static final String TABLE_FIELD = "fields";
    public static final String TABLE_OPTION = "options";
    public static final String TABLE_FIELD_OPTION = "field_options";
    public static final String TABLE_PT = "pt";
    public static final String TABLE_RESULT = "results";
    public static final String TABLE_FEEDBACK = "feedback";

    // Common column names
    public static final String KEY_ID = "id";
    public static final String KEY_CREATED_AT = "created_at";
    public static final String KEY_UPDATED_AT = "updated_at";
    public static final String KEY_TITLE = "title";
    public static final String KEY_RID = "rId";
    public static final String KEY_FID = "fId";
    public static final String KEY_COMMENT = "comment";

    // USERS Table - column names
    public static final String KEY_NAME = "name";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_UID = "uid";
    public static final String KEY_DOB = "dob";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    //public static final String KEY_CREATED_AT = "created_at";
    //public static final String KEY_UPDATED_AT = "updated_at";

    // FAILURES Table - column names
    //public static final String KEY_TITLE = "title";

    // DESIGNATION Table - column names
    //public static final String KEY_TITLE = "title";

    // ENROLLMENTS Table - column names
    public static final String KEY_TID = "tid";
    public static final String KEY_FACILITY = "facility";
    public static final String KEY_MFL = "mfl";
    public static final String KEY_PROGRAM = "program";
    public static final String KEY_DESIGNATION = "designation";
    public static final String KEY_FAILURE = "failure";
    //public static final String KEY_COMMENT = "comment";

    // ROUNDS Table - column names
    //public static final String KEY_TITLE = "title";
    public static final String KEY_STARTS = "starts";
    public static final String KEY_ENDS = "ends";

    // PANELS Table - column names
    //public static final String KEY_RID = "rId";
    public static final String KEY_RDATE = "rDate";
    public static final String KEY_PANELS = "panels";
    public static final String KEY_RECEIVER = "receiver";

    // SETS Table - column names
    //public static final String KEY_TITLE = "title";

    // FIELDS Table - column names
    public static final String KEY_SID = "sId";
    //public static final String KEY_TITLE = "title";
    public static final String KEY_TAG = "tag";

    // OPTIONS Table - column names
    //public static final String KEY_TITLE = "title";

    // FIELD-OPTIONS Table - column names
    //public static final String KEY_FID = "fId";
    public static final String KEY_OID = "oId";

    // PT Table - column names
    //public static final String KEY_RID = "rId";
    //public static final String KEY_CREATED_AT = "created_at";
    //public static final String KEY_UPDATED_AT = "updated_at";
    public static final String KEY_STATUS = "status";

    // RESULTS Table - column names
    public static final String KEY_PTID = "ptId";
    //public static final String KEY_FID = "fId";
    public static final String KEY_RESPONSE = "response";
    //public static final String KEY_COMMENT = "comment";

    // FEEDBACK Table - column names
    public static final String KEY_ROUND = "round";
    public static final String KEY_FEEDBACK = "feedback";
    //public static final String KEY_COMMENT = "comment";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_GENDER + " INTEGER," + KEY_UID + " TEXT," + KEY_DOB + " DATETIME,"
            + KEY_MOBILE + " TEXT," + KEY_EMAIL + " TEXT," + KEY_USERNAME + " TEXT,"
            + KEY_PASSWORD + " TEXT," + KEY_CREATED_AT + " DATETIME,"+ KEY_UPDATED_AT
            + " DATETIME" + ")";

    private static final String CREATE_TABLE_FACILITY = "CREATE TABLE "
            + TABLE_FACILITY + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT"
            + KEY_MFL + " INTEGER" + ")";

    private static final String CREATE_TABLE_FAILURE = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT" + ")";

    private static final String CREATE_TABLE_DESIGNATION = "CREATE TABLE "
            + TABLE_DESIGNATION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT" + ")";

    private static final String CREATE_TABLE_ENROL = "CREATE TABLE "
            + TABLE_FAILURE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TID + " INTEGER,"
            + KEY_FACILITY + " TEXT," + KEY_MFL + " INTEGER," + KEY_PROGRAM + " INTEGER,"
            + KEY_DESIGNATION + " TEXT," + KEY_FAILURE + " INTEGER," + KEY_COMMENT + " TEXT"
            + KEY_CREATED_AT + " DATETIME,"+ KEY_UPDATED_AT + " DATETIME" + ")";

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
        db.execSQL(CREATE_TABLE_FACILITY);
        db.execSQL(CREATE_TABLE_FAILURE);
        db.execSQL(CREATE_TABLE_DESIGNATION);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DESIGNATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAILURE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACILITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // create new tables
        onCreate(db);
    }

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