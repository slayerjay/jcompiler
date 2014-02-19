package com.janithw.jcompiler.lexer;

public class Id extends Token {

	public final String lexeme;

	public Id(int t, String lexeme) {
		super(t);
		this.lexeme = lexeme;
	}

	public String getLexeme() {
		return lexeme;
	}

}
