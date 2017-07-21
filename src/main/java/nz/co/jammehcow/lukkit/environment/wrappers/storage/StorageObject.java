package nz.co.jammehcow.lukkit.environment.wrappers.storage;

import nz.co.jammehcow.lukkit.environment.plugin.LukkitPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The abstract Storage class.
 *
 * @author jammehcow
 */
public abstract class StorageObject {
    /**
     * The enum Storage.
     */
    public enum Storage {
        /**
         * JSON storage.
         */
        JSON("json"),
        /**
         * YAML storage.
         */
        YAML("yaml");

        private final String type;
        Storage(String type) { this.type = type; }
    }

    private File storageFile;
    private LukkitPlugin plugin;
    private Storage type;

    /**
     * Instantiates a new StorageObject.
     *
     * @param plugin the lukkit plugin
     * @param path   the file path
     * @param type   the storage type
     */
    public StorageObject(LukkitPlugin plugin, String path, Storage type) {
        this.type = type;
        this.plugin = plugin;
        this.storageFile = new File(this.plugin.getDataFolder().getAbsolutePath() + File.separator + path);

        if (!this.storageFile.exists()) {
            try {
                Files.createFile(this.storageFile.toPath());
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    /**
     * Gets the storage type.
     *
     * @return the type
     */
    public Storage getType() {
        return this.type;
    }

    /**
     * Sets a value if it doesn't exist.
     *
     * @param path  the path of the key
     * @param value the value
     * @return true if the value is set, false if not
     */
    public abstract boolean setDefaultValue(String path, Object value);

    /**
     * Sets the value of a key.
     *
     * @param path  the path of the key
     * @param value the value
     */
    public abstract void setValue(String path, Object value);

    /**
     * Gets the value of a key.
     *
     * @param path the path of the key
     * @return the Object value
     */
    public abstract Object getValue(String path);

    /**
     * Save the file.
     */
    public abstract void save();

    /**
     * Gets the absolute path of the storage file.
     *
     * @return the absolute file path
     */
    public String getFilePath() {
        return this.storageFile.getAbsolutePath();
    }

    /**
     * Gets the associated {@link nz.co.jammehcow.lukkit.environment.plugin.LukkitPlugin}.
     *
     * @return the LukkitPlugin
     */
    public LukkitPlugin getPlugin() {
        return this.plugin;
    }

    /**
     * Gets the storage file.
     *
     * @return the storage file
     */
    public File getStorageFile() {
        return this.storageFile;
    }
}
