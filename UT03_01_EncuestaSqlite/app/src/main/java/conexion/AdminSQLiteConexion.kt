package conexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteConexion (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "miBaseDeDatos.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Aquí creamos la tabla.
        val CREATE_TABLE_ENCUESTA = """
            CREATE TABLE Encuesta (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                SO TEXT,
                especialidad TEXT,  -- Guardaremos las especialidades como un solo String separado por comas
                horasEstudio INTEGER
            )
        """
        db?.execSQL(CREATE_TABLE_ENCUESTA)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Aquí se manejarían las actualizaciones de la base de datos si es necesario
        val DROP_TABLE_ENCUESTA = "DROP TABLE IF EXISTS Encuesta"
        db?.execSQL(DROP_TABLE_ENCUESTA)
        onCreate(db)
    }
}