package fr.hyu.niflheimMMO.mobs;

import fr.hyu.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.List;

import static java.lang.Math.abs;
import static org.bukkit.attribute.Attribute.GENERIC_ARMOR;

public class MobsSpawning implements Listener {

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {

        Entity entity = event.getEntity();
        EntityType entityType = event.getEntityType();
        LivingEntity livingEntity = event.getEntity();
        Location worldSpawn = event.getLocation().getWorld().getSpawnLocation();
        Location entitySpawnLocation = event.getLocation();

        int worldSpawnDistance = abs((int) worldSpawn.getX()) + abs((int) worldSpawn.getZ());
        int entitySpawnDistance = (abs((abs((int) entitySpawnLocation.getX()) + abs((int) entitySpawnLocation.getZ())) - worldSpawnDistance));
        int mobLevel = (int) Math.round((entitySpawnDistance)* Math.pow(10.0, -2.0));

        AttributeModifier healthModifier = new AttributeModifier("YourHealthModifier", entitySpawnDistance*0.02 + livingEntity.getHealth(), AttributeModifier.Operation.ADD_NUMBER);


        switch (entityType) {

            case DROPPED_ITEM:
                break;
            case EXPERIENCE_ORB:
                break;
            case AREA_EFFECT_CLOUD:
                break;
            case ELDER_GUARDIAN:
                break;
            case WITHER_SKELETON:
                break;
            case STRAY:
                break;
            case EGG:
                break;
            case LEASH_HITCH:
                break;
            case PAINTING:
                break;
            case ARROW:
                break;
            case SNOWBALL:
                break;
            case FIREBALL:
                break;
            case SMALL_FIREBALL:
                break;
            case ENDER_PEARL:
                break;
            case ENDER_SIGNAL:
                break;
            case SPLASH_POTION:
                break;
            case THROWN_EXP_BOTTLE:
                break;
            case ITEM_FRAME:
                break;
            case WITHER_SKULL:
                break;
            case PRIMED_TNT:
                break;
            case FALLING_BLOCK:
                break;
            case FIREWORK:
                break;
            case HUSK:
                break;
            case SPECTRAL_ARROW:
                break;
            case SHULKER_BULLET:
                break;
            case DRAGON_FIREBALL:
                break;
            case ZOMBIE_VILLAGER:
                break;
            case SKELETON_HORSE:
                break;
            case ZOMBIE_HORSE:
                break;
            case ARMOR_STAND:
                break;
            case DONKEY:
                break;
            case MULE:
                break;
            case EVOKER_FANGS:
                break;
            case EVOKER:
                break;
            case VEX:
                break;
            case VINDICATOR:
                break;
            case ILLUSIONER:
                break;
            case MINECART_COMMAND:
                break;
            case BOAT:
                break;
            case MINECART:
                break;
            case MINECART_CHEST:
                break;
            case MINECART_FURNACE:
                break;
            case MINECART_TNT:
                break;
            case MINECART_HOPPER:
                break;
            case MINECART_MOB_SPAWNER:
                break;
            case CREEPER:
                entity.setCustomName(ChatColor.GRAY + "Creeper [" + ChatColor.BLUE + mobLevel + ChatColor.GRAY + "]");
                entity.setCustomNameVisible(true);
                livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(healthModifier);
                livingEntity.setHealth(entitySpawnDistance*0.02 + livingEntity.getHealth());

                break;
            case SKELETON:
                entity.setCustomName(ChatColor.GRAY + "Skeleton [" + ChatColor.BLUE + mobLevel + ChatColor.GRAY + "]");
                entity.setCustomNameVisible(true);

                livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(healthModifier);
                livingEntity.setHealth(entitySpawnDistance*0.02 + livingEntity.getHealth());

                break;
            case SPIDER:
                entity.setCustomName(ChatColor.GRAY + "Spider [" + ChatColor.BLUE + mobLevel + ChatColor.GRAY + "]");
                entity.setCustomNameVisible(true);

                livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(healthModifier);
                livingEntity.setHealth(entitySpawnDistance*0.02 + livingEntity.getHealth());

                break;
            case GIANT:
                break;
            case ZOMBIE:

             entity.setCustomName(ChatColor.GRAY + "Zombie [" + ChatColor.BLUE + mobLevel + ChatColor.GRAY + "]");
                entity.setCustomNameVisible(true);

                livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(healthModifier);
                livingEntity.setHealth(entitySpawnDistance*0.02 + livingEntity.getHealth());

                break;
            case SLIME:
                break;
            case GHAST:
                break;
            case ZOMBIFIED_PIGLIN:
                break;
            case ENDERMAN:
                entity.setCustomName(ChatColor.GRAY + "Enderman [" + ChatColor.BLUE + mobLevel + ChatColor.GRAY + "]");
                entity.setCustomNameVisible(true);

                livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(healthModifier);
                livingEntity.setHealth(entitySpawnDistance*0.02 + livingEntity.getHealth());

                break;
            case CAVE_SPIDER:
                break;
            case SILVERFISH:
                break;
            case BLAZE:
                break;
            case MAGMA_CUBE:
                break;
            case ENDER_DRAGON:
                break;
            case WITHER:
                break;
            case BAT:
                break;
            case WITCH:
                break;
            case ENDERMITE:
                break;
            case GUARDIAN:
                break;
            case SHULKER:
                break;
            case PIG:
                break;
            case SHEEP:
                break;
            case COW:
                break;
            case CHICKEN:
                break;
            case SQUID:
                break;
            case WOLF:
                break;
            case MUSHROOM_COW:
                break;
            case SNOWMAN:
                break;
            case OCELOT:
                break;
            case IRON_GOLEM:
                break;
            case HORSE:
                break;
            case RABBIT:
                break;
            case POLAR_BEAR:
                break;
            case LLAMA:
                break;
            case LLAMA_SPIT:
                break;
            case PARROT:
                break;
            case VILLAGER:
                break;
            case ENDER_CRYSTAL:
                break;
            case TURTLE:
                break;
            case PHANTOM:
                break;
            case TRIDENT:
                break;
            case COD:
                break;
            case SALMON:
                break;
            case PUFFERFISH:
                break;
            case TROPICAL_FISH:
                break;
            case DROWNED:
                break;
            case DOLPHIN:
                break;
            case CAT:
                break;
            case PANDA:
                break;
            case PILLAGER:
                break;
            case RAVAGER:
                break;
            case TRADER_LLAMA:
                break;
            case WANDERING_TRADER:
                break;
            case FOX:
                break;
            case BEE:
                break;
            case HOGLIN:
                break;
            case PIGLIN:
                break;
            case STRIDER:
                break;
            case ZOGLIN:
                break;
            case PIGLIN_BRUTE:
                break;
            case AXOLOTL:
                break;
            case GLOW_ITEM_FRAME:
                break;
            case GLOW_SQUID:
                break;
            case GOAT:
                break;
            case MARKER:
                break;
            case ALLAY:
                break;
            case CHEST_BOAT:
                break;
            case FROG:
                break;
            case TADPOLE:
                break;
            case WARDEN:
                break;
            case CAMEL:
                break;
            case BLOCK_DISPLAY:
                break;
            case INTERACTION:
                break;
            case ITEM_DISPLAY:
                break;
            case SNIFFER:
                break;
            case TEXT_DISPLAY:
                break;
            case FISHING_HOOK:
                break;
            case LIGHTNING:
                break;
            case PLAYER:
                break;
            case UNKNOWN:
                break;
        }

    }
}
