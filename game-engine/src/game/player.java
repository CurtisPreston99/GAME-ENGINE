package game;

import engine.entity;
import engine.scene;
import engine.window;
import engine.sprite.sprite;
import engine.sprite.spriteGroup;
import processing.core.PGraphics;

public class player extends entity {
	sprite sprite;

	public player(int x, int y, int size, scene s, window w) {
		super(x, y, size, s, w, "player");
		sprite[] sprites = { new sprite("images/spriteGroupTest/bodyUP.png", 64, w),
				new sprite("images/spriteGroupTest/tshitup.png", 64, w),
				new sprite("images/spriteGroupTest/robeLegsUP.png", 64, w) };
		int[][] grouppos = { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } };
		int[] spd = { 4, 4, 4, 4 };

//		sprite= new spriteGroup(sprites,grouppos,  spd, 64, 64, w);
		try {
			sprite = spriteGroup.JSONload(w.Loader.loadJSON("grouptest"), w);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sizex = sprite.sizex;
		sizey = sprite.sizey;

		System.out.print(sprite);
	}

	@Override
	public void update(window w) {

		collide(w.getSelectedScene().getEntity("col"));

		int speed = 1;

		if (w.input.get("shift")) {
			speed = 10;
		}
		if (w.input.get('W')) {
			y = y - speed;
		}
		if (w.input.get('S')) {
			y = y + speed;
		}
		if (w.input.get('A')) {
			x = x - speed;
		}
		if (w.input.get('D')) {
			x = x + speed;
		}
	}

	@Override
	public void draw(PGraphics b) {

		b.rect(x, y, sizex, sizey);

		b.image(sprite.frame(), x, y);
		sprite.Nframe();
//		w.print("frame");
	}

	@Override
	public void click() {

	}

	@Override
	public void key() {

	}

}
