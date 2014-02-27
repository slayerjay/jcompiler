package com.janithw.jcompiler.inter;

import com.janithw.jcompiler.lexer.Token;
import com.janithw.jcompiler.lexer.Type;

public class Constant extends Expr {
	public Constant(Token tok, Type p) {
		super(tok, p);
	}
}
