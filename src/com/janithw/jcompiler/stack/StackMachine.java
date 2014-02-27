package com.janithw.jcompiler.stack;

import com.janithw.jcompiler.lexer.Float;
import com.janithw.jcompiler.lexer.Int;
import com.janithw.jcompiler.lexer.Token;
import com.janithw.jcompiler.lexer.Type;
import com.janithw.jcompiler.lexer.ValuedToken;

public class StackMachine {

	private Stack stack;

	public StackMachine() {
		stack = new Stack();
	}

	public void push(Token token) {
		stack.push(token);
	}

	public void evaluate(char op) {
		ValuedToken num1 = (ValuedToken) stack.pop();
		ValuedToken num2 = (ValuedToken) stack.pop();
		if(num1.getVal() instanceof java.lang.Float || num2.getVal() instanceof java.lang.Float){
			float ans = 0;
			if (op == '+') {
				ans = getFloat(num1)+getFloat(num2);
			} else if (op == '*') {
				ans = getFloat(num1)*getFloat(num2);
			}
			stack.push(new Float(ans));
		}else{
			int ans = 0;
			if (op == '+') {
				ans = getInt(num1)+getInt(num2);
			} else if (op == '*') {
				ans = getInt(num1)*getInt(num2);
			}
			stack.push(new Int(ans));
		}
		
	}

	private float getFloat(ValuedToken num) {
		return java.lang.Float.parseFloat(num.getVal().toString());
	}
	
	private int getInt(ValuedToken num) {
		return Integer.parseInt(num.getVal().toString());
	}

	public Object getCurrentValue(){
		return ((ValuedToken)stack.peek()).getVal();
	}
	
	public Type getCurrentType(){
		if(getCurrentValue() instanceof Integer){
			return Type.IntType;
		}else if(getCurrentValue() instanceof java.lang.Float){
			return Type.FloatType;
		}else{
			//Shouldnt come here
			throw new RuntimeException("Type conversion error");
		}
	}
}
