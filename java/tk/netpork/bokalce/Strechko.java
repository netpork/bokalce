package tk.netpork.bokalce;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by netpork on 10/16/14.
 */
public class Strechko {
    private static final String TAG = "Strechko";
    private static float startOpacity = 0, endOpacity = 255;
    private static Rect src = new Rect();
    private static Rect dst = new Rect();
    private static float yStep, scrollStep = 0;
    private static double yAngle1, yAngle2, scrollAngle;
    private static final int textureSize = 256;
    private static final int plotX = (Video.width - textureSize) / 2;
//    private static final int plotY = (Video.height - textureSize) / 2;
    private static final int plotY = 0;



    public Strechko() {}

    public static void start(Canvas canvas) {
        final float delta = (endOpacity - startOpacity);
        startOpacity += delta * 0.009f;

//        Log.i(TAG, "opac ----" + startOpacity + " " + endOpacity);

        if (delta <= 4) {
            startOpacity = 255;
            endOpacity = 0;
            MainPanel.strechkoStart = false;
            MainPanel.strechkoEnd = true;
//            ((Activity) getContext()).finish();
        }

        canvas.drawColor((int) startOpacity << 24 | 0xffffff);
    }

    public static void end(Canvas canvas) {
        final float delta = (startOpacity - endOpacity);
        startOpacity -= delta * 0.05f;

        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(Video.nana, (Video.width - 256) / 2, (Video.height - 256) / 2, null);
        canvas.drawColor((int) startOpacity << 24 | 0xffffff);

        if (delta <= 4) {
            // next part
        }

    }

    public static void stretch(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        float y = 0;
        double a = yAngle1;

        for (int i = 0; i < Video.height; i++) {
//            yStep = (float) (((Math.sin(MainPanel.radians * yAngle1) * 2) + 2) +
//                    (Math.sin(MainPanel.radians * yAngle2) * Math.PI)
//            );
            y = (float) ((26 + (i - 8) + 25 * Math.sin(a)) % 256);
//            y = (float) ((i - 64 * Math.sin(a)) % 256);

            src.set(0, (int) y, 256, (int) y + 1);
            dst.set(plotX, i, 256, i + 1);
            canvas.drawBitmap(Video.nana, src, dst, null);
            a += 0.0275;
        }

        yAngle1 += 0.1;
    }

    public static void stretchAhmad(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        float y = 0;
        double a = yAngle1;

        for (int i = 0; i < Video.height; i++) {
            y = (float) ((26 + (i - 8) + 25 * Math.sin(a)) % 256);
//            y = (float) ((i - 64 * Math.sin(a)) % 256);

            src.set(0, (int) y, 256, (int) y + 1);
            dst.set(plotX, i, 256, i + 1);
            canvas.drawBitmap(Video.ahmad, src, dst, null);
            a -= 0.0375;
        }

        yAngle1 -= 0.1;
    }

    public static void stretchIlkke(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        float y = 0;
        double a = yAngle1;

        for (int i = 0; i < Video.height; i++) {
            y = (float) ((26 + (i - 8) + 25 * Math.sin(a)) % 256);
//            y = (float) ((i - 64 * Math.sin(a)) % 256);

            src.set(0, (int) y, 256, (int) y + 1);
            dst.set(plotX, i, 256, i + 1);
            canvas.drawBitmap(Video.ilkke, src, dst, null);
            a += 0.0900;
        }

        yAngle1 -= 0.1;
    }
}
