package engine;

import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.MouseEvent;

public abstract class window extends PApplet {
	public static HashMap<String, Integer> uiDark = new HashMap<String, Integer>(){{
		put("c_very_dark",-14408402);
		put("c_dark", -14868180);
		put("c_mid", -13878713);
		put("c_light",-13418416);
		put("c_hover", -14640224);
		put("c_text_color",-1);
	}};

	// public static HashMap<String, Integer> uiLight = new HashMap<String, Integer>(){{
	// 	put("c_very_dark", color(100));
	// 	put("c_dark", color(150));
	// 	put("c_mid", color(200));
	// 	put("c_light", color(250));
	// 	put("c_hover", color(32,155,160));
	// 	put("c_text_color", color(10));
		
	// }};


	public input input = new input(this);
	public HashMap<String, scene> screens = new HashMap<>();
	public String selected;
	public loader Loader = new loader(this);

	public static HashMap<String, Integer> getDark(){
		return uiDark;
	}

	@Override
	abstract public void settings();

	@Override
	abstract public void setup();

	abstract public void update();

	abstract public void keyUpdate();

	abstract public void mouseClick();

	@Override
	abstract public void mouseWheel();

	public void addScene(scene s) {
		screens.put(s.id, s);
	}

	public scene getScene(String s) {
		return screens.get(s);
	}

	public scene getSelectedScene() {
		return screens.get(selected);
	}

	public void selectScene(String s) {
		selected = s;
	}

	@Override
	public void draw() {
		background(uiDark.get("c_dark"));
		update();
		screens.get(selected).update();
		PGraphics frame = createGraphics(width, height);
		frame.beginDraw();
		frame.background(uiDark.get("c_dark"));
		screens.get(selected).draw(frame);
		frame.endDraw();
		image(frame, 0, 0);
	}

	@Override
	public void keyPressed() {
		input.set(key, true);
		screens.get(selected).key();
		keyUpdate();
	}

	@Override
	public void keyReleased() {
		input.set(key, false);
		screens.get(selected).key();
		keyUpdate();
	}

	@Override
	public void mousePressed() {
		input.Mouse.press(mouseButton, true);
		screens.get(selected).click();
		mouseClick();
	}

	@Override
	public void mouseReleased() {
		input.Mouse.press(mouseButton, false);
		screens.get(selected).click();
		mouseClick();
	}

	@Override
	public void mouseWheel(MouseEvent event) {
		float e = event.getCount();
		input.Mouse.scroll(e);
		mouseWheel();
	}

}
