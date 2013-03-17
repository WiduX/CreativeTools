package widux.creativetools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBlockCreative extends ItemBlock
{
	
	private Block blockPlaced;
	
	public ItemBlockCreative(int ID)
	{
		super(ID);
		this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.blockPlaced = CreativeTools.creativeBlock;
	}
	
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta)
    {
        return this.blockPlaced.getBlockTextureFromSideAndMetadata(2, meta);
    }

    public int getMetadata(int meta)
    {
        return meta;
    }
    
	public String getItemDisplayName(ItemStack itemstack)
	{
		switch(itemstack.getItemDamage())
		{
		case 0:
			return "Creative Item Provider";
		case 1:
			return "Creative Liquid Provider";
		case 2:
			return "Creative Energy Provider";
		default:
			return "";
		}
	}
}
