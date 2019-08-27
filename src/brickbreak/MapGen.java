package brickbreak;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGen {
	public int map[][];
	public int brickHeight;
	public int brickWidth;
	
	public MapGen(int row, int cols) {
		map= new int[row][cols];
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j<map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		
		brickWidth = 540/cols;
		brickHeight = 150/row;
	}
	
	public void draw(Graphics2D g) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j<map[0].length; j++) {
				if(map[i][j] > 0) {
					g.setColor(Color.white);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrickVal(int value, int row, int cols) {
		map[row][cols] = value;
	}
}
