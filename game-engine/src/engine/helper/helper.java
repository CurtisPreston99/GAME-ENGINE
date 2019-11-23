package engine.helper;

import engine.screen;
import engine.window;
import engine.ui.UIelement;
import engine.ui.button;
import game.player;

public class helper extends window{
	public static void main(String[] args) {
		window.main("engine.helper.helper",args);
	}

	@Override
	public void settings() {
		size(800,800);
		screen initscreen = new screen(this, "home");
		addScreen(initscreen);
		selectScreen("home");
		initscreen.addEntity(new button(10,10,100,30,this));

		
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
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
