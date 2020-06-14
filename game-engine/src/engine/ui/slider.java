package engine.ui;

import java.util.HashMap;

import engine.scene;
import engine.window;
import processing.core.PConstants;
import processing.core.PGraphics;

public class slider extends UIelement {
	float maxVal, minVal;
	float slider = 0;
    boolean hover;
    boolean clicked;
    public HashMap<String, Integer> colors;

	public slider(int x, int y, int sizex, int sizey, float minVal, float maxVal, scene s, window w, String name) {
		super(x, y, sizex, sizey, s, w, name);
		this.maxVal = maxVal;
		this.minVal = minVal;
        colors=window.getDark();

    }
    
    // public slider

	public slider(int x, int y, int sizex, int sizey, scene s, window w, String name) {
        this(x, y, sizex, sizey, 0, 1, s, w, name);
        colors=window.getDark();

	}

	public float getVal() {
		if (slider == 0) {
			return 0;
		}
//		(slider / sizex)* (maxVal-minVal)+minVal

		Float value = (slider / sizex) * (maxVal - minVal) + minVal;

		return value;

//		return (float) 0.5;
    }
    
    public static float mapRange(double a1, double a2, double b1, double b2, double s) {
		return (float) (b1 + ((s - a1) * (b2 - b1)) / (a2 - a1));
	}

	@Override
	public void draw(PGraphics b) {


        b.noStroke();
        b.fill(colors.get("c_light"));
        b.rect(x, y+sizey/2, sizex, 4, 2);
        float pos = mapRange(minVal, maxVal, 0, sizex,slider);
        b.fill(colors.get("c_hover"));
        b.rect(x, y+sizey/2, pos, 4, 2);

        //Hover
        if (hover)
        {
            b.fill(colors.get("c_hover"));
            if (clicked) {
                b.fill(colors.get("c_hover"), 100);
                b.ellipse(pos, y+sizey/2, sizex, sizex); 
                b.fill(colors.get("c_hover"));
                b.ellipse(pos, y+sizey/2, sizey-8, sizey-8);
            } else {
                b.fill(colors.get("c_hover"), 50);
                b.ellipse(pos+x, y+sizey/2, sizey, sizey); 
                b.fill(colors.get("c_hover"));
                b.ellipse(pos+x, y+sizey/2, sizey-8, sizey-8);
            }
        } 
        //Normal
        else {
            b.noStroke();
            b.fill(colors.get("c_hover"));
            b.ellipse(pos+x, y+sizey/2, sizey-8, sizey-8);
        }

    }
    

    @Override
	public void update(window w) {

		hover= w.mouseX >= x && w.mouseX <= x+sizex && 
		w.mouseY >= y && w.mouseY <= y+sizey;

	}

	@Override
	public void click() {
		// mapRange(minVal, maxVal, 0, sizex,slider);
		if (w.input.Mouse.left) {
            clicked=true;
			if ( w.mouseX >= x && w.mouseX <= x+sizex && w.mouseY >= y && w.mouseY <= y+sizey) {
                slider=mapRange(x, x+sizex,minVal, maxVal,w.mouseX);
                
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
