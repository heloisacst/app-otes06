package appmedico.com.appotes06;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import api.ApiService;
import model.Paciente;
import model.PaginaDePacientesDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import api.RetrofitClient;


public class ConsultasCreateActivity extends AppCompatActivity {

    private EditText dataConsultaView, horarioConsultaView;
    private AutoCompleteTextView namePacienteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_create);

        componentes();
        fetchPacientesFromApi();

        // Adiciona o listener para o campo de data
        dataConsultaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria uma instância de java.util.Calendar (não depende do ICU)
                Calendar calendar = Calendar.getInstance();  // Usa java.util.Calendar
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Cria o DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(ConsultasCreateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Formata a data escolhida no formato desejado (ex: dd/MM/yyyy)
                                calendar.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                dataConsultaView.setText(sdf.format(calendar.getTime())); // Preenche o EditText com a data escolhida
                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();
            }
        });


        // Adiciona o listener para o campo de horário
        horarioConsultaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria uma instância de Calendar (para selecionar a hora)
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                // Cria o TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(ConsultasCreateActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Formata o horário e preenche o campo
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                                horarioConsultaView.setText(sdf.format(calendar.getTime()));
                            }
                        }, hour, minute, true); // `true` para 24 horas

                timePickerDialog.show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    private void fetchPacientesFromApi() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        Call<PaginaDePacientesDTO> call = apiService.getPacientes();

        call.enqueue(new Callback<PaginaDePacientesDTO>() {
            @Override
            public void onResponse(Call<PaginaDePacientesDTO> call, Response<PaginaDePacientesDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PaginaDePacientesDTO paginaDePacientesDTO = response.body();
                    List<Paciente> pacientes = paginaDePacientesDTO.getPacientes();

                    // Extrai os nomes dos pacientes para preencher o AutoCompleteTextView
                    String[] nomesPacientes = new String[pacientes.size()];
                    for (int i = 0; i < pacientes.size(); i++) {
                        nomesPacientes[i] = pacientes.get(i).getPacte_nome();
                    }

                    // Cria um ArrayAdapter para fornecer os nomes dos pacientes
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ConsultasCreateActivity.this, android.R.layout.simple_dropdown_item_1line, nomesPacientes);

                    // Define o adaptador no AutoCompleteTextView
                    namePacienteView.setAdapter(adapter);
                } else {
                    Toast.makeText(ConsultasCreateActivity.this, "Erro ao carregar pacientes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaginaDePacientesDTO> call, Throwable t) {
                Toast.makeText(ConsultasCreateActivity.this, "Falha na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void componentes() {
        dataConsultaView = findViewById(R.id.dataConsultaView);
        horarioConsultaView = findViewById(R.id.horarioConsultaView);
        //namePacienteView = findViewById(R.id.namePacienteView);

    }
}
