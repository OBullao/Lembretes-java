package com.example.lembrete.lembrete.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@MappedSuperclass
@Getter @Setter
public abstract class AbstractEntity {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable=false,unique=true)
    private Long id;
}
