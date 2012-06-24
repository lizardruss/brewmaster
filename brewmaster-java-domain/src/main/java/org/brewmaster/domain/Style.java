package org.brewmaster.domain;

import javax.persistence.Id;
import java.util.List;

public class Style {
    @Id
    private Long id;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    private String appearance;

    public String getAppearance() {
	return appearance;
    }

    public void setAppearance(String appearance) {
	this.appearance = appearance;
    }

    private String aroma;

    public String getAroma() {
	return aroma;
    }

    public void setAroma(String aroma) {
	this.aroma = aroma;
    }

    private StyleCategory category;

    public StyleCategory getCategory() {
	return category;
    }

    public void setCategory(StyleCategory category) {
	this.category = category;
    }

    private String comments;

    public String getComments() {
	return comments;
    }

    public void setComments(String comments) {
	this.comments = comments;
    }

    private List<String> commercialExamples;

    public List<String> getCommercialExamples() {
	return commercialExamples;
    }

    public void setCommercialExamples(List<String> commercialExamples) {
	this.commercialExamples = commercialExamples;
    }
    
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String flavor;

    public String getFlavor() {
	return flavor;
    }

    public void setFlavor(String flavor) {
	this.flavor = flavor;
    }
    
    private String history;

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    private String impression;

    public String getImpression() {
	return impression;
    }

    public void setImpression(String impression) {
	this.impression = impression;
    }

    private String ingredients;

    public String getIngredients() {
	return ingredients;
    }

    public void setIngredients(String ingredients) {
	this.ingredients = ingredients;
    }

    private String mouthfeel;

    public String getMouthfeel() {
	return mouthfeel;
    }

    public void setMouthfeel(String mouthfeel) {
	this.mouthfeel = mouthfeel;
    }

    private String name;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    private StyleStatistics statistics;

    public StyleStatistics getStatistics() {
	return statistics;
    }

    public void setStatistics(StyleStatistics statistics) {
	this.statistics = statistics;
    }

    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append("Style");
	buffer.append("\n");
	buffer.append("\t\t[");
	buffer.append("\n");
	buffer.append("\t\t\tappearance=" + appearance);
	buffer.append("\n");
	buffer.append("\t\t\taroma=" + aroma);
	buffer.append("\n");
	buffer.append("\t\t\tcomments=" + comments);
	buffer.append("\n");
	buffer.append("\t\t\tcommercialExamples=" + commercialExamples);
	buffer.append("\n");
	buffer.append("\t\t\tdescription=" + description);
	buffer.append("\n");
	buffer.append("\t\t\tflavor=" + flavor);
	buffer.append("\n");
	buffer.append("\t\t\thistory=" + history);
	buffer.append("\n");
	buffer.append("\t\t\timpression=" + impression);
	buffer.append("\n");
	buffer.append("\t\t\tingredients=" + ingredients);
	buffer.append("\n");
	buffer.append("\t\t\tmouthfeel=" + mouthfeel);
	buffer.append("\n");
	buffer.append("\t\t\tname=" + name);
	buffer.append("\n");
	buffer.append("\t\t\tstatistics=" + statistics);
	buffer.append("\n");
	buffer.append("\t\t]");
	return buffer.toString();
    }

}
