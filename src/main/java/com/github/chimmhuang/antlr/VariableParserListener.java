// Generated from /Users/huangshuai/IdeaProjects/chimm_excel/src/main/java/com/github/chimmhuang/parser/VariableParser.g4 by ANTLR 4.8
package com.github.chimmhuang.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link VariableParserParser}.
 */
public interface VariableParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Liter}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLiter(VariableParserParser.LiterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Liter}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLiter(VariableParserParser.LiterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(VariableParserParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(VariableParserParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(VariableParserParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(VariableParserParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Var}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVar(VariableParserParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVar(VariableParserParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(VariableParserParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(VariableParserParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by {@link VariableParserParser#variableExpr}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpr(VariableParserParser.VariableExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link VariableParserParser#variableExpr}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpr(VariableParserParser.VariableExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link VariableParserParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(VariableParserParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link VariableParserParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(VariableParserParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link VariableParserParser#arrayIdx}.
	 * @param ctx the parse tree
	 */
	void enterArrayIdx(VariableParserParser.ArrayIdxContext ctx);
	/**
	 * Exit a parse tree produced by {@link VariableParserParser#arrayIdx}.
	 * @param ctx the parse tree
	 */
	void exitArrayIdx(VariableParserParser.ArrayIdxContext ctx);
	/**
	 * Enter a parse tree produced by {@link VariableParserParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(VariableParserParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link VariableParserParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(VariableParserParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link VariableParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(VariableParserParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link VariableParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(VariableParserParser.LiteralContext ctx);
}