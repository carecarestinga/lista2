
package br.edu.ifrs.restinga.carlossoares.ds.lista2.erro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author 10070265
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoEncontrado  extends RuntimeException {

    public NaoEncontrado(String msg) {
        super(msg);
    }
    
    
    
}
