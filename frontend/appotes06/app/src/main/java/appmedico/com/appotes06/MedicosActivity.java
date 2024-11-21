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
import data.MedicoRepository;
import model.Medico;

public class MedicosActivity extends AppCompatActivity {

    private ListView listViewMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medicos);

        // Inicializando o ListView
        listViewMedicos = findViewById(R.id.listViewMedicos);

        // Carregar os médicos ao abrir a Activity
        carregarMedicos();

        //configura button_cadastrar para navegar para activity de cadastro
        findViewById(R.id.button_cadastrar).setOnClickListener(view -> startActivity(new Intent(MedicosActivity.this, MedicosCreateActivity.class)));

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    });

    }

    // Método para carregar medicos
    private void carregarMedicos() {
        MedicoRepository medicoRepository = new MedicoRepository();
        medicoRepository.listarMedicos(new MedicoRepository.MedicosCallback() {
            @Override
            public void onSucess(List<Medico> medicos) {
                // Criando o adapter e configurando no ListView
                MedicoAdapter adapter = new MedicoAdapter(MedicosActivity.this, medicos);
                listViewMedicos.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                // Exibe mensagem de erro
                Toast.makeText(MedicosActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}