/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  highlands.biome.BiomeDecoratorHighlands
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraft.world.gen.feature.WorldGenDeadBush
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraft.world.gen.feature.WorldGenLiquids
 *  net.minecraft.world.gen.feature.WorldGenPumpkin
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Decorate
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Decorate$EventType
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Post
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Pre
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package exterminatorJeff.undergroundBiomes.worldGen;

import Zeno410Utils.AccessInt;
import Zeno410Utils.MethodAccessor;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventBus;
import highlands.biome.BiomeDecoratorHighlands;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class CorrectedBiomeDecoratorHighlands
extends BiomeDecoratorHighlands {
    public static AccessInt<BiomeDecoratorHighlands> highlandsPl = new AccessInt("highlandsPlantsPerChunk");
    private BiomeDecorator mcthis;
    private MethodAccessor<BiomeDecorator> genOreAccess = new MethodAccessor("func_76797_b");

    private static int trees(BiomeDecorator source) {
        return source.field_76832_z;
    }

    private static int grass(BiomeDecorator source) {
        return source.field_76803_B;
    }

    private static int flowers(BiomeDecorator source) {
        return source.field_76802_A;
    }

    public CorrectedBiomeDecoratorHighlands(BiomeGenBase biome, BiomeDecoratorHighlands toCorrect) {
        super(biome, CorrectedBiomeDecoratorHighlands.trees((BiomeDecorator)toCorrect), CorrectedBiomeDecoratorHighlands.grass((BiomeDecorator)toCorrect), CorrectedBiomeDecoratorHighlands.flowers((BiomeDecorator)toCorrect), highlandsPl.get(toCorrect));
        this.mcthis = this;
    }

    public void func_150512_a(World world, Random random, BiomeGenBase biome, int x, int z) {
        World targetWorld;
        World previous = this.field_76815_a;
        Random randomizer = this.field_76813_b;
        this.field_76815_a = null;
        World world2 = targetWorld = world == null ? previous : world;
        if (targetWorld == null) {
            throw new RuntimeException();
        }
        if (random == null) {
            throw new RuntimeException();
        }
        if (biome == null) {
            throw new RuntimeException();
        }
        try {
            super.func_150512_a(targetWorld, random, biome, x, z);
        }
        catch (RuntimeException e) {
            boolean doGen = TerrainGen.decorate((World)this.field_76815_a, (Random)this.field_76813_b, (int)this.field_76814_c, (int)this.field_76811_d, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.FLOWERS);
            for (int j = 0; doGen && j < this.field_76802_A; ++j) {
                int i1;
                int l;
                int k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
                String s = biome.func_150572_a(this.field_76813_b, k, i1 = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(k, l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8) + 32), l);
                BlockFlower blockflower = BlockFlower.func_149857_e((String)s);
                if (blockflower.func_149688_o() == Material.field_151579_a) continue;
                this.field_150514_p.func_150550_a((Block)blockflower, BlockFlower.func_149856_f((String)s));
                this.field_150514_p.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
            }
            throw e;
        }
        this.field_76815_a = previous;
        this.field_76813_b = randomizer;
    }

    public void decorateChunk(World world, Random p_150512_2_, BiomeGenBase p_150512_3_, int p_150512_4_, int p_150512_5_) {
        World previous = this.currentWorld;
        Random randomizer = this.randomGenerator;
        this.currentWorld = null;
        World targetWorld = world == null ? previous : world;
        super.decorateChunk(targetWorld, p_150512_2_, p_150512_3_, p_150512_4_, p_150512_5_);
        this.currentWorld = previous;
        this.randomGenerator = randomizer;
    }

    public void func_150513_ab(BiomeGenBase p_150513_1_) {
        int i1;
        int k;
        int j;
        int i;
        int l;
        Random randomizer = this.mcthis.field_76813_b;
        World world = this.mcthis.field_76815_a;
        int x = this.mcthis.field_76814_c;
        int z = this.mcthis.field_76811_d;
        MinecraftForge.EVENT_BUS.post((Event)new DecorateBiomeEvent.Pre(world, randomizer, x, z));
        this.genOreAccess.run(this.mcthis);
        boolean doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.SAND);
        for (i = 0; doGen && i < this.mcthis.field_76805_H; ++i) {
            j = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            k = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            this.mcthis.field_76810_g.func_76484_a(world, randomizer, j, world.func_72825_h(j, k), k);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.CLAY);
        for (i = 0; doGen && i < this.mcthis.field_76806_I; ++i) {
            j = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            k = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            this.mcthis.field_76809_f.func_76484_a(world, randomizer, j, world.func_72825_h(j, k), k);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.SAND_PASS2);
        for (i = 0; doGen && i < this.mcthis.field_76801_G; ++i) {
            j = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            k = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            this.mcthis.field_76822_h.func_76484_a(world, randomizer, j, world.func_72825_h(j, k), k);
        }
        i = this.mcthis.field_76832_z;
        if (randomizer == null) {
            throw new RuntimeException("already missing randomizer");
        }
        if (randomizer.nextInt(10) == 0) {
            ++i;
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.TREE);
        for (j = 0; doGen && j < i; ++j) {
            if (randomizer == null) {
                throw new RuntimeException("no randomizer");
            }
            k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            l = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            i1 = world.func_72976_f(k, l);
            WorldGenAbstractTree worldgenabstracttree = p_150513_1_.func_150567_a(randomizer);
            worldgenabstracttree.func_76487_a(1.0, 1.0, 1.0);
            if (!worldgenabstracttree.func_76484_a(world, randomizer, k, i1, l)) continue;
            worldgenabstracttree.func_150524_b(world, randomizer, k, i1, l);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM);
        for (j = 0; doGen && j < this.mcthis.field_76807_J; ++j) {
            k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            l = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            this.mcthis.field_76826_u.func_76484_a(world, randomizer, k, world.func_72976_f(k, l), l);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.FLOWERS);
        for (j = 0; doGen && j < this.mcthis.field_76802_A; ++j) {
            k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            String s = p_150513_1_.func_150572_a(randomizer, k, i1 = randomizer.nextInt(world.func_72976_f(k, l = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8) + 32), l);
            BlockFlower blockflower = BlockFlower.func_149857_e((String)s);
            if (blockflower.func_149688_o() == Material.field_151579_a) continue;
            this.mcthis.field_150514_p.func_150550_a((Block)blockflower, BlockFlower.func_149856_f((String)s));
            this.mcthis.field_150514_p.func_76484_a(world, randomizer, k, i1, l);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.GRASS);
        for (j = 0; doGen && j < this.mcthis.field_76803_B; ++j) {
            k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            l = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            i1 = randomizer.nextInt(world.func_72976_f(k, l) * 2);
            WorldGenerator worldgenerator = p_150513_1_.func_76730_b(randomizer);
            worldgenerator.func_76484_a(world, randomizer, k, i1, l);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH);
        for (j = 0; doGen && j < this.mcthis.field_76804_C; ++j) {
            k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            l = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            i1 = randomizer.nextInt(world.func_72976_f(k, l) * 2);
            new WorldGenDeadBush((Block)Blocks.field_150330_I).func_76484_a(world, randomizer, k, i1, l);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.LILYPAD);
        for (j = 0; doGen && j < this.mcthis.field_76833_y; ++j) {
            k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            l = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            for (i1 = randomizer.nextInt((int)(world.func_72976_f((int)k, (int)l) * 2)); i1 > 0 && world.func_147437_c(k, i1 - 1, l); --i1) {
            }
            this.mcthis.field_76834_x.func_76484_a(world, randomizer, k, i1, l);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.SHROOM);
        for (j = 0; doGen && j < this.mcthis.field_76798_D; ++j) {
            if (randomizer.nextInt(4) == 0) {
                k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
                l = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
                i1 = world.func_72976_f(k, l);
                this.mcthis.field_76828_s.func_76484_a(world, randomizer, k, i1, l);
            }
            if (randomizer.nextInt(8) != 0) continue;
            k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            l = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            i1 = randomizer.nextInt(world.func_72976_f(k, l) * 2);
            this.mcthis.field_76827_t.func_76484_a(world, randomizer, k, i1, l);
        }
        if (doGen && randomizer.nextInt(4) == 0) {
            j = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            k = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            l = randomizer.nextInt(world.func_72976_f(j, k) * 2);
            this.mcthis.field_76828_s.func_76484_a(world, randomizer, j, l, k);
        }
        if (doGen && randomizer.nextInt(8) == 0) {
            j = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            k = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            l = randomizer.nextInt(world.func_72976_f(j, k) * 2);
            this.mcthis.field_76827_t.func_76484_a(world, randomizer, j, l, k);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.REED);
        for (j = 0; doGen && j < this.mcthis.field_76799_E; ++j) {
            k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            l = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            i1 = randomizer.nextInt(world.func_72976_f(k, l) * 2);
            this.mcthis.field_76825_v.func_76484_a(world, randomizer, k, i1, l);
        }
        for (j = 0; doGen && j < 10; ++j) {
            k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            l = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            i1 = randomizer.nextInt(world.func_72976_f(k, l) * 2);
            this.mcthis.field_76825_v.func_76484_a(world, randomizer, k, i1, l);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.PUMPKIN);
        if (doGen && randomizer.nextInt(32) == 0) {
            j = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            k = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            l = randomizer.nextInt(world.func_72976_f(j, k) * 2);
            new WorldGenPumpkin().func_76484_a(world, randomizer, j, l, k);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.CACTUS);
        for (j = 0; doGen && j < this.mcthis.field_76800_F; ++j) {
            k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
            l = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
            i1 = randomizer.nextInt(world.func_72976_f(k, l) * 2);
            this.mcthis.field_76824_w.func_76484_a(world, randomizer, k, i1, l);
        }
        doGen = TerrainGen.decorate((World)world, (Random)randomizer, (int)x, (int)z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.LAKE);
        if (doGen && this.mcthis.field_76808_K) {
            for (j = 0; j < 50; ++j) {
                k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
                l = randomizer.nextInt(randomizer.nextInt(248) + 8);
                i1 = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
                new WorldGenLiquids((Block)Blocks.field_150358_i).func_76484_a(world, randomizer, k, l, i1);
            }
            for (j = 0; j < 20; ++j) {
                k = this.mcthis.field_76814_c + randomizer.nextInt(16) + 8;
                l = randomizer.nextInt(randomizer.nextInt(randomizer.nextInt(240) + 8) + 8);
                i1 = this.mcthis.field_76811_d + randomizer.nextInt(16) + 8;
                new WorldGenLiquids((Block)Blocks.field_150356_k).func_76484_a(world, randomizer, k, l, i1);
            }
        }
        MinecraftForge.EVENT_BUS.post((Event)new DecorateBiomeEvent.Post(world, randomizer, x, z));
    }
}

