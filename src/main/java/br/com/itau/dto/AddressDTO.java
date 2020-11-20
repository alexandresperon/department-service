package br.com.itau.dto;

import br.com.itau.enumerator.States;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax. validation.constraints.NotNull;

@Data
public class AddressDTO {

    private Long id;

    @NotBlank(message = "{validation.address.address.not.blank}")
    private String address;

    @NotBlank(message = "{validation.address.city.not.blank}")
    private String city;

    @NotNull(message = "{validation.address.state.not.null}")
    private States state;

}
