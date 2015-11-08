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

        int iNo = cursor.getInt(0);
        String strNombre = cursor.getString(1);
        String strColonia = cursor.getString(2);
        String strFechaConstruccion = cursor.getString(3);
        String strDireccion = cursor.getString(4);
        String strMetros = cursor.getString(5);
        String strBeneficiarios = cursor.getString(6);
        String strLaminadasDonadas = cursor.getString(7);
        TextView tvTodoTexto = (TextView) (view.findViewById(R.id.tvtodotexto));
        String strTodoPegado = iNo + " " + strNombre  + " " + strColonia  + " " + strFechaConstruccion
                + " " + strDireccion
                + " " + strMetros   + " " + strBeneficiarios   + " " + strLaminadasDonadas;
                tvTodoTexto.setText(strTodoPegado);

    }
}
