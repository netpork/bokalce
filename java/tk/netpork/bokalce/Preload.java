package tk.netpork.bokalce;

import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by netpork on 10/20/14.
 */
public class Preload extends AsyncTask <Integer, Void, Integer>{
    private static final String TAG = "Preload";
    private MainPanel mPanel;

    public Preload(MainPanel panel) {
        mPanel = panel;
    }

    @Override
    protected Integer doInBackground(Integer... something) {

        Video.djidji = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.djidji, Video.optionsNoScale);
        Video.nana = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.nana, Video.optionsNoScale);
        Video.ahmad = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.ahmad, Video.optionsNoScale);
        Video.ilkke = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.ilkke, Video.optionsNoScale);

        Video.bubbles[0] = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles1, Video.optionsNoScale);
        Video.bubbles[1] = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles2, Video.optionsNoScale);
        Video.bubbles[2] = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles3, Video.optionsNoScale);
        Video.bubbles[3] = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles4, Video.optionsNoScale);
        Video.bubbles[4] = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles5, Video.optionsNoScale);
        Video.bubbles[5] = BitmapFactory.decodeResource(mPanel.getResources(), R.drawable.boobles6, Video.optionsNoScale);

        MainPanel.plopSound1 = MediaPlayer.create(MainPanel.context, R.raw.plop0);
        MainPanel.plopSound2 = MediaPlayer.create(MainPanel.context, R.raw.plop1);
        MainPanel.plopSound3 = MediaPlayer.create(MainPanel.context, R.raw.plop2);

        MainPanel.modPlayer = new ModMusic(MainPanel.context, R.raw.xality);

//        Log.i(TAG, "preload finished!");
        return 1;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

//        Log.i(TAG, "finished from postexecute");
        mPanel.startMusicAndThread();

    }
}
