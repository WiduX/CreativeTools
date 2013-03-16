package widux.creativetools;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ClientProxy extends CommonProxy
{
	
	@Override
	public void addNames()
	{
		LanguageRegistry.addName(new ItemStack(CreativeTools.creativeBlock, 1, 0), "Creative Item Provider");
		LanguageRegistry.addName(new ItemStack(CreativeTools.creativeBlock, 1, 1), "Creative Liquid Provider");
		LanguageRegistry.addName(new ItemStack(CreativeTools.creativeBlock, 1, 2), "Creative Energy Provider");
	}
	
	@Override
	public void addRenderers()
	{
		
	}
	
}
