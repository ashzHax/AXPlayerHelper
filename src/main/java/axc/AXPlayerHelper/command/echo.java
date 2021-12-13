package axc.AXPlayerHelper.command;

import axc.AXPlayerHelper.AXPlayerHelper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class echo implements CommandExecutor {

    private AXPlayerHelper plugin;

    public echo(AXPlayerHelper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {
            commandSender.sendMessage("player only command");
            return false;
        }

        Player commandPlayer = (Player) commandSender;



        plugin.getServer().broadcastMessage(ChatColor.GRAY + "[" + commandSender.getName() + "] "
                + ChatColor.BOLD + "" + ChatColor.GRAY+"X:" + ChatColor.YELLOW + commandPlayer.getEyeLocation().getBlockX()
                + ChatColor.BOLD + "" + ChatColor.GRAY+" Y:" + ChatColor.YELLOW + commandPlayer.getEyeLocation().getBlockY()
                + ChatColor.BOLD + "" + ChatColor.GRAY+" Z:" + ChatColor.YELLOW + commandPlayer.getEyeLocation().getBlockZ());

        return false;
    }
}
