package br.com.itau.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class I18nUtil {

    public static String getMessage(final Enum<?> en) {
        final String code = en.getDeclaringClass().getSimpleName() + '.' + en.name();
        return getMessage(code.toLowerCase());
    }

    public static String getMessage(final String code) {
        return SpringContext.getBean(MessageSource.class).getMessage(code, new Object[]{}, LocaleContextHolder.getLocale());
    }

}
