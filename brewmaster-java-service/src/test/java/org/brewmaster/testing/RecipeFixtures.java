package org.brewmaster.testing;

import org.brewmaster.domain.Recipe;

public class RecipeFixtures {
	public static Recipe pilsenerUrquelle()
	{
		Recipe recipe = new Recipe();
		recipe.setName("Pilsener Urquelle");
		recipe.setDescription("The original lager.");
		return recipe;
	}
	
	public static Recipe krampusRed()
	{
		Recipe recipe = new Recipe();
		recipe.setName("Professor Grizzle's Krampus Red");
		recipe.setDescription("An Irish Red for the naughty ones.");
		return recipe;
	}
	
	public static Recipe hatePorter()
	{
		Recipe recipe = new Recipe();
		recipe.setName("Uncle Luke's Hate Porter");
		recipe.setDescription("The porter you hate to love.");
		return recipe;
	}
}
