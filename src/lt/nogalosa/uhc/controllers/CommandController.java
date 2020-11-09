package lt.nogalosa.uhc.controllers;

import lt.nogalosa.uhc.utils.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandController {

    public static void onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("uhc")) {
            if(!sender.isOp()) {
                Msg.s(sender, "Neturite teisės šiai komandai");
                return;
            }
            if(args.length == 0) {
                Msg.s(sender, "UHCv2");
                Msg.s(sender, "/uhc start - pradėti žaidimą");
            }
            if(args.length > 0) {
                if(args[0].equalsIgnoreCase("start")){
                    Msg.s(sender, "Žaidimas pradedamas.");
                    GameController.getInstance().startGame();
                }
            }
        }
    }
}
