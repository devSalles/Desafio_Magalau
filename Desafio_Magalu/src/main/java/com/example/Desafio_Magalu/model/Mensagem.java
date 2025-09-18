package com.example.Desafio_Magalu.model;

import com.example.Desafio_Magalu.Enum.TipoMensagem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb__Mensagem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mensagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) @NotBlank(message = "Formato de mensagem inválido") @NotNull(message = "Formato de mensagem inválido")
    private String destinatario;

    @Enumerated(EnumType.STRING) @NotNull(message = "Tipo de mensagem obrigatório")
    private TipoMensagem tipoMensagem;

    @Column(nullable = false) @NotNull(message = "Data e hora de envio obrigatório")
    private LocalDateTime dataHoraEnvio;
}
