package sg.edu.rp.c346.id22011050.songlistpt2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 2;
    // Filename of the database
    private static final String DATABASE_NAME = "songs.db";
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS= "singer";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "star";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONG +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                +  COLUMN_SINGERS + " TEXT,"
                +  COLUMN_YEAR + " INT,"
                +  COLUMN_STARS + " INT )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        // Create table(s) again
        onCreate(db);
    }
    public ArrayList<String> getSongsTitle() {
        // Create an ArrayList that holds String objects
        ArrayList<String> songs = new ArrayList<String>();
        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS ,COLUMN_YEAR,COLUMN_STARS};
        // Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        // moveToFirst() moves to first row, null if no records
        if (cursor.moveToFirst()) {
            do {
                songs.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return songs;
    }
    public ArrayList<String> getSongsSinger() {
        // Create an ArrayList that holds String objects
        ArrayList<String> songs = new ArrayList<String>();
        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS ,COLUMN_YEAR,COLUMN_STARS};
        // Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        // moveToFirst() moves to first row, null if no records
        if (cursor.moveToFirst()) {
            do {
                songs.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return songs;
    }
    public ArrayList<String> getSongsYear() {
        // Create an ArrayList that holds String objects
        ArrayList<String> songs = new ArrayList<String>();
        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS ,COLUMN_YEAR,COLUMN_STARS};
        // Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        // moveToFirst() moves to first row, null if no records
        if (cursor.moveToFirst()) {
            do {
                songs.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return songs;
    }
    public ArrayList<String> getSongsStar() {
        // Create an ArrayList that holds String objects
        ArrayList<String> songs = new ArrayList<String>();
        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS ,COLUMN_YEAR,COLUMN_STARS};
        // Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        // moveToFirst() moves to first row, null if no records
        if (cursor.moveToFirst()) {
            do {
                songs.add(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return songs;
    }

    public void insertSong(String title, String singer,int year,int star){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singer);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, star);
        db.insert(TABLE_SONG, null, values);
        // Close the database connection
        db.close();
    }
    public ArrayList<Songs> getSongs() {
        ArrayList<Songs> Song = new ArrayList<Songs>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Songs obj = new Songs(id, title, singers, year, stars);
                Song.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Song;
    }
    public ArrayList<Songs> getAllSongs(String keyword) {
        ArrayList<Songs> songs = new ArrayList<Songs>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        String condition = COLUMN_STARS + " Like ?";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_SONG, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Songs obj = new Songs(id, title, singers, year, stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
    public int updateSong(Songs title, Songs singers, Songs year, Songs stars){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title.getTitle());
        values.put(COLUMN_SINGERS, singers.getSingers());
        values.put(COLUMN_YEAR, year.getYear());
        values.put(COLUMN_STARS, stars.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(title.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }
    public int deleteSongs(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }
}
