package testplatformer;

import engine.scene;
import engine.window;
import engine.entity.entity;
import engine.sprite.sprite;
import processing.core.PGraphics;

public class player extends entity {
    sprite p;
    int xspd, yspd, acc, grav;
    int maxspd = 20;
    platformManager platforms;
    int jumps=2;
    boolean canjump=true;
    public player(int x, int y, scene s, window w, String name, platformManager p) {
        super(x, y, 50, 50, s, w, name);
        xspd = 0;
        yspd = 0;
        acc = 2;
        grav = 3;
        platforms = p;

    }

    @Override
    public void update(window w) {


        if(w.input.get('r')){
            platforms.load();
        }
        // acceleration
        yspd+=grav;
        if (w.input.get('w')) {
            if(canjump && jumps>0){
                yspd -= acc*10;
                jumps-=1;
                // canjump=false;
            }
        }else{
            canjump=true;
        }
        if (w.input.get('a')) {
            xspd -= acc;

        }
        if (w.input.get('s')) {
            yspd += acc;
        }
        if (w.input.get('d')) {
            xspd += acc;
        }
        // not pressing keys
        if (!(w.input.get('a') || w.input.get('d'))) {
            if (xspd < 0) {
                xspd = xspd / 2;
            }

            if (xspd > 0) {
                xspd = xspd / 2;
            }
        }

        // platform colition
        if(platforms.colPlayer(x+xspd, y, sizex,sizey)){
            xspd=0;
        }
        if(platforms.colPlayer(x, y+yspd, sizex,sizey)){
            if(yspd>0){
                jumps=2;
            }
            yspd=0;
        }
        if(platforms.colPlayer(x+xspd, y+yspd, sizex,sizey)){
            yspd=0;
            xspd=0;
        }

        // side of screen col
        if(x+xspd<0 || x+xspd+sizex>w.width){
            xspd=0;
        }

        // moving player
        if(xspd<-maxspd){
            xspd=-maxspd;
        }
        if(xspd>maxspd){
            xspd=maxspd;
        }
        
        
        // moving player
        x += xspd;
        y += yspd;

        // moving cammera
        if(y<100){
            platforms.UPDateoffset(-20);
            y=y+20;
        }
        if(y<0){
            platforms.UPDateoffset(-40);
            y=y+40;
        }

        if(y>w.height-100){
            platforms.UPDateoffset(20);
            y=y-20;
        }
        if(y>w.height){
            platforms.UPDateoffset(40);
            y=y-40;
        }

        //
        

    }

    public boolean hitwall(int x, int y) {
        return false;
    }

    @Override
    public void draw(PGraphics b) {
        if(platforms.colPlayer(x, y, sizex,sizey)){
            b.fill(0,0,255);
        }else{
            b.fill(255);
        }
        b.rect(x, y, sizex, sizey);

    }

    @Override
    public void click() {

    }

    @Override
    public void key() {

    }

}