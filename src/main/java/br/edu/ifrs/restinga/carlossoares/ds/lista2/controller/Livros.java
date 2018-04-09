package br.edu.ifrs.restinga.carlossoares.ds.lista2.controller;

import br.edu.ifrs.restinga.carlossoares.ds.lista2.dao.LivroDAO;
import br.edu.ifrs.restinga.carlossoares.ds.lista2.erro.NaoEncontrado;
import br.edu.ifrs.restinga.carlossoares.ds.lista2.erro.RequisicaoInvalida;
import br.edu.ifrs.restinga.carlossoares.ds.lista2.model.Livro;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 10070265
 */
@RestController
public class Livros {

    @Autowired
    LivroDAO livroDAO;

    public void validaObjeto(Livro livro) {

        if (livro.getTitulo() == null || livro.getTitulo().isEmpty()) {
            throw new RequisicaoInvalida("Aluno sem CPF");
        }

        if (livro.getAutorPrimeiroNome() == null || livro.getAutorPrimeiroNome().isEmpty()) {
            throw new RequisicaoInvalida("Aluno sem nome");
        }

        if (livro.getEditora() == null || livro.getEditora().isEmpty()) {
            throw new RequisicaoInvalida("Aluno sem nome");
        }

        if (livro.getAnoPublicacao() > 1800) {
            throw new RequisicaoInvalida("Ano de Publicação maior que 1800");
        }

    }

//    @GetMapping
    @RequestMapping(path = "/livros/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Livro> listar() {
        return livroDAO.findAll();

    }

//    @GetMapping(path = "/{id}")
    @RequestMapping(path = "/livros/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Livro recuperar(@PathVariable int id) {
        if (!livroDAO.existsById(id)) {
            throw new NaoEncontrado("Id não encontrada");
        }
        return livroDAO.findById(id).get();
    }

//    @PostMapping
    @RequestMapping(path = "/livros/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Livro inserir(@RequestBody Livro livro) {
        livro.setId(0);
        validaObjeto(livro);
        return livroDAO.save(livro);
    }

//    @PutMapping
    @RequestMapping(path = "/livros/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Livro livro) {
        if(!livroDAO.existsById(id)) {
            throw new NaoEncontrado("Id não encontrada");
        }
        livro.setId(id);
        validaObjeto(livro);
        livroDAO.save(livro);
    }
    
//    @DeleteMapping
    @RequestMapping(path = "/livros/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {

        if (!livroDAO.existsById(id)) {
            throw new NaoEncontrado("ID não encontrada");
        }

        livroDAO.deleteById(id);

    }

}
