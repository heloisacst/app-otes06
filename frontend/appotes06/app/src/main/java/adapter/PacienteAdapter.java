package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.List;

import appmedico.com.appotes06.R;
import model.Paciente;  // Modelo Paciente

public class PacienteAdapter extends ArrayAdapter<Paciente> {

    private Context context;
    private List<Paciente> pacientes;

    // Construtor do adapter
    public PacienteAdapter(Context context, List<Paciente> pacientes) {
        super(context, 0, pacientes);
        this.context = context;
        this.pacientes = pacientes;
    }

    // Método que infla o layout e popula os dados
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Verifica se a view já foi inflada, se não, infla a view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_paciente, parent, false);
        }

        // Obtém o paciente na posição desejada
        Paciente paciente = getItem(position);

        // Verifica se o paciente não é nulo
        if (paciente != null) {
            // Referência dos TextViews
            TextView textViewNomePaciente = convertView.findViewById(R.id.textViewNomePaciente);
            TextView textViewEmailPaciente = convertView.findViewById(R.id.textViewEmailPaciente);

            // Preenche os campos com os dados do paciente
            textViewNomePaciente.setText(paciente.getPacte_nome());
            textViewEmailPaciente.setText(paciente.getPacte_email());
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
