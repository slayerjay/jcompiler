package com.janithw.jcompiler.stack;

import com.janithw.jcompiler.lexer.Float;
import com.janithw.jcompiler.lexer.Id;
import com.janithw.jcompiler.lexer.Int;
import com.janithw.jcompiler.lexer.Token;

public class StackMachine {

	private Stack stack;

	public StackMachine() {
		stack = new Stack();
	}

	public void push(Token token) {
		stack.push(token);
	}

	public void evaluate(char op) {
		//No type checking yet. Everything is float
		Token num1 = stack.pop();
		Token num2 = stack.pop();
		float n1 = getVal(num1), n2 = getVal(num2);
		float ans = 0;
		if (op == '+') {
			ans = n1 + n2;
		} else if (op == '*') {
			ans = n1 * n2;
		}
		stack.push(new Float(ans));
	}

	private float getVal(Token num) {
		if (num instanceof Int) {
			return ((Int) num).getVal();
		}
		if (num instanceof Float) {
			return ((Float) num).getVal();
		}
		if(num instanceof Id){
			return java.lang.Float.parseFloat(((Id) num).getVal().toString());
		}
		return (float) 0.0;

	}

	public Object getCurrentValue(){
		return getVal(stack.peek());
	}
}
