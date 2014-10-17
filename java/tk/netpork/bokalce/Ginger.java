package tk.netpork.bokalce;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by netpork on 10/17/14.
 */
public class Ginger {

    private List<Bobble> bobbles = new ArrayList<Bobble>();
    private List<Object> parts = new ArrayList<Object>(){};

    private static final int bobblesCount = 50;
    private Bitmap bobblesBitmap;
    private Video mVideo;
    private Scroller mScroller;
    private MainPanel mPanel;

    public Ginger(Video video, MainPanel panel) {
        mVideo = video;
        mPanel = panel;
        mScroller = new Scroller(16, 22, mPanel);
        initBobbles();
    }

    public void update(Canvas canvas) {
        mVideo.mCanvas.drawColor(0xffecf0f1);
        boolean djidjiDrawn = false;

        for (int i = 0; i < bobblesCount; i++) {

            if (i >= 24 && !djidjiDrawn) {
                drawDjidji(mVideo.mCanvas);
                djidjiDrawn = true;
            }

            bobbles.get(i).update(mVideo.mCanvas);
        }

        mScroller.update(mVideo.mCanvas);
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

    public boolean handleEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (Bobble b : bobbles) {
                b.handleAction(event.getX(), event.getY());
            }
            return true;
        }
        return false;
    }
}
