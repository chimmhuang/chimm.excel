// Generated from /Users/huangshuai/IdeaProjects/chimm_excel/src/main/java/com/github/chimmhuang/antlr/VariableParser.g4 by ANTLR 4.8
package com.github.chimmhuang.excel.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link VariableParserParser}.
 */
public interface VariableParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code FormulaCall}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFormulaCall(VariableParserParser.FormulaCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FormulaCall}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFormulaCall(VariableParserParser.FormulaCallContext ctx);
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
	 * Enter a parse tree produced by the {@code ExcelArray}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExcelArray(VariableParserParser.ExcelArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExcelArray}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExcelArray(VariableParserParser.ExcelArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Name}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterName(VariableParserParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Name}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitName(VariableParserParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link VariableParserParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(VariableParserParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link VariableParserParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(VariableParserParser.ExprListContext ctx);
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
	 * Enter a parse tree produced by {@link VariableParserParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(VariableParserParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link VariableParserParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(VariableParserParser.FormulaContext ctx);
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
	 * Enter a parse tree produced by {@link VariableParserParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(VariableParserParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link VariableParserParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(VariableParserParser.ArrayContext ctx);
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