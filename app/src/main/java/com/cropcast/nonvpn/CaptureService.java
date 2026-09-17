package com.cropcast.nonvpn;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CaptureService extends Service {
    @Override
    public IBinder onBind(Intent i) { return null; }

    @Override
    public int onStartCommand(Intent i, int flags, int startId) {
        // TODO: MediaProjection 回调、裁剪区域、编码器、WebRTC/WS
        return START_STICKY;
    }
}
