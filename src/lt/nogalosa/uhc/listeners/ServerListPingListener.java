package lt.nogalosa.uhc.listeners;

import lt.nogalosa.uhc.Period;
import lt.nogalosa.uhc.controllers.GameController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener {

    @EventHandler
    public void serverListPingListener(ServerListPingEvent event) {
        if(GameController.getInstance().getPeriod() == Period.PREGAME){
            event.setMotd("§6Redfox Cloud Ultra Hardcore Turnyras\n§aLaukiame prisijungiant!");
        } else if(GameController.getInstance().getPeriod() == Period.STARTING){
            event.setMotd("§6Redfox Cloud Ultra Hardcore Turnyras\n§eGreičiau! Žaidimas prasideda!");
        } else {
            event.setMotd("§6Redfox Cloud Ultra Hardcore Turnyras\n§eŽaidimas vyksta!");
        }
    }
}
