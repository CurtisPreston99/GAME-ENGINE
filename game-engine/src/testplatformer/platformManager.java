package testplatformer;

import engine.scene;
import engine.window;
import engine.entity.entity;
import processing.core.PGraphics;
import processing.data.JSONArray;
import processing.data.JSONObject;

class platformManager extends entity {
    JSONObject levels;
    int yoffset = 0;
    int[][] level;

    public platformManager(scene s, window w) {
        super(0, 0, w.width, s, w);
        load();
        room("level1");
    }
    
    public void room(String l){
        JSONArray lev= levels.getJSONArray(l);

        level= new int[lev.size()][lev.getJSONArray(0).size()];
        
        for(int i=0;i<lev.size();i++){
            JSONArray row=lev.getJSONArray(i);
            for(int e=0;e<row.size();e++){
                level[i][e]=row.getInt(e);
                System.out.print(level[i][e]);
            }
            System.out.println();
        }
    }

    public void load() {
        try {
            levels = w.Loader.loadJSON("testplatformer");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void UPDateoffset(int i){
        yoffset+=i;
    }


    public boolean colPlayer(int x,int y,int xlen, int ylen){

        int blockWidth= w.width/(level[0].length);
        int blockHeight=blockWidth;



        // top left
        int blockonX=(x/blockWidth);
        int blockonY=(y+yoffset)/blockHeight;

        if(blockonX<level[0].length && blockonX>=0 && blockonY>=0 && blockonY<level.length ){
            if(level[blockonY][blockonX]==1){
                return true;
            }
        }
        // top right
        blockonX=((x+xlen)/blockWidth);
        blockonY=(y+yoffset)/blockHeight;

        if(blockonX<level[0].length && blockonX>=0 && blockonY>=0 && blockonY<level.length ){
            if(level[blockonY][blockonX]==1){
                return true;
            }
        }

        // bottom right
        blockonX=((x+xlen)/blockWidth);
        blockonY=(y+yoffset+ylen)/blockHeight;

        if(blockonX<level[0].length && blockonX>=0 && blockonY>=0 && blockonY<level.length ){
            if(level[blockonY][blockonX]==1){
                return true;
            }
        }

        // bottom left
        blockonX=((x)/blockWidth);
        blockonY=(y+yoffset+ylen)/blockHeight;

        if(blockonX<level[0].length && blockonX>=0 && blockonY>=0 && blockonY<level.length ){
            if(level[blockonY][blockonX]==1){
                return true;
            }
        }

        return false;
    }

    @Override
    public void update(window w) {
        
    }

    @Override
    public void draw(PGraphics b) {
        int blockWidth= b.width/(level[y].length);
        int blockHeight=blockWidth;
        // System.out.println("--------");
        for(int y=0;y<level.length;y++){
            for(int x=0;x<level[y].length;x++){
                // System.out.print(level[y][x]);
                if(level[y][x]!=1){
                    // print(ln(x))
                    b.rect((x)*blockWidth,(y*blockHeight)-yoffset,blockWidth,blockHeight);
                }
            }
            // System.out.println();
        }

    }

    @Override
    public void click() {
        // TODO Auto-generated method stub

    }

    @Override
    public void key() {
        // TODO Auto-generated method stub

    }


}