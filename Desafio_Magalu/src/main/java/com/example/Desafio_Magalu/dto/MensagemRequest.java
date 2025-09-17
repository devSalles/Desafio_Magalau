package com.example.Desafio_Magalu.dto;

import com.example.Desafio_Magalu.Enum.TipoMensagem;
import com.example.Desafio_Magalu.model.Mensagem;
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
public class MensagemRequest {

    @NotBlank(message = "Formato de mensagem inválido") @NotNull(message = "Formato de mensagem inválido")
    private String destinatario;

    @Enumerated(EnumType.STRING) @NotBlank(message = "Tipo de mensagem obrigatório") @NotNull(message = "Tipo de mensagem obrigatório")
    private TipoMensagem tipoMensagem;

    @NotBlank(message = "Data e hora de envio obrigatório") @NotNull(message = "Data e hora de envio obrigatório")
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
