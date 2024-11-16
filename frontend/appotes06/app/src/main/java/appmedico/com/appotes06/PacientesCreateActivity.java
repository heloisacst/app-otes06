package appmedico.com.appotes06;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import data.PacienteRepository;
import model.Endereco;
import model.Paciente;
import utils.Validador;

public class PacientesCreateActivity extends AppCompatActivity {

    private TextView nameView, telefoneView, emailView, logradouroView, numeroView, complementoView, cidadeView, bairroView, ufView, cepView;
    private PacienteRepository pacienteRepository;  // Repositório para interagir com a API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pacientes_create);

        pacienteRepository = new PacienteRepository();  // Inicializando o repositório

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

    public void enviarDados() {
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

        // Enviar os dados para o repositório para criação do paciente
        pacienteRepository.criarPaciente(paciente, new PacienteRepository.PacienteCallback() {
            @Override
            public void onSuccess(String message) {
                // Exibe uma mensagem de sucesso
                Toast.makeText(PacientesCreateActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                // Exibe uma mensagem de erro
                Toast.makeText(PacientesCreateActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
