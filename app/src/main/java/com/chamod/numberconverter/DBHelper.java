package com.chamod.numberconverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "User_DB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Users";
    private static final String ID_COL = "ID_No";
    private static final String NAME_COL = "Full_Name";
    private static final String EMAIL_COL = "Email_Address";
    private static final String PASSWORD_COL = "Password";
    private static final String CONFIRM_PASSWORD_COL = "Confirm_Password";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + EMAIL_COL + " TEXT UNIQUE, "
                + PASSWORD_COL + " TEXT, "
                + CONFIRM_PASSWORD_COL + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addUser(String fullName, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, fullName);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);
        values.put(CONFIRM_PASSWORD_COL, confirmPassword);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {EMAIL_COL};
        String selection = EMAIL_COL + "=? AND " + PASSWORD_COL + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
}