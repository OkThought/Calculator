package ru.nsu.ccfit.bogush.calculator;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    public static final char PLUS           = '+';
    public static final char MINUS          = '-';
    public static final char OPEN_BRACKET   = '(';
    public static final char CLOSE_BRACKET  = ')';
    public static final char MULT           = '*';
    public static final char DIV            = '/';
    public static final char POWER          = '^';
    public static final int  EOF            = -1;

    private Reader reader;
    private int current;
    private StringBuilder numberBuffer = new StringBuilder();

    public Lexer(Reader reader)
            throws IOException {
        this.reader = reader;
        current = reader.read();
    }

    public Lexeme getLexeme()
            throws IOException {
        while (Character.isWhitespace(current)) {
            current = reader.read();
        }

        if (Character.isDigit((char) current)) {
            do {
                numberBuffer.append((char) current);
                current = reader.read();
            } while (Character.isDigit((char) current));
        }


        if (numberBuffer.length() != 0) {
            Lexeme lexeme = new Lexeme(LexemeType.NUMBER, numberBuffer.toString());
            numberBuffer.setLength(0);
            return lexeme;
        }

        int cur = current;
        current = reader.read();

        switch (cur) {
            case EOF:   return Lexeme.EOF;
            case MINUS: return Lexeme.MINUS;
            case PLUS:  return Lexeme.PLUS;
            case MULT:  return Lexeme.MULT;
            case DIV:   return Lexeme.DIV;
            case POWER: return Lexeme.POWER;
            case OPEN_BRACKET: return Lexeme.OPEN_BRACKET;
            case CLOSE_BRACKET: return Lexeme.CLOSE_BRACKET;
            default:
                throw new IllegalStateException("Unknown char: '" + (char) current + "'");
        }
    }
}
