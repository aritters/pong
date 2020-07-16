package com.ritter.pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import com.ritter.pong.graphics.Spritesheet;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = 240;
	private final int HEIGHT = 120;
	private final int SCALE = 3;

	private Thread thread;
	private boolean isRunning;
	private BufferedImage image;

	private Spritesheet sheet;

	public static JFrame frame;

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		sheet = new Spritesheet("/spritesheet.png");
	}

	public synchronized void start() {
		this.isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		long lastTime = System.nanoTime();
		double ammountsOfTicks = 60.0;
		double ns = 1000000000 / ammountsOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();

		long now;

		while (isRunning) {
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta > 1) {
				tick();
				render();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}

		stop();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = image.getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		/* Game render */

		/****/

		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

		bs.show();
	}

	private void tick() {
	}

	private void initFrame() {
		frame = new JFrame("Pong");
		frame.add(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

}
