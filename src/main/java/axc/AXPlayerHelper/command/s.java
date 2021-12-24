package axc.AXPlayerHelper.command;

import axc.AXPlayerHelper.AXPlayerHelper;
import axc.AXPlayerHelper.utility.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class s implements CommandExecutor {

    private AXPlayerHelper plugin;
    public s(AXPlayerHelper pl){
        this.plugin = pl;
    }

    private void setSpectate(boolean flag, Player commandPlayer)
    {
        FileManager fm = new FileManager(plugin, commandPlayer.getUniqueId().toString());
        if(flag) {
            Location pLoc = commandPlayer.getLocation();
            fm.configuration.set("world", pLoc.getWorld().getUID().toString());
            fm.configuration.set("x", pLoc.getBlockX());
            fm.configuration.set("y", pLoc.getBlockY());
            fm.configuration.set("z", pLoc.getBlockZ());
            fm.configuration.set("yaw", pLoc.getYaw());
            fm.configuration.set("pitch", pLoc.getPitch());
            fm.save();

            commandPlayer.setGameMode(GameMode.SPECTATOR);
        } else {
            Location pLoc = new Location(plugin.getServer().getWorld(UUID.fromString(fm.configuration.getString("world"))),
                    fm.configuration.getDouble("x"),
                    fm.configuration.getDouble("y"),
                    fm.configuration.getDouble("z"),
                    (float)fm.configuration.getDouble("yaw"),
                    (float)fm.configuration.getDouble("pitch"));

            commandPlayer.teleport(pLoc);
            commandPlayer.setGameMode(GameMode.SURVIVAL);
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("player only command");
            return false;
        }

        Player commandPlayer = (Player) commandSender;
        switch (commandPlayer.getGameMode()) {
            case CREATIVE:
                setSpectate(true, commandPlayer);
                break;
            case SURVIVAL:
                setSpectate(true, commandPlayer);
                break;
            case ADVENTURE:
                setSpectate(true, commandPlayer);
                break;
            case SPECTATOR:
                setSpectate(false, commandPlayer);
                break;
            default:
                commandPlayer.sendMessage(ChatColor.RED + "internal API failure.");
                return false;
        }

        return false;
    }
}
