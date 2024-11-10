package model;

public class Paciente {
    private String pacte_nome;
    private String pacte_email;
    private String pacte_telefone;
    private Endereco endereco;

    public Paciente(String pacte_nome, String pacte_email, String pacte_telefone, Endereco endereco) {
        this.pacte_nome = pacte_nome;
        this.pacte_email = pacte_email;
        this.pacte_telefone = pacte_telefone;
        this.endereco = endereco;
    }
}
