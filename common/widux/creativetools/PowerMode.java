package widux.creativetools;

public enum PowerMode
{	
	NONE("None", "-"),
	BUILDCRAFT("BuildCraft", "MJ"),
	INDUSTRIALCRAFT("IndustrialCraft", "EU"),
	REDPOWER("RedPower", "Blutricity"),
	UNIVERSAL_ELECTRICITY("Universal Electricity", "J"),
	FACTORIZATION("Factorization", "Energy");
	
	private String name;
	private String power;
	
	private PowerMode(String properName, String powerName)
	{
		name = properName;
		power = powerName;
	}
	
	public int toInt()
	{
		return this.ordinal();
	}
	
	public static PowerMode toMode(int type)
	{
		return values()[type];
	}

	public String getProperName()
	{
		return name;
	}
	
	public String getPowerName()
	{
		return power;
	}
	
}
