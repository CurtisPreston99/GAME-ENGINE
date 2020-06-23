package engine.cgel;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.script.*;
import engine.cgel.executable;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

class method extends executable {
    String methodName;
    String[] methodLines;
    script s;
    public method(String meth,script s) {
        this.s=s;
        //remove white space
        meth = meth.replaceAll("\\s+(?=((\\\\[\\\\\"]|[^\\\\\"])*\"(\\\\[\\\\\"]|[^\\\\\"])*\")*(\\\\[\\\\\"]|[^\\\\\"])*$)", "");
        // get name
        int nameEnd = meth.indexOf("{");
        methodName = meth.substring(0, nameEnd);

        // remove start and end brackets and remove comments
        meth = meth.substring(nameEnd + 1, meth.length() - 1).replaceAll("//(.*?)//", "");
        // split by line/command
        methodLines = meth.split(";");
    }

    public String getName() {
        return methodName;
    }

    variable run(HashMap<String, variable> var, cgel cg){
        return null;

    }

    @Override
    int execute(HashMap<String, variable> var, cgel cg) {
        for (String line : methodLines) {
            if (line.matches(".*?=.*")) {// if setting a variable in format name=value or name=equation

                String[] parts = line.split("=");// split name from setting part
                String[] math = parts[1].split("(?<=[-+*/])|(?=[-+*/])");

                if (math.length == 1 || (math[0].matches("-") && math.length == 2)) {

                    if(var.get(math[0])!=null){//if var
                        var.put(parts[0], new variable(var.get(math[0])));// set variable
                        continue;
                    }

                    if(math[0].matches("-") && var.get(math[1])!=null){//if -var
                        var.put(parts[0], new variable(var.get(math[0])));// set variable
                        continue;
                    }
                    if (parts[1].matches("-?[0-9]+")) {//if int
                        var.put(parts[0], new variable(Integer.parseInt(parts[1])));// set variable
                        continue;//so that it doesnt get counted as another type
                    }
                    if (parts[1].matches("[-+]?[0-9]*\\.?[0-9]+")) {//if float
                        var.put(parts[0], new variable(Float.parseFloat(parts[1])));// set variable
                        continue;
                    }
                    if (parts[1].matches("\".*?\"")) {//if string
                        var.put(parts[0], new variable(math[0]));// set variable
                        continue;
                    }
                    if (parts[1].equals("False")) {//if false boolean
                        var.put(parts[0], new variable(false));
                        continue;
                    }
                    if (parts[1].equals("True")) {//if true boolean
                        var.put(parts[0], new variable(true));
                        continue;
                    }
                }else{
                // isnt just setting and needs to work out value
                    System.out.println("math line");
                    System.out.println(Arrays.toString(math));
                    // substituting variable names with values;
                    for(int i=0;i<math.length;i++){
                        String s = math[i];
                        System.out.println(s);
                        if(var.get(s)!=null){//if var
                            math[i]=var.get(s).ValString();
                        }
                        if(s.matches("^-?[0-9][0-9,.]+$")){
                            System.out.println("number");
                            
                        }
                        if(s.matches("[-+*/]")){
                            System.out.println("mathfunction");
                        }
                        if(s.matches(".*?/(.*?/)")){
                            System.out.println("functioncall");
                        }
                    }
                    String eq="";
                    for(int i=0;i<math.length;i++){
                        eq+=math[i];
                    }
                    System.out.println(Arrays.toString(math));
                    System.out.println(eq);

                    System.out.println("out1");
                    Expression e = new ExpressionBuilder(eq).build();
                    // System.out.println(e.evaluate());
                    double out=e.evaluate();
                    if(out==Math.ceil(out)){
                        var.put(parts[0], new variable((int)Math.ceil(out)));

                    }else{
                        var.put(parts[0], new variable(e.evaluate()));
                    }
                }
            }
        }
        return 0;
    }


    void setValue(HashMap<String, variable> var,String name){

    }
}
