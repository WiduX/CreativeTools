package widux.creativetools;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPowerDuplicator extends TileEntity
{
	
	PowerMode currentMode = PowerMode.NONE;
	int powerStrength; // Applies to: BC, IC, UE, RP
	int packetSize; // Applies to: IC
	
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
	
}
