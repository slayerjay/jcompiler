package com.janithw.jcompiler.symbols;

import java.util.Hashtable;

import com.janithw.jcompiler.inter.Id;
import com.janithw.jcompiler.lexer.Token;



public class Env {
	private Hashtable<Token, Id> table;
	protected Env prevEnv;

	public Env(Env prevEnv) {
		super();
		this.table = new Hashtable<Token, Id>();
		this.prevEnv = prevEnv;
	}

	public void put(Token w, Id i) {
		table.put(w, i);
	}

	public Id get(Token w) {
		for (Env e = this; e != null; e = e.prevEnv) {
			Id found = (Id) (e.table.get(w));
			if (found != null)
				return found;
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Token token : table.keySet()) {
			Id id = table.get(token);
			sb.append(id.getToken()).append("\t").append(id.getType()).append("\t").append(id.getVal()).append("\n");
		}
		return sb.toString();
	}
}
