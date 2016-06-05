/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.config.Configuration
 */
package Zeno410Utils;

import Zeno410Utils.Settings;
import Zeno410Utils.Zeno410Logger;
import java.io.File;
import java.util.logging.Logger;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.config.Configuration;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ConfigManager<Type extends Settings> {
    static Logger logger = new Zeno410Logger("ConfigManager").logger();
    public static final String CONFIG_DIRECTORY = "worldSpecificConfig";
    private Configuration general;
    private File generalConfigFile;
    private Type settings;
    private File worldConfigFile;
    private Configuration worldSpecific;

    public ConfigManager(Configuration general, Type settings, File generalFile) {
        this.general = general;
        this.settings = settings;
        this.generalConfigFile = generalFile;
    }

    private boolean usable(File tested) {
        return tested != null;
    }

    public void saveWorldSpecific() {
        this.worldSpecific.save();
    }

    private void setWorldConfigFile(File newFile) {
        if (this.worldConfigFile == null || !newFile.getAbsolutePath().equals(this.worldConfigFile.getAbsolutePath())) {
            this.worldConfigFile = newFile;
            if (this.usable(this.worldConfigFile)) {
                logger.info(this.worldConfigFile.getPath());
                if (newFile.exists()) {
                    this.worldSpecific = new Configuration(this.worldConfigFile);
                    logger.info("exists ");
                    this.worldSpecific.load();
                    this.settings.readFrom(this.worldSpecific);
                } else {
                    logger.info("doesn't exist");
                    this.worldSpecific = new Configuration(this.worldConfigFile);
                    this.settings.readFrom(this.general);
                    this.settings.copyTo(this.worldSpecific);
                }
                this.worldSpecific.save();
            } else {
                logger.info("null file");
                this.worldSpecific = null;
                this.settings.readFrom(this.general);
            }
        }
    }

    public void setWorldFile(File newFile) {
        File configDirectory = new File(newFile, "worldSpecificConfig");
        configDirectory.mkdir();
        String configName = this.generalConfigFile.getPath();
        String generalConfigDirectoryName = this.generalConfigFile.getParentFile().getPath();
        String detailName = configName.substring(generalConfigDirectoryName.length() + 1);
        logger.info("Filename " + detailName);
        File localConfigFile = new File(configDirectory, detailName);
        this.setWorldConfigFile(localConfigFile);
    }

    public void clearWorldFile() {
        this.worldConfigFile = null;
        this.worldSpecific = null;
        logger.info("clearing ");
    }

    public void setWorldFile(WorldServer server) {
        this.setWorldFile(server.getChunkSaveLocation());
    }
}

