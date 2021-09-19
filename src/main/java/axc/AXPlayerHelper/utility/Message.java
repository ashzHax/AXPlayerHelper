package axc.AXPlayerHelper.utility;
import axc.AXPlayerHelper.AXPlayerHelper;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.Map;

public class Message {

    public enum LogType {
        PLAYER_JOIN,
        PLAYER_QUIT,
        PLAYER_DEATH,
        COMMAND_INVALID_ARGUMENT_TYPE,
        COMMAND_REQUIRE_ARGUMENTS,
    };

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

    public static String getConfigMessage(AXPlayerHelper plugin, LogType logT, Map<DataType, String> data)
    {
        String returnString;

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
    }

	public static String createLocationString(Location TL /*Target Location*/)
	{
		return "W:"+TL.getWorld().getName()+",X:"+TL.getBlockX()+",Y:"+TL.getBlockY()+",Z:"+TL.getBlockZ();
	}

}
