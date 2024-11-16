package data;

import java.util.List;

import api.ApiService;
import api.RetrofitClient;
import model.Paciente;
import model.PacienteResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PacienteRepository {

    private ApiService apiService;

    public PacienteRepository() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    // Método para listar pacientes
    public void listarPacientes(final PacientesCallback callback) {
        Call<PacienteResponse> call = apiService.listarPacientes();

        call.enqueue(new Callback<PacienteResponse>() {
            @Override
            public void onResponse(Call<PacienteResponse> call, Response<PacienteResponse> response) {
                if (response.isSuccessful()) {
                    PacienteResponse pacienteResponse = response.body();
                    if (pacienteResponse != null && pacienteResponse.getContent() != null) {
                        callback.onSuccess(pacienteResponse.getContent()); // Retorna a lista de pacientes para a Activity
                    } else {
                        callback.onError("Nenhum paciente encontrado");
                    }
                } else {
                    callback.onError("Erro ao carregar pacientes");
                }
            }

            @Override
            public void onFailure(Call<PacienteResponse> call, Throwable t) {
                callback.onError("Falha na comunicação com o servidor: " + t.getMessage());
            }
        });
    }

    // Método para criar um paciente
    public void criarPaciente(Paciente paciente, final PacienteCallback callback) {
        Call<Void> call = apiService.criarPaciente(paciente);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess("Paciente criado com sucesso!");
                } else {
                    callback.onError("Erro ao criar paciente!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Falha na comunicação com o servidor: " + t.getMessage());
            }
        });
    }

    // Interface para callback ao criar paciente
    public interface PacienteCallback {
        void onSuccess(String message);
        void onError(String error);
    }

    // Interface para callback de sucesso e erro ao listar pacientes
    public interface PacientesCallback {
        void onSuccess(List<Paciente> pacientes);
        void onError(String error);
    }
}
