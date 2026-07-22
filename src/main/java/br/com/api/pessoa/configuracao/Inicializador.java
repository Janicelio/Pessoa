package br.com.api.pessoa.configuracao;

// Importações
import br.com.api.pessoa.modelo.PessoaModelo;
import br.com.api.pessoa.repositorio.PessoaRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Classe como um componente gerenciado pelo Spring, permitindo a injeção de dependência e execução na inicialização
@Component
public class Inicializador implements CommandLineRunner {

    // Atributo da classe
    @SuppressWarnings("unused")
    private final PessoaRepositorio pessoaRepositorio;

    // Construtor que injeta a dependência do PessoaRepositorio, permitindo o acesso ao banco de dados
    public Inicializador(PessoaRepositorio pessoaRepositorio) {
        this.pessoaRepositorio = pessoaRepositorio; 
    }

    // Método que é executado logo após a inicialização da aplicação
    @Override
    public void run(String... args) throws Exception {
       
        // Criar objetos que serão utilizados para armazenar na tabela de pessoas
        PessoaModelo pessoa1 = new PessoaModelo();
        pessoa1.setNome("João");
        pessoa1.setIdade(30);  
        pessoa1.setCidade("São Paulo");

        PessoaModelo pessoa2 = new PessoaModelo();
        pessoa2.setNome("Maria");
        pessoa2.setIdade(25);
        pessoa2.setCidade("Rio de Janeiro");

        PessoaModelo pessoa3 = new PessoaModelo();
        pessoa3.setNome("Carlos");
        pessoa3.setIdade(40);
        pessoa3.setCidade("Belo Horizonte");

        PessoaModelo pessoa4 = new PessoaModelo();
        pessoa4.setNome("Ana");
        pessoa4.setIdade(28);
        pessoa4.setCidade("São Paulo");

        PessoaModelo pessoa5 = new PessoaModelo();
        pessoa5.setNome("Luciano");
        pessoa5.setIdade(35);
        pessoa5.setCidade("Rio de Janeiro");

        PessoaModelo pessoa6 = new PessoaModelo();
        pessoa6.setNome("Suellen");
        pessoa6.setIdade(22);
        pessoa6.setCidade("Curitiba");

        PessoaModelo pessoa7 = new PessoaModelo();
        pessoa7.setNome("Felipe");
        pessoa7.setIdade(50);
        pessoa7.setCidade("Salvador");

        PessoaModelo pessoa8 = new PessoaModelo();
        pessoa8.setNome("Luana");
        pessoa8.setIdade(30);
        pessoa8.setCidade("Porto Alegre");

        PessoaModelo pessoa9 = new PessoaModelo();
        pessoa9.setNome("Ricardo");
        pessoa9.setIdade(40);
        pessoa9.setCidade("Florianópolis");

        PessoaModelo pessoa10 = new PessoaModelo();
        pessoa10.setNome("Sofia");
        pessoa10.setIdade(27);
        pessoa10.setCidade("Fortaleza");

        PessoaModelo pessoa11 = new PessoaModelo();
        pessoa11.setNome("Gustavo");
        pessoa11.setIdade(33);
        pessoa11.setCidade("Recife");

        PessoaModelo pessoa12 = new PessoaModelo();
        pessoa12.setNome("Fernanda");
        pessoa12.setIdade(45);
        pessoa12.setCidade("São Paulo");

        PessoaModelo pessoa13 = new PessoaModelo();
        pessoa13.setNome("Roberta");
        pessoa13.setIdade(26);
        pessoa13.setCidade("Manaus");

        PessoaModelo pessoa14 = new PessoaModelo();
        pessoa14.setNome("Eduardo");
        pessoa14.setIdade(38);
        pessoa14.setCidade("Campinas");

        PessoaModelo pessoa15 = new PessoaModelo();
        pessoa15.setNome("Camila");
        pessoa15.setIdade(31);
        pessoa15.setCidade("Belém");

        // Efetuar cadastros
        pessoaRepositorio.save(pessoa1);
        pessoaRepositorio.save(pessoa2);
        pessoaRepositorio.save(pessoa3);
        pessoaRepositorio.save(pessoa4);
        pessoaRepositorio.save(pessoa5);
        pessoaRepositorio.save(pessoa6);
        pessoaRepositorio.save(pessoa7);
        pessoaRepositorio.save(pessoa8);
        pessoaRepositorio.save(pessoa9);
        pessoaRepositorio.save(pessoa10);
        pessoaRepositorio.save(pessoa11);
        pessoaRepositorio.save(pessoa12);
        pessoaRepositorio.save(pessoa13);
        pessoaRepositorio.save(pessoa14);
        pessoaRepositorio.save(pessoa15);

    }
}