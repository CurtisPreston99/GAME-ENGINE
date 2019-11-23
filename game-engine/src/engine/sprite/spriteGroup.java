package engine.sprite;

import engine.window;
import processing.core.PGraphics;

public class spriteGroup extends sprite {
	int width, height;
	int[][] spritePos;
	int[] spriteSpeed;
	sprite[] spritelist;
	int frame=0;
	
	public spriteGroup(sprite[] spritelist, int[][] pos,int[] spd, int width, int height, window w) {
		super(w);
		spritePos = pos;
		spriteSpeed =spd;
		this.spritelist= spritelist;
		this.width=width;
		this.height=height;
	}

	@Override
	public PGraphics frame() {
		PGraphics frame = w.createGraphics(width, height);
		frame.beginDraw();
		for(int i=0;i<spritelist.length;i++) {
			frame.image(spritelist[i].frame(),spritePos[i][0],spritePos[i][1]);
		}
		frame.endDraw();
		
		return frame;
	}
	@Override
	public void Nframe() {
		frame++;
		for(int i=0;i<spritelist.length;i++) {
			if(frame%spriteSpeed[i]==0) {
				spritelist[i].Nframe();
			}
		}
	}

}
