// ConsultasActivity.java
package appmedico.com.appotes06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import data.ListarConsultaRepository;
import model.Agenda;
import adapter.ConsultaAdapter;


public class ConsultasActivity extends AppCompatActivity {

    private ListView listConsultas;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        listConsultas = findViewById(R.id.listConsulta);

        //configura button_cadastrar para navegar para activity de cadastro
        findViewById(R.id.button_cadastrar).setOnClickListener(view -> startActivity(new Intent(ConsultasActivity.this, ConsultasCreateActivity.class)));


        carregarConsultas(); // Carregar as consultas ao abrir a activity
    }

    // Método para carregar as consultas
    private void carregarConsultas() {
        ListarConsultaRepository listarConsultaRepository = new ListarConsultaRepository();
        listarConsultaRepository.listarConsultas(new ListarConsultaRepository.AgendamentoCallback() {
            @Override
            public void onSuccess(List<Agenda> agendas) {
                // Usar o ConsultaAdapter personalizado
                ConsultaAdapter adapter = new ConsultaAdapter(ConsultasActivity.this, agendas);
                listConsultas.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(ConsultasActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

}
