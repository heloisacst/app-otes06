package api;

import java.util.List;

import model.Agenda;
import model.AgendaResponse;
import model.Medico;
import model.MedicoResponse;
import model.Paciente;
import model.PacienteResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("medicos")
    Call<Void> criarMedico(@Body Medico medico);

    @GET("medicos")
    Call<MedicoResponse> listarMedicos();

    @POST("pacientes")
    Call<Void> criarPaciente(@Body Paciente paciente);

    @GET("pacientes")
    Call<PacienteResponse> listarPacientes();

    @POST("agendar")
    Call<Void> agendarConsulta(@Body Agenda agenda);

    @GET("agendar")
    Call<AgendaResponse> listarAgenda();

}
