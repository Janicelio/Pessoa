package br.com.api.pessoa.servico;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.pessoa.dto.PessoaModeloDTO;
import br.com.api.pessoa.modelo.PessoaModelo;
import br.com.api.pessoa.repositorio.PessoaRepositorio;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PessoaServico {

    // Atributos da classe
    private final PessoaRepositorio pr;

    // Método responsável pela listagem de pessoas
   public ResponseEntity<Iterable<PessoaModelo>> listarPessoas(){
        return new ResponseEntity<> (this.pr.findAll(), HttpStatus.OK); // Retona uma lista de pessoas
    }

    // Método responsável pelo cadastro de pessoas
    public ResponseEntity<PessoaModelo> cadastratPessoa(PessoaModelo pm){
        return new ResponseEntity<>(this.pr.save(pm), HttpStatus.CREATED);
    }

    // Método responsável pela alteração total dos dados
    public ResponseEntity<PessoaModelo> alterarPessoaTotal(Long codigo, PessoaModelo pm){
        // Obter o registro contido na tabela
        Optional<PessoaModelo> obj = this.pr.findById(codigo);

        // Condicional - Registro encontrado?
        if(obj.isPresent()){
           pm.setCodigo(codigo);
            return new ResponseEntity<>(this.pr.save(pm),HttpStatus.OK);         
        }

        // Caso o registro não tenha sido encontrato
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);    
    }

    // Método responsável pela alteração parcial dos dados
    public ResponseEntity<PessoaModelo> alterarPessoaParcial(Long codigo, PessoaModelo pm){
        // Obter o registro contido na tabela
        Optional<PessoaModelo> obj = this.pr.findById(codigo);

        // Condicional - Se o registro foi encontrado
        if(obj.isPresent()){

            // Converte Optional para PessoaModelo
            PessoaModelo pm2 = obj.get();

            // Verifica Nome
            if(pm.getNome() != null){
                pm2.setNome(pm.getNome());
            }

            // Verifica Idade
            if(pm.getIdade() != null){
                pm2.setIdade(pm.getIdade());
            }

            // Verifica Cidade
            if(pm.getCidade() != null){
                pm2.setCidade(pm.getCidade());
            }

            // Retorno
            return new ResponseEntity<>(this.pr.save(pm2), HttpStatus.OK);         
        }

        // Caso o registro não tenha sido encontrado
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 

    }

    // Método responsável pela remoção de pessoas
    public ResponseEntity<Void> removerPessoa(Long codigo){
        // Verificar a existência do código
        boolean existeCodigo = this.pr.existsById(codigo);

        // Condicional - Remoção do registro encontrato (existente)
        if(existeCodigo){
            this.pr.deleteById(codigo);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        // Caso o registro não exista (não seja encontrato)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
   
    
    // Método responsável por retornar um objeto do tipo PessoaModelo através do código
    public ResponseEntity<PessoaModelo> localizarPessoa(Long codigo){
        // Verificar a existência do código
        boolean existeCodigo = this.pr.existsById(codigo);

        // Condicional
        if(existeCodigo){
            // Criar um obj do tipo Optional que recebe o PessoaModelo via findById
            Optional<PessoaModelo> obj = this.pr.findById(codigo);

            // Converter Optional para PessoaModelo
            PessoaModelo pm = obj.get();

            // Retorno
            return new ResponseEntity<>(pm, HttpStatus.OK);
        }

        // Retorno caso o código não exista
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Método responsável pela listagem de pessoas
    public ResponseEntity<Iterable<PessoaModeloDTO>> listarPessoas2(){
        // Busca todas as pessoas do banco de dados usando o repositório
        Iterable<PessoaModelo> pessoas = this.pr.findAll();

        // Converte cada PessoaModelo em um PessoaModeloDTO
        Iterable<PessoaModeloDTO> pessoasDTO = StreamSupport.stream(pessoas.spliterator(), false)
        .map(p -> new PessoaModeloDTO(p.getNome(), p.getIdade()))
        .toList(); // Converte o resultado em uma lista (que também é um Iterable)

        // Retorno
        return new ResponseEntity<>(pessoasDTO, HttpStatus.OK);
    }

}
