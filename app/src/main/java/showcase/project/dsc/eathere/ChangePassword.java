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

public class ChangePassword extends AppCompatDialogFragment {
    private EditText etPassword;
    private ChangePasswordDialog listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_change_password, null);

        etPassword = view.findViewById(R.id.et_change_password);

        builder.setView(view).setTitle("Change Password").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newPassword = etPassword.getText().toString();
                listener.applyNewPassword(newPassword);
            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (ChangePasswordDialog) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }

    }

    public interface ChangePasswordDialog{
        void applyNewPassword(String newPassword);
    }

}
