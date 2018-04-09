package br.edu.ifrs.restinga.carlossoares.ds.lista2.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author 10070265
 */
@Entity
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    @NotNull
    //    @NotEmpty
    private String titulo;
    //    @NotNull
    //    @NotEmpty
    private String autorPrimeiroNome;
    private String autorSegundoNome;
    
    //    @Size(min = 1800)
    private int anoPublicacao;
    //    @NotNull
    //    @NotEmpty
    private String editora;
    private boolean doacao;

    public Livro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutorPrimeiroNome() {
        return autorPrimeiroNome;
    }

    public void setAutorPrimeiroNome(String autorPrimeiroNome) {
        this.autorPrimeiroNome = autorPrimeiroNome;
    }

    public String getAutorSegundoNome() {
        return autorSegundoNome;
    }

    public void setAutorSegundoNome(String autorSegundoNome) {
        this.autorSegundoNome = autorSegundoNome;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public boolean isDoacao() {
        return doacao;
    }

    public void setDoacao(boolean doacao) {
        this.doacao = doacao;
    }

}
