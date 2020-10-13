// Generated from /Users/huangshuai/IdeaProjects/chimm_excel/src/main/java/com/github/chimmhuang/antlr/VariableParser.g4 by ANTLR 4.8
package com.github.chimmhuang.excel.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class VariableParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, QUOT=2, IDENTIFIER=3, NUMBER=4, DIGIT=5, AMP=6, ADD=7, MINUS=8, 
		MUL=9, DIV=10, POWER=11, PERCENT=12, ABS=13, EXCL=14, COLON=15, COMMA=16, 
		DOT=17, SEMI=18, LPAR=19, RPAR=20, EQ=21, NEQ=22, LTEQ=23, GTEQ=24, GT=25, 
		LT=26, AND_OP=27, OR_OP=28, LBRA=29, RBRA=30, LSQU=31, RSQU=32, WS=33;
	public static final int
		RULE_expr = 0, RULE_exprList = 1, RULE_qualifiedName = 2, RULE_variableExpr = 3, 
		RULE_variable = 4, RULE_formula = 5, RULE_arrayIdx = 6, RULE_array = 7, 
		RULE_literal = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"expr", "exprList", "qualifiedName", "variableExpr", "variable", "formula", 
			"arrayIdx", "array", "literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'\"'", null, null, null, "'&'", "'+'", "'-'", "'*'", "'/'", 
			"'^'", "'%'", "'$'", "'!'", "':'", "','", "'.'", "';'", "'('", "')'", 
			null, null, "'<='", "'>='", "'>'", "'<'", "'&&'", "'||'", "'{'", "'}'", 
			"'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "STRING", "QUOT", "IDENTIFIER", "NUMBER", "DIGIT", "AMP", "ADD", 
			"MINUS", "MUL", "DIV", "POWER", "PERCENT", "ABS", "EXCL", "COLON", "COMMA", 
			"DOT", "SEMI", "LPAR", "RPAR", "EQ", "NEQ", "LTEQ", "GTEQ", "GT", "LT", 
			"AND_OP", "OR_OP", "LBRA", "RBRA", "LSQU", "RSQU", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "VariableParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public VariableParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FormulaCallContext extends ExprContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public FormulaCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterFormulaCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitFormulaCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitFormulaCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiterContext extends ExprContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiterContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterLiter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitLiter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitLiter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MUL() { return getToken(VariableParserParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(VariableParserParser.DIV, 0); }
		public MulDivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ADD() { return getToken(VariableParserParser.ADD, 0); }
		public TerminalNode MINUS() { return getToken(VariableParserParser.MINUS, 0); }
		public AddSubContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarContext extends ExprContext {
		public VariableExprContext variableExpr() {
			return getRuleContext(VariableExprContext.class,0);
		}
		public VarContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParensContext extends ExprContext {
		public TerminalNode LPAR() { return getToken(VariableParserParser.LPAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(VariableParserParser.RPAR, 0); }
		public ParensContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterParens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitParens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExcelArrayContext extends ExprContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public ExcelArrayContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterExcelArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitExcelArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitExcelArray(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NameContext extends ExprContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public NameContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				_localctx = new VarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(19);
				variableExpr();
				}
				break;
			case 2:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(20);
				match(LPAR);
				setState(21);
				expr(0);
				setState(22);
				match(RPAR);
				}
				break;
			case 3:
				{
				_localctx = new LiterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(24);
				literal();
				}
				break;
			case 4:
				{
				_localctx = new ExcelArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(25);
				array();
				}
				break;
			case 5:
				{
				_localctx = new FormulaCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(26);
				formula();
				}
				break;
			case 6:
				{
				_localctx = new NameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(27);
				qualifiedName();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(38);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(36);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(30);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(31);
						((MulDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((MulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(32);
						expr(8);
						}
						break;
					case 2:
						{
						_localctx = new AddSubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(33);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(34);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==MINUS) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(35);
						expr(7);
						}
						break;
					}
					} 
				}
				setState(40);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(VariableParserParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(VariableParserParser.COMMA, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitExprList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			expr(0);
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(42);
				match(COMMA);
				setState(43);
				expr(0);
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualifiedNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(VariableParserParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(VariableParserParser.IDENTIFIER, i);
		}
		public TerminalNode ABS() { return getToken(VariableParserParser.ABS, 0); }
		public List<TerminalNode> DOT() { return getTokens(VariableParserParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(VariableParserParser.DOT, i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitQualifiedName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_qualifiedName);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ABS) {
				{
				setState(49);
				match(ABS);
				}
			}

			setState(52);
			match(IDENTIFIER);
			setState(57);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(53);
					match(DOT);
					setState(54);
					match(IDENTIFIER);
					}
					} 
				}
				setState(59);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableExprContext extends ParserRuleContext {
		public TerminalNode ABS() { return getToken(VariableParserParser.ABS, 0); }
		public TerminalNode LBRA() { return getToken(VariableParserParser.LBRA, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode RBRA() { return getToken(VariableParserParser.RBRA, 0); }
		public List<TerminalNode> DOT() { return getTokens(VariableParserParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(VariableParserParser.DOT, i);
		}
		public VariableExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterVariableExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitVariableExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitVariableExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableExprContext variableExpr() throws RecognitionException {
		VariableExprContext _localctx = new VariableExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variableExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(ABS);
			setState(61);
			match(LBRA);
			setState(62);
			variable();
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(63);
				match(DOT);
				setState(64);
				variable();
				}
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(70);
			match(RBRA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(VariableParserParser.IDENTIFIER, 0); }
		public List<ArrayIdxContext> arrayIdx() {
			return getRuleContexts(ArrayIdxContext.class);
		}
		public ArrayIdxContext arrayIdx(int i) {
			return getRuleContext(ArrayIdxContext.class,i);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(IDENTIFIER);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LSQU) {
				{
				{
				setState(73);
				arrayIdx();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulaContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(VariableParserParser.IDENTIFIER, 0); }
		public TerminalNode LPAR() { return getToken(VariableParserParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(VariableParserParser.RPAR, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitFormula(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_formula);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(IDENTIFIER);
			setState(80);
			match(LPAR);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << IDENTIFIER) | (1L << NUMBER) | (1L << ABS) | (1L << LPAR))) != 0)) {
				{
				setState(81);
				exprList();
				}
			}

			setState(84);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayIdxContext extends ParserRuleContext {
		public TerminalNode LSQU() { return getToken(VariableParserParser.LSQU, 0); }
		public TerminalNode RSQU() { return getToken(VariableParserParser.RSQU, 0); }
		public TerminalNode NUMBER() { return getToken(VariableParserParser.NUMBER, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public ArrayIdxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayIdx; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterArrayIdx(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitArrayIdx(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitArrayIdx(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayIdxContext arrayIdx() throws RecognitionException {
		ArrayIdxContext _localctx = new ArrayIdxContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_arrayIdx);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(LSQU);
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(87);
				match(NUMBER);
				}
				break;
			case IDENTIFIER:
			case ABS:
				{
				setState(88);
				qualifiedName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(91);
			match(RSQU);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(VariableParserParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(VariableParserParser.IDENTIFIER, i);
		}
		public TerminalNode COLON() { return getToken(VariableParserParser.COLON, 0); }
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_array);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(IDENTIFIER);
			setState(94);
			match(COLON);
			setState(95);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public Token type;
		public TerminalNode STRING() { return getToken(VariableParserParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(VariableParserParser.NUMBER, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VariableParserListener ) ((VariableParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VariableParserVisitor ) return ((VariableParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			((LiteralContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==NUMBER) ) {
				((LiteralContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#f\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\37\n\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\'\n"+
		"\2\f\2\16\2*\13\2\3\3\3\3\3\3\7\3/\n\3\f\3\16\3\62\13\3\3\4\5\4\65\n\4"+
		"\3\4\3\4\3\4\7\4:\n\4\f\4\16\4=\13\4\3\5\3\5\3\5\3\5\3\5\7\5D\n\5\f\5"+
		"\16\5G\13\5\3\5\3\5\3\6\3\6\7\6M\n\6\f\6\16\6P\13\6\3\7\3\7\3\7\5\7U\n"+
		"\7\3\7\3\7\3\b\3\b\3\b\5\b\\\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\2"+
		"\3\2\13\2\4\6\b\n\f\16\20\22\2\5\3\2\13\f\3\2\t\n\4\2\3\3\6\6\2j\2\36"+
		"\3\2\2\2\4+\3\2\2\2\6\64\3\2\2\2\b>\3\2\2\2\nJ\3\2\2\2\fQ\3\2\2\2\16X"+
		"\3\2\2\2\20_\3\2\2\2\22c\3\2\2\2\24\25\b\2\1\2\25\37\5\b\5\2\26\27\7\25"+
		"\2\2\27\30\5\2\2\2\30\31\7\26\2\2\31\37\3\2\2\2\32\37\5\22\n\2\33\37\5"+
		"\20\t\2\34\37\5\f\7\2\35\37\5\6\4\2\36\24\3\2\2\2\36\26\3\2\2\2\36\32"+
		"\3\2\2\2\36\33\3\2\2\2\36\34\3\2\2\2\36\35\3\2\2\2\37(\3\2\2\2 !\f\t\2"+
		"\2!\"\t\2\2\2\"\'\5\2\2\n#$\f\b\2\2$%\t\3\2\2%\'\5\2\2\t& \3\2\2\2&#\3"+
		"\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)\3\3\2\2\2*(\3\2\2\2+\60\5\2\2\2"+
		",-\7\22\2\2-/\5\2\2\2.,\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2"+
		"\61\5\3\2\2\2\62\60\3\2\2\2\63\65\7\17\2\2\64\63\3\2\2\2\64\65\3\2\2\2"+
		"\65\66\3\2\2\2\66;\7\5\2\2\678\7\23\2\28:\7\5\2\29\67\3\2\2\2:=\3\2\2"+
		"\2;9\3\2\2\2;<\3\2\2\2<\7\3\2\2\2=;\3\2\2\2>?\7\17\2\2?@\7\37\2\2@E\5"+
		"\n\6\2AB\7\23\2\2BD\5\n\6\2CA\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH"+
		"\3\2\2\2GE\3\2\2\2HI\7 \2\2I\t\3\2\2\2JN\7\5\2\2KM\5\16\b\2LK\3\2\2\2"+
		"MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\13\3\2\2\2PN\3\2\2\2QR\7\5\2\2RT\7\25"+
		"\2\2SU\5\4\3\2TS\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\7\26\2\2W\r\3\2\2\2X[\7"+
		"!\2\2Y\\\7\6\2\2Z\\\5\6\4\2[Y\3\2\2\2[Z\3\2\2\2\\]\3\2\2\2]^\7\"\2\2^"+
		"\17\3\2\2\2_`\7\5\2\2`a\7\21\2\2ab\7\5\2\2b\21\3\2\2\2cd\t\4\2\2d\23\3"+
		"\2\2\2\f\36&(\60\64;ENT[";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}