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
Name = [:jletter:][:jletterdigit:]*

/* floating point literals */
Number = \-?\d*\.?\d+


/* string and character literals */
StringCharacter = [^\r\n\"\\]

%state STRING

%%

<YYINITIAL> {

  /* keywords */
  "set"                         {return symbol(SET);}
  "as"                          {return symbol(AS);}
  "in case that"                {return symbol(IF);}
  "fallback"                    {return symbol(ELSE);}
  "as long as"                  {return symbol(WHILE);}
  "task"                        {return symbol(FUNCTION);}
  "takes"                       {return symbol(PARAMS);}
  "execute"                     {return symbol(FUNCTION_CALL);}
  "with"                        {return symbol(FUNCTION_PARAMS);}

  /* operators */
  "="                            { return symbol(EQ); }
  "equal than"                   { return symbol(EQEQ); }
  "lower than"                   { return symbol(LT); }
  "greater than"                 { return symbol(GT); }
  "lower or equal than"          { return symbol(LTEQ); }
  "greater or equal than"        { return symbol(GTEQ); }
  "not equal than"               { return symbol(NOTEQ); }

  "add"                          { return symbol(PLUS); }
  "substract"                    { return symbol(MINUS); }
  "multiply"                     { return symbol(MULTIPLY); }
  "divide"                       { return symbol(DIVIDE); }

  /* separators */
  "("                            { return symbol(LPAREN); }
  ")"                            { return symbol(RPAREN); }
//  "["                          { return symbol(LBRACK); }
//  "]"                          { return symbol(RBRACK); }
  "."                            { return symbol(DOT); }


  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }


  /* numeric literals */
  {Number}                { return symbol(NUMBER, new Double(yytext())); }
  {Number}[dD]            { return symbol(NUMBER, new Double(yytext().substring(0,yylength()-1))); }
  


  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }


  /* identifiers */ 
  {Name}                         { return symbol(NAME, yytext()); }
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
