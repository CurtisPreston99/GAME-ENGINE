package engine.ui;

import java.util.ArrayList;

import engine.scene;
import engine.window;
import engine.input.mouse;
import processing.core.PGraphics;

public class card extends UIelement {
    private boolean dragable = true;
    private boolean title = false;
    private String titleText = "";
    private UItheme colors = UItheme.Singleton();

    ArrayList<UIelement> UIList=new ArrayList<UIelement>();


    private boolean pressed = false;

    public card(int x, int y, int sizex, int sizey, scene s, window w, String name) {
        super(x, y, sizex, sizey, s, w, name);
        title = true;
        titleText = name;
    }

    @Override
    public void ValUpdate() {
    }

    @Override
    public void update(window w) {
        mouse mouse = w.input.Mouse;
        if(mouse.left){
            if(pressed){
                        
                x=x+(mouse.X()-mouse.prevX());
                y=y+(mouse.Y()-mouse.prevY());
            }
            if(mouse.X()>x&&mouse.X()<x+sizex){
                if(mouse.Y()>y&&mouse.Y()<y+sizey){
                    pressed=true;
                }
            }
        }else{
            pressed=false;
        }
    }

    @Override
    public void key() {
        // TODO Auto-generated method stub

    }
    @Override
	public void draw(PGraphics b) {

        b.noStroke();
        //Shadow
        b.fill(0, 0, 0, 15);
        b.rect(x+5, y+5, sizex, sizey);
        b.fill(colors.c_light);
        b.rect(x, y, sizex, 40, 2, 2, 0, 0);
        b.textSize(15);
        b.textAlign(b.CENTER, b.CENTER);
        b.fill(colors.c_text_color);
        if(title){
            b.text(titleText, x, y, sizex, 40);
        }
        b.fill(colors.c_mid);

        b.rect(x, y+40, sizex, sizey-40, 0, 0, 2, 2);
        
    }

    
}