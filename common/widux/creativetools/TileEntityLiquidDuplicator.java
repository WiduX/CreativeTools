package widux.creativetools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityLiquidDuplicator extends TileEntity implements IInventory, ITankContainer
{
	
	private ItemStack item;
	private LiquidTank outputTank;
	
	
	public TileEntityLiquidDuplicator()
	{
		outputTank = new LiquidTank(LiquidContainerRegistry.BUCKET_VOLUME * 10);
	}
	
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill)
	{
		return 0;
	}

	public int fill(int tankIndex, LiquidStack resource, boolean doFill)
	{
		return 0;
	}

	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return drain(0, maxDrain, doDrain);
	}

	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain)
	{
		return outputTank.drain(maxDrain, doDrain);
	}

	public ILiquidTank[] getTanks(ForgeDirection direction)
	{
		return new ILiquidTank[]{this.outputTank};
	}

	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type)
	{
		return this.outputTank;
	}

	public int getSizeInventory()
	{
		return 1;
	}

	public ItemStack getStackInSlot(int slot)
	{
		if(slot == 0)
		{
			return this.item;
		}
		else
		{
			return null;
		}
	}

	public ItemStack decrStackSize(int slot, int amountDecr)
	{
		return null;
	}

	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return null;
	}

	public void setInventorySlotContents(int slot, ItemStack item)
	{
		
	}

	public String getInvName()
	{
		return null;
	}

	public boolean func_94042_c()
	{
		return false;
	}

	public int getInventoryStackLimit()
	{
		return 0;
	}

	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return false;
	}

	public void openChest()
	{
		
	}

	public void closeChest()
	{
		
	}

	public boolean func_94041_b(int i, ItemStack itemstack)
	{
		return false;
	}
	
	
	
}
