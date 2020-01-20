package engine.ui;

import java.util.ArrayList;

import engine.scene;
import engine.window;
import engine.entity.entity;
import engine.map.map;
import processing.core.PGraphics;

public class camera extends UIelement {
	map map;
	int PosX = 0;
	int PosY = 0;

	float scale = 1;
	Boolean blur = false;
	ArrayList<entity> entityes = new ArrayList<>();

	public camera(int x, int y, int sizex, int sizey, map m, scene s, window w, String name) {
		super(x, y, sizex, sizey, s, w, name);
		map = m;
	}

	@Override
	void ValUpdate() {

	}

	@Override
	public void update(window w) {
		for (entity e : entityes) {
			e.update(w);
		}
	}

	@Override
	public void key() {

	}

	public void pos(int x, int y) {
		PosX = x;
		PosY = y;
	}

	public int[] MapTanslate(int x, int y) {
		int X = (x) / (map.tiles[0].getTile().width);
		int Y = (y) / (map.tiles[0].getTile().height);
		int[] r = { X, Y };
		return r;
	}

	public void SetScale(float m) {
		scale = m;
	}

	public map getMap() {
		return map;
	}

	@Override
	public void draw(PGraphics p) {
		PGraphics frame = w.createGraphics(sizex, sizey);
		if (!blur) {
			frame.noSmooth();
		} else {
			frame.smooth();
		}
		frame.beginDraw();

		int[][] mapArray = map.getmap();
		for (int i = 0; i < map.height; i++) {
			frame.line(i * map.tiles[0].getTile().height, 0, i * map.tiles[0].getTile().height, frame.width);
		}

		for (int i = 0; i < map.width; i++) {
			frame.line(0, i * map.tiles[0].getTile().height, frame.height, i * map.tiles[0].getTile().height);
		}


		
		for (int i = 0; i < mapArray.length; i++) {
			for (int e = 0; e < mapArray[i].length; e++) {
				if (mapArray[i][e] > -1) {
					frame.image(map.tiles[mapArray[i][e]].getTile(), e * map.tileWidth * scale,
							i * map.tileHeight * scale, map.tileWidth * scale, map.tileHeight * scale);
				}
			}
		}
		frame.endDraw();
		p.image(frame, x, y);

	}
}
