package com.app.scheduling.agenda;

import java.time.LocalDateTime;
public record DadosListagemAgenda(String nomePaciente, String nomeMedico, LocalDateTime dataHoraConsulta) {
    public DadosListagemAgenda(Agenda agenda) {
        this(
                agenda.getIdPaciente().getPacte_nome(),
                agenda.getIdMedico().getNome(),
                agenda.getDataHoraConsulta()
        );
    }
}
