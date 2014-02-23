package com.janithw.jcompiler;

import java.io.IOException;

import com.janithw.jcompiler.lexer.Lexer;
import com.janithw.jcompiler.lexer.Token;
import com.janithw.jcompiler.parser.Parser;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Lexer scanner = new Lexer(System.in);
		Parser parser = new Parser(scanner);
		parser.P();
		System.out.println(parser.getEnv());
//		
//		while(true){
//			Token nextToken = scanner.scan();
//			System.out.println(nextToken);
//		}
		
	}

}
