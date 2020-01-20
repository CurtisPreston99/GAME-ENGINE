package engine;

import java.util.HashMap;

import engine.entity.entity;
import processing.core.PGraphics;

public class scene {
	public window Parrent;
	String id = "";
	int background;
	public HashMap<String, entity> UIentities = new HashMap<>();

	public scene(window Parrent, String id) {
		this.Parrent = Parrent;
		this.id = id;
		this.background = Parrent.color(255, 255, 255);
	}

	public void addUIEntity(entity e, String name) {
		UIentities.put(name, e);
	}

	public entity getUIEntity(String name) {
		return UIentities.get(name);
	}

	public void tick() {

	}

	public void update() {
		for (entity e : UIentities.values()) {
			e.update(Parrent);
		}
		if (Parrent.mousePressed) {
			click();
		}
		tick();
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
		frame.background(background);
		for (entity e : UIentities.values()) {
			e.draw(frame);
		}
	}

}
