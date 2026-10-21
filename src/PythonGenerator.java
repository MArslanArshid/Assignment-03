package src;

import generated.OFPBaseVisitor;
import generated.OFPParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class PythonGenerator extends OFPBaseVisitor<String> {

    private int depth = 0;

    public PythonGenerator(ParseTreeProperty<Scope> scopes) {
        
    }

    /////////////////////----------Safety check for reserved keywords in Python----------///////////////////////// used in visitIdExpr.

    private static java.util.HashSet<String> reservedIds = new java.util.HashSet<>(java.util.Arrays.asList(
    "False", "None", "True", "and", "as", "assert", "async", "await", "break",
    "class", "continue", "def", "del", "elif", "else", "except", "finally",
    "for", "from", "global", "if", "import", "in", "is", "lambda", "nonlocal",
    "not", "or", "pass", "raise", "return", "try", "while", "with", "yield",
    "print", "len", "sum", "str", "int", "float", "bool", "list", "range",
    "input", "type", "max", "min", "abs", "sorted", "open", "id", "dir"
    ));

    private static String getSafePythonId(String id) {
    if (reservedIds.contains(id)) {
        return "ofp_" + id;
    }
    return id;
}


///////////////////////-----------For indentation in Python, we need to keep track of the current depth of nested blocks. Each level of depth corresponds to 4 spaces of indentation. The `indent()` method generates the appropriate number of spaces based on the current depth. used in.
private String indent() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < depth; i++) {
        sb.append("    ");  // 4 spaces per level
    }
    return sb.toString();
}



    @Override //01 //int x = 42; //Is mein 42 wala node IntLitExprContext hay.
    public String visitIntLitExpr(OFPParser.IntLitExprContext ctx) {
    return ctx.getText();
}

    @Override //02 //float pi = 3.14; //3.14 wala node FloatLitExprContext hai.
    public String visitFloatLitExpr(OFPParser.FloatLitExprContext ctx) {
    return ctx.getText();
}

    @Override //03 //bool flag = true; //true wala node BoolLitExprContext hai.
    public String visitBoolLitExpr(OFPParser.BoolLitExprContext ctx) {
    String text = ctx.getText();  // "true" ya "false"
    if (text.equals("true")) {
        return "True";
    } else {
        return "False";
    }
}


    @Override //04 //string name = "John"; // "John" wala node StringLitExprContext hai.
    public String visitStringLitExpr(OFPParser.StringLitExprContext ctx) {
    return ctx.getText();
}


    @Override //05 //char c = 'A'; //Python mein char naam ka koi alag type hota hi nahi — single-quote wali cheez Python mein khud hi ek chota sa string samjha jata hai. Isliye seedha copy kaam kar jata hai, Python isko valid samjhega.
    public String visitCharLitExpr(OFPParser.CharLitExprContext ctx) {
    return ctx.getText();
}

/////////////////////////--------------------05 literal expressions complete--------------------/////////////////////////.

    @Override
    public String visitIdExpr(OFPParser.IdExprContext ctx) {
    String name = ctx.getText();
    return getSafePythonId(name);
}


    @Override // paran may brackets preserve krtay hain and andr jo bhi add mul us par visitadd ya mul chalta or return hamay 2+3 example kaya tor par hota.
    public String visitParanExpr(OFPParser.ParanExprContext ctx) {
    String inner = visit(ctx.expr());
    return "(" + inner + ")";
}


    @Override // -x → visit(ctx.expr()) → visitIdExpr chalega (agar x simple variable hai) → return "x" (ya renamed version agar reserved ho) → fir "-" + "x" = "-x"
    public String visitNegExpr(OFPParser.NegExprContext ctx) {
    String inner = visit(ctx.expr());
    return "-" + inner;
}


/////////////////////////////------------Binary expressions (addition, subtraction, multiplication, division, relational)------------/////////////////////////

    @Override
    public String visitAddSubExpr(OFPParser.AddSubExprContext ctx) {
    String left = visit(ctx.expr(0));
    String right = visit(ctx.expr(1));
    String op = ctx.getChild(1).getText();  // "+" ya "-"
    return left + " " + op + " " + right;
}


    @Override
    public String visitMulDivExpr(OFPParser.MulDivExprContext ctx) {
    String left = visit(ctx.expr(0));
    String right = visit(ctx.expr(1));
    String op = ctx.getChild(1).getText();  // "*" ya "/"
    return left + " " + op + " " + right;
}


    @Override
    public String visitRelEqExpr(OFPParser.RelEqExprContext ctx) {
    String left = visit(ctx.expr(0));
    String right = visit(ctx.expr(1));
    String op = ctx.getChild(1).getText();  // "<", ">", ya "=="
    return left + " " + op + " " + right;
}



////////////////////////////------------Array access expression------------///////////////////////// 
// exmaple: visit(ctx.expr(0)) → visitIdExpr → "arr" 
// visit(ctx.expr(1)) → visitIdExpr → "i" 
// Final: "arr" + "[" + "i" + "]" = "arr[i]"

    @Override
    public String visitArrayAccessExpr(OFPParser.ArrayAccessExprContext ctx) {
    String base = visit(ctx.expr(0));
    String index = visit(ctx.expr(1));
    return base + "[" + index + "]";
}



/////////Python may .length() function nahi hota, is liye len() function use karna parta hay. to ham expression visit kr kay expression ko base may dalian gay or len may rap krdain gay exmaple : OFP: i < a.length (RelEqExpr ke andar) →
//  expr(0) = i → "i" 
// expr(1) = a.length → visitLengthExpr chalega → "len(a)" Final: "i < len(a)"

    @Override
    public String visitLengthExpr(OFPParser.LengthExprContext ctx) {
    String base = visit(ctx.expr());
    return "len(" + base + ")";
}


/////////////////FuncallExpression sum(a,b) bhi ho skta hay or len() sada bhi ho skta hay. because grammer may ? laga wa tha function = id (argumentlist)? jis may argument thy : expr(,expr)*.

@Override
public String visitFunCallExpr(OFPParser.FunCallExprContext ctx) {
    OFPParser.SumFunctioncallContext callCtx = ctx.sumFunctioncall();
    String funcName = getSafePythonId(callCtx.ID().getText());

    StringBuilder args = new StringBuilder();
    if (callCtx.argumentList() != null) {
        java.util.List<OFPParser.ExprContext> exprs = callCtx.argumentList().expr();
        for (int i = 0; i < exprs.size(); i++) {
            if (i > 0) {
                args.append(", ");
            }
            args.append(visit(exprs.get(i)));
        }
    }

    return funcName + "(" + args + ")";
}



@Override //Arrays in OFP are replaced with lists in Python
public String visitArrayLitExpr(OFPParser.ArrayLitExprContext ctx) {
    OFPParser.ArrayLiteralContext litCtx = ctx.arrayLiteral();
    java.util.List<OFPParser.ExprContext> exprs = litCtx.argumentList().expr();

    StringBuilder elements = new StringBuilder();
    for (int i = 0; i < exprs.size(); i++) {
        if (i > 0) {
            elements.append(", ");
        }
        elements.append(visit(exprs.get(i)));
    }

    return "[" + elements + "]";
}



@Override
public String visitNewArrayExpr(OFPParser.NewArrayExprContext ctx) {
    OFPParser.ArraywithnewkeywordContext newCtx = ctx.arraywithnewkeyword();
    String type = newCtx.getChild(1).getText();  // "int", "float", ya "char"
    String size = visit(newCtx.expr());

    String defaultValue;
    if (type.equals("int")) {
        defaultValue = "0";
    } else if (type.equals("float")) {
        defaultValue = "0.0";
    } else {
        defaultValue = "''";
    }

    return "[" + defaultValue + "] * (" + size + ")";
}



////////////////////////----------------From here Statements start----------------/////////////////////////



@Override
public String visitAssignStmt(OFPParser.AssignStmtContext ctx) {
    OFPParser.AssignContext assignCtx = ctx.assign();
    String varName = getSafePythonId(assignCtx.ID().getText());

    if (assignCtx.expr().size() == 2) {
        // Case: arr[0] = 5;
        String index = visit(assignCtx.expr(0));
        String value = visit(assignCtx.expr(1));
        return indent() + varName + "[" + index + "] = " + value + "\n";
    } else {
        // Case: x = 5;
        String value = visit(assignCtx.expr(0));
        return indent() + varName + " = " + value + "\n";
    }
}



@Override
public String visitPrintStmt(OFPParser.PrintStmtContext ctx) {
    String keyword = ctx.getChild(0).getText();  // "print" ya "println"
    String value = visit(ctx.expr());

    if (keyword.equals("println")) {
        return indent() + "print(" + value + ")\n";
    } else {
        return indent() + "print(" + value + ", end='')\n";
    }
}



@Override
public String visitVarDeclStmt(OFPParser.VarDeclStmtContext ctx) {
    OFPParser.VariableDeclareContext varCtx = ctx.variableDeclare();
    String varName = getSafePythonId(varCtx.ID().getText());

    if (varCtx.expr() != null) {
        // Case: int x = 5;
        String value = visit(varCtx.expr());
        return indent() + varName + " = " + value + "\n";
    } else {
        // Case: int x;  (no initializer, give a default)
        String type = varCtx.getChild(0).getText();
        String defaultValue;
        if (type.equals("int")) {
            defaultValue = "0";
        } else if (type.equals("float")) {
            defaultValue = "0.0";
        } else if (type.equals("bool")) {
            defaultValue = "False";
        } else {
            defaultValue = "''";
        }
        return indent() + varName + " = " + defaultValue + "\n";
    }
}



//////////////////////-------------------------Helper method for block----////////////////////////////////////////.



private String visitStmtList(java.util.List<OFPParser.StmtContext> stmts) {
    depth++;
    StringBuilder sb = new StringBuilder();

    if (stmts.isEmpty()) {
        sb.append(indent()).append("pass\n");
    } else {
        for (OFPParser.StmtContext s : stmts) {
            sb.append(visit(s));
        }
    }

    depth--;
    return sb.toString();
}

private String visitBlock(OFPParser.IfandWhileHelpersContext ctx) {
    return visitStmtList(ctx.stmt());
}



@Override
public String visitWhileStmt(OFPParser.WhileStmtContext ctx) {
    OFPParser.WhileStatementContext whileCtx = ctx.whileStatement();
    String cond = visit(whileCtx.expr());
    String header = indent() + "while " + cond + ":\n";
    String body = visitBlock(whileCtx.ifandWhileHelpers());
    return header + body;
}



@Override
public String visitIfStmt(OFPParser.IfStmtContext ctx) {
    OFPParser.IfStatementContext ifCtx = ctx.ifStatement();
    String cond = visit(ifCtx.expr());
    String header = indent() + "if " + cond + ":\n";
    String thenBody = visitBlock(ifCtx.ifandWhileHelpers(0));

    String result = header + thenBody;

    if (ifCtx.ifandWhileHelpers().size() == 2) {
        result += indent() + "else:\n";
        result += visitBlock(ifCtx.ifandWhileHelpers(1));
    }

    return result;
}



@Override
public String visitReturnStmt(OFPParser.ReturnStmtContext ctx) {
    OFPParser.ReturnStatementContext retCtx = ctx.returnStatement();
    String value = visit(retCtx.expr());
    return indent() + "return " + value + "\n";
}



@Override
public String visitCallStmt(OFPParser.CallStmtContext ctx) {
    OFPParser.CallstatementContext callCtx = ctx.callstatement();
    String funcName = getSafePythonId(callCtx.ID().getText());

    StringBuilder args = new StringBuilder();
    if (callCtx.argumentList() != null) {
        java.util.List<OFPParser.ExprContext> exprs = callCtx.argumentList().expr();
        for (int i = 0; i < exprs.size(); i++) {
            if (i > 0) {
                args.append(", ");
            }
            args.append(visit(exprs.get(i)));
        }
    }

    return indent() + funcName + "(" + args + ")\n";
}



@Override
public String visitFunctionDeclare(OFPParser.FunctionDeclareContext ctx) {
    String funcName = getSafePythonId(ctx.ID().getText());

    StringBuilder params = new StringBuilder();
    if (ctx.multiparameter() != null) {
        java.util.List<OFPParser.SingleparameterContext> paramList = ctx.multiparameter().singleparameter();
        for (int i = 0; i < paramList.size(); i++) {
            if (i > 0) {
                params.append(", ");
            }
            params.append(getSafePythonId(paramList.get(i).ID().getText()));
        }
    }

    String header = indent() + "def " + funcName + "(" + params + "):\n";
    String body = visitStmtList(ctx.stmt());

    return header + body;
}



@Override
public String visitMaindeclaration(OFPParser.MaindeclarationContext ctx) {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    sb.append("# Program entry point - main\n");
    sb.append("\n");

    for (OFPParser.StmtContext s : ctx.stmt()) {
        sb.append(visit(s));
    }

    return sb.toString();
}



@Override
public String visitProgram(OFPParser.ProgramContext ctx) {
    StringBuilder sb = new StringBuilder();

    for (OFPParser.FunctionDeclareContext f : ctx.functionDeclare()) {
        sb.append(visit(f));
        sb.append("\n");
    }

    sb.append(visit(ctx.maindeclaration()));

    return sb.toString();
}



}