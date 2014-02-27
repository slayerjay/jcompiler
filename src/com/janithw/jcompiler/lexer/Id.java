package com.janithw.jcompiler.lexer;

public class Id extends Token implements ValuedToken{

	private final String lexeme;
	private Object val;
	private Type type;

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

	public void setType(Type type) {
		this.type = type;
	}

	public void setVal(Object val, Type type) {
		this.val = val;
		this.type = type;
	}

	@Override
	public String toString() {
		return getLexeme();
	}

	@Override
	public Type getType() {
		return type;
	}

}
