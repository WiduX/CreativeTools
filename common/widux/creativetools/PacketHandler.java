package widux.creativetools;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{

	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		System.out.println("Packet!");
		if(packet.channel.equals("WiduX-CT-Chn"))
		{
			handlePowerUpdate(packet);
		}
	}
	
	private void handlePowerUpdate(Packet250CustomPayload packet)
	{
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		int str = 0;
		int mode = 0;
		int size = 0;
		int x = 0;
		int y = 0;
		int z = 0;
		
		TileEntityPowerDuplicator tePower;
		
		try
		{
			str = inputStream.readInt();
			mode = inputStream.readInt();
			size = inputStream.readInt();
			x = inputStream.readInt();
			y = inputStream.readInt();
			z = inputStream.readInt();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		tePower = (TileEntityPowerDuplicator) Minecraft.getMinecraft().theWorld.getBlockTileEntity(x, y, z);
		tePower.setPowerStrength(str);
		tePower.setPowerMode(PowerMode.toMode(mode));
		tePower.setPacketSize(size);
		System.out.println("DerpPack!");
		
	}
	
	
	
}
