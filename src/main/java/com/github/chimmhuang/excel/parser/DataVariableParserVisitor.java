package com.github.chimmhuang.excel.parser;

import com.github.chimmhuang.excel.ExcelHelper;
import com.github.chimmhuang.excel.parser.VariableParserParser.AddSubContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.ArrayContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.ArrayIdxContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.ExcelArrayContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.ExprContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.ExprListContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.FormulaCallContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.FormulaContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.LiterContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.LiteralContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.MulDivContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.NameContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.ParensContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.QualifiedNameContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.VarContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.VariableContext;
import com.github.chimmhuang.excel.parser.VariableParserParser.VariableExprContext;

import java.util.List;


/**
 * @author Chimm Huang
 */
public class DataVariableParserVisitor extends VariableParserBaseVisitor {

    private Object data;

    public DataVariableParserVisitor(Object data) {
        this.data = data;
    }


    @Override
    public Object visitFormulaCall(FormulaCallContext ctx) {
        return super.visitFormulaCall(ctx);
    }

    @Override
    public Object visitLiter(LiterContext ctx) {
        return super.visitLiter(ctx);
    }

    /**
     * multiply | divide
     * e.g. A1*A2
     */
    @Override
    public Object visitMulDiv(MulDivContext ctx) {
        return substitutionVariable(ctx.getText(), ctx.expr());
    }

    @Override
    public Object visitAddSub(AddSubContext ctx) {
        return substitutionVariable(ctx.getText(), ctx.expr());
    }

    @Override
    public Object visitVar(VarContext ctx) {
        String propName = ctx.getText();
        return ExcelHelper.parseCellVariable(data, propName);
    }

    @Override
    public Object visitParens(ParensContext ctx) {
        return super.visitParens(ctx);
    }

    @Override
    public Object visitExcelArray(ExcelArrayContext ctx) {
        return super.visitExcelArray(ctx);
    }

    @Override
    public Object visitName(NameContext ctx) {
        return super.visitName(ctx);
    }

    /**
     * exprList
     * e.g. A1,A2,${demo.value}
     */
    @Override
    public String visitExprList(ExprListContext ctx) {
        return substitutionVariable(ctx.getText(), ctx.expr());
    }

    @Override
    public Object visitQualifiedName(QualifiedNameContext ctx) {
        return super.visitQualifiedName(ctx);
    }

    @Override
    public Object visitVariableExpr(VariableExprContext ctx) {
        return super.visitVariableExpr(ctx);
    }

    @Override
    public Object visitVariable(VariableContext ctx) {
        return super.visitVariable(ctx);
    }

    /**
     * formula
     * e.g. SUM(A1,A2,${demo.value})
     */
    @Override
    public String visitFormula(FormulaContext ctx) {
        String formula = ctx.getText();
        ExprListContext exprListContext = ctx.exprList();
        String oldExprList = exprListContext.getText();
        String newExprList = visitExprList(exprListContext);
        return formula.replace(oldExprList, newExprList);
    }

    @Override
    public Object visitArrayIdx(ArrayIdxContext ctx) {
        return super.visitArrayIdx(ctx);
    }

    @Override
    public Object visitArray(ArrayContext ctx) {
        return super.visitArray(ctx);
    }

    @Override
    public Object visitLiteral(LiteralContext ctx) {
        return super.visitLiteral(ctx);
    }

    /**
     * take the final result of each expression, replace it and return.
     *
     * @param expr            expression in string form
     * @param exprContextList expression list
     */
    private String substitutionVariable(String expr, List<ExprContext> exprContextList) {
        for (ExprContext exprContext : exprContextList) {
            String exprContextText = exprContext.getText();
            if (exprContextText.startsWith("$")) {
                Object visit = super.visit(exprContext);
                expr = expr.replace(exprContextText, visit == null ? "" : visit.toString());
            }
        }
        return expr;
    }
}
