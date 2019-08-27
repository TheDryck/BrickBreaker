package brickbreak;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;


import javax.swing.JPanel;

public class Breaker extends JPanel implements KeyListener, ActionListener{
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	private Timer time;
	private int delay = 8;
	
	private int sliderStart = 310;
	private int ballposX = 120;
	private int ballposY = 350;
	private int balldirX = -1;
	private int balldirY = -2;
	
	private MapGen map;
	
	public Breaker() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		map = new MapGen(3, 7);
		
		time = new Timer(delay,this);
		time.start();
	}
	
	public void paint(Graphics g) {
		//Background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//Borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//Slider
		g.setColor(Color.green);
		g.fillRect(sliderStart, 550, 100, 8);
		
		//Ball
		g.setColor(Color.cyan);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		//Bricks
		map.draw((Graphics2D)g);
		
		//Score
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+ score, 590, 30);
		
		if(ballposY > 570) {
			play = false;
			balldirX = 0;
			balldirY = 0;
			
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 35));
			g.drawString("Game Over: Fatality!!", 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to restart", 230, 350);
		}
		
		if(totalBricks <= 0) {
			play = false;
			balldirX = 0;
			balldirY = 0;
			
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 35));
			g.drawString("Victory!!", 260, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		time.start();
		
		if(play == true) {
			if(new Rectangle(ballposX,ballposY, 20, 20).intersects(new Rectangle(sliderStart, 550, 100, 8))) {
				balldirY = -balldirY;
			}
			
			A: for(int i = 0; i<map.map.length; i++) {
				for(int j = 0; j<map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j*map.brickWidth + 80;
						int brickY = i*map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rectangle = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRectangle = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRectangle = rectangle;
						
						if(ballRectangle.intersects(brickRectangle)) {
							map.setBrickVal(0, i, j);
							totalBricks--;
							score += 5;
							
							if(ballposX + 19 <= brickRectangle.x || ballposX + 1 >= brickRectangle.x + brickRectangle.width) {
								balldirX = -balldirX;
							}else {
								balldirY = -balldirY;
							}
							
							break A;
						}
					}
				}
			}
			
			ballposX += balldirX;
			ballposY += balldirY;
			
			if(ballposY < 0) {
				balldirY = -balldirY;
			}
			if(ballposX < 0) {
				balldirX = -balldirX;
			}
			if(ballposX > 670) {
				balldirX = -balldirX;
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(sliderStart >= 600) {
				sliderStart = 600;
			}else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(sliderStart <= 10) {
				sliderStart = 10;
			}else {
				moveLeft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				balldirX = -1;
				balldirY = -2;
				sliderStart = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGen(3, 7);
				
				repaint();
			}
		}
	}
	
	public void moveRight() {
		play = true;
		sliderStart += 20;
	}

	public void moveLeft() {
		play = true;
		sliderStart -= 20;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
