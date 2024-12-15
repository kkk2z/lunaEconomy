package com.lunaeconomy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LunaEconomy extends JavaPlugin {

    @Override
    public void onEnable() {
        // プラグインが有効化された際に実行されるメソッド
        getLogger().info("LunaEconomy plugin has been enabled!");
        saveDefaultConfig(); // デフォルトのconfig.ymlを作成
    }

    @Override
    public void onDisable() {
        // プラグインが無効化された際に実行されるメソッド
        getLogger().info("LunaEconomy plugin has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration config = getConfig();

        // le-test コマンドの処理
        if (cmd.getName().equalsIgnoreCase("le-test")) {
            String requiredPermission = config.getString("special-commands.test", "plugin.test");

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission(requiredPermission)) {
                    player.sendMessage("LunaEconomy のテストコマンドが実行されました！");
                } else {
                    player.sendMessage("このコマンドを実行する権限がありません！");
                }
            } else {
                sender.sendMessage("コンソールからテストコマンドが実行されました！");
            }
            return true;
        }

        // le-ver コマンドの処理
        if (cmd.getName().equalsIgnoreCase("le-ver")) {
            String requiredPermission = config.getString("special-commands.version", "plugin.version");

            String version = getDescription().getVersion();
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission(requiredPermission)) {
                    player.sendMessage("LunaEconomy プラグインのバージョン: " + version);
                } else {
                    player.sendMessage("このコマンドを実行する権限がありません！");
                }
            } else {
                sender.sendMessage("LunaEconomy プラグインのバージョン: " + version);
            }
            return true;
        }

        return false;
    }
}
