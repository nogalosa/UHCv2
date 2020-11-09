package lt.nogalosa.uhc;

import lt.nogalosa.uhc.listeners.PlayerJoinListener;
import lt.nogalosa.uhc.utils.Msg;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Main extends JavaPlugin {

    private static Main instance;
    private static Random random = new Random();

    public static Main getInstance() {
        return instance;
    }

    public static Random getRandom() {
        return random;
    }

    public void onEnable() {
        Msg.console("Įskiepis įjungiamas...");
        this.instance = this;

        registerListeners();

        Msg.console("Įskiepis sėkmingai įjungtas.");
    }

    public void onDisable() {
        Msg.console("Įskiepis išjungiamas...");
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }
}
