package testplatformer;

import engine.scene;
import engine.window;
import engine.ui.button;
import engine.ui.card;

public class game extends scene {

    public game(window Parrent, String id) {
        super(Parrent, id);
        // addUIEntity(new button(400, 400, 100, 30,"games", this, Parrent), "name");
        platformManager p = new platformManager(this,Parrent);
        player play=new player(10,10,this,Parrent, "player",p);
        addEntity(p,"management");
        addEntity(play, "player");
        card c = new card(10,10,300,200,this,Parrent,"player data");
        playerUI playinfo = new playerUI(play, this, Parrent, "playerUI");
        c.addElemend(playinfo);
        addUIEntity(c, "playerUI");
    }


} 