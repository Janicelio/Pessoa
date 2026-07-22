package br.com.api.pessoa.controle;

import br.com.api.pessoa.dto.PessoaModeloDTO;
import br.com.api.pessoa.modelo.PessoaModelo;
import br.com.api.pessoa.servico.PessoaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Pessoa", description = "Endpoints para gerenciamento de pessoas")
@CrossOrigin(value = "*")
@RestController
@RequiredArgsConstructor
public class PessoaControle {

    // Atributo da classe PessoaControle
    private final PessoaServico ps;

    // Rota responsável pela listagem de pessoas
    @Operation(summary = "Listar todas as pessoas")
    @GetMapping("/")
    public ResponseEntity<Iterable<PessoaModelo>> listarPessoas(){
        return this.ps.listarPessoas(); // Retona uma lista de pessoas
    }

    // Rota responsável pelo cadastro de pessoas
    @Operation(summary = "Cadastrar uma nova pessoas")
    @PostMapping("/")
    public ResponseEntity<PessoaModelo> cadastratPessoa(@Valid @RequestBody PessoaModelo pm){
        return this.ps.cadastratPessoa(pm);
    }

    // Rota responsável pela alteração total dos dados
    @Operation(summary = "Alteração total de uma pessoa (todos os atributos)")
    @PutMapping("/{codigo}")
    public ResponseEntity<PessoaModelo> alterarPessoaTotal(@PathVariable Long codigo, @Valid @RequestBody PessoaModelo pm){
        return this.ps.alterarPessoaTotal(codigo, pm);    
    }

    // Rota responsável pela alteração parcial dos dados
    @Operation(summary = "Alteração parcial de uma pessoas (alteração de um ou mais atributos)")
    @PatchMapping("/{codigo}")
    public ResponseEntity<PessoaModelo> alterarPessoaParcial(@PathVariable Long codigo, @RequestBody PessoaModelo pm){
        return this.alterarPessoaParcial(codigo, pm);
    }

    // Rota responsável pela remoção de pessoas
    @Operation(summary = "remoção de uma pessoas")
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removerPessoa(@PathVariable Long codigo){
        return this.ps.removerPessoa(codigo);
    }

    // Rota responsável por listar uma pessoa através do código
    @Operation(summary = "Listar uma pessoas, dado um código")
    @GetMapping("/{codigo}")
    public ResponseEntity<PessoaModelo> buscarPessoa(@PathVariable Long codigo) {
        return this.ps.localizarPessoa(codigo);
    }

    // Rota responsável pela listagem de pessoas (apenas nomes e idades)
    @Operation(summary = "Listar parcialmente os atributos de uma dada pessoas")
    @GetMapping("/listar2")
    public ResponseEntity<Iterable<PessoaModeloDTO>> listarPessoas2(){
        return this.ps.listarPessoas2();
    }

}
