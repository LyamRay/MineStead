package me.lyamray.minestead.utils.timers;

import lombok.experimental.UtilityClass;
import me.lyamray.minestead.MineStead;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

@UtilityClass
public class TimerUtil {

    private final MineStead mineStead = MineStead.getInstance();

    public BukkitTask runTaskTimer(Runnable task, long delayTicks, long periodTicks) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                task.run();
            }
        }.runTaskTimer(mineStead, delayTicks, periodTicks);
    }

    public BukkitTask runTaskLater(Runnable task, long delayTicks) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                task.run();
            }
        }.runTaskLater(mineStead, delayTicks);
    }

    public BukkitTask runAsyncTaskTimer(Runnable task, long delayTicks, long periodTicks) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                task.run();
            }
        }.runTaskTimerAsynchronously(mineStead, delayTicks, periodTicks);
    }

    public BukkitTask runAsyncTaskLater(Runnable task, long delayTicks) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                task.run();
            }
        }.runTaskLaterAsynchronously(mineStead, delayTicks);
    }
}
