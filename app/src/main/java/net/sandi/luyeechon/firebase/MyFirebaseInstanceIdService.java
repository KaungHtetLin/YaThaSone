package net.sandi.luyeechon.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by Kaung Htet Lin on 10/2/2016.
 */
public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String GENERAL_ENGAGEMENT_TOPIC="m-general-enagement-topic";
    private static final String TAG="InstanceIdService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"FCM InstanceId"+token);

        FirebaseMessaging.getInstance()
                .subscribeToTopic(GENERAL_ENGAGEMENT_TOPIC);
    }
}
