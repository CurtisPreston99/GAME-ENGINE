package testplatformer;

import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import engine.scene;
import engine.window;
import processing.data.JSONArray;

public class platformL {
    int[][] level;
    ArrayList<int[]> gems= new ArrayList<int[]>();
    ArrayList<JSONObject> doors= new ArrayList<JSONObject>();
    ArrayList<defultEm> enemies = new ArrayList<defultEm>(); 

    public platformL(JSONObject l,scene s,window w,platformManager p){
        JSONArray lev= l.getJSONArray("map");
        JSONArray gem= l.getJSONArray("gems");
        JSONArray door= l.getJSONArray("doors");
        
        level= new int[lev.size()][lev.getJSONArray(0).size()];
        
        
        for(int i=0;i<lev.size();i++){
            JSONArray row=lev.getJSONArray(i);
            for(int e=0;e<row.size();e++){
                switch (row.getInt(e)) {
                    case 1:
                        level[i][e]=1;
                        break;
                    case 2:
                        enemies.add(new defultEm(i, e, s, w, genName(), p));
                        break;
                    default:
                        break;
                }
            }
        }

        for(int i=0;i<gem.size();i++){
            gems.add(gem.getJSONArray(i).getIntArray());
        }

        for(int i=0;i<door.size();i++){
            doors.add(door.getJSONObject(i));
        }
    }

    private String genName() {
 
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
     
        return generatedString;
    }


}