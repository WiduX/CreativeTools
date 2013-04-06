package widux.creativetools;

import java.io.File;
import net.minecraft.block.Block;
import widux.core.ConfigAssist;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;


@Mod
(
	modid = ModInfo.INTERNAL_NAME,
	name = ModInfo.NAME,
	version = ModInfo.VERSION
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false,
	channels = {"WiduX-CT-Chn"},
	packetHandler = PacketHandler.class
)
public class CreativeTools
{
	
	public static ConfigAssist config;
	
	public static Block creativeBlock;
	
	@Instance(ModInfo.INTERNAL_NAME)
	public static CreativeTools instance;
	
	@SidedProxy
	(
		clientSide = "widux.creativetools.ClientProxy",
		serverSide = "widux.creativetools.CommonProxy"
	)
	public static CommonProxy common;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new ConfigAssist(ModInfo.NAME, new File(event.getModConfigurationDirectory() + "/WiduX/CreativeTools.cfg"));
		config.init();
	}
	
	@Init
	public void init(FMLInitializationEvent event)
	{
		common.addComponents();
		common.registerBlocks();
		common.addNames();
		common.addRecipes();
		common.addRenderers();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		config.save();
		NetworkRegistry.instance().registerGuiHandler(instance, new GUIManager());
	}
}
