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
	private int tabSelected;
	//private GuiTextBox;
	
	public GUIPowerDuplicator(int xCoord, int yCoord, int zCoord, TileEntityPowerDuplicator tileEntity)
	{
		this.tePower = tileEntity;
		this.x = xCoord;
		this.y = yCoord;
		this.z = zCoord;
		this.tabSelected = tePower.getPowerMode().toInt();
	}
	
	@SuppressWarnings("unchecked")
	public void initGui()
	{
		this.buttonList.clear();
		
		// Finish & Close Button
		this.buttonList.add(new GuiButton(999, 90, 20, 50, 20, "Apply"));
		
		// Tabs
		this.buttonList.add(new GuiButton(000, 20, 20, 50, 20, "None"));
		this.buttonList.add(new GuiButton(100, 90, 20, 50, 20, "MJ"));
		this.buttonList.add(new GuiButton(200, 160, 20, 50, 20, "EU"));
		this.buttonList.add(new GuiButton(300, 230, 20, 50, 20, "UE W"));
		this.buttonList.add(new GuiButton(400, 300, 20, 50, 20, "Blu. W"));
		
		// Tab - None
		//Nothing here.
		
		// Tab - MJ
		this.buttonList.add(new GuiButton(101, 90, 20, 50, 20, "5 MJ/t"));
		this.buttonList.add(new GuiButton(102, 90, 20, 50, 20, "10 MJ/t"));
		this.buttonList.add(new GuiButton(103, 90, 20, 50, 20, "25 MJ/t"));
		this.buttonList.add(new GuiButton(104, 90, 20, 50, 20, "50 MJ/t"));
		this.buttonList.add(new GuiButton(105, 90, 20, 50, 20, "100 MJ/t"));
		
	}
	
	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{
		case 000: // Switch to tab "None"
			break;
		case 100: // Swith to tab "MJ"
			break;
		case 200: // Switch to tab "EU"
			break;
		case 300: // Switch to tab "UE W"
			break;
		case 400: //Switch to tab "Blu. W"
			break;
		case 999: // Finish & Close
            mc.displayGuiScreen(null);
            mc.setIngameFocus();
			break;
		default:
			System.out.println("[CreativeTools] Button pressed in PowerGUI was invalid. Button: " + button.id);
		}
	}
	
	private void drawContainerBackground()
	{
		//int i = mc.renderEngine.getTexture("/codechicken/wirelessredstone/core/guiWirelessLarge.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture("/codechicken/wirelessredstone/core/guiWirelessLarge.png");
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
