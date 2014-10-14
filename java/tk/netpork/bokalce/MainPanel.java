package tk.netpork.bokalce;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/**
 * Created by netpork on 10/3/14.
 */
public class MainPanel extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener {
    private static final String TAG = "MainPanel";
    private ModMusic modPlayer;
    private MainThread mThread;
    private Video mVideo;
    private Scroller mScroller;

    private static Context context;

    public static int screenWidth, screenHeight;
    public static float density;

    private List<Bobble> bobbles = new ArrayList<Bobble>();
    private static final int bobblesCount = 50;
    private Bitmap bobblesBitmap;
    public static final Random RND = new Random();
    public static final double radians = Math.PI / 180.0;
    private float lastTouchX, lastTouchY;


    private String avgFps;
    
    public MainPanel(Context context, DisplayMetrics metrics) {
        super(context);
        MainPanel.context = context;
        density = metrics.density;

        getHolder().addCallback(this);
        setFocusable(true);
        setOnTouchListener(this);
        mThread = new MainThread(getHolder(), this);
    }

    public void update(Canvas canvas) {
//        Log.i(TAG, "***** update");



    }

    public void render(Canvas canvas) {
//        Log.i(TAG, "***** render");

//        canvas.drawColor(Color.RED);
        mVideo.mCanvas.drawColor(Color.WHITE);
        boolean djidjiDrawn = false;

        for (int i = 0; i < bobblesCount; i++) {

            if (i >= 24 && !djidjiDrawn) {
                drawDjidji(mVideo.mCanvas);
                djidjiDrawn = true;
            }

            bobbles.get(i).update(mVideo.mCanvas);
        }

        mScroller.update(mVideo.mCanvas);
        mVideo.update(canvas);

        displayFps(canvas, avgFps);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i(TAG, "surface-created");

        initBobbles();
        mScroller = new Scroller(16, 22, this);

        mThread.running = true;
        mThread.start();

        modPlayer = new ModMusic(MainPanel.context, R.raw.xality);
        modPlayer.play();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Log.i(TAG, "surface-changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.i(TAG, "surface-destroyed");
        modPlayer.stop();

        boolean retry = true;
        mThread.running = false;

        while (retry) {
            try {
                mThread.join();
                retry = false;
            } catch (InterruptedException e) {}
        }

//        Log.i(TAG, String.valueOf(screenWidth) + " " + String.valueOf(screenHeight) + " " + String.valueOf(density));
        Log.i(TAG, "thread shutdown clearly...");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenWidth = w;
        screenHeight = h;

        mVideo = new Video(this);
    }

    public void initBobbles() {
//        bobblesBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boobles, mVideo.optionsNoScale);

        for(int i = 0; i < bobblesCount; i++) {
            bobbles.add(new Bobble(64, 64, 6, 1));
        }
    }

    public void drawDjidji(Canvas canvas) {
//        Log.i(TAG, String.valueOf(Video.djidjiX + " " + Video.djidjiY));
//        Paint paint = new Paint();
//        paint.setDither(false);
//        paint.setAntiAlias(false);
//        paint.setFilterBitmap(false);
        canvas.drawBitmap(mVideo.djidji, Video.djidjiX, Video.djidjiY, null);
    }

    public void setAvgFps(String avgFps) {
        this.avgFps = avgFps;
    }

    public String getAvgFps() {
        return avgFps;
    }

    private void displayFps(Canvas canvas, String fps) {
        if (canvas != null && fps != null) {
            Paint paint = new Paint();
            paint.setARGB(255, 0, 0, 0);
            paint.setTextSize(15 * density);
            canvas.drawText(fps, (this.getWidth() - (70 * density)), 20 * density, paint);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
//        Log.i(TAG, "touched=======:" + event.getX() + " " + event.getY());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (Bobble b : bobbles) {
                b.handleAction(event.getX(), event.getY());
            }
            return true;
        }
        return false;
    }
}
