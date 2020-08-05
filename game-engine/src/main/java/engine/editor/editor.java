package engine.editor;

import engine.scene;
import engine.window;
import engine.ui.UIimage;
import engine.ui.button;

public class editor extends window {
	public static void main(String[] args) {
		window.main("engine.editor.editor", args);
	}

	@Override
	public void settings() {
		size(800,800);
		println("this.sketchPath");

		println(this.sketchPath());
		println(this.dataPath(""));
	}

	@Override
	public void setup() {

		scene initscreen = new scene(this, "home");
		initscreen.addUIEntity(new button(10, 10, 100, 40, "Sprite Tools", initscreen, this), "spriteTools");
		initscreen.addUIEntity(new button(10, 60, 100, 40, "Map Tools", initscreen, this), "MapTools");
		initscreen.addUIEntity(UIimage.NewUIimage(600, 50, "images/logo.png", initscreen, this, "logo"), "logo");

		addScene(initscreen);
		scene Sprites = new scene(this, "Sprites");
		addScene(Sprites);

		scene mapping = new mapeditor(this, "mapping");
		addScene(initscreen);
		addScene(mapping);
		selectScene("home");
	}

	@Override
	public void update() {
		if (selected == "home") {
			scene s = getScene("home");
			if (((button) s.getUIEntity("spriteTools")).getVal()) {
				selected = "Sprites";
				scene spr = getScene("Sprites");

			}
			if (((button) s.getUIEntity("MapTools")).getVal()) {
				selected = "mapping";

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
