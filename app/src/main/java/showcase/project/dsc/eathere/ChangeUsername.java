package showcase.project.dsc.eathere;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeUsername extends AppCompatDialogFragment {
    private EditText etUsername;
    private TextView tvUsername;
    private DataPassing dataPassing = DataPassing.getInstance();
    private ChangeUsernameDialog listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_change_username, null);

        etUsername = view.findViewById(R.id.et_change_username);
        tvUsername = view.findViewById(R.id.tv_show_change_username);
        tvUsername.setText(dataPassing.getUsername());

        builder.setView(view).setTitle("Change Username").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String newUsername = etUsername.getText().toString();
                listener.applyNewUsername(newUsername);

            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (ChangeUsernameDialog) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }

    }

    public interface ChangeUsernameDialog{
        void applyNewUsername(String newUsername);
    }

}
