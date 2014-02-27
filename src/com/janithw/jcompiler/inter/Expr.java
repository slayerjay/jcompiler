package com.janithw.jcompiler.inter;

import com.janithw.jcompiler.lexer.Token;
import com.janithw.jcompiler.lexer.Type;

public class Expr extends Node {

	public Token tok;
	public Type type;

	Expr(Token tok, Type p) {
		this.tok = tok;
		type = p;
	}

	public Expr gen() {
		return this;
	}

	public Expr reduce() {
		return this;
	}

	public String toString() {
		return tok.toString();
	}

	public Token getToken() {
		return tok;
	}

	public Type getType() {
		return type;
	}
}
