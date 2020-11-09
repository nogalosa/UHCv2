package lt.nogalosa.uhc.listeners;

import lt.nogalosa.uhc.Period;
import lt.nogalosa.uhc.controllers.GameController;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void playerRespawnListener (PlayerRespawnEvent event) {
        if(GameController.getInstance().getPeriod() == Period.PREGAME || GameController.getInstance().getPeriod() == Period.STARTING) {
            event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        } else {
            event.getPlayer().kickPlayer("Jūs žuvote!");
        }
    }
}
