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

import pt.rht.Models.Panel;


public class PanelOperations {
    public static final String LOGTAG = "KENYA_RHT_PT";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_RID,
            DatabaseHelper.KEY_RDATE,
            DatabaseHelper.KEY_PANELS,
            DatabaseHelper.KEY_RECEIVER
    };

    public PanelOperations(Context context){
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
    public Panel addPanel(Panel panel){
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.KEY_RID, panel.getrId());
        values.put(DatabaseHelper.KEY_RDATE, panel.getrDate());
        values.put(DatabaseHelper.KEY_PANELS, panel.getPanels());
        values.put(DatabaseHelper.KEY_RECEIVER, panel.getReceiver());
        long insertId = database.insert(DatabaseHelper.TABLE_PANEL,null,values);
        panel.setId(insertId);
        return panel;

    }

    // Getting single Panel
    public Panel getPanel(long id) {

        Cursor cursor = database.query(DatabaseHelper.TABLE_PANEL, allColumns,DatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Panel panel = new Panel(Long.parseLong(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), Integer.parseInt(cursor.getString(3)), cursor.getString(4));
        // return Option
        return panel;
    }

    public List<Panel> getAllPanels() {

        Cursor cursor = database.query(DatabaseHelper.TABLE_PANEL, allColumns, null, null, null, null, null);

        List<Panel> panels = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Panel panel = new Panel();
                panel.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                panel.setrId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_RID))));
                panel.setrDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_RDATE)));
                panel.setPanels(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PANELS))));
                panel.setReceiver(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_RECEIVER)));
                panels.add(panel);
            }
        }
        // return All Panels
        return panels;
    }
    // Updating Panel
    public int updatePanel(Panel panel) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_RID, panel.getrId());
        values.put(DatabaseHelper.KEY_RDATE, panel.getrDate());
        values.put(DatabaseHelper.KEY_PANELS, panel.getPanels());
        values.put(DatabaseHelper.KEY_RECEIVER, panel.getReceiver());

        // updating row
        return database.update(DatabaseHelper.TABLE_PANEL, values,
                DatabaseHelper.KEY_ID + "=?",new String[] { String.valueOf(panel.getId())});
    }

    // Deleting Panel
    public void removePanel(Panel panel) {

        database.delete(DatabaseHelper.TABLE_PANEL, DatabaseHelper.KEY_ID + "=" + panel.getId(), null);
    }
}