package engine;

import java.util.ArrayList;

import processing.core.PGraphics;

public class screen {
	window Parrent;
	String id="";
	
	public ArrayList<entity> entities = new ArrayList<>();
	public screen(window Parrent,String id){
		this.Parrent=Parrent;
		this.id = id;
	}
	
	public void addEntity(entity e) {
		entities.add(e);
	}
	
	public void update() {
		for(entity e : entities) {
			e.update(Parrent);
		}
	}
	
	public void draw(PGraphics frame) {
		for(entity e : entities) {
			e.draw(frame);
		}
	}
	
	
	
}
