package widux.creativetools;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityItemDuplicator extends TileEntity implements ISidedInventory
{
	
	private ItemStack[] items = new ItemStack[2];
	private static final String NBT_ITEM = "Item";
	
	public TileEntityItemDuplicator()
	{
		
	}

    public void readFromNBT(NBTTagCompound nbt)
    {
    	super.readFromNBT(nbt);
    	
    	for(int slot = 0; slot < items.length; slot++)
    	{
    		if(nbt.hasKey(NBT_ITEM + "_" + slot))
    		{
    			this.items[slot] = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag(NBT_ITEM + "_" + slot));
    		}
    	}
    }
    
    public void writeToNBT(NBTTagCompound nbt)
    {
    	super.writeToNBT(nbt);

    	for(int slot = 0; slot < items.length; slot++)
    	{
    		if(items[slot] != null)
    		{
    			nbt.setCompoundTag(NBT_ITEM + "_" + slot, this.items[slot].writeToNBT(new NBTTagCompound()));
    		}
    	}
    }
	
    public void updateEntity()
    {
    	if(items[0] != null)
    	{
    		items[1] = items[0].copy();
			items[1].stackSize = items[1].getMaxStackSize();
    	}
    }
    
	public int getSizeInventory()
	{
		return 2;
	}

	public ItemStack getStackInSlot(int slot)
	{
		return items[slot];
	}

	public ItemStack decrStackSize(int slot, int amount)
	{
		if (this.items != null)
        {
            ItemStack item;

            if (this.items[slot].stackSize <= amount)
            {
                item = this.items[slot];
                this.items[slot] = null;
                return item;
            }
            else
            {
                item = this.items[slot].splitStack(amount);

                if (this.items[slot].stackSize == 0)
                {
                    this.items[slot] = null;
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
		return this.items[slot];
	}

	public void setInventorySlotContents(int slot, ItemStack item)
	{
        this.items[slot] = item;

        if (item != null && item.stackSize > this.getInventoryStackLimit())
        {
            item.stackSize = this.getInventoryStackLimit();
        }
	}

	public String getInvName()
	{
		return "WiduX-CT-TilEntity-ItemDuplicator-Inventory";
	}

	public boolean func_94042_c()
	{
		return false;
	}

	public int getInventoryStackLimit()
	{
		return 64;
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
	
	public void dropContentsAndInvalidate(World world, int x, int y, int z)
	{
		if(this.items[0] != null)
		{
			float f = 0.7F;
			double xItem = (double)(worldObj.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
			double yItem = (double)(worldObj.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
			double zItem = (double)(worldObj.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
			EntityItem item = new EntityItem(worldObj, (double)xCoord + xItem, (double)yCoord + yItem, (double)zCoord + zItem, new ItemStack(this.items[0].itemID, 1, 0));
			item.delayBeforeCanPickup = 10;
			worldObj.spawnEntityInWorld(item);
		}
		this.invalidate();
	}

	public int func_94127_c(int i)
	{
		return 0;
	}

	public int func_94128_d(int i)
	{
		return 1;
	}

	@Override
	public boolean isInvNameLocalized()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getSizeInventorySide(int var1)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean func_102007_a(int i, ItemStack itemstack, int j)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean func_102008_b(int i, ItemStack itemstack, int j)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
