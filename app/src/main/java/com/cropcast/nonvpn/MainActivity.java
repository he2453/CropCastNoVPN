package com.cropcast.nonvpn;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_OVERLAY = 1001;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start);
        updateButtonText();

        btnStart.setOnClickListener(v -> {
            if (CropService.isRunning) {
                stopService(new Intent(this, CropService.class));
                updateButtonText();
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName())
                );
                startActivityForResult(intent, REQ_OVERLAY);
                Toast.makeText(this, "请允许悬浮窗权限", Toast.LENGTH_LONG).show();
                return;
            }

            startActivity(new Intent(this, RequestPermissionActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButtonText();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_OVERLAY) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
                startActivity(new Intent(this, RequestPermissionActivity.class));
            } else {
                Toast.makeText(this, "没有悬浮窗权限，无法启动裁剪悬浮窗", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateButtonText() {
        if (btnStart == null) return;
        btnStart.setText(CropService.isRunning ? "停止悬浮裁剪" : "启动/停止 悬浮裁剪");
    }
}
