package br.com.api.pessoa.repositorio;

import org.springframework.data.repository.CrudRepository;

import br.com.api.pessoa.modelo.PessoaModelo;

public interface PessoaRepositorio extends CrudRepository<PessoaModelo, Long>{

    
    
} 
