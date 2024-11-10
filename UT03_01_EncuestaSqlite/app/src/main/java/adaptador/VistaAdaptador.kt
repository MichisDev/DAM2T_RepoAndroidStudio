package adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ut03_01_encuestasqlite.R
import modelo.Encuesta

class VistaAdaptador(private val encuestas: List<Encuesta>) :
    RecyclerView.Adapter<VistaAdaptador.EncuestaViewHolder>() {

    // Crear viewHolder
    inner class EncuestaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Definir vistas
        val tvNombre: TextView = itemView.findViewById(R.id.txNomEncuesta)
        val tvSO: TextView = itemView.findViewById(R.id.txSoEncuesta)
        val tvEspecialidades: TextView = itemView.findViewById(R.id.txEspeEncuesta)
        val tvHoras: TextView = itemView.findViewById(R.id.txHorasEncuesta)

    }

    // Crear viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EncuestaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_encuesta, parent, false)
        return EncuestaViewHolder(itemView)

    }

    override fun getItemCount(): Int {

        return encuestas.size
    }

    override fun onBindViewHolder(holder: EncuestaViewHolder, position: Int) {

        val encuesta = encuestas[position]
        holder.tvNombre.text = encuesta.nombre
        holder.tvSO.text = encuesta.SO
        holder.tvEspecialidades.text = encuesta.especialidad.toString()
        holder.tvHoras.text = encuesta.horasEstudio.toString()

    }
}