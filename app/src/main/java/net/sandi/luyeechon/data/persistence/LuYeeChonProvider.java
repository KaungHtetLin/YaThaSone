package net.sandi.luyeechon.data.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class LuYeeChonProvider extends ContentProvider {

    public static final int HEALTHS = 100;
    public static final int JOKES = 200;
    public static final int MOTIVATOR = 300;
    public static final int QUIZ = 400;
    public static final int FAV_JOKE = 500;
    public static final int FAV_HEALTH = 600;

    private static final String sHealthTitleSelection = LuYeeChonContract.HealthEntry.COLUMN_TITLE + " = ?";
    private static final String sJokeTitleSelection = LuYeeChonContract.JokeEntry.COLUMN_TITLE + " = ?";
    private static final String sAttractionTitleSelection = LuYeeChonContract.MotivatorEntry.COLUMN_TITLE + " = ?";
    private static final String sQuizTitleSelection = LuYeeChonContract.QuizEntry.COLUMN_TITLE + " = ?";
    private static final String sFavJokeTitleSelection = LuYeeChonContract.FavouriteJokesEntry.COLUMN_TITLE + " =?";
    private static final String sFavHealthTitleSelection = LuYeeChonContract.FavouriteHealthsEntry.COLUMN_TITLE + " =?";

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private LuYeeChonDBHelper mLuYeeChonDBHelper;

    @Override
    public boolean onCreate() {
        mLuYeeChonDBHelper = new LuYeeChonDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor queryCursor;

        int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {

            case HEALTHS:
                String healthTitle = LuYeeChonContract.HealthEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(healthTitle)) {
                    selection = sHealthTitleSelection;
                    selectionArgs = new String[]{healthTitle};
                }
                queryCursor = mLuYeeChonDBHelper.getReadableDatabase().query(LuYeeChonContract.HealthEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;
            case JOKES:
                String jokeTitle = LuYeeChonContract.JokeEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(jokeTitle)) {
                    selection = sJokeTitleSelection;
                    selectionArgs = new String[]{jokeTitle};
                }
                queryCursor = mLuYeeChonDBHelper.getReadableDatabase().query(LuYeeChonContract.JokeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;

            case MOTIVATOR:
                // if uri's request parameter is title-> return value of these title
                String attractionTitle = LuYeeChonContract.MotivatorEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(attractionTitle)) {  //this check string =="" or string.length>0
                    selection = sAttractionTitleSelection;
                    selectionArgs = new String[]{attractionTitle};
                }
                queryCursor = mLuYeeChonDBHelper.getReadableDatabase().query(LuYeeChonContract.MotivatorEntry.TABLE_NAME,

                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;

            case QUIZ:
                // if uri's request parameter is title-> return value of these title
                String quizTitle = LuYeeChonContract.QuizEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(quizTitle)) {  //this check string =="" or string.length>0
                    selection = sQuizTitleSelection;
                    selectionArgs = new String[]{quizTitle};
                }
                queryCursor = mLuYeeChonDBHelper.getReadableDatabase().query(LuYeeChonContract.QuizEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;

            case FAV_JOKE:
                String favouriteJokeTitle = LuYeeChonContract.FavouriteJokesEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(favouriteJokeTitle)) {
                    selection = sFavJokeTitleSelection;
                    selectionArgs = new String[]{favouriteJokeTitle};
                }
                queryCursor = mLuYeeChonDBHelper.getReadableDatabase().query(LuYeeChonContract.FavouriteJokesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;

            case FAV_HEALTH:
                String favHealthTitle = LuYeeChonContract.FavouriteHealthsEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(favHealthTitle)) {
                    selection = sFavHealthTitleSelection;
                    selectionArgs = new String[]{favHealthTitle};
                }
                queryCursor = mLuYeeChonDBHelper.getReadableDatabase().query(LuYeeChonContract.FavouriteHealthsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {

            case HEALTHS:
                return LuYeeChonContract.HealthEntry.DIR_TYPE;

            case JOKES:
                return LuYeeChonContract.JokeEntry.DIR_TYPE;

            case MOTIVATOR:
                return LuYeeChonContract.MotivatorEntry.DIR_TYPE;

            case QUIZ:
                return LuYeeChonContract.QuizEntry.DIR_TYPE;

            case FAV_JOKE:
                return LuYeeChonContract.FavouriteJokesEntry.DIR_TYPE;

            case FAV_HEALTH:
                return  LuYeeChonContract.FavouriteHealthsEntry.DIR_TYPE;


            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        final SQLiteDatabase db = mLuYeeChonDBHelper.getWritableDatabase();

        final int matchUri = sUriMatcher.match(uri);
        Uri insertedUri;

        switch (matchUri) {

            case HEALTHS: {
                long _id = db.insert(LuYeeChonContract.HealthEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = LuYeeChonContract.HealthEntry.buildHealthUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case JOKES: {
                long _id = db.insert(LuYeeChonContract.JokeEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = LuYeeChonContract.JokeEntry.buildJokeUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }

            case MOTIVATOR: {
                long _id = db.insert(LuYeeChonContract.MotivatorEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    //create uri object for inserted data
                    insertedUri = LuYeeChonContract.MotivatorEntry.buildMotivatorUri(_id);

                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }

            case QUIZ: {
                long _id = db.insert(LuYeeChonContract.QuizEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    //create uri object for inserted data
                    insertedUri = LuYeeChonContract.QuizEntry.builQuizUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case FAV_JOKE: {
                long _id = db.insert(LuYeeChonContract.FavouriteJokesEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = LuYeeChonContract.FavouriteJokesEntry.buildFavouriteJokeUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case FAV_HEALTH: {
                long _id = db.insert(LuYeeChonContract.FavouriteHealthsEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = LuYeeChonContract.FavouriteHealthsEntry.buildFavouriteHealthUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {

            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedUri;
    }

    @Override

    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mLuYeeChonDBHelper.getWritableDatabase();

        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();

            db.close();

        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override

    public int delete(Uri uri, String s, String[] strings) {
        final SQLiteDatabase db = mLuYeeChonDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, s, strings);

        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override

    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        final SQLiteDatabase db = mLuYeeChonDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, s, strings);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


        uriMatcher.addURI(LuYeeChonContract.CONTENT_AUTHORITY, LuYeeChonContract.PATH_HEALTHS, HEALTHS);
        uriMatcher.addURI(LuYeeChonContract.CONTENT_AUTHORITY, LuYeeChonContract.PATH_JOKES, JOKES);
        uriMatcher.addURI(LuYeeChonContract.CONTENT_AUTHORITY, LuYeeChonContract.PATH_MOTIVATOR, MOTIVATOR);
        uriMatcher.addURI(LuYeeChonContract.CONTENT_AUTHORITY, LuYeeChonContract.PATH_QUIZ, QUIZ);
        uriMatcher.addURI(LuYeeChonContract.CONTENT_AUTHORITY, LuYeeChonContract.PATH_FAVOURITE_JOKES, FAV_JOKE);
        uriMatcher.addURI(LuYeeChonContract.CONTENT_AUTHORITY, LuYeeChonContract.PATH_FAVOURITE_HEALTHS, FAV_HEALTH);

        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);

        switch (matchUri) {

            case HEALTHS:
                return LuYeeChonContract.HealthEntry.TABLE_NAME;

            case JOKES:
                return LuYeeChonContract.JokeEntry.TABLE_NAME;

            case MOTIVATOR:
                return LuYeeChonContract.MotivatorEntry.TABLE_NAME;

            case QUIZ:
                return LuYeeChonContract.QuizEntry.TABLE_NAME;

            case FAV_JOKE:
                return LuYeeChonContract.FavouriteJokesEntry.TABLE_NAME;

            case FAV_HEALTH:
                return LuYeeChonContract.FavouriteHealthsEntry.TABLE_NAME;



            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

}
