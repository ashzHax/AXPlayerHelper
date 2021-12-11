package axc.AXPlayerHelper.event;

import axc.AXPlayerHelper.AXPlayerHelper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class Explosion implements Listener {

    private AXPlayerHelper plugin;
    public Explosion(AXPlayerHelper p) {this.plugin = p;}

    @EventHandler
    public void HandleExplosion(EntityExplodeEvent event) {
        //plugin.getServer().broadcastMessage("debug: explooooosion ("+event.getLocation().toString()+")");
    }
}
