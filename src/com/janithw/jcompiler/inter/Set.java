package com.janithw.jcompiler.inter;

import com.janithw.jcompiler.lexer.Type;

public class Set extends Stmt {

	public Id id;
	public Expr expr;

	public Set(Id i, Expr x) {
		id = i;
		expr = x;
		if (check(id.type, expr.type) == null)
			error("type error");
	}

	public Type check(Type p1, Type p2) {
		Type max = max(p1, p2);
		if (max == p1) {
			return max;
		}
		return null;
	}

	public void gen() {
		Expr e = expr.gen();
		e = e.reduce();
		e = widen(e, e.getType(), id.getType());
		emit(id.toString() + " = " + e.toString());
	}
}
