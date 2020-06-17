package engine.helper;


// https://www.youtube.com/watch?v=h1o5UzKfZcQ


import engine.scene;
import engine.window;
import engine.sprite.spriteGroup;
import engine.ui.UItheme;
import engine.ui.button;
import engine.ui.imageButton;
import engine.ui.slider;
import engine.ui.textbox;
import engine.ui.toggle;
import processing.core.PImage;

public class helper extends window {
	public static void main(String[] args) {
		window.main("engine.helper.helper", args);
	}

	@Override
	public void settings() {
		size(800, 800);
		scene initscreen = new scene(this, "home");
		addScene(initscreen);
		scene mapping = new scene(this, "mapping");
		addScene(initscreen);
		addScene(mapping);
		selectScene("home");
		initscreen.addUIEntity(new button(10, 10, 100, 30, initscreen, this), "homebutton");
		initscreen.addUIEntity(new toggle(400, 10, 100, 30,initscreen, this,"UI switch"), "UI switch");
		initscreen.addUIEntity(new button(300, 10, 100, 30, "file test", initscreen, this), "savingTest");
		initscreen.addUIEntity(new toggle(10, 100, 100, 40, initscreen, this, "toggle"), "toggle");
		initscreen.addUIEntity(new slider(10, 200, 100, 40, initscreen, this, "slider"), "slider");
		initscreen.addUIEntity(new textbox(10, 300, 150, 80, initscreen, this, "textbox"), "textbox");
		PImage l = Loader.loadImage("images/almond.jpg");
		initscreen.addUIEntity(new imageButton(10, 300,l , initscreen, this,"btest"), "image button");
	}

	@Override
	public void setup() {
		try {
			spriteGroup s = spriteGroup.JSONload(Loader.loadJSON("grouptest"), this);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public void update() {
		if (selected == "home") {
			slider slider = (slider) getSelectedScene().UIentities.get("slider");

			button save = (button) getSelectedScene().UIentities.get("savingTest");
			toggle UI = (toggle) getSelectedScene().UIentities.get("UI switch");
			if(UI.getVal()){
				UItheme.Singleton().setDark();
			}else{
				UItheme.Singleton().setLight();
			}
			if (save.getVal()) {
				try {
					String in = Loader.loadTxt("test");
					System.out.println(in);
					in = in + in + in;

					Loader.save(in, "fuckme");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			button b = (button) getSelectedScene().UIentities.get("homebutton");
			if (b.pressed) {
				selectScene("mapping");
				scene s = getSelectedScene();
				s.addUIEntity(new button(10, 10, 100, 20, s, this), "mappingButton");
			}
		}
		if (selected == "mapping") {
			button b = (button) getSelectedScene().UIentities.get("mappingButton");
			if (b.pressed) {
				getSelectedScene().addUIEntity(new slider(10, 300, 200, 20, 0, 20, getSelectedScene(), this, "slider"),
						"slider");
				getSelectedScene().addUIEntity(new button(140, 10, 100, 20, getSelectedScene(), this),"back");

			}
			b = (button) getSelectedScene().UIentities.get("back");
			if (b!=null && b.pressed) {
				selectScene("home");

			}
		}
		
	}

	@Override
	public void keyUpdate() {
	}

	@Override
	public void mouseClick() {

	}

	@Override
	public void mouseWheel() {
	}

}
