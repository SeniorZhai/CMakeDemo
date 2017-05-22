package io.seniorzhai.cmake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.sample_text);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String avformatinfo();

    public native String urlprotocolinfo();

    private native String avcodecinfo();

    private native String avconfiguration();

    private native String avfilter();

    public void url(View view) {
        tv.setText(urlprotocolinfo());
    }

    public void format(View view) {
        tv.setText(avformatinfo());
    }


    public void avcodec(View view) {
        tv.setText(avcodecinfo());
    }

    public void configuration(View view) {
        tv.setText(avconfiguration());
    }

    public void filter(View view) {
        tv.setText(avfilter());
    }
}
