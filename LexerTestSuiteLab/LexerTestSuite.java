import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

public class LexerTestSuite {

    @Test
    public void EmptyString(){
        Lexer lexer = new Lexer("");
        lexer.run();
        Vector<Token> expected_tokens = new Vector<>();
        Assert.assertEquals(lexer.getTokens(), expected_tokens);
    }
    
    @Test
    public void Inheritance(){
        Lexer lexer = new Lexer("class X extends Y{\n\n}");
        lexer.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("class", ""));
        expected_tokens.add(new Token("X", ""));
        expected_tokens.add(new Token("extends", ""));
        expected_tokens.add(new Token("Y", ""));
        expected_tokens.add(new Token("{", ""));
        expected_tokens.add(new Token("}", ""));
        Assert.assertEquals(lexer.getTokens().toString(), expected_tokens.toString());
    }
    
    @Test
    public void Assignment(){
        Lexer lexer = new Lexer("class X extends Y{\n A a = new A();\n}");
        lexer.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("class", ""));
        expected_tokens.add(new Token("X", ""));
        expected_tokens.add(new Token("extends", ""));
        expected_tokens.add(new Token("Y", ""));
        expected_tokens.add(new Token("{", ""));
        expected_tokens.add(new Token("A", ""));
        expected_tokens.add(new Token("a", ""));
        expected_tokens.add(new Token("=", ""));
        expected_tokens.add(new Token("new", ""));
        expected_tokens.add(new Token("A", ""));
        expected_tokens.add(new Token("(", ""));
        expected_tokens.add(new Token(")", ""));
        expected_tokens.add(new Token(";", ""));
        expected_tokens.add(new Token("}", ""));
        Assert.assertEquals(lexer.getTokens().toString(), expected_tokens.toString());
    }
}
