package src;

public class Symbol {
    private String name; // name, jaisay 'x' ya 'SumUpTo'.
    private String type; // type, jaisay 'int' ya 'float'.

    public Symbol(String name, String type){
        this.name = name;  //this.name is box where we will store new name. same for type
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    @Override 
    public String toString(){
        return name + ":" + type;
    }
}
