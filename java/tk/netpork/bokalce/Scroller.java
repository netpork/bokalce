package tk.netpork.bokalce;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

public class Scroller {
    public static final String TAG = MainThread.class.getCanonicalName();

    private final MainPanel mPanel;

    public final int fontWidth, fontHeight;
    private float scrollThreshold;

    private final float realFontWidth, realFontHeight;

    public Bitmap fontBitmap;

    private Canvas scrollCanvas;
    private Bitmap scrollBitmap;
    private int bitmapScrollIndex = 0;
    private int textScrollIndex = 0;
    final int charsPerLine;
    private int counter = 0;

    private Camera mCamera;
    private Matrix mMatrix;

    private int[] pixels;
//    private final Paint scrollerBackground;

    private Rect src = new Rect();
    private Rect dst = new Rect();


    public static final String fontMap = " abcdefghijklmnopqrstuvwxyz0123456789!,.:;?*()-";

/*  public String scrollText = "ispechi sedam kg paprika,oljushtitii staviti da se ocedi." +
            "podrazumeva se da znate,paprika se dooobro ispeche,sa svih strana,jer,shto je bolje pechena," +
            "brzhe che se uprzhiti.najbolje je pechene paprike stavljati u kese,koje su u kofi," +
            "napunjena kesa se zavezhe,a kofa pokrije krpom,kako bi se paprike podusparile i lepo" +
            " oljushtile.meshati u jednom pravcu,kako bi se paprika sama raspala u rezance," +
            "kao da je cepkana prethodno.na kraju dodati soli po ukusu,dva do tri seckana chena belog " +
            "luka i seckanu vezu pershuna...            ";
*/

    public final String scrollText = "              #4 kitty, kitty,   #2 soft and white, likes to sleep all through the night. wake up kitty, it’s morning now. wake up kitty, meow, meow, meow.";

    public Scroller(int fontWidth, int fontHeight, MainPanel panel) {
        this.fontBitmap = fontBitmap;

        fontBitmap = BitmapFactory.decodeResource(panel.getResources(), R.drawable.font_cute, Video.optionsNoScale);
//        this.fontWidth = (int) (fontWidth * MainPanel.density);
//        this.fontHeight = (int) (fontHeight * MainPanel.density);

        realFontWidth = fontWidth;
        realFontHeight = fontWidth;

        this.fontWidth = fontWidth;
        this.fontHeight = fontHeight;

        this.scrollThreshold = 4;

        mPanel = panel;
        charsPerLine = Video.width / this.fontWidth;

//      injectSpaces();

        // make scroll bitmap and canvas with SCREEN_WIDTH + FONT_WIDTH on both sides
        scrollBitmap = Bitmap.createBitmap(Video.width + (this.fontWidth * 2), this.fontHeight, Bitmap.Config.ARGB_8888);
        scrollCanvas = new Canvas(scrollBitmap);
        pixels = new int[(Video.width + this.fontWidth) * this.fontHeight];

//        scrollerBackground = new Paint();
//        scrollerBackground.setARGB(125, 0, 0, 0);

//      Log.i(TAG, "fonto: " + this.fontWidth + " " + this.fontHeight);

//        mCamera = new Camera();
//        mMatrix = new Matrix();

    }

    private int getCharPosition(int index) {
        char c = scrollText.charAt(index);

        if (c == '#') {
            // new scroll speed, skip #speed
            textScrollIndex += 3;
            scrollThreshold = Character.getNumericValue(scrollText.charAt(index + 1));
//            Log.i(TAG, "scrollt=========" + scrollThreshold);
            c = scrollText.charAt(index + 3);
        }

        return fontMap.indexOf(c);
    }

    private void scroll(Canvas c) {
        // do we need to draw a new char from a text
        if (bitmapScrollIndex <= 0) {
            drawNewChar();
            bitmapScrollIndex = fontWidth;

            if (textScrollIndex < scrollText.length() - 1) {
                textScrollIndex++;
            } else {
                textScrollIndex = 0;
            }
        }

        bitmapScrollIndex -= scrollThreshold;
    }

    private void drawNewChar() {
        final int pos = getCharPosition(textScrollIndex);
//        Log.i(TAG, "================= pos: " + pos);
//      Paint paint = new Paint();
//      paint.setDither(true);
//      Log.i(TAG, "pos: " + fontWidth);
        scrollBitmap.getPixels(pixels, 0, Video.width + fontWidth, fontWidth, 0, Video.width + fontWidth, fontHeight);
//      scrollCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//      scrollCanvas.drawColor(Color.BLACK);
        scrollBitmap.eraseColor(Color.TRANSPARENT);

        scrollCanvas.drawBitmap(pixels, 0, Video.width + fontWidth, 0, 0, Video.width + fontWidth, fontHeight, true, null);

//      Rect src = new Rect(pos * fontWidth, 0, pos * fontWidth + fontWidth, fontHeight);
//        Rect src = new Rect((int) (pos * realFontWidth), 0, (int) (pos * realFontWidth + realFontWidth), fontHeight);

        src.set((int) (pos * realFontWidth), 0, (int) (pos * realFontWidth + realFontWidth), fontHeight);

//      Rect src = new Rect(0, (int) (pos * (22 * MainPanel.density)), fontWidth, (int) (pos * (22 * MainPanel.density)+ 22));

//        Rect dst = new Rect(MainPanel.screenWidth + fontWidth, 0, MainPanel.screenWidth + (fontWidth << 1), fontHeight);
        dst.set(Video.width + fontWidth, 0, Video.width + (fontWidth << 1), fontHeight);

        scrollCanvas.drawBitmap(fontBitmap, src, dst, null);
    }

/*  public void drawChars(Canvas c) {
//      char[] scrolla = scrollText.toCharArray();
        int pos, textIndex = textScrollIndex;
        int x = 0, y = 0;

        Log.i(TAG, "bitmappp: " + fontBitmap.getWidth() + " " + fontBitmap.getHeight() + " " + fontBitmap.getDensity() + " " + MainPanel.density);

        for (int i = 0; i < 10; i++) {
            pos = getCharPosition(textIndex);
            Rect src = new Rect((int) (pos * fontWidth), 0, (int) (pos * fontWidth + fontWidth), fontHeight);
            Rect dst = new Rect(x, y, x + fontWidth, y + fontHeight);
            c.drawBitmap(fontBitmap, src, dst, null);
            x += fontWidth;
            textIndex++;
        }
    }
*/

    private void draw(Canvas c) {
        final int offset = (fontWidth - 1) - bitmapScrollIndex;

        // draw scroller transparent background
//        c.drawRect(0, MainPanel.getLogoY(), MainPanel.screenWidth, MainPanel.getLogoY() + fontHeight, scrollerBackground);

//        Rect src = new Rect(offset, 0, MainPanel.screenWidth + offset, fontHeight);
//        Rect dst = new Rect(0, MainPanel.getLogoY(), MainPanel.screenWidth, MainPanel.getLogoY() + fontHeight);
        src.set(offset, 0, Video.width + offset, fontHeight);
        dst.set(0, 150, Video.width, 150 + fontHeight);


        c.drawBitmap(scrollBitmap, src, dst, null);

//      scrollCanvas.drawColor(Color.TRANSPARENT);
//      scrollCanvas.drawColor(0x10ff0000);
//      scrollBitmap.getPixels(pixels, 0, MainPanel.screenWidth, offset, 0, MainPanel.screenWidth, fontHeight);
//
//      c.drawBitmap(pixels, 0, MainPanel.screenWidth, 0, MainPanel.getLogoY(), MainPanel.screenWidth, fontHeight, true, null);


    }

    public void update(Canvas c) {
//      c.drawColor(Color.WHITE);

//      if (counter % 3 == 0) {
//          scroll(c);
//      }

//      mCamera.save();
//      mCamera.rotateY(MainPanel.getOrientationAngle());
//      mCamera.getMatrix(mMatrix);
//      mMatrix.preTranslate(-MainPanel.screenWidth / 2, -MainPanel.screenHeight / 2);
//      mMatrix.postTranslate(MainPanel.screenWidth / 2, MainPanel.screenHeight / 2);
//        c.save();
//      c.concat(mMatrix);
//        c.rotate(MainPanel.getOrientationAngle(), MainPanel.screenWidth / 2, MainPanel.screenHeight / 2);
//        mPanel.drawLogo(c);
        scroll(c);
        draw(c);
//        c.restore();
//      mCamera.restore();
//      counter++;

//      drawChars(c);
    }

/*  private void injectSpaces() {
        for (int i = 0; i < charsPerLine - 1; i++) {
            scrollText = " " + scrollText;
        }
    }
*/

}