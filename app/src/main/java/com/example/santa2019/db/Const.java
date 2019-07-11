package com.example.santa2019.db;

import android.os.Environment;

/**
 * Created by giapmn on 12/4/17.
 */

public class Const {
    public static final String TABLE_MY_RINGTONE = "MY_RINGTONE";
    public static final String KEY_ITEM_RINGTONE = "KEY_ITEM_RINGTONE";
    public static final String TABLE_FAVOURITE = "FAVOURITE";
    public static final int UN_FAVOURITE = 0;
    public static final int FAVOURITE = 1;

    public static final int TYPE_M4R = 1;
    public static final int TYPE_MP3 = 0;

    public static final String ACTION_NETWORK_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";

    public static final String FOLDER_RINGTONE = Environment.getExternalStorageDirectory().toString() + "/Ringtones";
    public static final String KEY_ITEM_RESULT_CONTACT = "KEY_ITEM_RESULT_CONTACT";
}
