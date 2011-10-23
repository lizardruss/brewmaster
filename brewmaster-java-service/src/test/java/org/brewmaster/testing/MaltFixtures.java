package org.brewmaster.testing;

import org.brewmaster.domain.Malt;

public class MaltFixtures {
	public static Malt crystal()
	{
		Malt malt = new Malt();
		malt.setName("Crystal");
		return malt;
	}
	
	public static Malt pale2row()
	{
		Malt malt = new Malt();
		malt.setName("Pale 2-Row");
		return malt;
	}

	public static Malt pale6row()
	{
		Malt malt = new Malt();
		malt.setName("Pale 6-Row");
		return malt;
	}

	public static Malt germanMunich()
	{
		Malt malt = new Malt();
		malt.setName("Munich");
		return malt;
	}
	
	public static Malt carapils()
	{
		Malt malt = new Malt();
		malt.setName("Carapils");
		return malt;
	}
}
