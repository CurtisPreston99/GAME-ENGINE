package engine;

import processing.core.PGraphics;

public abstract class entity {
	public int x,y,sizex,sizey;
	public String name;
	public window w;
	public entity(int x,int y, int size,window w){
		this(x,y,size,size,w,"unkown");
	}
	public entity(int x,int y, int size,window w,String name){
		this(x,y,size,size,w,name);

	}
	
	public entity(int x,int y, int sizex,int sizey,window w,String name){
		this.w=w;
		this.x=x;
		this.y=y;
		this.sizex=sizex;
		this.sizey=sizey;
		this.name=name;
	}
	
	
	public abstract void update(window w);
	
	public abstract void draw(PGraphics b);
	public abstract void click();
	
}
