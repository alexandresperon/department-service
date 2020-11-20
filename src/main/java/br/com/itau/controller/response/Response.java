package br.com.itau.controller.response;

import br.com.itau.enumerator.Status;
import br.com.itau.util.I18nUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T data;

    private Status status;

    private List<String> messages;

    public Response() {
        this.status = Status.SUCCESS;
        this.messages = Collections.singletonList(I18nUtil.getMessage("default.success"));
    }

    public Response(T data) {
        this();
        this.data = data;
    }

    public Response(Status status, String... messages) {
        this.status = status;
        this.messages = Arrays.asList(messages);
    }

}
