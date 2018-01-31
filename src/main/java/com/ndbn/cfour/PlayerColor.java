package com.ndbn.cfour;

public enum PlayerColor {
    RED('R'),
    GREEN('G');

    private final char color;

    PlayerColor(final char string) {
        this.color = string;
    }

    public char getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color + "";
    }
}