package engine.cgel;

import java.util.regex.Pattern;

public class variable {
    int type;
    Object Value;
    public variable(int type,Object value){
        this.type=type;
        this.Value=value;
    };
    public variable(int obj){
        this.type=0;
        this.Value=obj;
    };
    public variable(float obj){
        this.type=1;
        this.Value=obj;
    };
    public variable(String obj){
        if (obj.matches("-?[0-9]+")) {
            this.type=0;
            this.Value=Integer.parseInt(obj);
            return;
        }
        if(obj.matches("[-+]?[0-9]*\\.?[0-9]+")){
            this.type=1;
            this.Value=Float.parseFloat(obj);
            return;
        }
        if(obj.matches("\".*?\"")){
        
            this.type=2;
            this.Value=obj;
            return;
        }
        if(obj.equals("False")){
            this.type=3;
            this.Value=false;
            return;

        }
        if(obj.equals("True")){
            this.type=3;
            this.Value=true;
            return;

        }
    };
    public variable(Boolean obj){
        this.type=3;
        this.Value=obj;
    };
    public variable(double d) {
        this((float)d);
	}
	public Boolean isInt(){
        return type==0;
    }
    public Boolean isFloat(){
        return type==1;
    }
    public Boolean isString(){
        return type==2;
    }
    public Boolean isBoolean(){
        return type==3;
    }
    public Object getobj(){
        return Value;
    }

    public boolean equals(Object arg0) {
        try{
            variable v = (variable)arg0;
            if(v.type==this.type){
                switch (this.type) {
                    case 0:
                        return (int)v.Value==(int)this.Value;
                    case 1:
                        return (float)v.Value==(float)this.Value;
                    case 2:
                        return ((String)v.Value).equals((String)this.Value);
                    case 3:
                        return (boolean)v.Value==(boolean)this.Value;
                    default:
                        return false;
                }
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public String toString(){
        switch (type) {
            case 0:
                return "int:"+((int)Value);
            case 1:
                return "float:"+((float)Value);
            case 2:
                return "string:"+((String)Value);
            case 3:
                return "Boolean:"+((boolean)Value);
            default:
                return "cant find type";
        }
    }
}