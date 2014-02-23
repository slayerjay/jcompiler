package com.janithw.jcompiler.lexer;

public class Float extends Token implements ValuedToken{

	private float val;
	
	public Float(float val) {
		super(Tag.FLOAT);
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
		return "float";
	}
	
	

}
