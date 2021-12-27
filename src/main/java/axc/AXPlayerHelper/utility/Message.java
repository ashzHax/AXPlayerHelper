package axc.AXPlayerHelper.utility;
import axc.AXPlayerHelper.AXPlayerHelper;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Message {

    // general attributes
    private AXPlayerHelper plugin;

    // configuration attributes
    private FileConfiguration config;
    public Map<String, MessageTypeConfig> msgConfigMap = new HashMap<String, MessageTypeConfig>();

    // data list
    public enum DataType {
        PLAYER_NAME,
        POSITION,
        DAMAGE,
        DEATH_CAUSE,
        WORLD,
        POSX,
        POSY,
        POSZ,
    };

    public Message(AXPlayerHelper pl)
    {
        ConfigurationSection sc;
        this.plugin = pl;
        this.config = pl.getConfig();

        // get all configuration
        sc = this.config.getConfigurationSection("message");
        sc.getKeys(false).forEach((String key) -> {
            MessageTypeConfig newConfig = new MessageTypeConfig();
            {
                newConfig.enable                = sc.getBoolean(    key + ".enable");
                newConfig.custom_enable         = sc.getBoolean(    key + ".custom.enable");
                newConfig.custom_string         = sc.getString(     key + ".custom.string");
                newConfig.append_enable         = sc.getBoolean(    key + ".append.enable");
                newConfig.append_prefix         = sc.getString(     key + ".append.prefix");
                newConfig.append_suffix         = sc.getString(     key + ".append.suffix");
                newConfig.append_string_color   = sc.getString(     key + ".append.string_color");
                newConfig.optional_player_name  = sc.getString(     key + ".optional.player_name");
            }
            this.msgConfigMap.put(key, newConfig);
        });
    }

    private String convertColor(String original)
    {
        String msg = original;

        msg = msg.replaceAll("\\{AQUA\\}",            ChatColor.AQUA+"");
        msg = msg.replaceAll("\\{BLACK\\}",           ChatColor.BLACK+"");
        msg = msg.replaceAll("\\{BLUE\\}",            ChatColor.BLUE+"");
        msg = msg.replaceAll("\\{BOLD\\}",            ChatColor.BOLD+"");
        msg = msg.replaceAll("\\{COLOR_CHAR\\}",      ChatColor.COLOR_CHAR+"");
        msg = msg.replaceAll("\\{DARK_AQUA\\}",       ChatColor.DARK_AQUA+"");
        msg = msg.replaceAll("\\{DARK_BLUE\\}",       ChatColor.DARK_BLUE+"");
        msg = msg.replaceAll("\\{DARK_GRAY\\}",       ChatColor.DARK_GRAY+"");
        msg = msg.replaceAll("\\{DARK_GREEN\\}",      ChatColor.DARK_GREEN+"");
        msg = msg.replaceAll("\\{DARK_PURPLE\\}",     ChatColor.DARK_PURPLE+"");
        msg = msg.replaceAll("\\{DARK_RED\\}",        ChatColor.DARK_RED+"");
        msg = msg.replaceAll("\\{GOLD\\}",            ChatColor.GOLD+"");
        msg = msg.replaceAll("\\{GRAY\\}",            ChatColor.GRAY+"");
        msg = msg.replaceAll("\\{GREEN\\}",           ChatColor.GREEN+"");
        msg = msg.replaceAll("\\{ITALIC\\}",          ChatColor.ITALIC+"");
        msg = msg.replaceAll("\\{LIGHT_PURPLE\\}",    ChatColor.LIGHT_PURPLE+"");
        msg = msg.replaceAll("\\{MAGIC\\}",           ChatColor.MAGIC+"");
        msg = msg.replaceAll("\\{RED\\}",             ChatColor.RED+"");
        msg = msg.replaceAll("\\{RESET\\}",           ChatColor.RESET+"");
        msg = msg.replaceAll("\\{STRIKETHROUGH\\}",   ChatColor.STRIKETHROUGH+"");
        msg = msg.replaceAll("\\{UNDERLINE\\}",       ChatColor.UNDERLINE+"");
        msg = msg.replaceAll("\\{WHITE\\}",           ChatColor.WHITE+"");
        msg = msg.replaceAll("\\{YELLOW\\}",          ChatColor.YELLOW+"");

        return msg;
    }

    private static String convertGeneralString(DataType strT, String target, Map<DataType, String> data)
    {
        switch(strT) {
            case PLAYER_NAME: {
                if(data == null) {
                    target = target.replaceAll("\\{PLAYER_NAME\\}", "NULL");
                } else {
                    target = target.replaceAll("\\{PLAYER_NAME\\}", data.get(strT));
                }
                break;
            }
            case POSITION: {
                if(data == null) {
                    target = target.replaceAll("\\{POSITION\\}", "NULL");
                } else {
                    target = target.replaceAll("\\{POSITION\\}", data.get(strT));
                }
                break;
            }
            case DAMAGE: {
                if(data == null) {
                    target = target.replaceAll("\\{DAMAGE\\}", "NULL");
                } else {
                    target = target.replaceAll("\\{DAMAGE\\}", data.get(strT));
                }
                break;
            }
            case DEATH_CAUSE: {
                if(data == null) {
                    target = target.replaceAll("\\{DEATH_CAUSE\\}", "NULL");
                } else {
                    target = target.replaceAll("\\{DEATH_CAUSE\\}", data.get(strT));
                }
                break;
            }
			case WORLD: {
                if(data == null) {
                    target = target.replaceAll("\\{WORLD\\}", "NULL");
                } else {
                    target = target.replaceAll("\\{WORLD\\}", data.get(strT));
                }
				break;
			}
			case POSX: {
                if(data == null) {
                    target = target.replaceAll("\\{POSX\\}", "NULL");
                } else {
                    target = target.replaceAll("\\{POSX\\}", data.get(strT));
                }
				break;
			}
			case POSY: {
                if(data == null) {
                    target = target.replaceAll("\\{POSY\\}", "NULL");
                } else {
                    target = target.replaceAll("\\{POSY\\}", data.get(strT));
                }
				break;
			}
			case POSZ: {
                if(data == null) {
                    target = target.replaceAll("\\{POSZ\\}", "NULL");
                } else {
                    target = target.replaceAll("\\{POSZ\\}", data.get(strT));
                }
				break;
			}
        }

        return target;
    }

    private String convertData(String original) {
        String msg = original;
        return msg;
    }

    private String convert(String original) {
        String msg = original;

        msg = convertColor(msg);
        msg = convertData(msg);

        return msg;
    }

    public static String getConfigMessage(AXPlayerHelper plugin/*, LogType logT*/, Map<DataType, String> data)
    {
        String returnString;

        /*
        switch(logT) {
            case PLAYER_JOIN: {
                returnString = plugin.getConfig().getString("messages.player_join");

                returnString = convertGeneralString(DataType.PLAYER_NAME, returnString, data);
                returnString = convertColorString(returnString);
                break;
            }
            case PLAYER_QUIT: {
                returnString = plugin.getConfig().getString("messages.player_quit");

                returnString = convertGeneralString(DataType.PLAYER_NAME, returnString, data);
                returnString = convertColorString(returnString);
                break;
            }
            case PLAYER_DEATH: {
                returnString = plugin.getConfig().getString("messages.player_death");

                returnString = convertGeneralString(DataType.PLAYER_NAME, returnString, data);
                //returnString = convertGeneralString(DataType.POSITION, returnString, data);
                returnString = convertGeneralString(DataType.DAMAGE, returnString, data);
                returnString = convertGeneralString(DataType.DEATH_CAUSE, returnString, data);
                returnString = convertGeneralString(DataType.WORLD, returnString, data);
                returnString = convertGeneralString(DataType.POSX, returnString, data);
                returnString = convertGeneralString(DataType.POSY, returnString, data);
                returnString = convertGeneralString(DataType.POSZ, returnString, data);
                returnString = convertColorString(returnString);
                break;
            }
            case COMMAND_INVALID_ARGUMENT_TYPE: {
                returnString = plugin.getConfig().getString("messages.invalid_argument_type");
                returnString = convertColorString(returnString);
                break;
            }
            case COMMAND_REQUIRE_ARGUMENTS: {
                returnString = plugin.getConfig().getString("messages.require_arguments");
                returnString = convertColorString(returnString);
                break;
            }

            default: {
                returnString = "NULL";
            }
        }
        return returnString;
        */
        return null;
    }

	public static String createLocationString(Location TL /*Target Location*/)
	{
		return "W:"+TL.getWorld().getName()+",X:"+TL.getBlockX()+",Y:"+TL.getBlockY()+",Z:"+TL.getBlockZ();
	}

}
