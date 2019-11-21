package game;

import engine.screen;

public class gameWindow extends engine.window {

	@Override
	public void settings() {
		size(600,600);
		screen initscreen = new screen(this,"home");
		initscreen.addEntity(new player(150,150,20));
		addScreen(initscreen);
		selectScreen("home");
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
//		println(input.Mouse.toString());
		
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
