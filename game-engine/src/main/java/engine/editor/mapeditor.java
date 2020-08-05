package engine.editor;

import java.io.File;

import engine.scene;
import engine.window;
import engine.entity.entity;
import engine.map.map;
import engine.map.tile;
import engine.ui.button;
import engine.ui.camera;
import engine.ui.textbox;
import processing.core.PApplet;

public class mapeditor extends scene {

	tile[] tiles;
	map m;
	camera c;
	tileSelector t;
	button b;

	public mapeditor(window Parrent, String id) {
		super(Parrent, id);
		m = new map("map/testmap.json", Parrent);
		c = new camera(0, 0, Parrent.width, Parrent.height, m, this, Parrent, "cammera");

		addUIEntity(c, "cammera");
		File file;
		file = new File(Parrent.dataPath("") + "/tiles");
		String[] listPath = file.list();
		PApplet.printArray(listPath);
		tiles = new tile[listPath.length];
		for (int i = 0; i < listPath.length; i++) {
			tiles[i] = new tile("tiles/" + listPath[i], Parrent);
		}
		t = new tileSelector(Parrent.width - tiles[0].getTile().width, 0, tiles, this, Parrent, "tiles");

		addUIEntity(t, "tiles");
		b = new button(Parrent.width - 100, tiles.length * m.tileHeight + 20, 100, 30, "save map", this, Parrent);
		addUIEntity(b, "save");
		addUIEntity(new textbox(Parrent.width - 100, tiles.length * m.tileHeight + 70, 200, 20, this, Parrent, "Fname"),
				"Fname");

		addUIEntity(new button(Parrent.width/2, Parrent.height-150, 100, 100, "+", this, Parrent), "resizeY");
		addUIEntity(new button(Parrent.width-150, Parrent.height/2, 100, 100, "+", this, Parrent), "resizeX");
		getUIEntity("resizeX").setAtrribute("added", "false");
		getUIEntity("resizeY").setAtrribute("added", "false");
	}

	@Override
	public void tick() {
	
		if (b.pressed) {
			System.out.print(c.getMap().toJson());
			Parrent.Loader.save(c.getMap().toJson(), "map/" + ((textbox) getUIEntity("Fname")).text + ".json");
		}

		if(((button)getUIEntity("resizeY")).getVal()){
			if(getUIEntity("resizeY").getAtrribute("added")=="false"){
				getUIEntity("resizeY").setAtrribute("added", "true");
				this.m.resize(m.width+1, m.height);
			}
		}
		if(((button)getUIEntity("resizeX")).getVal()){
			if(getUIEntity("resizeX").getAtrribute("added")=="false"){
				getUIEntity("resizeX").setAtrribute("added", "true");
				this.m.resize(m.width, m.height+1);
			}
		}
	}

	@Override
	public void click() {
		int[] mouse = c.MapTanslate(Parrent.input.Mouse.X(), Parrent.input.Mouse.Y());
		if (mouse[0] < c.getMap().height && mouse[0] >= 0 && mouse[1] < c.getMap().width && mouse[1] >= 0) {
			if (Parrent.input.Mouse.left) {

				m.setTile(tiles[t.selected], mouse[0], mouse[1]);
			}
			if (Parrent.input.Mouse.right) {
				m.setTile(-1, mouse[0], mouse[1]);
			}
		}
		if(!Parrent.input.Mouse.left){
			getUIEntity("resizeX").setAtrribute("added", "false");
			getUIEntity("resizeY").setAtrribute("added", "false");
		}
		for (entity e : UIentities.values()) {
			e.click();
		}
	}
}


