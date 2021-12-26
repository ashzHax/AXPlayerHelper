package axc.AXPlayerHelper;

import axc.AXPlayerHelper.command.*;
import axc.AXPlayerHelper.event.*;
import axc.AXPlayerHelper.utility.SpigotOutputLog;
import org.bukkit.plugin.java.JavaPlugin;
import axc.AXPlayerHelper.utility.Message;

@SuppressWarnings("unused")
public class AXPlayerHelper extends JavaPlugin {

    public Message msg;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();       // return void. cannot check if function failed. :(
        msg = new Message(this);    // class Message() must be initialized after saveDefaultConfig()

        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this),this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuit(this),this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
//        this.getServer().getPluginManager().registerEvents(new Explosion(this), this);
        this.getCommand("echo").setExecutor(new echo(this));
        this.getCommand("s").setExecutor(new s(this));
        this.getCommand("test").setExecutor(new test(this));

    }

    @Override
    public void onDisable() {

    }
}
