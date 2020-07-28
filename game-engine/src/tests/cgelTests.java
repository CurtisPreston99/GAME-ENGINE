package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import engine.cgel.cgel;
import engine.cgel.script;
import engine.cgel.variable;
import engine.editor.editor;
import processing.core.PApplet;


class cgelTests {
    editor e;
    cgel s;

    @BeforeEach
    public void initObjects() {
        e = new editor();
        String[] args = {"arg"};
        PApplet.runSketch(args, e);
        s=new cgel(e);
    }


    @After
    public void removeObj() {
        e.exit();
    }

    @DisplayName("loadscriptFail")
    @Test
    public void loadscriptFail() {
        script load = s.loadScript("notafile");
        assertTrue(load == null);
    }
    @DisplayName("loadscriptPass")
    @Test
    public void loadscriptass() {
        script load = s.loadScript("./data/testscript.cgel");
        // System.out.print(load);

        assertTrue(load != null);
    }

    @DisplayName("splitting functions")
    @Test
    public void scriptFunctionSplit() {
        script load = s.loadScript("./data/testscript.cgel");

        // System.out.println("no");
        assertTrue(load.getMethods().length>0);

    }

    @DisplayName("making varables")
    @Test
    public void variableInits() {
        script load = s.loadScript("./data/testscript.cgel");
        
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

        // System.out.println(vars);
        // System.out.println(correct);

        assertTrue(correct.equals(vars));
    }

    @DisplayName("doing math")
    @Test
    public void variableMath() {
        script load = s.loadScript("./data/testscript.cgel");
        
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


    @DisplayName("function return variable")
    @Test
    public void returnVar() {
        script load = s.loadScript("./data/testscript.cgel");
        
        HashMap<String,variable> vars = new HashMap<>();
        variable r= load.runMethod("returnTest", vars);
        System.out.println(r);

        assertTrue(r.equals(10));
    }

    @DisplayName("function return variable in equations")
    @Test
    public void funcReturnMath() {
        script load = s.loadScript("./data/testscript.cgel");
        
        HashMap<String,variable> vars = new HashMap<>();
        variable r= load.runMethod("returnMathTest", vars);
        System.out.println(vars);

        assertTrue(vars.get("x").equals(20));
    }


}