package cc.davyy.manhunt.utils.arguments;

import cc.davyy.manhunt.Manhunt;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
import org.bukkit.World;
import org.bukkit.generator.WorldInfo;
import panda.std.Option;
import panda.std.Result;

import java.util.List;
import java.util.stream.Collectors;

@ArgumentName("world")
public class WorldArgument implements OneArgument<World> {

    private final Manhunt instance;

    public WorldArgument(Manhunt instance) {
        this.instance = instance;
    }

    @Override
    public Result<World, Object> parse(LiteInvocation invocation, String argument) {
        return Option.of(this.instance.getServer().getWorld(argument)).toResult("There is no such world!");
    }

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        return this.instance.getServer().getWorlds().stream()
                .map(WorldInfo::getName)
                .map(Suggestion::of)
                .collect(Collectors.toList());
    }

}
