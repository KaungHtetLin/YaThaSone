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
 * Created by Kaung Htet Lin on 9/24/2016.
 */
public class QuizVO {

    @SerializedName("question")
    private String question;

    @SerializedName("answer")
    private String answer;

    @SerializedName("contain")
    private String contain;

//    public QuizVO(String question, String answer, String contain) {
//        this.question = question;
//        this.answer = answer;
//        this.contain = contain;
//    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getContain() {
        return contain;
    }

    public static void saveQuiz(List<QuizVO> quizList) {
        Context context = LuYeeChonApp.getContext();
        ContentValues[] quizCVs = new ContentValues[quizList.size()];
        for (int index = 0; index < quizList.size(); index++) {
            QuizVO quiz = quizList.get(index);
            quizCVs[index] = quiz.parseToContentValues();

            //Bulk insert into attraction_images.
            //   AttractionVO.saveAttractionImages(quiz.getTitle(), quiz.getImages());
        }

        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(LuYeeChonContract.QuizEntry.CONTENT_URI, quizCVs);

        Log.d(LuYeeChonApp.TAG, "Bulk inserted into attraction table : " + insertedCount);
    }


    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(LuYeeChonContract.QuizEntry.COLUMN_TITLE, question);
        cv.put(LuYeeChonContract.QuizEntry.COLUMN_ANSWER, answer);
        cv.put(LuYeeChonContract.QuizEntry.COLUMN_CONTAIN, contain);
        return cv;
    }

    public static QuizVO parseFromCursor(Cursor data) {
        QuizVO quiz = new QuizVO();
        quiz.question = data.getString(data.getColumnIndex(LuYeeChonContract.QuizEntry.COLUMN_TITLE));
        quiz.answer = data.getString(data.getColumnIndex(LuYeeChonContract.QuizEntry.COLUMN_ANSWER));
        quiz.contain = data.getString(data.getColumnIndex(LuYeeChonContract.QuizEntry.COLUMN_CONTAIN));
        return quiz;
    }


}
