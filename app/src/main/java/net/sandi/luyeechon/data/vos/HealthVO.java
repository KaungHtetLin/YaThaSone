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
 * Created by UNiQUE on 9/18/2016.
 */
public class HealthVO {

    @SerializedName("type")
    private String type;

    @SerializedName("health_title")
    private String healthTitle;

    @SerializedName("health_des")
    private String healthDes;

    @SerializedName("health_photo")
    private String image;

    private String fav;

    public HealthVO(String healthTitle, String healthDes, String image) {
        this.healthTitle = healthTitle;
        this.healthDes = healthDes;
        this.image = image;
    }

    public HealthVO(String healthTitle, String healthDes) {
        this.healthTitle = healthTitle;
        this.healthDes = healthDes;
    }

    public HealthVO(){}


    public String getHealthTitle() {
        return healthTitle;
    }

    public String getHealthDes() {
        return healthDes;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public static void saveHealths(List<HealthVO> healthList) {

        Context context = LuYeeChonApp.getContext();
        ContentValues[] healthCVs = new ContentValues[healthList.size()+1];

        for (int index = 0; index < healthList.size(); index++) {
            HealthVO health = healthList.get(index);
            healthCVs[index] = health.parseToContentValues("0");

        }

        int insertedCount = context.getContentResolver().bulkInsert(LuYeeChonContract.HealthEntry.CONTENT_URI, healthCVs);

        Log.d(LuYeeChonApp.TAG, "Bulk inserted into health table : " + insertedCount);
    }

    public static void saveFavouriteHealths(HealthVO health,String fav){
        Context context = LuYeeChonApp.getContext();
        ContentValues healthCV = new ContentValues();
        healthCV = health.parseToContentValues(fav);

        Uri insertedUri= context.getContentResolver().insert(LuYeeChonContract.FavouriteHealthsEntry.CONTENT_URI, healthCV);
        Toast.makeText(context,"Uri of Fav : "+insertedUri.toString(),Toast.LENGTH_LONG).show();

    }

    public static void removeFavouriteHealth(HealthVO health){
        Context context = LuYeeChonApp.getContext();

        int removedUri= context.getContentResolver().delete(LuYeeChonContract.FavouriteHealthsEntry.CONTENT_URI, "title=?",new String[]{health.getHealthTitle()});
        Toast.makeText(context,"Remove count : "+removedUri+"",Toast.LENGTH_LONG).show();

    }


    private ContentValues parseToContentValues(String fav) {
        ContentValues cv = new ContentValues();
        cv.put(LuYeeChonContract.HealthEntry.COLUMN_TYPE, type);
        cv.put(LuYeeChonContract.HealthEntry.COLUMN_TITLE, healthTitle);
        cv.put(LuYeeChonContract.HealthEntry.COLUMN_DESC, healthDes);
        cv.put(LuYeeChonContract.HealthEntry.COLUMN_PHOTO,image);
        cv.put(LuYeeChonContract.HealthEntry.COLUMN_FAV,fav);
        return cv;
    }

    public static HealthVO parseFromCursor(Cursor data) {
        HealthVO health = new HealthVO();
        health.type = data.getString(data.getColumnIndex(LuYeeChonContract.HealthEntry.COLUMN_TYPE));
        health.healthTitle = data.getString(data.getColumnIndex(LuYeeChonContract.HealthEntry.COLUMN_TITLE));
        health.healthDes = data.getString(data.getColumnIndex(LuYeeChonContract.HealthEntry.COLUMN_DESC));
        health.image = data.getString(data.getColumnIndex(LuYeeChonContract.HealthEntry.COLUMN_PHOTO));
        health.fav = data.getString(data.getColumnIndex(LuYeeChonContract.HealthEntry.COLUMN_FAV));
        return health;
    }

    public static HealthVO parseFromCursorFavourite(Cursor data){
        HealthVO health = new HealthVO();
        health.healthTitle = data.getString(data.getColumnIndex(LuYeeChonContract.FavouriteHealthsEntry.COLUMN_TITLE));
        health.healthDes = data.getString(data.getColumnIndex(LuYeeChonContract.FavouriteHealthsEntry.COLUMN_DES
        ));
        health.type = data.getString(data.getColumnIndex(LuYeeChonContract.FavouriteHealthsEntry.COLUMN_TYPE));
        health.image = data.getString(data.getColumnIndex(LuYeeChonContract.FavouriteHealthsEntry.COLUMN_PHOTO));
        health.fav = data.getString(data.getColumnIndex(LuYeeChonContract.FavouriteHealthsEntry.COLUMN_FAV));
        return health;
    }
}
