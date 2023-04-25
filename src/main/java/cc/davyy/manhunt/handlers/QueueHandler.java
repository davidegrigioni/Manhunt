package cc.davyy.manhunt.handlers;

import org.bukkit.entity.Player;

public interface QueueHandler {

    /**
     * Adds a player to the queue.
     *
     * @param player the player to add
     * @return true if the player was successfully added to the queue, false otherwise
     */
    void addPlayer(Player player);

    /**
     * Removes a player from the queue.
     *
     * @param player the player to remove
     * @return true if the player was successfully removed from the queue, false otherwise
     */
    void removePlayer(Player player);

    /**
     * Checks if a player is in the queue.
     *
     * @param player the player to check
     * @return true if the player is in the queue, false otherwise
     */
    boolean hasPlayer(Player player);

    /**
     * Gets the size of the queue.
     *
     * @return the size of the queue
     */
    int getSize();

    /**
     * Clears the queue, removing all players.
     */
    void clear();

}
