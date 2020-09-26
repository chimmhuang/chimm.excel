// Generated from /Users/huangshuai/IdeaProjects/chimm_excel/src/main/java/com/github/chimmhuang/parser/VariableParser.g4 by ANTLR 4.8
package com.github.chimmhuang.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link VariableParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface VariableParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Liter}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiter(VariableParserParser.LiterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(VariableParserParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(VariableParserParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(VariableParserParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link VariableParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(VariableParserParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by {@link VariableParserParser#variableExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableExpr(VariableParserParser.VariableExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link VariableParserParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(VariableParserParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link VariableParserParser#arrayIdx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayIdx(VariableParserParser.ArrayIdxContext ctx);
	/**
	 * Visit a parse tree produced by {@link VariableParserParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(VariableParserParser.QualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link VariableParserParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(VariableParserParser.LiteralContext ctx);
}