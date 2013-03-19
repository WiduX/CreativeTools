package widux.creativetools;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUIPowerDuplicator extends GuiScreen
{
	
	TileEntityPowerDuplicator tePower;
	int x, y, z;
	int tabSelected;
	
	public GUIPowerDuplicator(int xCoord, int yCoord, int zCoord, TileEntityPowerDuplicator tileEntity)
	{
		this.tePower = tileEntity;
		this.x = xCoord;
		this.y = yCoord;
		this.z = zCoord;
		this.tabSelected = tePower.getPowerMode().toInt();
	}
	
	public void initGui()
	{
		this.buttonList.clear();
	}
	
	public void drawScreen(int i, int j, float f)
	{
		super.drawScreen(i, j, f);
	}
	
	public void actionPerformed(GuiButton button)
	{
		
	}
	
}
