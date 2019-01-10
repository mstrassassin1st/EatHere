package showcase.project.dsc.eathere;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeEmail extends AppCompatDialogFragment {
    private EditText etEmail;
    private TextView tvEmail;
    private EmailDialogListener listener;
    private DataPassing dataPassing = DataPassing.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.change_email, null);

        tvEmail.setText(dataPassing.getEmail());

        builder.setView(view).setTitle("Change Email").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String username = etEmail.getText().toString();
                listener.applyText(username);
            }
        });

        etEmail = view.findViewById(R.id.et_change_email);
        tvEmail = view.findViewById(R.id.tv_show_email);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (EmailDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement EmailDialogListener");
        }

    }

    public interface EmailDialogListener{
        void applyText(String email);
    }
}
