package net.sandi.luyeechon.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Kaung Htet Lin on 10/2/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG="MyFBS";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG,"FCM Message id: "+remoteMessage.getMessageId());
        Log.d(TAG,"FCM Notification Message : "+remoteMessage.getNotification());
        Log.d(TAG,"FCM Messaging Data: "+remoteMessage.getData());

        String title=remoteMessage.getNotification().getTitle();
        String body=remoteMessage.getNotification().getBody();

    }
}
