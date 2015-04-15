package ru.ifmo.ctddev.shalamov.topdown;

/**
 * Describes interface of token
 * Created by viacheslav on 15.04.2015.
 */
public interface Token {
    public final char LPAREN = '(';
    public final char RPAREN = ')';
    public final char PLUS = '+';
    public final char MINUS = '-';
    public final char MUL = '*';
    public final char END = '$';
    public final char EPS = 'e';

    public boolean isSame(Token other);

    @Override
    public String toString();


    public boolean isLPAREN();
    public boolean isRPAREN();
    public boolean isPLUS();
    public boolean isMINUS();
    public boolean isMUL();
    public boolean isEND();
    public boolean isEPS();


    public boolean isNUMBER();
}
