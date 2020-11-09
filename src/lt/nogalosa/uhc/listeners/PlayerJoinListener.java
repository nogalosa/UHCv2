package lt.nogalosa.uhc.listeners;

import lt.nogalosa.uhc.Period;
import lt.nogalosa.uhc.controllers.GameController;
import lt.nogalosa.uhc.controllers.PlayerController;
import lt.nogalosa.uhc.models.UPlayer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoinListener(PlayerJoinEvent ev) {
        UPlayer uPlayer = PlayerController.getInstance().findPlayer(ev.getPlayer());
        if(uPlayer == null){
            PlayerController.getInstance().addPlayer(ev.getPlayer());
        } else {
            uPlayer.setDisconnected(false);
        }

        if(GameController.getInstance().getPeriod() == Period.PREGAME){
            if(!ev.getPlayer().getInventory().contains(Material.IRON_SWORD)) {
                ev.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_SWORD));
            }
        }
    }
}
