package ru.ifmo.ctddev.shalamov.topdown;

/**
 * Created by viacheslav on 15.04.2015.
 */
public class Number implements Token {
    private int number;

    @Override
    public boolean isSame(Token other) {
        return other instanceof Number;
    }

    public Number(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean isLPAREN() {
        return false;
    }

    @Override
    public boolean isRPAREN() {
        return false;
    }

    @Override
    public boolean isPLUS() {
        return false;
    }

    @Override
    public boolean isMINUS() {
        return false;
    }

    @Override
    public boolean isMUL() {
        return false;
    }

    @Override
    public boolean isEND() {
        return false;
    }

    @Override
    public boolean isEPS() {
        return false;
    }

    @Override
    public boolean isNUMBER() {
        return true;
    }

}
