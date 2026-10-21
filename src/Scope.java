package src;

import java.util.Map; // comment nechay.
import java.util.LinkedHashMap; //Java ke build in chezain istamal krnay kay liyay use hota.
import java.util.List;
import java.util.ArrayList;

public class Scope {
    private Scope enclosingScope; // parent ka rasta
    private Map<String, Symbol> symbols = new LinkedHashMap<String, Symbol>(); // dictionary ban rahi hay new jisay ham name day kr daba bnwatay hain symbol.
    private List<Scope> children = new ArrayList<Scope>();
    private String label = "Scope";

    public Scope(Scope enclosingScope){ // Scope constructor mein, humein EK (bara) Scope diya jata hai, jiska naam newParent hai. Hum us newParent ko apne enclosingScope khaanay mein RAKH lete hain. Phir hum CHECK karte hain — 'agar khaali NAHI hai, toh us newParent ki children list mein khud ko add kar dete hain.
        this.enclosingScope = enclosingScope;//Haan — "constructor jo naam (parent) leta hai, usay pehle STORE karta hai. Phir check karta hai — agar khaali NAHI hai, toh children list mein add kar deta hai. Agar khaali hai, toh kuch nahi karta."
        if (enclosingScope != null ) 
        {
            enclosingScope.children.add(this);
        }
    }

    public Scope getEnclosingScope(){ // bara parent scope wapis deta hay
        return enclosingScope;
    }

    public void setLabel(String label) {
    this.label = label;
    }

    public boolean define(Symbol sym)
    { /////// jo check krta bhai phly hay ya nhi is scope may yeh variable.
        if (symbols.containsKey(sym.getName()))
        {
            System.out.println("ERROR:" + sym.getName() + "'Already Declared in this Scope'");
            return false;
        }
        else
        {
            symbols.put(sym.getName(), sym);
            return true;
        }
    }

    public Symbol resolve(String name) // dekhna kay idr hay ya nhi yeh name symbols may or phir baray kay pass check krna...
    {
        Symbol sym = symbols.get(name);
        if (sym != null)
        {
            return sym;
        }
        if (enclosingScope != null)
        {
            return enclosingScope.resolve(name);
        }
        return null;
    }

   public void print(String indent){
    System.out.println(indent +  "-" + label);
    for(Symbol sym : symbols.values()){
        System.out.println(indent + "    " + sym.getName() + " : " + sym.getType());
    }
    for (Scope child : children){
        child.print(indent + "  ");
    }
    }

    @Override 
    public String toString(){
        return symbols.toString();
    }

}
