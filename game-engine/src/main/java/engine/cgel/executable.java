package engine.cgel;

import java.util.HashMap;

abstract class executable{
    abstract variable execute(HashMap<String,variable> var, cgel L);
}