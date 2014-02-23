package com.janithw.jcompiler.lexer;

public class Int extends Token {
	
	private int val;

	public Int(int val) {
		super(Tag.INT);
		this.val = val;
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}

	public int getVal() {
		return val;
	}

}
