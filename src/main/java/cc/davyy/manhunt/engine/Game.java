package cc.davyy.manhunt.engine;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

    private final Manhunt instance;

    private final String name;
    private final Player host;
    private final int maxPlayers;
    private int currentPlayers;
    private boolean started;
    private GameState gameState;

    public Game(Manhunt instance, String name, Player host, int maxPlayers) {
        this.instance = instance;
        this.name = name;
        this.host = host;
        this.maxPlayers = maxPlayers;
        this.currentPlayers = 1; // The host is already a player
        this.started = false;
        this.gameState = GameState.WAITING;
    }

    public String getName() {
        return name;
    }

    public Player getHost() {
        return host;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public boolean isStarted() {
        return started;
    }

    public void addPlayer() {
        if (currentPlayers < maxPlayers) {
            currentPlayers++;
        }
    }

    public void removePlayer() {
        if (currentPlayers > 0) {
            currentPlayers--;
        }
    }

    public void start() {
        started = true;
    }

    public void stop() {
        started = false;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(host);
        players.addAll(Bukkit.getOnlinePlayers());
        return players;
    }

    public void setGameState(GameState gameState) { this.gameState = gameState; }
    public GameState getGameState() { return gameState; }
}
