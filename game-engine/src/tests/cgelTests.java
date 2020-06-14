package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import engine.cgel.cgel;
import engine.cgel.script;
import engine.cgel.variable;
import engine.editor.editor;
import processing.core.PApplet;


@DisplayName("CGLE test Cases")
class cgelTests {

    @DisplayName("loadscriptFail")
    @Test
    public void loadscriptFail() {
        editor e = new editor();
        String[] args = {
            "arg"
        };
        PApplet.runSketch(args, e);
        cgel s = new cgel(e);
        script load = s.loadScript("notafile");
        e.exit();
        assertTrue(load == null);
    }
    @DisplayName("loadscriptPass")
    @Test
    public void loadscriptass() {
        editor e = new editor();
        String[] args = {
            "arg"
        };
        PApplet.runSketch(args, e);
        cgel s = new cgel(e);
        script load = s.loadScript("./data/testscript.cgel");
        System.out.print(load);


        e.exit();
        assertTrue(load != null);
    }

    @DisplayName("splitting functions")
    @Test
    public void scriptFunctionSplit() {
        editor e = new editor();
        String[] args = {
            "arg"
        };
        PApplet.runSketch(args, e);
        cgel s = new cgel(e);
        script load = s.loadScript("./data/testscript.cgel");

        e.exit();
        PApplet.printArray(load.getMethods());
        System.out.println("no");
        assertTrue(load.getMethods().length>0);

    }

    @DisplayName("making varables")
    @Test
    public void variableInits() {
        editor e = new editor();
        String[] args = {
            "arg"
        };
        PApplet.runSketch(args, e);
        cgel s = new cgel(e);
        script load = s.loadScript("./data/testscript.cgel");
        
        e.exit();
        PApplet.printArray(load.getMethods());
        HashMap<String,variable> vars = new HashMap<>();
        load.runMethod("init", vars);
        HashMap<String,variable> correct = new HashMap<>();
        correct.put("x",new variable(10));
        correct.put("y",new variable(5));
        correct.put("z",new variable(-10));
        correct.put("test",new variable("\"stringtest\""));
        correct.put("floatTest",new variable(1.3));
        correct.put("floatTestN",new variable(-1.3));
        correct.put("BooltestTr",new variable(true));
        correct.put("BooltestFa",new variable(false));
        correct.put("SpaceString",new variable("\"test String with a space\""));

        System.out.println(vars);
        System.out.println(correct);

        assertTrue(correct.equals(vars));
    }

    @DisplayName("doing math")
    @Test
    public void variableMath() {
        editor e = new editor();
        String[] args = {
            "arg"
        };
        PApplet.runSketch(args, e);
        cgel s = new cgel(e);
        script load = s.loadScript("./data/testscript.cgel");
        
        e.exit();
        PApplet.printArray(load.getMethods());
        HashMap<String,variable> vars = new HashMap<>();
        load.runMethod("init", vars);
        load.runMethod("mathTest", vars);
        
        HashMap<String,variable> correct = new HashMap<>();
        correct.put("x",new variable(5));
        correct.put("y",new variable(-8));
        correct.put("z",new variable(-10));
        correct.put("test",new variable("\"stringtest\""));
        correct.put("floatTest",new variable(0.65));
        correct.put("floatTestN",new variable(-1.3));
        correct.put("BooltestTr",new variable(true));
        correct.put("BooltestFa",new variable(false));
        correct.put("SpaceString",new variable("\"test String with a space\""));

        System.out.println(vars);
        System.out.println(correct);
        assertTrue(correct.equals(vars));
    }


}