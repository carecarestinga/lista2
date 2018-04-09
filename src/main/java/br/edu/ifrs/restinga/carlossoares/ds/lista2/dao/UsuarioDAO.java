
package br.edu.ifrs.restinga.carlossoares.ds.lista2.dao;

import br.edu.ifrs.restinga.carlossoares.ds.lista2.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 10070265
 */
@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer>{
    
}
