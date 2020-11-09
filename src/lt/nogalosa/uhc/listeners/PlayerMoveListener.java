package lt.nogalosa.uhc.listeners;

import lt.nogalosa.uhc.Config;
import lt.nogalosa.uhc.Period;
import lt.nogalosa.uhc.controllers.GameController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void playerMoveListener(PlayerMoveEvent event) {
        double toX = event.getTo().getX();
        double toZ = event.getTo().getZ();
        double minX = Config.WORLD_CENTER_X - Config.WORLD_SIZE_X / 2 - 10;
        double minZ = Config.WORLD_CENTER_Z - Config.WORLD_SIZE_Z / 2 - 10;
        double maxX = Config.WORLD_CENTER_X + Config.WORLD_SIZE_X / 2 + 10;
        double maxZ = Config.WORLD_CENTER_Z + Config.WORLD_SIZE_Z / 2 + 10;
        if(toX < minX || toX > maxX || toZ < minZ || toZ > maxZ) {
            event.getPlayer().teleport(event.getFrom());
        }

        if(GameController.getInstance().getPeriod() == Period.PREGAME || GameController.getInstance().getPeriod() == Period.STARTING){
            if(event.getTo().getY() < Config.SPAWN_LOWEST_Y){
                event.getPlayer().teleport(event.getTo().getWorld().getSpawnLocation());
            }
        }
    }
}
