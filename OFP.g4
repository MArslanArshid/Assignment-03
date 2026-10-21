grammar OFP; // Define a grammar called OFP

@header {
    package generated;
} //Defining package for java files

//Syntax Specification ==> CFG

program : functionDeclare* maindeclaration functionDeclare* ; //Program can have multiple functions. //new.

singleparameter : ( 'int' | 'float' | 'char') ('[' ']')? ID | ('bool' | 'string') ID; //Only for single Parameter //new.

multiparameter : singleparameter (',' singleparameter)* ; //For multiple parameters.
 
stmt : assign                                #AssignStmt
     | ('print' | 'println') '('expr')'';'   #PrintStmt
     | variableDeclare                       #VarDeclStmt
     | whileStatement                        #WhileStmt
     | ifStatement                           #IfStmt
     | returnStatement                       #ReturnStmt
     | callstatement                         #CallStmt
     ;

expr : '('expr')'                       #ParanExpr
     |'-' expr                          #NegExpr
     |expr '[' expr ']'                 #ArrayAccessExpr
     |expr '.' 'length'                 #LengthExpr
     |expr ('*' | '/') expr             #MulDivExpr
     |expr ('+' | '-') expr             #AddSubExpr
     |expr ('<' | '>'| '==') expr       #RelEqExpr
     | INT                              #IntLitExpr
     | ID                               #IdExpr
     | BOOL                             #BoolLitExpr
     | FLOAT                            #FloatLitExpr
     | STRING                           #StringLitExpr
     | CHAR                             #CharLitExpr
     | sumFunctioncall                  #FunCallExpr
     | arrayLiteral                     #ArrayLitExpr
     | arraywithnewkeyword              #NewArrayExpr
    ;


assign : ID '=' expr ';'
       | ID '[' expr ']' '=' expr ';'
       ;
      

variableDeclare : ('int' ('['']')? | 'float' ('['']')? | 'bool' | 'char' ('['']')? | 'string') ID ('=' expr)? ';' ; //Declaring a variable with or without value.

functionDeclare : ('void'|'int'| 'float' | 'char') ('[' ']')? ID '(' multiparameter? ')' '{' stmt* '}' | ('bool'|'string') ID '(' multiparameter?')' '{' stmt* '}' ; //yeh function declare kay liyay hai. is may sirf void, int, float, bool, char, string allowed hain aur us kay sath ID allowed hain. example: void myFunction(int a, float b) { a = 5; b = 10; } int myFunction(int a) { a = 5; } float myFunction(float b) { b = 10; } bool myFunction(bool c) { c = true; } char myFunction(char d) { d = 'a'; } string myFunction(string e) { e = "Hello"; } //new.

maindeclaration : 'void' 'main' '(' ')' '{' stmt* '}';  

whileStatement : 'while' '(' expr ')' ifandWhileHelpers ; //While loop wala part.

ifStatement : 'if' '(' expr ')' ifandWhileHelpers ('else' ifandWhileHelpers)? ; //if lazmi else if 0 ya ik bar or lastly else optional with ?. //new.

ifandWhileHelpers : '{' stmt* '}' | stmt ; //yeh if aur while kay liyay helper kaam karay ga. is may sirf {} allowed hain aur us kay sath stmt allowed hain. example: { a = 5; b = 10; c = 15; d = 20; e = 25; } ya phir stmt; //new.

sumFunctioncall : ID '(' argumentList? ')' ; //yeh function call kay liyay hai. is may sirf ID allowed hai aur us kay sath () allowed hain. example: sum(a, b); sum(a); sum();

argumentList : expr (',' expr)* ; //yeh function call kay liyay hai. is may sirf ID allowed hai aur us kay sath () allowed hain. example: sum(a, b); sum(a); sum();

returnStatement : 'return' expr ';' ; //yeh return statement kay liyay hai. is may sirf return allowed hai aur us kay sath expr allowed hain. example: return a; return b; return c; return d; return e;

arrayLiteral : '{'argumentList'}' ; //argumentList ko use kar kay array literal banai gyi hai. is may sirf {} allowed hain aur us kay sath argumentList allowed hain. example: {a, b, c, d, e}; line 30.    

arraywithnewkeyword : 'new' ('int' | 'float'| 'char') '[' expr ']' ; //yeh array kay liyay hai. is may sirf new keyword allowed hai aur us kay sath [] allowed hain. example: new int[5]; new float[10]; new char[15];
 
callstatement : ID '('argumentList?')' ';' ; //new.



//Lexer Specification ==> RE

fragment HELPER :('a'..'z' | 'A'..'Z'); // Fragment yahan sirf ik helper kay tor par kam krta hay na keh indpendent token ke trha. yeh string kay liyay help karay ga string may sirf speific cheezain a skti hain un kay liyay helper banaya gya hai. is may sirf small and capital letters allowed hain.

STRING : '"' (HELPER | '!' | '.' | ',' | '?' |'=' | ':' | '(' | ')' | ' ')* '"' ; //yeh sirf string kay liyay hai. is may sirf small and capital letters allowed hain. aur kuch special characters allowed hain. like ! . , ? = : ( ) and space.

INT : '0' | [1-9][0-9]* ; 

BOOL : 'true' | 'false' ; // For boolean values.

ID : HELPER HELPER* ; //Helper ko use kar kay ID banai gyi hai. ID may small and capital letters allowed hain aur numbers bhi allowed hain.

FLOAT : ('0' | [1-9][0-9]*) '.' [0-9]+ ; 

CHAR : '\'' ( HELPER | '!' | '.' | ',' | '?' | '=' | ':' | '(' | ')' | ' ') '\'' ; // Yeh char kay liyay hai. is may sirf small and capital letters allowed hain aur single quotes bhi allowed hain. example: 'a', 'A', 'b', 'B'

WHITESPACES : [ \t\r\n]+ -> skip ; // For skipping tab, newlines.
 
COMMENT : '#' ~[\r\n]* -> skip ; //Yeh line comment kay liyay hai. is may sirf # allowed hain aur us kay sath koi bhi character allowed hain. like a-z, A-Z, 0-9, ! . , ? = : ( ) and space. 


