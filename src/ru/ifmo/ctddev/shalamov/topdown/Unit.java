package ru.ifmo.ctddev.shalamov.topdown;

/**
 * Created by viacheslav on 15.04.2015.
 */
public class Unit implements Token {

    public String token;

    Unit(String token) {
        this.token = token;
    }

    @Override
    public boolean isSame(Token other) {
        if (other instanceof Unit)
            return this.token.equals(((Unit) other).token);
        else
            return false;
    }

    @Override
    public String toString() {
        return token == null ? "" : token;
    }

    @Override
    public boolean isLPAREN() {
        return token.equals(Character.toString(Token.LPAREN));
    }

    @Override
    public boolean isRPAREN() {
        return token.equals(Character.toString(Token.RPAREN));
    }

    @Override
    public boolean isPLUS() {
        return token.equals(Character.toString(Token.PLUS));
    }

    @Override
    public boolean isMINUS() {
        return token.equals(Character.toString(Token.MINUS));
    }

    @Override
    public boolean isMUL() {
        return token.equals(Character.toString(Token.MUL));
    }

    @Override
    public boolean isEND() {
        return token.equals(Character.toString(Token.END));
    }

    @Override
    public boolean isEPS() {
        return token.equals(Character.toString(Token.EPS));
    }

    @Override
    public boolean isNUMBER() {
        return false;
    }

    public static Token fromChar(char curChar) {
        if (LPAREN == curChar || RPAREN == curChar || PLUS == curChar || MINUS == curChar || MUL == curChar) {
            return new Unit(Character.toString(curChar));
        }
        return null;
    }
}
