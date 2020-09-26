package com.github.chimmhuang.antlr;

import com.github.chimmhuang.parser.ExcelVariableParserVisitor;
import com.github.chimmhuang.parser.VariableParserLexer;
import com.github.chimmhuang.parser.VariableParserParser;
import com.github.chimmhuang.parser.VariableParserParser.ExprContext;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chimm Huang
 */
public class AntlrTest {

    @Test
    public void testAntlr() {
        Map<String, Object> params = new HashMap<>();
        params.put("A1", 100);
        params.put("A2", 200);
        params.put("A3", -300);

        // 词法解析
//        VariableParserLexer lexer = new VariableParserLexer(CharStreams.fromString("${demo01.value} + ${demo02.age}"));
        VariableParserLexer lexer = new VariableParserLexer(CharStreams.fromString("1+2"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 语法解析
        VariableParserParser parser = new VariableParserParser(tokens);
        ExprContext tree = parser.expr();

        // 以 lisp 格式打印 AST
        System.out.println(tree.toString(parser));

        System.out.println(tree.getText());

        ExcelVariableParserVisitor visitor = new ExcelVariableParserVisitor(params);
        Object visit = visitor.visit(tree);
        System.out.println(visit);
    }
}
