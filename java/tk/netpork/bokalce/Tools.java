package tk.netpork.bokalce;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by netpork on 10/15/14.
 */
public class Tools {
    private static final String TAG = "Tools";
    private static int  fadeColor;
    private static float tweenStart, tweenEnd, tweenCurrent, tweenEase = 0.5f;

    public static void tween(int start, int end, float ease) {
        tweenStart = start;
        tweenEnd = end;
        tweenEase = ease;
    }

    public static float tween() {
        float delta = tweenEnd - tweenStart;
        tweenCurrent = (int) (delta * tweenEase);
        return tweenCurrent;
    }

    public static void prepareFade(int start, int end, int color, float ease) {
        tweenStart = start;
        tweenEnd = end;
        fadeColor = color;
        tweenEase = ease;
        tweenCurrent = 0;
    }

    public static boolean fade(Canvas canvas) {
//        Log.i(TAG, "ffffff: --- " + (tweenEnd - tweenStart));
        final float delta = tweenEnd - tweenStart;
        tweenStart += delta * tweenEase;

//        Log.i(TAG, "fade ------" + delta + " " + tweenStart);
        if (Math.abs(delta) < 0.1) {
            tweenStart = tweenEnd;
            canvas.drawColor((int) tweenStart << 24 | fadeColor);
            return false;
        } else {
            canvas.drawColor((int) tweenStart << 24 | fadeColor);
            return true;
        }
    }

    public static boolean fadeIn(Canvas canvas) {
//        Log.i(TAG, "ffffff: --- " + (tweenEnd - tweenStart));
        final float delta = tweenEnd - tweenStart;
        tweenStart += delta * tweenEase;

//        Log.i(TAG, "fade ------" + delta + " " + tweenStart);
        if (Math.abs(delta) < 0.1) {
            tweenStart = tweenEnd;
            canvas.drawColor((int) tweenStart << 24 | fadeColor);
            return false;
        } else {
            canvas.drawColor((int) tweenStart << 24 | fadeColor);
            return true;
        }
    }



}
