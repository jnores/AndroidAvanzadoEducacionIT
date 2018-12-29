package com.example.yoshknight.androidavanzado_educacionit.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.yoshknight.androidavanzado_educacionit.modelos.Usuario;

@Database(entities = {Usuario.class}, version = 1)
public abstract class UsuariosDatabase extends RoomDatabase {
    public abstract UsuarioDao getDao();
    private static UsuariosDatabase sInstance;

    public static UsuariosDatabase getDatabaseReference(Context context){
        if (sInstance == null){
            sInstance = Room
                    .databaseBuilder(context,UsuariosDatabase.class,"usuarios")
                    .build();
        }
        return sInstance;
    }
}
