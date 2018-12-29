package com.example.yoshknight.androidavanzado_educacionit.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.yoshknight.androidavanzado_educacionit.modelos.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void crearUsuarios(Usuario... usuario);

    // void insertAll(List<Usuario> usuarios);

    @Query("SELECT * FROM usuarios")
    List<Usuario> obtenerUsuarios();

}
