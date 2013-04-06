package widux.creativetools;

import java.util.List;
import net.minecraft.inventory.Slot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
	private LiquidStack liquid;
	
	public TileEntityLiquidDuplicator()
	{
		outputTank = new LiquidTank(LiquidContainerRegistry.BUCKET_VOLUME * 10);
	}
	
	public void updateEntity()
	{
		if(outputTank.getLiquid() != null)
		{
			liquid = outputTank.getLiquid();
			int amountMore = outputTank.getCapacity() - outputTank.getLiquid().amount;
			
			if(amountMore > 0)
			{
				outputTank.fill(new LiquidStack(liquid.itemID, amountMore, liquid.itemMeta), true);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void inventoryChanged(List slots)
	{
		Slot slot = (Slot) slots.get(0);
		LiquidStack newLiquid;
		
		if(slot.getStack() == null)
		{
			outputTank.drain(outputTank.getCapacity(), true);
			return;
		}
		
		newLiquid = LiquidContainerRegistry.getLiquidForFilledItem(slot.getStack());
		
		if(newLiquid != null)
		{
			outputTank.drain(outputTank.getCapacity(), true);
			outputTank.fill(newLiquid, true);
		}
		
	}
	
	@SuppressWarnings("static-access")
    public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		//liquid = LiquidStack.loadLiquidStackFromNBT(new NBTTagCompound("Liquid"));
		//outputTank.setLiquid(liquid);
		
		if (nbt.hasKey("stored") && nbt.hasKey("liquidId"))
		{
			LiquidStack liquid = new LiquidStack(nbt.getInteger("liquidId"), nbt.getInteger("stored"), 0);
			outputTank.setLiquid(liquid);
		}
		else
		{
			LiquidStack liquid = new LiquidStack(0, 0, 0);
			liquid.loadLiquidStackFromNBT(nbt.getCompoundTag("Liquid"));
			if (Item.itemsList[liquid.itemID] != null && liquid.amount > 0)
			{
				outputTank.setLiquid(liquid);
			}
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		if(liquid != null)
		{
			nbt.setTag("Liquid", liquid.writeToNBT(new NBTTagCompound()));
		}
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
		return new ILiquidTank[] {this.outputTank};
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

	public ItemStack decrStackSize(int slot, int amount)
	{
		if (this.item != null)
        {
            ItemStack item;

            if (this.item.stackSize <= amount)
            {
                item = this.item;
                this.item = null;
                return item;
            }
            else
            {
                item = this.item.splitStack(amount);

                if (this.item.stackSize == 0)
                {
                    this.item = null;
                }

                return item;
            }
        }
        else
        {
            return null;
        }
	}

	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return null;
	}

	public void setInventorySlotContents(int slot, ItemStack item)
	{
		this.item = item;
	}

	public String getInvName()
	{
		return "WiduX-CT-TileEntity-LiquidDuplicator-Inventory";
	}

	public boolean func_94042_c()
	{
		return false;
	}

	public int getInventoryStackLimit()
	{
		return 1;
	}

	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
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

	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack)
	{
		return false;
	}
	
	
	
}
