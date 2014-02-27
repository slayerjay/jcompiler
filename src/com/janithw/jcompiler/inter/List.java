package com.janithw.jcompiler.inter;

public class List extends Stmt {

	Stmt stmt1;
	Stmt stmt2;

	public List(Stmt s1, Stmt s2) {
		stmt1 = s1;
		stmt2 = s2;
	}

	public void gen() {
		if (stmt1 != Stmt.Null)
			stmt1.gen();
		if (stmt2 != Stmt.Null)
			stmt2.gen();
	}

}
