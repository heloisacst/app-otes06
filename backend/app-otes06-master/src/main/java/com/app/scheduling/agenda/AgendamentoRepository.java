package com.app.scheduling.agenda;

import com.app.scheduling.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agenda, Long> {
    Page<Agenda> findAll(Pageable pageable);
}
