package com.example.Desafio_Magalu.dto;

import com.example.Desafio_Magalu.Enum.TipoMensagem;
import com.example.Desafio_Magalu.model.Mensagem;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemRequestDTO {

    @NotBlank(message = "Formato de mensagem inválido") @NotNull(message = "Formato de mensagem inválido")
    private String destinatario;

    @Enumerated(EnumType.STRING) @NotNull(message = "Tipo de mensagem obrigatório")
    private TipoMensagem tipoMensagem;

    @NotNull(message = "Data e hora de envio obrigatório") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private LocalDateTime dataHoraEnvio;


    public Mensagem toMensagem()
    {
        Mensagem mensagem = new Mensagem();

        mensagem.setDestinatario(this.destinatario);
        mensagem.setTipoMensagem(this.tipoMensagem);
        mensagem.setDataHoraEnvio(this.dataHoraEnvio);

        return mensagem;
    }
}
