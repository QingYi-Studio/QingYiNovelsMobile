package com.qingyi.novels;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import java.util.Calendar;

public class NotificationReceiver extends BroadcastReceiver {

    /*
    * TODO: 让点击通知时自动前往软件。
    */

    private static final String CHANNEL_ID = "book_notification_channel";
    private static final int NOTIFICATION_ID = 100;

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        // 获取NotificationManager实例
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // 创建NotificationChannel（仅在Android O及以上版本需要）
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Book Notification", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);

        // 创建通知
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // 设置小图标
                .setContentTitle(context.getString(R.string.notification_title)) // 设置标题
                .setContentText(context.getString(R.string.notification_text)) // 设置内容
                .setAutoCancel(true) // 点击后自动取消通知
                .build();

        // 发送通知
        notificationManager.notify(NOTIFICATION_ID, notification);

        // 设置下一次发送通知的时间
        setAlarm(context);
    }

    private void setAlarm(Context context) {
        // 获取AlarmManager实例
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // 创建PendingIntent，用于发送广播
        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // 设置下一次发送通知的时间（每天上午8点和下午6点各发送一次）
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        calendar.set(Calendar.HOUR_OF_DAY, 18);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
