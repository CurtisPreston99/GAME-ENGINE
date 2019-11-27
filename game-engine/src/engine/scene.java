package engine;

import java.util.HashMap;

import processing.core.PGraphics;

public class scene {
	window Parrent;
	String id = "";

	public HashMap<String, entity> UIentities = new HashMap<>();

	public scene(window Parrent, String id) {
		this.Parrent = Parrent;
		this.id = id;
	}

	public void addEntity(entity e, String name) {
		UIentities.put(name, e);
	}

	public entity getEntity(String name) {
		return UIentities.get(name);
	}

	public void update() {
		for (entity e : UIentities.values()) {
			e.update(Parrent);
		}
		if (Parrent.mousePressed) {
			click();
		}
	}

	public void key() {
		for (entity e : UIentities.values()) {
			e.key();
		}
	}

	public void click() {
		for (entity e : UIentities.values()) {
			e.click();
		}
	}

	public void draw(PGraphics frame) {
		for (entity e : UIentities.values()) {
			e.draw(frame);
		}
	}

}
