package com.janithw.jcompiler.inter;

import com.janithw.jcompiler.lexer.Lexer;

public class Node {

	   int lexline = 0;

	   Node() { lexline = Lexer.getCurrentLine(); }

	   void error(String s) { throw new Error("near line "+lexline+": "+s); }

	   static int labels = 0;

	   public int newlabel() { return ++labels; }

	   public void emitlabel(int i) { System.out.print("L" + i + ":"); }

	   public void emit(String s) { System.out.println("\t" + s); }
	}
