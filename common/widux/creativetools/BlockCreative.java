package widux.creativetools;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCreative extends BlockContainer
{
	
	public BlockCreative(int ID)
	{
		super(ID, Material.iron);
	}

	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityCreative();
	}
	
}
