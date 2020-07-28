package com.eclass.vaishnavism.divyadesamyatra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "divyadesamManager";
    private static final String TABLE_DIVYADESAM = "divyadesams";
    private static final String KEY_DIVYADESAM = "divyadesam";
    private static final String KEY_DATE_VISITED = "dateVisited";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_DIVYADESAM_TABLE = "CREATE TABLE " + TABLE_DIVYADESAM + "("
                +  KEY_DIVYADESAM + " TEXT PRIMARY KEY,"
                + KEY_DATE_VISITED + " TEXT" + ")";
        db.execSQL(CREATE_DIVYADESAM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIVYADESAM);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addDivyadesam(Divyadesam divyadesam) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DIVYADESAM, divyadesam.getDivyadesam()); // Contact Name
        values.put(KEY_DATE_VISITED, divyadesam.getDateVisited()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_DIVYADESAM, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Divyadesam getDivyadesam(String dd) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DIVYADESAM, new String[] {
                        KEY_DIVYADESAM, KEY_DATE_VISITED }, KEY_DIVYADESAM + "=?",
                new String[] { dd }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Divyadesam divyadesam = new Divyadesam(cursor.getString(0),
                cursor.getString(1));
        // return divyadesam
        return divyadesam;
    }


    // code to get all contacts in a list view
    public List<Divyadesam> getAllDivyadesam() {
        List<Divyadesam> divyadesamList = new ArrayList<Divyadesam>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DIVYADESAM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Divyadesam divyadesam = new Divyadesam();

                divyadesam.setDivyadesam(cursor.getString(0));
                divyadesam.setDateVisited(cursor.getString(1));
                // Adding contact to list
                divyadesamList.add(divyadesam);
            } while (cursor.moveToNext());
        }

        // return contact list
        return divyadesamList;
    }

    // code to update the single contact
    public int updateDivyadesam(Divyadesam contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DIVYADESAM, contact.getDivyadesam());
        values.put(KEY_DATE_VISITED, contact.getDateVisited());

        // updating row
        return db.update(TABLE_DIVYADESAM, values, KEY_DIVYADESAM + " = ?",
                new String[] { contact.getDivyadesam()});
    }

    // Deleting single contact
    public void deleteDivyadesam(Divyadesam divyadesam) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DIVYADESAM, KEY_DIVYADESAM + " = ?",
                new String[] { divyadesam.getDivyadesam() });
        db.close();
    }

    // Getting contacts Count
    public int getDivyadesamCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DIVYADESAM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
