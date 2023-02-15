package LexerTestSuiteLab;

import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

public class LexerTestSuite {

    @Test
    public void EmptyString() {
        Lexer lexer = new Lexer("");
        lexer.run();
        Vector<Token> expected_tokens = new Vector<>();
        Assert.assertEquals(lexer.getTokens(), expected_tokens);
    }

    @Test
    public void Inheritance() {
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
    public void customAssignment() {
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

    /**
     * tests all getter and setter methods of Token
     */
    @Test
    public void testTokenFunctions() {
        Token temp = new Token("blank", "token", 0);
        Assert.assertEquals("blank", temp.getWord());
        Assert.assertEquals("token", temp.getToken());
        Assert.assertEquals(0, temp.getLine());
        temp.setWord("word");
        temp.setToken("new");
        temp.setLine(37);
        Assert.assertEquals("word", temp.getWord());
        Assert.assertEquals("new", temp.getToken());
        Assert.assertEquals(37, temp.getLine());
    }

    /**
     * Testing binary variable assignment
     */
    @Test
    public void binaryAssignment() {
        Lexer lexer = new Lexer("byte binNumber = 0b00010001;");
        lexer.run();
        String[] listOfTokens = new String[]{"byte", "binNumber", "=", "0b00010001", ";"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }

        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    /**
     * Testing octal variable assignment
     */
    @Test
    public void octalAssignment() {
        Lexer lexer = new Lexer("int number = 024;");
        lexer.run();

        String[] listOfTokens = new String[]{"int", "number", "=", "024", ";"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }
        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    /**
     * Testing integer variable assignment
     */
    @Test
    public void integerAssignment() {
        Lexer lexer = new Lexer("int number = 0;");
        lexer.run();

        String[] listOfTokens = new String[]{"int", "number", "=", "0", ";"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }
        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    /**
     * Testing hexadecimal variable assignment
     */
    @Test
    public void hexAssignment() {
        Lexer lexer = new Lexer("int number = 0x24;");
        lexer.run();

        String[] listOfTokens = new String[]{"int", "number", "=", "0x24", ";"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }
        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    /**
     * Testing float variable assignment
     */
    @Test
    public void floatAssignment() {
        Lexer lexer = new Lexer("float number = 2.04;");
        lexer.run();

        String[] listOfTokens = new String[]{"float", "number", "=", "2.04", ";"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }
        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    /**
     * Testing valid char variable assignment with a normal char
     */
    @Test
    public void charAssignment() {
        Lexer lexer = new Lexer("char letter = 'a';");
        lexer.run();

        String[] listOfTokens = new String[]{"char", "letter", "=", "'a'", ";"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }
        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    /**
     * Testing valid char variable assignment with a special char
     */
    @Test
    public void specialCharAssignment() {
        Lexer lexer = new Lexer("char letter = '\\a';");
        lexer.run();

        String[] listOfTokens = new String[]{"char", "letter", "=", "'\\a'", ";"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }
        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    /**
     * Testing valid char variable assignment with a special char \'
     * targets condition on line 119 of Lexer.java
     */
    @Test
    public void quoteAsCharAssignment() {
        Lexer lexer = new Lexer("char letter = '\\'';");
        lexer.run();

        String[] listOfTokens = new String[]{"char", "letter", "=", "'\\''", ";"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }
        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    /**
     * Testing invalid char variable assignment with a normal char
     */
    @Test
    public void invalidCharAssignment() {
        Lexer lexer = new Lexer("char letter = 'aa';");
        lexer.run();

        String[] listOfTokens = new String[]{"char", "letter", "=", "'aa'", ";"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }
        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    /**
     * Testing invalid char variable assignment with empty char
     */
    @Test
    public void emptyCharAssignment() {
        Lexer lexer = new Lexer("char letter = '';");
        lexer.run();

        String[] listOfTokens = new String[]{"char", "letter", "=", "''", ";"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }
        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    /**
     * Testing invalid char variable assignment with only one '
     */
    @Test
    public void invalidMissingQuoteCharAssignment() {
        Lexer lexer = new Lexer("char letter = ';");
        lexer.run();

        String[] listOfTokens = new String[]{"char", "letter", "=", "';"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }
        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    /**
     * Testing empty string variable assignment
     */
    @Test
    public void emptyStringAssignment() {
        Lexer lexer = new Lexer("String str = \"\";");
        lexer.run();

        String[] listOfTokens = new String[]{"String", "str", "=", "\"\"", ";"};
        Vector<Token> expected_tokens = new Vector<>();
        for (String word : listOfTokens) {
            expected_tokens.add(new Token(word, ""));
        }
        Assert.assertEquals(expected_tokens.toString(), lexer.getTokens().toString());
    }

    @Test
    public void testNumbersBetween2And7() {
        Lexer lexer = new Lexer("class X extends Y{\n A a = new A(4);\n}");
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
        expected_tokens.add(new Token("4", ""));
        expected_tokens.add(new Token(")", ""));
        expected_tokens.add(new Token(";", ""));
        expected_tokens.add(new Token("}", ""));
        Assert.assertEquals(lexer.getTokens().toString(), expected_tokens.toString());
    }

    @Test
    public void testSpecialCharacter() {
        Lexer lexer = new Lexer("class X extends Y{\n A a = new A('@Archie');\n}");
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
        expected_tokens.add(new Token("'@Archie'", ""));
        expected_tokens.add(new Token(")", ""));
        expected_tokens.add(new Token(";", ""));
        expected_tokens.add(new Token("}", ""));
        Assert.assertEquals(lexer.getTokens().toString(), expected_tokens.toString());
    }

    @Test
    public void testQuotations() {
        Lexer lexer = new Lexer("class X extends Y{\n A a = new A('@Archie', \"Jones\");\n}");
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
        expected_tokens.add(new Token("'@Archie'", ""));
        expected_tokens.add(new Token(",", ""));
        expected_tokens.add(new Token("\"Jones\"", ""));
        expected_tokens.add(new Token(")", ""));
        expected_tokens.add(new Token(";", ""));
        expected_tokens.add(new Token("}", ""));
        Assert.assertEquals(lexer.getTokens().toString(), expected_tokens.toString());

    }

    @Test
    public void testNumbers8or9() {
        Lexer lexer = new Lexer("class X extends Y{\n int a = 9;\n}");
        lexer.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("class", ""));
        expected_tokens.add(new Token("X", ""));
        expected_tokens.add(new Token("extends", ""));
        expected_tokens.add(new Token("Y", ""));
        expected_tokens.add(new Token("{", ""));
        expected_tokens.add(new Token("int", ""));
        expected_tokens.add(new Token("a", ""));
        expected_tokens.add(new Token("=", ""));
        expected_tokens.add(new Token("9", ""));
        expected_tokens.add(new Token(";", ""));
        expected_tokens.add(new Token("}", ""));
        Assert.assertEquals(lexer.getTokens().toString(), expected_tokens.toString());
    }

    @Test
    public void testOperationsPlusOrMinus() {
        Lexer lexer = new Lexer("class X extends Y{\n int a = 8 + 0;\n}");
        lexer.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("class", ""));
        expected_tokens.add(new Token("X", ""));
        expected_tokens.add(new Token("extends", ""));
        expected_tokens.add(new Token("Y", ""));
        expected_tokens.add(new Token("{", ""));
        expected_tokens.add(new Token("int", ""));
        expected_tokens.add(new Token("a", ""));
        expected_tokens.add(new Token("=", ""));
        expected_tokens.add(new Token("8", ""));
        expected_tokens.add(new Token("+", ""));
        expected_tokens.add(new Token("0", ""));
        expected_tokens.add(new Token(";", ""));
        expected_tokens.add(new Token("}", ""));
        Assert.assertEquals(lexer.getTokens().toString(), expected_tokens.toString());
    }

    @Test
    public void testDollarUnderscore() {
        Lexer lexer = new Lexer("class X extends Y{\n String a_b = 'c_d';\n}");
        lexer.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("class", ""));
        expected_tokens.add(new Token("X", ""));
        expected_tokens.add(new Token("extends", ""));
        expected_tokens.add(new Token("Y", ""));
        expected_tokens.add(new Token("{", ""));
        expected_tokens.add(new Token("String", ""));
        expected_tokens.add(new Token("a_b", ""));
        expected_tokens.add(new Token("=", ""));
        expected_tokens.add(new Token("'c_d'", ""));
        expected_tokens.add(new Token(";", ""));
        expected_tokens.add(new Token("}", ""));
        Assert.assertEquals(lexer.getTokens().toString(), expected_tokens.toString());
    }

    @Test
    public void testOtherCharacter() {
        Lexer lexer = new Lexer("class X extends Y{\n String a_b = '`c_d~';\n}");
        lexer.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("class", ""));
        expected_tokens.add(new Token("X", ""));
        expected_tokens.add(new Token("extends", ""));
        expected_tokens.add(new Token("Y", ""));
        expected_tokens.add(new Token("{", ""));
        expected_tokens.add(new Token("String", ""));
        expected_tokens.add(new Token("a_b", ""));
        expected_tokens.add(new Token("=", ""));
        expected_tokens.add(new Token("'`c_d~", ""));
        expected_tokens.add(new Token("';", ""));
        expected_tokens.add(new Token("}", ""));
        Assert.assertEquals(lexer.getTokens().toString(), expected_tokens.toString());
    }
}
