package engine;

import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.MouseEvent;

public abstract class window extends PApplet {
	public input input = new input(this);
	public HashMap<String, screen> screensh = new HashMap<>();
	public String selected;

	abstract public void settings();

	abstract public void setup();

	abstract public void update();

	abstract public void keyUpdate();

	abstract public void mouseClick();

	
	public void addScreen(screen s) {
		screensh.put(s.id, s);
	}

	public void selectScreen(String s) {
		selected = s;
	}

	public void draw() {
		background(255);
		update();
//		screens.get(selectedScreen).update();
		screensh.get(selected).update();
		PGraphics frame = createGraphics(width, height);
		frame.beginDraw();
		frame.background(100);
		screensh.get(selected).draw(frame);
		frame.endDraw();
		image(frame, 0, 0);
	}

	public void keyPressed() {
		input.set(key, true);
	}

	public void keyReleased() {
		input.set(key, false);
	}

	public void mousePressed() {
		input.Mouse.press(mouseButton, true);
	}

	public void mouseReleased() {
		input.Mouse.press(mouseButton, false);
	}

	public void mouseWheel(MouseEvent event) {
		float e = event.getCount();
		input.Mouse.scroll(e);
	}

}
