package com.janithw.jcompiler.inter;

public class Id extends Expr {

	public Id(com.janithw.jcompiler.lexer.Id id) {
		super(id, id.getType());
	}

	public Object getVal() {
		return ((com.janithw.jcompiler.lexer.Id) getToken()).getVal();
	}

}
