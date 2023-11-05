package fr.hyu.niflheimMMO.statsApplicator;

import fr.hyu.niflheimMMO.mobs.MonsterProfile;
import fr.hyu.niflheimPermissions.player.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.text.DecimalFormat;
import java.util.Random;

public class AgilityApplicator {
    public static boolean isDodgeMob(MonsterProfile monsterProfile) {

        Random rand = new Random();

        // Génère un nombre aléatoire entre 0 et 1 pour déterminer si l'attaque réussit
        double randomValue = rand.nextDouble();

        // Si la probabilité d'esquive est supérieure au nombre aléatoire, l'attaque échoue
        if (randomValue > agilityCalculator(monsterProfile.getAgility())) {
            return false;
        } else {
            return true;
        }
    }

    public static double agilityCalculator(int agility) {

        // Créez un objet DecimalFormat pour spécifier le format
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        double agilityCalculated = 0.001 + 0.0749 * Math.log(agility + 1);

        // Utilisez DecimalFormat pour formater le nombre double
        String formattedDouble = decimalFormat.format(agilityCalculated);

        // Convertissez la chaîne formatée en double si nécessaire
        return Double.parseDouble(formattedDouble);
    }
}
