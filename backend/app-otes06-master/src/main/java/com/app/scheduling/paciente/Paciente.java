package com.app.scheduling.paciente;

import com.app.scheduling.endereco.Endereco;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pacte_nome;
    private String pacte_email;

    private String pacte_telefone;

    @Embedded
    private Endereco endereco;

    @Column(name = "pacte_ativo")
    private Boolean pacteAtivo;

    public Paciente(DadosCadastroPaciente dados) {
        this.pacteAtivo = true;
        this.pacte_nome = dados.pacte_nome();;
        this.pacte_email = dados.pacte_email();
        this.pacte_telefone = dados.pacte_telefone();
        this.endereco = new Endereco(dados.endereco());
    }

}
