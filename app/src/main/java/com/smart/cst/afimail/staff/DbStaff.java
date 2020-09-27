package com.smart.cst.afimail.staff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbStaff extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "staffManager";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_DATA = "data";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_STUDID = "staffId";
    private static final String TABLE_CONTACTS = "staff";

    public DbStaff(Context paramContext) {
        super(paramContext, "staffManager", null, 1);
    }

    public void addData(String paramString1, String paramString2) {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("staffId", paramString1);
        localContentValues.put("data", paramString2);
        localContentValues.put("password", "");
        localSQLiteDatabase.insert("staff", null, localContentValues);
        localSQLiteDatabase.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
//        localSQLiteDatabase.delete("staff", null, null);
//        localSQLiteDatabase.close();
        db.execSQL("delete from "+ "staff");
        db.close();
    }

    public void deletestaffid(String paramString) {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        localSQLiteDatabase.delete("staff", "staffId = ?", new String[]{paramString});
        localSQLiteDatabase.close();
    }

    public List<ArrayList<String>> getAllData() {
        ArrayList localArrayList1 = new ArrayList();
        Cursor localCursor = getWritableDatabase().rawQuery("SELECT  * FROM staff", null);
        if (localCursor.moveToFirst())
            do {
                ArrayList localArrayList2 = new ArrayList();
                localArrayList2.add(localCursor.getString(0));
                localArrayList2.add(localCursor.getString(1));
                if (localCursor.getString(2) != null)
                    localArrayList2.add(localCursor.getString(2));
                localArrayList1.add(localArrayList2);
            }
            while (localCursor.moveToNext());
        return localArrayList1;
    }

    public int getCountBystaffid(String paramString) {
        String str = "SELECT  * FROM staff WHERE staffId='" + paramString + "'";
        Cursor localCursor = getReadableDatabase().rawQuery(str, null);
        int i = localCursor.getCount();
        localCursor.close();
        return i;
    }

    // Getting single contact
    public ArrayList<String> getDataBystaffid(String staffid) {

        SQLiteDatabase db = this.getReadableDatabase();

        final Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_STUDID,
                        KEY_DATA, KEY_PASSWORD}, KEY_STUDID + "=?",
                new String[]{staffid}, null, null, null, null);
        if (cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            return new ArrayList<String>() {{
                add(cursor.getString(0));
                add(cursor.getString(1));
                if (cursor.getString(2) != null) {
                    add(cursor.getString(2));
                }
            }};
        }
        return null;
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
        paramSQLiteDatabase.execSQL("CREATE TABLE staff(staffId TEXT,data TEXT,password TEXT)");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS staff");
        onCreate(paramSQLiteDatabase);
    }

    public int updatePassBystaffid(String paramString1, String paramString2) {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("password", paramString2);
        return localSQLiteDatabase.update("staff", localContentValues, "staffId = ?", new String[]{paramString1});
    }

    public int updatedataBystaffid(String paramString1, String paramString2) {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("staffId", paramString1);
        localContentValues.put("data", paramString2);
        return localSQLiteDatabase.update("staff", localContentValues, "staffId = ?", new String[]{paramString1});
    }
}

/* Location:           D:\Gopal\downloads\Apk decompile java\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     smart.breed.contest.db.DbStudent
 * JD-Core Version:    0.6.0
 */