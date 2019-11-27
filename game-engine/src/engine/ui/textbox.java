package engine.ui;

import engine.scene;
import engine.window;
import processing.core.PGraphics;

public class textbox extends UIelement {

	String text = "";
	boolean selected = false;
	int pointer = 0;

	public textbox(int x, int y, int sizex, int sizey, scene s, window w, String name) {
		super(x, y, sizex, sizey, s, w, name);
	}

	@Override
	void ValUpdate() {
		// TODO Auto-generated method stub

	}

	String getVal() {
		return text;
	}

	@Override
	public void draw(PGraphics b) {
		String text1 = text.substring(0, pointer);
		String text2 = text.substring(pointer, text.length());
		String Drawntext = text1 + '|' + text2;
		b.rect(x, y, sizex, sizey);
		b.fill(0);
		b.textAlign(w.LEFT, w.CENTER);
		b.text(Drawntext, x + (sizex / 10), y + (sizey / 2));
		b.fill(255);

	}

	@Override
	public void click() {
		if (w.input.Mouse.left) {
			if (w.input.Mouse.X() > x && w.input.Mouse.X() < x + sizex && w.input.Mouse.Y() > y
					&& w.input.Mouse.Y() < y + sizey) {

				selected = true;

			} else {
				selected = false;
			}
		}
	}

	@Override
	public void key() {
		if (selected) {
			int c = w.input.LastkeyCode;
			if (w.input.get(c)) {
				switch (w.input.LastkeyCode) {
				case 37:// left
					if (pointer > 0) {
						pointer--;
						return;
					}
					break;

				case 39:// right
					if (pointer < text.length()) {
						pointer++;
						return;
					}
					break;

				case 8:// backspace
					try {
						String text1 = text.substring(0, pointer);
						String text2 = text.substring(pointer, text.length());
						text1 = text1.substring(0, text1.length() - 1) + text2;
						text = text1;
						pointer--;
						ValUpdate();
					} catch (Exception e) {
						// just carry on
					}
					return;

				default:
					if (Character.isDefined(w.input.Lastkey)) {

						if (text.length() == pointer) {
							text += w.input.Lastkey;
						} else {
//						text += w.input.Lastkey;
							String text1 = text.substring(0, pointer);
							String text2 = text.substring(pointer, text.length());
							text1 += w.input.Lastkey + text2;
							text = text1;
						}
						pointer++;
						ValUpdate();
					}
				}
			}

		}
	}
}
