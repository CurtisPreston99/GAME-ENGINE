package engine.entity;

import engine.scene;
import engine.window;
import engine.sprite.sprite;
import processing.core.PGraphics;

public abstract class spriteEntity extends entity {
	sprite s;

	public spriteEntity(int x, int y, sprite draw, scene s, window w, String name) {
		super(x, y, draw.sizex, draw.sizey, s, w, name);
	}

	@Override
	public void draw(PGraphics b) {
		b.image(s.frame(), x, y);
	}

}
