package com.example.losbrunchies

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class AdminSQLIteConexion(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        const val DATABASE_NAME = "UsuarioSQLite.db3"
        const val DATABASE_VERSION = 1
    }
    override fun onCreate(db: SQLiteDatabase) {
        Log.e("ACSCO","Paso por OnCreate del AdminSQLLite")
        db.execSQL("create table Usuarios(nick text primary key, contraseña text)")
        Log.d("Database Path", db.path)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.e("ACSCO","Paso por OnUpgrade del AdminSQLLite")
    }
}