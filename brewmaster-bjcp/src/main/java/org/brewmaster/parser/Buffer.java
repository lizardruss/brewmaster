package org.brewmaster.parser;

import java.util.LinkedList;
import java.util.List;

public class Buffer {
    private List<String> lines;

    public Buffer() {
        super();
        lines = new LinkedList<String>();
    }

    public void clearBuffer() {
        lines.clear();
    }

    public String readBuffer() {
        return lines.remove(0);
    }

    public String readAll() {
        StringBuffer string = new StringBuffer();
        for (String line : lines) {
            string.append(line);
        }
        lines.clear();
        return string.toString();
    }

    public void writeBuffer(String line) {
        lines.add(line);
    }
}
