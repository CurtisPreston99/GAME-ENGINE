package engine.ui;

import engine.scene;
import engine.window;
import processing.core.PGraphics;

public class toggle extends UIelement {
	Boolean toggle = false;
	Boolean can = true;

	public toggle(int x, int y, int sizex, int sizey, scene s, window w, String name) {
		super(x, y, sizex, sizey, s, w, name);
	}

	public Boolean getVal() {
		return toggle;
	}

	@Override
	public void draw(PGraphics b) {
		if (toggle) {

		}
		b.fill(w.color(0, 0, 255));
		b.rect(x, y, sizex, sizey);
		b.fill(w.color(255));

		if (toggle) {
			b.rect(x + (sizex / 10), y + (sizey / 10), sizex / 2 - (sizex / 10), sizey - ((sizey / 10) * 2));
		} else {
			b.rect(x + (sizex / 2), y + (sizey / 10), sizex / 2 - ((sizex / 10)), sizey - ((sizey / 10) * 2));

		}
		b.fill(255);

	}

	@Override
	public void click() {
		if (w.input.Mouse.left && can) {
			if (w.input.Mouse.X() > x && w.input.Mouse.X() < x + sizex) {
				if (w.input.Mouse.Y() > y && w.input.Mouse.Y() < y + sizey) {
					toggle = !toggle;
					can = false;
					ValUpdate();
				}
			}
		} else {
			if (!w.input.Mouse.left) {
				can = true;
			}
		}
	}

	@Override
	public void ValUpdate() {
		// TODO Auto-generated method stub
		
	}
}
