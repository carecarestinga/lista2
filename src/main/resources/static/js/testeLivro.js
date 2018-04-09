async function teste() {
    let teste = new TestController("livros/");
    await teste.testar("GET", "", {},
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 200 && Array.isArray(dados), "listar");
        fechar();
    });

    await teste.testar("POST", "", {
        "titulo": "titulo1",
        "autorPrimeiroNome": "autorPrimeiroNome1",
        "editora": "editora1"
    },
            (metodo, param, envio, status, retorno) => {
        exibir(metodo, param, envio, status, retorno);
        verificar(status == 201, "Status inserção");
        verificar(envio.titulo == retorno.titulo && envio.autorPrimeiroNome == retorno.autorPrimeiroNome
                && envio.editora == retorno.editora, "Campos");
        fechar();
    });


    await teste.testar("POST", "", {
        "id": 144,
        "titulo": "Titulo1",
        "autorPrimeiroNome": "autorPrimeiroNome1",
        "editora": "editora1"
    },
            (metodo, param, envio, status, retorno) => {
        exibir(metodo, param, envio, status, retorno);
        verificar(envio.id != retorno.id && retorno.id > 0, "ID");
        fechar();
    });


    await teste.testar("POST", "", {
        "autorPrimeiroNome": "autorPrimeiroNome1"

    },
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 400, "Sem o autorPrimeiroNome1");
        fechar();

    });


    await teste.testar("POST", "", {
        "titulo": "Teste id",
        "autorPrimeiroNome": "autorPrimeiroNome1",
        "editora": "editora1"
    },
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 400, "Campo vazio");
        fechar();

    });


    let lista;
    await teste.testar("GET", "", {},
            (metodo, param, conteudo, status, dados) => {
        lista = dados;
    });

    await teste.testar("GET", "/3000", {},
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 404, "buscar conteúdo inexistente");
        fechar();
    });

    await teste.testar("GET", "/" + lista[lista.length - 1].id, {},
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(dados.nome == lista[lista.length - 1].nome, "buscar por ID");
        fechar();
    });

    let nome = "Nome mudado " + Math.random();
    await teste.testar("PUT", "/" + lista[lista.length - 1].id, {
        "id": 144,
        "titulo": "Titulo1111",
        "autorPrimeiroNome": "autorPrimeiroNome1111",
        "editora": "editora1111"
    },
            async (metodo, param, envio, status, retorno) => {
        await teste.testar("GET", "/" + lista[lista.length - 1].id, {},
                (metodo2, param2, conteudo2, status2, retorno) => {
            exibir(metodo, param, envio, status, retorno);
            verificar(status == "204", "Atualização status");
            verificar(retorno.nome == nome, "Mudança de nome");
            fechar();
        });
    });

    await teste.testar("PUT", "/" + lista[lista.length - 1].id, {
        "titulo": "",
        "autorPrimeiroNome": "autorPrimeiroNome1111",
        "editora": "editora1111"
    },
            async (metodo, param, envio, status, retorno) => {
        await teste.testar("GET", "/" + lista[lista.length - 1].id, {},
                (metodo2, param2, conteudo2, status2, retorno) => {
            exibir(metodo, param, envio, status, retorno);
            verificar(status == "400", "Atualização lista vazia status");
            verificar(retorno.nome == nome, "Mudança de nome em branco");
            fechar();
        });
    });

    await teste.testar("PUT", "/3000", {
        "titulo": "Titulo111222",
        "autorPrimeiroNome": "autorPrimeiroNome1111",
        "editora": "editora1111"
    },
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 404, "atualizar conteúdo inexistente");
        fechar();
    });

    await teste.testar("DELETE", "/3000", {},
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 404, "apagar conteúdo inexistente");
        fechar();
    });

    await teste.testar("DELETE", "/" + lista[lista.length - 1].id, {},
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 204, "apagar conteúdo");
        fechar();
    });


}



teste();