package widux.creativetools;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	
	public void addComponents()
	{
		CreativeTools.creativeBlock = new BlockCreative(CreativeTools.config.getBlockID(1200, "Creative Block", null)).setUnlocalizedName("WiduX-CT-Block-CreativeBlock");
	}
	
	public void registerBlocks()
	{
		GameRegistry.registerBlock(CreativeTools.creativeBlock, ItemBlockCreative.class, "WiduX-CT-Block-CreativeBlock");
		GameRegistry.registerTileEntity(TileEntityItemDuplicator.class, "WiduX-CT-TileEntity-ItemDuplicator");
		GameRegistry.registerTileEntity(TileEntityLiquidDuplicator.class, "WiduX-CT-TileEntity-LiquidDuplicator");
		//GameRegistry.registerTileEntity(TileEntityEnergyDuplicator.class, "WiduX-CT-TileEntity-EnergyDuplicator");
	}
	
	public void addNames()
	{
		// Not done server-side. See ClientProxy.
	}
	
	public void addRecipes()
	{
		// No recipes. Creative mode only.
	}
	
	public void addRenderers()
	{
		// Not done server-side. See ClientProxy.
	}
	
}
