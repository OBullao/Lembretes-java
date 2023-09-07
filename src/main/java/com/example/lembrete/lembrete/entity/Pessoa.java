package com.example.lembrete.lembrete.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import java.util.List;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "Pessoa",schema = "public")
public class Pessoa extends AbstractEntity {
    @Column(name = "Nome",nullable = false,length = 30)
    private String Nome;
    @OneToMany(mappedBy = "pessoa")
    @JsonManagedReference
    @Column(name = "tarefas",length = 100)
    private List<Lembrete> lembretes;


    public Pessoa(String nome) {
        this.Nome = nome;
    }
}
