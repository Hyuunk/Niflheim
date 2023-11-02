package fr.hyu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

import static fr.hyu.Toolskit.CommandsTypeResults.*;

public class Toolskit {
        public static CommandsTypeResults checkArgs(Player sender ,CommandsType cmdType ,String[] argsString) {
            ArrayList<String> args = new ArrayList<String>(Arrays.asList(argsString));
            switch (cmdType) {
                case AMOUNT: {
                    if (args.size() == 1 && isNumeric(args.get(0))) {
                        return NORMALLY;
                    }
                    return FIRST_ARGUMENT_ERROR;
                }
                case NOUN: {
                    if (args.size() == 1) {
                        return NORMALLY;
                    }
                    return FIRST_ARGUMENT_ERROR;
                }
                case PLAYER: {
                    if (args.size() != 1) {
                        return FIRST_ARGUMENT_ONSELF;
                    }
                    if (Bukkit.getPlayer(args.get(0)) == null) {
                        return PLAYER_NONEXISTANT;
                    }
                    Player targetPlayer = Bukkit.getPlayer(args.get(0));
                    if (sender == targetPlayer) {
                        return FIRST_ARGUMENT_ONSELF;
                    }
                    return NORMALLY;
                }
                case PLAYER_AMOUNT: {
                    if (args.size() != 2) {
                        return SECOND_ARGUMENT_ERROR;
                    }
                    if (args.size() == 1) {
                        return FIRST_ARGUMENT_ERROR;
                    }
                    if (Bukkit.getPlayer((String)args.get(0)) == null) {
                        return PLAYER_NONEXISTANT;
                    }
                    Player targetPlayer = Bukkit.getPlayer((String)args.get(0));
                    if (sender == targetPlayer) {
                        if (isNumeric(args.get(1))) {
                            return FIRST_ARGUMENT_ONSELF;
                        }
                        return SECOND_ARGUMENT_ERROR;
                    }
                    else {
                        if (isNumeric(args.get(1))) {
                            return NORMALLY;
                        }
                        return SECOND_ARGUMENT_ERROR;
                    }
                }
                case PLAYER_NOUN: {
                    if (args.size() != 2) {
                        return SECOND_ARGUMENT_ERROR;
                    }
                    if (args.size() == 1) {
                        return FIRST_ARGUMENT_ERROR;
                    }
                    if (Bukkit.getPlayer((String)args.get(0)) == null) {
                        return PLAYER_NONEXISTANT;
                    }
                    Player targetPlayer = Bukkit.getPlayer((String)args.get(0));
                    if (sender == targetPlayer) {
                        return FIRST_ARGUMENT_ONSELF;
                    }
                    return NORMALLY;
                }
                case PLAYER_PLAYER: {
                    if (args.size() != 2) {
                        return SECOND_ARGUMENT_ERROR;
                    }
                    if (args.size() == 1) {
                        return FIRST_ARGUMENT_ERROR;
                    }
                    if (Bukkit.getPlayer((String)args.get(0)) == null) {
                        return PLAYER_NONEXISTANT;
                    }
                    Player targetPlayer2 = Bukkit.getPlayer((String)args.get(0));
                    if (sender == targetPlayer2) {
                        if (Bukkit.getPlayer((String)args.get(1)) == null) {
                            return PLAYER_NONEXISTANT;
                        }
                        Player targetPlayer3 = Bukkit.getPlayer((String)args.get(1));
                        if (sender == targetPlayer3) {
                            return SECOND_ARGUMENT_ERROR;
                        }
                        return SECOND_ARGUMENT_ONSELF;
                    }
                    else {
                        if (Bukkit.getPlayer((String)args.get(1)) == null) {
                            return PLAYER_NONEXISTANT;
                        }
                        Player targetPlayer3 = Bukkit.getPlayer((String)args.get(1));
                        if (sender == targetPlayer3) {
                            return SECOND_ARGUMENT_ONSELF;
                        }
                        return SECOND_ARGUMENT_ONSELF;
                    }
                }
                case NOUN_NOUN_PLAYER:

                    if (args.size() > 3) return THIRD_ARGUMENT_ERROR;
                    if (args.size() == 1) return FIRST_ARGUMENT_ERROR;
                    if (args.size() == 2) return THIRD_ARGUMENT_ONSELF;
                    if (args.size() == 3) {
                        if (Bukkit.getPlayer(args.get(2)) == null) return PLAYER_NONEXISTANT;
                        Player targetPlayer = Bukkit.getPlayer((String)args.get(2));
                        if(targetPlayer == sender) {
                            return THIRD_ARGUMENT_ONSELF;
                        } else {
                            return NORMALLY;
                        }
                    }
                    return SECOND_ARGUMENT_ERROR;

                default:
                    return ERROR;
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
            PLAYER_NOUN("PLAYER_NOUN"),
            NOUN_PLAYER("NOUN_PLAYER"),
            NOUN_AMOUNT("NOUN_AMOUNT"),
            NOUN_NOUN("NOUN_NOUN"),
            NOUN_NOUN_PLAYER("NOUN_NOUN_PLAYER");

            private CommandsType(final String name) {
            }
        }

        public enum CommandsTypeResults
        {
            NORMALLY(),
            ERROR(),
            PLAYER_NONEXISTANT(),
            FIRST_ARGUMENT_ONSELF(),
            SECOND_ARGUMENT_ONSELF(),
            THIRD_ARGUMENT_ONSELF(),
            FIRST_ARGUMENT_ERROR(),
            SECOND_ARGUMENT_ERROR(),
            THIRD_ARGUMENT_ERROR();
            private CommandsTypeResults() {
            }
        }
}
