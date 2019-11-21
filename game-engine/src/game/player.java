package game;

import engine.entity;
import engine.window;

public class player extends entity {

	public player(int x, int y, int size) {
		super(x, y, size,"player");
	}
	
	@Override
	public void update(window w) {
		int speed=1;
		
		if(w.input.get("shift")) {
			speed=10;
		}
		if(w.input.get('W')) {
			y=y-speed;
		}
		if(w.input.get('S')) {
			y=y+speed;
		}
		if(w.input.get('A')) {
			x=x-speed;
		}
		if(w.input.get('D')) {
			x=x+speed;
		}
	}

}
