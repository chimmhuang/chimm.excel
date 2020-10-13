grammar VariableParser;

expr
    : variableExpr                             # Var
    | expr op=(MUL|DIV) expr                   # MulDiv
    | expr op=(ADD|MINUS) expr                 # AddSub
    | LPAR expr RPAR                           # Parens
    | literal                                  # Liter
    | array                                    # ExcelArray
    | formula                                  # FormulaCall
    | qualifiedName                            # Name
    ;

exprList
    : expr (',' expr)*
    ;

qualifiedName
    :   (ABS)? IDENTIFIER ( DOT IDENTIFIER )*
    ;

variableExpr
    :  ABS LBRA variable  (DOT variable)* RBRA
    ;

variable
    :  IDENTIFIER (arrayIdx)*
    ;

formula
    : IDENTIFIER LPAR  exprList?  RPAR
    ;

arrayIdx
    :  LSQU (NUMBER|qualifiedName) RSQU
    ;

array
    :  IDENTIFIER COLON IDENTIFIER
    ;

literal
    : type=(STRING | NUMBER)
    ;

/* String literals, i.e. anything inside the delimiters */
STRING
	:	QUOT .*? QUOT
	;

QUOT: '"';

IDENTIFIER
// :'"' (~'"' | '""')* '"'
// | '`' (~'`' | '``')* '`'
// | '[' ~']'* ']'
 : [a-zA-Z_] [a-zA-Z_0-9]*
 ;

/* Numeric literals */
NUMBER: ( DIGIT )+ ( DOT ( DIGIT )+ )? ;
DIGIT : [0-9]+ ;

/* Text operators */
AMP		: '&' ;

/* Arithmetic operators */
ADD	    : '+' ;
MINUS	: '-' ;
MUL	    : '*' ;
DIV		: '/' ;
POWER	: '^' ;
PERCENT : '%' ;

/* Reference operators */
ABS     : '$' ;
EXCL    : '!' ;
COLON   : ':' ;

/* Miscellaneous operators */
COMMA	: ',' ;
DOT	    : '.' ;
SEMI	: ';' ;
LPAR	: '(' ;
RPAR	: ')' ;

/* Comparison operators */
EQ		: '=' | '==' ;
NEQ		: '<>' | '!=' ;
LTEQ	: '<=' ;
GTEQ	: '>=' ;
GT		: '>' ;
LT		: '<' ;
AND_OP	: '&&' ;
OR_OP	: '||' ;
LBRA    : '{'  ;
RBRA    : '}'  ;
LSQU    : '['  ;
RSQU    : ']'  ;

/* White-space (ignored) */
WS : [ \r\t\n]+ -> skip ;