package game;

import engine.loader;
import engine.scene;

public class gameWindow extends engine.window {

	@Override
	public void settings() {
		size(600, 600);

	}

	@Override
	public void setup() {
		scene initscreen = new scene(this, "home");
		initscreen.addEntity(new player(150, 150, 20, initscreen, this), "player");
		addScene(initscreen);
		selectScene("home");

	}

	@Override
	public void update() {
//		println(input.Mouse.toString());
		// TODO Auto-generated method stub

	}

	@Override
	public void keyUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClick() {
		// TODO Auto-generated method stub

	}

}
