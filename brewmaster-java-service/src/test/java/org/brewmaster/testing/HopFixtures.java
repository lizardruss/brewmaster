package org.brewmaster.testing;

import org.brewmaster.domain.Hop;

public class HopFixtures {
	public static Hop cascade()
	{
		Hop hop = new Hop();
		hop.setName("Cascade");
		return hop;
	}

	public static Hop centennial()
	{
		Hop hop = new Hop();
		hop.setName("Centennial");
		return hop;
	}
	
	public static Hop hallertauer()
	{
		Hop hop = new Hop();
		hop.setName("Hallertauer");
		return hop;
	}
	
	public static Hop magnum()
	{
		Hop hop = new Hop();
		hop.setName("Magnum");
		return hop;
	}
}
