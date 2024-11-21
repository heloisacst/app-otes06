package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import appmedico.com.appotes06.R;
import model.Agenda;

public class ConsultaAdapter extends ArrayAdapter<Agenda> {

    private Context context;
    private List<Agenda> agendas;

    // Construtor do adapter
    public ConsultaAdapter(Context context, List<Agenda> agendas) {
        super(context, 0, agendas);
        this.context = context;
        this.agendas = agendas;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Verifica se a view já foi inflada, se não, infla a view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_consulta, parent, false);
        }

        // Obtem a agenda na posição desejada
        Agenda agenda = getItem(position);

        // Verifica se a agenda não é nula
        if (agenda != null) {
            // Referência dos TextViews
            TextView textViewNomeMedico = convertView.findViewById(R.id.textViewNomeMedico);
            TextView textViewNomePaciente = convertView.findViewById(R.id.textViewNomePaciente);
            TextView textViewDataHoraConsulta = convertView.findViewById(R.id.textViewDataHoraConsulta);

            // Preenche os campos com os dados da Agenda
            textViewNomeMedico.setText(agenda.getNomeMedico());
            textViewNomePaciente.setText(agenda.getNomePaciente());

            // Verifica se a data de consulta não é nula
            if (agenda.getDataHoraConsulta() != null) {
                try {
                    // Converte a dataHoraConsulta de String para Date
                    SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date date = isoFormat.parse(agenda.getDataHoraConsulta());

                    // Formata a data para o formato desejado (dd/MM/yyyy HH:mm)
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    String dataHoraFormatada = dateFormat.format(date);

                    // Exibe a data formatada
                    textViewDataHoraConsulta.setText(dataHoraFormatada);
                } catch (ParseException e) {
                    e.printStackTrace();
                    textViewDataHoraConsulta.setText("Data inválida");
                }
            } else {
                // Caso a data seja nula, exibe uma mensagem alternativa
                textViewDataHoraConsulta.setText("Data e Hora não disponíveis");
            }
        }

        // Ícone de opções (que pode ser editado ou cancelado)
        ImageView imageViewOptions = convertView.findViewById(R.id.imageViewOptions);

        // Botões Editar e Cancelar
        LinearLayout llButtons = convertView.findViewById(R.id.llButtons);

        // Exibe ou oculta os botões Editar e Cancelar
        imageViewOptions.setOnClickListener(v -> {
            if (llButtons.getVisibility() == View.GONE) {
                llButtons.setVisibility(View.VISIBLE);
            } else {
                llButtons.setVisibility(View.GONE);
            }
        });


        // Retorna a view preenchida com os dados
        return convertView;
    }
}
