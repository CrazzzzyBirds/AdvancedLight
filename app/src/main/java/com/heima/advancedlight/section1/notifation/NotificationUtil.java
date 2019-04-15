package com.heima.advancedlight.section1.notifation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.heima.advancedlight.R;

import java.util.ArrayList;
import java.util.List;

import static android.app.Notification.VISIBILITY_SECRET;

public class NotificationUtil {

    public static NotificationManager notificationManager;
    public static String CHANNEL_ID = "channel1";

    /**
     * 创建notification渠道
     *
     * @param context
     */
    public static void initChannel(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "通知渠道1", NotificationManager.IMPORTANCE_DEFAULT);
            //channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
            channel.setDescription("通知渠道的描述1");
            //是否绕过请勿打扰模式
            channel.canBypassDnd();
            //闪光灯
            channel.enableLights(true);
            //锁屏显示通知
            channel.setLockscreenVisibility(VISIBILITY_SECRET);
            //闪关灯的灯光颜色
            channel.setLightColor(Color.RED);
            //桌面launcher的消息角标
            channel.canShowBadge();
            //是否允许震动
            channel.enableVibration(true);
            //获取系统通知响铃声音的配置
            channel.getAudioAttributes();
            //获取通知取到组
            channel.getGroup();
            //设置可绕过  请勿打扰模式
            channel.setBypassDnd(true);
            //设置震动模式
            channel.setVibrationPattern(new long[]{100, 100, 200});
            //是否会有灯光
            channel.shouldShowLights();
            notificationManager.createNotificationChannel(channel);
        }
        sendNotification(context);
    }

    /**
     * 创建多个notification渠道
     *
     * @param context
     */
    public static void initChannel(Context context, int number) {
        List<NotificationChannel> channelList = new ArrayList<>();
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            for (int i = 0; i <= number; i++) {

                NotificationChannel channel = new NotificationChannel("channel1",
                        "通知渠道" + number, NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription("通知渠道的描述" + number);
                channel.enableLights(true);
                channel.setLightColor(Color.WHITE);
                channelList.add(channel);
            }
            notificationManager.createNotificationChannels(channelList);
        }
//        // 通知渠道组的id.
//        String groupId = "渠道组的id";
//        String groupName = "渠道组的id";
//       // 用户可见的通知渠道组名称.
//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.createNotificationChannelGroup(new NotificationChannelGroup(groupId, groupName));
    }


    public static void sendNotification(Context context) {
        //跳转链接
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.csdn.net/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.layout_custom_notifition);
        remoteView.setTextViewText(R.id.notification_title, "custom_title");
        remoteView.setTextViewText(R.id.notification_content, "custom_content");
        //remoteView.setOnClickPendingIntent(R.id.turn_next, pendingIntent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setContentIntent(pendingIntent)
                // .setChannelId("")
                .setContentTitle("标题")
                .setContentText("内容")
                //设置大图标，未设置时使用小图标代替，拉下通知栏显示的那个图标
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round))
                .setSmallIcon(R.mipmap.ic_launcher)
                //  .setCustomBigContentView(remoteView)
               // .setCustomContentView(remoteView)
                .setNumber(3); //久按桌面图标时允许的此条通知的数量;
        int progress = 50;
        if (progress > 0 && progress < 100) {
            //一种是有进度刻度的（false）,一种是循环流动的（true）
            //设置为false，表示刻度，设置为true，表示流动
            builder.setProgress(100, progress, false);
        } else {
            //0,0,false,可以将进度条隐藏
            builder.setProgress(0, 0, false);
            builder.setContentText("下载完成");
        }
        //设置点击信息后自动清除通知
        builder.setAutoCancel(true);
        //通知的时间
        builder.setWhen(System.currentTimeMillis());
        notificationManager.notify(1, builder.build());
    }


    /**
     * 更新通知渠道设置
     *
     * @param context
     * @param channel 待设置渠道
     */
    public static void settingChannel(Context context, NotificationChannel channel) {
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
        context.startActivity(intent);
    }


    /**
     * 删除渠道
     *
     * @param context
     * @param channelId 待设置渠道ID
     */
    public static void removeChannel(Context context, String channelId) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel mChannel = mNotificationManager.getNotificationChannel(channelId);
        mNotificationManager.deleteNotificationChannel(channelId);
    }


}
