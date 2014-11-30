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
    private final int textureWidthMask = textureWidth - 1;
    private final int textureHeightMask = textureHeight - 1;


    private int[] pixels = new int[Video.width * Video.height];

    public Rotozoom(Video video, MainPanel panel) {
        mPanel = panel;
        mVideo = video;
    }

    public void update(Canvas canvas) {
        rotoZoomFixed();
        mVideo.mBitmap.setPixels(pixels, 0, Video.width, 0, 0, Video.width, Video.height);
    }

    private void rotoZoom() {
        double z, a, dx, dy, px, py, u, v;
        int offset = 0;
        z = 1 + Math.sin(zoom);
        a = Math.PI * Math.sin(angle);
        dx = z * Math.cos(a);
        dy = z * Math.sin(a);

        px = ((bx + textureWidth / 2 + Video.width / 2 * dx + Video.height / 2 * dy) % textureWidth);
        py = ((by + textureHeight / 2 - Video.width / 2 * dy + Video.height / 2 * dx) % textureHeight);
        u = (-Video.width * dx + dy);
        v = (-Video.width * dy - dx);

        for (int y = 0; y < Video.height; y++) {
            for (int x = 0; x < Video.width; x++) {
//                pixels[y * Video.width + x] = Video.spritePixels[((int) py & textureWidthMask) * textureHeight + ((int) px & textureHeightMask)];
                pixels[offset] = Video.spritePixels[((int) py & textureWidthMask) * textureHeight + ((int) px & textureHeightMask)];
                px -= dx;
                py += dy;
                offset++;
            }
            px -= u;
            py += v;
        }

        angle += 0.04;
        zoom += 0.1;

//        bx += 3;
//        by += 5;

    }

    private void rotoZoomFixed() {
        double z, a, dx, dy, px, py;
        int offset = 0, u, v;
        z = 1 + Math.sin(zoom);
        a = Math.PI * Math.sin(angle);
        dx = z * Math.cos(a);
        dy = z * Math.sin(a);

        px = ((bx + textureWidth / 2 + Video.width / 2 * dx + Video.height / 2 * dy) % textureWidth);
        py = ((by + textureHeight / 2 - Video.width / 2 * dy + Video.height / 2 * dx) % textureHeight);

        int dxFixed = (int) (dx * 65536.0);
        int dyFixed = (int) (dy * 65536.0);
        int pxFixed = (int) (px * 65536.0);
        int pyFixed = (int) (py * 65536.0);

        u = (-Video.width * dxFixed + dyFixed);
        v = (-Video.width * dyFixed - dxFixed);

        for (int y = 0; y < Video.height; y++) {
            for (int x = 0; x < Video.width; x++) {
                pixels[offset] = Video.spritePixels[((pyFixed >> 16) & textureWidthMask) * textureHeight + ((pxFixed >> 16) & textureHeightMask)];
                pxFixed -= dxFixed;
                pyFixed += dyFixed;
                offset++;
            }
            pxFixed -= u;
            pyFixed += v;
        }

        angle += 0.04;
        zoom += 0.1;

        bx += 3;
        by += 5;

    }
}