package appmedico.com.appotes06;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import data.AgendarConsultaRepository;
import data.MedicoRepository;
import data.PacienteRepository;
import model.Agenda;
import model.Medico;
import model.Paciente;

public class ConsultasCreateActivity extends AppCompatActivity {

    // Referências para a interface
    private Spinner namePacienteView;  // Spinner para exibir os pacientes
    private Spinner nameMedicoView;    // Spinner para exibir os médicos
    private TextView dateTextView;     // TextView para exibir a data selecionada
    private TextView timeTextView;     // TextView para exibir a hora selecionada

    // Listas que armazenam os dados de pacientes e médicos
    private List<Paciente> pacientesList = new ArrayList<>(); // Lista de pacientes
    private List<Medico> medicosList = new ArrayList<>();     // Lista de médicos

    // Repositórios para acessar dados de pacientes, médicos e agendamentos
    private PacienteRepository pacienteRepository;
    private MedicoRepository medicoRepository;
    private AgendarConsultaRepository agendarConsultaRepository; // Repositório para agendamento de consultas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_create);

        // Inicializando as views
        namePacienteView = findViewById(R.id.namePacienteView);
        nameMedicoView = findViewById(R.id.nameMedicoView);
        dateTextView = findViewById(R.id.dateTextView);
        timeTextView = findViewById(R.id.timeTextView);

        // Inicializando os repositórios de dados
        pacienteRepository = new PacienteRepository();
        medicoRepository = new MedicoRepository();
        agendarConsultaRepository = new AgendarConsultaRepository(); // Inicializando o repositório de agendamentos

        // Carregar a lista de pacientes e médicos da API
        carregarPacientes();
        carregaMedicos();

        // Configura o DatePicker quando o usuário clicar na TextView de data
        dateTextView.setOnClickListener(v -> openDatePicker());

        // Configura o TimePicker quando o usuário clicar na TextView de hora
        timeTextView.setOnClickListener(v -> openTimePicker());

        // Configura o botão de Agendar para chamar o método agendarConsulta
        findViewById(R.id.btnAgendar).setOnClickListener(v -> agendarConsulta());

        // Configura o botão de Cancelar para voltar para a MainActivity
        findViewById(R.id.btnCancelar).setOnClickListener(v -> cancelarConsulta());
    }

    // Carregar os pacientes da API usando o repositório
    private void carregarPacientes() {
        pacienteRepository.listarPacientes(new PacienteRepository.PacientesCallback() {
            @Override
            public void onSuccess(List<Paciente> pacientes) {
                pacientesList = pacientes; // Armazenando a lista de pacientes retornada
                prepareSpinnerPaciente(); // Preparando o Spinner com a lista de pacientes
            }

            @Override
            public void onError(String error) {
                // Exibe uma mensagem de erro caso haja falha ao carregar os pacientes
                Toast.makeText(ConsultasCreateActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Carregar os médicos da API usando o repositório
    private void carregaMedicos() {
        medicoRepository.listarMedicos(new MedicoRepository.MedicosCallback() {
            @Override
            public void onSucess(List<Medico> medicos) {
                medicosList = medicos; // Armazenando a lista de médicos retornada
                prepareSpinnerMedico(); // Preparando o Spinner com a lista de médicos
            }

            @Override
            public void onError(String error) {
                // Exibe uma mensagem de erro caso haja falha ao carregar os médicos
                Toast.makeText(ConsultasCreateActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Prepara o Spinner para exibir a lista de pacientes
    private void prepareSpinnerPaciente() {
        List<String> nomesPacientes = new ArrayList<>();
        for (Paciente paciente : pacientesList) {
            nomesPacientes.add(paciente.getPacte_nome()); // Adiciona o nome do paciente à lista
        }

        // Cria o adaptador e configura o Spinner com os nomes dos pacientes
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nomesPacientes);
        namePacienteView.setAdapter(adapter);
        namePacienteView.setPopupBackgroundResource(android.R.color.white); // Define o fundo branco para o Spinner
    }

    // Prepara o Spinner para exibir a lista de médicos
    private void prepareSpinnerMedico() {
        List<String> nomesMedicos = new ArrayList<>();
        for (Medico medico : medicosList) {
            nomesMedicos.add(medico.getNome()); // Adiciona o nome do médico à lista
        }

        // Cria o adaptador e configura o Spinner com os nomes dos médicos
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nomesMedicos);
        nameMedicoView.setAdapter(adapter);
        nameMedicoView.setPopupBackgroundResource(android.R.color.white); // Define o fundo branco para o Spinner
    }

    // Abre o DatePickerDialog para selecionar a data
    private void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Cria o DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    dateTextView.setText(selectedDate);  // Exibe a data selecionada na TextView
                },
                year, month, day);  // Passa os valores atuais de ano, mês e dia

        datePickerDialog.show();  // Exibe o DatePicker
    }

    // Abre o TimePickerDialog para selecionar a hora
    private void openTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Cria o TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, selectedHour, selectedMinute) -> {
                    // Formata a hora como HH:mm
                    String selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                    timeTextView.setText(selectedTime);  // Exibe a hora selecionada na TextView
                },
                hour, minute, true);  // true para utilizar o formato de 24 horas

        timePickerDialog.show();  // Exibe o TimePicker
    }

    private void agendarConsulta() {
        int idPaciente = getPacienteId();  // Obtém o ID do paciente selecionado
        int idMedico = getMedicoId();  // Obtém o ID do médico selecionado

        // Cria a string com data e hora no formato exibido
        String dataHoraConsultaString = dateTextView.getText().toString() + " " + timeTextView.getText().toString();

        // Log para verificar a string antes de enviar
        Log.d("DataHoraConsulta", "Data e Hora: " + dataHoraConsultaString);

        // Usando SimpleDateFormat para converter a string para um objeto Date
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // Formato original
        Date dataHoraConsulta = null;

        try {
            // Tenta converter a string para o formato Date
            dataHoraConsulta = format.parse(dataHoraConsultaString);  // Converte para Date
        } catch (ParseException e) {
            e.printStackTrace();  // Exibe erro caso o formato da data seja inválido
            Toast.makeText(this, "Erro ao converter a data e hora", Toast.LENGTH_SHORT).show();
            return;
        }

        // Converte para String no formato ISO 8601, que é o formato esperado pela API
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dataHoraConsultaISO = isoFormat.format(dataHoraConsulta);

        // Log para verificar o formato da data antes de enviar
        Log.d("DataHoraConsultaISO", "Data e Hora formatada para API: " + dataHoraConsultaISO);

        // Cria o objeto Agenda com a data no formato ISO 8601
        Agenda agenda = new Agenda(idPaciente, idMedico, dataHoraConsultaISO);

        // Chama o repositório para agendar a consulta
        agendarConsultaRepository.agendarConsulta(agenda, new AgendarConsultaRepository.AgendamentoCallback() {
            @Override
            public void onSuccess(String message) {
                // Exibe a mensagem de sucesso
                Toast.makeText(ConsultasCreateActivity.this, message, Toast.LENGTH_SHORT).show();

                // Após o sucesso, navega para a ConsultasActivity
                Intent intent = new Intent(ConsultasCreateActivity.this, ConsultasActivity.class);
                startActivity(intent);  // Inicia a ConsultasActivity
                finish();  // Finaliza a ConsultasCreateActivity
            }

            @Override
            public void onError(String errorMessage) {
                // Exibe mensagem de erro caso o agendamento falhe
                Toast.makeText(ConsultasCreateActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Obtém o ID do paciente selecionado no Spinner
    private int getPacienteId() {
        Paciente pacienteSelecionado = pacientesList.get(namePacienteView.getSelectedItemPosition());
        return pacienteSelecionado.getId();  // Retorna o ID do paciente
    }

    // Obtém o ID do médico selecionado no Spinner
    private int getMedicoId() {
        Medico medicoSelecionado = medicosList.get(nameMedicoView.getSelectedItemPosition());
        return medicoSelecionado.getId();  // Retorna o ID do médico
    }

    // Método para cancelar o agendamento e voltar para a MainActivity
    private void cancelarConsulta() {
        // Cria uma Intent para voltar à MainActivity
        Intent intent = new Intent(ConsultasCreateActivity.this, MainActivity.class);
        startActivity(intent);  // Inicia a MainActivity
        finish();  // Finaliza a ConsultasCreateActivity
    }
}
