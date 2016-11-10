package net.sandi.luyeechon.data.responses;

import com.google.gson.annotations.SerializedName;

import net.sandi.luyeechon.data.vos.MotivatorVO;

import java.util.ArrayList;

/**
 * Created by Kaung Htet Lin on 9/27/2016.
 */
public class MotivatorListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("motivator-list")
    private ArrayList<MotivatorVO> motivatorList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<MotivatorVO> getMotivatorList() {
        return motivatorList;
    }
}
