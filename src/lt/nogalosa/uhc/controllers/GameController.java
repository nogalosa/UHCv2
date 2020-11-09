package lt.nogalosa.uhc.controllers;

import lt.nogalosa.uhc.Config;
import lt.nogalosa.uhc.Main;
import lt.nogalosa.uhc.Period;
import lt.nogalosa.uhc.models.UPlayer;
import lt.nogalosa.uhc.utils.Msg;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GameController {

    private static GameController instance;
    private Period period = Period.PREGAME;
    private int timeLeft = Integer.MAX_VALUE;

    public static GameController getInstance() {
        if(instance == null)
            instance = new GameController();
        return instance;
    }

    public GameController() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(period == Period.STARTING){
                    switch (timeLeft) {
                        case 60:
                        case 30:
                        case 15:
                        case 10:
                        case 5:
                        case 4:
                        case 3:
                        case 2:
                        case 1:
                            Msg.sa("§aŽaidimas prasideda už §l"+formatTimeleftKilm(timeLeft));
                            break;
                        case 0:
                            Msg.sa("§e§lŽAIDIMAS PRASIDEDA!");
                            setPeriod(Period.TELEPORTING);
                            break;
                    }
                }
                if(period == Period.TELEPORTING) {
                    if(timeLeft == 0){
                        setPeriod(Period.GRACE);
                    }
                }
                if(period == Period.GRACE) {
                    switch (timeLeft) {
                        case 300:
                        case 120:
                        case 60:
                        case 30:
                        case 15:
                        case 10:
                        case 5:
                        case 4:
                        case 3:
                        case 2:
                        case 1:
                            Msg.sa("§aGrace periodas baigiasi už: §l"+formatTimeleftKilm(timeLeft));
                            break;
                        case 0:
                            setPeriod(Period.GAME);
                            break;
                    }
                }
                if(period == Period.GAME) {
                    switch (timeLeft) {
                        case 600:
                        case 300:
                        case 60:
                            Msg.sa("§aIki visiško žemėlapio susitraukimo liko: §l"+formatTimeleftKilm(timeLeft));
                            break;
                        case 0:
                            setPeriod(Period.FINISHED);
                    }
                }
                timeLeft--;
            }
        }.runTaskTimer(Main.getInstance(), 20, 20);
    }

    public void setPeriod(Period period) {
        this.period = period;
        switch (period) {
            case PREGAME:
                break;
            case STARTING:
                this.timeLeft = 60;
                break;
            case TELEPORTING:
                this.timeLeft = Bukkit.getOnlinePlayers().size() / 4 + 3; // .25s for one player
                Msg.sa("§e§lVyksta žaidėjų teleportacija... Prašome palaukti.");
                this.teleportPlayers();
                break;
            case GRACE:
                this.timeLeft = 60 * 20;
                this.clearPlayers();
                for (int i = 0; i < 5; i++)
                    Msg.sa("§a§lGRACE PERIODAS PRASIDEDA!");
                break;
            case GAME:
                this.timeLeft = 60 * 100;
                for (int i = 0; i < 5; i++)
                    Msg.sa("§e§lGRACE PERIODAS PASIBAIGĖ!");
                Msg.sa("§c§lPasaulis pradėjo trauktis!");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 10 " + 60*1000);
                break;
            case FINISHED:
                this.timeLeft = Integer.MAX_VALUE;
                break;
            default:
                break;
        }
    }

    public void teleportPlayers() {
        int i = 0;
        for (final UPlayer pl : PlayerController.getInstance().getPlayers()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    pl.clearPlayer();
                    pl.teleportRandom();
                    Msg.console("Teleporting " + pl.getUuid());
                }
            }.runTaskLater(Main.getInstance(), i * 5);
            i++;
        }
    }

    public void clearPlayers() {
        for (final UPlayer pl : PlayerController.getInstance().getPlayers()) {
            pl.clearPlayer();
        }
    }

    public Period getPeriod() {
        return period;
    }

    public void startGame() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder center " + Config.WORLD_CENTER_X + " " + Config.WORLD_CENTER_Z);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set " + Math.max(Config.WORLD_SIZE_X, Config.WORLD_SIZE_Z));
        this.setPeriod(Period.STARTING);
    }

    private String formatTimeleft(int timeLeft) {
        String endM = "minutės";
        if((timeLeft / 60) % 10 == 1) {
            endM = "minutė";
        }
        if(((timeLeft / 60) > 10 && (timeLeft / 60) < 20) || (timeLeft / 60) % 10 == 0) {
            endM = "minučių";
        }
        String endS = "sekundės";
        if(timeLeft % 10 == 1) {
            endS = "sekundė";
        }
        if((timeLeft > 10 && timeLeft < 20) || timeLeft % 10 == 0) {
            endS = "sekundžių";
        }
        return (timeLeft / 60 != 0 ? timeLeft / 60 + " " + endM + " " : "") + (timeLeft % 60 != 0 ? (timeLeft % 60 + " " + endS) : "");
    }

    private String formatTimeleftKilm(int timeLeft) {
        String endM = "minučių";
        if((timeLeft / 60) % 10 == 1) {
            endM = "minutės";
        }
        String endS = "sekundžių";
        if(timeLeft % 10 == 1) {
            endS = "sekundės";
        }
        return (timeLeft / 60 != 0 ? timeLeft / 60 + " " + endM + " " : "") + (timeLeft % 60 != 0 ? (timeLeft % 60 + " " + endS) : "");
    }
}