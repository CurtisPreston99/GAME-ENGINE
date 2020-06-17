package engine.ui;

import java.util.HashMap;

import engine.scene;
import engine.window;
import processing.core.PConstants;
import processing.core.PGraphics;

public class textbox extends UIelement {

	public String text = "";
	String hint = "";
	boolean selected = false;
	int pointer = 0;
	public UItheme colors;

	public textbox(int x, int y, int sizex, int sizey, scene s, window w, String name) {
		super(x, y, sizex, sizey, s, w, name);
		colors = window.getUItheme();
	}

	public textbox(int x, int y, int sizex, int sizey, scene s, window w, String name, String hint) {
		this(x, y, sizex, sizey, s, w, name);
		this.hint = hint;
	}

	@Override
	void ValUpdate() {
		// TODO Auto-generated method stub

	}

	String getVal() {
		return text;
	}

	@Override
	public void update(window w) {
	}

	@Override
	public void draw(PGraphics b) {

		if (selected) {
			b.fill(colors.c_dark);
			b.rect(x, y, sizex, sizey);
			b.stroke(colors.c_light);
			b.noStroke();
			b.fill(colors.c_text_color);
			b.textSize(15);
			b.textAlign(b.CENTER, b.CENTER);
			b.text(text, x, y, sizex, sizey);

		} else if (hover()) {
			b.fill(colors.c_hover);
			b.rect(x, y, sizex, sizey);
			b.fill(colors.c_text_color);
			b.textSize(15);
			b.textAlign(b.CENTER, b.CENTER);
			b.text(text, x, y, sizex, sizey);
		} else {
			b.fill(colors.c_light);
			b.stroke(colors.c_dark);
			b.rect(x, y, sizex, sizey);
			b.fill(colors.c_text_color);
			b.textSize(15);
			b.textAlign(b.CENTER, b.CENTER);
			b.text(text, x, y, sizex, sizey);
		}
		if (text.length() == 0) {
			b.fill(150);
			b.textSize(15);
			b.textAlign(b.CENTER, b.CENTER);
			b.text(hint, x, y, sizex, sizey);
		}

	}

	@Override
	public void click() {
		if (w.input.Mouse.left) {
			if (hover()) {

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
								// text += w.input.Lastkey;
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
