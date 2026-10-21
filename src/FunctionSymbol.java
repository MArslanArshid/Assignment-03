package src;

import java.util.List;
import java.util.ArrayList;

public class FunctionSymbol extends Symbol{
    private List<Symbol> parameters = new ArrayList<Symbol>();

public FunctionSymbol(String name, String type){
    super(name, type);
}

public void addParameter(Symbol param){
    parameters.add(param);
}

public List<Symbol> getParameters(){
    return parameters;
}
}
