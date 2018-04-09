
package br.edu.ifrs.restinga.carlossoares.ds.lista2.erro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author 10070265
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequisicaoInvalida extends RuntimeException {

    public RequisicaoInvalida(String msg) {
        super(msg);
        
    }

    public RequisicaoInvalida() {
    }
    
    
}
