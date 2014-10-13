package tk.netpork.bokalce;

import android.content.Context;

import com.peculiargames.andmodplug.MODResourcePlayer;

/**
 * Created by netpork on 10/3/14.
 */
public class ModMusic {

    private static int resource;
    private MODResourcePlayer modPlayer;
    private static Context context;

    public ModMusic(Context context, int modResource) {
        ModMusic.resource = modResource;
        ModMusic.context = context;
    }

    public void play() {
        modPlayer = new MODResourcePlayer(context);
        modPlayer.LoadMODResource(resource);
        modPlayer.setVolume(255);
        modPlayer.start();
    }

    public void stop() {
        modPlayer.StopAndClose();
        modPlayer = null;
    }

}
