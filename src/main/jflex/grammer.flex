import java_cup.runtime.*;
import java.math.BigDecimal;

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
  "in case that"                {return symbol(IN_CASE_THAT);}
  "fallback"                    {return symbol(FALLBACK);}
  "as long as"                  {return symbol(AS_LONG_AS);}
  "task"                        {return symbol(TASK);}
  "takes"                       {return symbol(TAKES);}
  "execute"                     {return symbol(EXECUTE);}
  "with"                        {return symbol(WITH);}

  /* operators */
  "equal"                       { return symbol(EQUAL); }
  "lower"                        { return symbol(LOWER); }
  "greater"                      { return symbol(GREATER); }
  "lower or equal"               { return symbol(LOWER_OR_EQUAL); }
  "greater or equal"             { return symbol(GREATER_OR_EQUAL); }
  "not equal"                    { return symbol(NOT_EQUAL); }

  "add"                          { return symbol(PLUS); }
  "substract"                    { return symbol(MINUS); }
  "multiply"                     { return symbol(MULTIPLY); }
  "divide"                       { return symbol(DIVIDE); }

  /* separators */
  "["                          { return symbol(LBRACK); }
  "]"                          { return symbol(RBRACK); }
  ","                            { return symbol(COMMA); }


  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }


  /* numeric literals */
  {Number}                { return symbol(NUMBER, new BigDecimal(yytext())); }
  {Number}[dD]            { return symbol(NUMBER, new BigDecimal(yytext().substring(0,yylength()-1))); }
  


  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }


  /* identifiers */ 
  {Name}                         { return symbol(NAME, yytext()); }
}

<STRING> {
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
