package auxiliar

import android.content.ContentValues
import android.database.Cursor
import conexion.AdminSQLiteConexion
import modelo.Encuesta

object Conexion {

    // Método para insertar una encuesta
    fun insertarEncuesta(dbHelper: AdminSQLiteConexion, encuesta: Encuesta): Long {
        var resultado: Long = -1
        val db = dbHelper.writableDatabase

        try {
            db.beginTransaction()  // Iniciar una transacción

            val values = ContentValues().apply {
                put("nombre", encuesta.nombre)
                put("SO", encuesta.SO)
                put("especialidad", encuesta.especialidad.joinToString(","))
                put("horasEstudio", encuesta.horasEstudio)
            }

            // Insertamos el registro en la tabla Encuesta
            resultado = db.insert("Encuesta", null, values)

            // Confirmar la transacción
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()  // Captura cualquier error y lo muestra en el log
        } finally {
            db.endTransaction()  // Finaliza la transacción
            db.close()  // Cerramos la base de datos
        }

        return resultado
    }

    // Método para obtener todas las encuestas
    fun obtenerEncuestas(dbHelper: AdminSQLiteConexion): List<Encuesta> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM Encuesta", null)
        val listaEncuestas = mutableListOf<Encuesta>()

        try {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                    val SO = cursor.getString(cursor.getColumnIndexOrThrow("SO"))
                    val especialidad = cursor.getString(cursor.getColumnIndexOrThrow("especialidad"))
                        .split(",")  // Convertimos el String de especialidades de nuevo a lista
                    val horasEstudio = cursor.getInt(cursor.getColumnIndexOrThrow("horasEstudio"))

                    // Añadimos la encuesta a la lista
                    listaEncuestas.add(Encuesta(id, nombre, SO, especialidad, horasEstudio))
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            e.printStackTrace()  // Captura cualquier error
        } finally {
            cursor.close()  // Asegurarse de cerrar el cursor
            db.close()  // Cerramos la base de datos
        }

        return listaEncuestas
    }
}