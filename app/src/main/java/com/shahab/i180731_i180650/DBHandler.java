package com.shahab.i180731_i180650;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DB_Version = 1;
    private static final String DB_Name = "ChatAppDB";
    //users table
    private static final String Table_Users = "Users";
    private static final String Column_Users_pid = "pid";
    private static final String Column_Users_name = "name";
    private static final String Column_Users_status = "status";
    //call history
    private static final String Table_CallHistory = "CallHistory";
    private static final String Column_CallHistory_callid = "callid";
    private static final String Column_CallHistory_time = "time";
    private static final String Column_CallHistory_reciever = "reciever";
    private static final String Column_CallHistory_sender = "sender";
    //chat history
    private static final String Table_ChatHistory = "ChatHistory";
    private static final String Column_ChatHistory_chatid = "chatid";
    private static final String Column_ChatHistory_message = "message";
    private static final String Column_ChatHistory_time = "time";
    private static final String Column_ChatHistory_reciever = "reciever";
    private static final String Column_ChatHistory_sender = "sender";


    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 = "CREATE TABLE " + Table_Users + " ("
                + Column_Users_pid + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Column_Users_name + " TEXT, "
                + Column_Users_status + "TEXT)";
        sqLiteDatabase.execSQL(query1);

        String query2 = "CREATE TABLE " + Table_CallHistory + " ("
                + Column_CallHistory_callid + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Column_CallHistory_time + " TEXT, "
                + Column_CallHistory_reciever + " INTEGER, "
                + Column_CallHistory_sender + "INTEGER, "
                + " FOREIGN KEY (" + Column_CallHistory_reciever + ") REFERENCES " + Table_Users + "(" + Column_Users_pid + "),"
                + " FOREIGN KEY (" + Column_CallHistory_sender + ") REFERENCES " + Table_Users + "(" + Column_Users_pid + "))";
        sqLiteDatabase.execSQL(query2);

        String query3 = "CREATE TABLE " + Table_ChatHistory + " ("
                + Column_ChatHistory_chatid + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Column_ChatHistory_message + " TEXT, "
                + Column_ChatHistory_time + " TEXT, "
                + Column_ChatHistory_reciever + " INTEGER, "
                + Column_ChatHistory_sender + "INTEGER, "
                + " FOREIGN KEY (" + Column_ChatHistory_reciever + ") REFERENCES " + Table_Users + "(" + Column_Users_pid + "),"
                + " FOREIGN KEY (" + Column_ChatHistory_sender + ") REFERENCES " + Table_Users + "(" + Column_Users_pid + "))";
        sqLiteDatabase.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase Table_CallsqLiteDatabase, int i, int i1) {
        Table_CallsqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Users + Table_CallHistory + Table_ChatHistory);
        onCreate(Table_CallsqLiteDatabase);
    }
}
