package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import model.Medico;
import appmedico.com.appotes06.R;

public class MedicoAdapter extends ArrayAdapter<Medico> {

    private Context context;
    private List<Medico> medicos;

    // Construtor
    public MedicoAdapter(Context context, List<Medico> medicos) {
        super(context, 0, medicos);
        this.context = context;
        this.medicos = medicos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Verifica se a view já foi inflada, se não, inflamos ela
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_medico, parent, false);
        }

        // Obtém o médico da lista
        Medico medico = getItem(position);

        // Referências para os TextViews do layout
        TextView textViewNomeMedico = convertView.findViewById(R.id.textViewNomeMedico);
        TextView textViewEspecialidade = convertView.findViewById(R.id.textViewEspecialidade);
        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
        TextView textViewCRM = convertView.findViewById(R.id.textViewCRM);

        // Preenche os campos com os dados do médico
        if (medico != null) {
            textViewNomeMedico.setText(medico.getNome());
            textViewEspecialidade.setText(medico.getEspecialidade());
            textViewEmail.setText(medico.getEmail());
            textViewCRM.setText(medico.getCrm());
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

        // Retorna a view preenchida
        return convertView;
    }
}
