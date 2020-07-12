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
        scene editor = new editor(this, "editor");
        editor.addUIEntity(new button(10, 10, 100, 30,"return", initscreen, this), "return");
        addScene(initscreen);
        selectScene("home");
        addScene(new game(this,"game"));
        addScene(editor);

        initscreen.addUIEntity(new button((width/2)-50, (height/2)-5, 100, 30,"start", initscreen, this), "homebutton");
        initscreen.addUIEntity(new button(10, 10, 100, 30,"level editor", initscreen, this), "level editor");

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

            if(((button) getScene("home").UIentities.get("level editor")).getVal()){
                selectScene("editor");
            }
        }

        if(selected=="editor"){
            if(((button) getScene("editor").UIentities.get("return")).getVal()){
                selectScene("home");
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