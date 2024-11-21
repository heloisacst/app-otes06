package com.app.scheduling.controller;

import com.app.scheduling.paciente.DadosCadastroPaciente;
import com.app.scheduling.paciente.DadosListagemPaciente;
import com.app.scheduling.paciente.Paciente;
import com.app.scheduling.paciente.PacienteRepository;
import com.app.scheduling.paciente.dto.PaginaDePacientesDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public PaginaDePacientesDTO listar(@PageableDefault(size = 10) Pageable paginacao) {
        Page<Paciente> pacientesPage = repository.findAllByPacteAtivoTrue(paginacao);
        List<DadosListagemPaciente> pacientes = pacientesPage.getContent().stream()
                .map(DadosListagemPaciente::new)
                .collect(Collectors.toList());

        // Retorna a DTO com a lista de pacientes e informações de paginação
        return new PaginaDePacientesDTO(pacientes, pacientesPage.getTotalPages(), pacientesPage.getTotalElements());
    }
}
