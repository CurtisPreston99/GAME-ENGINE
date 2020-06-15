package engine.ui;

import engine.scene;
import engine.window;
import processing.core.PGraphics;

public class card extends UIelement {
    private boolean dragable=false;
    private boolean title=true;
    
    public card(int x, int y, int sizex, int sizey, scene s, window w, String name) {
        super(x, y, sizex, sizey, s, w, name);
        // TODO Auto-generated constructor stub
    }

    @Override
    void ValUpdate() {
        // TODO Auto-generated method stub

    }



    @Override
    public void key() {
        // TODO Auto-generated method stub

    }
    @Override
	public void draw(PGraphics b) {
        
    }

    
}