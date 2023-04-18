package cc.davyy.manhunt.utils;

import cc.davyy.manhunt.Manhunt;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
import org.bukkit.entity.Player;
import panda.std.Result;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ArgumentName("player")
public class PlayerArgument implements OneArgument<Player> {

    private final Manhunt instance;

    public PlayerArgument(Manhunt instance) {
        this.instance = instance;
    }

    @Override
    public Result<Player, ?> parse(LiteInvocation liteInvocation, String argument) {
        Optional<Player> player = Optional.ofNullable(this.instance.getServer().getPlayer(argument));

        if (player.isPresent()) {
            return Result.ok(player.get());
        }

        return Result.error("Player not found");
    }

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        return instance.getServer().getOnlinePlayers().stream()
                .map(Player::getName)
                .map(Suggestion::of)
                .collect(Collectors.toList());
    }

}
