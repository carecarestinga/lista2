package br.edu.ifrs.restinga.carlossoares.ds.lista2.dao;

import br.edu.ifrs.restinga.carlossoares.ds.lista2.model.Usuario;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 10070265
 */
@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
    // pesquisar por nome
    Usuario findByNome(String nome);

    // Equivalente ao like, mas não precisamo nos preocupar com o sinal de percentual. 
    // Podemos usar também EndingWith, Containing.
    List<Usuario> findByNomeStartingWith(String nome);

    // Ordenando pelo nome.
    List<Usuario> findByNomeStartingWithOrderByNome(String nome);

    // Não levar em consideração a caixa.
    List<Usuario> findByNomeStartingWithIgnoreCase(String nome);

    // Nesse caso, precisamos usar o sinal de percentual em nossas consultas.
    List<Usuario> findByNomeLike(String nome);

    // Quando você quer negar o que passa no parâmetro
    List<Usuario> findByNomeNot(String nome);
    
    // pesquisar por nome
    Usuario findByCpf(String cpf);

    // Equivalente ao like, mas não precisamo nos preocupar com o sinal de percentual. 
    // Podemos usar também EndingWith, Containing.
    List<Usuario> findByCpfStartingWith(String cpf);

    // Ordenando pelo nome.
    List<Usuario> findByCpfStartingWithOrderByCpf(String cpf);

    // Não levar em consideração a caixa.
    List<Usuario> findByCpfStartingWithIgnoreCase(String cpf);

    // Nesse caso, precisamos usar o sinal de percentual em nossas consultas.
    List<Usuario> findByCpfLike(String cpf);

    // Quando você quer negar o que passa no parâmetro
    List<Usuario> findByCpfNot(String cpf);

    // Todos os usuarios com os IDs passados no varargs. Poderia usar NotIn para negar os IDs.
    List<Usuario> findByIdIn(int... ids);

    // @Query("from Usuario")
    //List<Usuario> pesquisarUsuarios();

}
