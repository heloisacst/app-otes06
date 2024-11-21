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

    public String getPacte_nome() {
        return pacte_nome;
    }

    public void setPacte_nome(String pacte_nome) {
        this.pacte_nome = pacte_nome;
    }

    public String getPacte_email() {
        return pacte_email;
    }

    public void setPacte_email(String pacte_email) {
        this.pacte_email = pacte_email;
    }

    public String getPacte_telefone() {
        return pacte_telefone;
    }

    public void setPacte_telefone(String pacte_telefone) {
        this.pacte_telefone = pacte_telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    // Método toString() para facilitar a visualização
    @Override
    public String toString() {
        return "Paciente{" +
                "nome='" + pacte_nome + '\'' +
                ", email='" + pacte_email + '\'' +
                ", telefone='" + pacte_telefone + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
