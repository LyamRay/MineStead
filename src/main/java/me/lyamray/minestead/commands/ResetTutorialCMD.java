package me.lyamray.minestead.commands;

import me.lyamray.minestead.player.data.PlayerDataHandler;
import me.lyamray.minestead.player.data.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ResetTutorialCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        PlayerData playerData = PlayerDataHandler.getInstance().getData(uuid);

        if (playerData == null) {
            sender.sendMessage("§cNo player data found for you.");
            return true;
        }

        playerData.setTutorialFinished(false);
        sender.sendMessage("§aYour tutorial status has been reset!");

        return true;
    }
}