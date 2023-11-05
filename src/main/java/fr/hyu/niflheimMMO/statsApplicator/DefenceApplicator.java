package fr.hyu.niflheimMMO.statsApplicator;

import fr.hyu.niflheimMMO.mobs.MonsterProfile;
import org.bukkit.Bukkit;

import java.text.DecimalFormat;

public class DefenceApplicator {
    public static int newAttackDamage(int attackDamage, MonsterProfile monsterProfile) {

        int newAttackDamage = (int) (attackDamage * (1- defenceCalculator(monsterProfile.getDefence())));

        return newAttackDamage;

    }

    public static double defenceCalculator(int defence) {

        double Vmax = 30; // Maximum (30% de réduction à 200 points)
        double Vmin = 5;  // Minimum (5% de réduction à 10 points)
        double K = 100;   // Point de transition (où la réduction est d'environ 17.5%)

        // Créez un objet DecimalFormat pour spécifier le format
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        double mobDefence = Vmin + (Vmax - Vmin) / (1 + Math.exp((K - defence) / 25));

        // Utilisez DecimalFormat pour formater le nombre double
        String formattedDouble = decimalFormat.format(mobDefence);

        // Convertissez la chaîne formatée en double si nécessaire
        double roundedDouble = Double.parseDouble(formattedDouble);

        return roundedDouble;

    }
}
