package axc.AXPlayerHelper.utility;

import axc.AXPlayerHelper.AXPlayerHelper;
import org.bukkit.ChatColor;

import java.util.Map;

public class Message {

    public enum LogType {
        PLAYER_JOIN,
        PLAYER_QUIT,
        PLAYER_DEATH,
    };

    public enum StringType {
        PLAYER_NAME,
        POSITION,
        DAMAGE,
        DEATH_CAUSE,
    };

    private static String convertColorString(String target)
    {
        target = target.replaceAll("\\{AQUA\\}", ChatColor.AQUA+"");
        target = target.replaceAll("\\{BLACK\\}", ChatColor.BLACK+"");
        target = target.replaceAll("\\{BLUE\\}", ChatColor.BLUE+"");
        target = target.replaceAll("\\{BOLD\\}", ChatColor.BOLD+"");
        target = target.replaceAll("\\{COLOR_CHAR\\}", ChatColor.COLOR_CHAR+"");
        target = target.replaceAll("\\{DARK_AQUA\\}", ChatColor.DARK_AQUA+"");
        target = target.replaceAll("\\{DARK_BLUE\\}", ChatColor.DARK_BLUE+"");
        target = target.replaceAll("\\{DARK_GRAY\\}", ChatColor.DARK_GRAY+"");
        target = target.replaceAll("\\{DARK_GREEN\\}", ChatColor.DARK_GREEN+"");
        target = target.replaceAll("\\{DARK_PURPLE\\}", ChatColor.DARK_PURPLE+"");
        target = target.replaceAll("\\{DARK_RED\\}", ChatColor.DARK_RED+"");
        target = target.replaceAll("\\{GOLD\\}", ChatColor.GOLD+"");
        target = target.replaceAll("\\{GRAY\\}", ChatColor.GRAY+"");
        target = target.replaceAll("\\{GREEN\\}", ChatColor.GREEN+"");
        target = target.replaceAll("\\{ITALIC\\}", ChatColor.ITALIC+"");
        target = target.replaceAll("\\{LIGHT_PURPLE\\}", ChatColor.LIGHT_PURPLE+"");
        target = target.replaceAll("\\{MAGIC\\}", ChatColor.MAGIC+"");
        target = target.replaceAll("\\{RED\\}", ChatColor.RED+"");
        target = target.replaceAll("\\{RESET\\}", ChatColor.RESET+"");
        target = target.replaceAll("\\{STRIKETHROUGH\\}", ChatColor.STRIKETHROUGH+"");
        target = target.replaceAll("\\{UNDERLINE\\}", ChatColor.UNDERLINE+"");
        target = target.replaceAll("\\{WHITE\\}", ChatColor.WHITE+"");
        target = target.replaceAll("\\{YELLOW\\}", ChatColor.YELLOW+"");

        return target;
    }

    private static String convertGeneralString(StringType strT, String target, Map<StringType, String> data)
    {
        switch(strT) {
            case PLAYER_NAME: {
                if(data == null) {
                    target = target.replaceAll("\\{PLAYERNAME\\}", "NULL");
                } else {
                    target = target.replaceAll("\\{PLAYERNAME\\}", data.get(strT));
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
        }

        return target;
    }

    public static String getConfigMessage(AXPlayerHelper plugin, LogType logT, Map<StringType, String> data)
    {
        String returnString;

        switch(logT) {
            case PLAYER_JOIN: {
                returnString = plugin.getConfig().getString("messages.player_join");

                returnString = convertGeneralString(StringType.PLAYER_NAME, returnString, data);
                returnString = convertColorString(returnString);
                break;
            }
            case PLAYER_QUIT: {
                returnString = plugin.getConfig().getString("messages.player_quit");

                returnString = convertGeneralString(StringType.PLAYER_NAME, returnString, data);
                returnString = convertColorString(returnString);
                break;
            }
            case PLAYER_DEATH: {
                returnString = plugin.getConfig().getString("messages.player_death");

                returnString = convertGeneralString(StringType.PLAYER_NAME, returnString, data);
                returnString = convertGeneralString(StringType.POSITION, returnString, data);
                returnString = convertGeneralString(StringType.DAMAGE, returnString, data);
                returnString = convertGeneralString(StringType.DEATH_CAUSE, returnString, data);
                returnString = convertColorString(returnString);
                break;
            }
            default: {
                returnString = "NULL";
            }
        }
        return returnString;
    }

}
