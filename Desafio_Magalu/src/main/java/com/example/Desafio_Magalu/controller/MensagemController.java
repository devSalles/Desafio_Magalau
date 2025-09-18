package com.example.Desafio_Magalu.controller;

import com.example.Desafio_Magalu.dto.MensagemRequestDTO;
import com.example.Desafio_Magalu.service.MensagemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mensagem")
@RequiredArgsConstructor
@Tag(name="mensagem")
public class MensagemController {

    private final MensagemService mensagemService;

    @PostMapping("/add")
    public ResponseEntity<Object> postMessage(@Valid @RequestBody MensagemRequestDTO mensagemRequestDTO)
    {
        return ResponseEntity.ok(this.mensagemService.postMessage(mensagemRequestDTO));
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> showById(@PathVariable Long id)
    {
        return ResponseEntity.ok(this.mensagemService.showById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id)
    {
        return ResponseEntity.ok(this.mensagemService.deleteById(id));
    }
}
