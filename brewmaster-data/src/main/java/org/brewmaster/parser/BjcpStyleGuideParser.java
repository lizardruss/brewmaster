package org.brewmaster.parser;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.brewmaster.domain.Style;
import org.brewmaster.domain.StyleCategory;
import org.brewmaster.domain.StyleStatistics;

import static org.brewmaster.parser.BjcpPatterns.*;

public class BjcpStyleGuideParser {
    public static final Pattern TABLE_TITLE_PATTERN = Pattern
	    .compile("\\s*2004 BJCP STYLE CHART, 2008 Update\\s*");
    public static final Pattern TABLE_TITLE_LINE_PATTERN = Pattern
	    .compile("^\\s*2004 BJCP STYLE CHART, 2008 Update\\s*$");

    public static final Pattern FLAVOR_PATTERN = Pattern
	    .compile("^Flavor\\:[\\p{L}\\s\\p{Punct}]+$");

    private Buffer buffer;

    private Scanner lineScanner;

    private int mode;

    public BjcpStyleGuideParser() {
	super();
	buffer = new Buffer();
    }

    public void parse(String fileName) throws Exception {
	PDFParser parser = new PDFParser(new FileInputStream(fileName));
	parser.parse();

	PDDocument document = parser.getPDDocument();
	PDFTextStripper textStripper = new PDFTextStripper();

	lineScanner = new Scanner(textStripper.getText(document));
	lineScanner.useDelimiter("\\n");

	mode = ParserModes.READ_CATEGORY_OR_STYLE_TITLE;

	List<StyleCategory> categories = new LinkedList<StyleCategory>();
	StyleCategory currentCategory = null;
	Style currentStyle = null;

	while (lineScanner.hasNext()) {
	    String currentLine = lineScanner.next();

	    if (CATEGORY.matches(currentLine)) {
		if (mode == ParserModes.READ_COMMERCIAL_EXAMPLES) {
		    String commercialExamples = buffer.readAll();
		    List<String> commercialExamplesList = parseCommercialExamples(commercialExamples);
		    currentStyle.setCommercialExamples(commercialExamplesList);
		}

		currentCategory = new StyleCategory();
		currentCategory.setTitle(currentLine);
		categories.add(currentCategory);

		currentStyle = null;

		buffer.clearBuffer();
		mode = ParserModes.READ_CATEGORY_DESCRIPTION;
	    } else if (STYLE.matches(currentLine)) {

		if (mode == ParserModes.READ_COMMERCIAL_EXAMPLES) {
		    String commercialExamples = buffer.readAll();
		    List<String> commercialExamplesList = parseCommercialExamples(commercialExamples);
		    currentStyle.setCommercialExamples(commercialExamplesList);
		}
		
		if (mode == ParserModes.READ_CATEGORY_DESCRIPTION) {
		    String categoryDescription = buffer.readAll();
		    currentCategory.setDescription(categoryDescription);
		}

		currentStyle = new Style();
		currentStyle.setName(currentLine);
		currentStyle.setCategory(currentCategory);
		currentCategory.addStyle(currentStyle);

		buffer.clearBuffer();
		mode = ParserModes.READ_STYLE_DESCRIPTION;
	    } else if (AROMA.matches(currentLine)) {

		// Category with single style.
		if (currentStyle == null) {
		    currentStyle = new Style();
		    currentStyle.setName(currentCategory.getTitle());
		    currentStyle.setCategory(currentCategory);
		}

		if (mode == ParserModes.READ_STYLE_DESCRIPTION) {
		    String styleDescription = buffer.readAll();
		    currentStyle.setDescription(styleDescription);
		}
		mode = ParserModes.READ_AROMA;
		buffer.writeBuffer(currentLine);
	    } else if (APPEARANCE.matches(currentLine)) {
		if (mode == ParserModes.READ_AROMA) {
		    String aroma = buffer.readAll();
		    currentStyle.setAroma(aroma);
		}
		mode = ParserModes.READ_APPEARANCE;
		buffer.writeBuffer(currentLine);
	    } else if (FLAVOR.matches(currentLine)) {
		if (mode == ParserModes.READ_APPEARANCE) {
		    String appearance = buffer.readAll();
		    currentStyle.setAppearance(appearance);
		}
		mode = ParserModes.READ_FLAVOR;
		buffer.writeBuffer(currentLine);
	    } else if (MOUTHFEEL.matches(currentLine)) {
		if (mode == ParserModes.READ_FLAVOR) {
		    String flavor = buffer.readAll();
		    currentStyle.setFlavor(flavor);
		}
		mode = ParserModes.READ_MOUTHFEEL;
		buffer.writeBuffer(currentLine);
	    } else if (OVERALL_IMPRESSION.matches(currentLine)) {
		if (mode == ParserModes.READ_MOUTHFEEL) {
		    String mouthfeel = buffer.readAll();
		    currentStyle.setMouthfeel(mouthfeel);
		}
		mode = ParserModes.READ_OVERALL_IMPRESSION;
		buffer.writeBuffer(currentLine);
	    } else if (HISTORY.matches(currentLine)) {
		if (mode == ParserModes.READ_OVERALL_IMPRESSION) {
		    String impression = buffer.readAll();
		    currentStyle.setImpression(impression);
		}
		mode = ParserModes.READ_HISTORY;
		buffer.writeBuffer(currentLine);
	    } else if (COMMENTS.matches(currentLine)) {
		if (mode == ParserModes.READ_OVERALL_IMPRESSION) {
		    String impression = buffer.readAll();
		    currentStyle.setImpression(impression);
		} else if (mode == ParserModes.READ_HISTORY) {
		    String history = buffer.readAll();
		    currentStyle.setHistory(history);
		}
		mode = ParserModes.READ_COMMENTS;
		buffer.writeBuffer(currentLine);
	    } else if (INGREDIENTS.matches(currentLine)) {
		if (mode == ParserModes.READ_COMMENTS) {
		    String comments = buffer.readAll();
		    currentStyle.setComments(comments);
		}
		mode = ParserModes.READ_INGREDIENTS;
		buffer.writeBuffer(currentLine);
	    } else if (VITAL_STATISTICS.matches(currentLine)) {
		if (mode == ParserModes.READ_INGREDIENTS) {
		    String ingredients = buffer.readAll();
		    currentStyle.setIngredients(ingredients);
		}
		mode = ParserModes.READ_VITAL_STATISTICS;
		buffer.writeBuffer(currentLine);
	    } else if (COMMERCIAL_EXAMPLES.matches(currentLine)) {
		if (mode == ParserModes.READ_VITAL_STATISTICS) {
		    String vitalStatistics = buffer.readAll();
		    StyleStatistics statistics = parseVitalStatistics(vitalStatistics);
		    currentStyle.setStatistics(statistics);
		}
		mode = ParserModes.READ_COMMERCIAL_EXAMPLES;
		buffer.writeBuffer(currentLine);
	    } else if (TABLE_TITLE_PATTERN.matcher(currentLine).matches()) {
		break;
	    } else {
		buffer.writeBuffer(currentLine);
	    }
	}

	for (StyleCategory category : categories) {
	    System.out.println(category);
	}

	lineScanner.close();
    }

    public static void main(String[] args) throws Exception {
	String fileName = "src/main/resources/2008_Guidelines.pdf";
	BjcpStyleGuideParser parser = new BjcpStyleGuideParser();
	parser.parse(fileName);
    }

    private StyleStatistics parseVitalStatistics(String vitalStatistics) {
	StyleStatistics statistics = new StyleStatistics();
	return statistics;
    }

    private List<String> parseCommercialExamples(String commercialExamples) {
	return Arrays.asList(commercialExamples.split(","));
    }
}
