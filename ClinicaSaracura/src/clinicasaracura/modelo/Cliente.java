/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.modelo;

public class Cliente {
    private int id;
    private String nome;
    private String identidade;
    private String cpf;
    private String endereco;
    private String telefone;
    private String dataNascimento;

    public Cliente() {
    }

    public Cliente(int id, String nome, String identidade, String cpf, String endereco, String telefone, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.identidade = identidade;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
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

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "\nId: " + id + "\nNome: " + nome + "\nIdentidade: " + identidade +
            "\nCPF: " + cpf + "\nEndereco: " + endereco + "\nTelefone: " + telefone +
            "\nData de Nascimento: " + dataNascimento;
    }
    
    
}
