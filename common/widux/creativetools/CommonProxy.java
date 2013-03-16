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
		GameRegistry.registerBlock(CreativeTools.creativeBlock, "WiduX-CT-Block-CreativeBlock");
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
