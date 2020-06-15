package engine.ui;

import java.util.HashMap;

public class UItheme {


    
    private static UItheme single_instance=null; 

    public int c_very_dark;
    public int c_dark;
    public int c_mid;
    public int c_light;
    public int c_hover;
    public int c_text_color;


    public static HashMap<String, Integer> uiDark = new HashMap<String, Integer>(){{
		put("c_very_dark",-14408402);
		put("c_dark", -14868180);
		put("c_mid", -13878713);
		put("c_light",-13418416);
		put("c_hover", -14640224);
		put("c_text_color",-1);
    }};

    public static HashMap<String, Integer> uiLight = new HashMap<String, Integer>(){{
		put("c_very_dark", -10197916);
		put("c_dark", -6908266);
		put("c_mid", -3618616);
		put("c_light", -328966);
		put("c_hover", -14640224);
		put("c_text_color", -16119286);
    }};
    

    private UItheme(){
        c_very_dark=uiDark.get("c_very_dark");
        c_mid=uiDark.get("c_mid");
        c_light=uiDark.get("c_light");
        c_hover=uiDark.get("c_hover");
        c_text_color=uiDark.get("c_text_color");
        c_dark=uiDark.get("c_dark");

    }


    public boolean setTheme(HashMap<String, Integer> newtheme){
        try {
            c_very_dark=newtheme.get("c_very_dark");
            c_mid=newtheme.get("c_mid");
            c_light=newtheme.get("c_light");
            c_hover=newtheme.get("c_hover");
            c_text_color=newtheme.get("c_text_color");
            c_dark=newtheme.get("c_dark");
        } catch (Exception e) {
            return false;
        }
        return true;
    }



    public void setDark(){
        setTheme(uiDark);
    }

    public void setLight(){
        setTheme(uiLight);
    }
    

    public static UItheme Singleton() 
    { 
        // To ensure only one instance is created 
        if (single_instance == null) 
        { 
            single_instance = new UItheme(); 
        } 
        return single_instance; 
    }

    
}