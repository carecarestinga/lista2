package br.edu.ifrs.restinga.carlossoares.ds.lista2.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 10070265
 */
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    @NotNull
    //    @NotEmpty    
    private String nome;
    //    @NotNull
    //    @NotEmpty
    //    @CPF(message = "CPF não pode ser nulo")
    //    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    @Column
    private String[] telefone;
    //    @NotNull
    //    @NotEmpty
    //    @Email
    private String email;

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String[] getTelefone() {
        return telefone;
    }

    public void setTelefone(String[] telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
