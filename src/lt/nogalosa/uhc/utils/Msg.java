package lt.nogalosa.uhc.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class Msg {

    public static void console(String msg) {
        System.out.println("[UHC] "+msg);
    }

    public static void s (CommandSender to, String msg) {
        to.sendMessage("§6[§eUHC§6] §r" + msg);
    }

    public static void sa(String msg) {
        Bukkit.broadcastMessage("§6[§eUHC§6] §r" + msg);
    }

}
