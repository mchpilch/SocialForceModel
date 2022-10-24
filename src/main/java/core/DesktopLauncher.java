package core;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import core.Experiment.Boot;
import core.Lab.WallRepulsion;
import core.Model.SocialForceModel;

public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setIdleFPS(60);
        config.useVsync(true);
        config.setTitle("SFM - Michał Pilch");

        config.setWindowedMode(1600,900);
        //config.setWindowedMode(1920,1080);
        new Lwjgl3Application(new SocialForceModel(), config);
    }
}
