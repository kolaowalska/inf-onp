# INF &lt;=> ONP conversion program

This project implements a Java program that converts mathematical expressions between infix notation (INF) and Reverse Polish notation (RPN/ONP). 
Key features include: 
* INF to ONP conversion - uses a finite-state machine to validate input and convert expressions from INF to ONP
* ONP to INF conversion - uses a stack-based validation for operator and operand counts to convert expressions from ONP to INF
* Stack implementation - uses custom stack classes to manage operator precedence and operand associations during conversion
* Error handling - detects and reports invalid syntax in expressions, such as mismatched parentheses and incorrect operator placement

The program recognizes the following operators:
- Unary: `!`, `~`
- Binary: `^`, `*`, `/`, `%`, `+`, `-`, `<`, `>`, `&`, `|`, `=`

Operators follow standard precedence rules, with unary operators having higher precedence than binary operators. 

__Input format__\
The program accepts multiple expressions (the number of them should be specified), with each line starting with either `INF:` or `ONP:`, followed by the expression. It does not handle non-standard characters in expressions.

Example input:
```
2
INF: ((p&e)*!j)^~m^(k-x)&((j>d)+m/g&j<(b>a))
ONP: kx^~qo+~%zyc%jv</-<
```
Expected output:
```
ONP: p e & j ! * m ~ k x - ^ ^ j d > m g / + j b a > < & &
INF: ~ ( k ^ x ) % ~ ( q + o ) < z - y % c / ( j < v )
```

Prerequisites: JDK 11
