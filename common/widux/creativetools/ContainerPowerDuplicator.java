package widux.creativetools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class ContainerPowerDuplicator extends Container
{
	
	private TileEntityPowerDuplicator tePower;
	
	public ContainerPowerDuplicator(IInventory inventory, TileEntityPowerDuplicator tileEntity)
	{
		this.tePower = tileEntity;
	}
	
	public void setPowerStrength(int strength)
	{
		tePower.setPowerStrength(strength);
	}
	
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.tePower.isUsableByPlayer(player);
	}
	
}
