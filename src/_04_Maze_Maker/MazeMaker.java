package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	private static Random r;
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		r = new Random();
		int x = r.nextInt(w);
		int y = r.nextInt(h);
		Cell randomCell = maze.getCell(x, y);
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(randomCell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		currentCell.setBeenVisited(true);
		ArrayList<Cell> unvisted = getUnvisitedNeighbors(currentCell);
		if(unvisted.size()>0) {
			int pick = r.nextInt(unvisted.size()); 
			uncheckedCells.push(unvisted.get(pick));
			removeWalls(currentCell, unvisted.get(pick));
			currentCell=unvisted.get(pick);
			selectNextPath(currentCell);
		}
		else if(unvisted.size()<=0) {
			if(uncheckedCells.size()>0) {
				currentCell = uncheckedCells.pop();
				selectNextPath(currentCell);
			}
		}
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(((c1.getX()+1==c2.getX()||c1.getX()-1==c2.getX())&&c1.getY()==c2.getY())||((c1.getY()+1==c2.getY()||c1.getY()-1==c2.getY())&&c1.getX()==c2.getX())) {
			if(c1.getX()+1==c2.getX()) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
			if(c1.getX()-1==c2.getX()) {
				c1.setWestWall(false);
				c2.setEastWall(false);				
			}
			if(c1.getY()+1==c2.getY()) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
			if(c1.getY()-1==c2.getY()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
		}
		
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();
		if(c.getX()>0&&!maze.cells[c.getX()-1][c.getY()].hasBeenVisited()) {
			unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()]);
		}
		if(c.getX()<width-1&&!maze.cells[c.getX()+1][c.getY()].hasBeenVisited()) {
			unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()]);
		}
		if(c.getY()>0&&!maze.cells[c.getX()][c.getY()-1].hasBeenVisited()) {
			unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()-1]);
		}
		if(c.getY()<height-1&&!maze.cells[c.getX()][c.getY()+1].hasBeenVisited()) {
			unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()+1]);
		}
		return unvisitedNeighbors;
	}
}
