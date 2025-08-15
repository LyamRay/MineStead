package me.lyamray.minestead.utils.money;

import lombok.experimental.UtilityClass;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.player.data.PlayerDataHandler;
import me.lyamray.minestead.utils.messages.MiniMessage;
import org.bukkit.entity.Player;

import java.util.UUID;

@UtilityClass
public class Money {

    public void addMoney(Player player, int amount) {
        UUID uuid = player.getUniqueId();
        PlayerData data = PlayerDataHandler.getInstance().getData(uuid);

        if (data != null) {
            data.setMoney(data.getMoney() + amount);
            MiniMessage.sendMessage("\n<gray> • </gray><gradient:#c89651:#8adb9a>Je hebt <b>" + amount + "</b> bits ontvangen!</gradient>\n", player);
        }
    }

    public boolean removeMoney(Player player, int amount) {
        UUID uuid = player.getUniqueId();
        PlayerData data = PlayerDataHandler.getInstance().getData(uuid);

        if (data != null && data.getMoney() >= amount) {
            data.setMoney(data.getMoney() - amount);
            MiniMessage.sendMessage("\n<gray> • </gray><gradient:#c89651:#8adb9a>Je hebt <b>" + amount + "</b> bits uitgegeven!</gradient>\n", player);
            return true;
        }
        return false;
    }

    public int getMoney(Player player) {
        PlayerData data = PlayerDataHandler.getInstance().getData(player.getUniqueId());
        return data.getMoney();
    }

    public boolean hasMoney(Player player, int amount) {
        PlayerData data = PlayerDataHandler.getInstance().getData(player.getUniqueId());
        return data.getMoney() >= amount;
    }

    public void setMoney(Player player, int amount) {
        PlayerData data = PlayerDataHandler.getInstance().getData(player.getUniqueId());
        data.setMoney(amount);

        MiniMessage.sendMessage("\n<gray> • </gray><gradient:#c89651:#8adb9a>Je bits zijn naar <b>" + amount + "</b> gezet!</gradient>\n", player);
    }
}
