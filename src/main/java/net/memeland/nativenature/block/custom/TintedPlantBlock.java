package net.memeland.nativenature.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TintedPlantBlock extends BushBlock {
    public static final MapCodec<TintedPlantBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    com.mojang.serialization.Codec.STRING.fieldOf("essence_name").forGetter(block -> block.essenceName),
                    propertiesCodec()
            ).apply(instance, TintedPlantBlock::new)
    );

    private final String essenceName;

    public TintedPlantBlock(String essenceName, Properties properties) {
        super(properties);
        this.essenceName = essenceName;
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    public String getEssenceName() {
        return essenceName;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }
}