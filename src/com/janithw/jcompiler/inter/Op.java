package com.janithw.jcompiler.inter;

import com.janithw.jcompiler.lexer.Token;
import com.janithw.jcompiler.lexer.Type;


public class Op extends Expr {

   public Op(Token tok, Type p)  { super(tok, p); }

   public Expr reduce() {
      Expr x = gen();
      Temp t = new Temp(type);
      emit( t.toString() + " = " + x.toString() );
      return t;
   }
}
