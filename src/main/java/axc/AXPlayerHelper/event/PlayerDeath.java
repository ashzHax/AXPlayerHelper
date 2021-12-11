package axc.AXPlayerHelper.event;

import axc.AXPlayerHelper.AXPlayerHelper;
import axc.AXPlayerHelper.utility.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.Map;

public class PlayerDeath implements Listener {

    private AXPlayerHelper plugin;
    public PlayerDeath(AXPlayerHelper p) {
        this.plugin = p;
    }

    @EventHandler
    public void HandlePlayerDeathEvent(PlayerDeathEvent event)
    {

        if (event.getEntityType() == EntityType.PLAYER) {
            Player eventPlayer = (Player) event.getEntity();
            Location deathLocation = eventPlayer.getLocation();
            EntityDamageEvent ede = eventPlayer.getLastDamageCause();
            Map<Message.DataType, String> messageData = new HashMap<Message.DataType, String>();

            String finalDeathMessage = event.getDeathMessage();
            finalDeathMessage = ""+ChatColor.GRAY+ChatColor.BOLD+"["+ChatColor.GREEN+ChatColor.BOLD+"!"+ChatColor.GRAY+ ChatColor.BOLD+"]"+ ChatColor.GRAY+" " + finalDeathMessage;
            finalDeathMessage = finalDeathMessage.replaceAll(eventPlayer.getName(),ChatColor.YELLOW+eventPlayer.getName()+ChatColor.GRAY+"");
            finalDeathMessage = finalDeathMessage + " "
//                    +ChatColor.GRAY+""+ChatColor.BOLD+"["+ChatColor.YELLOW+"W"+ChatColor.GRAY+":"+deathLocation.getWorld().getName()+""+ChatColor.GRAY+""+ChatColor.BOLD+"]"
                    +ChatColor.GRAY+""+ChatColor.BOLD+"["+ChatColor.YELLOW+"X"+ChatColor.GRAY+":"+deathLocation.getBlockX()+""+ChatColor.GRAY+""+ChatColor.BOLD+"]"
                    +ChatColor.GRAY+""+ChatColor.BOLD+"["+ChatColor.YELLOW+"Y"+ChatColor.GRAY+":"+deathLocation.getBlockY()+""+ChatColor.GRAY+""+ChatColor.BOLD+"]"
                    +ChatColor.GRAY+""+ChatColor.BOLD+"["+ChatColor.YELLOW+"Z"+ChatColor.GRAY+":"+deathLocation.getBlockZ()+""+ChatColor.GRAY+""+ChatColor.BOLD+"]";


//            data.put(Message.StringType.PLAYER_NAME, eventPlayer.getName());
//            data.put(Message.StringType.DEATH_CAUSE, ede.getCause().toString());
//            data.put(Message.StringType.DAMAGE, ExF.double_to_string_selective_decimal(ede.getDamage(), 1));
//            //data.put(Message.StringType.POSITION, Message.createLocationString(deathLocation));
//            data.put(Message.StringType.WORLD, deathLocation.getWorld().getName());
//            data.put(Message.StringType.POSX, ""+deathLocation.getBlockX());
//            data.put(Message.StringType.POSY, ""+deathLocation.getBlockY());
//            data.put(Message.StringType.POSZ, ""+deathLocation.getBlockZ());
//
//            event.setDeathMessage(Message.getConfigMessage(plugin, Message.LogType.PLAYER_DEATH, data));
                event.setDeathMessage(finalDeathMessage);
        }
    }


}
