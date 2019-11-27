package game;

import engine.scene;
import processing.core.PApplet;

public class gameWindow extends engine.window {

	public static void main(String[] args) {
		PApplet.main("game.gameWindow");
	}

	@Override
	public void settings() {
		size(600, 600);

	}

	@Override
	public void setup() {
		scene initscreen = new scene(this, "home");
		initscreen.addEntity(new player(150, 150, 20, initscreen, this), "player");
		initscreen.addEntity(new colbox(300, 300, 20, initscreen, this), "col");
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
