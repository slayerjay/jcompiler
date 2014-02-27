package com.janithw.jcompiler.lexer;

public class Token {

	private final int tag;

	public Token(int tag) {
		this.tag = tag;
	}

	public int tag() {
		return tag;
	}
	
	@Override
	public String toString() {
		return String.valueOf((char)tag);
	}
}
