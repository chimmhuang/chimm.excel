// Generated from /Users/huangshuai/IdeaProjects/chimm_excel/src/main/java/com/github/chimmhuang/parser/VariableParser.g4 by ANTLR 4.8
package com.github.chimmhuang.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class VariableParserLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, QUOT=2, IDENTIFIER=3, NUMBER=4, DIGIT=5, AMP=6, ADD=7, MINUS=8, 
		MUL=9, DIV=10, POWER=11, PERCENT=12, ABS=13, EXCL=14, COLON=15, COMMA=16, 
		DOT=17, SEMI=18, LPAR=19, RPAR=20, EQ=21, NEQ=22, LTEQ=23, GTEQ=24, GT=25, 
		LT=26, AND_OP=27, OR_OP=28, LBRA=29, RBRA=30, LSQU=31, RSQU=32, WS=33;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"STRING", "QUOT", "IDENTIFIER", "NUMBER", "DIGIT", "AMP", "ADD", "MINUS", 
			"MUL", "DIV", "POWER", "PERCENT", "ABS", "EXCL", "COLON", "COMMA", "DOT", 
			"SEMI", "LPAR", "RPAR", "EQ", "NEQ", "LTEQ", "GTEQ", "GT", "LT", "AND_OP", 
			"OR_OP", "LBRA", "RBRA", "LSQU", "RSQU", "WS"
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


	public VariableParserLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "VariableParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u00b1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\7\2H\n\2\f\2\16\2K\13\2\3\2\3\2\3\3\3\3\3\4\3\4\7"+
		"\4S\n\4\f\4\16\4V\13\4\3\5\6\5Y\n\5\r\5\16\5Z\3\5\3\5\6\5_\n\5\r\5\16"+
		"\5`\5\5c\n\5\3\6\6\6f\n\6\r\6\16\6g\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\5\26\u008b\n\26\3\27"+
		"\3\27\3\27\3\27\5\27\u0091\n\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!"+
		"\3!\3\"\6\"\u00ac\n\"\r\"\16\"\u00ad\3\"\3\"\3I\2#\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#\3\2\6\5\2"+
		"C\\aac|\6\2\62;C\\aac|\3\2\62;\5\2\13\f\17\17\"\"\2\u00b9\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5N\3\2\2\2\7P\3\2\2\2"+
		"\tX\3\2\2\2\13e\3\2\2\2\ri\3\2\2\2\17k\3\2\2\2\21m\3\2\2\2\23o\3\2\2\2"+
		"\25q\3\2\2\2\27s\3\2\2\2\31u\3\2\2\2\33w\3\2\2\2\35y\3\2\2\2\37{\3\2\2"+
		"\2!}\3\2\2\2#\177\3\2\2\2%\u0081\3\2\2\2\'\u0083\3\2\2\2)\u0085\3\2\2"+
		"\2+\u008a\3\2\2\2-\u0090\3\2\2\2/\u0092\3\2\2\2\61\u0095\3\2\2\2\63\u0098"+
		"\3\2\2\2\65\u009a\3\2\2\2\67\u009c\3\2\2\29\u009f\3\2\2\2;\u00a2\3\2\2"+
		"\2=\u00a4\3\2\2\2?\u00a6\3\2\2\2A\u00a8\3\2\2\2C\u00ab\3\2\2\2EI\5\5\3"+
		"\2FH\13\2\2\2GF\3\2\2\2HK\3\2\2\2IJ\3\2\2\2IG\3\2\2\2JL\3\2\2\2KI\3\2"+
		"\2\2LM\5\5\3\2M\4\3\2\2\2NO\7$\2\2O\6\3\2\2\2PT\t\2\2\2QS\t\3\2\2RQ\3"+
		"\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\b\3\2\2\2VT\3\2\2\2WY\5\13\6\2X"+
		"W\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[b\3\2\2\2\\^\5#\22\2]_\5\13\6"+
		"\2^]\3\2\2\2_`\3\2\2\2`^\3\2\2\2`a\3\2\2\2ac\3\2\2\2b\\\3\2\2\2bc\3\2"+
		"\2\2c\n\3\2\2\2df\t\4\2\2ed\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\f\3"+
		"\2\2\2ij\7(\2\2j\16\3\2\2\2kl\7-\2\2l\20\3\2\2\2mn\7/\2\2n\22\3\2\2\2"+
		"op\7,\2\2p\24\3\2\2\2qr\7\61\2\2r\26\3\2\2\2st\7`\2\2t\30\3\2\2\2uv\7"+
		"\'\2\2v\32\3\2\2\2wx\7&\2\2x\34\3\2\2\2yz\7#\2\2z\36\3\2\2\2{|\7<\2\2"+
		"| \3\2\2\2}~\7.\2\2~\"\3\2\2\2\177\u0080\7\60\2\2\u0080$\3\2\2\2\u0081"+
		"\u0082\7=\2\2\u0082&\3\2\2\2\u0083\u0084\7*\2\2\u0084(\3\2\2\2\u0085\u0086"+
		"\7+\2\2\u0086*\3\2\2\2\u0087\u008b\7?\2\2\u0088\u0089\7?\2\2\u0089\u008b"+
		"\7?\2\2\u008a\u0087\3\2\2\2\u008a\u0088\3\2\2\2\u008b,\3\2\2\2\u008c\u008d"+
		"\7>\2\2\u008d\u0091\7@\2\2\u008e\u008f\7#\2\2\u008f\u0091\7?\2\2\u0090"+
		"\u008c\3\2\2\2\u0090\u008e\3\2\2\2\u0091.\3\2\2\2\u0092\u0093\7>\2\2\u0093"+
		"\u0094\7?\2\2\u0094\60\3\2\2\2\u0095\u0096\7@\2\2\u0096\u0097\7?\2\2\u0097"+
		"\62\3\2\2\2\u0098\u0099\7@\2\2\u0099\64\3\2\2\2\u009a\u009b\7>\2\2\u009b"+
		"\66\3\2\2\2\u009c\u009d\7(\2\2\u009d\u009e\7(\2\2\u009e8\3\2\2\2\u009f"+
		"\u00a0\7~\2\2\u00a0\u00a1\7~\2\2\u00a1:\3\2\2\2\u00a2\u00a3\7}\2\2\u00a3"+
		"<\3\2\2\2\u00a4\u00a5\7\177\2\2\u00a5>\3\2\2\2\u00a6\u00a7\7]\2\2\u00a7"+
		"@\3\2\2\2\u00a8\u00a9\7_\2\2\u00a9B\3\2\2\2\u00aa\u00ac\t\5\2\2\u00ab"+
		"\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\b\"\2\2\u00b0D\3\2\2\2\f\2ITZ`bg"+
		"\u008a\u0090\u00ad\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}