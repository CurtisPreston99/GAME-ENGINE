package engine.cgel;

import java.util.HashMap;

import engine.cgel.executable;

class method extends executable {
    String methodName;
    String[] methodLines;

    public method(String meth) {
        meth = meth.replaceAll("\\s+", "");
        // get name
        int nameEnd = meth.indexOf("{");
        methodName = meth.substring(0, nameEnd);

        //remove start and end brackets and remove comments 
        meth = meth.substring(nameEnd+1, meth.length()-1).replaceAll("//(.*?)//", "");
        //split by line/command
        methodLines=meth.split(";");
    }

    public String getName(){
        return methodName;
    }

    @Override
    int execute(HashMap<String, variable> var,cgel cg) {
        // TODO Auto-generated method stub
        for(String line:methodLines){
            if(line.matches(".*?=.*")){
                System.out.println(line);
                
                String[] parts=line.split("=");
                var.put(parts[0], new variable(parts[1]));
                System.out.println(parts[1]);
            }
        }
        return 0;
    }
}