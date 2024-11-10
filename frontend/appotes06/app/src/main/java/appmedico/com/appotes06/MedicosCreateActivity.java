package appmedico.com.appotes06;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import api.ApiService;
import api.RetrofitClient;
import model.Endereco;
import model.Medico;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.Validador;

public class MedicosCreateActivity extends AppCompatActivity {

    private TextView nameView, crmView, emailView, telefoneView, logradouroView, numeroView, complementoView, cidadeView, bairroView, ufView, cepView;
    private Spinner especialidadeSpinner;
    private String especialidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medicos_create);

        componentes();


        // Configurar o Spinner para selecionar a especialidade
        especialidadeSpinner = findViewById(R.id.spinnerEspecialidade);

        //lista de especialidades
        List<String> especialidades = new ArrayList<>();
        especialidades.add("ORTOPEDIA");
        especialidades.add("CARDIOLOGIA");
        especialidades.add("GINECOLOGIA");
        especialidades.add("DERMATOLOGIA");

        // Criar o ArrayAdapter com a lista de especialidades
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, especialidades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Define o layout do item da lista suspensa
        especialidadeSpinner.setAdapter(adapter); // Configura o adapter no Spinner



        Button btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(v -> enviarDados());  // Chama o método quando o botão for clicado
    }

    private void componentes() {
        nameView = findViewById(R.id.nameView);
        crmView = findViewById(R.id.crmView);
        emailView = findViewById(R.id.emailView);
        telefoneView = findViewById(R.id.telefoneView);
        logradouroView = findViewById(R.id.logradouroView);
        numeroView = findViewById(R.id.numeroView);
        complementoView = findViewById(R.id.complementoView);
        cidadeView = findViewById(R.id.cidadeView);
        ufView = findViewById(R.id.ufView);
        cepView = findViewById(R.id.cepView);
        bairroView = findViewById(R.id.bairroView);
    }

    private void enviarDados() {
        String nome = nameView.getText().toString();
        especialidade = especialidadeSpinner.getSelectedItem().toString();
        String crm = crmView.getText().toString();
        String email = emailView.getText().toString();
        String telefone = telefoneView.getText().toString();
        String logradouro = logradouroView.getText().toString();
        String numero = numeroView.getText().toString();
        String complemento = complementoView.getText().toString();
        String cidade = cidadeView.getText().toString();
        String uf = ufView.getText().toString();
        String cep = cepView.getText().toString();
        String bairro = bairroView.getText().toString();

        if (especialidade == null || especialidade.isEmpty()) {
            Toast.makeText(this, "Por favor, selecione uma especialidade.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validações
        if (!Validador.validarEmail(email)) {
            Toast.makeText(MedicosCreateActivity.this, "Email inválido. Por favor, insira um email válido.", Toast.LENGTH_SHORT).show();
            return; // Não segue para a API se a validação falhar
        }

        if (!Validador.validarCRM(crm)) {
            Toast.makeText(MedicosCreateActivity.this, "CRM inválido. O CRM deve ter entre 4 e 6 dígitos.", Toast.LENGTH_SHORT).show();
            return; // Não segue para a API se a validação falhar
        }

        Endereco endereco = new Endereco(logradouro, bairro, cep, cidade, uf, complemento, numero);

        Medico medico = new Medico(nome, especialidade, crm, email, telefone, endereco);

        // Criação da instância do ApiService
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Envio da requisição para criar o médico
        Call<Void> call = apiService.criarMedico(medico);

        // Enfileirando a chamada de forma assíncrona
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // A resposta foi bem-sucedida
                    // Exibe uma mensagem de sucesso
                    Toast.makeText(MedicosCreateActivity.this, "Médico criado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    // A resposta não foi bem-sucedida
                    Toast.makeText(MedicosCreateActivity.this, "Erro ao criar médico!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Em caso de falha na requisição
                Toast.makeText(MedicosCreateActivity.this, "Erro na requisição: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
