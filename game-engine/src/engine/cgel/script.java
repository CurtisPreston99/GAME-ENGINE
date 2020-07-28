package engine.cgel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import engine.loader;

public class script {
    String File;
    cgel STDfunctions;
    HashMap<String,method> methods= new HashMap<>();
    @Override
    public String toString(){
        return File;
    }
    
    script(String file, cgel l) throws Exception {
            String f = l.l.loadTxt(file);
            File=file;
            parseScript(f);
    }
    script(String script) {
        parseScript(script);
    }

    private void parseScript(String s){
        String[] methodStrings=s.split("function");
        for(String i : methodStrings){
            if(i.trim().length() > 0){
                method methTmp = new method(i,this);
                methods.put(methTmp.getName(), methTmp);
            }
        }
    }

    public String[] getMethods(){
        java.util.Iterator<String> methodset = methods.keySet().iterator();
        String[] methodArray=new String[methods.keySet().size()];
        int i=0;
        while(methodset.hasNext()){
            methodArray[i]=methodset.next();
            i++;
        }
        return methodArray;
    }

    public variable runMethod(String name,HashMap<String,variable>  var){
        variable r = methods.get(name).execute(var, STDfunctions);
        return r;
        
    }
}