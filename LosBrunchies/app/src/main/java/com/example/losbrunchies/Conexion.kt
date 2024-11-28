package com.example.losbrunchies

import com.example.losbrunchies.AdminSQLIteConexion
import com.example.losbrunchies.UsuarioSQLite
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity

object Conexion {
    private  var DATABASE_NAME = "UsuarioSQLite.db3"
    private  var DATABASE_VERSION = 1

    fun cambiarBD(nombreBD:String){
        this.DATABASE_NAME = nombreBD
    }
    fun addUsuario(contexto: AppCompatActivity, p: UsuarioSQLite):Long{
        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("nick", p.nick)
        registro.put("pass",p.pass)
        val codigo = bd.insert("Usuarios", null, registro)
        bd.close()
        return codigo
    }

    fun delUsuario(contexto: AppCompatActivity, nick: String):Int{
        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.writableDatabase
        val cant = bd.delete("Usuarios", "nick=?", arrayOf(nick.toString()))
        bd.close()
        return cant
    }

    fun modUsuario(contexto:AppCompatActivity, nick:String, p:UsuarioSQLite):Int {
        val admin = AdminSQLIteConexion(contexto, this.DATABASE_NAME, null, DATABASE_VERSION)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("nick", p.nick)
        registro.put("pass", p.pass)
        val cant = bd.update("Usuarios", registro, "nick=?", arrayOf(nick.toString()))
        bd.close()
        return cant
    }

}