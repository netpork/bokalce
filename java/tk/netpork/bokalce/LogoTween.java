package tk.netpork.bokalce;

import android.graphics.Canvas;
import android.graphics.Color;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import penner.easing.Bounce;

/**
 * Created by netpork on 11/6/14.
 */
public class LogoTween {
    private MainPanel mPanel;
    private Video mVideo;
    private TweenObject mTween;
    private static final String TAG = "Logo Tween";

    public LogoTween(MainPanel panel, Video video) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mPanel = panel;
        mVideo = video;
        mTween = new TweenObject(Bounce.class, 160, -64, 160, 80, 1000);
    }

    public void update(Canvas canvas) throws InvocationTargetException, IllegalAccessException {
        mVideo.mCanvas.drawColor(Color.WHITE);
        float y = 0;
        if (mTween.time < mTween.duration) {
//            y = Bounce.easeOut(mTween.time, mTween.startY, mTween.changeY, mTween.duration);
            y = (Float) mTween.method.invoke(null, mTween.time, mTween.startY, mTween.changeY, mTween.duration);
            canvas.drawBitmap(Video.djidji, Video.djidjiX, y, null);
            mTween.time += MainThread.getTimeDiff();
        } else {
            canvas.drawBitmap(Video.djidji, Video.djidjiX, mTween.targetY, null);
        }

//        Log.i(TAG, "logo y:" + y);
    }

    public class TweenObject {
        public float startX, startY, targetX, targetY, changeX, changeY, time, duration;
        public String tweenMethod;
        public Class tweenClass;
        public Method method;
//        public Class[] params = new Class[4];
//        public Object obj;

        public TweenObject(Class tweenClass, float startX, float startY, float targetX, float targetY, float duration) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            this.startX = startX;
            this.startY = startY;
            this.targetX = targetX;
            this.targetY = targetY;
            this.changeX = targetX - startX;
            this.changeY = targetY - startY;
            this.time = 0;
            this.duration = duration;
//            this.tweenClass = Class.forName(tweenClass);
//            obj = this.tweenClass.newInstance();
//            params[0] = Float.TYPE;
//            params[1] = Float.TYPE;
//            params[2] = Float.TYPE;
//            params[3] = Float.TYPE;

            try {
                this.method = tweenClass.getDeclaredMethod("easeOut", new Class[] {Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE });
                this.method.setAccessible(true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}