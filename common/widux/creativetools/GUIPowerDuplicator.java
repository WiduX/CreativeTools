package widux.creativetools;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUIPowerDuplicator extends GuiScreen
{
	
	private TileEntityPowerDuplicator tePower;
	private int x, y, z;
	//private GuiTextBox;
	private ContainerPowerDuplicator container;
	
	public GUIPowerDuplicator(int xCoord, int yCoord, int zCoord, TileEntityPowerDuplicator tileEntity)
	{
		this.tePower = tileEntity;
		this.x = xCoord;
		this.y = yCoord;
		this.z = zCoord;
		container = new ContainerPowerDuplicator(null, tePower);
	}
	
	@SuppressWarnings("unchecked")
	public void initGui()
	{
		this.buttonList.clear();
		
		// Finish & Close Button
		this.buttonList.add(new GuiButton(999, width / 2 + 56, height / 2 + 86, 60, 20, "Close"));
		
		// Tabs
		this.buttonList.add(new GuiButton(000, width / 2 - 116, height / 2 - 106, 40, 20, "None"));
		this.buttonList.add(new GuiButton(100, width / 2 - 68, height / 2 - 106, 40, 20, "MJ"));
		this.buttonList.add(new GuiButton(200, width / 2 - 20, height / 2 - 106, 40, 20, "EU"));
		this.buttonList.add(new GuiButton(300, width / 2 + 28, height / 2 - 106, 40, 20, "UE W"));
		this.buttonList.add(new GuiButton(400, width / 2 + 76, height / 2 - 106, 40, 20, "Blu. W"));
		
		// Tab - None
		// Nothing here.
		
		// Tab - MJ
		this.buttonList.add(new GuiButton(101, width / 2 + 56, height / 2 - 66, 60, 20, "5 MJ/t"));
		this.buttonList.add(new GuiButton(102, width / 2 + 56, height / 2 - 38, 60, 20, "10 MJ/t"));
		this.buttonList.add(new GuiButton(103, width / 2 + 56, height / 2 - 10, 60, 20, "25 MJ/t"));
		this.buttonList.add(new GuiButton(104, width / 2 + 56, height / 2 + 18, 60, 20, "50 MJ/t"));
		this.buttonList.add(new GuiButton(105, width / 2 + 56, height / 2 + 46, 60, 20, "100 MJ/t"));
		
		// Tab - EU
		this.buttonList.add(new GuiButton(201, width / 2 + 56, height / 2 - 66, 60, 20, "5 EU/t"));
		this.buttonList.add(new GuiButton(202, width / 2 + 56, height / 2 - 38, 60, 20, "32 EU/t"));
		this.buttonList.add(new GuiButton(203, width / 2 + 56, height / 2 - 10, 60, 20, "128 EU/t"));
		this.buttonList.add(new GuiButton(204, width / 2 + 56, height / 2 + 18, 60, 20, "512 EU/t"));
		this.buttonList.add(new GuiButton(205, width / 2 + 56, height / 2 + 46, 60, 20, "2048 EU/t"));
		
		// Tab - UE W
		// Nothing here yet.
		
		// Tab - Blu. W
		// Nothing here yet.
		
		// Open the GUI with the correct tab selected.
		selectCurrent();
		
	}
	
	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{
		// Switching tab
		case 000: // Switch to tab "None"
			switchTab(0);
			tePower.setPowerMode(PowerMode.NONE);
			break;
		case 100: // Swith to tab "MJ"
			switchTab(1);
			tePower.setPowerMode(PowerMode.BUILDCRAFT);
			break;
		case 200: // Switch to tab "EU"
			switchTab(2);
			break;
		case 300: // Switch to tab "UE W"
			switchTab(3);
			break;
		case 400: //Switch to tab "Blu. W"
			switchTab(4);
			break;
		
		// On Tab - MJ
		case 101:
			this.tePower.setPowerStrength(5); // 5 MJ/t
			apply();
			break;
		case 102:
			this.tePower.setPowerStrength(10); // 10 MJ/t
			break;
		case 103:
			this.tePower.setPowerStrength(25); // 25 MJ/t
			break;
		case 104:
			this.tePower.setPowerStrength(50); // 50 MJ/t
			break;
		case 105:
			this.tePower.setPowerStrength(100); // 100 MJ/t
			break;
			
		// Others
		case 999: // Finish & Close
            mc.displayGuiScreen(null);
            mc.setIngameFocus();
			break;
		default:
			System.out.println("[CreativeTools] Button pressed in PowerGUI was invalid. Button: " + button.id);
		}
	}
	
	private void apply()
	{
		int str = tePower.getPowerStrength();
		int mode = tePower.getPowerMode().toInt();
		int size = tePower.getPacketSize();
		
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream(24);
		DataOutputStream output = new DataOutputStream(byteArray);
		try
		{
			output.writeInt(str);
			output.writeInt(mode);
			output.writeInt(size);
			output.writeInt(x);
			output.writeInt(y);
			output.writeInt(z);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "WiduX-CT-Chn";
		packet.data = byteArray.toByteArray();
		packet.length = byteArray.size();
		EntityClientPlayerMP player = (EntityClientPlayerMP) this.mc.thePlayer;
		player.sendQueue.addToSendQueue(packet);
		//PacketDispatcher.sendPacketToServer(packet);
		System.out.println("PacketSend");
	}
	
	private void selectCurrent()
	{
		switchTab(tePower.getPowerMode().toInt());
	}
	
	private void switchTab(int newTab)
	{
		GuiButton button;
		for(int i = 0; i < this.buttonList.size(); i++)
		{
			button = (GuiButton)this.buttonList.get(i);
			if(button.id != 000 && button.id != 100 && button.id != 200 && button.id != 300 && button.id != 400 && button.id != 999) // Make sure we're not removing an important button.
			{
				if((button.id / 100.00F) > (newTab + 0.99F) || (button.id / 100.00F) < (newTab - 0.01F))
				{
					button.drawButton = false;
				}
				else
				{
					button.drawButton = true;
				}
			}
			if(button.id == (newTab * 100))
			{
				button.enabled = false;
			}
			else
			{
				button.enabled = true;
			}
		}
	}
	
	private void drawContainerBackground()
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture("/mods/" + ModInfo.INTERNAL_NAME + "/textures/gui/PowerDuplicator.png");
		int posx = width / 2 - 128;
		int posy = height / 2 - 118;
		drawTexturedModalRect(posx, posy, 0, 0, 256, 236);
	}
	
	public void drawScreen(int i, int j, float f)
	{
		this.drawDefaultBackground();
		this.drawContainerBackground();
		super.drawScreen(i, j, f);
	}
	
}
