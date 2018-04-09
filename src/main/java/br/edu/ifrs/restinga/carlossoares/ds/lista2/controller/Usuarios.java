package br.edu.ifrs.restinga.carlossoares.ds.lista2.controller;

import br.edu.ifrs.restinga.carlossoares.ds.lista2.dao.UsuarioDAO;
import br.edu.ifrs.restinga.carlossoares.ds.lista2.erro.NaoEncontrado;
import br.edu.ifrs.restinga.carlossoares.ds.lista2.erro.RequisicaoInvalida;
import br.edu.ifrs.restinga.carlossoares.ds.lista2.model.Usuario;
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
public class Usuarios {

    @Autowired
    UsuarioDAO usuarioDAO;

    public void validaObjeto(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new RequisicaoInvalida("NOME é Obrigatório");
        }

        if (usuario.getCpf() == null || usuario.getCpf().isEmpty()) {
            throw new RequisicaoInvalida("CPF é Obrigatório");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new RequisicaoInvalida("EMAIL é Obrigatório");
        }

        for (int x = 0; x < usuario.getCpf().length(); x++) {
            for (int y = 0; y < usuario.getCpf().length(); y++) {
                if ( usuario.getCpf().charAt(x) ==  usuario.getCpf().charAt(y) && x != y ) {
                    throw new RequisicaoInvalida("CPF JÄ EXISTE");
                }
            }
        }

    }

//    @GetMapping()
    @RequestMapping(path = "/usuarios/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Usuario> listar() {
        return usuarioDAO.findAll();
    }

//    @GetMapping(path = "/{id}")
    @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Usuario recuperar(@PathVariable int id) {
        if (!usuarioDAO.existsById(id)) {
            throw new NaoEncontrado("Id não encontrada");
        }
        return usuarioDAO.findById(id).get();
    }

//    @PostMapping
    @RequestMapping(path = "/usuarios/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario inserir(@RequestBody Usuario usuario) {
        usuario.setId(0);
        validaObjeto(usuario);
        return usuarioDAO.save(usuario);
    }

//    @PutMapping
    @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        if (!usuarioDAO.existsById(id)) {
            throw new NaoEncontrado("Id não encontrada");
        }
        usuario.setId(id);
        validaObjeto(usuario);
        usuarioDAO.save(usuario);
    }

//    @DeleteMapping
    @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {

        if (!usuarioDAO.existsById(id)) {
            throw new NaoEncontrado("ID não encontrada");
        }

        usuarioDAO.deleteById(id);

    }

}
