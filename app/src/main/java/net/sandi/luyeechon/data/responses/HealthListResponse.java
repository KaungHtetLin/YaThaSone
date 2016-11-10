package net.sandi.luyeechon.data.responses;

import com.google.gson.annotations.SerializedName;

import net.sandi.luyeechon.data.vos.HealthVO;

import java.util.ArrayList;

/**
 * Created by aung on 7/9/16.
 */
public class HealthListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("health-list")
    private ArrayList<HealthVO> healthList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<HealthVO> getHealthList() {
        return healthList;
    }
}
