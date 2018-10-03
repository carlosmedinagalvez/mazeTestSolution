import maze.objects.Robot;
import maze.objects.RobotImpl;

/**
 * Main Class
 */
public class AppMain {

    public static void main(String[] args) {
    	int initialXCoordinate = 3; // X coordinate of starting point
    	int initialYCoordinate = 3; // Y coordinate of starting point
        System.out.println("Hello Maze");
        Robot robot = new RobotImpl(initialXCoordinate,initialYCoordinate);
        robot.walk2(initialXCoordinate,initialYCoordinate); 
    }
}
