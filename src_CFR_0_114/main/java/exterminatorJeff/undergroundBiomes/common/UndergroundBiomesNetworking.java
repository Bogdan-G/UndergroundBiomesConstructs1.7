/*
 * Decompiled with CFR 0_114.
 */
package exterminatorJeff.undergroundBiomes.common;

import Zeno410Utils.Acceptor;
import Zeno410Utils.Streamer;
import exterminatorJeff.undergroundBiomes.api.UndergroundBiomesSettings;
import exterminatorJeff.undergroundBiomes.network.DirectChannel;
import exterminatorJeff.undergroundBiomes.network.PacketPipeline;

public class UndergroundBiomesNetworking {
    private final PacketPipeline pipeline;
    public final DirectChannel<UndergroundBiomesSettings> settings;

    public UndergroundBiomesNetworking(PacketPipeline pipeline, UndergroundBiomesSettings settingsTarget) {
        this.pipeline = pipeline;
        this.settings = new DirectChannel<UndergroundBiomesSettings>(pipeline, UndergroundBiomesSettings.streamer(settingsTarget), new Acceptor.Ignorer());
    }
}

