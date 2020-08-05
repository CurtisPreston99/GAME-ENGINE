package engine.ui;

import java.util.ArrayList;

import engine.scene;
import engine.window;
import engine.input.mouse;
import processing.core.PGraphics;

public class card extends UIelement {
    private boolean dragable = true;
    private boolean title = false;
    private boolean wrapup = false;
    private String titleText = "";
    private UItheme colors = UItheme.Singleton();
    private int titleSize=40;

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


    public void addElemend(UIelement e){
        UIList.add(e);
    }

    @Override
	public void click() {
        mouse mouse = w.input.Mouse;


        if(mouse.right){
            if(mouse.X()>x&&mouse.X()<x+sizex){
                if(mouse.Y()>y&&mouse.Y()<y+titleSize){
                    wrapup=!wrapup;
                }
            }
        }
        
        if(!wrapup){
            int mX=mouse.X();
            int mY=mouse.Y();

            w.mouseX=mX-x;
            w.mouseY=mY-y-titleSize;

            for(UIelement e:UIList){
                e.click();

            }



            w.mouseX=mX;
            w.mouseY=mY;
        }
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
                if(mouse.Y()>y&&mouse.Y()<y+titleSize){
                    pressed=true;
                }
            }
        }else{
            pressed=false;
        }

        

        // hacky but should work
        int mX=mouse.X();
        int mY=mouse.Y();

        w.mouseX=mX-x;
        w.mouseY=mY-y-titleSize;

        for(UIelement e:UIList){
            e.update(w);

        }



        w.mouseX=mX;
        w.mouseY=mY;
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
        // title rect
        b.fill(colors.c_light);
        b.rect(x, y, sizex, titleSize, 2, 2, 0, 0);
        b.textSize(15);
        b.textAlign(b.CENTER, b.CENTER);
        b.fill(colors.c_text_color);
        // tiltle text
        if(title){
            b.text(titleText, x, y, sizex, 40);
        }
        // main portoion
        if(!wrapup){
            b.fill(colors.c_mid);

            b.rect(x, y+titleSize, sizex, sizey-titleSize, 0, 0, 2, 2);

            mouse mouse = w.input.Mouse;

            int mX=mouse.X();
            int mY=mouse.Y();

            w.mouseX=mX-x;
            w.mouseY=mY-y-titleSize;
            

            b.pushMatrix();
            b.translate(x, y+titleSize);

            for(UIelement e:UIList){
                e.draw(b);
            }
            b.popMatrix();


            w.mouseX=mX;
            w.mouseY=mY;
        }
        
    }

    
}