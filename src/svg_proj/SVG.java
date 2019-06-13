package svg_proj;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class SVG implements AutoCloseable {
    private PrintStream out;
    private StringBuilder indent = new StringBuilder("\t");

    public SVG(String fileName, int width, int height) throws FileNotFoundException {
        out = new PrintStream(fileName);
        out.println(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" " +
                "width=\"%d\" height=\"%d\">", width, height));
        setBackground(width, height);
    }

    private void setBackground(int width, int height) {
        Tag backgroundTag = new Tag("rect");
        backgroundTag.set("width", String.format("%d", width));
        backgroundTag.set("height", String.format("%d", height));
        backgroundTag.set("style", "fill:" + Settings.getInstance().getBackground());
        this.addTag(backgroundTag);
    }

    public void addTag(Tag tag) {
        String tagName = tag.getName();
        StringBuilder allTag = new StringBuilder();
        if (tag.getTagType() != TagType.CLOSE) {
            allTag.append(String.format("%s<%s ", indent, tagName));
            HashMap<String, String> tagProp = tag.getProp();


            for (Map.Entry<String, String> entry : tagProp.entrySet()) {
                allTag.append(String.format("%s=\"%s\" ", entry.getKey(), entry.getValue()));
            }

            if (tag.getTagType() == null || tag.getTagType() == TagType.OPEN_AND_CLOSE) {
                allTag.append("/>");
                out.println(allTag);
            }
            if (tag.getTagType() == TagType.OPEN) {
                allTag.append(">");
                out.println(allTag);
                indent.append("\t");
            }
        } else {
            indent.delete(0, 1);
            allTag.append(String.format("%s</%s>", indent, tagName));
            out.println(allTag);
        }
    }

    @Override
    public void close() {
        out.println("</svg>");
        out.close();
    }

}
