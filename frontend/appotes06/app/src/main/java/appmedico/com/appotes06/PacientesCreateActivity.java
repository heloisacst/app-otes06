package appmedico.com.appotes06;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import api.ApiService;
import api.RetrofitClient;
import model.Endereco;

import model.Paciente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.Validador;

public class PacientesCreateActivity extends AppCompatActivity {

    private TextView nameView, telefoneView, emailView, logradouroView, numeroView, complementoView, cidadeView, bairroView, ufView, cepView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pacientes_create);

       componentes();

        Button btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(v -> enviarDados());  // Chama o método quando o botão for clicado

    }

    public void componentes() {
        nameView = findViewById(R.id.nameView);
        telefoneView = findViewById(R.id.telefoneView);
        emailView = findViewById(R.id.emailView);
        logradouroView = findViewById(R.id.logradouroView);
        numeroView = findViewById(R.id.numeroView);
        complementoView = findViewById(R.id.complementoView);
        cidadeView = findViewById(R.id.cidadeView);
        ufView = findViewById(R.id.ufView);
        cepView = findViewById(R.id.cepView);
        bairroView = findViewById(R.id.bairroView);
    }

    public void enviarDados(){
        String pacte_nome = nameView.getText().toString();
        String pacte_telefone = telefoneView.getText().toString();
        String pacte_email = emailView.getText().toString();
        String logradouro = logradouroView.getText().toString();
        String numero = numeroView.getText().toString();
        String complemento = complementoView.getText().toString();
        String cidade = cidadeView.getText().toString();
        String uf = ufView.getText().toString();
        String cep = cepView.getText().toString();
        String bairro = bairroView.getText().toString();

        // Validações
        if (!Validador.validarEmail(pacte_email)) {
            Toast.makeText(PacientesCreateActivity.this, "Email inválido. Por favor, insira um email válido.", Toast.LENGTH_SHORT).show();
            return; // Não segue para a API se a validação falhar
        }

        Endereco endereco = new Endereco(logradouro, bairro, cep, cidade, uf, complemento, numero);

        Paciente paciente = new Paciente(pacte_nome, pacte_email, pacte_telefone, endereco);

        // Criação da instância do ApiService
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Envio da requisição para criar o médico
        Call<Void> call = apiService.criarPaciente(paciente);

        // Enfileirando a chamada de forma assíncrona
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // A resposta foi bem-sucedida
                    // Exibe uma mensagem de sucesso
                    Toast.makeText(PacientesCreateActivity.this, "Paciente criado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    // A resposta não foi bem-sucedida
                    Toast.makeText(PacientesCreateActivity.this, "Erro ao criar paciente!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Em caso de falha na requisição
                Toast.makeText(PacientesCreateActivity.this, "Erro na requisição: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}