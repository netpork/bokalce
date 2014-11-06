package tk.netpork.bokalce;

import android.graphics.Canvas;

/**
 * Created by tvweb2 on 10/17/14.
 */
public class GingerPart implements Part {
    private MainPanel mPanel;
    private Canvas mCanvas;
    private Fade mFade;
    public boolean doStart = false;
    public boolean doEnd = false;

    public GingerPart(MainPanel panel) {
        mPanel = panel;
        mCanvas = mPanel.mVideo.mCanvas;
        mFade = new Fade(0, 255, 0xffffff, 0.05f);
    }

    @Override
    public void execute() {
        mPanel.mGinger.update(mCanvas);
        if (doStart) start();
        if (doEnd) end();
    }

    @Override
    public void start() {}

    @Override
    public void end() {
        boolean f = mFade.fadeScreen(mCanvas);
        if (!f)  {
            MainPanel.nextPart();
            mFade = null;
        }
    }

    @Override
    public void enableEnd() {
        doEnd = true;
    }
}
