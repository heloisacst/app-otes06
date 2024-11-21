package data;

import java.util.List;

import api.ApiService;
import api.RetrofitClient;
import model.Agenda;
import model.AgendaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendarConsultaRepository {

    private ApiService apiService;

    public AgendarConsultaRepository() {
        // Obtém uma instância do ApiService utilizando RetrofitClient
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    // Método para agendar a consulta
    public void agendarConsulta(Agenda agenda, final AgendamentoCallback callback) {
        Call<Void> call = apiService.agendarConsulta(agenda);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Se a resposta for bem-sucedida, chama o callback.onSuccess
                    callback.onSuccess("Consulta agendada com sucesso!");
                } else {
                    // Se houver erro no servidor, chama o callback.onError
                    callback.onError("Erro ao agendar consulta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                // Se a requisição falhar por problemas de rede, chama o callback.onError
                callback.onError("Falha na comunicação com o servidor: " + throwable.getMessage());
            }
        });
    }

    // Interface de callback para o resultado do agendamento
    public interface AgendamentoCallback {
        void onSuccess(String message);
        void onError(String errorMessage);
    }
}
