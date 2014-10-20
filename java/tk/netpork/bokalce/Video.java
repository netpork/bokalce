package tk.netpork.bokalce;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by netpork on 10/10/14.
 */
public class Video {

    public static final String TAG = "Video";

    public Canvas mCanvas;
    private Bitmap mBitmap;
    private MainPanel mPanel;
    public static final int width  = 320;
    public static final int height = 180;
    private static float aspect;
    public static BitmapFactory.Options optionsNoScale;

    public static Bitmap djidji;
    public static Bitmap nana, ahmad, ilkke;
    public static Bitmap[] bubbles = new Bitmap[6];

    public static final int djidjiX = (width - 96) / 2;
    public static final int djidjiY = (height - 98) / 2;

    public static int yOffset;

    public Video(MainPanel panel) {
        mPanel = panel;
        aspect = (float) MainPanel.screenWidth / width;
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        optionsNoScale = new BitmapFactory.Options();
        optionsNoScale.inDither = false;
        optionsNoScale.inPreferredConfig = Bitmap.Config.ARGB_8888;
        optionsNoScale.inScaled = false;

//        djidji = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.djidji, optionsNoScale);
//        nana = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.nana, optionsNoScale);
//        ahmad = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.ahmad, optionsNoScale);
//        ilkke = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.ilkke, optionsNoScale);
//        bobl1 = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles1, optionsNoScale);
//        bobl2 = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles2, optionsNoScale);
//        bobl3 = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles3, optionsNoScale);
//        bobl4 = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles4, optionsNoScale);
//        bobl5 = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles5, optionsNoScale);
//        bobl6 = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles6, optionsNoScale);

//        mCanvas.drawColor(Color.BLUE);
//        mCanvas.drawBitmap(ilkke, 0, 0, null);

//        Log.i(TAG, String.valueOf(MainPanel.screenWidth) + " " + String.valueOf(MainPanel.screenHeight));

        yOffset = (int) (((MainPanel.screenHeight - (height * aspect) + 0.5) / 2) / aspect);

//        Log.i(TAG, "======= yoffset: " + yOffset);
//        Log.i(TAG, "======= height: " + MainPanel.screenHeight);
//        Log.i(TAG, "======= aspect: " + aspect);
//        Log.i(TAG, "======= aspectt: " + (height * aspect));

    }

    public void update(Canvas canvas) {
//        Log.i(TAG, "aspect: " +  String.valueOf(aspect));
//        mBitmap.setDensity(Bitmap.DENSITY_NONE);
//        Paint paint = new Paint();
//        paint.setDither(false);
//        paint.setAntiAlias(false);
//        paint.setFilterBitmap(false);
        canvas.save();
//        canvas.translate(0, yOffset);
        canvas.scale(aspect, aspect);
        canvas.drawBitmap(mBitmap, 0, yOffset, null);
        canvas.restore();
    }

}
