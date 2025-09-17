package com.example.Desafio_Magalu.dto;

import com.example.Desafio_Magalu.Enum.TipoMensagem;
import com.example.Desafio_Magalu.model.Mensagem;

import java.time.LocalDateTime;

public record MensagemResponse(
        Long id,
        String destinatario,
        LocalDateTime dataHoraEnvio,
        TipoMensagem tipoMensagem
) {
    public static MensagemResponse fromMensagem(Mensagem mensagem)
    {
        return new MensagemResponse(mensagem.getId(), mensagem.getDestinatario(), mensagem.getDataHoraEnvio(),mensagem.getTipoMensagem());
    }
}
