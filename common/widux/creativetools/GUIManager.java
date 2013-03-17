package widux.creativetools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIManager implements IGuiHandler
{
	
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
		case 0:
			return new ContainerItemDuplicator(player.inventory, (TileEntityItemDuplicator) world.getBlockTileEntity(x, y, z));
		case 1:
			//return new ContainerLiquidDuplicator(player.inventory, (TileEntityLiquidDuplicator) world.getBlockTileEntity(x, y, z));
		case 2:
			//return new ContainerEnergyDuplicator(player.inventory, (TileEntityEnergyDuplicator) world.getBlockTileEntity(x, y, z));
		default:
			return null;
		}
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
		case 0:
			return new GUIItemDuplicator(player.inventory, (TileEntityItemDuplicator) world.getBlockTileEntity(x, y, z));
		case 1:
		//	return new GUILiquidDuplicator(player.inventory, (TileEntityLiquidDuplicator) world.getBlockTileEntity(x, y, z));
		case 2:
		//	return new GUIEnergyDuplicator(player.inventory, (TileEntityEnergyDuplicator) world.getBlockTileEntity(x, y, z));
		default:
			return null;
		}
	}
	
	
	
}
