package axc.AXPlayerHelper.utility;

import java.io.File;
import java.io.IOException;

import axc.AXPlayerHelper.AXPlayerHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import axc.AXPlayerHelper.AXPlayerHelper;

public class FileManager
{
    // ashz : configurations
    public enum playerRank{
        PLAYER,
        MODERATOR,
        BUILDER,
        TECHNICIAN,
        DEVELOPER,
        ADMINISTRATOR
    }
    public static String rank_staff = new String("rank_staff");

    // ashz : all paths
    public static String folder_Profile = new String(File.separator+"Profile"+File.separator);
    public static String file_automove = new String("automove");

    // ashz : use object "configuration" methods to edit a Configuration File
    public FileConfiguration configuration;
    private File objectFile;
    private AXPlayerHelper plugin;
    private String filePath;

    // ashz : initiating a file object for editing (creates file if it does not exist)
    public FileManager(AXPlayerHelper plugin, String fileName)
    {
        this.plugin = plugin;
        this.filePath = new String(fileName+".yml");
        this.objectFile = new File(this.plugin.getDataFolder()+File.separator+this.filePath);

        if(!this.objectFile.exists()) {
            try {
                this.objectFile.createNewFile();
                this.plugin.getServer().getConsoleSender().sendMessage("[File] created new file : \""+this.filePath+"\"");
            }
            catch(IOException IOE){
                IOE.printStackTrace();
                this.plugin.getServer().getConsoleSender().sendMessage("[File] error creating new file : \""+this.filePath+"\"");
            }
        }
        this.configuration = YamlConfiguration.loadConfiguration(this.objectFile);
        this.save();
    }

    /* ========================================================================================================================================================================= */
    // ashz : static methods
    public static String getPlayerFile(String playerUUID) {
        return new String(FileManager.folder_Profile+playerUUID);
    }

    // ashz : on plugin start, check or create for files and folder
    public static void init_FileManager(AXPlayerHelper plugin)
    {
        // ashz : create folder if does not exist
        if(!new File(plugin.getDataFolder()+File.separator+folder_Profile).exists()) {
            new File(plugin.getDataFolder()+File.separator+folder_Profile).mkdirs();
        }

        // ashz : create file if does not exist
        FileManager.createFile(plugin, file_automove);
    }

    // ashz : will try to make a new file (returns true on success, false on fail)
    public static boolean createFile(AXPlayerHelper plugin, String fileName)
    {
        File tmpObjectFile = new File(plugin.getDataFolder()+File.separator+fileName+".yml");

        if(!tmpObjectFile.exists()) {
            try {
                tmpObjectFile.createNewFile();
                plugin.getServer().getConsoleSender().sendMessage("[File] file "+fileName+".yml created successfully");
            }
            catch(IOException IOE){
                IOE.printStackTrace();
                plugin.getServer().getConsoleSender().sendMessage("[File] error creating file "+fileName+".yml (show error log above to developer)");
                return false;
            }
        }

        FileConfiguration objectConfiguration = YamlConfiguration.loadConfiguration(tmpObjectFile);
        try {
            objectConfiguration.save(tmpObjectFile);
        }
        catch(IOException IOE){
            IOE.printStackTrace();
            plugin.getServer().getConsoleSender().sendMessage("[File] error saving file "+fileName+".yml (show error log above to developer) (new file should not be created)");
            return false;
        }
        return true;
    }

    // ashz : use on simple value edits to file (returns true on success, false on fail)
    public static boolean editFile(AXPlayerHelper plugin, String filePath, String indexPath, Object value)
    {
        File tmpObjectFile = new File(plugin.getDataFolder()+File.separator+filePath);
        if(!tmpObjectFile.exists()) {
            try {
                tmpObjectFile.createNewFile();
                plugin.getServer().getConsoleSender().sendMessage("[File] file "+filePath+" created successfully");
            }
            catch(IOException IOE){
                IOE.printStackTrace();
                plugin.getServer().getConsoleSender().sendMessage("[File] error creating file "+filePath+" (show error log above to developer)");
                return false;
            }
        }

        FileConfiguration configuration = YamlConfiguration.loadConfiguration(tmpObjectFile);
        configuration.set(indexPath, value);

        try {
            configuration.save(tmpObjectFile);
        }
        catch(IOException IOE){
            IOE.printStackTrace();
            plugin.getServer().getConsoleSender().sendMessage("[File] error saving file "+filePath+" (show error log above to developer)");
        }
        return true;
    }

    /* ========================================================================================================================================================================= */

    // ashz : always need to save file after edit
    // ashz : if extend works, wont need this
    public void save()
    {
        try {
            configuration.save(objectFile);
        }
        catch(IOException IOE) {
            IOE.printStackTrace();
            plugin.getServer().getConsoleSender().sendMessage("[File] error saving file "+this.filePath+".yml (show error log above to developer)");
        }
    }

    // ashz : check if a index exists
    public boolean doesIndexExist(String indexName)
    {
        if(this.configuration.get(indexName) == null) {
            return false;
        } else {
            return true;
        }
        //return this.configuration.contains(indexName);
        //return configuration.isConfigurationSection(indexName);
    }

    // ashz : create new player (deletes old profile)
    public void createNewProfile(Player targetPlayer) {
        if(this.objectFile.exists()) {
            this.objectFile.delete();
            plugin.getServer().getConsoleSender().sendMessage("[File] deleted file : \""+this.filePath+"\" (creating new profile)");
        }

        try {
            this.objectFile.createNewFile();
            plugin.getServer().getConsoleSender().sendMessage("[File] created new file : \""+this.filePath+"\"");
        }
        catch(IOException IOE){
            IOE.printStackTrace();
            plugin.getServer().getConsoleSender().sendMessage("[File] error creating new file : \""+this.filePath+"\"");
        }

        this.configuration.set("tag.first_join",false);
        this.configuration.set("status.silent",false);
        this.save();

    }

    // ashz : gets a boolean value of a player status
    public boolean get_player_status_boolean(String statusName) {
        if(!this.doesIndexExist("status."+statusName)) {
            this.plugin.getServer().getConsoleSender().sendMessage("[File] error getting status of player ("+this.filePath+")");
            return false;
        }
        return this.configuration.getBoolean("status."+statusName);
    }

    // ashz : sets a boolean value of a player status
    public void set_player_status_boolean(String statusName,boolean toggle) {
        this.configuration.set("status."+statusName, toggle);
        this.save();
    }

    // ashz : check if its a players first time joining the server
    public boolean isPlayerFirstJoin_Boolean() {
        if(!this.doesIndexExist("tag.first_join")) {
            return true;
        }
        return this.configuration.getBoolean("tag.first_join");
    }

    public String getPlayerRank() {
        return this.configuration.getString(FileManager.rank_staff);
    }

    public void setPlayerRank(playerRank rankValue) {
        String rankString;
        switch(rankValue) {
            case PLAYER:{
                rankString="PLAYER";
                break;
            }
            case MODERATOR:{
                rankString="MODERATOR";
                break;
            }
            case TECHNICIAN:{
                rankString="TECHNICIAN";
                break;
            }
            case DEVELOPER:{
                rankString="DEVELOPER";
                break;
            }
            case ADMINISTRATOR:{
                rankString="ADMINISTRATOR";
                break;
            }
            default:{
                this.plugin.getServer().getConsoleSender().sendMessage("[Rank] error in getting rank value, invalid rank value (possible Java error)");
                return;
            }
        }
        this.configuration.set(FileManager.rank_staff, rankString);
        save();
    }

    public boolean setPlayerRank(String rankValue) {
        switch(rankValue) {
            case "PLAYER":
            case "MODERATOR":
            case "TECHNICIAN":
            case "DEVELOPER":
            case "ADMINISTRATOR": break;
            default:{
                return false;
            }
        }
        this.configuration.set(FileManager.rank_staff, rankValue);
        save();
        return true;
    }

    public void logtime() {



    }

}



