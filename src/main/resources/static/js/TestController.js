/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


class TestController {
    constructor(url) {
        this.url=url;

    }

    testar(metodo,param,conteudo, callBack) {
        return fetch(`${this.url}${param}`,{
            method:metodo,
             headers: new Headers({
                'Content-Type': 'application/json'
               }),
            body:metodo=="GET"?null:JSON.stringify(conteudo)
        }).then((resposta) =>{
                resposta.json().then(
                        (dados)=>{
                            callBack(metodo,param,conteudo,resposta.status,dados);
                        }
                        ).catch((erro)=>callBack(metodo,param,conteudo,resposta.status,{}));
        } );

    }
 
    
    
}