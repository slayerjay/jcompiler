package com.janithw.jcompiler.lexer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;


public class Lexer {

	private InputStream reader;
	private int line = 1;
	private int cPos = 0;
	private char peek = ' ';

	private Hashtable<String, Id> words = new Hashtable<String, Id>();
	
	public Lexer(InputStream in) {
		this.reader = in;
		reserve(Type.IntType);
		reserve(Type.FloatType);
	}
	
	public void reserve(Id id){
		words.put(id.getLexeme(), id);
	}

	public Token scan() throws IOException {
		//Skip white space
		do {
			readNext();
			if (peek == ' ' || peek == '\t') {
				continue;
			} else if (peek == '\n') {
				line++;
				cPos = 0;
				continue;
			} /*else if (peek == '\r') {
				continue;
			} */else {
				break;
			}
		} while (peek!=-1);

		//Handle numbers
		if (Character.isDigit(peek)) {
			return makeNumber();
		}
		
		//Handle reserved words and identifiers
		if (Character.isLetter(peek)) {
			String str = getString();
			Id id = words.get(str);
			if (id != null) {
				return id;
			}
			if (str.length() == 1 && Character.isLowerCase(str.charAt(0))) {	//Validation for an identifier
				id = new Id(Tag.ID, str);
				reserve(id);
				return id;
			} else {
				// Error
				System.out.println("Error");
			}
		}
		/* if we get here, treat read-ahead character peek as a token */
		Token t = new Token(peek) ;
        peek = ' ';
        return t;

	}

	private String getString() throws IOException {
		StringBuffer sb = new StringBuffer();
		do {
			sb.append(peek);
			readNext();
		} while (Character.isLetterOrDigit(peek));
		return sb.toString();
	}

	private Token makeNumber() throws IOException {
		int v = 0;
		do {
			v = 10 * v + Character.digit(peek, 10);
			readNext();
		} while (Character.isDigit(peek));

		if (peek != '.') {
			return new Int(v);
		}

		float x = v;

		float d = 10;
		for (;;) {
			readNext();
			if (!Character.isDigit(peek)) {
				break;
			}
			x = x + Character.digit(peek, 10) / d;
			d = d * 10;
		}
		return new Float(x);
	}

	private void readNext() throws IOException {
		peek = (char) reader.read();
		cPos++;
	}

	public int getCurrentLine() {
		return line;
	}

	public int getCurrentPosition() {
		return cPos;
	}

}
