package cc.davyy.manhunt.utils;

import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.handle.InvalidUsageHandler;
import dev.rollczi.litecommands.schematic.Schematic;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class InvalidUsage implements InvalidUsageHandler<CommandSender> {

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, Schematic schematic) {
        List<String> schematics = schematic.getSchematics();

        if (schematics.size() == 1) {
            sender.sendMessage(Component.text("Invalid usage! Correct usage: " + schematics.get(0), NamedTextColor.RED));
            return;
        }

        sender.sendMessage(Component.text("Invalid usage! Correct usages: ", NamedTextColor.RED));
        for (String sch : schematics) {
            sender.sendMessage(Component.text(" - " + sch, NamedTextColor.RED));
        }
    }

}
