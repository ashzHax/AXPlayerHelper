package axc.AXPlayerHelper;

import axc.AXPlayerHelper.events.*;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class AXPlayerHelper extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
    }

    @Override
    public void onDisable() {

    }
}
