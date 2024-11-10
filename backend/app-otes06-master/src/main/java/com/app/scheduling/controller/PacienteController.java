package com.app.scheduling.controller;

import com.app.scheduling.paciente.DadosCadastroPaciente;
import com.app.scheduling.paciente.DadosListagemPaciente;
import com.app.scheduling.paciente.Paciente;
import com.app.scheduling.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10) Pageable paginacao){
        return repository.findAllByPacteAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }
}
