package edu.wgu.capstone.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import edu.wgu.capstone.R;

/**
 * Receives broadcast messages from the system.
 */
public class NotificationReceiver extends BroadcastReceiver {

    private static int notificationId = 0;

    /**
     * Receives broadcast messages from the system.
     * @param context The context in which the receiver is running.
     * @param intent The intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the message from the intent
        String message = intent.getStringExtra("start");
        if (message != null) {
            showNotification(context, message);
        }
        String message2 = intent.getStringExtra("end");
        if (message2 != null) {
            showNotification(context, message2);
        }
    }

    /**
     * Shows a notification.
     * @param context The context in which the receiver is running.
     * @param message The message to display in the notification.
     */
    private void showNotification(Context context, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(context, "test_channel_01")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setChannelId("test_channel_01")
                .setContentTitle("Travel Organizer")
                .setContentText(message)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(notificationId++, notification);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

