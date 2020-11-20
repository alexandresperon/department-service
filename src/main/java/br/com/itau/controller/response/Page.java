package br.com.itau.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private List<T> content;
    private Long total;
    private Integer pages;

    public Page(org.springframework.data.domain.Page<T> page) {
        this.total = page.getTotalElements();
        this.content = page.getContent();
        this.pages = page.getTotalPages();
    }

}
