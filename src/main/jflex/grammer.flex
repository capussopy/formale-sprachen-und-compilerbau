package ch.capasso.fac;

%%

%public
%class Subst
%standalone

%unicode

%{
  String name;
%}

%%

"name " [a-zA-Z]+  { name = yytext().substring(5); }
[Hh] "ello"        { System.out.print(yytext()+" "+name+"!"); }
