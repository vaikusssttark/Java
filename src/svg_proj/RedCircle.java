package svg_proj;

import java.util.Collections;
import java.util.List;

public class RedCircle implements Shape {
    @Override
    public List<Tag> getTags() {
        Tag circle = new Tag("circle");
        circle.set("cx", "0");
        circle.set("cy", "0");
        circle.set("r", "20");
        circle.set("style", "stroke:#000000; fill: #ff0000");
        return Collections.singletonList(circle);
    }
}
