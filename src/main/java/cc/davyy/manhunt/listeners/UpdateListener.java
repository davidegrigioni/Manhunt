package cc.davyy.manhunt.listeners;

import com.moleculepowered.api.Console;
import com.moleculepowered.api.event.updater.UpdateCompleteEvent;
import com.moleculepowered.api.updater.provider.AbstractProvider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UpdateListener implements Listener {

    @EventHandler
    public void onUpdateComplete(UpdateCompleteEvent event) {

        AbstractProvider provider = event.getProvider();
        String latestVersion = provider.getRelease().getVersion();

        switch (event.getResult()) {
            case LATEST -> Console.info("You have the latest version installed");
            case UPDATE_AVAILABLE -> Console.info("Update available: {0}", latestVersion);
            case EXISTS -> Console.info("Check your Update folder for the updated version");
            case DISABLED -> Console.warning("The updater is disabled");
            default -> Console.log("The updater result is unknown");
        }
    }

}
