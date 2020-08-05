package engine.cgel;

import java.util.HashMap;

import engine.loader;
import engine.window;

public class cgel {
    HashMap<String,function> functions= new HashMap<String,function>();
    loader l;

    public cgel(window w) {
        this.l = w.Loader;
    }

    public script loadScript(String fname) {
        try {
            return new script(fname, this);
        } catch (Exception e) {
            return null;
        }
    }
}