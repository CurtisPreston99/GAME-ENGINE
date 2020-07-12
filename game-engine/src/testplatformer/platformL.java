package testplatformer;

import processing.data.JSONObject;

import java.util.ArrayList;

import processing.data.JSONArray;

public class platformL {
    int[][] level;
    ArrayList<int[]> gems= new ArrayList<int[]>();
    ArrayList<JSONObject> doors= new ArrayList<JSONObject>();
    ArrayList<defultEm> enemies = new ArrayList<defultEm>(); 

    public platformL(JSONObject l){
        JSONArray lev= l.getJSONArray("map");
        JSONArray gem= l.getJSONArray("gems");
        JSONArray door= l.getJSONArray("doors");
        
        level= new int[lev.size()][lev.getJSONArray(0).size()];
        
        
        for(int i=0;i<lev.size();i++){
            JSONArray row=lev.getJSONArray(i);
            for(int e=0;e<row.size();e++){
                
                level[i][e]=row.getInt(e);
            }
        }

        for(int i=0;i<gem.size();i++){
            gems.add(gem.getJSONArray(i).getIntArray());
        }

        for(int i=0;i<door.size();i++){
            doors.add(door.getJSONObject(i));
        }
    }


}