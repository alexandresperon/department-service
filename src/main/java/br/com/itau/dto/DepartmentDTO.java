package br.com.itau.dto;

import br.com.itau.enumerator.Boards;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "{validation.department.name.not.blank}")
    private String name;

    @NotNull(message = "{validation.department.board.not.null}")
    private Boards board;

    @Valid
    @NotNull(message = "{validation.department.address.not.null}")
    private AddressDTO address;

}
