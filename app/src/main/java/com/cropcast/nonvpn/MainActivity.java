package com.cropcast.nonvpn;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final int REQ_CAPTURE = 1001;
    private TextView tv;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> {
            MediaProjectionManager mpm = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
            startActivityForResult(mpm.createScreenCaptureIntent(), REQ_CAPTURE);
        });
        tv.setText("CropCastNoVPN skeleton. 下一步：CaptureService + 裁剪 + WebRTC。");
    }

    @Override
    protected void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);
        if (req == REQ_CAPTURE && res == RESULT_OK && data != null) {
            tv.setText("已获投屏权限，待接入 CaptureService。");
            // Intent s = new Intent(this, CaptureService.class);
            // s.putExtra("mp_intent", data);
            // startForegroundService(s);
        }
    }
}
