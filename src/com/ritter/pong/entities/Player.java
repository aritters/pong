package com.ritter.pong.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.ritter.pong.Game;

public class Player {

	public boolean left, right;
	private int x, y, width, height;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 10;
	}

	public void tick() {
		if (right) {
			x++;
		} else if (left) {
			x--;
		}

		if (x + width > Game.WIDTH) {
			x = Game.WIDTH - width;
		} else if (x < 0) {
			x = 0;
		}
	}

	public void render(Graphics grafics) {
		grafics.setColor(Color.blue);
		grafics.fillRect(x, y, width, height);
	}

}
