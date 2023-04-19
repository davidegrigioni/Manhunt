package cc.davyy.manhunt.managers;

import cc.davyy.manhunt.Manhunt;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private final Manhunt instance;
    private List<String> runners;

    public ConfigManager(Manhunt instance) {
        this.instance = instance;
    }

    public void load() {
        runners = new ArrayList<>();
        YamlDocument config = instance.getConfiguration();
        runners.addAll(config.getStringList("runners"));
    }

    public void add(String value) {
        runners.add(value);
    }

    public void remove(String value) {
        runners.remove(value);
    }

    public void save() {
        YamlDocument config = instance.getConfiguration();
        config.set("runners", runners);
    }

    public boolean contains(String value) {
        return runners.contains(value);
    }

    public List<String> getRunners() {
        return runners;
    }
}
