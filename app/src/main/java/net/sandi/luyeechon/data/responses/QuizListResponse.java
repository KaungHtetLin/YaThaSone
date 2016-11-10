package net.sandi.luyeechon.data.responses;

import com.google.gson.annotations.SerializedName;

import net.sandi.luyeechon.data.vos.QuizVO;

import java.util.ArrayList;

/**
 * Created by Kaung Htet Lin on 9/30/2016.
 */
public class QuizListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("quiz-list")
    private ArrayList<QuizVO> quizList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<QuizVO> getQuizList() {
        return quizList;
    }
}
