package com.example.Desafio_Magalu.service;

import com.example.Desafio_Magalu.core.exception.IdNaoEncontrado;
import com.example.Desafio_Magalu.dto.MensagemRequest;
import com.example.Desafio_Magalu.dto.MensagemResponse;
import com.example.Desafio_Magalu.model.Mensagem;
import com.example.Desafio_Magalu.repository.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;

    public Mensagem postMessage(MensagemRequest mensagemRequest)
    {
        Mensagem mensagem = mensagemRequest.toMensagem();
        return this.mensagemRepository.save(mensagem);
    }

    public MensagemResponse showById(Long id)
    {
        Mensagem mensagemID = this.mensagemRepository.findById(id).orElseThrow(IdNaoEncontrado::new);
        return MensagemResponse.fromMensagem(mensagemID);
    }

    public Boolean deleteById(Long id)
    {
        Mensagem mensagemID = this.mensagemRepository.findById(id).orElseThrow(IdNaoEncontrado::new);
        this.mensagemRepository.delete(mensagemID);
        return true;
    }
}
