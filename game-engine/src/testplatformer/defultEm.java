package testplatformer;

import engine.scene;
import engine.window;
import engine.entity.entity;
import processing.core.PGraphics;

public class defultEm extends entity {
    int size=64;
    int state=0;
    int MaxSpd=10;
    int acc=2;
    platformManager platforms;
    

    public defultEm(int x, int y,scene s, window w, String name, platformManager p) {
        super(x, y, 64, 64, s, w, name);
        platforms=p;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update(window w) {
        
    }

    @Override
    public void draw(PGraphics b) {
        b.fill(255,0,0);
        b.rect(x,y,size,size);
    }

    @Override
    public void click() {
        // TODO Auto-generated method stub

    }

    @Override
    public void key() {
        // TODO Auto-generated method stub

    }
    
}