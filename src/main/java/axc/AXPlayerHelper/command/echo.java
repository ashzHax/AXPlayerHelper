package axc.AXPlayerHelper.command;

import axc.AXPlayerHelper.utility.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class echo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player commandPlayer = (Player) commandSender;

        if(!commandPlayer.isOp()) {
//            Message.getConfigMessage();
        }



        return false;
    }
}
