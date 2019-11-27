package engine.ui;

import engine.scene;
import engine.window;
import processing.core.PGraphics;

public class slider extends UIelement {
	float maxVal, minVal;
	float slider = 0;
	int col1;
	int col2;

	public slider(int x, int y, int sizex, int sizey, float minVal, float maxVal, scene s, window w, String name) {
		super(x, y, sizex, sizey, s, w, name);
		this.maxVal = maxVal;
		this.minVal = minVal;
		this.col2 = w.color(0, 0, 150);
		this.col1 = w.color(0, 0, 250);
	}

	public slider(int x, int y, int sizex, int sizey, scene s, window w, String name) {
		this(x, y, sizex, sizey, 0, 1, s, w, name);
	}

	public float getVal() {
		if (slider == 0) {
			return 0;
		}
//		(slider / sizex)* (maxVal-minVal)+minVal

		Float value = (slider / sizex) * (maxVal - minVal) + minVal;

		return value;

//		return (float) 0.5;
	}

	@Override
	public void draw(PGraphics b) {

		b.fill(col1);
		b.rect(x, y, slider, sizey);
		b.fill(col2);
		b.rect(x + slider, y, sizex - (slider), sizey);
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
					if (slider != w.input.Mouse.X() - x) {
						slider = w.input.Mouse.X() - x;
						ValUpdate();
					}
				}
			}
		}
	}

	@Override
	public void ValUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void key() {
		// TODO Auto-generated method stub

	}

}
