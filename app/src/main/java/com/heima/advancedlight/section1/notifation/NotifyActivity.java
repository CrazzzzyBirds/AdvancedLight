package com.heima.advancedlight.section1.notifation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.heima.advancedlight.MainActivity;
import com.heima.advancedlight.R;

import static android.app.Notification.VISIBILITY_SECRET;
import static com.heima.advancedlight.section1.notifation.NotificationUtil.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotifyActivity extends AppCompatActivity {

    @BindView(R.id.bt_normal)
    Button btNormal;
    @BindView(R.id.bt_fold)
    Button btFold;
    @BindView(R.id.bt_suspension)
    Button btSuspension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.bt_normal, R.id.bt_fold, R.id.bt_suspension})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_normal:
                initChannel(this);
               // sendNotification(this);
                break;
            case R.id.bt_suspension:

                break;
            case R.id.bt_fold:
                break;

        }
    }



}
