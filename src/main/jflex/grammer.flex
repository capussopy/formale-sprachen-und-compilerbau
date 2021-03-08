import java_cup.runtime.*;

%%

%public
%class Scanner
%implements sym

%unicode

%line
%column

%cup
%cupdebug

%{
  StringBuilder string = new StringBuilder();
  
  private Symbol symbol(int type) {
    return new JavaSymbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new JavaSymbol(type, yyline+1, yycolumn+1, value);
  }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]


/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* floating point literals */
Number = [0-9]+ \. [0-9]*


/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

%state STRING

%%

<YYINITIAL> {

  /* separators */
  "("                            { return symbol(LPAREN); }
  ")"                            { return symbol(RPAREN); }
  "{"                            { return symbol(LBRACE); }
  "}"                            { return symbol(RBRACE); }
  "["                            { return symbol(LBRACK); }
  "]"                            { return symbol(RBRACK); }
  ";"                            { return symbol(SEMICOLON); }
  ","                            { return symbol(COMMA); }
  "."                            { return symbol(DOT); }
  
  /* operators */
  "="                            { return symbol(EQ); }
  "greater than"                 { return symbol(GT); }
  "lower than"                   { return symbol(LT); }
  "equal than"                   { return symbol(EQEQ); }
  "lower or equal than"          { return symbol(LTEQ); }
  "greater or equal than"        { return symbol(GTEQ); }
  "not equal than"               { return symbol(NOTEQ); }


  "add to"                       { return symbol(PLUS); }
  "substract from"               { return symbol(MINUS); }
  "multiply with"                { return symbol(MULT); }
  "divide through"               { return symbol(DIV); }

  /* condition */
  "in case that"                 {return symbol(IF);}
  "fallback"                     {return symbol(ELSE);}


  /* Loop */
  "as long as"                  {return symbol(WHILE);}
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }


  /* numeric literals */
  {Number}                { return symbol(FLOATING_POINT_LITERAL, new Double(yytext())); }
  {Number}[dD]            { return symbol(FLOATING_POINT_LITERAL, new Double(yytext().substring(0,yylength()-1))); }
  


  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }


  /* identifiers */ 
  {Identifier}                   { return symbol(IDENTIFIER, yytext()); }  
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(STRING_LITERAL, string.toString()); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }

  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}


/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+ "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(EOF); }