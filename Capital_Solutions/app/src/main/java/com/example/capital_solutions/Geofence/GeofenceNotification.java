package com.example.capital_solutions.Geofence;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.capital_solutions.MainActivity;
import com.example.capital_solutions.R;
import com.google.android.gms.location.Geofence;

public class GeofenceNotification {
    public static final int NOTIFICATION_ID = 20;

    protected Context context;

    protected NotificationManager notificationManager;
    protected Notification notification;

    public GeofenceNotification(Context context) {
        this.context = context;

        this.notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
    }

    protected void buildNotificaction(SimpleGeofence simpleGeofence,
                                      int transitionType) {

        String notificationText = "";
        Object[] notificationTextParams = new Object[]{simpleGeofence.getId()};

        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                notificationText = String.format(
                        context.getString(R.string.geofence_dwell),
                        notificationTextParams);
                break;

            case Geofence.GEOFENCE_TRANSITION_ENTER:
                notificationText = String.format(
                        context.getString(R.string.geofence_enter),
                        notificationTextParams);
                break;

            case Geofence.GEOFENCE_TRANSITION_EXIT:
                notificationText = String.format(
                        context.getString(R.string.geofence_exit),
                        notificationTextParams);
                break;
        }
        Log.d(MainActivity.TAG, notificationText);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(notificationText).setAutoCancel(true);

        notification = notificationBuilder.build();
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
    }

    public void displayNotification(SimpleGeofence simpleGeofence,
                                    int transitionType) {
        buildNotificaction(simpleGeofence, transitionType);

        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}