package tk.netpork.bokalce;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
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
    protected static ModMusic modPlayer;
    private MainThread mThread;

    protected Video mVideo;
    protected Ginger mGinger;
    protected LogoTween mLogoTween;


    protected static Context context;

    public static int screenWidth, screenHeight;
    public static float density;
    public static final Random RND = new Random();
    public static final double radians = Math.PI / 180.0;
    protected static MediaPlayer plopSound1, plopSound2, plopSound3;

    private List<Part> introParts = new ArrayList<Part>();

    // section flags
    protected static int part = 2;
    protected static boolean endIntro = false;

    public static boolean gingerPart = true;

    public static boolean strechkoStart = true;
    public static boolean strechkoEnd = false;

    private String avgFps;
    
    public MainPanel(Context context, DisplayMetrics metrics) {
        super(context);
        MainPanel.context = context;
        density = metrics.density;

        getHolder().addCallback(this);
        setFocusable(true);
        setOnTouchListener(this);

//        mCamera = new Camera();
//        mMatrix = new Matrix();

//        mCamera.save();

        mThread = new MainThread(getHolder(), this);
    }

    public void update(Canvas canvas) {
//        Log.i(TAG, "***** update");
    }

    public void render(Canvas canvas) {
//        Log.i(TAG, "***** render");

//        canvas.drawColor(Color.RED);
//        mVideo.mCanvas.drawColor(Color.BLACK);
//        handlePart(mVideo.mCanvas);
        introParts.get(part).execute();

//          if (gingerPart) mGinger.update(mVideo.mCanvas);

//        if (strechkoStart) Strechko.start(mVideo.mCanvas);
//        if (strechkoStart) Strechko.stretch(mVideo.mCanvas);
//        if (strechkoEnd) Strechko.end(mVideo.mCanvas);

        mVideo.update(canvas);

        displayFps(canvas, avgFps);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i(TAG, "surface-created");

        // preload
        final Preload preload = new Preload(this);
        preload.execute(123);
    }

    public void startMusicAndThread() {
        mThread.running = true;
        mThread.start();

//        modPlayer = new ModMusic(MainPanel.context, R.raw.xality);
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

        plopSound1.stop();
        plopSound1.release();
        plopSound2.stop();
        plopSound2.release();
        plopSound3.stop();
        plopSound3.release();

        boolean retry = true;
        mThread.running = false;

        while (retry) {
            try {
                mThread.join();
                retry = false;
            } catch (InterruptedException e) {}
        }


        ((Activity)getContext()).finish();

  //        Log.i(TAG, String.valueOf(screenWidth) + " " + String.valueOf(screenHeight) + " " + String.valueOf(density));
        Log.i(TAG, "thread shutdown clearly...");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenWidth = w;
        screenHeight = h;

        mVideo = new Video(this);
        mGinger = new Ginger(mVideo, this);
        mLogoTween = new LogoTween(this, mVideo);


        introParts.add(new GingerPart(this));
        introParts.add(new StrechkoPart(this));
        introParts.add(new LogoTweenPart(this));
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
        return mGinger.handleEvent(event);
    }

    public static void playSample(int which) {
        switch (which) {
            case 0:
                plopSound1.start();
                break;
            case 1:
                plopSound2.start();
                break;
            case 2:
                plopSound3.start();
                break;

            default:
                break;
        }
    }

    public void exit() {
        ((Activity) getContext()).finish();
    }

    public static void nextPart() {
        part++;
    }

    public void enableEnd() {
        introParts.get(part).enableEnd();
    }

}
