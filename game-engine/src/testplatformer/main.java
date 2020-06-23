package testplatformer;

import engine.scene;
import engine.window;
import engine.ui.button;
import processing.core.PApplet;

public class main extends window {

    public static void main(String[] args) {
        window.main("testplatformer.main", args);
    }
    @Override
    public void settings() {
        size(800,800);

        scene initscreen = new scene(this, "home");
        addScene(initscreen);
        selectScene("home");
        addScene(new game(this,"game"));

        initscreen.addUIEntity(new button((width/2)-50, (height/2)-5, 100, 30,"start", initscreen, this), "homebutton");

    }

    @Override
    public void setup() {
    }

    @Override
    public void update() {
        if(selected=="home"){
            if(((button) getScene("home").UIentities.get("homebutton")).getVal()){
                selectScene("game");
            }
        }

    }


    @Override
    public void keyUpdate() {

    }

    @Override
    public void mouseClick() {

    }

    @Override
    public void mouseWheel() {

    }
    
}