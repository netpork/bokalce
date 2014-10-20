package tk.netpork.bokalce;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MyActivity extends Activity {

    private static final String TAG = "MyActivity";
    private MainPanel mPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

//        Log.i(TAG, String.valueOf(metrics.widthPixels) + " " + String.valueOf(metrics.heightPixels));

        mPanel = new MainPanel(this, metrics);

        setContentView(mPanel);
    }

    @Override
    public void onBackPressed() {
        if (MainPanel.endIntro) {
            super.onBackPressed();
            this.finish();
        } else {
            mPanel.enableEnd();
//            MainPanel.part++;
        }
        Log.i(TAG, "------------------------------ back pressed");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
