package me.metin2ranks.metin2ranks.utils;

public class Rounding {
    public static double round(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
