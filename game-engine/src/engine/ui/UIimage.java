package engine.ui;

import engine.scene;
import engine.window;
import engine.sprite.sprite;
import processing.core.PGraphics;

public class UIimage extends UIelement {
	sprite image;

	public UIimage(int x, int y, int sizex, int sizey, scene s, window w, String name) {
		super(x, y, sizex, sizey, s, w, name);
	}

	public static UIimage NewUIimage(int x, int y, String image, scene s, window w, String name) {
		sprite display = new sprite(image, w);

		UIimage ret = new UIimage(x, y, display.sizex, display.sizey, s, w, name);

		ret.image = display;
		return ret;
	}

	@Override
	public void ValUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void key() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(PGraphics b) {
		b.image(image.frame(), x, y);
	}

}
