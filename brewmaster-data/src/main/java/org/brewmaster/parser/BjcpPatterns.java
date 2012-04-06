package org.brewmaster.parser;

import java.util.regex.Pattern;

public enum BjcpPatterns {
	CATEGORY("\\d+\\.[\\p{Lu}\\(\\)\\s\\/\\/\\-]+"),
	STYLE("\\d+\\p{Lu}\\.\\s\\p{Lu}[\\,\\p{L}\\(\\)\\s\\/\\/\\-\\p{Digit}]+"),
	AROMA("^Aroma\\:[\\p{L}\\s\\p{Punct}\\p{Digit}]+$"),
	AROMA_APPEARANCE_FLAVOR_MOUTHFEEL("^Aroma, appearance, flavor, mouthfeel[\\p{L}\\s\\p{Punct}\\p{Digit}]+$"),
	APPEARANCE("^Appearance\\:[\\p{L}\\s\\p{Punct}\\p{Digit}]+$"),
	FLAVOR("^Flavor\\:[\\p{L}\\s\\p{Punct}\\p{Digit}]+$"),
	HISTORY("^History\\:[\\p{L}\\s\\p{Punct}\\p{Digit}]+$"),
	MOUTHFEEL("^Mouthfeel\\:[\\p{L}\\s\\p{Punct}\\p{Digit}]+$"),
	OVERALL_IMPRESSION("^Overall Impression\\:[\\p{L}\\s\\p{Punct}\\p{Digit}]+$"),
	COMMENTS("^Comments\\:[\\p{L}\\s\\p{Punct}\\p{Digit}]+$"),
	INGREDIENTS("^Ingredients\\:[\\s\\p{L}\\p{Punct}\\p{Digit}]+$"),
	VITAL_STATISTICS("^Vital Statistics\\:[\\–\\p{L}\\s\\p{Punct}\\p{Digit}]+$"),
	COMMERCIAL_EXAMPLES("^Commercial Examples\\:[\\p{L}\\s\\p{Punct}\\p{Digit}[^\u0000-\u0080]]+$");
	
	private BjcpPatterns(String pattern)
	{
		this.pattern = Pattern.compile(pattern);
	}
	
	private final Pattern pattern;
	
	public Pattern getPattern()
	{
		return pattern;
	}
	
	public boolean matches(String line)
	{
		return pattern.matcher(line).matches();
	}
	
	public static void main(String[] args)
	{
	    System.out.println(COMMERCIAL_EXAMPLES.matches("Commercial Examples: Pilsner Urquell, Krušovice Imperial 12°,"));
	    System.out.println(COMMERCIAL_EXAMPLES.matches("Commercial Examples: Pilsner Urquell, Krusovice Imperial 12°,"));
	    System.out.println(COMMERCIAL_EXAMPLES.matches("Commercial Examples: Pilsner Urquell, Krusovice Imperial 12,"));
	}
}
