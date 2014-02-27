package com.janithw.jcompiler.inter;

import com.janithw.jcompiler.lexer.Lexer;
import com.janithw.jcompiler.lexer.Type;

public class Node {

	   int lexline = 0;

	   Node() { lexline = Lexer.getCurrentLine(); }

	   void error(String s) { throw new Error("near line "+lexline+": "+s); }

	   static int labels = 0;

	   public int newlabel() { return ++labels; }

	   public void emitlabel(int i) { System.out.print("L" + i + ":"); }

	   public void emit(String s) { System.out.println("\t" + s); }
	   
		public Type max(Type p1, Type p2) {
			return Type.max(p1, p2);
		}

		public Expr widen(Expr a, Type t, Type w) {
			if (t == w) {
				return a;
			} else if (t == Type.IntType && w == Type.FloatType) {
				Temp temp = new Temp(Type.FloatType);
				emit(temp + " = (float)" + a);
				return temp;
			} else {
				error("Type error");
			}
			return null;
		}
	}
