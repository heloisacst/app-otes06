package appmedico.com.appotes06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import adapter.MedicoAdapter;
import adapter.PacienteAdapter;
import data.MedicoRepository;
import data.PacienteRepository;
import model.Medico;
import model.Paciente;

public class PacientesActivity extends AppCompatActivity {

    private ListView listViewPacientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pacientes);

        listViewPacientes = findViewById(R.id.listViewPacientes);

        // Carregar os pacientes ao abrir a Activity
        carregarPacientes();

        //configura button_cadastrar para navegar para activity de cadastro
        findViewById(R.id.button_cadastrar).setOnClickListener(view -> startActivity(new Intent(PacientesActivity.this, PacientesCreateActivity.class)));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para carregar pacientes
    private void carregarPacientes() {
        PacienteRepository pacienteRepository = new PacienteRepository();
        pacienteRepository.listarPacientes(new PacienteRepository.PacientesCallback(){
            @Override
            public void onSuccess(List<Paciente> pacientes) {
                // Criando o adapter e configurando no ListView
                PacienteAdapter adapter = new PacienteAdapter(PacientesActivity.this, pacientes);
                listViewPacientes.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                // Exibe mensagem de erro
                Toast.makeText(PacientesActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}