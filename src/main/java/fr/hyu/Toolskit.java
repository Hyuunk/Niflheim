package fr.hyu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Toolskit {
        public static int checkArgs(Player sender ,CommandsType cmdType ,String[] argsString) {
            ArrayList<String> args = new ArrayList<String>(Arrays.asList(argsString));
            switch (cmdType) {
                case AMOUNT: {
                    if (args.size() == 1 && isNumeric(args.get(0))) {
                        return 0;
                    }
                    return -1;
                }
                case NOUN: {
                    if (args.size() == 1) {
                        return 0;
                    }
                    return -1;
                }
                case PLAYER: {
                    if (args.size() != 1) {
                        return 1;
                    }
                    if (Bukkit.getPlayer((String)args.get(0)) == null) {
                        return -3;
                    }
                    Player targetPlayer = Bukkit.getPlayer((String)args.get(0));
                    if (sender == targetPlayer) {
                        return 1;
                    }
                    return 0;
                }
                case PLAYER_AMOUNT: {
                    if (args.size() != 2) {
                        return -2;
                    }
                    if (args.size() == 1) {
                        return -1;
                    }
                    if (Bukkit.getPlayer((String)args.get(0)) == null) {
                        return -3;
                    }
                    Player targetPlayer = Bukkit.getPlayer((String)args.get(0));
                    if (sender == targetPlayer) {
                        if (isNumeric(args.get(1))) {
                            return 1;
                        }
                        return -2;
                    }
                    else {
                        if (isNumeric(args.get(1))) {
                            return 0;
                        }
                        return -2;
                    }
                }
                case PLAYER_NOUN: {
                    if (args.size() != 2) {
                        return -2;
                    }
                    if (args.size() == 1) {
                        return -1;
                    }
                    if (Bukkit.getPlayer((String)args.get(0)) == null) {
                        return -3;
                    }
                    Player targetPlayer = Bukkit.getPlayer((String)args.get(0));
                    if (sender == targetPlayer) {
                        return 1;
                    }
                    return 0;
                }
                case PLAYER_PLAYER: {
                    if (args.size() != 2) {
                        return -2;
                    }
                    if (args.size() == 1) {
                        return -1;
                    }
                    if (Bukkit.getPlayer((String)args.get(0)) == null) {
                        return -3;
                    }
                    Player targetPlayer2 = Bukkit.getPlayer((String)args.get(0));
                    if (sender == targetPlayer2) {
                        if (Bukkit.getPlayer((String)args.get(1)) == null) {
                            return -3;
                        }
                        Player targetPlayer3 = Bukkit.getPlayer((String)args.get(1));
                        if (sender == targetPlayer3) {
                            return -2;
                        }
                        return 2;
                    }
                    else {
                        if (Bukkit.getPlayer((String)args.get(1)) == null) {
                            return -3;
                        }
                        Player targetPlayer3 = Bukkit.getPlayer((String)args.get(1));
                        if (sender == targetPlayer3) {
                            return 2;
                        }
                        return 2;
                    }
                }
                default: {
                    return -100;
                }
            }
        }

        public static boolean isNumeric(String args) {
            try {
                Double.parseDouble(args);
                return true;
            }
            catch (NumberFormatException e) {
                return false;
            }
        }

        public enum CommandsType
        {
            PLAYER("PLAYER"),
            AMOUNT("AMOUNT"),
            NOUN("NOUN"),
            PLAYER_PLAYER("PLAYER_PLAYER"),
            PLAYER_AMOUNT("PLAYER_AMOUNT"),
            PLAYER_NOUN("PLAYER_NOUN");

            private CommandsType(final String name) {
            }
        }
}
