package com.janithw.jcompiler.lexer;

public class Type extends Id {

	public static final Type IntType = new Type(Tag.INT_KEYWORD, "int", 4),
			FloatType = new Type(Tag.FLOAT_KEYWORD, "float", 8);

	private int width;

	public Type(int t, String lexeme, int w) {
		super(t, lexeme);
		width = w;
	}

	public static boolean numeric(Type p) {
		if (p == Type.IntType || p == Type.FloatType)
			return true;
		else
			return false;
	}

	public static Type max(Type p1, Type p2) {
		if (!numeric(p1) || !numeric(p2))
			return null;
		else if (p1 == Type.FloatType || p2 == Type.FloatType)
			return Type.FloatType;
		else if (p1 == Type.IntType || p2 == Type.IntType)
			return Type.IntType;
		else
			return null;
	}
}
