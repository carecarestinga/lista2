package br.edu.ifrs.restinga.carlossoares.ds.lista2.dao;

import br.edu.ifrs.restinga.carlossoares.ds.lista2.model.Livro;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 10070265
 */
@Repository
public interface LivroDAO extends CrudRepository<Livro, Integer> {

    //pesquisa por titulo
    Livro findByTitulo(String titulo);

    // Equivalente ao like, mas não precisamo nos preocupar com o sinal de percentual. 
    // Podemos usar também EndingWith, Containing.
    List<Livro> findByTituloStartingWith(String titulo);

    // Ordenando pelo nome.
    List<Livro> findByTituloStartingWithOrderByTitulo(String titulo);

    // Não levar em consideração a caixa.
    List<Livro> findByTituloStartingWithIgnoreCase(String titulo);

    // Pesquisando por duas propriedades: nome e ativo.
    List<Livro> findByTituloStartingWithIgnoreCaseAndDoacaoEquals(String titulo, boolean doacao);

    //pesquisa por nome
    Livro findByAutorPrimeiroNome(String autorPrimeiroNome);

    // Equivalente ao like, mas não precisamo nos preocupar com o sinal de percentual. 
    // Podemos usar também EndingWith, Containing.
    List<Livro> findByAutorPrimeiroNomeStartingWith(String autorPrimeiroNome);

    // Ordenando pelo nome.
    List<Livro> findByAutorPrimeiroNomeStartingWithOrderByAutorPrimeiroNome(String autorPrimeiroNome);

    // Não levar em consideração a caixa.
    List<Livro> findByAutorPrimeiroNomeStartingWithIgnoreCase(String autorPrimeiroNome);

    // Pesquisando por duas propriedades: nome e ativo.
    List<Livro> findByAutorPrimeiroNomeStartingWithIgnoreCaseAndDoacaoEquals(String autorPrimeiroNome, boolean doacao);

    // Nesse caso, precisamos usar o sinal de percentual em nossas consultas.
    List<Livro> findByAutorPrimeiroNomeLike(String autorPrimeiroNome);

    // Todos com quantidade "menor que". Poderia ser usado
    // também LessThanEqual, GreaterThan, GreaterThanEqual.
    List<Livro> findByAnoPublicacaoLessThan(int anoPublicacao);

    // @Query("from Livro")
    //List<Livro> pesquisarLivros();

}
