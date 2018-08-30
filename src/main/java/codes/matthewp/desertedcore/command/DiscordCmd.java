package codes.matthewp.desertedcore.command;

import codes.matthewp.desertedcore.DesertedCore;
import codes.matthewp.desertedcore.message.MessageUtil;
import codes.matthewp.desertedcore.string.ColorHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCmd implements CommandExecutor {

    private DesertedCore core;

    public DiscordCmd(DesertedCore core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("discord")) {
            if (commandSender.hasPermission("core.discord")) {
                commandSender.sendMessage(ColorHelper.color(core.getConfiguration().getString("discordMsg")));
            } else {
                commandSender.sendMessage(MessageUtil.getMessage("noPerm"));
                return false;
            }
        }
        return false;
    }
}
