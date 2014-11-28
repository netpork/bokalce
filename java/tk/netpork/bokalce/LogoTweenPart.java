package tk.netpork.bokalce;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by netpork on 11/6/14.
 */
public class LogoTweenPart implements Part {
    private MainPanel mPanel;

    public LogoTweenPart(MainPanel panel) {
        mPanel = panel;
    }

    @Override
    public void execute() {
        try {
            mPanel.mLogoTween.update(mPanel.mVideo.mCanvas);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public void enableEnd() {

    }
}
