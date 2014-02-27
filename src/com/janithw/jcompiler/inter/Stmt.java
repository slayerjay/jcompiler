package com.janithw.jcompiler.inter;

public class Stmt extends Node {

	private Expr expr;

	public Stmt(Expr expr) {
		this.expr = expr;
	}

	public Stmt() {
	}

	public static Stmt Null = new Stmt(); // Empty list of statements

	public void gen() {
		if(expr!=null){
			Expr gen = expr.gen();
			if(gen instanceof Arith){
				((Arith)gen).reduce();
			}
		}
	}

	int after = 0; // saves label after

}
