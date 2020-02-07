package engine.cgel;

import java.util.HashMap;

abstract class executable{
    abstract int execute(HashMap<String,variable> var, cgel L);
}