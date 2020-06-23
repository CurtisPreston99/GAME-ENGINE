package engine.editor;

import engine.scene;
import engine.window;
import engine.map.tile;
import engine.ui.button;
import processing.core.PConstants;
import processing.core.PGraphics;

public class tileSelector extends button {
	tile[] tiles;
	int selected = 0;

	public tileSelector(int x, int y, int sizex, int sizey, scene s, window w) {
		super(x, y, sizex, sizey, s, w);
	}

	public tileSelector(int x, int y, int sizex, int sizey, String Name, scene s, window w) {
		super(x, y, sizex, sizey, Name, s, w);
	}

	public tileSelector(int x, int y, int sizex, int sizey, int idle, int selected, scene s, window w) {
		super(x, y, sizex, sizey, s, w);
	}

	public tileSelector(int x, int y, tile[] tiles, scene s, window w, String name) {
		this(x, y, tiles[0].getTile().width, tiles[0].getTile().height * tiles.length, s, w);
		this.name = name;
		this.tiles = tiles;
	}

	@Override
	public void draw(PGraphics b) {
		if (pressed) {
			b.fill(colors.c_hover);
		}
		b.rect(x, y, sizex, sizey);
		b.textAlign(PConstants.CENTER, PConstants.CENTER);
		b.fill(0);
		for (int i = 0; i < tiles.length; i++) {
			b.image(tiles[i].getTile(), x, y + (i * tiles[i].getTile().height));
			if (i == selected) {
				b.fill(255.0f, 0f, 0f, 150f);
				b.rect(x, y + (i * tiles[i].getTile().height), tiles[i].getTile().height, tiles[i].getTile().height);
			}
		}
		b.fill(255);
	}

	@Override
	public void click() {
		if (w.input.Mouse.left) {
			if (w.input.Mouse.X() > x && w.input.Mouse.X() < x + sizex) {
				if (w.input.Mouse.Y() > y && w.input.Mouse.Y() < y + sizey) {
					for (int i = 0; i < tiles.length; i++) {
//						if(w.input.Mouse.Y() - y )
						int top = i * tiles[0].getTile().height;
						int bottom = (i + 1) * tiles[0].getTile().height;
						if (w.input.Mouse.Y() > top && w.input.Mouse.Y() < bottom) {
							System.out.println(i);
							selected = i;
							break;
						}

					}
				}
			}
		} else {
		}
	}

}
