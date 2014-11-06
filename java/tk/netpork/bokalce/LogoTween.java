package tk.netpork.bokalce;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import penner.easing.Bounce;
import penner.easing.Elastic;

/**
 * Created by netpork on 11/6/14.
 */
public class LogoTween {
    private MainPanel mPanel;
    private Video mVideo;
    private TweenObject mTween;
    private static final String TAG = "Logo Tween";

    public LogoTween(MainPanel panel, Video video) {
        mPanel = panel;
        mVideo = video;
        mTween = new TweenObject(160, -64, 160, 120, 5000);
    }

    public void update(Canvas canvas) {
        mVideo.mCanvas.drawColor(Color.BLACK);
        float y = 0;
        if (mTween.time < mTween.duration) {
            y = Bounce.easeOut(mTween.time, mTween.startY, mTween.changeY, mTween.duration);
            canvas.drawBitmap(Video.djidji, Video.djidjiX, y, null);
            mTween.time += (float) MainThread.getTimeDiff();
        } else {
            canvas.drawBitmap(Video.djidji, Video.djidjiX, mTween.targetY, null);
        }

        Log.i(TAG, "logo y:" + y);
    }

    public static float  easeOut(float t,float b , float c, float d) {
        if ((t/=d) < (1/2.75f)) {
            return c*(7.5625f*t*t) + b;
        } else if (t < (2/2.75f)) {
            return c*(7.5625f*(t-=(1.5f/2.75f))*t + .75f) + b;
        } else if (t < (2.5/2.75)) {
            return c*(7.5625f*(t-=(2.25f/2.75f))*t + .9375f) + b;
        } else {
            return c*(7.5625f*(t-=(2.625f/2.75f))*t + .984375f) + b;
        }
    }

    public static float easeInOut(float t, float b, float c, float d) {
        if (t == 0) return b;  if ((t/=d/2)==2) return b + c;
        final float p = d * (.3f * 1.5f);
        final float a = c;
        final float s = p / 4;
        if (t < 1) return - .5f * (a * (float)Math.pow(2, 10 * (t -= 1)) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p )) + b;
        return a * (float)Math.pow(2,-10*(t-=1)) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p )*.5f + c + b;
    }

        public class TweenObject {
            public float startX, startY, targetX, targetY, changeX, changeY, time, duration;

            public TweenObject(float startX, float startY, float targetX, float targetY, float duration) {
                this.startX = startX;
                this.startY = startY;
                this.targetX = targetX;
                this.targetY = targetY;
                this.changeX = targetX - startX;
                this.changeY = targetY - startY;
                this.time = 0;
                this.duration = duration;
            }
        }

}