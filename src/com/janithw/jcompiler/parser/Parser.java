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

import com.janithw.jcompiler.error.ErrorHandler;
import com.janithw.jcompiler.inter.Arith;
import com.janithw.jcompiler.inter.Constant;
import com.janithw.jcompiler.inter.Expr;
import com.janithw.jcompiler.inter.List;
import com.janithw.jcompiler.inter.Set;
import com.janithw.jcompiler.inter.Stmt;
import com.janithw.jcompiler.lexer.Id;
import com.janithw.jcompiler.lexer.Lexer;
import com.janithw.jcompiler.lexer.Tag;
import com.janithw.jcompiler.lexer.Token;
import com.janithw.jcompiler.lexer.Type;
import com.janithw.jcompiler.stack.StackMachine;
import com.janithw.jcompiler.symbols.Env;

public class Parser {

	private Lexer scanner;

	private Token lookahead;

	private Token current;
	
	private Token next;
	
	private StringBuffer postfixBuffer;

	private StackMachine stackMachine;

	private Env env;
	
	private boolean skipNext = false;

	public Parser(Lexer scanner) throws IOException {
		this.scanner = scanner;
		postfixBuffer = new StringBuffer();
		stackMachine = new StackMachine();
		env = new Env(null);
		scanNext();
	}

	private void scanNext() throws IOException {
		lookahead = scanner.scan();
	}

	private void match(int t) throws IOException {
		if (lookahead.tag() == t && !skipNext) {
			scanNext();
		} else if(skipNext){
			lookahead = next;
			skipNext = false;
		}else {
			ErrorHandler.parsingError(ErrorHandler.Syntax);
		}
	}

	public List P() throws IOException {
		D();
		List l = L();
		return l;
	}

	private void D() throws IOException {
		Type type = B();
		N(type);
		match(';');
		D1();
	}

	private Type B() throws IOException {
		Type type = null;
		if (lookahead.tag() == Tag.INT_KEYWORD) {
			type = (Type) lookahead;
			match(Tag.INT_KEYWORD);
		} else if (lookahead.tag() == Tag.FLOAT_KEYWORD) {
			type = (Type) lookahead;
			match(Tag.FLOAT_KEYWORD);
		} else {
			ErrorHandler.parsingError(ErrorHandler.Syntax);
		}
		return type;

	}

	private void N(Type type) throws IOException {
		((Id) lookahead).setType(type);
		env.put(lookahead, new com.janithw.jcompiler.inter.Id((Id) lookahead));
		match(Tag.ID);
		N1(type);
	}

	private void N1(Type type) throws IOException {
		if (lookahead.tag() == ',') {
			match(',');
			((Id) lookahead).setType(type);
			env.put(lookahead, new com.janithw.jcompiler.inter.Id(
					(Id) lookahead));
			match(Tag.ID);
			N1(type);
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

	private List L() throws IOException {
		Stmt s = S();
		match(';');
		Stmt l1 = L1();
		return new List(s, l1);
	}

	private Stmt S() throws IOException {
		postfixBuffer = new StringBuffer();
		stackMachine = new StackMachine();
		Stmt stmtNode = Stmt.Null;
		if (lookahead.tag() == '(' || lookahead.tag() == Tag.INT
				|| lookahead.tag() == Tag.FLOAT) {
			stmtNode = new Stmt(E());
		} else if (lookahead.tag() == Tag.ID) {
			current = lookahead;
			Id id = (Id) lookahead;
			match(Tag.ID);
			if(lookahead.tag() == '='){
				match('=');
				Expr expr = E();
				if (!id.getType().equals(stackMachine.getCurrentType())) {
					if ("int".equals(id.getType().toString())) { // id is int,
																	// trying to
																	// assign
						// float
						ErrorHandler.parsingError(ErrorHandler.Narrowing);
					} else { // id is float, trying to assign int.
						ErrorHandler.parsingWarning(ErrorHandler.Widening);
					}
				}
				id.setVal(stackMachine.getCurrentValue(),
						stackMachine.getCurrentType());
				stmtNode = new Set(env.get(id), expr);
			} else {
				next = lookahead;
				lookahead = current;
				skipNext = true;
				stmtNode = new Stmt(E());
			}

		} else {
			ErrorHandler.parsingError(ErrorHandler.Syntax);
		}
		System.out.println("Line: " + Lexer.getCurrentLine());
		System.out
				.println("----------------------------------------------------");
		System.out.println("Postfix notation: " + postfixBuffer.toString());
		System.out.println("Evaluated Result: "
				+ stackMachine.getCurrentValue());
		return stmtNode;
	}

	private Stmt L1() throws IOException {
		Stmt stmts = null;
		if (lookahead.tag() == Tag.ID || lookahead.tag() == '('
				|| lookahead.tag() == Tag.INT || lookahead.tag() == Tag.FLOAT) {
			stmts = L();
		} else {
			stmts = Stmt.Null;
		}
		return stmts;
	}

	private Expr E() throws IOException {
		Expr t = T();
		return E1(t);
	}

	private Expr T() throws IOException {
		Expr f = F();
		return T1(f);
	}

	private Expr F() throws IOException {
		Expr expr = null;
		switch (lookahead.tag()) {
		case '(':
			match('(');
			expr = E();
			match(')');
			break;
		case Tag.INT:
			postfixBuffer.append(lookahead);
			stackMachine.push(lookahead);
			expr = new Constant(lookahead, Type.IntType);
			match(Tag.INT);
			break;
		case Tag.FLOAT:
			postfixBuffer.append(lookahead);
			stackMachine.push(lookahead);
			expr = new Constant(lookahead, Type.FloatType);
			match(Tag.FLOAT);
			break;
		case Tag.ID:
			postfixBuffer.append(lookahead);
			stackMachine.push(lookahead);
			expr = env.get(lookahead);
			match(Tag.ID);
			break;
		default:
			ErrorHandler.parsingError(ErrorHandler.Syntax);
		}
		return expr;
	}

	private Expr T1(Expr inhF) throws IOException {
		Expr term = null;
		if (lookahead.tag() == '*') {
			Token op = lookahead;
			match('*');
			Expr f = F();
			postfixBuffer.append('*');
			stackMachine.evaluate('*');
			Expr term1 = new Arith(op, inhF, f);
			term = T1(term1);
		} else {
			term = inhF;
		}
		return term;
	}

	private Expr E1(Expr inhT) throws IOException {
		Expr expr = null;
		if (lookahead.tag() == '+') {
			Token op = lookahead;
			match('+');
			Expr t = T();
			postfixBuffer.append('+');
			stackMachine.evaluate('+');
			Expr exp1 = new Arith(op, inhT, t);
			expr = E1(exp1);
		} else {
			expr = inhT;
		}
		return expr;
	}

	public Env getEnv() {
		return env;
	}

}
