package tk.netpork.bokalce;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by netpork on 10/18/14.
 */
public class StrechkoPart implements Part {
    private static final String TAG = "StrechkoPart";
    private MainPanel mPanel;
    private Canvas mCanvas;
    private Fade mFade;
    public boolean doStart = true;
    public boolean doEnd = false;
    private static int subPartTimeout = 0;

    public StrechkoPart(MainPanel panel) {
        mPanel = panel;
        mCanvas = mPanel.mVideo.mCanvas;
        mFade = new Fade(255, 0, 0xffffff, 0.05f);
    }

    @Override
    public void execute() {
        if (subPartTimeout > 0 && subPartTimeout < 5000) {
            Strechko.stretch(mCanvas);
        } else if (subPartTimeout > 5000 && subPartTimeout < 10000) {
            Strechko.stretchAhmad(mCanvas);
        } else if (subPartTimeout > 10000 && subPartTimeout < 15000) {
            Strechko.stretchIlkke(mCanvas);
        }

        if (subPartTimeout > 15000) subPartTimeout = 0;
        subPartTimeout += MainThread.getTimeDiff();

        if (doStart) start();
        if (doEnd) end();

//        Log.i(TAG, "timeout: --- " + subPartTimeout);
    }

    @Override
    public void start() {
        boolean f = mFade.fadeScreen(mCanvas);
        if (!f) {
            doStart = false;
            mFade = null;
        }
    }

    @Override
    public void end() {

    }

    @Override
    public void enableEnd() {
        doEnd = true;
    }
}
