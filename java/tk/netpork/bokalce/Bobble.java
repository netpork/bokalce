package tk.netpork.bokalce;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Bobble extends Sprite {
    private static final String TAG = MainThread.class.getCanonicalName();

    public Bitmap[] bubbles = {Video.bobl1, Video.bobl2, Video.bobl3, Video.bobl4, Video.bobl5, Video.bobl6};

    public Bobble(int tileWidth, int tileHeight, int frameCount,	int animDelay) {
        super();
        this.frameCount = frameCount;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.animDelay = animDelay;

        newBubble();
    }

    public double getY() {
        return y;
    }

    public void update(Canvas c) {
/*
        if (tickDelay <= animDelay) {
            tickDelay++;
        } else {
            tickDelay = 0;
            if (isTouched() && currentFrame < frameCount)  {
                if (currentFrame == 2) {
                    MainPanel.playSample(RND.nextInt(3));
                }
                currentFrame++;
                yAdder = -1;
            }
        }

        if (isTouched() && currentFrame == 5) {
            newBubble();
        }
*/

        if (y >= -tileWidth) {
            y -= yAdder;
        } else {
            newBubble();
        }

        angle += angleAdder;
        x = (int) (Math.sin(radians * angle) * offset) + (Video.width / 2) - tileWidth;
        super.draw(c, (int) x, (int) y, tileWidth, tileHeight, bubbles[currentFrame]);
    }

    public void handleAction(int eventX, int eventY) {
        if(isTouched()) return;

        if (eventX >= x && (eventX <= (x + tileWidth))) {
            if (eventY >= y && (eventY <= (y + tileHeight))) {
                // touched
                setTouched(true);
            } else {
                setTouched(false);
            }
        } else {
            setTouched(false);
        }
    }

    public void newBubble() {
        x = RND.nextInt(Video.width - tileWidth);
        y = Video.height + tileHeight;
//        y = RND.nextInt(Video.height - tileHeight);
        offset = RND.nextDouble() * Video.width / 2;
        angleAdder = RND.nextDouble() * 5;
        yAdder = (RND.nextDouble() * 5) + 0.5;
        currentFrame = 0;
        setTouched(false);
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }
}
