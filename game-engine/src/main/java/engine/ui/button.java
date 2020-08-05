package engine.ui;

import java.util.HashMap;

import engine.scene;
import engine.window;
import engine.input.mouse;
import processing.core.PConstants;
import processing.core.PGraphics;

public class button extends UIelement {
	
	public Boolean pressed = false;
	public UItheme colors;

	public button(int x, int y, int sizex, int sizey, scene s, window w) {
		super(x, y, sizex, sizey, s, w, "");
		colors=window.getUItheme();
	}

	public button(int x, int y, int sizex, int sizey, String Name, scene s, window w) {
		super(x, y, sizex, sizey, s, w, Name);
		colors=window.getUItheme();
	}

	public Boolean getVal() {
		return pressed;
	}

	@Override
	public void ValUpdate() {

	}

	@Override
	public void update(window w) {

		// hover= w.mouseX >= x && w.mouseX <= x+sizex && 
		// w.mouseY >= y && w.mouseY <= y+sizey;

	}

	@Override
	public void draw(PGraphics b) {


		if (hover()) {
			b.fill(colors.c_hover);
			b.rect(x, y, sizex, sizey);
			b.fill(colors.c_text_color);
			b.textSize(15);
			b.textAlign(b.CENTER, b.CENTER);
			b.text(name, x, y,  sizex, sizey);
			if (pressed) {
			b.fill(colors.c_light);
			b.rect(x, y,  sizex, sizey);
			b.text(name, x, y,  sizex, sizey);
			}
		} else {
			b.fill(colors.c_light);
			b.rect(x, y,  sizex, sizey);
			b.fill(colors.c_text_color);
			b.textSize(15);
			b.textAlign(b.CENTER, b.CENTER);
			b.text(name, x, y,  sizex, sizey);
		}

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
