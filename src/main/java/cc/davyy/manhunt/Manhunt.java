package cc.davyy.manhunt;

import cc.davyy.manhunt.cmds.ManhuntCommand;
import cc.davyy.manhunt.listeners.*;
import cc.davyy.manhunt.managers.ManhuntManager;
import cc.davyy.manhunt.placeholders.ManhuntExpansion;
import cc.davyy.manhunt.utils.InvalidUsage;
import cc.davyy.manhunt.utils.arguments.PlayerArgument;
import cc.davyy.manhunt.utils.arguments.WorldArgument;
import com.moleculepowered.api.Console;
import com.moleculepowered.api.updater.Updater;
import com.moleculepowered.api.updater.provider.GithubProvider;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

public final class Manhunt extends JavaPlugin {

    private YamlDocument config;
    private YamlDocument messages;
    private ManhuntManager manhuntManager;
    private Metrics metrics;
    private LiteCommands<CommandSender> liteCommands;

    @Override
    public void onEnable() {

        registerConfig();

        updater();

        prettyConsole();

        registerCommands();

        registerManagers();

        registerListeners();

        registerBStats();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            registerPlaceholders();
        }

    }

    private void registerCommands() {
        liteCommands = LiteBukkitFactory.builder(this.getServer(), "manhunt")
                .commandInstance(
                        new ManhuntCommand(this))
                .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>("This command is only available for players!"))
                .argument(World.class, new WorldArgument(this))
                .argument(Player.class, new PlayerArgument(this))
                .invalidUsageHandler(new InvalidUsage())
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
            getLogger().log(Level.SEVERE, "Could not create Configs", ex);
        }
    }

    private void registerManagers() {
        manhuntManager = new ManhuntManager(this);
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new SpectatorListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerKillEntityListener(this), this);
    }

    private void registerPlaceholders() {
        new ManhuntExpansion(this).register();
    }

    private void registerBStats() {
        int pluginId = 18248;

        metrics = new Metrics(this, pluginId);
    }

    private void prettyConsole() {
        Console.setPrettyPrint(true);
        Console.setDebugToggle(true);
        Console.setPrefix("[Manhunt]");
    }

    private void updater() {
        new Updater(this)
                .addProvider(new GithubProvider("davidegrigioni/Manhunt"))
                .setEnableToggle(this.getConfiguration().getBoolean("auto-update"))
                .setInterval("2h")
                .setEventHandler(new UpdateListener())
                .setPermission("manhunt.admin.update")
                .scheduleAsync();
    }

    @NotNull
    public YamlDocument getConfiguration() {
        return config;
    }

    @NotNull
    public YamlDocument getMessages() {
        return messages;
    }

    public ManhuntManager getManhuntManager() { return manhuntManager; }
}
