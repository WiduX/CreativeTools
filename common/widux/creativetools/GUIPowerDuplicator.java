package widux.creativetools;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUIPowerDuplicator extends GuiScreen
{
	
	private TileEntityPowerDuplicator tePower;
	private int x, y, z;
	//private GuiTextBox;
	
	public GUIPowerDuplicator(int xCoord, int yCoord, int zCoord, TileEntityPowerDuplicator tileEntity)
	{
		this.tePower = tileEntity;
		this.x = xCoord;
		this.y = yCoord;
		this.z = zCoord;
	}
	
	@SuppressWarnings("unchecked")
	public void initGui()
	{
		this.buttonList.clear();
		this.drawContainerBackground();
		
		// Finish & Close Button
		this.buttonList.add(new GuiButton(999, 325, 210, 80, 20, "Close"));
		
		// Tabs
		this.buttonList.add(new GuiButton(000, 20, 20, 50, 20, "None"));
		this.buttonList.add(new GuiButton(100, 90, 20, 50, 20, "MJ"));
		this.buttonList.add(new GuiButton(200, 160, 20, 50, 20, "EU"));
		this.buttonList.add(new GuiButton(300, 230, 20, 50, 20, "UE W"));
		this.buttonList.add(new GuiButton(400, 300, 20, 50, 20, "Blu. W"));
		
		// Tab - None
		// Nothing here.
		
		// Tab - MJ
		this.buttonList.add(new GuiButton(101, 195, 80, 50, 20, "5 MJ/t"));
		this.buttonList.add(new GuiButton(102, 195, 105, 50, 20, "10 MJ/t"));
		this.buttonList.add(new GuiButton(103, 195, 130, 50, 20, "25 MJ/t"));
		this.buttonList.add(new GuiButton(104, 195, 155, 50, 20, "50 MJ/t"));
		this.buttonList.add(new GuiButton(105, 195, 180, 50, 20, "100 MJ/t"));
		
		// Tab - EU
		// Nothing here yet.
		
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
			break;
		case 100: // Swith to tab "MJ"
			switchTab(1);
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
		int posx = width / 2 - 118;
		int posy = height / 2 - 106;
		drawTexturedModalRect(posx, posy, 0, 0, 237, 190);
	}
	
	public void drawScreen(int i, int j, float f)
	{
		//this.drawContainerBackground();
		super.drawScreen(i, j, f);
	}
	
}
