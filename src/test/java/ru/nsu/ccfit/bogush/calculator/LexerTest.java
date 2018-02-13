package ru.nsu.ccfit.bogush.calculator;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class LexerTest {
    private void testCase(String inputString, Lexeme... expectedLexemes)
            throws IOException {
        Lexer lexer = new Lexer(new StringReader(inputString));
        for (Lexeme lexeme : expectedLexemes) {
            assertEquals(lexeme, lexer.getLexeme());
        }
    }

    @Test
    public void emptyExpression()
            throws Exception {
        testCase("",
                Lexeme.EOF);
    }

    @Test
    public void whitespace()
            throws Exception {
        testCase("  1  ",
                new Lexeme(1),
                Lexeme.EOF
        );

        testCase(" ",
                Lexeme.EOF);

        testCase("      ",
                Lexeme.EOF);

        testCase(" \t\r\n ",
                Lexeme.EOF);
    }

    @Test
    public void longNumber()
            throws Exception {
        testCase("1234567890",
                new Lexeme(1234567890),
                Lexeme.EOF
        );
    }

    @Test
    public void allCharacters()
            throws Exception {
        testCase("1+2-3/4*5^6(7)",
                new Lexeme(1),
                Lexeme.PLUS,
                new Lexeme(2),
                Lexeme.MINUS,
                new Lexeme(3),
                Lexeme.DIV,
                new Lexeme(4),
                Lexeme.MULT,
                new Lexeme(5),
                Lexeme.POWER,
                new Lexeme(6),
                Lexeme.OPEN_BRACKET,
                new Lexeme(7),
                Lexeme.CLOSE_BRACKET,
                Lexeme.EOF
                );
    }


}