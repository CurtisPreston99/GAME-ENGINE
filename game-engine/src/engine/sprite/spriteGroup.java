package engine.sprite;

import java.util.ArrayList;

import engine.window;
import processing.core.PGraphics;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class spriteGroup extends sprite {
	int width, height;
	int[][] spritePos;
	int[] spriteSpeed;
	sprite[] spritelist;
	int frame = 0;

	public spriteGroup(sprite[] spritelist, int[][] pos, int[] spd, int width, int height, window w) {
		super(w);
		spritePos = pos;
		spriteSpeed = spd;
		this.spritelist = spritelist;
		this.width = width;
		this.height = height;

		this.sizex = width;
		this.sizey = height;
	}

	public static spriteGroup JSONload(JSONObject json, window w) {
		ArrayList<int[]> pos = new ArrayList<>();
		ArrayList<Integer> spd = new ArrayList<>();
		ArrayList<sprite> spritelist = new ArrayList<>();
		int width = json.getInt("width");
		int height = json.getInt("height");
		JSONArray spriteList = json.getJSONArray("sprites");
		int cuttoffX;
		int cuttoffY;

		for (int i = 0; i < spriteList.size(); i++) {
			JSONObject sprite = spriteList.getJSONObject(i);
			String fileloc = sprite.getString("sprite");
			try {
				cuttoffX = sprite.getInt("cutX");
			} catch (Exception e) {
				cuttoffX = -1;
			}
			try {
				cuttoffY = sprite.getInt("cutY");
			} catch (Exception e) {
				cuttoffY = -1;
			}

			int x = sprite.getInt("X");
			int y = sprite.getInt("Y");
			int speed = sprite.getInt("spd");

			sprite s = null;
			if (cuttoffX != -1 && cuttoffY != -1) {
				s = new sprite(fileloc, cuttoffX, cuttoffY, w);
			}
			if (cuttoffX != -1 && cuttoffY == -1) {
				s = new sprite(fileloc, cuttoffX, w);
			}
			if (cuttoffX == -1 && cuttoffY == -1) {
				s = new sprite(fileloc, w);
			}
			spritelist.add(s);
			pos.add(new int[] { x, y });
			spd.add(speed);
		}
		int[] spdarray = new int[spd.size()];
		sprite[] spritearray = new sprite[spritelist.size()];
		int[][] posarray = new int[spritelist.size()][2];

		for (int i = 0; i < spd.size(); i++) {
			spdarray[i] = spd.get(i);
			spritearray[i] = spritelist.get(i);
			posarray[i] = pos.get(i);
		}

		spriteGroup group = new spriteGroup(spritearray, posarray, spdarray, width, height, w);
		return group;
	}

	@Override
	public PGraphics frame() {
		PGraphics frame = w.createGraphics(width, height);
		frame.beginDraw();
		for (int i = 0; i < spritelist.length; i++) {
			frame.image(spritelist[i].frame(), spritePos[i][0], spritePos[i][1]);
		}
		frame.endDraw();

		return frame;
	}

	@Override
	public void Nframe() {
		frame++;
		for (int i = 0; i < spritelist.length; i++) {
			if (frame % spriteSpeed[i] == 0) {
				spritelist[i].Nframe();
			}
		}
	}

}
