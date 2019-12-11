package engine.ui;

import engine.scene;
import engine.window;
import engine.entity.entity;
import processing.core.PGraphics;

public abstract class UIelement extends entity {

	public UIelement(int x, int y, int sizex, int sizey, scene s, window w, String name) {
		super(x, y, sizex, sizey, s, w, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(window w) {
		// TODO Auto-generated method stub
	}

	abstract void ValUpdate();

	@Override
	public void draw(PGraphics b) {
		b.rect(x, y, sizex, sizey);
	}

	@Override
	public void click() {
	}

}
