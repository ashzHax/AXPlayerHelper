package axc.AXPlayerHelper.events;

import axc.AXPlayerHelper.AXPlayerHelper;
import axc.AXPlayerHelper.utility.Message;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    private AXPlayerHelper plugin;
    public PlayerDeath(AXPlayerHelper p) {
        this.plugin = p;
    }

    private static String double_get_only_selective_decimal(double value, int decimalPoint)
    {
        return String.format("%."+decimalPoint+"f", value);
    }

    @EventHandler
    public void HandlePlayerDeathEvent(PlayerDeathEvent event)
    {
        if (event.getEntityType() == EntityType.PLAYER) {
            Player eventPlayer = (Player) event.getEntity();

            EntityDamageEvent de = eventPlayer.getLastDamageCause();

            event.setDeathMessage(Message.getConfigMessage(plugin, Message.LogType.PLAYER_DEATH, eventPlayer.getName(), de.getCause().toString()+","+double_get_only_selective_decimal(de.getDamage(), 1)));
        }
    }


}
