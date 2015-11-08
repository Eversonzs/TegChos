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

import java.text.SimpleDateFormat;
import java.util.Date;

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
            int id = 1;
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
                    JSONArray jaTechos = result.getJSONArray("fArray");
                    for (int i = 9; i < jaTechos.length(); i++) {
                        JSONObject c = jaTechos.getJSONObject(i);
                        switch (counter) {
                            case 0:
//                                strNo = c.getString("fStr");
                                strNo = Integer.toString(id);
                                counter++;
                                break;
                            case 1:
                                strNombre = UppercaseFirstLetters(c.getString("fStr").toLowerCase());
                                counter++;
                                break;
                            case 2:
                                strColonia = UppercaseFirstLetters(c.getString("fStr").toLowerCase());
                                counter++;
                                break;
                            case 3:
                                if (c.has("fNum")) {
                                    Long lgFechaConstruccion = c.getLong("fNum");
                                    strFechaConstruccion = new SimpleDateFormat("dd/MM/yyyy").format(new Date(lgFechaConstruccion));

                                } else {
                                    strFechaConstruccion = c.getString("fStr");
                                }

                                counter++;
                                break;
                            case 4:
                                strDireccion = UppercaseFirstLetters(c.getString("fStr").toLowerCase());;
                                counter++;
                                break;
                            case 5:
                                strMetros = c.getString("fStr");
                                counter++;
                                break;
                            case 6:
                                if (c.has("fNum")) {
                                    int intBeneficiarios= c.getInt("fNum");
                                    strBeneficiarios = Integer.toString(intBeneficiarios);
                                } else {
                                    strBeneficiarios= c.getString("fStr");
                                }
                                counter++;
                                break;
                            case 7:
                                strDonLaminas= c.getString("fStr");
                                String arr[] = strDonLaminas.split(" ",2);
                                strDonLaminas = arr[0];
                                counter++;
                                break;
                            case 8:
                                counter = 0;
                                id++;
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
    public static String UppercaseFirstLetters(String str)
    {
        boolean prevWasWhiteSp = true;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                if (prevWasWhiteSp) {
                    chars[i] = Character.toUpperCase(chars[i]);
                }
                prevWasWhiteSp = false;
            } else {
                prevWasWhiteSp = Character.isWhitespace(chars[i]);
            }
        }
        return new String(chars);
    }
}

