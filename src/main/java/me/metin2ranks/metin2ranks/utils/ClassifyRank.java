package me.metin2ranks.metin2ranks.utils;

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ClassifyRank {
    public static Ranks classify(Player player) {
        int mobKills = player.getStatistic(Statistic.MOB_KILLS);
        int playerKills = player.getStatistic(Statistic.PLAYER_KILLS);
        int bossKills  = 2* player.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDER_DRAGON) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.WITHER);
        int badPplKills = player.getStatistic(Statistic.KILL_ENTITY, EntityType.FISHING_HOOK);
        int friendlyMobKills = player.getStatistic(Statistic.KILL_ENTITY, EntityType.CAT) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.WOLF) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.DOLPHIN) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.TURTLE) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.OCELOT) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.DONKEY) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.HORSE) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.IRON_GOLEM) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.SNOWMAN) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.MULE) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.BEE) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.PANDA);
        double friendlyFactor = (((6*(double)mobKills - (20*(double)playerKills) - (15*(double)friendlyMobKills) + (15*(double)badPplKills)) / 41) / 5) + bossKills;
        Ranks rank = friendlyFactor > 8 ? Ranks.Chilvaric : friendlyFactor > 6 ? Ranks.Noble : friendlyFactor > 4 ? Ranks.Good : friendlyFactor > 2 ? Ranks.Friendly : friendlyFactor < 2 && friendlyFactor > -2 ? Ranks.Neutral : friendlyFactor < -2 && friendlyFactor > -4 ? Ranks.Agressive : friendlyFactor < -4 && friendlyFactor > -6 ? Ranks.Fraudulent : friendlyFactor < -6 && friendlyFactor > -8 ? Ranks.Fraudulent : Ranks.Cruel;
        return rank;
    }
    public static double friendlyFactor(Player player) {
        int mobKills = player.getStatistic(Statistic.MOB_KILLS);
        int playerKills = player.getStatistic(Statistic.PLAYER_KILLS);
        int bossKills  = player.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDER_DRAGON) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.WITHER);
        int badPplKills = player.getStatistic(Statistic.KILL_ENTITY, EntityType.FISHING_HOOK);
        int friendlyMobKills = player.getStatistic(Statistic.KILL_ENTITY, EntityType.CAT) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.WOLF) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.DOLPHIN) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.TURTLE) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.OCELOT) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.DONKEY) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.HORSE) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.IRON_GOLEM) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.SNOWMAN) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.MULE) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.BEE) + player.getStatistic(Statistic.KILL_ENTITY, EntityType.PANDA);
        double friendlyFactor = (((6*(double)mobKills - (20*(double)playerKills) - (15*(double)friendlyMobKills) + (15*(double)badPplKills)) / 41) / 5) + bossKills;
        return friendlyFactor;
    }
    public static String formatRank(Ranks rank) {
        String txtrank = "";
        switch(rank) {
            case Chilvaric:
            txtrank = ChatColor.DARK_BLUE + "Chilvaric";
                break;
            case Noble:
            txtrank = ChatColor.BLUE + "Noble";
                break;
            case Good:
                txtrank = ChatColor.DARK_AQUA + "Good";
            case Friendly:
                txtrank = ChatColor.AQUA + "Friendly";
                break;
            case Neutral:
            txtrank = "Neutral";
                    break;
            case Agressive:
            txtrank = ChatColor.YELLOW + "Agressive";
                break;
            case Fraudulent:
            txtrank = ChatColor.GOLD + "Fraudulent";
                break;
            case Malicious:
            txtrank = ChatColor.RED + "Malicious";
                break;
            case Cruel:
            txtrank = ChatColor.DARK_RED + "Cruel";
            break;
        }
        return txtrank;
    }
}
