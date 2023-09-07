package com.example.lembrete.lembrete;

import com.example.lembrete.lembrete.Service.PessoaService;
import com.example.lembrete.lembrete.controller.PessoaController;
import com.example.lembrete.lembrete.entity.Pessoa;
import com.example.lembrete.lembrete.repository.PessoaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PessoaTeste {
    @MockBean
    PessoaRepository repository;

    @Autowired
    private PessoaService service;

    @Autowired
    private PessoaController controller;

    @BeforeEach
    void injectData(){
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("Lucas"));
        Mockito.when(repository.findByNome("Lucas")).thenReturn(pessoas);
    }
    @Test
    void testControllerPessoa(){
        var pessoacontroller = controller.acharNome("Lucas");
        //long id = pessoacontroller.getBody().getId().LongValue();
        List<Pessoa> pessoas = pessoacontroller.getBody();
        for(int i=0;i<pessoas.size();i++){
            Assert.assertEquals("Lucas",pessoas.get(i).getNome());
        }
    }
}
