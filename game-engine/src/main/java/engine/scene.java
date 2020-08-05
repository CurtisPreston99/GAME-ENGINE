package engine;

import java.util.HashMap;

import engine.entity.entity;
import engine.ui.UIelement;
import processing.core.PGraphics;

public class scene {
	public window Parrent;
	String id = "";
	int background;

	public HashMap<String, entity> entities = new HashMap<>();
	public HashMap<String, UIelement> UIentities = new HashMap<>();

	public scene(window Parrent, String id) {
		this.Parrent = Parrent;
		this.id = id;
		this.background = Parrent.color(255, 255, 255);
	}


	public void addEntity(entity e, String name) {
		entities.put(name, e);
	}

	public entity getEntity(String name) {
		return entities.get(name);
	}


	public void addUIEntity(UIelement e, String name) {
		UIentities.put(name, e);
	}

	public UIelement getUIEntity(String name) {
		return UIentities.get(name);
	}

	public void tick() {

	}

	public void update() {
		for (entity e : entities.values()) {
			e.update(Parrent);
		}
		for (UIelement e : UIentities.values()) {
			e.update(Parrent);
		}
		tick();
	}

	public void key() {
		for (entity e : UIentities.values()) {
			e.key();
		}
		for (entity e : entities.values()) {
			e.key();
		}
	}

	public void click() {
		for (entity e : entities.values()) {
			e.click();
		}
		for (entity e : UIentities.values()) {
			e.click();
		}
	}

	public void draw(PGraphics frame) {
		for (entity e : entities.values()) {
			e.draw(frame);
		}
		for (entity e : UIentities.values()) {
			e.draw(frame);
		}
	}

}
