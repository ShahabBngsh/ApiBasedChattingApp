package com.shahab.i180731_i180650;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHandler{
    myDbHelper helper;

    public DBHandler(Context context){
        helper = new myDbHelper(context);
    }

    public String getChatHistory()
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {myDbHelper.Column_ChatHistory_message, myDbHelper.Column_ChatHistory_time, myDbHelper.Column_ChatHistory_reciever, myDbHelper.Column_ChatHistory_sender};
        Cursor cursor =db.query(myDbHelper.Table_ChatHistory, columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid = cursor.getColumnIndex(myDbHelper.Column_ChatHistory_message);
            String  message = cursor.getString(cid);
            cid = cursor.getColumnIndex(myDbHelper.Column_ChatHistory_time);
            String  time = cursor.getString(cid);
            cid = cursor.getColumnIndex(myDbHelper.Column_ChatHistory_reciever);
            String  reciever = cursor.getString(cid);
            cid = cursor.getColumnIndex(myDbHelper.Column_ChatHistory_sender);
            String  sender = cursor.getString(cid);

            buffer.append(message + "," + time + "," + reciever + "," + sender + " \n");
        }
        return buffer.toString();
    }

    public long insertMessage(String message, String time, int reciever, int sender)
    {
        SQLiteDatabase dbb = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.Column_ChatHistory_message, message);
        contentValues.put(myDbHelper.Column_ChatHistory_time, time);
        contentValues.put(myDbHelper.Column_ChatHistory_reciever, reciever);
        contentValues.put(myDbHelper.Column_ChatHistory_sender, sender);
        long id = dbb.insert(myDbHelper.Table_ChatHistory, null , contentValues);
        return id;
    }

    static class myDbHelper extends SQLiteOpenHelper {

        private static final String DB_Name = "ChatAppDB";    // Database Name
        private static final int DB_Version = 1; // db version

        private static final String Table_Users = "Users";
        private static final String Table_CallHistory = "CallHistory";
        private static final String Table_ChatHistory = "ChatHistory";

        //users table
        private static final String Column_Users_pid = "pid";
        private static final String Column_Users_name = "name";
        private static final String Column_Users_status = "status";
        //call history
        private static final String Column_CallHistory_callid = "callid";
        private static final String Column_CallHistory_time = "time";
        private static final String Column_CallHistory_reciever = "reciever";
        private static final String Column_CallHistory_sender = "sender";
        //chat history
        private static final String Column_ChatHistory_chatid = "chatid";
        private static final String Column_ChatHistory_message = "message";
        private static final String Column_ChatHistory_time = "time";
        private static final String Column_ChatHistory_reciever = "reciever";
        private static final String Column_ChatHistory_sender = "sender";

        private static final String query1 = "CREATE TABLE " + Table_Users + " ("
                + Column_Users_pid + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Column_Users_name + " TEXT, "
                + Column_Users_status + "TEXT)";

        private static final String query2 = "CREATE TABLE " + Table_CallHistory + " ("
                + Column_CallHistory_callid + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Column_CallHistory_time + " TEXT, "
                + Column_CallHistory_reciever + " INTEGER, "
                + Column_CallHistory_sender + "INTEGER, "
                + " FOREIGN KEY (" + Column_CallHistory_reciever + ") REFERENCES " + Table_Users + "(" + Column_Users_pid + "),"
                + " FOREIGN KEY (" + Column_CallHistory_sender + ") REFERENCES " + Table_Users + "(" + Column_Users_pid + "))";

        private static final String query3 = "CREATE TABLE " + Table_ChatHistory + " ("
                + Column_ChatHistory_chatid + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Column_ChatHistory_message + " TEXT, "
                + Column_ChatHistory_time + " TEXT, "
                + Column_ChatHistory_reciever + " INTEGER, "
                + Column_ChatHistory_sender + "INTEGER, "
                + " FOREIGN KEY (" + Column_ChatHistory_reciever + ") REFERENCES " + Table_Users + "(" + Column_Users_pid + "),"
                + " FOREIGN KEY (" + Column_ChatHistory_sender + ") REFERENCES " + Table_Users + "(" + Column_Users_pid + "))";

        private static final String DROP_ALL_TABLES = "DROP TABLE IF EXISTS " + query1 + query2 + query3;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DB_Name, null, DB_Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                sqLiteDatabase.execSQL(query1);
                sqLiteDatabase.execSQL(query2);
                sqLiteDatabase.execSQL(query3);

            } catch (Exception e) {
                Toast.makeText(context, "ERROR" + e, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase Table_CallsqLiteDatabase, int i, int i1) {
            Table_CallsqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Users + Table_CallHistory + Table_ChatHistory);
            onCreate(Table_CallsqLiteDatabase);
        }
    }
}
