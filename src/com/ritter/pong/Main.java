package com.ritter.pong;

import javax.swing.JFrame;

public class Main {

	public static JFrame frame;

	public static void main(String[] args) {
		Game game = new Game();

		frame = new JFrame("Pong");
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);

		game.start();
	}

}