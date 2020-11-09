package lt.nogalosa.uhc.listeners;

import lt.nogalosa.uhc.controllers.GameController;
import lt.nogalosa.uhc.controllers.PlayerController;
import lt.nogalosa.uhc.models.UPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;

public class PlayerLoginListener implements Listener {

    @EventHandler
    public void playerLoginListener(PlayerLoginEvent event) {
        UPlayer player = PlayerController.getInstance().findPlayer(event.getPlayer());
        if(player == null || player.isDead()){
            switch(GameController.getInstance().getPeriod()) {
                case TELEPORTING:
                case GRACE:
                case GAME:
                case FINISHED:
                    event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                    event.setKickMessage("Atsiprašome, į žaidimą prisijungti nebegalite.");
            }
        }
    }
}
