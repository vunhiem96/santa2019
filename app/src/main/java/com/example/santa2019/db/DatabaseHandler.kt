package com.example.santa2019.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.santa2019.Model.Image

import com.example.santa2019.Model.WallpaperFavorites

import java.util.ArrayList

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    /**
     * Getting All Contacts
     */

    // Select All Query
    // looping through all rows and adding to list
    // return contact list
    val allContacts: List<Image>
        get() {
            val contactList = ArrayList<Image>()
            val selectQuery = "SELECT  * FROM $TABLE_FAVOURITE"

            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val contact = Image()
                    contact.id = Integer.parseInt(cursor.getString(0))
                    contactList.add(contact)
                } while (cursor.moveToNext())
            }
            return contactList
        }

    //Create tables
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_CONTACTS = ("CREATE TABLE " + TABLE_FAVOURITE + "("
                + KEY_ID + " INTEGER PRIMARY KEY );")
        db.execSQL(CREATE_TABLE_CONTACTS)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FAVOURITE")

        // Create tables again
        onCreate(db)
    }

    fun addContacts(fv: Image) {
        val db = this.readableDatabase
        val values = ContentValues()

        values.put(KEY_NAME, fv.id)


        db.insert(TABLE_FAVOURITE, null, values)
        db.close()
    }

    /**
     * Deleting single contact
     */
    fun deleteContact(name: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_FAVOURITE, "$KEY_NAME = ?",
                arrayOf(name.toString()))
        Log.e("db", "đã xóa")
        val contacts = ArrayList(allContacts)
        for (i in contacts.indices) {
            Log.e("thanhdb", contacts.size.toString() + "")
        }
        db.close()
    }

    companion object {

        // Database Version
        private val DATABASE_VERSION = 1

        // Database Name
        private val DATABASE_NAME = "FavouriteManager"

        // Contacts table name
        private val TABLE_FAVOURITE = "Favouriter"

        // Contacts Table Columns names
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
    }
}
