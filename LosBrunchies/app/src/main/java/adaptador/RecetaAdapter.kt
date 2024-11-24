package adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.losbrunchies.R
import modelo.Receta

class RecetaAdapter(private val recetas: List<Receta>) :
    RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    class RecetaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenReceta: ImageView = itemView.findViewById(R.id.iv_imagen_receta)
        val nombreReceta: TextView = itemView.findViewById(R.id.tv_nombre_receta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_receta, parent, false)
        return RecetaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        val receta = recetas[position]
        holder.imagenReceta.setImageResource(receta.imagen)
        holder.nombreReceta.text = receta.nombre
    }

    override fun getItemCount(): Int {
        return recetas.size
    }
}