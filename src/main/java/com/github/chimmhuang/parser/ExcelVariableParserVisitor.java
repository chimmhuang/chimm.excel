package com.github.chimmhuang.parser;

import com.github.chimmhuang.parser.VariableParserParser.AddSubContext;
import com.github.chimmhuang.parser.VariableParserParser.ArrayIdxContext;
import com.github.chimmhuang.parser.VariableParserParser.ExprContext;
import com.github.chimmhuang.parser.VariableParserParser.LiterContext;
import com.github.chimmhuang.parser.VariableParserParser.MulDivContext;
import com.github.chimmhuang.parser.VariableParserParser.ParensContext;
import com.github.chimmhuang.parser.VariableParserParser.QualifiedNameContext;
import com.github.chimmhuang.parser.VariableParserParser.VarContext;
import com.github.chimmhuang.parser.VariableParserParser.VariableContext;
import com.github.chimmhuang.parser.VariableParserParser.VariableExprContext;
import com.github.chimmhuang.util.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * This class will read the variables filled in the excel template, and then fill in the corresponding values from the data set
 *
 * @author Chimm Huang
 */
public class ExcelVariableParserVisitor extends VariableParserBaseVisitor<Object> {

    private static Logger log = LoggerFactory.getLogger(ExcelVariableParserVisitor.class);

    private Map<String, Object> data;

    public ExcelVariableParserVisitor(Map<String, Object> data) {
        this.data = data;
    }

    public ExcelVariableParserVisitor() {
    }

    /**
     * Get the value of a variable
     */
    @Override
    public Object visitLiter(LiterContext ctx) {
        log.info("# enter: visitLiter:{}", ctx.getRuleIndex());
        VariableParserParser.LiteralContext literal = ctx.literal();
        if (literal.type.getType() == VariableParserParser.NUMBER) {
            return new BigDecimal(literal.NUMBER().getText());
        }
        return literal.STRING().getText();
    }

    @Override
    public Object visitVar(VarContext ctx) {
        System.out.println("进入：visitVar");
        System.out.println(ctx.getText());
        return super.visitVar(ctx);
    }

    @Override
    public Object visitVariableExpr(VariableExprContext ctx) {
        System.out.println("进入：visitVariableExpr");
        System.out.println(ctx.getText());

        for (VariableContext variableContext : ctx.variable()) {
            System.out.println(variableContext.IDENTIFIER().getText());
        }
//        return super.visitVariableExpr(ctx);
        return null;
    }

    /**
     * multiply | divide
     */
    @Override
    public BigDecimal visitMulDiv(MulDivContext ctx) {
        log.info("# enter: visitMulDiv");
        List<ExprContext> exprContextList = ctx.expr();
        BigDecimal arg1 = (BigDecimal) visitExpr(exprContextList.get(0));
        BigDecimal arg2 = (BigDecimal) visitExpr(exprContextList.get(1));

        return NumberUtils.multiply(arg1, arg2);
    }

    /**
     * add | subtract
     */
    @Override
    public BigDecimal visitAddSub(AddSubContext ctx) {
        log.info("# enter: visitAddSub");
        List<ExprContext> exprContextList = ctx.expr();
        BigDecimal arg1 = (BigDecimal) visitExpr(exprContextList.get(0));
        BigDecimal arg2 = (BigDecimal) visitExpr(exprContextList.get(1));

        return NumberUtils.sum(arg1, arg2);
    }

    @Override
    public Object visitParens(ParensContext ctx) {
        System.out.println("进入：visitParens");
        return super.visitParens(ctx);
    }

    @Override
    public Object visitVariable(VariableContext ctx) {
        System.out.println("进入：visitVariable");
        System.out.println(ctx.IDENTIFIER().getText());
        return super.visitVariable(ctx);
    }

    @Override
    public Object visitArrayIdx(ArrayIdxContext ctx) {
        System.out.println("进入：visitArrayIdx");
        return super.visitArrayIdx(ctx);
    }

    @Override
    public Object visitQualifiedName(QualifiedNameContext ctx) {
        System.out.println("进入：visitQualifiedName");
        return super.visitQualifiedName(ctx);
    }

    public Object visitExpr(ExprContext ctx) {
        if (ctx instanceof AddSubContext) {
            return visitAddSub((AddSubContext) ctx);
        } else if (ctx instanceof MulDivContext) {
            return visitMulDiv((MulDivContext) ctx);
        } else if (ctx instanceof VarContext) {
            return visitVar((VarContext) ctx);
        } else if (ctx instanceof LiterContext) {
            return visitLiter((LiterContext) ctx);
        }

        throw new RuntimeException("parse expression error.");
    }
}
