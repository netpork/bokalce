package tk.netpork.bokalce;

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
        mPanel.mLogoTween.update(mPanel.mVideo.mCanvas);
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
