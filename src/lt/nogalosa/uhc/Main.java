package lt.nogalosa.uhc;

import lt.nogalosa.uhc.controllers.CommandController;
import lt.nogalosa.uhc.listeners.*;
import lt.nogalosa.uhc.utils.Msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        Bukkit.getPluginManager().registerEvents(new ServerListPingListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CommandController.onCommand(sender, command, label, args);
        return false;
    }
}
