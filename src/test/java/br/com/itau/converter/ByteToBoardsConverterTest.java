package br.com.itau.converter;

import br.com.itau.enumerator.Boards;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteToBoardsConverterTest {

    private final ByteToBoardsConverter converter = new ByteToBoardsConverter();

    @Test
    void convert_boardAsInteger_success() {
        assertEquals(Boards.E_I_S, this.converter.convert("1"));
        assertEquals(Boards.RECOVERY, this.converter.convert("2"));
        assertEquals(Boards.BUSINESS, this.converter.convert("3"));
    }

    @Test
    void convert_boardAsString_success() {
        assertEquals(Boards.E_I_S, this.converter.convert("E_I_S"));
        assertEquals(Boards.RECOVERY, this.converter.convert("RECOVERY"));
        assertEquals(Boards.BUSINESS, this.converter.convert("BUSINESS"));
    }

}
