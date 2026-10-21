// Generated from c:/Users/arsla/Desktop/Assignment 01/Assignment 02/OFP.g4 by ANTLR 4.13.1

    package generated;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link OFPParser}.
 */
public interface OFPListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link OFPParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(OFPParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(OFPParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#singleparameter}.
	 * @param ctx the parse tree
	 */
	void enterSingleparameter(OFPParser.SingleparameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#singleparameter}.
	 * @param ctx the parse tree
	 */
	void exitSingleparameter(OFPParser.SingleparameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#multiparameter}.
	 * @param ctx the parse tree
	 */
	void enterMultiparameter(OFPParser.MultiparameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#multiparameter}.
	 * @param ctx the parse tree
	 */
	void exitMultiparameter(OFPParser.MultiparameterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(OFPParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(OFPParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintStmt(OFPParser.PrintStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintStmt(OFPParser.PrintStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarDeclStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclStmt(OFPParser.VarDeclStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarDeclStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclStmt(OFPParser.VarDeclStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(OFPParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(OFPParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(OFPParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(OFPParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(OFPParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(OFPParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterCallStmt(OFPParser.CallStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitCallStmt(OFPParser.CallStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolLitExpr(OFPParser.BoolLitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolLitExpr(OFPParser.BoolLitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelEqExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelEqExpr(OFPParser.RelEqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelEqExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelEqExpr(OFPParser.RelEqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(OFPParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(OFPParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessExpr(OFPParser.ArrayAccessExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessExpr(OFPParser.ArrayAccessExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LengthExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLengthExpr(OFPParser.LengthExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LengthExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLengthExpr(OFPParser.LengthExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringLitExpr(OFPParser.StringLitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringLitExpr(OFPParser.StringLitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegExpr(OFPParser.NegExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegExpr(OFPParser.NegExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParanExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParanExpr(OFPParser.ParanExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParanExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParanExpr(OFPParser.ParanExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(OFPParser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(OFPParser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NewArrayExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNewArrayExpr(OFPParser.NewArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NewArrayExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNewArrayExpr(OFPParser.NewArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntLitExpr(OFPParser.IntLitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntLitExpr(OFPParser.IntLitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FloatLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloatLitExpr(OFPParser.FloatLitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FloatLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloatLitExpr(OFPParser.FloatLitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunCallExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunCallExpr(OFPParser.FunCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunCallExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunCallExpr(OFPParser.FunCallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CharLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCharLitExpr(OFPParser.CharLitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CharLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCharLitExpr(OFPParser.CharLitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(OFPParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(OFPParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayLitExpr(OFPParser.ArrayLitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayLitExpr(OFPParser.ArrayLitExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(OFPParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(OFPParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#variableDeclare}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclare(OFPParser.VariableDeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#variableDeclare}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclare(OFPParser.VariableDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#functionDeclare}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclare(OFPParser.FunctionDeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#functionDeclare}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclare(OFPParser.FunctionDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#maindeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMaindeclaration(OFPParser.MaindeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#maindeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMaindeclaration(OFPParser.MaindeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(OFPParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(OFPParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(OFPParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(OFPParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#ifandWhileHelpers}.
	 * @param ctx the parse tree
	 */
	void enterIfandWhileHelpers(OFPParser.IfandWhileHelpersContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#ifandWhileHelpers}.
	 * @param ctx the parse tree
	 */
	void exitIfandWhileHelpers(OFPParser.IfandWhileHelpersContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#sumFunctioncall}.
	 * @param ctx the parse tree
	 */
	void enterSumFunctioncall(OFPParser.SumFunctioncallContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#sumFunctioncall}.
	 * @param ctx the parse tree
	 */
	void exitSumFunctioncall(OFPParser.SumFunctioncallContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(OFPParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(OFPParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(OFPParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(OFPParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(OFPParser.ArrayLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(OFPParser.ArrayLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#arraywithnewkeyword}.
	 * @param ctx the parse tree
	 */
	void enterArraywithnewkeyword(OFPParser.ArraywithnewkeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#arraywithnewkeyword}.
	 * @param ctx the parse tree
	 */
	void exitArraywithnewkeyword(OFPParser.ArraywithnewkeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link OFPParser#callstatement}.
	 * @param ctx the parse tree
	 */
	void enterCallstatement(OFPParser.CallstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OFPParser#callstatement}.
	 * @param ctx the parse tree
	 */
	void exitCallstatement(OFPParser.CallstatementContext ctx);
}