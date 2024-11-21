package com.app.scheduling.agenda;

import com.app.scheduling.medico.Medico;
import com.app.scheduling.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Table(name="agendamento")
@EqualsAndHashCode(of = "id")
@Getter
@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico idMedico;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente idPaciente;

    @Column(name="data_hora_consulta")
    private LocalDateTime dataHoraConsulta;

    public Agenda(Medico medico, Paciente paciente, LocalDateTime dataHoraConsulta) {
        this.idMedico = medico;
        this.idPaciente = paciente;
        this.dataHoraConsulta = dataHoraConsulta;
    }
}
