package me.metin2ranks.metin2ranks;

import me.metin2ranks.metin2ranks.listeners.BukkitListener;
import me.metin2ranks.metin2ranks.utils.ClassifyRank;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Metin2Ranks extends JavaPlugin {

    public static Metin2Ranks instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new BukkitListener(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(Profile::removeProfile);
        instance = null;
    }
}
