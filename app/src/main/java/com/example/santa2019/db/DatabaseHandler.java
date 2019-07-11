package com.example.santa2019.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.santa2019.Model.WallpaperFavorites;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "FavouriteManager";

    // Contacts table name
    private static final String TABLE_FAVOURITE = "Favouriter";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CONTACTS="CREATE TABLE " + TABLE_FAVOURITE + "("
                + KEY_ID +" INTEGER PRIMARY KEY,"
                + KEY_NAME +"LONG" + ")";
        db.execSQL(CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURITE);

        // Create tables again
        onCreate(db);
    }

    public void addContacts(WallpaperFavorites fv){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values=new ContentValues();

        values.put(KEY_NAME, fv.getName() );


        db.insert(TABLE_FAVOURITE, null, values);
        db.close();
    }
    /**
     *Getting All Contacts
     **/

    public List<WallpaperFavorites> getAllContacts() {
        List<WallpaperFavorites> contactList = new ArrayList<WallpaperFavorites>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FAVOURITE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                WallpaperFavorites contact = new WallpaperFavorites();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(Integer.parseInt(cursor.getString(0)));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    /**
     *Deleting single contact
     **/
    public void deleteContact(int name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVOURITE, KEY_NAME + " = ?",
                new String[] {String.valueOf(name)});
        Log.e("db","đã xóa");
        ArrayList contacts = new ArrayList(getAllContacts());
        for (int i=0 ;i<contacts.size();i++){
            Log.e("thanhdb",contacts.size()+"");
        }
        db.close();
    }
}
