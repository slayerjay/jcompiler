package com.janithw.jcompiler.inter;

import com.janithw.jcompiler.lexer.Tag;
import com.janithw.jcompiler.lexer.Type;

public class Temp extends Expr {

	static int count = 0;
	int number = 0;

	public Temp(Type p) {
		super(new com.janithw.jcompiler.lexer.Id(Tag.TEMP, "t"), p);
		number = ++count;
	}

	public String toString() {
		return "t" + number;
	}
}
