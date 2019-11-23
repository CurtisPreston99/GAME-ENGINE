package engine.sprite;

import engine.window;
import processing.core.PGraphics;
import processing.core.PImage;

public class sprite {
	public PImage rawSprite;
	public PGraphics[] frames;
	int framePointer = 0;
	int len = 0;
	int mode = 0;
	int bomdir = 1;
	window w;
	
	public sprite(window w) {
		this.w=w;
	}
	
	public sprite(String fname, int w, int h, window win) {
		rawSprite = win.Loader.loadImage(fname);

		int fra = rawSprite.width / w;
		len = fra;

		frames = new PGraphics[fra];

		for (int i = 0; i < fra; i++) {
			PGraphics f = win.createGraphics(w, h);

			f.beginDraw();
			f.image(rawSprite, -(i * w), 0);
			f.endDraw();

			frames[i] = f;
		}
	}

	public sprite(String fname, int w, window win) {
		this(fname,w, win.Loader.loadImage(fname).height,win);

	}

	void modeSelect(String mode) {
		if (mode == "loop") {
			this.mode = 0;
		} else {
			if (mode == "boomarang") {
				this.mode = 2;
			} else {
				if (mode == "bloop") {
					this.mode = 1;
				}
			}
		}
	}

	public sprite(String fname, int w, String mode, window win) {
		this(fname, w, win);
		modeSelect(mode);
	}

	public sprite(String fname, int w, int h, String mode, window win) {
		this(fname, w, h, win);
		modeSelect(mode);
	}

	public void Nframe() {
		switch (mode) {
		case 0:
			framePointer++;
			if (framePointer >= len) {
				framePointer = 0;
			}
			break;
		case 1:
			framePointer--;
			if (framePointer < 0) {
				framePointer = len;
			}

		case 2:
			framePointer += bomdir;
			if (framePointer <= 0) {
				bomdir = 1;
			}
			if (framePointer == len-1) {
				bomdir = -1;
			}
			break;
		default:
			break;
		}
	}

	public void next() {

		framePointer++;
		if (framePointer >= len) {
			framePointer = 0;
		}
	}

	public void prev() {
		framePointer--;
		if (framePointer <= 0) {
			framePointer = len - 1;
		}
	}

	public PGraphics frame() {
		return frames[framePointer];
	}

}
