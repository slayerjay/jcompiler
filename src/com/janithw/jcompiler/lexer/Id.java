package com.janithw.jcompiler.lexer;

public class Id extends Token implements ValuedToken{

	public final String lexeme;
	public Object val;

	public Id(int t, String lexeme) {
		super(t);
		this.lexeme = lexeme;
	}

	public String getLexeme() {
		return lexeme;
	}
	
	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return getLexeme();
	}

}
