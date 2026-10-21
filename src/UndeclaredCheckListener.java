package src;

import generated.OFPBaseListener;
import generated.OFPParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.ParserRuleContext;

public class UndeclaredCheckListener extends OFPBaseListener {
    private ParseTreeProperty<Scope> scopes;
    private Scope globalScope;
    private int errorCount = 0;

    public UndeclaredCheckListener(ParseTreeProperty<Scope> scopes, Scope globalScope){
        this.scopes = scopes;
        this.globalScope = globalScope; //apnay walay scopes or globascope may rakh diya.
    }

    public int getErrorCount(){
        return errorCount;
    }

    private Scope findScope(ParserRuleContext ctx){ // ham ksi kamray may hain dekhaty hain nhi to parent may jao takay global tak pohanch jao.
        ParserRuleContext current = ctx;
        while (current != null){
            Scope s = scopes.get(current); // scopes list may dekho is name ka sath parent scope hay ya nhi. Agar hay to wapis do, nhi to parent may jao.
            if (s != null){
                return s;
            }
            current = current.getParent(); // wrna parent may jao or current ab wo name krdo isi trha oper tak jao jb tk global nhi mil jaye.
        }
        return globalScope;
    } //Yeh step-by-step search hota hai — agar ek kamray mein mil jaye, theek hai (wapas de do). Warna us kamray ke PARENT ke pass jao, aur check karo — agar wahan nahi mila, uske parent ke pass jao — jab tak Global Top tak na pahunch jao, wahin wapas de do. Phir humein pata chal jata hai, 'Global mil gaya, lekin (asal mein jo naam dhoond rahe thay) woh scope (us naam ka Symbol) nahi mila, isliye error.

    @Override 
    public void enterIdExpr(OFPParser.IdExprContext ctx){
        String name = ctx.getText();

        Scope scope = findScope(ctx); //Mujhe BATAO — 'main KIS KAMRAY mein hun?'
        Symbol sym = scope.resolve(name); //Ab us MILE HUE KAMRAY (scope) se POOCHO — 'kya name (jaisay x) is kamray mein, YA ISKE BARE mein, KAHIN declare hai

        if (sym == null){
            System.out.println("Error:'" + name + "' is not declared!");
            errorCount++;
        }
    }

        // Assignment ke bayan taraf (LHS) ko bhi check karo
    @Override
    public void enterAssignStmt(OFPParser.AssignStmtContext ctx) {
        OFPParser.AssignContext assignCtx = ctx.assign();
        String name = assignCtx.ID().getText();

        Scope scope = findScope(ctx);
        Symbol sym = scope.resolve(name);

        if (sym == null) {
            System.out.println("ERROR: '" + name + "' is not declared!");
            errorCount++;
        }
    }

    // Function call ke naam ko bhi check karo (jab expression ke andar ho)
    @Override
    public void enterFunCallExpr(OFPParser.FunCallExprContext ctx) {
        String name = ctx.sumFunctioncall().ID().getText();

        Scope scope = findScope(ctx);
        Symbol sym = scope.resolve(name);

        if (sym == null) {
            System.out.println("ERROR: '" + name + "' is not declared!");
            errorCount++;
        }
    }

    // Function call ke naam ko bhi check karo (jab akela statement ho)
    @Override
    public void enterCallStmt(OFPParser.CallStmtContext ctx) {
        String name = ctx.callstatement().ID().getText();

        Scope scope = findScope(ctx);
        Symbol sym = scope.resolve(name);

        if (sym == null) {
            System.out.println("ERROR: '" + name + "' is not declared!");
            errorCount++;
        }
    }
}
