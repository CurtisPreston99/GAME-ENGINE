package engine.ui;

import engine.scene;
import engine.window;
import engine.entity.entity;
import engine.input.mouse;
import processing.core.PGraphics;

public class dropdown extends UIelement {
    String[] options;
    int selected=0;
    boolean down=false;
    Boolean canDrop = true;
    int over=0;

    public dropdown(int x, int y, int sizex,int sizey,String[] options, scene s, window w, String name) {
        super(x, y, sizex, sizey, s, w, name);
        this.options=options;
    }

    @Override
    public void update(window w) {
        mouse mouse =w.input.Mouse;

        if(down){
            
            int Mx=mouse.X();
            int My=mouse.Y();
            if(down&& Mx>x&&Mx<x+sizex){
            int Ny=(My-(y+sizey))/sizey;
            if(Ny<options.length){
                // System.out.println(Ny);
                // System.out.println(options[Ny]);
                over=Ny;
                }
            }
        }
    }
    @Override
    public void draw(PGraphics b) {
        if(hover()){
            b.fill(UItheme.Singleton().c_hover);
        }else{
            b.fill(UItheme.Singleton().c_light);
        }
        b.rect(x, y, sizex, sizey);
        b.fill(UItheme.Singleton().c_text_color);
        b.text(options[selected],x+(sizex/2),y+(sizey/2));
        if(down){
            b.fill(UItheme.Singleton().c_mid);
            b.rect(x,y+sizey,sizex,sizey*options.length);
            int dx=(sizex/2);
            int dy=(sizey/2);
            b.fill(UItheme.Singleton().c_text_color);
            for(int i=0;i<options.length;i++){
                if(i==over){
                    b.fill(UItheme.Singleton().c_hover);
                    b.rect(x,y+(sizey*(i+1)),sizex,sizey);
                    b.fill(UItheme.Singleton().c_text_color);
                }
                b.text(options[i],x+dx,y+(sizey*(i+1)+dy));
            }

        }

    }

    @Override
    public void click() {
        mouse mouse =w.input.Mouse;
        if(mouse.left){
            int Mx=mouse.X();
            int My=mouse.Y();
            if(Mx>x&&Mx<x+sizex && My>y&&My<y+sizey &&canDrop){
                down=!down;
                canDrop=false;
            }else{
            if(down&& Mx>x&&Mx<x+sizex){
                int Ny=(My-(y+sizey))/sizey;
                if(Ny<options.length){
                    // System.out.println(Ny);
                    // System.out.println(options[Ny]);
                    selected=Ny;
                }

            }
        }
        }else{
            canDrop=true;
        }

    }

    @Override
    public void key() {
    }

    @Override
    public void ValUpdate() {
        // TODO Auto-generated method stub

    }
    
}