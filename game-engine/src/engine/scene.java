package engine;

import java.util.ArrayList;
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

	public void update() {
		for (entity e : UIentities.values()) {
			e.update(Parrent);
		}
		if(Parrent.mousePressed) {
			click();
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
