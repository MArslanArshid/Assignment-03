// Generated from c:/Users/arsla/Desktop/Assignment 01/Assignment 02/OFP.g4 by ANTLR 4.13.1

    package generated;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link OFPParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface OFPVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link OFPParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(OFPParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#singleparameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleparameter(OFPParser.SingleparameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#multiparameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiparameter(OFPParser.MultiparameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(OFPParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmt(OFPParser.PrintStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDeclStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclStmt(OFPParser.VarDeclStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(OFPParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(OFPParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(OFPParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallStmt}
	 * labeled alternative in {@link OFPParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStmt(OFPParser.CallStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLitExpr(OFPParser.BoolLitExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelEqExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelEqExpr(OFPParser.RelEqExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(OFPParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccessExpr(OFPParser.ArrayAccessExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LengthExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthExpr(OFPParser.LengthExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLitExpr(OFPParser.StringLitExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(OFPParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParanExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParanExpr(OFPParser.ParanExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpr(OFPParser.MulDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewArrayExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewArrayExpr(OFPParser.NewArrayExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLitExpr(OFPParser.IntLitExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatLitExpr(OFPParser.FloatLitExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunCallExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunCallExpr(OFPParser.FunCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CharLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLitExpr(OFPParser.CharLitExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(OFPParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayLitExpr}
	 * labeled alternative in {@link OFPParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLitExpr(OFPParser.ArrayLitExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(OFPParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#variableDeclare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclare(OFPParser.VariableDeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#functionDeclare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclare(OFPParser.FunctionDeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#maindeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaindeclaration(OFPParser.MaindeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(OFPParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(OFPParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#ifandWhileHelpers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfandWhileHelpers(OFPParser.IfandWhileHelpersContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#sumFunctioncall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumFunctioncall(OFPParser.SumFunctioncallContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(OFPParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(OFPParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#arrayLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteral(OFPParser.ArrayLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#arraywithnewkeyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraywithnewkeyword(OFPParser.ArraywithnewkeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link OFPParser#callstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallstatement(OFPParser.CallstatementContext ctx);
}