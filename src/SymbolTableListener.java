package src;

import generated.OFPBaseListener;
import generated.OFPParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class SymbolTableListener extends OFPBaseListener {
    private Scope currentScope; // abhi ham kis scope may hain track krta hay.
    private Scope globalScope; // Change nhi hoga fix rahay ga takah pora tree print ho or daikh sakain.
    private FunctionSymbol currentFunction;
    private ParseTreeProperty<Scope> scopes = new ParseTreeProperty<Scope>();
    
    private int errorCount = 0;
    public int getErrorCount(){
        return errorCount;
    }
    

    @Override
    public void enterProgram(OFPParser.ProgramContext ctx){
        globalScope = new Scope(null);
        globalScope.setLabel("global");
        currentScope = globalScope;
        System.out.println("Global Scope Created");
    }

    @Override 
    public void enterFunctionDeclare(OFPParser.FunctionDeclareContext ctx){
        String functionName = ctx.ID().getText();
        String returnType = ctx.getChild(0).getText();

        if (ctx.getChild(1) != null && ctx.getChild(1).getText().equals("[")){
            returnType = returnType + "[]";
        }

        FunctionSymbol funcSym = new FunctionSymbol(functionName, returnType);
        if(!currentScope.define(funcSym)){
            errorCount++;
        }
        currentFunction = funcSym;

        currentScope = new Scope(currentScope);
        currentScope.setLabel("function " + functionName);
        scopes.put(ctx , currentScope);

        System.out.println("Function Founded: " + functionName + "(type: " + returnType + ")");
    }

    @Override 
    public void exitFunctionDeclare(OFPParser.FunctionDeclareContext ctx){
        currentScope = currentScope.getEnclosingScope();
    }

    @Override 
    public void enterMaindeclaration(OFPParser.MaindeclarationContext ctx
    )
    {
        FunctionSymbol mainSym = new FunctionSymbol("main", "void");
        if(!currentScope.define(mainSym)){
            errorCount++;
        }
        currentFunction = mainSym;

        currentScope = new Scope(currentScope);
        currentScope.setLabel("function main");
        scopes.put(ctx, currentScope);

        System.out.println("Main Function Founded");
    }

    @Override 
    public void exitMaindeclaration(OFPParser.MaindeclarationContext ctx)
    {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override 
    public void enterVariableDeclare(OFPParser.VariableDeclareContext ctx)
    {
        String varName = ctx.ID().getText();
        String varType = ctx.getChild(0).getText();

        if (ctx.getText().contains("[]")){
            varType = varType + "[]";
        }

        Symbol varSym = new Symbol(varName, varType);
        if(!currentScope.define(varSym)){
            errorCount++;
        }

        System.out.println("Variable Founded: " + varName + "(type: " + varType + ")");
    }

    @Override 
    public void enterIfandWhileHelpers(OFPParser.IfandWhileHelpersContext ctx){
        currentScope = new Scope(currentScope);
        currentScope.setLabel("block");
        scopes.put(ctx, currentScope);
    }

    @Override 
    public void exitIfandWhileHelpers(OFPParser.IfandWhileHelpersContext ctx){
        currentScope = currentScope.getEnclosingScope();
    }

    @Override 
    public void enterSingleparameter(OFPParser.SingleparameterContext ctx){
        String paramName = ctx.ID().getText();
        String paramType = ctx.getChild(0).getText();

        if (ctx.getText().contains("[]")){
            paramType = paramType + "[]";
        }

        Symbol paramSym = new Symbol(paramName, paramType);
        currentFunction.addParameter(paramSym);
        if(!currentScope.define(paramSym)){
            errorCount++;
        }

        System.out.println("Parameter Founded: " + paramName + "(type: " + paramType + ")");
    }

    public Scope getGlobalScope(){
        return globalScope;
    }

    public ParseTreeProperty<Scope> getScopes(){
        return scopes;
    }

}