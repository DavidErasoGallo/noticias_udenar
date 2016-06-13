package com.example.root.noticias_Udenar;



import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.root.noticias_Udenar.RssParse.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by root on 20/05/16.
 */
public class NoticiaAdapter extends BaseAdapter {


    Fragment activity;
    List<Item> noticias;

    public NoticiaAdapter(Fragment activity, List<Item> noticias) {
        this.activity = activity;
        this.noticias = noticias;
    }

    @Override
    public int getCount() {
        return noticias.size();
    }

    @Override
    public Object getItem(int i) {
        return noticias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null)
            v = View.inflate(activity.getContext(), R.layout.item_layout, null);

        Item n = noticias.get(i);

        TextView titulo = (TextView) v.findViewById(R.id.titulo);
        TextView descripcion = (TextView) v.findViewById(R.id.descripcion);
        //TextView img = (TextView) v.findViewById(R.id.imagen);
        ImageView foto = (ImageView) v.findViewById(R.id.foto);



        titulo.setText(n.getTitle().trim().toUpperCase());

        descripcion.setText(n.getDescripcion().trim());


        String ALLOWED_URI_CHARS = "@#&=*+-ñ_.,:!?()/~'%";
        String aux = Uri.encode(n.getImg(), ALLOWED_URI_CHARS);
        Picasso.with(v.getContext()).load(aux).error(R.drawable.udenar).into(foto);

        return v;
    }
}
