package widux.creativetools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIManager implements IGuiHandler
{
	
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == 0)
		{
			return new ContainerItemDuplicator(player.inventory, (TileEntityItemDuplicator) ((TileEntityCreative) world.getBlockTileEntity(x, y, z)));
		}
		else
		{
			return null;
		}
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(ID == 0)
		{
			return new GUIItemDuplicator(player.inventory, (TileEntityItemDuplicator) ((TileEntityCreative) world.getBlockTileEntity(x, y, z)));
		}
		else
		{
			return null;
		}
	}
	
	
	
}
