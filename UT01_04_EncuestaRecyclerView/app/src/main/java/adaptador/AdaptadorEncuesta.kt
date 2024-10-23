package adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ut01_04_encuestarecyclerview.R
import modelo.Encuesta

class AdaptadorEncuesta(private val encuestas: List<Encuesta>) :
    RecyclerView.Adapter<AdaptadorEncuesta.EncuestaViewHolder>() {

    // ViewHolder dentro de la clase AdaptadorEncuesta
    inner class EncuestaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNomDetalle)
        val tvSistemaOperativo: TextView = itemView.findViewById(R.id.tvSoDetalle)
        val tvEspecialidades: TextView = itemView.findViewById(R.id.tvEspDetalle) // Nueva referencia
        val tvHorasEstudio: TextView = itemView.findViewById(R.id.tvHEstudioDetalle) // Nueva referencia
        // ... otros detalles
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EncuestaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_encuesta, parent, false)
        return EncuestaViewHolder(view)
    }

    override fun onBindViewHolder(holder: EncuestaViewHolder, position: Int) {
        val encuesta = encuestas[position]
        holder.tvNombre.text = encuesta?.nombre ?: ""
        holder.tvSistemaOperativo.text = encuesta?.sistemaOperativo ?: ""
        holder.tvEspecialidades.text = "Especialidades: ${encuesta?.especialidades?.joinToString(", ")}" // Mostrar especialidades
        holder.tvHorasEstudio.text = "Horas de estudio: ${encuesta?.horasEstudio}" // Mostrar horas de estudio

    }

    override fun getItemCount(): Int = encuestas.size
}