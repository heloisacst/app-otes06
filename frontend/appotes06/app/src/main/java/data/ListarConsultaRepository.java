package data;

import java.util.List;
import api.ApiService;
import api.RetrofitClient;
import model.Agenda;
import model.AgendaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarConsultaRepository {

    private ApiService apiService;

    public ListarConsultaRepository() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    // Método para listar consultas
    public void listarConsultas(final AgendamentoCallback callback) {
        Call<AgendaResponse> call = apiService.listarAgenda(); // A API retorna a lista de consultas

        call.enqueue(new Callback<AgendaResponse>() {
            @Override
            public void onResponse(Call<AgendaResponse> call, Response<AgendaResponse> response) {
                if (response.isSuccessful()) {
                    AgendaResponse agendaResponse = response.body();
                    if (agendaResponse != null && agendaResponse.getAgenda() != null) {
                        callback.onSuccess(agendaResponse.getAgenda()); // Retorna a lista de agendas
                    } else {
                        callback.onError("Erro ao carregar agenda: resposta vazia");
                    }
                } else {
                    callback.onError("Erro ao carregar agenda: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AgendaResponse> call, Throwable throwable) {
                callback.onError("Falha na comunicação com o servidor: " + throwable.getMessage());
            }
        });
    }

    // Interface de callback para o resultado da listagem
    public interface AgendamentoCallback {
        void onSuccess(List<Agenda> agendas); // Retorna a lista de agendas
        void onError(String errorMessage);     // Retorna mensagem de erro
    }
}
