package cc.davyy.manhunt;

import cc.davyy.manhunt.cmds.ManhuntCommand;
import cc.davyy.manhunt.cmds.TeleportWorldCommand;
import cc.davyy.manhunt.cmds.TestGUICommand;
import cc.davyy.manhunt.listeners.PlayerJoinListener;
import cc.davyy.manhunt.utils.PlayerArgument;
import cc.davyy.manhunt.worlds.WorldArgument;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Manhunt extends JavaPlugin {

    private LiteCommands<CommandSender> liteCommands;
    private YamlDocument config, messages;

    @Override
    public void onEnable() {
        registerCommands();
        registerConfig();
        registerListeners();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        this.liteCommands = LiteBukkitFactory.builder(this.getServer(), "manhunt")
                .commandInstance(
                        new ManhuntCommand(this),
                        new TeleportWorldCommand(this),
                        new TestGUICommand())
                .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>("This command is only available for players!"))
                .argument(World.class, new WorldArgument(this))
                .argument(Player.class, new PlayerArgument(this))
                .register();
    }

    private void registerConfig() {
        try {
            config = YamlDocument.create(new File(getDataFolder(), "config.yml"), Objects.requireNonNull(getResource("config.yml")),
                    GeneralSettings.DEFAULT,
                    LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.DEFAULT,
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
            messages = YamlDocument.create(new File(getDataFolder(), "messages.yml"), Objects.requireNonNull(getResource("messages.yml")),
                    GeneralSettings.DEFAULT,
                    LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.DEFAULT,
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    @NotNull
    public YamlDocument getConfiguration() {
        return config;
    }

    @NotNull
    public YamlDocument getMessages() {
        return messages;
    }

}
