package engine.ui;

import java.util.HashMap;

import engine.scene;
import engine.window;
import processing.core.PGraphics;

public class toggle extends UIelement {
	Boolean toggle = false;
	Boolean can = true;
	public HashMap<String, Integer> colors;
	boolean hover;

	public toggle(int x, int y, int sizex, int sizey, scene s, window w, String name) {
		super(x, y, sizex, sizey, s, w, name);
		colors=window.getDark();
	}

	public Boolean getVal() {
		return toggle;
	}

	@Override
	public void update(window w) {

		hover= w.mouseX >= x && w.mouseX <= x+sizex && 
		w.mouseY >= y && w.mouseY <= y+sizey;

	}

	@Override
	public void draw(PGraphics b) {
		b.fill(colors.get("c_dark"));
  		b.stroke(colors.get("c_light"));
  		b.rect(x, y, sizex, sizey, sizey/2);
		int pos=0;
		if(toggle){
			pos=sizex-sizey;
		}
		if (hover)
		{
	
		b.noStroke();
	
		b.fill(colors.get("c_hover"), 100);  
		b.ellipse(x+sizey/2+pos, y+sizey/2, sizey-2, sizey-2);
		b.fill(colors.get("c_hover"));
		b.ellipse(x+sizey/2+pos, y+sizey/2, sizey-8, sizey-8);
		b.noStroke();
		}else{
			b.fill(colors.get("c_light"));
    		b.stroke(colors.get("c_light"));
    		b.ellipse(x+sizey/2+pos, y+sizey/2, sizey-8, sizey-8);
		}

	}

	@Override
	public void click() {
		if (w.input.Mouse.left && can) {
			if (w.input.Mouse.X() > x && w.input.Mouse.X() < x + sizex) {
				if (w.input.Mouse.Y() > y && w.input.Mouse.Y() < y + sizey) {
					toggle = !toggle;
					can = false;
					ValUpdate();
				}
			}
		} else {
			if (!w.input.Mouse.left) {
				can = true;
			}
		}
	}

	@Override
	public void ValUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void key() {
		// TODO Auto-generated method stub

	}
}
