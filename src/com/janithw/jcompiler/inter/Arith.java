package com.janithw.jcompiler.inter;

import com.janithw.jcompiler.lexer.Token;

public class Arith extends Op {

	public Expr expr1, expr2;

	public Arith(Token op, Expr x1, Expr x2) {
		super(op, null);
		expr1 = x1;
		expr2 = x2;
		type = max(expr1.type, expr2.type);
		if (type == null)
			error("type error");
	}

	public Expr gen() {
		Expr e1 = expr1.reduce();
		Expr e2 = expr2.reduce();
		Expr a1 = widen(e1, e1.getType(), type);
		Expr a2 = widen(e2, e2.getType(), type);
		return new Arith(super.getToken(), a1, a2);
	}

	public String toString() {
		return expr1.toString() + " " + super.getToken().toString() + " "
				+ expr2.toString();
	}
}
