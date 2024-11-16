package model;

import java.util.Date;  // Usar Date em vez de LocalDateTime

public class Agenda {
    private int idPaciente;
    private int idMedico;
    private Date dataHora; // Alterando para Date

    public Agenda(int idPaciente, int idMedico, Date dataHora) {
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.dataHora = dataHora;
    }

    // Getters e Setters
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public Date getDataHoraConsulta() {
        return dataHora;
    }

    public void setDataHoraConsulta(Date dataHoraConsulta) {
        this.dataHora = dataHoraConsulta;
    }
}
