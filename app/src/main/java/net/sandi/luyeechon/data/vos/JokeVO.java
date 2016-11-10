package net.sandi.luyeechon.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.data.persistence.LuYeeChonContract;

import java.util.List;

/**
 * Created by UNiQUE on 9/19/2016.
 */
public class JokeVO {

    @SerializedName("joke_title")
    private String jokeTitle;

    @SerializedName("joke_desc")
    private String jokeDes;

    @SerializedName("joke_photo")
    private String imageJoke;

    private String fav;

    public JokeVO(String jokeTitle, String jokeDes, String imageJoke) {
        this.jokeTitle = jokeTitle;
        this.jokeDes = jokeDes;
        this.imageJoke = imageJoke;
    }

    public JokeVO(String jokeTitle, String jokeDes) {
        this.jokeTitle = jokeTitle;
        this.jokeDes = jokeDes;
    }

    public JokeVO(){}

    public void setFav(String fav) {
        this.fav = fav;
    }

    public String getJokeTitle() {
        return jokeTitle;
    }

    public String getJokeDes() {
        return jokeDes;
    }

    public String getImageJoke() {
        return imageJoke;
    }

    public String getFav() {
        return fav;
    }

    public static void saveJokes(List<JokeVO> jokeList) {
        Context context = LuYeeChonApp.getContext();
        ContentValues[] jokeCVs = new ContentValues[jokeList.size()];
        for (int index = 0; index < jokeList.size(); index++) {
            JokeVO joke = jokeList.get(index);
            jokeCVs[index] = joke.parseToContentValues("0");

            //Bulk insert into attraction_images.
        }

        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(LuYeeChonContract.JokeEntry.CONTENT_URI, jokeCVs);
        Toast.makeText(context,"Count : "+insertedCount,Toast.LENGTH_LONG).show();

        Log.d(LuYeeChonApp.TAG, "Bulk inserted into joke table : " + insertedCount);
    }

    public static void saveFavouriteJoke(JokeVO jokeVO,String fav){
        Context context = LuYeeChonApp.getContext();
        ContentValues jokeCV = new ContentValues();
        jokeCV = jokeVO.parseToContentValues(fav);

        Uri insertedUri= context.getContentResolver().insert(LuYeeChonContract.FavouriteJokesEntry.CONTENT_URI, jokeCV);
        Toast.makeText(context,"Uri of Fav : "+insertedUri.toString(),Toast.LENGTH_LONG).show();

    }
    public static void removeFavouriteJoke(JokeVO joke){
        Context context = LuYeeChonApp.getContext();

        int removedUri= context.getContentResolver().delete(LuYeeChonContract.FavouriteJokesEntry.CONTENT_URI, "title=?",new String[]{joke.getJokeTitle()});
        Toast.makeText(context,"Remove count : "+removedUri+"",Toast.LENGTH_LONG).show();

    }

    private ContentValues parseToContentValues(String fav) {
        ContentValues cv = new ContentValues();
        cv.put(LuYeeChonContract.JokeEntry.COLUMN_TITLE, jokeTitle);
        cv.put(LuYeeChonContract.JokeEntry.COLUMN_DESC, jokeDes);
        cv.put(LuYeeChonContract.JokeEntry.COLUMN_PHOTO,imageJoke);
        cv.put(LuYeeChonContract.JokeEntry.COLUMN_FAV,fav);
        return cv;
    }

    public static JokeVO parseFromCursor(Cursor data) {
        JokeVO joke = new JokeVO();
        joke.jokeTitle = data.getString(data.getColumnIndex(LuYeeChonContract.JokeEntry.COLUMN_TITLE));
        joke.jokeDes = data.getString(data.getColumnIndex(LuYeeChonContract.JokeEntry.COLUMN_DESC));
        joke.imageJoke = data.getString(data.getColumnIndex(LuYeeChonContract.JokeEntry.COLUMN_PHOTO));
        joke.fav = data.getString(data.getColumnIndex(LuYeeChonContract.JokeEntry.COLUMN_FAV));
        return joke;
    }

    public static JokeVO parseFromCursorFavourite(Cursor data){
        JokeVO joke = new JokeVO();
        joke.jokeTitle = data.getString(data.getColumnIndex(LuYeeChonContract.FavouriteJokesEntry.COLUMN_TITLE));
        joke.jokeDes = data.getString(data.getColumnIndex(LuYeeChonContract.FavouriteJokesEntry.COLUMN_DES
        ));
        joke.imageJoke = data.getString(data.getColumnIndex(LuYeeChonContract.FavouriteJokesEntry.COLUMN_PHOTO));
        joke.fav = data.getString(data.getColumnIndex(LuYeeChonContract.FavouriteJokesEntry.COLUMN_FAV));
        return joke;
    }

    public static boolean isJokeFav(JokeVO joke){
        boolean favBoolean = false;
        if(joke.fav.equals("1"))
            favBoolean = true;
        else if (joke.fav.equals("0"))
            favBoolean = false;
        return favBoolean;
    }


}
