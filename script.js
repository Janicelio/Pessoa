// Garante que o script execute após o HTML ser carregado.
document.addEventListener('DOMContentLoaded', function () {

    // Função para carregar todos os registros
    function carregarPessoas() {
        fetch('http://localhost:8080')
            .then(response => response.json())
            .then(data => {
                const tabela = document.getElementById('tabelaPessoas').getElementsByTagName('tbody')[0];
                tabela.innerHTML = ''; // Limpar a tabela

                data.forEach(function (pessoa) {
                    const row = tabela.insertRow();
                    row.innerHTML = `
                        <td>${pessoa.codigo}</td>
                        <td>${pessoa.nome}</td>
                        <td>${pessoa.idade}</td>
                        <td>${pessoa.cidade}</td>
                        <td>
                            <button class="btn btn-warning btn-editar" data-codigo="${pessoa.codigo}">Editar</button>
                            <button class="btn btn-danger btn-deletar" data-codigo="${pessoa.codigo}">Excluir</button>
                        </td>
                    `;
                });
            })
            .catch(error => console.error('Erro ao carregar dados:', error));
    }

    // Carregar as pessoas ao carregar a página
    carregarPessoas();

    // Função de validação do formulário
    function validarFormulario(nome, idade, cidade) {
        // Validação para o nome
        if (nome.trim().length < 3 || nome.trim().length > 30) {
            alert('O nome deve ter entre 3 e 30 caracteres.');
            return false;
        }

        // Validação para a idade
        if (isNaN(idade) || idade < 0 || idade > 120) {
            alert('A idade deve ser um número entre 0 e 120.');
            return false;
        }

        // Validação para a cidade
        if (cidade.trim().length < 3 || cidade.trim().length > 30) {
            alert('O nome da cidade deve ter entre 3 e 30 caracteres.');
            return false;
        }

        return true;
    }

    // Adicionar ou atualizar uma pessoa
    document.getElementById('formPessoa').addEventListener('submit', function (e) {
        e.preventDefault();

        // Obter valores do formulário
        const codigo = document.getElementById('codigo').value; // Será gerado no backend
        const nome = document.getElementById('nome').value;
        const idade = document.getElementById('idade').value;
        const cidade = document.getElementById('cidade').value;

        // Validar os dados
        if (!validarFormulario(nome, idade, cidade)) {
            return; // Se houver erro de validação, não enviar a requisição
        }

        const pessoa = { nome, idade, cidade }; // Não incluir 'codigo' aqui

        if (codigo) {
            // Atualiza a pessoa
            fetch(`http://localhost:8080/${codigo}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(pessoa)
            })
                .then(response => response.json())
                .then(() => {
                    alert('Pessoa atualizada!');
                    carregarPessoas();
                    document.getElementById('formPessoa').reset(); // Limpar o formulário
                    document.getElementById('btnSubmit').textContent = 'Salvar';
                })
                .catch(error => console.error('Erro ao atualizar a pessoa:', error));
        } else {
            // Cria uma nova pessoa - 'codigo' será gerado no backend
            fetch('http://localhost:8080', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(pessoa) // Não enviar 'codigo' no corpo
            })
                .then(response => response.json())
                .then(() => {
                    alert('Pessoa adicionada!');
                    carregarPessoas();
                    document.getElementById('formPessoa').reset(); // Limpar o formulário
                })
                .catch(error => console.error('Erro ao adicionar a pessoa:', error));
        }
    });

    // Editar pessoa
    document.addEventListener('click', function (e) {
        if (e.target && e.target.classList.contains('btn-editar')) {
            const codigo = e.target.dataset.codigo;
            fetch(`http://localhost:8080/${codigo}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('codigo').value = data.codigo; // Preencher o código, mas ele ficará oculto
                    document.getElementById('nome').value = data.nome;
                    document.getElementById('idade').value = data.idade;
                    document.getElementById('cidade').value = data.cidade;
                    document.getElementById('btnSubmit').textContent = 'Atualizar';
                })
                .catch(error => console.error('Erro ao editar a pessoa:', error));
        }
    });

    // Deletar pessoa
    document.addEventListener('click', function (e) {
        if (e.target && e.target.classList.contains('btn-deletar')) {
            const codigo = e.target.dataset.codigo;
            if (confirm('Tem certeza que deseja excluir?')) {
                fetch(`http://localhost:8080/${codigo}`, {
                    method: 'DELETE'
                })
                    .then(() => {
                        alert('Pessoa excluída!');
                        carregarPessoas();
                    })
                    .catch(error => console.error('Erro ao excluir a pessoa:', error));
            }
        }
    });
});
