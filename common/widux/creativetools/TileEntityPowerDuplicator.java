package widux.creativetools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPowerDuplicator extends TileEntity
{
	
	private PowerMode currentMode = PowerMode.NONE;
	private int powerStrength; // Applies to: BC, IC, UE, RP
	private int packetSize; // Applies to: IC
	
	private static final String NBT_POWERMODE = "PowerModeValue";
	private static final String NBT_STRENGTH = "PowerStrength";
	private static final String NBT_SIZE = "PowerPacketSize";
	
	public TileEntityPowerDuplicator()
	{
		
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.currentMode = PowerMode.toMode(nbt.getInteger(NBT_POWERMODE));
		this.powerStrength = nbt.getInteger(NBT_STRENGTH);
		this.packetSize = nbt.getInteger(NBT_SIZE);
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger(NBT_POWERMODE, this.currentMode.toInt());
		nbt.setInteger(NBT_STRENGTH, this.powerStrength);
		nbt.setInteger(NBT_SIZE, this.packetSize);
	}
	
	public void setPowerMode(PowerMode newMode)
	{
		this.currentMode = newMode;
	}
	
	public void setPowerStrength(int newStrength)
	{
		this.powerStrength = newStrength;
	}
	
	public void setPacketSize(int newSize)
	{
		this.packetSize = newSize;
	}
	
	public PowerMode getPowerMode()
	{
		return this.currentMode;
	}
	
	public int getPowerStrength()
	{
		return this.powerStrength;
	}
	
	public int getPacketSize()
	{
		return this.packetSize;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
}
