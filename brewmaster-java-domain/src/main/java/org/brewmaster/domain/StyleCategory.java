package org.brewmaster.domain;

import javax.persistence.Id;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class StyleCategory {

    private Map<String, Style> styles;

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Style> getStyles() {
        return styles.values();
    }

    public StyleCategory() {
        super();
        styles = new LinkedHashMap<String, Style>();
    }

    public void addStyle(Style style) {
        styles.put(style.getName(), style);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("StyleCategory");
        buffer.append("\n");
        buffer.append("[");
        buffer.append("\n");
        buffer.append("\ttitle=" + title);
        buffer.append("\n");
        buffer.append("\tdescription=" + description);
        buffer.append("\n");
        buffer.append("\tstyles=");
        buffer.append("[");
        buffer.append("\n");
        for (Style style : getStyles()) {
            buffer.append("\t\t" + style);
            buffer.append("\n");
        }
        buffer.append("\t]");
        buffer.append("\n");
        buffer.append("]");
        return buffer.toString();
    }
}
