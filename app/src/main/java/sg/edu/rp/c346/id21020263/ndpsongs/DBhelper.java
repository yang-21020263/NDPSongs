package sg.edu.rp.c346.id21020263.ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NDPSongs.db";
    private static final int DATABASE_VERSION = 4;
    private static final String TABLE_NDPSONGS = "NDPSONGS";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGER = "singer";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATINGS = "ratings";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNDPSongsTableSQL = "CREATE TABLE " + TABLE_NDPSONGS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TITLE +
                " TEXT," + COLUMN_SINGER + " TEXT," + COLUMN_YEAR + " TEXT," +
                COLUMN_RATINGS + " TEXT ) ";
        db.execSQL(createNDPSongsTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertTitle(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        long result = db.insert(TABLE_NDPSONGS, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }

    public long insertSinger(String singer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SINGER, singer);
        long result = db.insert(TABLE_NDPSONGS, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }

    public long insertYear(String year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_YEAR, year);
        long result = db.insert(TABLE_NDPSONGS, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }

    public long insertRatings(String ratings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RATINGS, ratings);
        long result = db.insert(TABLE_NDPSONGS, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_RATINGS};
        Cursor cursor = db.query(TABLE_NDPSONGS, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                String year = cursor.getString(3);
                String ratings = cursor.getString(4);
                Song song = new Song(id, title, singers, year, ratings);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public int updateSong(Song song){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, song.getTitle());
        values.put(COLUMN_SINGER, song.getSingers());
        values.put(COLUMN_YEAR, song.getYear());
        values.put(COLUMN_RATINGS, song.getRatings());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(song.getId())};
        int result = db.update(TABLE_NDPSONGS, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NDPSONGS, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Song> getAllSongs(String keyword) {
        ArrayList<Song> songs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_RATINGS};
        String condition = COLUMN_RATINGS + " Like ?";
        String[] args = { "%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_NDPSONGS, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                String year = cursor.getString(3);
                String ratings = cursor.getString(4);
                Song song = new Song(id, title, singers, year, ratings);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }





}
