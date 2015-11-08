package hn.tictanes.tegchos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jepz on 07-13-08.
 */
public class FeedPrincipalAdapter extends android.support.v4.widget.CursorAdapter  {

    public FeedPrincipalAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listview_item_feed_prinicipal, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String[] columnas = new String[]{
                "No", "Nombre", "Colonia", "FechaConstruccion", "Direccion",
                "Metros", "Beneficiarios", "LaminasDonadas"
        };

        String strNo = cursor.getString(0);
        String strNombre = cursor.getString(1);
        String strColonia = cursor.getString(2);
        String strFechaConstruccion = cursor.getString(3);
        String strDireccion = cursor.getString(4);
        String strMetros = cursor.getString(5);
        String strBeneficiarios = cursor.getString(6);
        String strLaminadasDonadas = cursor.getString(7);
        TextView tvId = (TextView) (view.findViewById(R.id.tvId));
        TextView tvNombre = (TextView) (view.findViewById(R.id.tvNombre));
        TextView tvColonia = (TextView) (view.findViewById(R.id.tvColonia));
        TextView tvFechaConstruccion = (TextView) (view.findViewById(R.id.tvFechaConstruccion));
        TextView tvDireccion = (TextView) (view.findViewById(R.id.tvDireccion));
        TextView tvMetros = (TextView) (view.findViewById(R.id.tvMetros));
        TextView tvBeneficiarios = (TextView) (view.findViewById(R.id.tvBeneficiarios));
        TextView tvDonacionLaminas = (TextView) (view.findViewById(R.id.tvDonacionLaminas));

        tvId.setText(strNo);
        tvNombre.setText(strNombre);
        tvColonia.setText(strColonia);
        tvFechaConstruccion.setText(strFechaConstruccion);
        tvDireccion.setText(strDireccion);
        tvMetros.setText(strMetros);
        tvBeneficiarios.setText(strBeneficiarios);
        tvDonacionLaminas.setText(strLaminadasDonadas);
    }
}
