package engine;

import processing.core.PGraphics;

public abstract class entity {
	public int x, y, sizex, sizey;
	public String name;
	public window w;

	public entity(int x, int y, int size, scene s, window w) {
		this(x, y, size, size, s, w, "unkown");
	}

	public entity(int x, int y, int size, scene s, window w, String name) {
		this(x, y, size, size, s, w, name);

	}

	public entity(int x, int y, int sizex, int sizey, scene s, window w, String name) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.sizex = sizex;
		this.sizey = sizey;
		this.name = name;
	}

	public Boolean collide(entity e) {
		Boolean col = false;

		Boolean test1 = (this.x < e.x + e.sizex && this.x + this.sizex > e.x && this.y < e.y + e.sizey
				&& this.y + this.sizey > e.y);

		System.out.println(test1);

		return col;
	}

	public abstract void update(window w);

	public abstract void draw(PGraphics b);

	public abstract void click();

	public abstract void key();

}
