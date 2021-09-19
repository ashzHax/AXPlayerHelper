package axc.AXPlayerHelper;

import axc.AXPlayerHelper.event.*;
import axc.AXPlayerHelper.utility.SpigotOutputLog;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class AXPlayerHelper extends JavaPlugin {
    @Override
    public void onEnable() {


        this.getServer().getConsoleSender().sendMessage("started on logging catcher? ithink");
        new SpigotOutputLog();

        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
        this.getServer().getPluginManager().registerEvents(new Explosion(this), this);

    }

    @Override
    public void onDisable() {

    }
}
