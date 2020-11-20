package br.com.itau.controller;

import br.com.itau.controller.response.Response;
import br.com.itau.dto.KeyValueDTO;
import br.com.itau.enumerator.Boards;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @GetMapping
    public Response<List<KeyValueDTO<Boards, String>>> get() {
        return new Response<>(Arrays.stream(Boards.values()).map(KeyValueDTO::create).collect(Collectors.toList()));
    }

}
