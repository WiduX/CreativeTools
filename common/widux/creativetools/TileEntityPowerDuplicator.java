package widux.creativetools;

import buildcraft.api.core.SafeTimeTracker;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerFramework;
import buildcraft.api.transport.IPipeConnection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityPowerDuplicator extends TileEntity implements IPowerProvider, IPipeConnection, IPowerReceptor
{
	
	private PowerMode currentMode = PowerMode.NONE;
	private int powerStrength; // Applies to: BC, IC, UE, RP
	private int packetSize; // Applies to: IC
	
	private IPowerProvider provider;
	
	private static final String NBT_POWERMODE = "PowerModeValue";
	private static final String NBT_STRENGTH = "PowerStrength";
	private static final String NBT_SIZE = "PowerPacketSize";
	
	public TileEntityPowerDuplicator()
	{
		provider = PowerFramework.currentFramework.createPowerProvider();
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

	
	public int getLatency()
	{
		return 0;
	}

	
	public int getMinEnergyReceived()
	{
		return 0;
	}

	
	public int getMaxEnergyReceived()
	{
		return 0;
	}

	
	public int getMaxEnergyStored()
	{
		return 10000;
	}

	
	public int getActivationEnergy()
	{
		return 0;
	}

	
	public float getEnergyStored()
	{
		return this.powerStrength;
	}

	
	public void configure(int latency, int minEnergyReceived, int maxEnergyReceived, int minActivationEnergy, int maxStoredEnergy)
	{
		
	}

	
	public void configurePowerPerdition(int powerLoss, int powerLossRegularity)
	{
		
	}

	
	public boolean update(IPowerReceptor receptor)
	{
		return true;
	}

	
	public boolean preConditions(IPowerReceptor receptor)
	{
		return true;
	}

	
	public float useEnergy(float min, float max, boolean doUse)
	{
		return 0;
	}
	
	public void receiveEnergy(float quantity, ForgeDirection from)
	{
		
	}
	
	public boolean isPowerSource(ForgeDirection from)
	{
		return true;
	}

	public SafeTimeTracker getTimeTracker()
	{
		return null;
	}

	public boolean isPipeConnected(ForgeDirection with)
	{
		return true;
	}

	public void updateEntity()
	{
		TileEntity te = worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
		if(te != null && te instanceof IPowerReceptor)
		{
			if(((IPowerReceptor) te).getPowerProvider() != null)
			{
				((IPowerReceptor) te).getPowerProvider().receiveEnergy(powerStrength, ForgeDirection.DOWN);
				System.out.println("Power: " + powerStrength);
			}
		}
	}
	
    public Packet getDescriptionPacket()
    {
    	NBTTagCompound tileTag = new NBTTagCompound();
    	this.writeToNBT(tileTag);
    	return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tileTag);
    }

    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
    	this.readFromNBT(pkt.customParam1);
    }
	
	public int produceEnergy(int energy)
	{
		return 9001;
	}
	
	public void setPowerProvider(IPowerProvider provider)
	{
		this.provider = provider;
	}

	public IPowerProvider getPowerProvider()
	{
		return provider;
	}

	public void doWork()
	{
		
	}

	public int powerRequest()
	{
		return 0;
	}

	@Override
	public int powerRequest(ForgeDirection from)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
}
