package tk.netpork.bokalce;

/**
 * Created by netpork on 10/15/14.
 */
public class Tools {

    private static int tweenStart, tweenEnd, tweenCurrent;
    private static float tweenEase = 0.5f;

    public static void tween(int start, int end, float ease) {
        tweenStart = start;
        tweenEnd = end;
        tweenEase = ease;
    }

    public static int tween() {
        int delta = tweenEnd - tweenStart;
        tweenCurrent = (int) (delta * tweenEase);
        return tweenCurrent;
    }
}
