package model;

public class Medico {
    private String nome;
    private String especialidade;
    private String crm;
    private String email;
    private String telefone;
    private Endereco endereco;

    public Medico(String nome, String especialidade, String crm, String email, String telefone, Endereco endereco) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }
}
