package engine.entity;

import java.util.HashMap;

import javax.print.attribute.HashAttributeSet;

import engine.scene;
import engine.window;
import processing.core.PGraphics;

public abstract class entity {
	public int x, y, sizex, sizey;
	public String name;
	protected HashMap<String, String> atributes = new HashMap<String, String>();
	public window w;
	public scene s;

	public void setAtrribute(final String name, final String val) {
		atributes.put(name, val);
	}

	public String getAtrribute(final String name) {
		return atributes.get(name).toString();
	}

	public entity(final int x, final int y, final int size, final scene s, final window w) {
		this(x, y, size, size, s, w, "unkown");
	}

	public entity(final int x, final int y, final int size, final scene s, final window w, final String name) {
		this(x, y, size, size, s, w, name);

	}

	public entity(final int x, final int y, final int sizex, final int sizey, final scene s, final window w,
			final String name) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.sizex = sizex;
		this.sizey = sizey;
		this.name = name;
		this.s=s;
	}

	public Boolean collide(final entity e) {

		Boolean test1 = (this.x < e.x + e.sizex && this.x + this.sizex > e.x && this.y < e.y + e.sizey
				&& this.y + this.sizey > e.y);

		return test1;
	}

	public abstract void update(window w);

	public abstract void draw(PGraphics b);

	public abstract void click();

	public abstract void key();

}
