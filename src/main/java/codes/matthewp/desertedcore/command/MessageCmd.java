package codes.matthewp.desertedcore.command;

import codes.matthewp.desertedcore.DesertedCore;
import codes.matthewp.desertedcore.message.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCmd implements CommandExecutor {

    public DesertedCore core;

    public MessageCmd(DesertedCore core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("msg")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.hasPermission("core.msg")) {
                    if (strings.length == 0) {
                        commandSender.sendMessage(MessageUtil.getMessage("msgUsage"));
                        return false;
                    }

                    Player target = Bukkit.getPlayer(strings[0]);

                    if (target == null) {
                        commandSender.sendMessage(MessageUtil.getMessage("playerNotOnline"));
                        return false;
                    }

                    if (strings.length >= 2) {
                        StringBuilder msgBuilder = new StringBuilder();

                        for (int i = 1; i < strings.length; i++) {
                            msgBuilder.append(strings[i] + " ");
                        }
                        String pMsg = msgBuilder.toString();

                        String msgToYou = MessageUtil.getMessage("msgToYou");
                        msgToYou = msgToYou.replaceAll("%SENDER%", p.getName());
                        msgToYou = msgToYou.replaceAll("%MSG%", pMsg);
                        target.sendMessage(msgToYou);

                        String youMsgPlayer = MessageUtil.getMessage("youMsgPlayer");
                        youMsgPlayer = youMsgPlayer.replaceAll("%PLAYER%", target.getName());
                        youMsgPlayer = youMsgPlayer.replaceAll("%MSG%", pMsg);
                        p.sendMessage(youMsgPlayer);

                        return true;
                    } else {
                        commandSender.sendMessage(MessageUtil.getMessage("msgUsage"));
                        return false;
                    }


                } else {
                    commandSender.sendMessage(MessageUtil.getMessage("noPerm"));
                    return false;
                }
            } else {
                commandSender.sendMessage(MessageUtil.getMessage("mustBePlayer"));
                return false;
            }
        }
        return false;
    }
}
