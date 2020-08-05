package engine.ui;

import engine.scene;
import engine.window;
import processing.core.PGraphics;
import processing.core.PImage;

public class imageButton extends button {
    PImage p;
    UItheme theme;
    public Boolean pressed = false;


    public imageButton(int x, int y,PImage p, scene s, window w, String name) {
        this(x, y, p.width, p.height, s, w, name);
        this.p=p;
    }

    // public imageButton(int x, int y,int maxSize,PImage p, scene s, window w, String name) {
    //     this(x, y, p.height, p.width, s, w, name);
    //     this.p=p;
    // }

    public imageButton(int x, int y,PImage p, int size, scene s, window w, String name) {
        this(x, y, size, size, s, w, name);
        this.p=p;
    }

    public imageButton(int x, int y, int sizex, int sizey, scene s, window w, String name) {
        super(x, y, sizex, sizey, s, w);
        theme=UItheme.Singleton();
    }



	@Override
	public void draw(PGraphics b) {
        if(hover()){
            b.image(p,x,y);
            b.fill(theme.c_hover,100);
            b.rect(x,y,sizex,sizey);
        }else{
            b.image(p,x,y);
        }
        // b.stroke(5);
        b.strokeWeight(5);
        b.stroke(theme.c_mid);
        b.fill(0,0,0,0);
        b.rect(x,y,sizex,sizey);
        b.stroke(0);
	}
    
}