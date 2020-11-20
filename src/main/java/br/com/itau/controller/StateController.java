package br.com.itau.controller;

import br.com.itau.controller.response.Response;
import br.com.itau.enumerator.States;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/states")
public class StateController {

    @GetMapping
    public Response<States[]> get() {
        return new Response<>(States.values());
    }

}
