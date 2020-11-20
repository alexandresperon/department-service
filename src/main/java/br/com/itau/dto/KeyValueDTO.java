package br.com.itau.dto;

import br.com.itau.util.I18nUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValueDTO<K, V> {

    private K key;
    private V value;

    public static <E extends Enum<E>> KeyValueDTO<E, String> create(E k) {
        return new KeyValueDTO<>(k, I18nUtil.getMessage(k));
    }

}
