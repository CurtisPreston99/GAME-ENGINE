package game;

import engine.scene;
import engine.window;
import engine.entity.entity;
import processing.core.PGraphics;

public class colbox extends entity {

	public colbox(int x, int y, int size, scene s, window w) {
		super(x, y, size, s, w);
	}

	public colbox(int x, int y, int size, scene s, window w, String name) {
		super(x, y, size, s, w, name);
	}

	public colbox(int x, int y, int sizex, int sizey, scene s, window w, String name) {
		super(x, y, sizex, sizey, s, w, name);
	}

	@Override
	public void update(window w) {

	}

	@Override
	public void draw(PGraphics b) {
		b.rect(x, y, sizex, sizey);
	}

	@Override
	public void click() {

	}

	@Override
	public void key() {

	}

}
