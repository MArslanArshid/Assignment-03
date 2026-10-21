package src;

import generated.OFPParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.ParserRuleContext;

public class TypeChecker {

    public static String getExprType(OFPParser.ExprContext ctx, ParseTreeProperty<Scope> scopes, Scope globalScope) { //1. Room dhoondo (findScope) Us room mein, 
    // check karo "declare hai ya nahi" (resolve)
    // Agar declare hai, uska TYPE wapas de do (getType)

        if (ctx instanceof OFPParser.IntLitExprContext) {
            return "int";
        }
        if (ctx instanceof OFPParser.FloatLitExprContext) {
            return "float";
        }
        if (ctx instanceof OFPParser.BoolLitExprContext) {
            return "bool";
        }
        if (ctx instanceof OFPParser.StringLitExprContext) {
            return "string";
        }
        if (ctx instanceof OFPParser.CharLitExprContext) {
            return "char";
        }
        if (ctx instanceof OFPParser.LengthExprContext){ // length ka type hamesha int hota hay
            return "int";
        }

        if (ctx instanceof OFPParser.IdExprContext) { // int b = a; only call when variable is already declared. Agar variable declare nahi hay to UndeclaredCheckListener error dega. Yahan sirf type check ho raha hay.
            String name = ctx.getText(); 
            Scope scope = findScope(ctx, scopes, globalScope);
            Symbol sym = scope.resolve(name); // resolve ka matlab hai — 'kya name (jaisay x) is kamray mein, YA ISKE BARE mein, KAHIN declare hai?'
            if (sym != null) {
                return sym.getType();
            }
            return "unknown"; //Apni list mein dekho → nahi mila → PARENT KI LIST mein dekho (recursive) → wahan bhi nahi → us PARENT KE PARENT KI LIST mein dekho → ... → kahin na milay toh null.sss
        }

        // YAHAN NAYA HISSA SHURU
        if (ctx instanceof OFPParser.AddSubExprContext) {
            OFPParser.AddSubExprContext addCtx = (OFPParser.AddSubExprContext) ctx; //casting ho rahi kynki OFPParser.ExprContext ka type OFPParser.AddSubExprContext hay. AddSubExprContext ka matlab hay ke yeh ya to addition ya subtraction ka expression hay. casting ka mtlb hay main chez ko covert kr raha hun. Jaisay main OFPParser.ExprContext ko OFPParser.AddSubExprContext may convert kr raha hun. ctx ko addsub ke trha treat kro or addctx name day do.
            String leftType = getExprType(addCtx.expr(0), scopes, globalScope);
            String rightType = getExprType(addCtx.expr(1), scopes, globalScope);
            if (leftType.equals(rightType)) {
                return leftType;
            } else {
                return "unknown";
            }
        }

        if (ctx instanceof OFPParser.MulDivExprContext) {
            OFPParser.MulDivExprContext mulCtx = (OFPParser.MulDivExprContext) ctx;
            String leftType = getExprType(mulCtx.expr(0), scopes, globalScope);
            String rightType = getExprType(mulCtx.expr(1), scopes, globalScope);
            if (leftType.equals(rightType)) {
                return leftType;
            } else {
                return "unknown";
            } //MulDivExprContext mein bhi Casting kar rahe hain, pehle kyunke yeh expr ke ANDAR hai, wahan se SPECIFICALLY NIKAAL kar, isay ctx ki tarah treat kar rahe hain, naam mulCtx diya. Phir leftType ke type check ke, aur SCOPE diya — yani kis ROOM mein hai, pehle wo dhoondo, mila toh TYPE do, warna GLOBAL de do. Same RIGHT side bhi. Agar dono OKAY (same), toh LEFT SIDE return karo, otherwise 'unknown' do.
        }



        if (ctx instanceof OFPParser.RelEqExprContext) {
            OFPParser.RelEqExprContext relCtx = (OFPParser.RelEqExprContext) ctx;
            String leftType = getExprType(relCtx.expr(0), scopes, globalScope);
            String rightType = getExprType(relCtx.expr(1), scopes, globalScope);

            if (leftType.equals(rightType)) {
                return "bool"; // comparison ka result hamesha bool hota hay
            } else {
                return "unknown";
            }
        }

        if (ctx instanceof OFPParser.ArrayAccessExprContext) { // arr[0].
            OFPParser.ArrayAccessExprContext arrCtx = (OFPParser.ArrayAccessExprContext) ctx;
            String baseType = getExprType(arrCtx.expr(0), scopes, globalScope); // jaisay 'arr' ka type

            if (baseType.endsWith("[]")) {
                return baseType.substring(0, baseType.length() - 2); // "[]" hata do
            }
            return "unknown";
        } 
        if (ctx instanceof OFPParser.FunCallExprContext) { //SumUpTO(5);
            OFPParser.FunCallExprContext funcCtx = (OFPParser.FunCallExprContext) ctx;
            String functionName = funcCtx.sumFunctioncall().ID().getText();

            Symbol sym = globalScope.resolve(functionName); // functions hamesha global scope mein hotay hain //jab humein KISI FUNCTION ka naam/type dhoondna ho, hum seedha globalScope CHECK kar sakte hain, poori tree mein UPAR-NEECHAY dhoondne ki zaroorat nahi, kyunke functions HAMESHA WAHIN RAHTE HAIN
            if (sym != null) {
                return sym.getType();
            }
        }

                    // Brackets '(...)' — bas andar wali cheez ka type wapas de do
        if (ctx instanceof OFPParser.ParanExprContext) { // int y = (x + 5);
            OFPParser.ParanExprContext parenCtx = (OFPParser.ParanExprContext) ctx; // casting again.
            return getExprType(parenCtx.expr(), scopes, globalScope);
        }
        // Unary minus '-x' — result ka type wahi hai jo andar wali cheez ka hai
        if (ctx instanceof OFPParser.NegExprContext) { // int a = a + b; so print(-a).
            OFPParser.NegExprContext negCtx = (OFPParser.NegExprContext) ctx;
            return getExprType(negCtx.expr(), scopes, globalScope);
        }

        // int[] arr = {1, 2, 3}; // Array literal {1,2,3} — pehle element ka type dekho, phir "[]" laga do
        if (ctx instanceof OFPParser.ArrayLitExprContext) {
            OFPParser.ArrayLitExprContext arrLitCtx = (OFPParser.ArrayLitExprContext) ctx; // casting.
            OFPParser.ArgumentListContext argList = arrLitCtx.arrayLiteral().argumentList(); //get argumentList from arrayLiteral. yani pori list ko get krdo. argumentList mein multiple expressions ho sakte hain, jaisay {1,2,3} mein 3 expressions hain. argumentList() function ka matlab hai — 'jo bhi arguments diye gaye hain, unka list wapas do'. Yani {1,2,3} ke liye, yeh function 3 expressions ka list wapas karega.
            if (argList == null || argList.expr().isEmpty()) {
                return "unknown"; // agar array literal khaali hai, toh type unknown hai
            }
            String firstType = getExprType(argList.expr(0), scopes, globalScope); // phle nikal kr check karain gay baki kiya isi type kay hain.
            for (OFPParser.ExprContext e : argList.expr()) {
                String t = getExprType(e, scopes, globalScope);
                if (!t.equals(firstType)) {
                    return "unknown"; // elements mismatch, TypeCheckListener alag se error dega
                }
            }
            return firstType + "[]";       //ROUND 1: e=1, t="int", firstType="int" → SAME, OK, AGLA JAO
            // ROUND 2: e=2, t="int", firstType="int" → SAME, OK, AGLA JAOROUND 3: e=3, t="int", firstType="int" → SAME, OK, LOOP KHATAM
        } 

        // new float[3] — type seedha 'new' ke baad wale keyword se milta hai
        if (ctx instanceof OFPParser.NewArrayExprContext) {
            OFPParser.NewArrayExprContext newCtx = (OFPParser.NewArrayExprContext) ctx;
            String baseType = newCtx.arraywithnewkeyword().getChild(1).getText();
            return baseType + "[]";
        }

            return "unknown";
    
    }

    private static Scope findScope(ParserRuleContext ctx, ParseTreeProperty<Scope> scopes, Scope globalScope) {
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
}