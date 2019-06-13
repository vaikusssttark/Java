package svg_proj;

import java.util.HashMap;

public class Tag {
    private String name;
    private TagType tagType = null;
    private HashMap<String,String> prop = new HashMap<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag(String name, TagType tagType) {
        this.name = name;
        this.tagType = tagType;
    }

    public void set(String attrName, String attrVal) {
        prop.put(attrName, attrVal);
    }

    public HashMap<String, String> getProp() {
        return prop;
    }

    public String getName() {
        return name;
    }

    public TagType getTagType() {
        return tagType;
    }
}
