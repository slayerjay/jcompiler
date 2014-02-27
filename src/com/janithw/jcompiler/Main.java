package com.janithw.jcompiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.janithw.jcompiler.inter.List;
import com.janithw.jcompiler.lexer.Lexer;
import com.janithw.jcompiler.parser.Parser;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream in = new FileInputStream(new File("tests\\test1.txt"));
		Lexer scanner = new Lexer(in);
		Parser parser = new Parser(scanner);
		List code = parser.P();
		System.out.println(parser.getEnv());
		System.out.println("Three Address Code: ");
		code.gen();
	}

}
