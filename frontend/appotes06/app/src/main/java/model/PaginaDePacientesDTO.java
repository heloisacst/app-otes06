package model;

import java.util.List;

public class PaginaDePacientesDTO {

    private List<Paciente> pacientes;  // Nome da chave "pacientes" do JSON
    private int totalPages;
    private int totalElements;

    // Getters and Setters
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
