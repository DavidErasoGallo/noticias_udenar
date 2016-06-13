package com.example.root.noticias_Udenar;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;


import com.example.root.noticias_Udenar.RssParse.Item;
import android.os.AsyncTask;
import android.widget.Toast;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.root.noticias_Udenar.RssParse.Rss;
import com.example.root.noticias_Udenar.Web.VolleySingleton;
import com.example.root.noticias_Udenar.Web.XmlRequest;

import java.util.ArrayList;
import java.util.List;


public class Fragment1 extends Fragment  {




    /*
    URL del feed
     */
    public static final String URL_FEED = "http://ccomunicaciones.udenar.edu.co/?feed=rss2";


    private ListView listView;
    private ProgressBar progressBar;
    List<Item> noticias;


    private NoticiaAdapter adapter;

    public static Fragment1 newInstance(){
        Fragment1 fragment = new Fragment1();
        return fragment;
    }

    public Fragment1() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment1,container,false);



        noticias = new ArrayList<>();
        // Obtener la lista
        listView = (ListView) v.findViewById(R.id.lista);
        progressBar = (ProgressBar) v.findViewById(R.id.bar);
        adapter = new NoticiaAdapter(this, noticias);
        listView.setAdapter(adapter);




        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            VolleySingleton.getInstance(getContext()).addToRequestQueue(
                    new XmlRequest<>(
                            URL_FEED,
                            Rss.class,
                            null,
                            new Response.Listener<Rss>() {
                                @Override
                                public void onResponse(Rss response) {

                                    // Carga inicial de datos...
                                    progressBar.setVisibility(View.VISIBLE);
                                    new LoadData().execute(response.getChannel().getItems());
                                    progressBar.setVisibility(View.GONE);
                                }


                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getContext(), "Error En conexión a internet", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                    Item item = new Item("Error En conexión a internet","Revisa tu conexión a Internet y presiona el boton actualizar", "http://www.udenar.edu.co/", "Ninguna");
                                    noticias.add(item);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                    )
            );
        } else {
            Toast.makeText(getContext(), "Error En conexión a internet", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
            Item item = new Item("Error En conexión a internet","Conectate a Internet y presiona el boton actualizar", "http://www.udenar.edu.co/", "Ninguna");
            noticias.add(item);
            adapter.notifyDataSetChanged();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //escuchador al listview que al hacer click en cada item
            //muestra la noticia en un segundo Activity
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Item item = (Item) adapter.getItem(position);

                // Obtene url de la entrada seleccionada
                String url = item.getLink();

                // Nuevo intent explicito
                Intent i = new Intent(getContext(), DetailActivity.class);


                // Setear url
                i.putExtra("url-extra", url);

                // Iniciar actividad
                startActivity(i);
            }
        });




            return v;

    }




    public class LoadData extends AsyncTask<List<Item>, Void, Item> {

        //Clase en segundo plano que agrega las noticias
        //a un ArrayList de noticias y actualiza
        //el adaptador


        @Override
        protected void onPostExecute(Item i) {

            adapter.notifyDataSetChanged();

        }


        @Override
        protected Item doInBackground(List<Item>... lists) {


            for (Item e : lists[0]) {

                noticias.add(e);

            }

            return null;
        }
    }



}

