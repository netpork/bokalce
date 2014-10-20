package tk.netpork.bokalce;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Sprite {
    private static final String TAG = "Sprite";

    private Bitmap bobble;
    public boolean touched;
    public double x, y;
    public int frameCount, tileWidth, tileHeight, animDelay, tickDelay = 0, currentFrame = 0;
    public double offset, angle, angleAdder, yAdder;


    public Sprite() {}

    public void draw(Canvas c, int x, int y, int width, int height, Bitmap bobble) {
//        Rect src = new Rect(currentFrame * width, 0, currentFrame * width + width, height);
//        Rect dst = new Rect(x, y, x + width, y + height);
//        Log.i(TAG, "--------------- " + bobble);
        c.drawBitmap(bobble, x, y, null);
    }

}