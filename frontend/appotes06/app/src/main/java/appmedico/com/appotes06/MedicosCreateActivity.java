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

import java.util.ArrayList;
import java.util.List;

import data.MedicoRepository;
import model.Endereco;
import model.Medico;
import utils.Validador;

public class MedicosCreateActivity extends AppCompatActivity {

    private TextView nameView, crmView, emailView, telefoneView, logradouroView, numeroView, complementoView, cidadeView, bairroView, ufView, cepView;
    private Spinner especialidadeSpinner;
    private String especialidade;
    private MedicoRepository medicoRepository;  // Repositório para interagir com a API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medicos_create);

        medicoRepository = new MedicoRepository();  // Inicializando o repositório

        componentes();

        // Configurar o Spinner para selecionar a especialidade
        especialidadeSpinner = findViewById(R.id.spinnerEspecialidade);

        // Lista de especialidades
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
        btnSalvar.setOnClickListener(v -> enviarDados());  // Chama o método para salvar os dados quando o botão for clicado
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

        // Verifica se a especialidade foi selecionada
        if (especialidade == null || especialidade.isEmpty()) {
            Toast.makeText(this, "Por favor, selecione uma especialidade.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validações
        if (!Validador.validarEmail(email)) {
            Toast.makeText(MedicosCreateActivity.this, "Email inválido. Por favor, insira um email válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validador.validarCRM(crm)) {
            Toast.makeText(MedicosCreateActivity.this, "CRM inválido. O CRM deve ter entre 4 e 6 dígitos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Criação do objeto Endereco
        Endereco endereco = new Endereco(logradouro, bairro, cep, cidade, uf, complemento, numero);

        // Criação do objeto Medico
        Medico medico = new Medico(nome, especialidade, crm, email, telefone, endereco);

        // Enviar os dados para o repositório para criação do médico
        medicoRepository.criarMedico(medico, new MedicoRepository.MedicoCallback() {
            @Override
            public void onSuccess(String message) {
                // Exibe uma mensagem de sucesso
                Toast.makeText(MedicosCreateActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                // Exibe uma mensagem de erro
                Toast.makeText(MedicosCreateActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
