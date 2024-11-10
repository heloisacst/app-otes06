package api;

import model.Medico;
import model.Paciente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("medicos")
    Call<Void> criarMedico(@Body Medico medico);

    @POST("pacientes")
    Call<Void> criarPaciente(@Body Paciente paciente);

}
