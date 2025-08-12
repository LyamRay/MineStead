package me.lyamray.minestead.utils.money;

import lombok.experimental.UtilityClass;
import me.lyamray.minestead.player.data.PlayerData;
import me.lyamray.minestead.utils.messages.MiniMessage;
import org.bukkit.entity.Player;

import java.util.UUID;

@UtilityClass
public class Money {

    public void addMoney(Player player, int amount) {
        UUID uuid = player.getUniqueId();
        PlayerData data = PlayerData.getInstance().getPlayerDataCache().get(uuid);

        if (data != null) {
            data.setMoney(data.getMoney() + amount);
            MiniMessage.sendMessage("<gray> • </gray><gradient:#c89651:#8adb9a>Je hebt <b>" + amount + "</b> bits ontvangen!</gradient>", player);
        }
    }

    public boolean removeMoney(Player player, int amount) {
        UUID uuid = player.getUniqueId();
        PlayerData data = PlayerData.getInstance().getPlayerDataCache().get(uuid);

        if (data != null && data.getMoney() >= amount) {
            data.setMoney(data.getMoney() - amount);
            MiniMessage.sendMessage("<gray> • </gray><gradient:#c89651:#8adb9a>Je hebt <b>" + amount + "</b> bits uitgegeven!</gradient>\n", player);
            return true;
        }
        return false;
    }

    public int getMoney(Player player) {
        UUID uuid = player.getUniqueId();
        PlayerData data = PlayerData.getInstance().getPlayerDataCache().get(uuid);

        return (data != null) ? data.getMoney() : 0;
    }

    public boolean hasMoney(Player player, int amount) {
        UUID uuid = player.getUniqueId();
        PlayerData data = PlayerData.getInstance().getPlayerDataCache().get(uuid);

        return data != null && data.getMoney() >= amount;
    }

    public void setMoney(Player player, int amount) {
        UUID uuid = player.getUniqueId();
        PlayerData data = PlayerData.getInstance().getPlayerDataCache().get(uuid);

        if (data != null) {
            data.setMoney(amount);
            MiniMessage.sendMessage("<gray> • </gray><gradient:#c89651:#8adb9a>Je bits zijn naar <b>" + amount + "</b> gezet!</gradient>\n", player);
        }
    }
}
