package axc.AXPlayerHelper.command;

import axc.AXPlayerHelper.AXPlayerHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class test implements CommandExecutor {
    private final AXPlayerHelper plugin;

    public test(AXPlayerHelper pl)
    {
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        this.plugin.msg.msgConfigMap.forEach((str, msg) -> {
            commandSender.sendMessage("=====================================================");
            commandSender.sendMessage("String: "+str);
            commandSender.sendMessage("enable: "+msg.enable);
            commandSender.sendMessage("custom_enable: "+msg.custom_enable);
            commandSender.sendMessage("custom_string: "+msg.custom_string);
            commandSender.sendMessage("append_enable: "+msg.append_enable);
            commandSender.sendMessage("append_prefix: "+msg.append_prefix);
            commandSender.sendMessage("append_string_color: "+msg.append_string_color);
            commandSender.sendMessage("optional_player_name: "+msg.optional_player_name);
            commandSender.sendMessage("=====================================================");
        });
        return false;
    }
}
