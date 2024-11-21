package model;

public class Agenda {
    private int idPaciente;
    private int idMedico;
    private String nomePaciente;
    private String nomeMedico;
    private String dataHoraConsulta;

    public Agenda(int idPaciente, int idMedico, String dataHoraConsulta) {
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.dataHoraConsulta = dataHoraConsulta;
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

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getDataHoraConsulta() {
        return dataHoraConsulta;
    }

    public void setDataHoraConsulta(String dataHoraConsulta) {
        this.dataHoraConsulta = dataHoraConsulta;
    }
}
