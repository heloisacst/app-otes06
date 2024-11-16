package model;

import java.util.List;

public class PacienteResponse {
    private List<Paciente> content; // Lista de pacientes

    // Getter e Setter
    public List<Paciente> getContent() {
        return content;
    }

    public void setContent(List<Paciente> content) {
        this.content = content;
    }
}
