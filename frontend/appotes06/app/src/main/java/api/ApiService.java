package api;

import java.util.List;

import model.Medico;
import model.Paciente;
import model.PaginaDePacientesDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("medicos")
    Call<Void> criarMedico(@Body Medico medico);

    @POST("pacientes")
    Call<Void> criarPaciente(@Body Paciente paciente);

    @GET("pacientes")
    Call<PaginaDePacientesDTO> getPacientes();
}
