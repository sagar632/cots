package activities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.example.cots.R;

public class NotificationHelper extends ContextWrapper {


    final String ChannelID="1";
    NotificationManager manager;
    final String Channelname="notify";


    @RequiresApi(api = Build.VERSION_CODES.P)
    public  void  createchannelp()
    {
        NotificationChannel notificationChannel=new NotificationChannel(ChannelID,Channelname, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getmanager().createNotificationChannel(notificationChannel);

    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    public NotificationHelper(Context base) {
        super(base);
        createchannel();


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public  void  createchannel()
    {
        NotificationChannel notificationChannel=new NotificationChannel(ChannelID,Channelname, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getmanager().createNotificationChannel(notificationChannel);

    }
    public NotificationManager getmanager(){
        if(manager==null){
            manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        }
        return manager;
    }
    public NotificationCompat.Builder getChannelNotification(String title,String body) {
        String s = body.toString();
        NotificationCompat.Builder bn = null;
        if ("COTS has a new post".equals(s)) {
            Intent intent = new Intent(this, Main3Activity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            bn = new NotificationCompat.Builder(getApplicationContext(), "1")
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.cotsl_logo)
                    .setAutoCancel(true)
                    .setContentText(body)
                    .setContentIntent(pendingIntent);
            
        } else if ("COTS has a new event registered".equals(s)) {
            Intent intent1 = new Intent(this, fragment_upcoming_events.class);
            PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

            bn = new NotificationCompat.Builder(getApplicationContext(), "1")
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.cotsl_logo)
                    .setAutoCancel(true)
                    .setContentText(body)
                    .setContentIntent(pendingIntent1);
        } else if ("COTS has a new article published".equals(s)) {
            Intent intent2 = new Intent(this, fragment_read_articles.class);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

            bn=new NotificationCompat.Builder(getApplicationContext(), "1")
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.cotsl_logo)
                    .setAutoCancel(true)
                    .setContentText(body)
                    .setContentIntent(pendingIntent2);
        }
        return bn;
    }

}
