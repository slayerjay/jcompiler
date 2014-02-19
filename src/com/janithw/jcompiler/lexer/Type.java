package com.janithw.jcompiler.lexer;

public class Type extends Id {
	
	public static final Type 
		IntType = new Type(Tag.INT_KEYWORD, "int", 4),
		FloatType = new Type(Tag.FLOAT_KEYWORD, "float", 8);
			
	
	
	
	private int width;

	public Type(int t, String lexeme, int w) {
		super(t, lexeme);
		width = w;
	}

}
