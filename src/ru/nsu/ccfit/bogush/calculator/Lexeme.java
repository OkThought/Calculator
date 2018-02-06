package ru.nsu.ccfit.bogush.calculator;

public class Lexeme {
    public static final Lexeme PLUS           = new Lexeme(LexemeType.PLUS,           Lexer.PLUS);
    public static final Lexeme MINUS          = new Lexeme(LexemeType.MINUS,          Lexer.MINUS);
    public static final Lexeme OPEN_BRACKET   = new Lexeme(LexemeType.OPEN_BRACKET,   Lexer.OPEN_BRACKET);
    public static final Lexeme CLOSE_BRACKET  = new Lexeme(LexemeType.CLOSE_BRACKET,  Lexer.CLOSE_BRACKET);
    public static final Lexeme MULT           = new Lexeme(LexemeType.MULT,           Lexer.MULT);
    public static final Lexeme DIV            = new Lexeme(LexemeType.DIV,            Lexer.DIV);
    public static final Lexeme POWER          = new Lexeme(LexemeType.POWER,          Lexer.POWER);
    public static final Lexeme EOF            = new Lexeme(LexemeType.EOF,            "EOF");

    private LexemeType type;
    private String value;

    public Lexeme(long number) {
        this(LexemeType.NUMBER, String.valueOf(number));
    }

    public Lexeme(LexemeType type, char value) {
        this(type, String.valueOf(value));
    }

    public Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    public LexemeType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lexeme lexeme = (Lexeme) o;

        if (type != lexeme.type) return false;
        return value != null ? value.equals(lexeme.value) : lexeme.value == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
