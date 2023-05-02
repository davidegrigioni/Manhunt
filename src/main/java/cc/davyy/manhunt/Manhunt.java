package cc.davyy.manhunt;

import cc.davyy.manhunt.cmds.ManhuntCommand;
import cc.davyy.manhunt.cmds.TeleportWorldCommand;
import cc.davyy.manhunt.cmds.TestGUICommand;
import cc.davyy.manhunt.engine.Game;
import cc.davyy.manhunt.listeners.*;
import cc.davyy.manhunt.managers.ScoreboardManager;
import cc.davyy.manhunt.placeholders.ManhuntExpansion;
import cc.davyy.manhunt.utils.InvalidUsage;
import cc.davyy.manhunt.arguments.PlayerArgument;
import cc.davyy.manhunt.arguments.WorldArgument;
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

public final class Manhunt extends JavaPlugin {

    private LiteCommands<CommandSender> liteCommands;
    private YamlDocument config;
    private YamlDocument messages;
    private ScoreboardManager scoreboardManager;
    private Game game;

    @Override
    public void onEnable() {

        updater();

        prettyConsole();

        registerCommands();

        registerConfig();

        registerListeners();

        registerBStats();

        registerManagers();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            registerPlaceholders();
        }

    }

    private void registerCommands() {
        this.liteCommands = LiteBukkitFactory.builder(this.getServer(), "manhunt")
                .commandInstance(
                        new ManhuntCommand(this),
                        new TeleportWorldCommand(this),
                        new TestGUICommand(this))
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
            ex.printStackTrace();
        }
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new SpectatorListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerKillEntityListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerTrackerListener(this), this);
    }

    private void registerPlaceholders() {
        new ManhuntExpansion(this).register();
    }

    private void registerBStats() {
        int pluginId = 18248;

        Metrics metrics = new Metrics(this, pluginId);
    }

    private void registerManagers() {
        scoreboardManager = new ScoreboardManager(this);
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

    public Game getGame() { return game; }

}
