package com.janithw.jcompiler.lexer;

public class Id extends Token implements ValuedToken{

	private final String lexeme;
	private Object val;
	private String type;

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

	public void setType(String type) {
		this.type = type;
	}

	public void setVal(Object val, String type) {
		this.val = val;
		this.type = type;
	}

	@Override
	public String toString() {
		return getLexeme();
	}

	@Override
	public String getType() {
		return type;
	}

}
