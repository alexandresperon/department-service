package br.com.itau.enumerator;

public enum States {

    AC((byte) 12),
    AL((byte) 27),
    AP((byte) 16),
    AM((byte) 13),
    BA((byte) 29),
    CE((byte) 23),
    DF((byte) 53),
    ES((byte) 32),
    GO((byte) 52),
    MA((byte) 21),
    MG((byte) 31),
    MS((byte) 50),
    MT((byte) 51),
    PA((byte) 15),
    PB((byte) 25),
    PE((byte) 26),
    PI((byte) 22),
    PR((byte) 41),
    RJ((byte) 33),
    RN((byte) 24),
    RO((byte) 11),
    RR((byte) 14),
    RS((byte) 43),
    SC((byte) 42),
    SE((byte) 28),
    SP((byte) 35),
    TO((byte) 17);

    private final byte ibgeCode;

    States(final byte ibgeCode) {
        this.ibgeCode = ibgeCode;
    }

    public byte getIbgeCode() {
        return this.ibgeCode;
    }
}
