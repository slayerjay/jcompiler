package com.janithw.jcompiler.lexer;

public class Float extends Token {

	private float val;
	
	public Float(float val) {
		super(Tag.FLOAT);
		this.val = val;
	}

}
