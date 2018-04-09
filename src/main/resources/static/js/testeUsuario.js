async function teste() {
    let teste = new TestController("professores/");
    await teste.testar("GET", "", {},
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 200 && Array.isArray(dados), "listar");
        fechar();
    });

    await teste.testar("POST", "", {
        "nome": "Teste ok",
        "siap": "1",
        "turmas": [1, 3]
    },
            (metodo, param, envio, status, retorno) => {
        exibir(metodo, param, envio, status, retorno);
        verificar(status == 201, "Status inserção");
        verificar(envio.nome == retorno.nome && envio.siap == retorno.siap, "Campos");
        fechar();
    });


    await teste.testar("POST", "", {
        "id": 144,
        "nome": "Teste id",
        "siap": "1",
        "turmas": [1, 3]
    },
            (metodo, param, envio, status, retorno) => {
        exibir(metodo, param, envio, status, retorno);
        verificar(envio.id != retorno.id && retorno.id > 0, "ID");
        fechar();
    });


    await teste.testar("POST", "", {
        "siap": "2",
        "turmas": [1, 3, 1]
    },
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 400, "Sem o nome");
        fechar();

    });


    await teste.testar("POST", "", {
        "nome": "nome ",
        "siap": "",
        "turmas": [1, 3, 1]
    },
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 400, "Campo vazio");
        fechar();

    });


    await teste.testar("POST", "", {
        "nome": "Teste de lista duplicada",
        "siap": "2",
        "turmas": [1, 3, 1]
    },
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 400, "Tratamento de duplicados");
        fechar();

    });

    await teste.testar("POST", "", {
        "nome": "João do Exemplo",
        "siap": "2",
        "turmas": []
    },
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 400, "Lista vazia");
        fechar();

    });

    await teste.testar("POST", "", {
        "nome": "João do Exemplo",
        "siap": "2",
        "turmas": [1,2,3, 4,5,6, 7, 8, 9]
    },
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 400, "Lista muito grande");
        fechar();

    });




    await teste.testar("POST", "", {
        "nome": "João do Exemplo",
        "siap": "2"
    },
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 400, "Sem o campo da lista");

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
        verificar(status == 404, "buscar conteudo inexistente");
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
        "nome": nome,
        "siap": "1",
        "turmas": [1, 3]
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
        "nome": "",
        "siap": "1",
        "turmas": [1, 3]
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
        "nome": "aaa",
        "siap": "1",
        "turmas": [1, 3]
    },
            (metodo, param, conteudo, status, dados) => {
        exibir(metodo, param, conteudo, status, dados);
        verificar(status == 404, "atualizar conteúdo inexistente");
        fechar();
    });


    await teste.testar("PUT", "/" + lista[lista.length - 1].id, {
        "nome": "novo nome",
        "siap": "1",
        "turmas": []
    },
            async (metodo, param, envio, status, retorno) => {
        await teste.testar("GET", "/" + lista[lista.length - 1].id, {},
                (metodo2, param2, conteudo2, status2, retorno) => {
            exibir(metodo, param, envio, status, retorno);
            verificar(status == "400", "Atualização lista vazia status");
            verificar(retorno.nome == nome, "Atualização lista vazia");
            fechar();
        });
    });


    await teste.testar("PUT", "/" + lista[lista.length - 1].id, {
        "nome": "novo nome",
        "siap": "1",
        "turmas": [1,2,3, 4,5,6,7,8, 9]
    },
            async (metodo, param, envio, status, retorno) => {
        await teste.testar("GET", "/" + lista[lista.length - 1].id, {},
                (metodo2, param2, conteudo2, status2, retorno) => {
            exibir(metodo, param, envio, status, retorno);
            verificar(status == "400", "Atualização lista grande status");
            verificar(retorno.nome == nome, "Atualização lista grande");
            fechar();
        });
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