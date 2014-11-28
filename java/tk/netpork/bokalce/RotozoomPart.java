package tk.netpork.bokalce;

import android.graphics.Canvas;

/**
 * Created by netpork on 11/28/14.
 */
public class RotozoomPart implements Part {
    public boolean doStart = false;
    public boolean doEnd = false;
    private MainPanel mPanel;
    private Canvas mCanvas;

    public RotozoomPart(MainPanel panel) {
        mPanel = panel;
        mCanvas = mPanel.mVideo.mCanvas;
    }

    @Override
    public void execute() {
        mPanel.mRotozoom.update(mCanvas);
        if (doStart) start();
        if (doEnd) end();
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public void enableEnd() {
        doEnd = true;
    }
}
