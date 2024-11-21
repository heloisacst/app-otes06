package com.app.scheduling.paciente.dto;

import com.app.scheduling.paciente.DadosListagemPaciente;

import java.util.List;

public class PaginaDePacientesDTO {
    private List<DadosListagemPaciente> pacientes;
    private int totalPages;
    private long totalElements;

    // Construtores, getters e setters
    public PaginaDePacientesDTO(List<DadosListagemPaciente> pacientes, int totalPages, long totalElements) {
        this.pacientes = pacientes;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<DadosListagemPaciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<DadosListagemPaciente> pacientes) {
        this.pacientes = pacientes;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
