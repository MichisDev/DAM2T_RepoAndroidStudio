package com.example.losbrunchies

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class AdminSQLIteConexion(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        Log.e("MICHEL","Paso por OnCreate del AdminSQLLite")
        db.execSQL("create table Usuarios(nick text primary key, contraseña text)")
        Log.d("Database Path", db.path)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.e("MICHEL","Paso por OnUpgrade del AdminSQLLite")
    }
}