package com.janithw.jcompiler.parser;

/**
 * Parser's CFG and the related SDT scheme
 * P -> D L
 * D -> B N ; D1
 * D1-> D | epsilon
 * B -> int | float
 * N -> id N1
 * N1-> , id N1 | epsilon
 * L -> S ; L1
 * L1-> L | epsilon
 * S -> id = E | E
 * E -> T E1
 * E1-> + T {print('+')} T1 | epsilon		
 * T -> F T1
 * T1-> * F {print('*')} T1 | epsilon
 * F -> (E) | num {print(num)} | id {print(id)}
 * 
 */

import java.io.IOException;

import com.janithw.jcompiler.lexer.Id;
import com.janithw.jcompiler.lexer.Lexer;
import com.janithw.jcompiler.lexer.Tag;
import com.janithw.jcompiler.lexer.Token;
import com.janithw.jcompiler.stack.StackMachine;

public class Parser {

	private Lexer scanner;

	private Token lookahead;
	
	private StringBuffer postfixBuffer;
	
	private StackMachine stackMachine;

	public Parser(Lexer scanner) throws IOException {
		this.scanner = scanner;
		postfixBuffer = new StringBuffer();
		stackMachine = new StackMachine();
		scanNext();
	}

	private void scanNext() throws IOException {
		lookahead = scanner.scan();
	}

	private void match(int t) throws IOException {
		if (lookahead.tag() == t) {
			scanNext();
		} else {
			error("Syntax Error");
		}
	}

	private void error(String string) {
		throw new RuntimeException(string);

	}

	public void P() throws IOException {
		D();
		L();
	}

	private void D() throws IOException {
		B();
		N();
		match(';');
		D1();

	}

	private void B() throws IOException {
		if (lookahead.tag() == Tag.INT_KEYWORD) {
			match(Tag.INT_KEYWORD);
		} else if (lookahead.tag() == Tag.FLOAT_KEYWORD) {
			match(Tag.FLOAT_KEYWORD);
		} else {
			error("Syntax Error");
		}

	}

	private void N() throws IOException {
		match(Tag.ID);
		N1();
	}

	private void N1() throws IOException {
		if (lookahead.tag() == ',') {
			match(',');
			match(Tag.ID);
			N1();
		} else {
			// empty string
		}

	}

	private void D1() throws IOException {
		if (lookahead.tag() == Tag.INT_KEYWORD
				|| lookahead.tag() == Tag.FLOAT_KEYWORD) {
			D();
		} else {

		}

	}

	private void L() throws IOException {
		S();
		match(';');
		L1();
	}

	private void S() throws IOException {
		postfixBuffer = new StringBuffer();
		stackMachine = new StackMachine();
		if (lookahead.tag() == Tag.ID) {
			Id id = (Id)lookahead;
			match(Tag.ID);
			match('=');
			E();
			id.setVal(stackMachine.getCurrentValue());
		} else if (lookahead.tag() == '(' || lookahead.tag() == Tag.INT
				|| lookahead.tag() == Tag.FLOAT) {
			E();
		} else {
			error("Syntax Error");
		}
		System.out.println(postfixBuffer.toString());
		System.out.println("Value: "+stackMachine.getCurrentValue());
	}

	private void L1() throws IOException {
		if (lookahead.tag() == Tag.ID || lookahead.tag() == '('
				|| lookahead.tag() == Tag.INT || lookahead.tag() == Tag.FLOAT) {
			L();
		} else {

		}

	}

	private void E() throws IOException {
		T();
		E1();

	}

	private void T() throws IOException {
		F();
		T1();

	}

	private void F() throws IOException {
		switch (lookahead.tag()) {
		case '(':
			match('(');
			E();
			match(')');
			break;
		case Tag.INT:
			postfixBuffer.append(lookahead);
			stackMachine.push(lookahead);
			match(Tag.INT);
			break;
		case Tag.FLOAT:
			postfixBuffer.append(lookahead);
			stackMachine.push(lookahead);
			match(Tag.FLOAT);
			break;
		case Tag.ID:
			postfixBuffer.append(lookahead);
			stackMachine.push(lookahead);
			match(Tag.ID);
			break;
		default:
			error("Syntax Error");
		}

	}

	private void T1() throws IOException {
		if (lookahead.tag() == '*') {
			match('*');
			F();
			postfixBuffer.append('*');
			stackMachine.evaluate('*');
			T1();
		} else {

		}

	}

	private void E1() throws IOException {
		if (lookahead.tag() == '+') {
			match('+');
			T();
			postfixBuffer.append('+');
			stackMachine.evaluate('+');
			T1();
		} else {

		}

	}

}
