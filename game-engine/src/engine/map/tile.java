package engine.map;

import engine.window;
import processing.core.PImage;

public class tile {
	PImage TileImage;
	String filePath;

	public tile(String f, window w) {
		filePath = f;
		TileImage = w.Loader.loadImage(f);
	}

	@Override
	public boolean equals(Object obj) {
		tile t = (tile) obj;
		return t.filePath == this.filePath;
	}

	@Override
	public String toString() {
		return filePath;
	}

	public PImage getTile() {
		return TileImage;
	}

}
