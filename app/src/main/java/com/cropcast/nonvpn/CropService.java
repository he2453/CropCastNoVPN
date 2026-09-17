package com.cropcast.nonvpn;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class CropService extends Service {

    public static boolean isRunning = false;

    private WindowManager wm;
    private View cropView;
    private static final String CHANNEL_ID = "crop_channel";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = true;
        createChannel();

        Notification notification = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("CropCast")
                .setContentText("悬浮裁剪运行中")
                .setSmallIcon(android.R.drawable.ic_menu_crop)
                .build();

        startForeground(1, notification);

        int type;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                1080,
                1920,
                type,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 0;
        params.y = 0;

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        cropView = LayoutInflater.from(this).inflate(R.layout.crop_window, null);

        Button btnClose = cropView.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> stopSelf());

        Toast.makeText(this, "悬浮窗已显示", Toast.LENGTH_SHORT).show();

        wm.addView(cropView, params);
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel ch = new NotificationChannel(
                    CHANNEL_ID,
                    "CropCast",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager nm = getSystemService(NotificationManager.class);
            if (nm != null) nm.createNotificationChannel(ch);
        }
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        if (wm != null && cropView != null) {
            wm.removeView(cropView);
            cropView = null;
            wm = null;
        }
        super.onDestroy();
    }
}
