package com.example.Desafio_Magalu.repository;

import com.example.Desafio_Magalu.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem,Long> {
}
