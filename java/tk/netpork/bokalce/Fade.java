package tk.netpork.bokalce;

import android.graphics.Canvas;

/**
 * Created by netpork on 10/20/14.
 */
public class Fade {
    private int colour;
    private float start, end, ease;

    public Fade(int start, int end, int colour, float ease) {
        this.start = start;
        this.end = end;
        this.colour = colour;
        this.ease = ease;
    }

    public boolean fadeScreen(Canvas canvas) {
        final float delta = end - start;
        start += delta * ease;

        if (Math.abs(delta) <= 0.1) {
            start = end;
            canvas.drawColor((int) start << 24 | colour);
            return false;
        } else {
            canvas.drawColor((int) start << 24 | colour);
            return true;
        }
    }

}
