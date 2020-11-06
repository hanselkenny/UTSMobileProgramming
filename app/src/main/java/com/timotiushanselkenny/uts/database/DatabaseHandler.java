package com.timotiushanselkenny.uts.database;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "UTS";

    // table name
    private static final String TABLE_TALL = "TrDrink";

    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_QUANTITY = "quantity";
    private static DatabaseHandler sInstance;

    // ...

    public static synchronized DatabaseHandler getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }
    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TALL + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"+ KEY_PRICE + " TEXT,"
                + KEY_QUANTITY + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // on Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TALL);
        onCreate(db);
    }
    public void addRecord(DrinkTransaction userModels){
        SQLiteDatabase db  = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, userModels.getName());
        values.put(KEY_PRICE, userModels.getPrice());
        values.put(KEY_QUANTITY, userModels.getQuantity());

        db.insert(TABLE_TALL, null, values);
        db.close();
    }
    public DrinkTransaction getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TALL, new String[] { KEY_ID,
                        KEY_NAME, KEY_PRICE,KEY_QUANTITY }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        DrinkTransaction contact = new DrinkTransaction(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)));
        // return contact
        return contact;
    }
    // get All Record
    public List<DrinkTransaction> getAllRecord() {
        List<DrinkTransaction> contactList = new ArrayList<DrinkTransaction>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TALL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                DrinkTransaction userModels = new DrinkTransaction();
                userModels.setId(Integer.parseInt(cursor.getString(0)));
                userModels.setmName(cursor.getString(1));
                userModels.setmPrice(Integer.parseInt(cursor.getString(2)));
                userModels.setmQuantity(Integer.parseInt(cursor.getString(3)));

                contactList.add(userModels);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public int getUserModelCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TALL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    public int updateContact(DrinkTransaction contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PRICE, contact.getPrice());
        values.put(KEY_QUANTITY, contact.getQuantity());

        // updating row
        return db.update(TABLE_TALL, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }
    public void deleteModel(DrinkTransaction contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TALL, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }
}
