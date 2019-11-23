package engine.ui;

import engine.entity;
import engine.window;
import processing.core.PGraphics;

public class button extends UIelement {

	int col = w.color(0, 255, 0);
	Boolean pressed = false;

	public button(int x, int y, int sizex, int sizey, window w) {
		super(x, y, sizex, sizey, w, "button");
	}

	@Override
	public void update(window w) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(PGraphics b) {
		b.fill(col);
		b.rect(x, y, sizex, sizey);
		b.textAlign(w.CENTER,w.CENTER);
		b.fill(0);

		b.text(name,x+(sizex/2),y+(sizey/2));
		b.fill(255);

	}

	@Override
	public void click() {
		if (w.input.Mouse.left) {
			if (w.input.Mouse.X() > x && w.input.Mouse.X() < x + sizex) {
				if (w.input.Mouse.Y() > y && w.input.Mouse.Y() < y + sizey) {
					col = w.color(255, 0, 0);
				}
			}
		}else {
			col = w.color(0, 255, 0);
		}
	}

}
