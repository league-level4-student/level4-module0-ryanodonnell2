package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	
	private Timer timer;
	
	//1. Create a 2D array of Cells. Do not initialize it.
	Cell cells[][];
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
	
		//2. Calculate the cell size.
		cellSize = h/cpr;
		//3. Initialize the cell array to the appropriate size.
		cells = new Cell[cellsPerRow][cellsPerRow];
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		for (int i = 0; i < cellsPerRow-1; i++) {
			for (int j = 0; j < cellsPerRow-1; j++) {
				cells[i][j] = new Cell(i, j, cellSize);
			}
		}
	}
	
	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive memeber to true of false
		Random r = new Random();
		for (int i = 0; i < cellsPerRow-1; i++) {
			for (int j = 0; j < cellsPerRow-1; j++) {
				cells[i][j].isAlive = r.nextBoolean();
			}
		}
		repaint();
	}
	
	public void clearCells() {
		//5. Iterate through the cells and set them all to dead.
		for (int i = 0; i < cellsPerRow-1; i++) {
			for (int j = 0; j < cellsPerRow-1; j++) {
				cells[i][j].isAlive = false;
			}
		}
		repaint();
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
		for (int i = 0; i < cellsPerRow-1; i++) {
			for (int j = 0; j < cellsPerRow-1; j++) {
				cells[i][j].draw(g);
			}
		}
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	
	//advances world one step
	public void step() {
		//7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		
		//8. check if each cell should live or die
		for (int i = 0; i < cellsPerRow-1; i++) {
			for (int j = 0; j < cellsPerRow-1; j++) {
				cells[i][j].liveOrDie(getLivingNeighbors(i,j));
			}
		}
		
		
		
		repaint();
	}
	
	//9. Complete the method.
	//   It returns an int of 8 or less based on how many
	//   living neighbors there are of the 
	//   cell identified by x and y
	public int getLivingNeighbors(int x, int y){
		int living = 0;
		if(x>1&&cells[x-1][y].isAlive) {
			living++;
		}
		if(y<cellsPerRow-2&&cells[x][y+1].isAlive) {
			living++;
		}
		if(x<cellsPerRow-2&&cells[x+1][y].isAlive) {
			living++;
		}
		if(y>1&&cells[x][y-1].isAlive) {
			living++;
		}
		if(x>0&&y>0&&cells[x-1][y-1].isAlive) {
			living++;
		}
		if(x<cellsPerRow-2&&y>0&&cells[x+1][y-1].isAlive) {
			living++;
		}
		if(y<cellsPerRow-2&&x>0&&y>1&&cells[x-1][y+1].isAlive) {
			living++;
		}
		if(x<cellsPerRow-2&&y<cellsPerRow-2&&cells[x+1][y+1].isAlive) {
			living++;
		}
		System.out.println(living);
		return living;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		if(cells[e.getX()/cellSize][e.getY()/cellSize].isAlive) {
			cells[e.getX()/cellSize][e.getY()/cellSize].isAlive = false;
		}
		else {
			cells[e.getX()/cellSize][e.getY()/cellSize].isAlive = true;
		}
		
		
		
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}
