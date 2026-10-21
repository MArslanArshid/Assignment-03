package src;

import generated.OFPBaseListener;
import generated.OFPParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.List;

public class TypeCheckListener extends OFPBaseListener {
    private ParseTreeProperty<Scope> scopes;
    private Scope globalScope;
    private int errorCount = 0;
    private FunctionSymbol currentFunction;

    public TypeCheckListener(ParseTreeProperty<Scope> scopes, Scope globalScope) {
        this.scopes = scopes;
        this.globalScope = globalScope;
    }

    public int getErrorCount() {
        return errorCount;
    }

    private Scope findScope(ParserRuleContext ctx) {
        ParserRuleContext current = ctx;
        while (current != null) {
            Scope s = scopes.get(current);
            if (s != null) {
                return s;
            }
            current = current.getParent();
        }
        return globalScope;
    }

    @Override
    public void enterAssignStmt(OFPParser.AssignStmtContext ctx) { //x = 5;
        OFPParser.AssignContext assignCtx = ctx.assign(); //CASTING
        String varName = assignCtx.ID().getText();
        Scope scope = findScope(ctx);
        Symbol varSym = scope.resolve(varName);

        if (varSym == null) {
            return; // code will stop here .
        }

        String leftType = varSym.getType(); // till here for x = 5;
        OFPParser.ExprContext rightExpr; // from here arr[0] = 5;

        if (assignCtx.expr().size() == 2) {
            rightExpr = assignCtx.expr(1);
            if (leftType.endsWith("[]")) {
                leftType = leftType.substring(0, leftType.length() - 2); // "[]" hata do int[] arr
            }
        } else {
            rightExpr = assignCtx.expr(0);
        }

        String rightType = TypeChecker.getExprType(rightExpr, scopes, globalScope);

        if (!rightType.equals("unknown") && !leftType.equals(rightType)) {
            System.out.println("ERROR: Type mismatch - '" + varName + "' is " + leftType + " but assigned " + rightType);
            errorCount++;
        }
    }

    @Override //int x = 5;
    public void enterVarDeclStmt(OFPParser.VarDeclStmtContext ctx) {
        OFPParser.VariableDeclareContext varCtx = ctx.variableDeclare();

        if (varCtx.expr() == null) { 
            return; //AGAR int x; HOTA — BINA VALUE — toh expr() NULL hota, aur return; chal kar METHOD RUK JATA.
        }

        String varName = varCtx.ID().getText();
        Scope scope = findScope(ctx);
        Symbol varSym = scope.resolve(varName);

        if (varSym == null) {
            return;
        }

        String leftType = varSym.getType();
        String rightType = TypeChecker.getExprType(varCtx.expr(), scopes, globalScope);

        if (!rightType.equals("unknown") && !leftType.equals(rightType)) {
            System.out.println("ERROR: Type mismatch - '" + varName + "' is " + leftType + " but assigned " + rightType);
            errorCount++;
        }
    }
//////////////////////////////////////////idr tak okay
    @Override
    public void enterAddSubExpr(OFPParser.AddSubExprContext ctx) {
        String leftType = TypeChecker.getExprType(ctx.expr(0), scopes, globalScope);
        String rightType = TypeChecker.getExprType(ctx.expr(1), scopes, globalScope);

        if (leftType.equals("unknown") || rightType.equals("unknown")) {
            return;
        }

        if (!leftType.equals(rightType)) {
            System.out.println("ERROR: Type mismatch in expression - " + leftType + " and " + rightType);
            errorCount++;
            return;
        }

        // Naya check: sirf int/float allowed hain +,-,*,/ ke liye
        if (!leftType.equals("int") && !leftType.equals("float")) {
            System.out.println("ERROR: Operator +/- only works on int/float, found " + leftType);
            errorCount++;
        }
    }

    @Override
    public void enterMulDivExpr(OFPParser.MulDivExprContext ctx) {
        String leftType = TypeChecker.getExprType(ctx.expr(0), scopes, globalScope);
        String rightType = TypeChecker.getExprType(ctx.expr(1), scopes, globalScope);

        if (leftType.equals("unknown") || rightType.equals("unknown")) {
            return;
        }

        if (!leftType.equals(rightType)) {
            System.out.println("ERROR: Type mismatch in expression - " + leftType + " and " + rightType);
            errorCount++;
            return;
        }

        if (!leftType.equals("int") && !leftType.equals("float")) {
            System.out.println("ERROR: Operator */ only works on int/float, found " + leftType);
            errorCount++;
        }
    }

    @Override //(<, >, ==)
    public void enterRelEqExpr(OFPParser.RelEqExprContext ctx) {
        String leftType = TypeChecker.getExprType(ctx.expr(0), scopes, globalScope);
        String rightType = TypeChecker.getExprType(ctx.expr(1), scopes, globalScope);

        if (leftType.equals("unknown") || rightType.equals("unknown")) {
            return;
        }

        if (!leftType.equals(rightType)) {
            System.out.println("ERROR: Type mismatch in comparison - " + leftType + " and " + rightType);
            errorCount++;
            return;
        }

        if (!leftType.equals("int") && !leftType.equals("float") && !leftType.equals("char")) {
            System.out.println("ERROR: Comparison operators only work on int/float/char, found " + leftType);
            errorCount++;
        }
    }

    @Override //while (a) { ... }
    public void enterWhileStmt(OFPParser.WhileStmtContext ctx) {
        OFPParser.WhileStatementContext whileCtx = ctx.whileStatement();
        String condType = TypeChecker.getExprType(whileCtx.expr(), scopes, globalScope);

        if (!condType.equals("unknown") && !condType.equals("bool")) {
            System.out.println("ERROR: while condition must be bool, found " + condType);
            errorCount++;
        }
    }

    @Override
    public void enterIfStmt(OFPParser.IfStmtContext ctx) {
        OFPParser.IfStatementContext ifCtx = ctx.ifStatement();
        String condType = TypeChecker.getExprType(ifCtx.expr(), scopes, globalScope);

        if (!condType.equals("unknown") && !condType.equals("bool")) {
            System.out.println("ERROR: if condition must be bool, found " + condType);
            errorCount++;
        }
    }

    @Override //print(...) YA println(...).
    public void enterPrintStmt(OFPParser.PrintStmtContext ctx) {
        String argType = TypeChecker.getExprType(ctx.expr(), scopes, globalScope);

        if (!argType.equals("unknown") && argType.endsWith("[]")) // print(...) YA println(...)..Array nhi ana
        {
            System.out.println("ERROR: print/println cannot take an array, found " + argType);
            errorCount++;
        }
    }
////////////////////////////////////////////////////////////////////--------///////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////--------///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////---------///////////////////////////////////////////////////////////////////////////////

    @Override // new enter function delcare not from symbol table listener
    //SymbolTableListener ka enterFunctionDeclare → NAYA FunctionSymbol BANATA //HAI (PEHLI DAFA DECLARE HOTE WAQT)
    //SIRF YAAD RAKHTA HAI "ABHI HUM KIS FUNCTION KE ANDAR HAIN" — KUCH NAYA NAHI BANATA, SIRF DHOONDTA HAI (PEHLE SE BANA HUA FunctionSymbol)
    public void enterFunctionDeclare(OFPParser.FunctionDeclareContext ctx) {
        String functionName = ctx.ID().getText();
        Symbol sym = globalScope.resolve(functionName);
        if (sym instanceof FunctionSymbol) //checking kiya wakay hi FunctionSymbol hay ya nhi. Agar hay to currentFunction ko set krdo.
        {
            currentFunction = (FunctionSymbol) sym;
        }
    }

    @Override
    public void enterMaindeclaration(OFPParser.MaindeclarationContext ctx) {
        Symbol sym = globalScope.resolve("main");
        if (sym instanceof FunctionSymbol) {
            currentFunction = (FunctionSymbol) sym;
        }
    }

    @Override // function plus return ke type same honi chayay.
    public void enterReturnStmt(OFPParser.ReturnStmtContext ctx) {
        OFPParser.ReturnStatementContext retCtx = ctx.returnStatement();
        String returnedType = TypeChecker.getExprType(retCtx.expr(), scopes, globalScope);

        if (currentFunction == null) {
            return; // kuch ajeeb ho gaya, skip karo
        }

        String expectedType = currentFunction.getType();

        if (!returnedType.equals("unknown") && !returnedType.equals(expectedType)) {
            System.out.println("ERROR: Function '" + currentFunction.getName() + "' should return " + expectedType + " but returned " + returnedType);
            errorCount++;
        }
    }


    //////////////////////////////////////////////////////For Subha//////////////////////////////////////////////////////////////////////////

    @Override
    public void enterFunCallExpr(OFPParser.FunCallExprContext ctx) {
        OFPParser.SumFunctioncallContext callCtx = ctx.sumFunctioncall();
        String functionName = callCtx.ID().getText();

        Symbol sym = globalScope.resolve(functionName);
        if (!(sym instanceof FunctionSymbol)) {
            return;
        }

        FunctionSymbol funcSym = (FunctionSymbol) sym;
        List<Symbol> expectedParams = funcSym.getParameters(); //function mila to kitnay arguments hain ? 

        OFPParser.ArgumentListContext argListCtx = callCtx.argumentList();
        int actualArgCount = (argListCtx == null) ? 0 : argListCtx.expr().size(); // kitnay milay way hain argiments?...

        if (actualArgCount != expectedParams.size()) //2==1 error
        {
            System.out.println("ERROR: Function '" + functionName + "' expects " + expectedParams.size() + " arguments, got " + actualArgCount);
            errorCount++;
            return;
        }

        for (int i = 0; i < actualArgCount; i++) //1=1 
        {
            String expectedType = expectedParams.get(i).getType(); //original
            String actualType = TypeChecker.getExprType(argListCtx.expr(i), scopes, globalScope);

            if (!actualType.equals("unknown") && !actualType.equals(expectedType)) {
                System.out.println("ERROR: Function '" + functionName + "' argument " + (i + 1) + " should be " + expectedType + " but got " + actualType);
                errorCount++;
            }
        }
    }

    @Override
    public void enterCallStmt(OFPParser.CallStmtContext ctx) {
        OFPParser.CallstatementContext callCtx = ctx.callstatement();
        String functionName = callCtx.ID().getText();

        Symbol sym = globalScope.resolve(functionName);
        if (!(sym instanceof FunctionSymbol)) {
            return;
        }

        FunctionSymbol funcSym = (FunctionSymbol) sym;
        List<Symbol> expectedParams = funcSym.getParameters();

        OFPParser.ArgumentListContext argListCtx = callCtx.argumentList();
        int actualArgCount = (argListCtx == null) ? 0 : argListCtx.expr().size();

        if (actualArgCount != expectedParams.size()) {
            System.out.println("ERROR: Function '" + functionName + "' expects " + expectedParams.size() + " arguments, got " + actualArgCount);
            errorCount++;
            return;
        }

        for (int i = 0; i < actualArgCount; i++) {
            String expectedType = expectedParams.get(i).getType();
            String actualType = TypeChecker.getExprType(argListCtx.expr(i), scopes, globalScope);

            if (!actualType.equals("unknown") && !actualType.equals(expectedType)) {
                System.out.println("ERROR: Function '" + functionName + "' argument " + (i + 1) + " should be " + expectedType + " but got " + actualType);
                errorCount++;
            }
        }
    }

        // Unary minus sirf int/float pe honi chahiye
    @Override //-x
    public void enterNegExpr(OFPParser.NegExprContext ctx) {
        String innerType = TypeChecker.getExprType(ctx.expr(), scopes, globalScope);

        if (!innerType.equals("unknown") && !innerType.equals("int") && !innerType.equals("float")) {
            System.out.println("ERROR: unary '-' can only be applied to int/float, found " + innerType);
            errorCount++;
        }
    }

    // Array literal ke andar sab elements ka type same hona chahiye
    @Override //float[] mismatchArrLit = {1, 2.5};
    public void enterArrayLitExpr(OFPParser.ArrayLitExprContext ctx) {
        OFPParser.ArgumentListContext argList = ctx.arrayLiteral().argumentList();
        if (argList == null || argList.expr().isEmpty()) {
            return;
        }

        String firstType = TypeChecker.getExprType(argList.expr(0), scopes, globalScope);
        for (OFPParser.ExprContext e : argList.expr()) {
            String t = TypeChecker.getExprType(e, scopes, globalScope);
            if (!t.equals("unknown") && !firstType.equals("unknown") && !t.equals(firstType)) {
                System.out.println("ERROR: array literal has mismatched element types - " + firstType + " and " + t);
                errorCount++;
                return;
            }
        }
    }


    // 'new type[size]' mein size hamesha int honi chahiye
    @Override //int[] badNewSize = jb yeh milay new int[pi];
    public void enterNewArrayExpr(OFPParser.NewArrayExprContext ctx) {
        OFPParser.ArraywithnewkeywordContext newCtx = ctx.arraywithnewkeyword();
        String sizeType = TypeChecker.getExprType(newCtx.expr(), scopes, globalScope);

        if (!sizeType.equals("unknown") && !sizeType.equals("int")) {
            System.out.println("ERROR: array size in 'new' must be int, found " + sizeType);
            errorCount++;
        }
    } //////////////new at last

    @Override //sirf string honi chayay ya array.
    public void enterLengthExpr(OFPParser.LengthExprContext ctx) {
        String baseType = TypeChecker.getExprType(ctx.expr(), scopes, globalScope);

        if (!baseType.equals("unknown") && !baseType.endsWith("[]") && !baseType.equals("string")) {
            System.out.println("ERROR: .length can only be used on arrays or strings, found " + baseType);
            errorCount++;
        }
    }

    @Override //int x = arr[0];
    // arr[0] mein arr hamesha array ya string honi chahiye, aur index hamesha int honi chahiye
    public void enterArrayAccessExpr(OFPParser.ArrayAccessExprContext ctx) {
        String baseType = TypeChecker.getExprType(ctx.expr(0), scopes, globalScope);
        String indexType = TypeChecker.getExprType(ctx.expr(1), scopes, globalScope);

        if (!baseType.equals("unknown") && !baseType.endsWith("[]") && !baseType.equals("string")) {
            System.out.println("ERROR: [] can only be used on arrays or strings, found " + baseType);
            errorCount++;
        }

        if (!indexType.equals("unknown") && !indexType.equals("int")) {
            System.out.println("ERROR: array/string index must be int, found " + indexType);
            errorCount++;
        }
    }





}