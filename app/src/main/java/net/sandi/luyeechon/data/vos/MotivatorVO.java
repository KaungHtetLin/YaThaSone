package net.sandi.luyeechon.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.data.persistence.LuYeeChonContract;

import java.util.List;

/**
 * Created by Kaung Htet Lin on 9/23/2016.
 */
public class MotivatorVO {

    @SerializedName("image_url")
    private String image;

//    public MotivatorVO(String imgurl)
//    {
//        image=imgurl;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static void saveMotivator(List<MotivatorVO> motivatorList) {
        Context context = LuYeeChonApp.getContext();
        ContentValues[] motivatorCVs = new ContentValues[motivatorList.size()];
        for (int index = 0; index < motivatorList.size(); index++) {
            MotivatorVO motivator = motivatorList.get(index);
            motivatorCVs[index] = motivator.parseToContentValues();

        }

        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(LuYeeChonContract.MotivatorEntry.CONTENT_URI, motivatorCVs);

        Log.d(LuYeeChonApp.TAG, "Bulk inserted into attraction table : " + insertedCount);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(LuYeeChonContract.MotivatorEntry.COLUMN_TITLE, image);
        //     cv.put(AttractionsContract.AttractionEntry.COLUMN_DESC, desc);
        return cv;
    }

    public static MotivatorVO parseFromCursor(Cursor data) {
        MotivatorVO motivator = new MotivatorVO();
        motivator.image = data.getString(data.getColumnIndex(LuYeeChonContract.MotivatorEntry.COLUMN_TITLE));
        //     motivator.desc = data.getString(data.getColumnIndex(AttractionsContract.AttractionEntry.COLUMN_DESC));
        return motivator;
    }

}
