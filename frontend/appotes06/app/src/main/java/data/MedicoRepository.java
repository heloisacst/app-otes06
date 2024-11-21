package data;

import java.util.List;

import api.ApiService;
import api.RetrofitClient;
import model.Medico;
import model.MedicoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicoRepository {
    private ApiService apiService;

    public MedicoRepository() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    // Método para listar médicos
    public void listarMedicos(final MedicosCallback callback){
        Call<MedicoResponse> call = apiService.listarMedicos();

        call.enqueue(new Callback<MedicoResponse>() {
            @Override
            public void onResponse(Call<MedicoResponse> call, Response<MedicoResponse> response) {
                if(response.isSuccessful()){
                    MedicoResponse medicoResponse = response.body();
                    if(medicoResponse != null && medicoResponse.getContent() != null){
                        callback.onSucess(medicoResponse.getContent());
                    } else {
                        callback.onError("Erro ao carregar médicos");
                    }
                }
            }

            @Override
            public void onFailure(Call<MedicoResponse> call, Throwable throwable) {
                callback.onError("Falha na comunicação com o servidor: " + throwable.getMessage());
            }
        });
    }

    // Novo método para criar médico
    public void criarMedico(Medico medico, final MedicoCallback callback) {
        Call<Void> call = apiService.criarMedico(medico);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess("Médico criado com sucesso!");
                } else {
                    callback.onError("Erro ao criar médico!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Falha na comunicação com o servidor: " + t.getMessage());
            }
        });
    }

    // Interface para callback de sucesso e erro ao criar médico
    public interface MedicoCallback {
        void onSuccess(String message);
        void onError(String error);
    }

    // Interface para callback de sucesso e erro ao listar médicos
    public interface MedicosCallback {
        void onSucess(List<Medico> medicos);
        void onError(String error);
    }
}
