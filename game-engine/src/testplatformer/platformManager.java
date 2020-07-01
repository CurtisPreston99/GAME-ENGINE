package testplatformer;

import java.util.ArrayList;
import java.util.HashMap;

import engine.scene;
import engine.window;
import engine.entity.entity;
import engine.ui.slider;
import processing.core.PGraphics;
import processing.data.JSONArray;
import processing.data.JSONObject;

class platformManager extends entity {
    JSONObject json;
    int yoffset = 0;
    String selected;
    HashMap<String, platformL> level = new HashMap<String, platformL>();

    public platformManager(scene s, window w) {
        super(0, 0, w.width, s, w);
        load();
        room("level1");
    }

    public void room(String l) {
        selected = l;
    }

    public void load() {
        try {
            json = w.Loader.loadJSON("testplatformer");
            selected = json.keys().toArray()[0].toString();
            for (Object s : json.keys()) {
                System.out.println(s.toString());
                level.put(s.toString(), new platformL(json.getJSONObject(s.toString())));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void UPDateoffset(int i) {
        yoffset += i;
    }




    public boolean colPlayer(int x, int y, int xlen, int ylen) {
        int[][] map = level.get(selected).level;

        int blockWidth = w.width / (map[0].length);
        int blockHeight = blockWidth;

        // top left
        int blockonX = (x / blockWidth);
        int blockonY = (y + yoffset) / blockHeight;

        if (blockonX < map[0].length && blockonX >= 0 && blockonY >= 0 && blockonY < map.length) {
            if (map[blockonY][blockonX] == 1) {
                return true;
            }
        }
        // top right
        blockonX = ((x + xlen) / blockWidth);
        blockonY = (y + yoffset) / blockHeight;

        if (blockonX < map[0].length && blockonX >= 0 && blockonY >= 0 && blockonY < map.length) {
            if (map[blockonY][blockonX] == 1) {
                return true;
            }
        }

        // bottom right
        blockonX = ((x + xlen) / blockWidth);
        blockonY = (y + yoffset + ylen) / blockHeight;

        if (blockonX < map[0].length && blockonX >= 0 && blockonY >= 0 && blockonY < map.length) {
            if (map[blockonY][blockonX] == 1) {
                return true;
            }
        }

        // bottom left
        blockonX = ((x) / blockWidth);
        blockonY = (y + yoffset + ylen) / blockHeight;

        if (blockonX < map[0].length && blockonX >= 0 && blockonY >= 0 && blockonY < map.length) {
            if (map[blockonY][blockonX] == 1) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void update(window w) {

    }

    public platformL getSelectedPlatform(){
        return level.get(selected);
    }

    @Override
    public void draw(PGraphics b) {

        int[][] map = level.get(selected).level;

        int blockWidth = b.width / (map[y].length);
        int blockHeight = blockWidth;
        // System.out.println("--------");
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                // System.out.print(level[y][x]);
                if (map[y][x] == 1) {
                    // print(ln(x))
                    b.rect((x) * blockWidth, (y * blockHeight) - yoffset, blockWidth, blockHeight);
                }
            }
            // System.out.println();
        }

        ArrayList<int[]> gems = level.get(selected).gems;
        for(int i=0;i<gems.size();i++){
            int[] cord=gems.get(i);
            b.fill(0,255,0);
            b.rect((cord[1]) * blockWidth, (cord[0] * blockHeight) - yoffset, blockWidth, blockHeight);

        }

        ArrayList<JSONObject> doors = level.get(selected).doors;
        for(int i=0;i<doors.size();i++){
            // JSONObject door=doors.get(i);
            b.fill(104,68,39);
            int[] cord=doors.get(i).getJSONArray("cords").getIntArray();
            b.rect(cord[1] * blockWidth, (cord[0] * blockHeight) - yoffset, blockWidth, blockHeight);

        }



    }

    @Override
    public void click() {
        if (selected == "level1") {
            selected = "level2";
        } else {
            selected = "level1";
        }
    }

    @Override
    public void key() {
        // TODO Auto-generated method stub

    }

}