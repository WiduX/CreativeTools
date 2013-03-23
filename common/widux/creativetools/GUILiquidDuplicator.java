package widux.creativetools;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GUILiquidDuplicator extends GuiContainer
{
	
	public GUILiquidDuplicator(InventoryPlayer playerInv, TileEntityLiquidDuplicator teItem)
	{
		super(new ContainerLiquidDuplicator(playerInv, teItem));
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString("Liquid Duplicator", 8, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
    
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
    	this.mc.renderEngine.bindTexture("/mods/" + ModInfo.INTERNAL_NAME + "/textures/gui/LiquidDuplicatorSimple.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }
	
}
