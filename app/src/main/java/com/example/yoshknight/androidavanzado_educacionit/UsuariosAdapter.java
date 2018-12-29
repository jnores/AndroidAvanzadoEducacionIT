package com.example.yoshknight.androidavanzado_educacionit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yoshknight.androidavanzado_educacionit.modelos.Usuario;

import java.util.List;

public class UsuariosAdapter extends BaseAdapter {
    List<Usuario> users;

    public UsuariosAdapter(List<Usuario> users) {
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, null);
        } else {
            v = convertView;
        }

        TextView name = v.findViewById(R.id.name);
        name.setText(users.get(position).getName());

        return v;
    }
}
