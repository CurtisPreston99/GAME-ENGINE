package engine;

import processing.core.PGraphics;

public abstract class entity {
	public int x,y,size;
	public String name;
	public entity(int x,int y, int size){
		this.x=x;
		this.y=y;
		this.size=size;
		name="unknown";
	}
	public entity(int x,int y, int size,String name){
		this(x,y,size);
		this.name=name;
	}
	
	
	public abstract void update(window w);
	
	void draw(PGraphics b) {
		b.strokeWeight(2);
		b.rect(x, y, size, size,2);
	};
}
