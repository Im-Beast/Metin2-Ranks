package me.metin2ranks.metin2ranks.listeners;

import me.metin2ranks.metin2ranks.Metin2Ranks;
import me.metin2ranks.metin2ranks.Profile;
import me.metin2ranks.metin2ranks.utils.ClassifyRank;
import me.metin2ranks.metin2ranks.utils.Ranks;
import me.metin2ranks.metin2ranks.utils.Rounding;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BukkitListener implements Listener {

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Profile prof = Profile.getProfile(event.getPlayer().getUniqueId());
        prof.armorStand.setCustomNameVisible(!event.isSneaking());
        prof.armorStand.setCustomName(ChatColor.GRAY + "<" + ChatColor.WHITE + Rounding.round(ClassifyRank.friendlyFactor(event.getPlayer()),2) + ChatColor.GRAY + ">" + ClassifyRank.formatRank(ClassifyRank.classify(event.getPlayer())));
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        if (ClassifyRank.friendlyFactor(event.getPlayer()) < 0) {
            if (event.getItem().getType() == Material.GOLDEN_APPLE) {
                event.getPlayer().setStatistic(Statistic.KILL_ENTITY, EntityType.FISHING_HOOK, event.getPlayer().getStatistic(Statistic.KILL_ENTITY, EntityType.FISHING_HOOK) + 1);
            }
            if (event.getItem().getType() == Material.ENCHANTED_GOLDEN_APPLE) {
                event.getPlayer().setStatistic(Statistic.KILL_ENTITY, EntityType.FISHING_HOOK, event.getPlayer().getStatistic(Statistic.KILL_ENTITY, EntityType.FISHING_HOOK) + 3);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Profile prof = Profile.getProfile(event.getPlayer().getUniqueId());
        if (prof.armorStand == null) {
            Location ploc = event.getTo().clone().add(0, 1.625, 0);
            ArmorStand as = (ArmorStand) ploc.getWorld().spawn(ploc, ArmorStand.class);
            as.setVisible(false);
            as.setGravity(false);
            as.setCanPickupItems(false);
            as.setCustomName(ClassifyRank.formatRank(ClassifyRank.classify(event.getPlayer())));
            as.setCustomNameVisible(!event.getPlayer().isSneaking());
            as.setInvulnerable(true);
            as.setMarker(true);
            as.setCollidable(false);
            prof.armorStand = as;
        } else {
            prof.armorStand.setCustomNameVisible(!event.getPlayer().isSneaking());
            prof.armorStand.setCustomName(ChatColor.GRAY + "<" + ChatColor.WHITE + Rounding.round(ClassifyRank.friendlyFactor(event.getPlayer()),2) + ChatColor.GRAY + "> " + ClassifyRank.formatRank(ClassifyRank.classify(event.getPlayer())));
            prof.armorStand.teleport(event.getTo().clone().add(3*(event.getTo().getX()-event.getFrom().getX()), 1.625, 3*(event.getTo().getZ()-event.getFrom().getZ())));
        }
    }

    @EventHandler
    public void onEntityDmg(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player && event.getDamager() instanceof Player)) return;
        Player dmgr = (Player) event.getDamager();
        Player dmgd = (Player) event.getEntity();
        if (dmgd.getHealth() + event.getFinalDamage() <= 0 && ClassifyRank.friendlyFactor(dmgr) > 0 && ClassifyRank.friendlyFactor(dmgd) < -2) {
            dmgr.setStatistic(Statistic.KILL_ENTITY, EntityType.FISHING_HOOK, dmgr.getStatistic(Statistic.KILL_ENTITY,EntityType.FISHING_HOOK) + 1);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity().getWorld().getGameRuleValue("keepInventory") != "true") return;
        Ranks rank = ClassifyRank.classify(event.getEntity());
        Random rand = new Random();
        int maxi = rank.equals(Ranks.Cruel) ? 10 : rank.equals(Ranks.Malicious) ? 8 : rank.equals(Ranks.Fraudulent) ? 6 : rank.equals(Ranks.Agressive) ? 4 : 0;
        int chance = rank.equals(Ranks.Cruel) ? 10 : rank.equals(Ranks.Malicious) ? 12 : rank.equals(Ranks.Fraudulent) ? 14 : rank.equals(Ranks.Agressive) ? 16 : 0;

        for (int i = 0; i <= maxi; i++) {
            if (rand.nextInt(chance) < 2) {
                int slot = rand.nextInt(36);
                if (event.getEntity().getInventory().getItem(slot) != null)
                event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), event.getEntity().getInventory().getItem(slot));
                event.getEntity().getInventory().setItem(slot, null);
            }
        }
    }

}
