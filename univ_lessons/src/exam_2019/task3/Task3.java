

//import TurtleGraphics.Pen;
//import TurtleGraphics.StandardPen;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class Task3 {
//    public static void main(String[] args) {
//        Pen turtle = new StandardPen();
//        turtle.up();
//        turtle.move(-50, -200);
//        turtle.setDirection(90);
//        turtle.down();
//        try {
//            String sb = Files.readString(Paths.get("task1.txt"));
//            for (int i = 0; i < sb.length(); i++) {
//                switch (sb.charAt(i)) {
//                    case 'F':
//                        turtle.move(0.007);
//                        break;
//                    case '+':
//                        turtle.turn(300);
//                        break;
//                    case '-':
//                        turtle.turn(60);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
