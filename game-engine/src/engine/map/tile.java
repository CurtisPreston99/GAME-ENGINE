package engine.map;

import engine.window;
import processing.core.PImage;

public class tile {
	PImage TileImage;
	String filePath;
	int size;

	public tile(String f, window w) {
		filePath = f;
		TileImage = w.Loader.loadImage(f);
		size = TileImage.height;
	}

	@Override
	public String toString() {
		return filePath + "  " + String.valueOf(size);
	}

	public PImage getTile() {
		return TileImage;
	}

}
