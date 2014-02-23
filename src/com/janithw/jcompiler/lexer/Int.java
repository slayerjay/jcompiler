package com.janithw.jcompiler.lexer;

public class Int extends Token implements ValuedToken {
	
	private int val;

	public Int(int val) {
		super(Tag.INT);
		this.val = val;
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}

	public Object getVal() {
		return val;
	}

	@Override
	public String getType() {
		return "int";
	}

}
