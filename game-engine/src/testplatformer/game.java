package testplatformer;

import engine.scene;
import engine.window;
import engine.ui.button;

public class game extends scene {

    public game(window Parrent, String id) {
        super(Parrent, id);
        // addUIEntity(new button(400, 400, 100, 30,"games", this, Parrent), "name");
        platformManager p = new platformManager(this,Parrent);
        addUIEntity(p,"management");
        addUIEntity(new player(10,10,this,Parrent, "player",p), "player");
    }


} 