package com.app.scheduling.controller;

import com.app.scheduling.agenda.Agenda;
import com.app.scheduling.agenda.AgendamentoRepository;
import com.app.scheduling.agenda.DadosCadastroAgenda;
import com.app.scheduling.medico.Medico;
import com.app.scheduling.medico.MedicoRepository;
import com.app.scheduling.paciente.Paciente;
import com.app.scheduling.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agendar")
public class AgendamentoController {

    @Autowired
    AgendamentoRepository repository;
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public String agendar(@RequestBody DadosCadastroAgenda dados) {
        System.out.println("antes:" + dados.idMedico());
        Medico medico = medicoRepository.findById(dados.idMedico()).orElseThrow(() -> new RuntimeException("Medico não encontrado"));
        Paciente paciente = pacienteRepository.findById(dados.idPaciente()).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        System.out.println(medico.getId() + " "+ paciente.getId());
        Agenda agenda = new Agenda(medico, paciente, dados.dataHoraConsulta());
        repository.save(agenda);
        return "Agendamento realizado!";
    }
}