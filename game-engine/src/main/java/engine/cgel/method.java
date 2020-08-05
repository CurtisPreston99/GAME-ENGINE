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
        int nameEnd = meth.indexOf("(");
        methodName = meth.substring(0, nameEnd);

        // remove start and end brackets and remove comments
        meth = meth.substring(nameEnd + 3, meth.length() - 1).replaceAll("//(.*?)//", "");
        // split by line/command
        methodLines = meth.split(";");
    }

    public String getName() {
        return methodName;
    }

    variable run(HashMap<String, variable> var, cgel cg){
        return null;

    }

    public variable evaluate(String line,HashMap<String, variable> var, cgel cg){
        String[] math = line.split("(?<=[-+*/])|(?=[-+*/])");
        if (math.length == 1 || (math[0].matches("-") && math.length == 2)) {//if single opritave

            if(var.get(math[0])!=null){//if var
                return new variable(var.get(math[0]));// set variable
            }

            if(math[0].matches("-") && var.get(math[1])!=null){//if -var
                return new variable(var.get(math[0]));
            }
            if (line.matches("-?[0-9]+")) {//if int
                return new variable(Integer.parseInt(line));//so that it doesnt get counted as another type
            }
            if (line.matches("[-+]?[0-9]*\\.?[0-9]+")) {//if float
                return new variable(Float.parseFloat(line));
            }
            if (line.matches("\".*?\"")) {//if string
                return new variable(math[0]);
            }
            if (line.equals("False")) {//if false boolean
                return new variable(false);
            }
            if (line.equals("True")) {//if true boolean
                return new variable(true);
            }
        }else{
        // isnt just setting and needs to work out value
            // substituting variable names with values;
            for(int i=0;i<math.length;i++){
                String st = math[i];
                // System.out.println(s);
                if(var.get(st)!=null){//if var
                    math[i]=var.get(st).ValString();
                }
                if(st.matches("^-?[0-9][0-9,.]+$")){
                    // System.out.println("number");
                    
                }
                if(st.matches("[-+*/]")){
                    // System.out.println("mathfunction");
                }
                if(st.matches("[a-zA-Z][a-zA-Z0-9]+\\((.)*\\)")){
                    variable tmp=s.runMethod(st.substring(0,st.length()-2), var);
                    System.out.println(tmp);
                    math[i]=tmp.ValString();

                    
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
                return new variable((int)Math.ceil(out));

            }else{
                return new variable(e.evaluate());
            }
        }
        return new variable(-1,null);
    }

    public variable executeLine(String line,HashMap<String, variable> var, cgel cg) {
        if (line.matches(".*?=.*")) {// if setting a variable in format name=value or name=equation

            String[] parts = line.split("=");// split name from setting part
            variable c =evaluate(parts[1],var,cg);
            var.put(parts[0],c);
            // if isnt setting value 
        }else{
            if(line.matches("[a-zA-Z][a-zA-Z0-9]+((.)*)")){
                // is a return statement\

                if(line.matches("return((.)*)")){
                    System.out.println("true");
                    variable v= evaluate(line.subSequence(7, line.length()-1).toString(), var, cg);
                    v.returnS=true;
                    return v;
                }
            }
        }
        return new variable(-1,null);
        
    }


    @Override
    public variable execute(HashMap<String, variable> var, cgel cg) {
        for (String line : methodLines) {
            variable c =executeLine(line,var,cg);
            if(c.returnS){
                c.returnS=false;
                return c;
            }
        }
        return new variable(-1,null);
    }


    void setValue(HashMap<String, variable> var,String name){

    }
}
