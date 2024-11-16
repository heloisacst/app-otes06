package model;

public class Paciente {
    private int id;                // O 'id' é agora opcional e será setado após a criação
    private String pacte_nome;
    private String pacte_email;
    private String pacte_telefone;
    private Endereco endereco;

    // Construtor sem id
    public Paciente(String pacte_nome, String pacte_email, String pacte_telefone, Endereco endereco) {
        this.pacte_nome = pacte_nome;
        this.pacte_email = pacte_email;
        this.pacte_telefone = pacte_telefone;
        this.endereco = endereco;
    }

    // Getter e Setter para id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters e Setters para pacte_nome
    public String getPacte_nome() {
        return pacte_nome;
    }

    public void setPacte_nome(String pacte_nome) {
        this.pacte_nome = pacte_nome;
    }

    // Getters e Setters para pacte_email
    public String getPacte_email() {
        return pacte_email;
    }

    public void setPacte_email(String pacte_email) {
        this.pacte_email = pacte_email;
    }

    // Getters e Setters para pacte_telefone
    public String getPacte_telefone() {
        return pacte_telefone;
    }

    public void setPacte_telefone(String pacte_telefone) {
        this.pacte_telefone = pacte_telefone;
    }

    // Getter e Setter para endereco
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
