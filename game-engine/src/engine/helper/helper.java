package engine.helper;

import engine.scene;
import engine.window;
import engine.sprite.spriteGroup;
import engine.ui.button;
import engine.ui.slider;
import engine.ui.textbox;
import engine.ui.toggle;

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
		initscreen.addUIEntity(new button(300, 10, 100, 30, "file test", initscreen, this), "savingTest");
		initscreen.addUIEntity(new toggle(10, 100, 100, 40, initscreen, this, "toggle"), "toggle");
		initscreen.addUIEntity(new slider(10, 200, 100, 40, initscreen, this, "slider"), "slider");
		initscreen.addUIEntity(new textbox(10, 300, 100, 40, initscreen, this, "textbox"), "textbox");
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		try {
			spriteGroup s = spriteGroup.JSONload(Loader.loadJSON("grouptest"), this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update() {
		if (selected == "home") {
			slider slider = (slider) getSelectedScene().UIentities.get("slider");

			button save = (button) getSelectedScene().UIentities.get("savingTest");
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

			}
		}
	}

	@Override
	public void keyUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheel() {
		// TODO Auto-generated method stub
		
	}

}
