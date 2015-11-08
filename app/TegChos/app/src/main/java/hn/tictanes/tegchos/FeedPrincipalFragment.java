package hn.tictanes.tegchos;

/**
 * Created by jepz on 07-12-08.
 */

import android.content.Intent;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedPrincipalFragment extends Fragment {
     static final String url = "http://hacktegus.cloudapi.junar.com/datastreams/invoke/TECHO-CONST-EN-COMAY-60640?auth_key=a5dae587b1d7e9d48bd186ddca254ddfbc70058b#sthash.7olvli0Y.dpuf";
    FeedPrincipalAdapter mFeedPrincipalAdapter ;
    MatrixCursor matrixcursor;
    ListView listView;
    public FeedPrincipalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview_feedPrincipal);
        String[] columnas = new String[] {
                "_id", "Nombre","Colonia","FechaConstruccion", "Direccion",
                "Metros", "Beneficiarios", "LaminasDonadas"
        };
        matrixcursor = new MatrixCursor(columnas);
        getActivity().startManagingCursor(matrixcursor);
        matrixcursor.addRow(new Object[]{
                "1", "LEYSI GABRIELA SIERRA CANIZALEZ", "LOMA LAS MINITAS", "09/01/15", "CONTIGUO A LA PUL. DON RODOLFO", "23.5", "5", "14 LAMINAS DE ZINC"
        });
        matrixcursor.addRow(new Object[]{
                "2","REINA ISABEL PAVON PAVON","LA SEMPE","19/01/15","TERCERA CALLE","30","8","22 LAMINAS DE ZINC"
        });
        getActivity().stopManagingCursor(matrixcursor);

        mFeedPrincipalAdapter = new FeedPrincipalAdapter(getActivity(),matrixcursor,0);

        listView.setAdapter(mFeedPrincipalAdapter);
        Button btnAgregarSolicitud = (Button) (rootView.findViewById(R.id.btnAgregarSolicitud));
        btnAgregarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IngresoSolicitud.class);
                startActivity(intent);
            }
        });
        new GetTechosConstruidos().execute();
        return rootView;
    }

    private class GetTechosConstruidos extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServiceHandler sh = new ServiceHandler();

            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ",">" + jsonStr);
            String[] columnas = new String[] {
                    "_id", "Nombre","Colonia","FechaConstruccion", "Direccion",
                    "Metros", "Beneficiarios", "LaminasDonadas"
            };
            matrixcursor = new MatrixCursor(columnas);
            int counter = 0;
            String strNo = "";
            String strNombre = "";
            String strColonia = "";
            String strFechaConstruccion = "";
            String strDireccion = "";
            String strMetros = "";
            String strBeneficiarios = "";
            String strDonLaminas = "";
            if (jsonStr != null) {
                try {
                    JSONObject todo = new JSONObject(jsonStr);
                    JSONObject result = todo.getJSONObject("result");
                    Log.d("result: ",">" + result.toString());
                    JSONArray jaTechos = result.getJSONArray("fArray");
                    Log.d("jaTechos: ",">" + jaTechos.toString());
                    for (int i = 9; i < jaTechos.length(); i++) {
                        JSONObject c = jaTechos.getJSONObject(i);
                        Log.d("C: ",">" + c.toString() + "counter: " + counter );
                        switch (counter) {
                            case 0:
                                strNo = c.getString("fStr");
                                counter++;
                                break;
                            case 1:
                                strNombre = c.getString("fStr");
                                counter++;
                                break;
                            case 2:
                                strColonia = c.getString("fStr");
                                counter++;
                                break;
                            case 3:
                                strFechaConstruccion = c.getString("fNum");
                                counter++;
                                break;
                            case 4:
                                strDireccion = c.getString("fStr");
                                counter++;
                                break;
                            case 5:
                                strMetros = c.getString("fStr");
                                counter++;
                                break;
                            case 6:
                                strBeneficiarios= c.getString("fNum");
                                counter++;
                                break;
                            case 7:
                                strDonLaminas= c.getString("fStr");
                                counter++;
                                break;
                            case 8:
                                counter = 0;
                                matrixcursor.addRow(new Object[]{
                                        strNo, strNombre, strColonia, strFechaConstruccion, strDireccion, strMetros, strBeneficiarios, strDonLaminas
                                });
                                break;
                            default:
                                break;
                        }
                        getActivity().startManagingCursor(matrixcursor);
                    }
                    getActivity().stopManagingCursor(matrixcursor);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mFeedPrincipalAdapter = new FeedPrincipalAdapter(getActivity(),matrixcursor,0);

            listView.setAdapter(mFeedPrincipalAdapter);

        }
    }
}

