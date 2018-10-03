package maze.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotImpl implements Robot {

	String mazeMAtrix[][] = {{"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
							 {"X"," "," "," "," "," "," "," "," "," "," "," "," "," ","X"},
	                         {"X"," ","X","X","X","X","X","X","X","X","X","X","X"," ","X"},
	                         {"X"," ","X","S"," "," "," "," "," "," "," "," ","X"," ","X"},
	                         {"X"," ","X","X","X","X","X","X","X","X","X"," ","X"," ","X"},
	                         {"X"," ","X","X","X","X","X","X","X","X","X"," ","X"," ","X"},
	                         {"X"," ","X","X","X","X"," "," "," "," "," "," ","X"," ","X"},
	                         {"X"," ","X","X","X","X"," ","X","X","X","X"," ","X"," ","X"},
	                         {"X"," ","X","X","X","X"," ","X","X","X","X"," ","X"," ","X"},
	                         {"X"," ","X"," "," "," "," ","X","X","X","X","X","X"," ","X"},
	                         {"X"," ","X"," ","X","X","X","X","X","X","X","X","X"," ","X"},
	                         {"X"," ","X"," ","X","X","X","X","X","X","X","X","X"," ","X"},
	                         {"X"," ","X"," "," "," "," "," "," "," "," "," ","X"," ","X"},
	                         {"X"," ","X","X","X","X","X","X","X","X","X"," "," "," ","X"},
	                         {"X","F","X","X","X","X","X","X","X","X","X","X","X","X","X"}};
	Integer movementCounter = new Integer(0);

	private int x;
	private int y;
	private Directions lastDirection = Directions.STATIC;
	private int xCont = 0;

	public RobotImpl(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * This method looks for Routes to go.
	 */

	public void walk2(int xx, int yy) {
		
		this.x = xx;
		this.y = yy;
		Directions newDirection = Directions.STATIC;

		while(true){
			
			//Robot please find possible routes to move
			
			List<Directions> routes = exploreRobot(x, y,lastDirection);			
			int xCP = 0;
			int yCP = 0;
			//consider the possible routes before take an action
			System.out.println("when in (" + x + "," + y + ") move " + routes.get(0));
			
			// find out if there are one or more routes
			if(routes.size()==1) move(routes.get(0),x,y);
			else if((routes.size() == 2) && (xCP == 0) && (yCP == 0)) {
				System.out.println("A coordinates with 2 ways was found at: (" + x + "," + y +")");
				
				xCP = x; // X coordinate of cross point
				yCP = y; // Y coordinate of cross point
				
				// try 1st Route
				System.out.println("try first route in cross point (" + xCP + "," + yCP + ")");
				newDirection = routes.get(0);
				
				while(true) {
					xCont = 0;
					if(move(newDirection,x,y)) {
						System.out.println("when in (" + x + "," + y +") move " + newDirection);
					}
					
					//verify if there is no escape!
					if( (xCont > 2) || (mazeMAtrix[x][y].equals("F"))) {
						// set direction to opposite
						System.out.println("wall found,switch to opposite direction");
						switch(lastDirection) {
						case UP:
							lastDirection = Directions.DOWN;
							newDirection = Directions.UP;
							break;
						case DOWN:
							lastDirection = Directions.UP;
							newDirection = Directions.DOWN;
							break;
						case RIGHT:
							lastDirection = Directions.LEFT;
							newDirection = Directions.RIGHT;
							break;
						case LEFT:
							lastDirection = Directions.RIGHT;
							newDirection = Directions.LEFT;
							break;
						default:
							break;
						}
					}
					if((x == xCP) && (y == yCP)) {
						break; // we haven gotten back to the cross point coordinate
					}
				}
				
				// then let's try the 2nd Route
				System.out.println("let's try the second route in cross point (" + xCP + "," + yCP + ")");
				xCP = x;
				yCP = y;
				newDirection = routes.get(1); // get second route from list of routes
				while(true) {
					xCont = 0;
					if(move(newDirection,x,y)) {
						System.out.println("when in (" + x + "," + y + ") move to " + newDirection);
					}
					
					//verify if there is no escape!
					if( (xCont > 2) || (mazeMAtrix[x][y].equals("F"))) {						
						System.out.println("switch direction");
						switch(lastDirection) {
						case UP:
							lastDirection = Directions.DOWN;
							newDirection = Directions.UP;
							break;
						case DOWN:
							lastDirection = Directions.UP;
							newDirection = Directions.DOWN;
							break;
						case RIGHT:
							lastDirection = Directions.LEFT;
							newDirection = Directions.RIGHT;
							break;
						case LEFT:
							lastDirection = Directions.RIGHT;
							newDirection = Directions.LEFT;
							break;
						default:
							break;
						}
					}
					
					// If there is a "X" in front of you then break out
					if((newDirection.equals(Directions.LEFT)) && (mazeMAtrix[x][y-1]).equals("X")) { 
						break;
					}
					if((newDirection.equals(Directions.RIGHT)) && (mazeMAtrix[x][y+1]).equals("X")) {  
						break;
					}
					if((newDirection.equals(Directions.UP)) && (mazeMAtrix[x-1][y]).equals("X")) {  
						break;
					}
					if((newDirection.equals(Directions.DOWN)) && (mazeMAtrix[x+1][y]).equals("X")) {  
						break;
					}
					
					
				}
			}	
			
			// verify in the 4 directions, if "F" is the next character. If it does then you have found the Exit of maze
			if((mazeMAtrix[x][y+1].equals("F")) || (mazeMAtrix[x][y-1].equals("F")) || (mazeMAtrix[x+1][y].equals("F")) || (mazeMAtrix[x-1][y].equals("F"))){
				System.out.println("when in (" + x + "," + y + ") move to " + routes.get(0)) ;
				switch(routes.get(0)) {
				case DOWN:
					x++;
					break;
				case UP:
					x--;
					break;
				case RIGHT:
					y++;
					break;
				case LEFT:
					y--;
					break;
				default:
					break;
				}
				System.out.println("'F' found at (" + x + "," + y + ")");
				break;
			}					
		}
	}
			
	/** This method explores the possible routes around it
	 *  and creates a List with all the routes found
	 * @param x
	 * @param y
	 * @return
	 */
	
	public List<Directions> exploreRobot(int x,int y, Directions lastDirection) {
		List<Directions> paths = new ArrayList<Directions>();
		
		if(mazeMAtrix[x-1][y].equals(" ") && !Directions.UP.equals(lastDirection)) {
			paths.add(Directions.UP);
		}
		if(mazeMAtrix[x+1][y].equals(" ") && !Directions.DOWN.equals(lastDirection)) {
			paths.add(Directions.DOWN);
		}
		if(mazeMAtrix[x][y+1].equals(" ") && !Directions.RIGHT.equals(lastDirection)) {
			paths.add(Directions.RIGHT);
		}
		if(mazeMAtrix[x][y-1].equals(" ") && !Directions.LEFT.equals(lastDirection)) {
			paths.add(Directions.LEFT);
		}
		
		return paths;
	}
	
	/** This method receives the Direction to move as parameter
	 * it returns a boolean flag that indicates if it could move in the required Direction 
	 * @param direction
	 * @param x
	 * @param y
	 * @return
	 */
	public Boolean move(Directions direction,int x, int y) {
		Boolean result = false;
		this.xCont = 0;
		switch(direction) {
			case UP:
				if(mazeMAtrix[x-1][y].equals(" ")) {
					this.setX(--x);
					result = true;
					this.setLastDirection(Directions.DOWN); //the opposite
					break;
				} else {
					this.xCont++;
				}
			case DOWN:
				if(mazeMAtrix[x+1][y].equals(" ")) {
					this.setX(++x);
					result = true;
					this.setLastDirection(Directions.UP); //the opposite
					break;
				}else {
					this.xCont++;
				}
			case RIGHT:			
				if(mazeMAtrix[x][y+1].equals(" ")) {
					this.setY(++y);
					result = true;
					this.setLastDirection(Directions.LEFT); //the opposite
					break;
				}else {
					this.xCont++;
				}
			case LEFT:
				if(mazeMAtrix[x][y-1].equals(" ")) {
					this.setY(--y);
					result=true;
					this.setLastDirection(Directions.RIGHT); //the opposite
					break;
				}else {
					this.xCont++;
				}
		default:
			break;
		}
		return result;
	}
	protected Map<Integer, Directions> robotMovements = null;
		
	public RobotImpl() {
		robotMovements = new HashMap<Integer, Directions>();
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public Directions getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(Directions lastDirection) {
		this.lastDirection = lastDirection;
	}


	public int getxCont() {
		return xCont;
	}

	public void setxCont(int xCont) {
		this.xCont = xCont;
	}


/*	class Coordinates {
		private int x;
		private int y;
		
		public void setX(int x) {
			this.x = x;
		}
		
		public int getX() {
			return x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
		
		public int getY() {
			return y;
			
		}
	}
*/
}
