package hn.tictanes.tegchos;

/**
 * Created by jepz on 07-12-08.
 */

import android.content.Intent;
import android.database.MatrixCursor;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedPrincipalFragment extends Fragment {

    FeedPrincipalAdapter mFeedPrincipalAdapter ;
    public FeedPrincipalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_feedPrincipal);
        String[] columnas = new String[] {
                "_id", "Nombre","Colonia","FechaConstruccion", "Direccion",
                "Metros", "Beneficiarios", "LaminasDonadas"
        };
        MatrixCursor matrixcursor = new MatrixCursor(columnas);
        getActivity().startManagingCursor(matrixcursor);
        matrixcursor.addRow(new Object[]{
                "1", "LEYSI GABRIELA SIERRA CANIZALEZ", "LOMA LAS MINITAS", "09/01/15", "CONTIGUO A LA PUL. DON RODOLFO", "23.5", "5", "14 LAMINAS DE ZINC"
        });
        matrixcursor.addRow(new Object[]{
                "2","REINA ISABEL PAVON PAVON","LA SEMPE","19/01/15","TERCERA CALLE","30","8","22 LAMINAS DE ZINC"
        });
        matrixcursor.addRow(new Object[]{
                "3","REINA ISABEL PAVON PAVON","LA SEMPE","19/01/15","TERCERA CALLE","30","8","22 LAMINAS DE ZINC"
        });matrixcursor.addRow(new Object[]{
                "4","REINA ISABEL PAVON PAVON","LA SEMPE","19/01/15","TERCERA CALLE","30","8","22 LAMINAS DE ZINC"
        });matrixcursor.addRow(new Object[]{
                "5","REINA ISABEL PAVON PAVON","LA SEMPE","19/01/15","TERCERA CALLE","30","8","22 LAMINAS DE ZINC"
        });matrixcursor.addRow(new Object[]{
                "6","REINA ISABEL PAVON PAVON","LA SEMPE","19/01/15","TERCERA CALLE","30","8","22 LAMINAS DE ZINC"
        });matrixcursor.addRow(new Object[]{
                "7","REINA ISABEL PAVON PAVON","LA SEMPE","19/01/15","TERCERA CALLE","30","8","22 LAMINAS DE ZINC"
        });matrixcursor.addRow(new Object[]{
                "8","REINA ISABEL PAVON PAVON","LA SEMPE","19/01/15","TERCERA CALLE","30","8","22 LAMINAS DE ZINC"
        });matrixcursor.addRow(new Object[]{
                "9","REINA ISABEL PAVON PAVON","LA SEMPE","19/01/15","TERCERA CALLE","30","8","22 LAMINAS DE ZINC"
        });
        getActivity().stopManagingCursor(matrixcursor);

        getActivity().stopManagingCursor(matrixcursor);
        mFeedPrincipalAdapter = new FeedPrincipalAdapter(getActivity(),matrixcursor,0);

        listView.setAdapter(mFeedPrincipalAdapter);
        Button btnAgregarSolicitud = (Button) (rootView.findViewById(R.id.btnAgregarSolicitud));

        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int size = getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height_material);
                outline.setOval(0,0,size,size);
            }
        };
        btnAgregarSolicitud.setOutlineProvider(viewOutlineProvider);
        btnAgregarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IngresoSolicitud.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
