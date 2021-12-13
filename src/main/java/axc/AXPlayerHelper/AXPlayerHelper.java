package axc.AXPlayerHelper;

import axc.AXPlayerHelper.command.echo;
import axc.AXPlayerHelper.event.*;
import axc.AXPlayerHelper.utility.SpigotOutputLog;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class AXPlayerHelper extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
//        this.getServer().getPluginManager().registerEvents(new Explosion(this), this);
        this.getCommand("echo").setExecutor(new echo(this));
    }

    @Override
    public void onDisable() {

    }
}
