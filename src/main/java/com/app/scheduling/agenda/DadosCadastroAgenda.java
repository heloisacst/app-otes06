package com.app.scheduling.agenda;

import com.app.scheduling.medico.Medico;
import com.app.scheduling.paciente.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosCadastroAgenda(
        Long idMedico,
        Long idPaciente,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataHoraConsulta
) {}
