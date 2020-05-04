package me.metin2ranks.metin2ranks;


import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Profile {

    private static ArrayList<Profile> userProfiles = new ArrayList<Profile>();

    public static void removeProfile(Player player) {
        UUID uuid = player.getUniqueId();
        if (userProfiles.contains(getProfile(uuid))) {
            getProfile(uuid).armorStand.remove();
            userProfiles.remove(getProfile(uuid));
        }
    }
    public static void removeProfile(UUID uuid) {
        if (userProfiles.contains(getProfile(uuid))) {
            getProfile(uuid).armorStand.remove();
            userProfiles.remove(getProfile(uuid));
        }
    }
    public static Profile getProfile(UUID uuid) {
        for (Profile profile : userProfiles) {
            if (profile.uuid == uuid) {
                return profile;
            }
        }

        Profile profile = new Profile(uuid);
        userProfiles.add(profile);

        return profile;
    }

    public UUID uuid;
    public Entity armorStand;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.armorStand = null;
    }

}
