package com.janithw.jcompiler.error;

import com.janithw.jcompiler.lexer.Lexer;

public class ErrorHandler {
	
	public static final String Narrowing = "Type mismatch (Narrowing conversion) float to int";
	public static final String Widening = "Type mismatch (Widening conversion) int to float";
	public static final String Syntax = "Syntax error";
	
	public static void parsingError(String message){
		throw new CompilerException("Parse Error on Line: " + Lexer.getCurrentLine()
				+ " Col: " + Lexer.getCurrentPosition() + ": " + message);
	}
	
	public static void parsingWarning(String message){
		System.out.println("Warning on Line: " + Lexer.getCurrentLine()
				+ " Col: " + Lexer.getCurrentPosition() + ": " + message);
	}
}


