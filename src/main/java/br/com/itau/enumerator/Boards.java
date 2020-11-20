package br.com.itau.enumerator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Boards {

    E_I_S((byte) 1), RECOVERY((byte) 2), BUSINESS((byte) 3);

    private final byte value;

    Boards(byte value) {
        this.value = value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Boards of(byte value) {
        for (Boards board : values()) {
            if (board.getValue() == value)
                return board;
        }
        return null;
    }

    @JsonValue
    public Byte getValue() {
        return this.value;
    }
}
