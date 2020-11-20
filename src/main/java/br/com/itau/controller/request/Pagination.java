package br.com.itau.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
@EqualsAndHashCode
public class Pagination {

    private Integer limit;
    private Integer offset;
    private Sort.Direction direction;
    private String[] properties;

    public Pagination() {
        this.limit = 10;
        this.offset = 0;
        this.direction = Sort.Direction.ASC;
        this.properties = new String[]{"id"};
    }

    public PageRequest request() {
        return PageRequest.of(offset, limit, direction, properties);
    }

}
