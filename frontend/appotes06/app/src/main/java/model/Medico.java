package model;

public class Medico {
    private int id;                // O 'id' será atribuído após a resposta da API
    private String nome;
    private String especialidade;
    private String crm;
    private String email;
    private String telefone;
    private Endereco endereco;

    // Construtor sem id
    public Medico(String nome, String especialidade, String crm, String email, String telefone, Endereco endereco) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getter e Setter para id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para especialidade
    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    // Getter e Setter para CRM
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    // Getter e Setter para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter e Setter para telefone
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Getter e Setter para endereco
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
