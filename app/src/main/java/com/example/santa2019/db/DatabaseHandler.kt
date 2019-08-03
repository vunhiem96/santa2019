package com.example.santa2019.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.santa2019.Model.Image
import com.example.santa2019.Model.Ring
import com.example.santa2019.Model.RingFa

import com.example.santa2019.Model.WallpaperFavorites

import java.util.ArrayList

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    /**
     * Getting All Contacts
     */

    // Select All Query
    // looping through all rows and adding to list
    // return contact list
    val allContacts: List<RingFa>
        get() {
            val contactList = ArrayList<RingFa>()
            val selectQuery = "SELECT  * FROM $TABLE_FAVOURITE"

            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val contact = RingFa()
                    contact.id = Integer.parseInt(cursor.getString(0))
                    contact.title = cursor.getString(1)
                } while (cursor.moveToNext())
            }
            return contactList
        }

    //Create tables
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_CONTACTS = ("CREATE TABLE " + TABLE_FAVOURITE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_NAME TEXT );")
        db.execSQL(CREATE_TABLE_CONTACTS)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FAVOURITE")

        // Create tables again
        onCreate(db)
    }

    fun addContacts(fv: RingFa) {
        val db = this.readableDatabase
        val values = ContentValues()

        values.put(KEY_NAME, fv.title)


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
        private val DATABASE_NAME = "FavouriteManager1"

        // Contacts table name
        private val TABLE_FAVOURITE = "Favouriter"

        // Contacts Table Columns names
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
    }
}
