package com.example.sse;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sse.R;
import com.example.sse.Usuario;

import java.util.List;


public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.ViewHolder> {
    private LayoutInflater inflador;
    protected List<Usuario> listaUsuario;

    public ListaPersonasAdapter(ConsultaClientes consultaClientes, List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    public ListaPersonasAdapter(Context contexto) {
        inflador=(LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView documento,nombre,telefono;
        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            documento=(TextView) itemView.findViewById(R.id.recycler_id);
            nombre=(TextView) itemView.findViewById(R.id.recycler_nombre);
            telefono=(TextView) itemView.findViewById(R.id.recycler_telefono);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_personas, null, false);
        ViewHolder pvh = new ViewHolder(view);
        return pvh;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        final int position = i;

        viewHolder.documento.setText(listaUsuario.get(position).getId());
        viewHolder.nombre.setText(listaUsuario.get(position).getNombre());
        viewHolder.telefono.setText(listaUsuario.get(position).getTelefono());
        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), listaUsuario.get(position).nombre, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() { return listaUsuario.size(); }
}
