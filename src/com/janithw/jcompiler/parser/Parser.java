package com.janithw.jcompiler.parser;

import java.io.IOException;

import com.janithw.jcompiler.lexer.Lexer;
import com.janithw.jcompiler.lexer.Tag;
import com.janithw.jcompiler.lexer.Token;

public class Parser {

	private Lexer scanner;

	private Token lookahead;

	public Parser(Lexer scanner) throws IOException {
		this.scanner = scanner;
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
		if (lookahead.tag() == Tag.ID) {
			match(Tag.ID);
			match('=');
			E();
		} else if (lookahead.tag() == '(' || lookahead.tag() == Tag.INT
				|| lookahead.tag() == Tag.FLOAT) {
			E();
		} else {
			error("Syntax Error");
		}
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
			match(Tag.INT);
			break;
		case Tag.FLOAT:
			match(Tag.FLOAT);
			break;
		case Tag.ID:
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
			T1();
		} else {

		}

	}

	private void E1() throws IOException {
		if (lookahead.tag() == '+') {
			match('+');
			T();
			T1();
		} else {

		}

	}

}
