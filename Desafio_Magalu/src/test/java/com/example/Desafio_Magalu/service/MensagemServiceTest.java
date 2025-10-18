package com.example.Desafio_Magalu.service;

import com.example.Desafio_Magalu.Enum.TipoMensagem;
import com.example.Desafio_Magalu.core.exception.IdNaoEncontrado;
import com.example.Desafio_Magalu.dto.MensagemRequestDTO;
import com.example.Desafio_Magalu.dto.MensagemResponseDTO;
import com.example.Desafio_Magalu.model.Mensagem;
import com.example.Desafio_Magalu.repository.MensagemRepository;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MensagemServiceTest {

    @Mock
    private MensagemRepository mensagemRepository;

    @InjectMocks
    private MensagemService mensagemService;

    private Mensagem validMensagem;
    private MensagemRequestDTO validMensagemDTO;

    //Validator usado para validar entrada de dados
    private static Validator validator;

    //Configuração do validator
    @BeforeAll
    static void setup_Validator()
    {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator=validatorFactory.getValidator();
    }

    //Configuração das entidades
    @BeforeEach
    void setup()
    {
        validMensagemDTO = new MensagemRequestDTO();
        validMensagemDTO.setDestinatario("Teste Mockito");
        validMensagemDTO.setDataHoraEnvio(LocalDateTime.now());
        validMensagemDTO.setTipoMensagem(TipoMensagem.email);

        validMensagem = new Mensagem();
        validMensagem.setId(1L);
        validMensagem.setDestinatario(validMensagemDTO.getDestinatario());
        validMensagem.setDataHoraEnvio(validMensagemDTO.getDataHoraEnvio());
        validMensagem.setTipoMensagem(validMensagemDTO.getTipoMensagem());
    }


    //--------- TESTE METODO postMessage ---------
    @Test
    void postMessage_DeveSalvarERetornarSucesso() {
        when(mensagemRepository.save(any(Mensagem.class))).thenReturn(validMensagem);

        Mensagem result = this.mensagemService.postMessage(validMensagemDTO);
        assertNotNull(result);

        assertEquals(validMensagemDTO.getDestinatario(),result.getDestinatario());
        assertEquals(validMensagemDTO.getTipoMensagem(),result.getTipoMensagem());
        assertEquals(validMensagemDTO.getDataHoraEnvio(),result.getDataHoraEnvio());

        //Verificação de chamada de repositório
        verify(mensagemRepository).save(any(Mensagem.class));
    }

    @Test
    void postMessage_DeveRetornarUmaExcecaoQuandoRequisicaoForInvalida()
    {
        MensagemRequestDTO mensagemRequestDTO = new MensagemRequestDTO();
        mensagemRequestDTO.setDestinatario(" ");
        mensagemRequestDTO.setDataHoraEnvio(null);
        mensagemRequestDTO.setTipoMensagem(null);

        //Trabalham juntos para validação do objeto
        Set<ConstraintViolation<MensagemRequestDTO>> violations = validator.validate(mensagemRequestDTO);
        assertFalse(violations.isEmpty(),"As violações devem ser válidas");

        //forEach usado para exibir mensagens de erros de validações
        violations.forEach(request-> System.out.println(request.getPropertyPath()+ " - " + request.getMessage()));
    }

    //--------- TESTE METODO showById ---------
    @Test
    void showById_DeveRetornarEntidadeQuandoIdExiste()
    {
        when(mensagemRepository.findById(anyLong())).thenReturn(Optional.of(validMensagem));

        MensagemResponseDTO result = this.mensagemService.showById(1L);

        assertNotNull(result);
        assertEquals(validMensagem.getId(),result.id());

        //Verificação de chamada de repositório
        verify(mensagemRepository,times(1)).findById(1L);
    }

    @Test
    void showById_LancaExcecaoQuandoIdNaoExistir()
    {
        when(mensagemRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IdNaoEncontrado.class,()->this.mensagemService.showById(99L));

        //Verificação de chamada de repositório
        verify(mensagemRepository,times(1)).findById(99L);
    }

    //--------- TESTE METODO deleteById ---------
    @Test
    void deleteById_DeveDeletarEntidadeQuandoIdExistir() {

        when(mensagemRepository.findById(anyLong())).thenReturn(Optional.of(validMensagem));
        doNothing().when(mensagemRepository).delete(validMensagem);

        Boolean result = this.mensagemService.deleteById(1L);
        assertTrue(result);

        //Verificação de chamada de repositório
        verify(mensagemRepository,times(1)).findById(1L);
        verify(mensagemRepository,times(1)).delete(validMensagem);
    }

    @Test
    void deleteById_LancarExcecaoQuandoIdNaoExistir()
    {
        when(mensagemRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IdNaoEncontrado.class,()->this.mensagemService.deleteById(1L));

        //Verificação de chamada de repositório
        verify(mensagemRepository,times(1)).findById(1L);
        verify(mensagemRepository,never()).deleteById(1L);
    }
}