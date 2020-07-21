package testplatformer;

import engine.scene;
import engine.window;
import engine.ui.UIelement;
import engine.ui.UItheme;
import engine.ui.primitives;
import processing.core.PGraphics;

public class playerUI extends UIelement {
    player player;
    UItheme col;
    int[] frameRate=new int[100];
    public playerUI(player p,scene s, window w, String name){
        this(0,0,200,100,s,w,name);
        player=p;
        col = UItheme.Singleton();
    }

    public playerUI(int x, int y, int sizex, int sizey, scene s, window w, String name) {
        super(x, y, sizex, sizey, s, w, name);

    }

    void leftRotatebyOne(int arr[], int n) 
    { 
        int i, temp; 
        temp = arr[0]; 
        for (i = 0; i < n - 1; i++){
            arr[i] = arr[i + 1]; 
        }
        arr[i] = temp; 
    } 

    public void update(window w) {
        if(w.frameCount%2==0){
            leftRotatebyOne(frameRate,frameRate.length);
            frameRate[frameRate.length-1]=(int)w.frameRate;

        }
    }
    
    @Override
    public void draw(PGraphics b){
        b.rect(x, y, sizex, sizey);
        b.fill(col.c_text_color);
        b.textAlign(b.LEFT,b.TOP);
        b.text(w.frameRate,5,5);
        b.text(player.gems,5,15);
        b.text(player.health,80,5);
        float perHealth=((float)player.health/(float)player.maxHealth)*100;
        b.textAlign(b.CENTER);
        primitives.bargraph(5, 30, 100,40, frameRate, b);
        primitives.healthBar(5, 70, 100,40,perHealth, b);

    }

    @Override
    public void ValUpdate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void key() {
        // TODO Auto-generated method stub

    }

    
}