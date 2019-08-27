package brickbreak;

import javax.swing.JFrame;

public class BrickBreakMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Breaker breaker = new Breaker();
		frame.setBounds(10, 10, 700, 600);
		frame.setTitle("Break 'Em All");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(breaker);
	}

}
