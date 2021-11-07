package activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.cots.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {
activities.NotificationHelper notificationHelper;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {notificationHelper =new NotificationHelper(this);

        NotificationCompat.Builder nb=notificationHelper.getChannelNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        notificationHelper.getmanager().notify(1,nb.build());
         /*  NotificationChannel notificationChannel=new NotificationChannel("Mynotifications","001",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

            shownotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());*/
        }
        else
        {
            shownotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }


    }
    public  void shownotification(String title,String body) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Mynotifications")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.cotsl_logo)
                .setAutoCancel(true)
                .setContentText(body);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999, builder.build());
    }


}
