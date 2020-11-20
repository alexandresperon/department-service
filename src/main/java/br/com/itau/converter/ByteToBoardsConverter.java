package br.com.itau.converter;

import br.com.itau.enumerator.Boards;
import org.springframework.core.convert.converter.Converter;

public class ByteToBoardsConverter implements Converter<String, Boards> {
    @Override
    public Boards convert(String source) {
        if (source.chars().allMatch(Character::isDigit))
            return Boards.of(Byte.parseByte(source));
        else
            return Boards.valueOf(source);
    }
}
