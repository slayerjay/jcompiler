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
			expr.gen();
		}
	}

	int after = 0; // saves label after

}
