package axc.AXPlayerHelper.events;

import axc.AXPlayerHelper.AXPlayerHelper;
import axc.AXPlayerHelper.utility.*;
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
            EntityDamageEvent ede = eventPlayer.getLastDamageCause();

            Map<Message.StringType, String> data = new HashMap<Message.StringType, String>();

            data.put(Message.StringType.PLAYER_NAME, eventPlayer.getName());
            data.put(Message.StringType.DEATH_CAUSE, ede.getCause().toString());
            data.put(Message.StringType.DAMAGE, ExF.double_to_string_selective_decimal(ede.getDamage(), 1));

            event.setDeathMessage(Message.getConfigMessage(plugin, Message.LogType.PLAYER_DEATH, data));
        }
    }


}
