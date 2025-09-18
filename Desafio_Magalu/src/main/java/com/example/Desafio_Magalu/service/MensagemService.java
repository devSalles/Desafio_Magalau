package com.example.Desafio_Magalu.service;

import com.example.Desafio_Magalu.core.exception.IdNaoEncontrado;
import com.example.Desafio_Magalu.dto.MensagemRequestDTO;
import com.example.Desafio_Magalu.dto.MensagemResponseDTO;
import com.example.Desafio_Magalu.model.Mensagem;
import com.example.Desafio_Magalu.repository.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;

    public Mensagem postMessage(MensagemRequestDTO mensagemRequestDTO)
    {
        Mensagem mensagem = mensagemRequestDTO.toMensagem();
        return this.mensagemRepository.save(mensagem);
    }

    public MensagemResponseDTO showById(Long id)
    {
        Mensagem mensagemID = this.mensagemRepository.findById(id).orElseThrow(IdNaoEncontrado::new);
        return MensagemResponseDTO.fromMensagem(mensagemID);
    }

    public Boolean deleteById(Long id)
    {
        Mensagem mensagemID = this.mensagemRepository.findById(id).orElseThrow(IdNaoEncontrado::new);
        this.mensagemRepository.delete(mensagemID);
        return true;
    }
}
