package tk.netpork.bokalce;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Bobble extends Sprite {
    private static final String TAG = MainThread.class.getCanonicalName();

    public Bitmap[] bubbles = {Video.bobl1, Video.bobl2, Video.bobl3, Video.bobl4, Video.bobl5, Video.bobl6};

    public Bobble(int tileWidth, int tileHeight, int frameCount, int animDelay) {
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

        if (tickDelay <= animDelay) {
            tickDelay++;
        } else {
            tickDelay = 0;
            if (isTouched() && currentFrame < frameCount)  {
                if (currentFrame == 2) {
                    MainPanel.playSample(MainPanel.RND.nextInt(3));
                }
                currentFrame++;
                yAdder = -(MainPanel.RND.nextInt(10));
            }
        }

        if (isTouched() && currentFrame == 5) {
            newBubble();
        }


        if (y >= -tileWidth) {
            y -= yAdder;
        } else {
            newBubble();
        }

        angle += angleAdder;
        x = (int) (Math.sin(MainPanel.radians * angle) * offset) + (Video.width / 2) - tileWidth;
        super.draw(c, (int) x, (int) y, tileWidth, tileHeight, bubbles[currentFrame]);
    }

    public void handleAction(float eventX, float eventY) {
//        if(isTouched()) return;
        if (touched) return;

        touched = false;
        // translate screen touch positions to video buffer size
        final int whereXPercentage = (int) (((eventX * 100) - MainPanel.screenWidth) / MainPanel.screenWidth);
        final int whereYPercentage = (int) (((eventY * 100) - MainPanel.screenHeight) / MainPanel.screenHeight);
//        Log.i(TAG, "----------" + whereXPercentage + " " + whereYPercentage);

        eventX = ((Video.width * whereXPercentage) / 100);
        eventY = ((Video.height * whereYPercentage) / 100) - Video.yOffset;
//        Log.i(TAG, "---------e:" + eventX + " " + eventY);

        if (eventX >= x && (eventX <= (x + tileWidth))) {
            if (eventY >= y && (eventY <= (y + tileHeight))) {
                // touched
//                setTouched(true);
                touched = true;
            }
        }

//            else {
////                setTouched(false);
//                touched = false;
//            }
//        } else {
////            setTouched(false);
//            touched = false;
//        }
    }

    public void newBubble() {
        x = MainPanel.RND.nextInt(Video.width - tileWidth);
        y = Video.height + tileHeight;
//        y = MainPanel.RND.nextInt(Video.height - tileHeight);
        offset = MainPanel.RND.nextDouble() * Video.width / 2;
        angleAdder = MainPanel.RND.nextDouble() * 5;
        yAdder = (MainPanel.RND.nextDouble() * 5) + 0.5;
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
