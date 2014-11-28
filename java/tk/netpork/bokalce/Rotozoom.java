package tk.netpork.bokalce;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by netpork on 11/28/14.
 */
public class Rotozoom {
    private static final String TAG = "Rotozoom";
    private MainPanel mPanel;
    private Video mVideo;

    private double angle = 0;
    private float zoom = 0;
    private int bx = 0, by = 0;

    private static final int textureWidth = 128;
    private static final int textureHeight = 128;

    private int[] pixels = new int[Video.width * Video.height];

    public Rotozoom(Video video, MainPanel panel) {
        mPanel = panel;
        mVideo = video;
    }

    public void update(Canvas canvas) {
        double z, a, dx, dy, px, py, u, v;

        z = 1 + Math.sin(zoom);
        a = Math.PI * Math.sin(angle);
        dx = z * Math.cos(a);
        dy = z * Math.sin(a);

//        Log.i(TAG, "---dx: " + dx + " dy: " + dy);

        px = ((bx + textureWidth / 2 + Video.width / 2 * dx + Video.height / 2 * dy) % textureWidth);
        py = ((by + textureHeight / 2 - Video.width / 2 * dy + Video.height / 2 * dx) % textureHeight);
        u = (-Video.width * dx + dy);
        v = (-Video.width * dy - dx);
//        Log.i(TAG, "---px: " + px + " py: " + py);


        for (int y = 0; y < Video.height; y++) {
            for (int x = 0; x < Video.width; x++) {
                pixels[y * Video.width + x] = Video.spritePixels[((int) py & textureHeight - 1) * textureHeight + ((int) px & textureWidth - 1)];
                px -= dx;
                py += dy;

                while (px < 0) {
                    px += textureWidth;
                }
                while (py < 0) {
                    py += textureHeight;
                }

                px %= textureWidth;
                py %= textureHeight;
            }

//            px -= (-Video.width * dx + dy);
//            py += (-Video.width * dy - dx);

            px -= u;
            py += v;

            while (px < 0) {
                px += textureWidth;
            }
            while (py < 0) {
                py += textureHeight;
            }

            px %= textureWidth;
            py %= textureHeight;
        }

        angle += 0.04;
        zoom += 0.1;

//        bx += 3;
//        by += 5;


        mVideo.mBitmap.setPixels(pixels, 0, Video.width, 0, 0, Video.width, Video.height);
    }


}
