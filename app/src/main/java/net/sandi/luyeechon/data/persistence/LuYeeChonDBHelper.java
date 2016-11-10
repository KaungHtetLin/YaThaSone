package net.sandi.luyeechon.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.sandi.luyeechon.data.persistence.LuYeeChonContract.*;

/**
 * Created by UNiQUE on 9/25/2016.
 */
public class LuYeeChonDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "luYeeChon.db";

    private static final String SQL_CREATE_HEALTH_TABLE = "CREATE TABLE " + HealthEntry.TABLE_NAME + " (" +
            HealthEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HealthEntry.COLUMN_TYPE + " TEXT NOT NULL, " +
            HealthEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            HealthEntry.COLUMN_PHOTO + " TEXT NOT NULL, " +
            HealthEntry.COLUMN_DESC + " TEXT NOT NULL, " +
            HealthEntry.COLUMN_FAV + " TEXT NOT NULL, " +

            " UNIQUE (" + HealthEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_JOKE_TABLE = "CREATE TABLE " + JokeEntry.TABLE_NAME + " (" +
            JokeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            JokeEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            JokeEntry.COLUMN_PHOTO + " TEXT NOT NULL, " +
            JokeEntry.COLUMN_DESC + " TEXT NOT NULL, " +
            JokeEntry.COLUMN_FAV + " TEXT NOT NULL, " +

            " UNIQUE (" + JokeEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_MOTIVATOR_TABLE = "CREATE TABLE " + MotivatorEntry.TABLE_NAME + " (" +
            MotivatorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MotivatorEntry.COLUMN_TITLE + " TEXT NOT NULL, " +

            " UNIQUE (" + MotivatorEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +  // can't duplicate
            " );";  //sql command

    private static final String SQL_CREATE_QUIZ_TABLE = "CREATE TABLE " + QuizEntry.TABLE_NAME + " (" +
            QuizEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuizEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            QuizEntry.COLUMN_ANSWER + " TEXT NOT NULL, " +
            QuizEntry.COLUMN_CONTAIN + " TEXT NOT NULL, " +

            " UNIQUE (" + QuizEntry.COLUMN_TITLE + "  ) ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_FAVOURITE_JOKE_TABLE = "CREATE TABLE " + FavouriteJokesEntry.TABLE_NAME + " (" +
            FavouriteJokesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FavouriteJokesEntry.COLUMN_TITLE + " TEXT NOT NULL, "+
            FavouriteJokesEntry.COLUMN_PHOTO + " TEXT NOT NULL, "+
            FavouriteJokesEntry.COLUMN_DES + " TEXT NOT NULL, "+
            FavouriteJokesEntry.COLUMN_FAV + " TEXT NOT NULL, "+

            " UNIQUE (" + FavouriteJokesEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +  // can't duplicate
            " );";

    private static final String SQL_CREATE_FAVOURITE_HEALTH_TABLE = "CREATE TABLE " + FavouriteHealthsEntry.TABLE_NAME + " (" +
            FavouriteHealthsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FavouriteHealthsEntry.COLUMN_TITLE + " TEXT NOT NULL, "+
            FavouriteHealthsEntry.COLUMN_PHOTO + " TEXT NOT NULL, "+
            FavouriteHealthsEntry.COLUMN_DES + " TEXT NOT NULL, "+
            FavouriteHealthsEntry.COLUMN_TYPE + " TEXT NOT NULL, "+
            FavouriteHealthsEntry.COLUMN_FAV + " TEXT NOT NULL, "+

            " UNIQUE (" + FavouriteHealthsEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +  // can't duplicate
            " );";
    public LuYeeChonDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_HEALTH_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_JOKE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_MOTIVATOR_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_QUIZ_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FAVOURITE_JOKE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FAVOURITE_HEALTH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HealthEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + JokeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MotivatorEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QuizEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavouriteJokesEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavouriteHealthsEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

}
