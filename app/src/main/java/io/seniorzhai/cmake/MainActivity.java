package io.seniorzhai.cmake;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSION_REQUEST_CODE = 0x001;
    private static final String[] permissionManifest = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private EditText inFile, outFile;
    private boolean isInit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inFile = (EditText) findViewById(R.id.inFile);
        outFile = (EditText) findViewById(R.id.outFile);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NativeBridge.nativeRelease();
    }

    public void task(View view) {
        permissionCheck();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    if (Manifest.permission.CAMERA.equals(permissions[i])) {
                        task();
                    } else if (Manifest.permission.RECORD_AUDIO.equals(permissions[i])) {

                    }
                }
            }
        }
    }

    private void permissionCheck() {
        if (Build.VERSION.SDK_INT >= 23) {
            boolean permissionState = true;
            for (String permission : permissionManifest) {
                if (ContextCompat.checkSelfPermission(this, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    permissionState = false;
                }
            }
            if (!permissionState) {
                ActivityCompat.requestPermissions(this, permissionManifest, PERMISSION_REQUEST_CODE);
            } else {
                task();
            }
        } else {
            task();
        }
    }

    private void task() {
        if (isInit) {
            isInit = false;
            NativeBridge.initFFmpeg(true, "/storage/emulated/0/ffmpeg_log.txt");
        }
        captureThumbnails(inFile.getText().toString(), outFile.getText().toString());
    }

    private void captureThumbnails(String in, String out) {
        int result = ffmpegRunCMD(getCaptureThumbnailsCMD(in, out, null));
        Toast.makeText(this, result == 0 ? "success" : "fail", Toast.LENGTH_SHORT).show();
    }


    private static String getCaptureThumbnailsCMD(String videopath, String outoutPath, String ss) {
        if (ss == null) {
            ss = "";
        } else {
            ss = " -ss " + ss;
        }

        return String.format("ffmpeg -y -i %s %s -vframes 1 %s ", videopath, ss, outoutPath);
    }

    public static int ffmpegRunCMD(String cmd) {
        String regulation = "[ \\t]+";
        final String[] split = cmd.split(regulation);
        return NativeBridge.CMDRun(split);
    }

}
