package net.sandi.luyeechon.data.responses;

import com.google.gson.annotations.SerializedName;

import net.sandi.luyeechon.data.vos.JokeVO;

import java.util.ArrayList;

/**
 * Created by UNiQUE on 9/26/2016.
 */
public class JokeListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("joke-list")
    private ArrayList<JokeVO> jokeList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<JokeVO> getJokeList() {
        return jokeList;
    }
}
