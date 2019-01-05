package com.example.yoshknight.androidavanzado_educacionit.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yoshknight.androidavanzado_educacionit.R;
import com.example.yoshknight.androidavanzado_educacionit.modelos.Usuario;

import java.util.List;

public class UsuariosRecyclerAdapter extends RecyclerView.Adapter<UsuariosRecyclerAdapter.UsuarioHolder> {

    private final List<Usuario> usuariosList;

    public UsuariosRecyclerAdapter(List<Usuario> usuarios){
        this.usuariosList = usuarios;
    }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item,viewGroup,false);
        return new UsuarioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder usuarioHolder, int position) {
        usuarioHolder.tvName.setText(usuariosList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return usuariosList.size();
    }

    class UsuarioHolder extends RecyclerView.ViewHolder{

        private final TextView tvName;

        public UsuarioHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);

        }
    }
}
