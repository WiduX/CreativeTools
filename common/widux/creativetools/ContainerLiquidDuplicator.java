package widux.creativetools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerLiquidDuplicator extends Container
{
	
private TileEntityLiquidDuplicator teLiquid;
	
	public ContainerLiquidDuplicator(IInventory inventory, TileEntityLiquidDuplicator teLiquid)
	{
		this.teLiquid = teLiquid;
		
		this.addSlotToContainer(new Slot(teLiquid, 0, 80, 35));
		((Slot)this.inventorySlots.get(0)).setBackgroundIconIndex(Item.bucketEmpty.getIconFromDamage(0));
		
		addPlayerInventory(inventory);
	}
	
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.teLiquid.isUseableByPlayer(player);
    }
    
    public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
    	teLiquid.inventoryChanged(inventorySlots);
    	System.out.println("Closed!");
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
    	ItemStack item = null;
        Slot slot = (Slot) this.inventorySlots.get(slotID);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemInSlot = slot.getStack();
            item = itemInSlot.copy();

            if (slotID < 1)
            {
                if (!this.mergeItemStack(itemInSlot, 1, 37, true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemInSlot, 0, 1, false))
            {
                return null;
            }

            if (itemInSlot.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemInSlot.stackSize == item.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemInSlot);
        }
        
        return item;
    }
	
	protected void addPlayerInventory(IInventory playerInv)
    {
    	for (int i = 0; i < 3; i++)
    	{
    		for (int j = 0; j < 9; j++)
    		{
    			addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
    		}
    	}

    	for (int i = 0; i < 9; i++)
    	{
    		addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
    	}
    }
	
}
