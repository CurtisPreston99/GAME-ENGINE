package engine.ui;

import engine.scene;
import engine.window;
import processing.core.PGraphics;

public class button extends UIelement {

	int colmain = w.color(0, 255, 0);
	int colpressed = w.color(255, 0, 0);
	public Boolean pressed = false;

	public button(int x, int y, int sizex, int sizey, scene s, window w) {
		super(x, y, sizex, sizey, s, w, "button");
	}

	public button(int x, int y, int sizex, int sizey, String Name, scene s, window w) {
		super(x, y, sizex, sizey, s, w, Name);
	}

	public button(int x, int y, int sizex, int sizey, int idle, int selected, scene s, window w) {
		super(x, y, sizex, sizey, s, w, "button");
		colmain = idle;
		colpressed = selected;
	}

	public Boolean getVal() {
		return pressed;
	}

	@Override
	public void ValUpdate() {

	}

	@Override
	public void update(window w) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(PGraphics b) {
		if (pressed) {
			b.fill(colpressed);
		}
		b.rect(x, y, sizex, sizey);
		b.textAlign(w.CENTER, w.CENTER);
		b.fill(0);
		b.text(name, x + (sizex / 2), y + (sizey / 2));
		b.fill(255);

	}

	@Override
	public void click() {
		if (w.input.Mouse.left) {
			if (w.input.Mouse.X() > x && w.input.Mouse.X() < x + sizex) {
				if (w.input.Mouse.Y() > y && w.input.Mouse.Y() < y + sizey) {
					if (!pressed) {
						pressed = true;
						ValUpdate();
					}
				}
			}
		} else {
			if (pressed) {
				pressed = false;
				ValUpdate();
			}
		}
	}

	@Override
	public void key() {
		// TODO Auto-generated method stub

	}

}
