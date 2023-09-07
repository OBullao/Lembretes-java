package com.example.lembrete.lembrete.controller;

import com.example.lembrete.lembrete.Service.LembreteService;
import com.example.lembrete.lembrete.Service.PessoaService;
import com.example.lembrete.lembrete.entity.Lembrete;
import com.example.lembrete.lembrete.entity.Pessoa;
import com.example.lembrete.lembrete.repository.LembreteRepository;
import com.example.lembrete.lembrete.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/lembrete")
public class LembreteController {


    @Autowired
    private LembreteRepository Repository;
    @Autowired
    private LembreteService Service;

    @GetMapping("/lista")
    public ResponseEntity<List<Lembrete>> lista(){
        List<Lembrete> listartudo = Service.listartudo();
        return ResponseEntity.ok(listartudo);
    }
    @GetMapping("/lista/pessoa/{nome}")
    public ResponseEntity<List<Lembrete>> parcelas(@PathVariable(value = "nome") String nomePessoa){
        List<Lembrete> listarnome= Service.achaNome(nomePessoa);
        return ResponseEntity.ok(listarnome);
    }

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id){
        Lembrete listarid = Repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Lembrete cadastro){
        try{
            this.Service.cadastrar(cadastro);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("ERRO:"+e.getMessage());
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Lembrete> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar( @PathVariable Long id, @RequestBody Lembrete atualizarId) {
        try {
            this.Service.atualizar(id, atualizarId);
            return ResponseEntity.ok().body(" atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
