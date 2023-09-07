package com.example.lembrete.lembrete.Service;

import com.example.lembrete.lembrete.entity.Lembrete;

import com.example.lembrete.lembrete.repository.LembreteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class LembreteService {
    @Autowired
    private LembreteRepository  Repository;
    public List<Lembrete> listartudo(){
        return Repository.findAll();
    }
    public List<Lembrete> achaNome(String nomePessoa){
        return Repository.findByNome(nomePessoa);
    }


    @Transactional(rollbackFor = Exception.class)
    public Lembrete cadastrar(Lembrete cadastrar) {

        return this.Repository.save(cadastrar);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Lembrete atualizar) {
        final Lembrete marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.Repository.save(atualizar);
    }

}
