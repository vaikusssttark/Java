package svg_proj;

public class ShapeFactory {
    public Shape create(String shapeName) {
        try {
            Class<?> shapeClass = Class.forName(shapeName);
            Object shape = shapeClass.newInstance();
            return (Shape) shape;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Что-то не так с именем класса фигуры...");
            e.printStackTrace();
        }
        return null;
    }
}
