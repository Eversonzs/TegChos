package hn.tictanes.tegchos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class IngresoSolicitudFragment extends Fragment {

    public IngresoSolicitudFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingreso_solicitud, container, false);

        Button btnEnviar = (Button) (rootView.findViewById(R.id.btnEnviarSolicitud));
        ImageButton imgButton = (ImageButton) (rootView.findViewById(R.id.imgButton));

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Seleccione una opcion...")
                        .setPositiveButton("Foto", dialogClickListener)
                        .setNegativeButton("Galeria", dialogClickListener)
                        .setTitle("Cargar foto")
                        .show();
            }
        });



        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Hola", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
