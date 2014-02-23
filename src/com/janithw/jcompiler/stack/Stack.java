package com.janithw.jcompiler.stack;

import com.janithw.jcompiler.lexer.Token;

public class Stack {

	private java.util.Stack<Token> stack;

	public Stack() {
		stack = new java.util.Stack<Token>();
	}

	public void push(Token token) {
		stack.push(token);
	}

	public Token pop() {
		return stack.pop();
	}
	
	public Token peek(){
		return stack.peek();
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}
}
